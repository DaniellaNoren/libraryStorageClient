package daniella.iths.se.librarystorageclient.menu;

import daniella.iths.se.librarystorageclient.communication.StorageCommunicator;
import daniella.iths.se.librarystorageclient.resources.Author;
import daniella.iths.se.librarystorageclient.resources.AuthorAttributes;
import daniella.iths.se.librarystorageclient.resources.Book;
import daniella.iths.se.librarystorageclient.resources.BookAttributes;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class MainMenu {

    static List<MenuItem> menuItems = new ArrayList<>();
    static List<MenuItem> chooseOrGoBackBook = new ArrayList<>();
    static List<MenuItem> chooseOrGoBackAuthor = new ArrayList<>();
    static List<MenuItem> changeAuthor = new ArrayList<>();
    static List<MenuItem> changeBook = new ArrayList<>();

    static StorageCommunicator storageCommunicator = new StorageCommunicator(new RestTemplate());
    static Scanner sc = new Scanner(System.in);

    static long id;

    public static void main(String[] args) {

        changeAuthor.add(new MenuItem("1. New first name", () -> {
            AuthorAttributes authorAttributes = new AuthorAttributes();
            sc.nextLine();
            authorAttributes.setFirstName(sc.nextLine());
            storageCommunicator.updateAuthor(id, authorAttributes);
        }));
        changeAuthor.add(new MenuItem("2. New last name", () -> {
            AuthorAttributes authorAttributes = new AuthorAttributes();
            sc.nextLine();
            authorAttributes.setLastName(sc.nextLine());
            storageCommunicator.updateAuthor(id, authorAttributes);
        }));
        changeAuthor.add(new MenuItem("4. Go back", () -> {
        }));

        changeBook.add(new MenuItem("1. New title", () -> {
            BookAttributes bookAttributes = new BookAttributes();
            sc.nextLine();
            System.out.println("Title: ");
            bookAttributes.setTitle(sc.nextLine());
            storageCommunicator.updateBook(id, bookAttributes);
        }));
        changeBook.add(new MenuItem("2. Borrow book", () -> {
            int userId;
            System.out.println("User ID: ");
                userId = sc.nextInt();
            if(userId > 0) {
                if (storageCommunicator.borrowBook(userId, id) != null)
                    System.out.println("Book borrowed");
                else
                    System.out.println("Book not available");
            }


        }));
        changeBook.add(new MenuItem("3. Return book", () -> {
            if(storageCommunicator.returnBook(id) != null){
                System.out.println("Book returned");
            }else
                System.out.println("Error");
        }));
        changeBook.add(new MenuItem("4. Add author", () -> {
            BookAttributes bookAttributes = new BookAttributes();
            Author a = addAuthorToBook();
            bookAttributes.setAuthors(new HashSet<>());
            bookAttributes.addAuthor(a);
            storageCommunicator.updateBook(id, bookAttributes);
        }));
        changeBook.add(new MenuItem("5. Go back", () -> {
        }));

        chooseOrGoBackBook.add(new MenuItem("1. Select book to update", () -> {
            System.out.println("Enter id: ");
            long bookId = sc.nextLong();
            Book b = storageCommunicator.getOneBook(bookId);
            if(b != null) {
                id = bookId;
                menuLoop(changeBook);
            }else
                System.out.println("Book does not exist");
        }));
        chooseOrGoBackBook.add(new MenuItem("2. Go back", () -> {
        }));

        chooseOrGoBackAuthor.add(new MenuItem("1. Select author to update", () -> {
            long id = 0;
            do {
                System.out.println("Enter id: ");
                id = sc.nextLong();
                if (id <= 0) {
                    System.out.println("Invalid ID, try again");
                    continue;
                }
            } while (id <= 0);
            Author a = storageCommunicator.getOneAuthor(id);
            if (a != null)
                System.out.println(a.toString());
            else
                System.out.println("Author with that ID does not exist");

        }));
        chooseOrGoBackAuthor.add(new MenuItem("2. Go back", () -> {
        }));


        menuItems.add(new MenuItem("1. View all books", () -> {
            storageCommunicator.getAllBooks().forEach(System.out::println);
            menuLoop(chooseOrGoBackBook);
        }));
        menuItems.add(new MenuItem("2. View all authors", () -> {
            storageCommunicator.getAllAuthors().forEach(System.out::println);
            menuLoop(chooseOrGoBackAuthor);
        }));
        menuItems.add(new MenuItem("3. Add new Book", () -> {
            addBook();
        }));
        menuItems.add(new MenuItem("4. Add new Author", () -> {
            addAuthor();
        }));
        menuItems.add(new MenuItem("5. Search with bookID", () -> {
            System.out.println("Enter id: ");
            Book b = storageCommunicator.getOneBook(sc.nextLong());
            if(b != null)
                System.out.println(b);
            else
                System.out.println("Invalid ID");
        }));
        menuItems.add(new MenuItem("6. Search with AuthorID", () -> {
            System.out.println("Enter id: ");
            Author a = storageCommunicator.getOneAuthor(sc.nextLong());
            if(a != null)
                System.out.println(a);
            else
                System.out.println("Invalid ID");
        }));
        menuItems.add(new MenuItem("7. Delete book", () -> {
            System.out.println("Enter id: ");
            id = sc.nextLong();
            Book b = storageCommunicator.getOneBook(id);
            if(b != null) {
                System.out.println("Are you sure? y/n");
                if(sc.next().equals("y"))
                    storageCommunicator.removeBook(id);
                else
                    System.out.println("Cancelling deletion");
            }
            else
                System.out.println("Invalid ID");
        }));
        menuItems.add(new MenuItem("8. Delete author", () -> {
            System.out.println("Enter id: ");
            id = sc.nextLong();
            Author a = storageCommunicator.getOneAuthor(id);
            if(a != null) {
                System.out.println("Are you sure? y/n");
                if(sc.next().equals("y"))
                    storageCommunicator.removeAuthor(id);
                else
                    System.out.println("Cancelling deletion");
            }
            else
                System.out.println("Invalid ID");

        }));
        menuItems.add(new MenuItem("9. Exit", () -> System.exit(0)));


        menuLoop(menuItems);


    }

    private static void addAuthor() {
        String firstName = "";
        String lastName = "";
        sc.nextLine();
        System.out.println("First name:");
        firstName = sc.nextLine();
        System.out.println("Last name: ");
        lastName = sc.nextLine();

        AuthorAttributes authorAttributes = new AuthorAttributes(firstName, lastName);
        storageCommunicator.addAuthor(authorAttributes);
    }


    public static void menuLoop(List<MenuItem> list){
        int option;
        do{
            list.forEach(op -> System.out.println(op.getOption()));
            option = sc.nextInt() - 1;
            if(option > -1  && option < list.size()-1)
                list.get(option).getMenuMethod().doThing();
            else if(option == list.size()-1)
                break;
            else
                System.out.println("Option does not exist, try again");
        }while(true);
    }

    public static void addBook(){
        String title = "";
        Set<Author> authors = new HashSet<>();
        int option = 0;
        sc.nextLine();
        System.out.println("Title:");
        title = sc.nextLine();
       do {
            System.out.println("1. Add author");
            System.out.println("2. Finish");
            option = sc.nextInt();
            if(option > 2 && option < 0) {
                System.out.println("Option does not exist, try again");
                continue;
            }
            if(option == 1) {
                Author aut = addAuthorToBook();
                if (aut != null)
                    authors.add(aut);
                else
                    System.out.println("Invalid Author");
            }

        }while(option != 2);
       BookAttributes bookAttributes = new BookAttributes();
       bookAttributes.setTitle(title);
       bookAttributes.setAuthors(authors);
       storageCommunicator.addBook(bookAttributes);
    }

    public static Author addAuthorToBook(){
        String firstName = "";
        String lastName = "";
        long option;
        do {
            System.out.println("1. Existing author");
            System.out.println("2. New author");
            option = sc.nextInt();
            sc.nextLine();
        }while(option < 0 && option > 2);

        if(option == 2) {
            System.out.println("First name: ");
            firstName = sc.nextLine();
            System.out.println("Last name: ");
            lastName = sc.nextLine();
            return new Author(firstName, lastName);
        }
        else {
            System.out.println("Author ID: ");
            option = sc.nextInt();
            return storageCommunicator.getOneAuthor(option);
        }


    }

    public static void updateBook(long id){
        if(storageCommunicator.getOneBook(id) == null)
            System.out.println("Book does not exist");
        else
            System.out.println();


    }



}
