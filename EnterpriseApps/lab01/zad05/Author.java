package lab01.zad05;

public class Author {
    public static enum Gender {
        MALE, FEMALE
    }

    private String name;
    private String email;
    private Gender gender;

    public Author() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Author[" +
                "name=" + name +
                ", email=" + email +
                ", gender=" + gender +
                ']';
    }
}