package daniella.iths.se.librarystorageclient.communication;

import daniella.iths.se.librarystorageclient.resources.Author;
import daniella.iths.se.librarystorageclient.resources.Book;
import daniella.iths.se.librarystorageclient.resources.ListOfObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StorageCommunicatorTest {

    StorageCommunicator storageCommunicator;

    @Before
    public void setUp(){
        storageCommunicator = new StorageCommunicator(new RestTemplate());
    }

    @Test
    public void getAllBooks() {
        List<Book> list = storageCommunicator.getAllBooks();
        assertNotNull(list);
        for(Book b : list)
            System.out.println(b);
    }

    @Test
    public void getOneBook(){
        Book b = storageCommunicator.getOneBook(1);
        assertNotNull(b);
        //assertEquals(1, b.getId());
        System.out.println(b);
    }

    @Test
    public void getAllAuthors(){
        List<Author> list = storageCommunicator.getAllAuthors();
        assertNotNull(list);
        for(Author a : list)
            System.out.println(a);
    }
//
//    @Test
//    public void addBook(){
//        Book b = new Book("cool title and stuff");
//        b.setLastUpdatedAt(new Date().toString());
//        Book a = storageCommunicator.addBook(b);
//        System.out.println(a);
//
//    }

}