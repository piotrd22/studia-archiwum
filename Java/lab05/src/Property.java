import java.time.LocalDate;

sealed abstract class Property permits House, Apartment {
    public static LocalDate TODAY = LocalDate.now();

    private String street;
    private String numberOf;
    private String city;
    private Double area;
    private String postalCode;
    private Double price;
    private LocalDate date;

    public Property(String street, String numberOf, String city, Double area, String postalCode, Double price, LocalDate date) throws Exception {
        if (area < 0 || price < 0) throw new Exception("Area and price must be higher than 0!");
        if (date.isBefore(TODAY)) throw new Exception("Date is wrong!");
        this.street = street;
        this.numberOf = numberOf;
        this.city = city;
        this.area = area;
        this.postalCode = postalCode;
        this.price = price;
        this.date = date;
    }

    public String getStreet() {
        return street;
    }

    public String getNumberOf() {
        return numberOf;
    }

    public String getCity() {
        return city;
    }

    public Double getArea() {
        return area;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("Street: %s, Number: %s, City: %s, Area: %s, Postal Code: %s, Price: %s, From date: %s",
                street, numberOf, city, area, postalCode, price, date);
    }
}
