package PrimaryArithmetic;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (sc.hasNextLong()) {
      long a = sc.nextLong();
      long b = sc.nextLong();

      if (a == 0 && b == 0) {
        break;
      }

      long carryCount = 0;
      long carry = 0;

      while (a > 0 || b > 0) {
        long digitA = a % 10;
        long digitB = b % 10;

        a /= 10;
        b /= 10;

        long sum = digitA + digitB + carry;

        if (sum > 9) {
          carry = 1;
          carryCount++;
        } else {
          carry = 0;
        }
      }

      if (carryCount == 0) {
        System.out.println("No carry operation.");
      } else if (carryCount == 1) {
        System.out.println("1 carry operation.");
      } else {
        System.out.println(carryCount + " carry operations.");
      }
    }

    sc.close();
  }
}
