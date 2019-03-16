package daniella.iths.se.librarystorageclient.communication;

import daniella.iths.se.librarystorageclient.resources.Author;
import daniella.iths.se.librarystorageclient.resources.Book;
import daniella.iths.se.librarystorageclient.resources.BookAttributes;

import java.util.List;

public interface BookStorage {

    List<Book> getAllBooks();

    List<Author> getAllAuthors();

    Book getOneBook(long id);

    Author getOneAuthor(long id);

    Book addBook(BookAttributes b);





}
