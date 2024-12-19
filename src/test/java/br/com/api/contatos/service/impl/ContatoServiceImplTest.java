package br.com.api.contatos.service.impl;

import br.com.api.contatos.AbsrtactTestContainer;
import br.com.api.contatos.dto.ContatoBuscarDTO;
import br.com.api.contatos.dto.ContatoSalvarAlterarDTO;
import br.com.api.contatos.error.exception.ObjectNotFoundException;
import br.com.api.contatos.model.Contato;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

class ContatoServiceIntegrationTest extends AbsrtactTestContainer {

    @Autowired
    private ContatoServiceImpl service;

    @Test
    @Sql(scripts = "/db/scripts/test_case_1_dml.sql", executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @Sql(scripts = "/db/scripts/clear_test_case_dml.sql", executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    void dadoContatosCadastrados_DeveRetornarContatos() {
        final Pageable pageable = PageRequest.of(0, 10);
        final Page<ContatoBuscarDTO> contatos = service.buscarTodosOsContatosPorNome(null, pageable);

        assertThat(contatos.getTotalElements()).isEqualTo(1L);
        assertThat(contatos.getContent().get(0).getNome()).isNotNull().isEqualTo("João da Silva");
    }

    @Test
    @Sql(scripts = "/db/scripts/test_case_1_dml.sql", executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @Sql(scripts = "/db/scripts/clear_test_case_dml.sql", executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    void dadoUmIdCadastrado_DeveRetornarUmContato() {
        final Contato contato = service.buscarPorId(1L);
        assertThat(contato).isNotNull();
        assertThat(contato.getNome()).isNotNull().isEqualTo("João da Silva");
    }

    @Test
    void dadoUmIdNaoCadastrado_DeveEstourarUmaExceptionDeObjetoNaoEncontrado() {
        assertThrows(ObjectNotFoundException.class, () -> service.buscarPorId(2L));
    }

    @Test
    @Sql(scripts = "/db/scripts/clear_test_case_dml.sql", executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    void dadoUmContatoSalvarDTONaoCadastrado_DeveRetornarUmContatoSalvo() {

        final ContatoSalvarAlterarDTO dto = new ContatoSalvarAlterarDTO(
                "Vinicius Pontes",
                "(14) 99837-1897",
                "vinicius@gmail.com",
                "18682-841",
                "183"
        );

        final Contato contato = service.salvar(dto);

        assertThat(contato).isNotNull();
        assertThat(contato.getNome()).isNotNull().isEqualTo("Vinicius Pontes");

    }

    @Test
    @Sql(scripts = "/db/scripts/test_case_1_dml.sql", executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @Sql(scripts = "/db/scripts/clear_test_case_dml.sql", executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    void dadoUmContatoAlterarDTOCadastrado_DeveRetornarUmContatoSalvo() {

        final ContatoSalvarAlterarDTO dto = new ContatoSalvarAlterarDTO(
                "Vinicius Pontes",
                "(14) 99837-1897",
                "vinicius@gmail.com",
                "18682-841",
                "183"
        );

        final Contato contato = service.alterar(1L, dto);

        assertThat(contato).isNotNull();
        assertThat(contato.getNome()).isNotNull().isEqualTo("Vinicius Pontes");

    }

    @Test
    @Sql(scripts = "/db/scripts/test_case_1_dml.sql", executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    @Sql(scripts = "/db/scripts/clear_test_case_dml.sql", executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    void dadoUmContatoCadastrado_DeveEstourarUmaExceptionDeObjetoNaoEncontradoAoBuscarContato() {

        service.deletar(1L);

        assertThrows(ObjectNotFoundException.class, () -> service.buscarPorId(1L));

    }

}