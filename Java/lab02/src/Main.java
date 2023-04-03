import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        printBaseMenu();
        Scanner scanner = new Scanner(System.in);
        Cylinder cylinder = new Cylinder();
        int option;
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1 -> System.out.printf("Radius: %s, Height: %s\n", cylinder.getRadius(), cylinder.getHeight());
                case 2 -> {
                    System.out.println("Set value of radius: ");
                    cylinder.setRadius(scanner.nextDouble());
                }
                case 3 -> {
                    System.out.println("Set value of height: ");
                    cylinder.setHeight(scanner.nextDouble());
                }
                case 4 -> System.out.printf("Base surface area of this cylinder is: %s\n", cylinder.baseSurfaceArea());
                case 5 -> System.out.printf("Side surface area of this cylinder is: %s\n", cylinder.sideSurfaceArea());
                case 6 -> System.out.printf("Total surface area of this cylinder is: %s\n", cylinder.surfaceArea());
                case 7 -> System.out.printf("Volume of this cylinder is: %s\n", cylinder.volume());
                case 8 -> isRunning = false;
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                Menu:\s
                1 - Get radius and height
                2 - Set radius
                3 - Set height
                4 - Get base surface area
                5 - Get side surface area
                6 - Get surface area
                7 - Get volume
                8 - Exit""");
    }

    private static void printBaseMenu() {
        System.out.println("""
                This application allows you to create a cylinder and calculate its volume and surface areas.
                You can also get the radius and height of the cylinder and set their values.
                """);
    }
}