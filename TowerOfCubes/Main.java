package TowerOfCubes;

import java.util.Scanner;

public class Main {

  // Nomes das faces correspondentes
  static final String[] faceNames = { "front", "back", "left", "right", "top", "bottom" };

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int caseNum = 1;

    while (sc.hasNextInt()) {
      int numCubes = sc.nextInt();
      if (numCubes == 0) {
        break;
      }

      // Imprime uma linha em branco entre os casos de teste sucessivos
      if (caseNum > 1) {
        System.out.println();
      }

      // Array para armazenar as cores de cada face dos cubos
      // Faces na ordem: 0=front, 1=back, 2=left, 3=right, 4=top, 5=bottom
      int[][] cubes = new int[numCubes][6];
      for (int i = 0; i < numCubes; i++) {
        for (int j = 0; j < 6; j++) {
          cubes[i][j] = sc.nextInt();
        }
      }

      // Tabelas de programação dinâmica
      // dp[i][j] = altura máxima da torre com a base sendo o cubo 'i' e sua face do
      // topo sendo 'j'
      int[][] dp = new int[numCubes][6];
      int[][] parentCube = new int[numCubes][6];
      int[][] parentFace = new int[numCubes][6];

      int maxLength = 0;
      int bestCube = -1;
      int bestFace = -1;

      // Preenche a tabela de programação dinâmica
      for (int i = 0; i < numCubes; i++) {
        for (int j = 0; j < 6; j++) {
          dp[i][j] = 1; // Todo cubo pode formar uma torre de tamanho 1 sozinho
          parentCube[i][j] = -1;
          parentFace[i][j] = -1;

          int topColor = cubes[i][j];

          // Procura o melhor cubo mais leve (p < i) para colocar em cima
          for (int p = 0; p < i; p++) {
            for (int fp = 0; fp < 6; fp++) {
              // A face de baixo do cubo de cima (p) deve ter a mesma cor do topo do cubo de
              // baixo (i)
              int bottomColorOfP = cubes[p][getOppositeFace(fp)];

              // Se as cores casam, tenta atualizar o tamanho da torre
              if (bottomColorOfP == topColor) {
                if (dp[p][fp] + 1 > dp[i][j]) {
                  dp[i][j] = dp[p][fp] + 1;
                  parentCube[i][j] = p;
                  parentFace[i][j] = fp;
                }
              }
            }
          }

          // Atualiza a resposta máxima encontrada
          if (dp[i][j] > maxLength) {
            maxLength = dp[i][j];
            bestCube = i;
            bestFace = j;
          }
        }
      }

      System.out.println("Case #" + caseNum);
      System.out.println(maxLength);

      // Imprime o caminho de cubos reconstruindo a resposta
      printPath(bestCube, bestFace, parentCube, parentFace);

      caseNum++;
    }

    sc.close();
  }

  // Retorna o índice da face oposta
  private static int getOppositeFace(int face) {
    switch (face) {
      case 0:
        return 1; // front -> back
      case 1:
        return 0; // back -> front
      case 2:
        return 3; // left -> right
      case 3:
        return 2; // right -> left
      case 4:
        return 5; // top -> bottom
      case 5:
        return 4; // bottom -> top
      default:
        return -1;
    }
  }

  // Imprime a solução de forma recursiva (de cima para baixo)
  private static void printPath(int cube, int face, int[][] parentCube, int[][] parentFace) {
    if (cube == -1) {
      return;
    }
    // Primeiro chama para o cubo de cima
    printPath(parentCube[cube][face], parentFace[cube][face], parentCube, parentFace);
    // Depois imprime o cubo atual
    System.out.println((cube + 1) + " " + faceNames[face]);
  }
}
