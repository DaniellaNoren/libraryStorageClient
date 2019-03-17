package daniella.iths.se.librarystorageclient.communication;

import daniella.iths.se.librarystorageclient.resources.Author;
import daniella.iths.se.librarystorageclient.resources.AuthorAttributes;

import java.util.List;

public interface AuthorStorage {

    Author addAuthor(AuthorAttributes authorAttributes);

    List<Author> getAllAuthors();

    String removeAuthor(long author_id);

    Author getOneAuthor(long author_id);

    Author updateAuthor(long author_id, AuthorAttributes authorAttributes);
}
