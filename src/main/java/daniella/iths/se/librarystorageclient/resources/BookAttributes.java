package daniella.iths.se.librarystorageclient.resources;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class BookAttributes {

    private String title;
    @Autowired
    private Set<Author> authors;

    public BookAttributes(){

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

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author){
        authors.add(author);
    }

    public void create(){
        if(this.getAuthors() == null)
            authors = new HashSet<>();
        if(this.getTitle() == null)
            this.title = "Default title";
    }
}
