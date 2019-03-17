package daniella.iths.se.librarystorageclient.resources;

import java.util.Set;

public class Author {

    private long author_id;
    private String firstName;
    private String lastName;
    private Set<Book> books;

    public Author(){

    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        String aut = "First name: " + firstName + "\n" +
                    "Last name: " + lastName + "\n" +
                     "Author ID: " + author_id + "\n";
                if(books != null)
                    aut += "Books:\n" + books + "\n";

                return aut;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
