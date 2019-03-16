package daniella.iths.se.librarystorageclient.communication;

import daniella.iths.se.librarystorageclient.resources.Author;
import daniella.iths.se.librarystorageclient.resources.Book;
import daniella.iths.se.librarystorageclient.resources.BookAttributes;
import daniella.iths.se.librarystorageclient.resources.ListOfObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageCommunicator implements BookStorage {

    RestTemplate restTemplate;

    @Autowired
    public StorageCommunicator(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Book> getAllBooks(){
        ResponseEntity<ListOfObject<Book>> responseEntity;
        RequestEntity<Void> req;
        try {
            req = RequestEntity.get(new URI("http://localhost:8081/books")).accept(MediaType.APPLICATION_JSON).build();
            responseEntity = restTemplate.exchange(req, new ParameterizedTypeReference<ListOfObject<Book>>(){});
            return responseEntity.getBody().getList();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Author> getAllAuthors(){
        ResponseEntity<ListOfObject<Author>> responseEntity;
        try{
            responseEntity = restTemplate.exchange(new URI("http://localhost:8081/authors"), HttpMethod.GET, null, new ParameterizedTypeReference<ListOfObject<Author>>() {});
            return responseEntity.getBody().getList();
        }catch(URISyntaxException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book getOneBook(long id) {
        ResponseEntity<Book> responseEntity;
        try{
            responseEntity = restTemplate.exchange(new URI("http://localhost:8081/books/"+id), HttpMethod.GET, null, Book.class);
            return responseEntity.getBody();
        }catch(URISyntaxException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Author getOneAuthor(long id) {
        return null;
    }

    @Override
    public Book addBook(BookAttributes b) {
        RequestEntity<BookAttributes> req;
        ResponseEntity<Book> responseEntity;
        try {
            //b.setLastUpdatedAt(new Date().toString());
           req = RequestEntity.post(new URI("http://localhost:8081/books")).accept(MediaType.APPLICATION_JSON).body(b);
           responseEntity = restTemplate.exchange(req, Book.class);
           return responseEntity.getBody();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
