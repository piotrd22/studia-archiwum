import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        printBaseMenu();
        Scanner scanner = new Scanner(System.in);
        GradeList grades = new GradeList();
        int option;
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            option = scanner.nextInt();
            try {
                switch (option) {
                    case 1 -> System.out.printf("All grades: %s\n", grades.getGrades());
                    case 2 -> grades.removeGrades();
                    case 3 -> grades.addGrade(scanner.nextDouble());
                    case 4 -> System.out.printf("Average grade is: %s\n", grades.averageGrade());
                    case 5 -> System.out.printf("Highest grade is: %s\n", grades.highestGrade());
                    case 6 -> System.out.printf("Lowest grade is: %s\n", grades.lowestGrade());
                    case 7 -> isRunning = false;
                    default -> System.out.println("Invalid option!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                Menu:\s
                1 - Get all grades
                2 - Remove all grades
                3 - Add new grade
                4 - Get average grade
                5 - Get highest grade
                6 - Get lowest grade
                7 - Exit
                """);
    }

    private static void printBaseMenu() {
        System.out.println("""
                The program allows you to create a grade book.
                Then you can add a rating, view the average rating, view the highest and lowest rating.
                You can also see all ratings or clear all ratings.
                """);
    }
}