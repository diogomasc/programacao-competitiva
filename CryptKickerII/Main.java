package CryptKickerII;

import java.util.*;

public class Main {

    static final String FRASE_CHAVE = "the quick brown fox jumps over the lazy dog";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int qntCasos = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < qntCasos; i++) {

            // linha em branco após o número de casos
            if (sc.hasNextLine()) {
                sc.nextLine();
            }

            List<String> linhasCifradas = new ArrayList<>();

            // lê as linhas do caso até encontrar uma linha em branco ou EOF
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                if (linha.isEmpty()) break;
                linhasCifradas.add(linha);
            }

            char[] mapaSubstituicao = null;

            // tenta cada linha como candidata à frase-chave
            for (String linhaCifrada : linhasCifradas) {
                if (linhaCifrada.length() != FRASE_CHAVE.length()) continue;

                char[] mapaCandidato = construirMapa(linhaCifrada, FRASE_CHAVE);
                if (mapaCandidato != null) {
                    mapaSubstituicao = mapaCandidato;
                    break;
                }
            }

            if (mapaSubstituicao == null) {
                System.out.println("No solution.");
            } else {
                for (String linhaCifrada : linhasCifradas) {
                    System.out.println(decodificarLinha(linhaCifrada, mapaSubstituicao));
                }
            }

            if (i != qntCasos - 1) {
                System.out.println();
            }
        }

        sc.close();
    }

    // constrói o mapa de substituição a partir de uma linha candidata e da frase-chave
    static char[] construirMapa(String linhaCifrada, String fraseChave) {
        char[] mapa = new char[26];         // mapa[letraCifrada] = letraOriginal
        boolean[] letraJaUsada = new boolean[26];

        for (int i = 0; i < linhaCifrada.length(); i++) {
            char caractereCifrado = linhaCifrada.charAt(i);
            char caractereChave   = fraseChave.charAt(i);

            // espaços precisam coincidir
            if (caractereChave == ' ') {
                if (caractereCifrado != ' ') return null;
                continue;
            }

            if (caractereCifrado < 'a' || caractereCifrado > 'z') return null;

            int indiceCifrado = caractereCifrado - 'a';
            int indiceChave   = caractereChave   - 'a';

            if (mapa[indiceCifrado] == 0 && !letraJaUsada[indiceChave]) {
                // ainda não mapeado: cria novo vínculo
                mapa[indiceCifrado] = caractereChave;
                letraJaUsada[indiceChave] = true;
            } else if (mapa[indiceCifrado] != caractereChave) {
                // conflito: mesma letra cifrada tentando virar letras diferentes
                return null;
            }
        }

        // opcional: garantir que todas as letras da frase-chave foram cobertas
        for (int i = 0; i < 26; i++) {
            char letra = (char) ('a' + i);
            if (FRASE_CHAVE.indexOf(letra) != -1 && !letraJaUsada[i]) {
                return null;
            }
        }

        return mapa;
    }

    // aplica o mapa de substituição a uma linha
    static String decodificarLinha(String linhaCifrada, char[] mapaSubstituicao) {
        StringBuilder linhaDecodificada = new StringBuilder();

        for (char caractereCifrado : linhaCifrada.toCharArray()) {
            if (caractereCifrado == ' ') {
                linhaDecodificada.append(' ');
            } else {
                char caractereOriginal = mapaSubstituicao[caractereCifrado - 'a'];
                linhaDecodificada.append(caractereOriginal == 0 ? '?' : caractereOriginal);
            }
        }

        return linhaDecodificada.toString();
    }
}
