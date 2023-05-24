import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OfferList {
    private List<Property> offerList;

    public OfferList() {
        this.offerList = new ArrayList<>();
    }

    public void add(String street, String numberOf, String city, Double area, String postalCode, Double price, LocalDate date, Double landArea) throws Exception {
        House house = new House(street, numberOf, city, area, postalCode, price, date, landArea);
        offerList.add(house);
    }


    public void add(String street, String numberOf, String city, Double area, String postalCode, Double price, LocalDate date, int numberOfFloor, int numberOfApart) throws Exception {
        Apartment apartment = new Apartment(street, numberOf, city, area, postalCode, price, date, numberOfFloor, numberOfApart);
        offerList.add(apartment);
    }

    public List<Property> getProperties(Predicate<Property> predicate) {
        List<Property> output = new ArrayList<>();

        for (Property property : offerList) {
            if (predicate.test(property)) {
                output.add(property);
            }
        }

        return output;
    }
}
