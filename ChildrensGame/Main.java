// Solução do problema "Children's Game"
// Número UVa: 10905
// URL: https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1846

package ChildrensGame;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (true) {
      int n = sc.nextInt();
      if (n == 0)
        break; // Entrada com 0 encerra o programa

      String[] numbers = new String[n];
      for (int i = 0; i < n; i++) {
        numbers[i] = sc.next(); // Lê cada número como string
      }

      // A lógica abaixo usa Stream (sequência de dados que permite operações
      // funcionais (map, filter, sort, etc.)) para ordenar e concatenar os números
      String result = Arrays.stream(numbers)
          // Comparador personalizado: decide qual número deve vir antes
          // concatenando-os em ordens diferentes (b+a vs a+b)
          // e escolhendo a combinação que gera o maior número
          // Exemplo: a=56, b=9:
          // (b+a) = "956" e (a+b) = "569"
          // Como "956" > "569", troca-se "9" com o "56"
          .sorted((a, b) -> (b + a).compareTo(a + b))
          // Junta todos os elementos ordenados em uma única string
          .collect(Collectors.joining());

      // Exibe o maior número possível formado pela concatenação
      System.out.println(result);
    }

    sc.close();
  }
}
