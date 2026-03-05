package PizzaCutting;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    try {
      while (sc.hasNextLong()) {
        long n = sc.nextLong();
        if (n >= 0) {
          System.out.println(((n * n) + n + 2) / 2);
        } else {
          break;
        }
      }
    } finally {
      sc.close();
    }
  }
}