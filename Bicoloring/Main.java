package Bicoloring;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int numVertices;
    while ((numVertices = sc.nextInt()) != 0) {
      int numEdges = sc.nextInt();

      // Lista de adjacência: para cada vértice, armazena seus vizinhos
      ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
      for (int i = 0; i < numVertices; i++) {
        adjacencyList.add(new ArrayList<>());
      }

      // Lê as arestas
      for (int i = 0; i < numEdges; i++) {
        int u = sc.nextInt();
        int v = sc.nextInt();
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u);
      }

      // Cores: -1 = não visitado, 0 = cor A, 1 = cor B
      int[] colors = new int[numVertices];
      for (int i = 0; i < numVertices; i++) {
        colors[i] = -1;
      }

      // Verifica se é bipartido
      boolean isBipartite = true;

      // Tenta colorir cada componente do grafo
      for (int i = 0; i < numVertices && isBipartite; i++) {
        if (colors[i] == -1) {
          // BFS, Breadth-First Search ou Busca em Largura, a partir do vértice i
          Queue<Integer> queue = new LinkedList<>();
          queue.add(i);
          colors[i] = 0;

          while (!queue.isEmpty() && isBipartite) {
            int vertex = queue.remove();

            // Verifica cada vizinho do vértice atual
            for (int neighbor : adjacencyList.get(vertex)) {
              if (colors[neighbor] == -1) {
                // Vizinho não foi visitado: colore com cor oposta
                colors[neighbor] = 1 - colors[vertex];
                queue.add(neighbor);
              } else if (colors[neighbor] == colors[vertex]) {
                // Vizinho tem a mesma cor: não é bipartido!
                isBipartite = false;
                break;
              }
            }
          }
        }
      }

      if (isBipartite) {
        System.out.println("BICOLORABLE.");
      } else {
        System.out.println("NOT BICOLORABLE.");
      }
    }

    sc.close();
  }
}