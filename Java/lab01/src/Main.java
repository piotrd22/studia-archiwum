import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Jezeli chcesz wyjsc z programu wpisz -1");
        System.out.println("Wybierz, czy chcesz obliczac n! czy 2^n");
        System.out.println("Aby wybrac n! wpisz n, aby wybrac 2^n wpisz np");

        String check = scan.nextLine();

        boolean state = true;

        while (state) {

            if (check.equals("n")) {
                System.out.println("Podaj liczbe n do obliczenia 2^n lub -1 dla wyjscia: ");
                int n = scan.nextInt();

                if (n == -1) state = false;
                else twoToNPrinter(n);
            }

            else if (check.equals("np")) {
                System.out.println("Podaj liczbe n do obliczenia n! lub -1 dla wyjscia: ");
                int n = scan.nextInt();

                if (n == -1) state = false;
                else factorialPrinter(n);
            }

            else state = false;
        }
    }

    public static long factorial(int n) throws IllegalArgumentException {
        long acc = 1;

        if (n < 0) throw new IllegalArgumentException("Liczba musi byc >= 0");

        if (n == 0 || n == 1) return acc;

        for (int i = 1; i <= n; i++) {
            acc = acc * i;
        }
        return acc;
    }

    public static void factorialPrinter(int n) {
        long result = factorial(n);
        System.out.println("Silnia " + n + " == " + result);
    }

    public static long twoToN(int n) throws IllegalArgumentException {
        long acc = 1;

        if (n < 1) throw new IllegalArgumentException("Liczba musi byc >= 1");

        for (int i = 1; i <= n; i++) {
            acc = acc * 2;
        }
        return acc;
    }

    public static void twoToNPrinter(int n) {
        long result = twoToN(n);
        System.out.println("2^" + n + " == " + result);
    }
}