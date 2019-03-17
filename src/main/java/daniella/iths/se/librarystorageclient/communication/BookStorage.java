package daniella.iths.se.librarystorageclient.communication;

import daniella.iths.se.librarystorageclient.resources.Author;
import daniella.iths.se.librarystorageclient.resources.Book;
import daniella.iths.se.librarystorageclient.resources.BookAttributes;

import java.util.List;

public interface BookStorage {

    List<Book> getAllBooks();

    Book getOneBook(long id);

    Book addBook(BookAttributes b);

    Book updateBook(long id, BookAttributes b);

    void removeBook(long id);


    Book borrowBook(long userId, long id);

    Book returnBook(long id);
}
