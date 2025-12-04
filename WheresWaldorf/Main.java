package WheresWaldorf;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    // Deslocamentos nas 8 direções possíveis em uma matriz:
    // (-1,-1) (-1,0) (-1,1)
    // ( 0,-1) ( 0,0) ( 0,1)
    // ( 1,-1) ( 1,0) ( 1,1)
    static final int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] dCol = {-1,  0,  1,-1, 1,-1, 0, 1};

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        // Número de casos de teste (formato UVa)
        int testCases = in.nextInt();

        for (int tc = 0; tc < testCases; tc++) {

            // Lê dimensões da grade: número de linhas e colunas
            int rows = in.nextInt();
            int cols = in.nextInt();

            // Cria a matriz de caracteres
            char[][] grid = new char[rows][cols];

            // Lê cada linha da grade e converte para minúsculas
            for (int r = 0; r < rows; r++) {
                String line = in.next();                 // ex: "abcDEFGhigg"
                grid[r] = line.toLowerCase().toCharArray(); // guarda como ['a','b',...]
            }

            // Número de palavras a procurar na grade
            int wordsCount = in.nextInt();

            // Para cada palavra, procura e imprime a posição
            for (int w = 0; w < wordsCount; w++) {
                String word = in.next().toLowerCase(Locale.ROOT);
                findAndPrintPosition(grid, word);
            }

            // Linha em branco entre casos (exceto depois do último)
            if (tc != testCases - 1) {
                System.out.println();
            }
        }

        in.close();
    }

    /**
     * Procura a palavra na grade.
     * Imprime a posição (linha, coluna) 1-based da primeira ocorrência encontrada.
     */
    static void findAndPrintPosition(char[][] grid, String word) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Varre todas as posições da matriz como possíveis inícios da palavra
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // Se a primeira letra não bate, nem tenta
                if (grid[r][c] != word.charAt(0)) continue;

                // Se a partir daqui, em alguma direção, a palavra encaixa, imprime e sai
                if (matchesFrom(grid, word, r, c)) {
                    System.out.println((r + 1) + " " + (c + 1)); // +1 para ficar 1-based
                    return;
                }
            }
        }
    }

    /**
     * Verifica se a palavra aparece na grade começando em (startRow, startCol)
     * em alguma das 8 direções codificadas em dRow/dCol.
     */
    static boolean matchesFrom(char[][] grid, String word, int startRow, int startCol) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Testa as 8 direções
        for (int d = 0; d < 8; d++) {
            int r = startRow;
            int c = startCol;
            int idx = 0; // índice da letra atual da palavra

            // Caminha na direção (dRow[d], dCol[d]) enquanto as letras forem iguais
            while (idx < word.length()) {
                // Se sair dos limites da matriz, para
                if (r < 0 || r >= rows || c < 0 || c >= cols) break;

                // Se a letra da grade não casar com a da palavra, para
                if (grid[r][c] != word.charAt(idx)) break;

                // Avança para a próxima letra e próxima célula na direção escolhida
                idx++;
                r += dRow[d];
                c += dCol[d];
            }

            // Se percorreu todas as letras da palavra, deu match nessa direção
            if (idx == word.length()) {
                return true;
            }
        }

        // Não encaixou em nenhuma das 8 direções
        return false;
    }
}
