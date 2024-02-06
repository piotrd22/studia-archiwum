package lab01.zad05;

public class Main {
    public static void main(String[] args) {
        Book book = new Book();
        book.setPrice(5);
        Author author = new Author();
        author.setName("Henryk");
        author.setEmail("henryk@wp.pl");
        author.setGender(Author.Gender.MALE);
        book.setAuthor(author);
        book.setName("Krzyzacy");
        System.out.println(book.toString());
        System.out.println(author.toString());
    }
}