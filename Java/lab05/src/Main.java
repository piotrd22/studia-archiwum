import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OfferList offerList = new OfferList();
        int option;
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            option = scanner.nextInt();
            try {
                switch (option) {
                    case 1 -> addOffer(scanner, offerList, 1);
                    case 2 -> addOffer(scanner, offerList, 2);
                    case 3 -> printAllHouses(offerList);
                    case 4 -> printAllApartments(offerList);
                    case 5 -> printHousesWithCityAndLandArea(scanner, offerList);
                    case 6 -> printApartsWithCityPriceAndFloor(scanner, offerList);
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
                1 - Add new house
                2 - Add new apartment
                3 - Get all houses offers
                4 - Get all apartments offers
                5 - Get all houses offers from city and land area
                6 - Get all apartments offers from price and number of floor
                7 - Exit
                """);
    }

    private static void addOffer(Scanner scanner, OfferList offerList, int choice) throws Exception {
        System.out.println("Enter street");
        String street = scanner.next();
        System.out.println("Add number of building");
        String numberOf = scanner.next();
        System.out.println("Add city");
        String city = scanner.next();
        System.out.println("Add area (for example 31.15) in m^2");
        Double area = scanner.nextDouble();
        System.out.println("Add postal code");
        String postalCode = scanner.next();
        System.out.println("Add price in PLN (for example 300000.0)");
        Double price = scanner.nextDouble();
        System.out.println("Add date YYYY-MM-DD");
        LocalDate date = LocalDate.parse(scanner.next());

        if (choice == 1) {
            System.out.println("Add land area in m^2");
            Double landArea = scanner.nextDouble();
            offerList.add(street, numberOf, city, area, postalCode, price, date, landArea);
        } else {
            System.out.println("Add number of apartment");
            String numberOfApartString = scanner.next();
            int numberOfApart = Integer.parseInt(numberOfApartString);
            System.out.println("Add number of floor");
            String numberOfFloorString = scanner.next();;
            int numberOfFloor = Integer.parseInt(numberOfFloorString);
            offerList.add(street, numberOf, city, area, postalCode, price, date, numberOfFloor, numberOfApart);
        }
    }

    private static void printAllHouses(OfferList offerList) {
        Predicate<Property> predicate = (house) ->
                house instanceof House && (house.getDate().isAfter(Property.TODAY) || house.getDate().equals(Property.TODAY));
        List<Property> arr = offerList.getProperties(predicate);
        propertyPrinter(arr);
    }

    private static void printAllApartments(OfferList offerList) {
        Predicate<Property> predicate = (apartment) ->
                apartment instanceof Apartment && (apartment.getDate().isAfter(Property.TODAY) || apartment.getDate().equals(Property.TODAY));
        List<Property> arr = offerList.getProperties(predicate);
        propertyPrinter(arr);
    }

    private static void printHousesWithCityAndLandArea(Scanner scanner, OfferList offerList) {
        System.out.println("Add city");
        String city = scanner.next();;
        System.out.println("Add min land area");
        Double landArea = scanner.nextDouble();

        Predicate<Property> predicate = (house) ->
                house instanceof House &&
                        (house.getDate().isAfter(Property.TODAY) || house.getDate().equals(Property.TODAY)) &&
                        house.getCity().equals(city) &&
                        ((House) house).getLandArea() >= landArea;

        List<Property> arr = offerList.getProperties(predicate);
        propertyPrinter(arr);
    }

    private static void printApartsWithCityPriceAndFloor(Scanner scanner, OfferList offerList) {
        System.out.println("Add price");
        Double price = scanner.nextDouble();
        System.out.println("Add min floor");
        int floor = scanner.nextInt();

        Predicate<Property> predicate = (apartment) ->
                apartment instanceof Apartment &&
                        (apartment.getDate().isAfter(Property.TODAY) || apartment.getDate().equals(Property.TODAY)) &&
                        ((Apartment) apartment).getNumberOfFloor() >= floor &&
                        apartment.getPrice() <= price;

        List<Property> arr = offerList.getProperties(predicate);
        propertyPrinter(arr);
    }

    private static void propertyPrinter(List<Property> arr) {
        for (Property property : arr) {
            System.out.println(property.toString() + "\n");
        }
    }
}