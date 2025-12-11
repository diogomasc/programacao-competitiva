package CryptKickerII;

import java.util.*;

public class Main {

    static final String FRASE_CHAVE = "the quick brown fox jumps over the lazy dog";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int qntCasos = Integer.parseInt(sc.nextLine().trim());

        // consome a linha em branco imediatamente após o número de casos
        if (sc.hasNextLine()) {
            sc.nextLine();
        }

        for (int caso = 0; caso < qntCasos; caso++) {

            List<String> linhasCifradas = new ArrayList<>();

            // lê todas as linhas do caso até encontrar uma linha em branco ou EOF
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                if (linha.isEmpty()) break;
                linhasCifradas.add(linha);
            }

            processarCaso(linhasCifradas);

            if (caso != qntCasos - 1) {
                System.out.println();
            }
        }

        sc.close();
    }

    static void processarCaso(List<String> linhasCifradas) {
        char[] mapaSubstituicao = null;

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
    }

    static char[] construirMapa(String linhaCifrada, String fraseChave) {
        if (linhaCifrada.length() != fraseChave.length()) return null;

        char[] mapa = new char[128];
        boolean[] letraCifradaUsada   = new boolean[128];
        boolean[] letraDecifradaUsada = new boolean[128];

        for (int i = 0; i < 128; i++) {
            mapa[i] = (char) i;
        }

        for (int i = 0; i < linhaCifrada.length(); i++) {
            char c = linhaCifrada.charAt(i);   // cifra
            char k = fraseChave.charAt(i);     // claro

            if (k == ' ') {
                if (c != ' ') return null;
                continue;
            }

            if (!letraCifradaUsada[c] && !letraDecifradaUsada[k]) {
                mapa[c] = k;
                letraCifradaUsada[c]   = true;
                letraDecifradaUsada[k] = true;
            } else if (mapa[c] != k) {
                return null;
            }
        }

        // garante que a linha candidata realmente decodifica na frase-chave
        String teste = decodificarLinha(linhaCifrada, mapa);
        if (!teste.equals(fraseChave)) return null;

        return mapa;
    }

    static String decodificarLinha(String linhaCifrada, char[] mapaSubstituicao) {
        StringBuilder sb = new StringBuilder();
        for (char c : linhaCifrada.toCharArray()) {
            sb.append(mapaSubstituicao[c]);
        }
        return sb.toString();
    }
}
