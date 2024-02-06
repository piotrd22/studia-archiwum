package lab01.zad02;

public class Zad02 {
    public static void main(String[] args) {
        System.out.println(isArmstrongNumber(9));
        System.out.println(isArmstrongNumber(10));
    }

    public static boolean isArmstrongNumber(int n) {
        int originalN = n;
        int numberOfDigits = String.valueOf(n).length();
        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += Math.pow(digit, numberOfDigits);
            n /= 10;
        }

        return sum == originalN;
    }
}