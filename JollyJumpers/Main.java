package JollyJumpers;

import java.util.Scanner;

public class Main {

    // Verifica se uma sequência é Jolly
    static boolean isJollySequence(int[] sequence) {
        int length = sequence.length;

        // Qualquer sequência de 1 elemento é Jolly
        if (length <= 1) return true;

        // differencesPresent[d] indica se a diferença d apareceu (1..length-1)
        boolean[] differencesPresent = new boolean[length];

        // Calcula diferenças absolutas entre vizinhos
        for (int i = 1; i < length; i++) {
            int difference = Math.abs(sequence[i] - sequence[i - 1]);
            if (difference >= 1 && difference <= length - 1) {
                differencesPresent[difference] = true;
            } else {
                // diferença fora do intervalo já invalida
                return false;
            }
        }

        // Confere se TODAS as diferenças 1..length-1 apareceram
        for (int requiredDiff = 1; requiredDiff <= length - 1; requiredDiff++) {
            if (!differencesPresent[requiredDiff]) {
                return false;         // faltou alguma diferença obrigatória
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cada linha: n seguido de n inteiros, até EOF.
        while (scanner.hasNextInt()) {
            int length = scanner.nextInt();
            int[] sequence = new int[length];

            for (int i = 0; i < length; i++) {
                sequence[i] = scanner.nextInt();
            }

            boolean jolly = isJollySequence(sequence);
            if (jolly) {
                System.out.println("Jolly");
            } else {
                System.out.println("Not jolly");
            }
        }

        scanner.close();
    }
}

