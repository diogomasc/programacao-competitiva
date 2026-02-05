// Programa: Resolver o Problema da Família do Vito

// Objetivo:
// Encontrar a localização ótima para a casa do Vito que minimize
// a soma total das distâncias até todos os seus parentes.

// Base matemática:
// A mediana de um conjunto de números é o ponto que minimiza a
// soma das distâncias absolutas. Isso significa que se temos
// endereços s1, s2, ..., sn, queremos encontrar a posição x que
// minimize a função: f(x) = |s1 - x| + |s2 - x| + ... + |sn - x|

package VitosFamily;

import java.util.Arrays;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1. Ler quantos casos de teste precisamos resolver (quantidade de linhas de
        // entrada)
        int quantidadeTestes = scanner.nextInt();

        for (int teste = 0; teste < quantidadeTestes; teste++) {
            int quantidadeParentes = scanner.nextInt();
            // 2. Criar uma lista para armazenar os endereços de todos os parentes
            int[] enderecos = new int[quantidadeParentes];

            // 3. Ler cada endereço onde um parente mora
            for (int i = 0; i < quantidadeParentes; i++) {
                enderecos[i] = scanner.nextInt();
            }

            // 4. Organizar todos os endereços em ordem crescente.
            Arrays.sort(enderecos);

            // 5. A mediana minimiza a soma das distâncias, pela razão matemática de sempre
            // está no centro de um conjunto ordenado.
            // Selecionar o elemento do meio do array ordenado
            int mediana = enderecos[quantidadeParentes / 2];

            int somaDistancias = 0;
            // 6. Calcular a soma das distâncias entre cada endereço e a mediana adicionando
            // o valor absoluto de cada distância
            for (int endereco : enderecos) {
                somaDistancias += Math.abs(endereco - mediana);
            }

            System.out.println(somaDistancias);
        }

        scanner.close();
    }
}