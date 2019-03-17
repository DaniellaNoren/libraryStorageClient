package daniella.iths.se.librarystorageclient.resources;

import java.util.HashSet;
import java.util.Set;

public class Book {

    private long id;
    private String title;

    private boolean available = true;
    private String returnDate;
    private String postedAt;
    private String lastUpdatedAt;
    private long user_id;
    private Set<Author> authors;

    public Book(){

    }

    public void setPostedAt(String postedAt){
        this.postedAt = postedAt;
    }
    public String getPostedAt(){
        return postedAt;
    }

    public String getLastUpdatedAt(){
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String date){
        if(date != null)this.lastUpdatedAt = date.toString();
    }

    public Book(String title){
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }


    public void addAuthor (Author author) {
        if (authors == null)
            authors = new HashSet<>();
        authors.add(author);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public long getUser_id(){
        return user_id;
    }

    public void setUser_Id(long user_id){
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        String book =   "Title: " + title + "\n" +
                        "Book ID: " + id + "\n" +
                        "Available: " + available + "\n" +
                        "User ID: " + user_id + "\n" +
                        "Return-date: " + returnDate + "\n" +
                        "Posted at: " + postedAt + "\n" +
                        "Last updated at: " + lastUpdatedAt + "\n";
        if(authors != null)
            book += "Author/s: \n" + authors + "\n";

        return book;
    }

}
