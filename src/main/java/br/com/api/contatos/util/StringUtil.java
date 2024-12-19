package br.com.api.contatos.util;

public abstract class StringUtil {

    public static String removerCaracteresEspeciais(String texto) {
        return texto.replaceAll("[^a-zA-Z0-9\\s]", "");
    }

}
