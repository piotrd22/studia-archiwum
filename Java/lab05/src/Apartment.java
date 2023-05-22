import java.time.LocalDate;

public final class Apartment extends Property {
    private int numberOfFloor;
    private int numberOfApart;

    public Apartment(String street, String numberOf, String city, Double area, String postalCode, Double price, LocalDate date, int numberOfFloor, int numberOfApart) throws Exception {
        super(street, numberOf, city, area, postalCode, price, date);
        if (numberOfFloor < 0) throw new Exception("Number of floor must be higher than 0!");
        this.numberOfFloor = numberOfFloor;
        this.numberOfApart = numberOfApart;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    @Override
    public String toString() {
        return String.format("%s, Number of floor: %s, Number of apartment: %s",
                super.toString(), numberOfFloor, numberOfApart);
    }
}
