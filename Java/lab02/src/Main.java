import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                Welcome to the Cylinder program!
                This application lets you create a cylinder and get its volume and surface area (total, base and side).
                You can also get the radius and height of the cylinder and change their values.
                """);

        Scanner scanner = new Scanner(System.in);

        Cylinder cylinder = new Cylinder();
        System.out.println("Set base value of radius: ");
        cylinder.setRadius(scanner.nextDouble());
        System.out.println("Set base value of height: ");
        cylinder.setHeight(scanner.nextDouble());

        int option;
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1 -> {
                    double height = cylinder.getHeight();
                    double radius = cylinder.getRadius();
                    System.out.printf("Radius: %s, Height: %s\n", radius, height);
                }
                case 2 -> {
                    System.out.println("Set value of radius: ");
                    cylinder.setRadius(scanner.nextDouble());
                }
                case 3 -> {
                    System.out.println("Set value of height: ");
                    cylinder.setHeight(scanner.nextDouble());
                }
                case 4 -> {
                    double baseSurfaceArea = cylinder.baseSurfaceArea();
                    System.out.printf("Base surface area of this cylinder is: %s\n", baseSurfaceArea);
                }
                case 5 -> {
                    double sideSurfaceArea = cylinder.sideSurfaceArea();
                    System.out.printf("Side surface area of this cylinder is: %s\n", sideSurfaceArea);
                }
                case 6 -> {
                    double surfaceArea = cylinder.surfaceArea();
                    System.out.printf("Total surface area of this cylinder is: %s\n", surfaceArea);
                }
                case 7 -> {
                    double volume = cylinder.volume();
                    System.out.printf("Volume of this cylinder is: %s\n", volume);
                }
                case 8 -> isRunning = false;
                default -> System.out.println("Invalid option!");
            }
        }
    }

    public static void printMenu() {
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
}