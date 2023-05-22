import java.time.LocalDate;

public final class House extends Property {
    private Double landArea;

    public House(String street, String numberOf, String city, Double area, String postalCode, Double price, LocalDate date, Double landArea) throws Exception {
        super(street, numberOf, city, area, postalCode, price, date);
        if (landArea < 0) throw new Exception("Land area must be higher than 0!");
        this.landArea = landArea;
    }

    public Double getLandArea() {
        return landArea;
    }

    @Override
    public String toString() {
        return String.format("%s, Land area: %s",
                super.toString(), landArea);
    }
}
