package daniella.iths.se.librarystorageclient.communication;

import daniella.iths.se.librarystorageclient.resources.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class StorageCommunicator implements BookStorage, AuthorStorage {

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
        }catch(HttpClientErrorException | HttpServerErrorException e){
            return null;
        }
        return null;
    }

    @Override
    public Author getOneAuthor(long id) {
        ResponseEntity<Author> res;
        try{
            res = restTemplate.exchange(new URI("http://localhost:8081/authors/"+id), HttpMethod.GET, null, Author.class);
            return res.getBody();
        }catch(URISyntaxException e){
            e.printStackTrace();
        }catch(HttpServerErrorException | HttpClientErrorException e){
            return null;
        }

        return null;
    }

    @Override
    public Author updateAuthor(long author_id, AuthorAttributes authorAttributes) {
        RequestEntity<AuthorAttributes> req;
        ResponseEntity<Author> res;
        try{
            req = RequestEntity.put(new URI("http://localhost:8081/authors/"+author_id)).accept(MediaType.APPLICATION_JSON).body(authorAttributes);
            res = restTemplate.exchange(req, Author.class);
            return res.getBody();

        }catch(URISyntaxException e){

        }


        return null;
    }

    @Override
    public Book addBook(BookAttributes bookAttributes) {
        RequestEntity<BookAttributes> req;
        ResponseEntity<Book> responseEntity;
        try {
           req = RequestEntity.post(new URI("http://localhost:8081/books")).accept(MediaType.APPLICATION_JSON).body(bookAttributes);
           responseEntity = restTemplate.exchange(req, Book.class);
           return responseEntity.getBody();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book updateBook(long id, BookAttributes b) {
        RequestEntity<BookAttributes> req;
        ResponseEntity<Book> res;
        try{
            req = RequestEntity.put(new URI("http://localhost:8081/books/"+id)).accept(MediaType.APPLICATION_JSON).body(b);
            res = restTemplate.exchange(req, Book.class);
            return res.getBody();

        }catch(URISyntaxException e){

        }


        return null;
    }

    @Override
    public Author addAuthor(AuthorAttributes authorAttributes) {
        RequestEntity<AuthorAttributes> req;
        ResponseEntity<Author> responseEntity;
        try {
        req = RequestEntity.post(new URI("http://localhost:8081/authors")).accept(MediaType.APPLICATION_JSON).body(authorAttributes);
        responseEntity = restTemplate.exchange(req, Author.class);
        return responseEntity.getBody();

    } catch (URISyntaxException e) {
        e.printStackTrace();
    }
        return null;
    }

    @Override
    public String removeAuthor(long author_id) {
        ResponseEntity<String> res;
        RequestEntity req;
        String mes = "";
        try{
            req = RequestEntity.delete(new URI("http://localhost:8081/authors/delete/"+author_id)).accept(MediaType.APPLICATION_JSON).build();
            res = restTemplate.exchange(req, String.class);
            return mes = res.getBody();

        }catch(URISyntaxException e){
            e.printStackTrace();
        }catch(HttpClientErrorException e){
            return e.toString();
        }
        return "Something went wrong";
    }

    public void removeBook(long id) {
        ResponseEntity<String> res;

        try{
            res = restTemplate.exchange(new URI("http://localhost:8081/books/delete/"+id), HttpMethod.DELETE, null, String.class);
        }catch(URISyntaxException e){
            e.printStackTrace();
        }
    }

    @Override
    public Book borrowBook(long userId, long id) {
        RequestEntity<Long> req;
        ResponseEntity<Book> res;
        try{
            req = RequestEntity.put(new URI("http://localhost:8081/books/borrow/"+id)).accept(MediaType.APPLICATION_JSON).body(userId);
            res = restTemplate.exchange(req, Book.class);
            return res.getBody();
        }catch(URISyntaxException e){

        }catch(HttpServerErrorException | HttpClientErrorException e){
            return null;
        }
        return null;
    }

    @Override
    public Book returnBook(long id) {
        ResponseEntity<Book> res;
        try{
            res = restTemplate.exchange(new URI("http://localhost:8081/books/return/"+id), HttpMethod.PUT, null, Book.class);
            return res.getBody();
        }catch(URISyntaxException e){

        }catch(HttpServerErrorException | HttpClientErrorException e){
            return null;
        }
        return null;
    }
}
