// Solução do problema "WERTYU"
// Número UVa: 10082
// URL: https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=12&page=show_problem&problem=1023

package WERTYU;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String keyboard = " `1234567890-=QWERTYUIOP[]\\ASDFGHJKL;'ZXCVBNM,./";

        while (in.hasNextLine()) {
            String line = in.nextLine();
            StringBuilder out = new StringBuilder();

            for (char ch : line.toCharArray()) {
                int idx = keyboard.indexOf(ch);
                if (idx > 0) {
                    out.append(keyboard.charAt(idx - 1));
                } else {
                    out.append(ch);
                }
            }

            System.out.println(out.toString());
        }

        in.close();
    }
}
