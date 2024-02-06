package lab01.zad04;

import java.util.Scanner;

public class Zad04 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;

        while (true) {
            System.out.print("Podaj liczbe naturalna n: ");
            if (input.hasNextInt()) {
                n = input.nextInt();
                if (n >= 1) {
                    break;
                }
            }
            input.nextLine();
            System.out.println("Bledna liczba");
        }

        input.close();

        String result = generatePatterns(n);
        System.out.println(result);
    }

    public static String generatePatterns(int n) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            sb.append("x".repeat(i));
            sb.append("\n");
        }

        for (int i = 1; i <= n; i++) {
            sb.append("x".repeat(n - i + 1));
            sb.append("\n");
        }

        for (int i = n; i >= 1; i--) {
            String spaces = " ".repeat(n - i);
            String xs = "x".repeat(i);
            sb.append(spaces).append(xs).append("\n");
        }

        for (int i = 1; i <= n; i++) {
            String spaces = " ".repeat(n - i);
            String xs = "x".repeat(i);
            sb.append(spaces).append(xs).append("\n");
        }

        return sb.toString();
    }
}
