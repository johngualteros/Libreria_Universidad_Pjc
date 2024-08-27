package v1.adapter;

import shared.ConsoleColors;
import v1.datasource.BookDataSource;
import v1.datasource.UserSource;
import v1.model.Book;
import v1.model.User;
import v1.port.BookPort;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BookAdapter implements BookPort {

    Scanner scanner = new Scanner(System.in);

    public BookAdapter() {
    }

    @Override
    public void createBook() {
        Book book = new Book();
        System.out.print(ConsoleColors.BLUE + "Ingrese el titulo del libro: ");
        book.setTitle(scanner.nextLine());
        System.out.print(ConsoleColors.BLUE + "Ingrese el autor del libro: ");
        book.setAuthor(scanner.nextLine());
        System.out.print(ConsoleColors.BLUE + "Ingrese el genero del libro: ");
        book.setGenre(scanner.nextLine());

        // default values
        book.setId(UUID.randomUUID().toString());
        book.setAvailable(true);
        book.setReservedBy(null);

        BookDataSource.add(book);

        System.out.println(ConsoleColors.YELLOW + "Libro creado con exito!");
    }

    @Override
    public List<Book> getBooks() {
        List<Book> books = BookDataSource.books;

        System.out.println(ConsoleColors.PURPLE + "______________________________________________________");
        System.out.println(ConsoleColors.PURPLE + "|                      Libros!                       |");
        System.out.println(ConsoleColors.PURPLE + "______________________________________________________");

        books.forEach(book -> {
            System.out.println(ConsoleColors.PURPLE + "|  ID: " + book.getId());
            System.out.println(ConsoleColors.PURPLE + "|  Titulo: " + book.getTitle());
            System.out.println(ConsoleColors.PURPLE + "|  Autor: " + book.getAuthor());
            System.out.println(ConsoleColors.PURPLE + "|  Genero: " + book.getGenre());
            System.out.println(ConsoleColors.PURPLE + "|  Disponible: " + (book.isAvailable() ? "Si" : "No"));
            System.out.println(ConsoleColors.PURPLE + "|  Reservado por: " + (book.getReservedBy() != null ? book.getReservedBy() : "Nadie"));
            System.out.println(ConsoleColors.PURPLE + "|____________________________________________________|");
        });

        return books;
    }

    @Override
    public Book getBook() {
        System.out.println(ConsoleColors.BLUE + "Ingrese el Titulo del libro: ");
        String title = scanner.nextLine();
        Book book = BookDataSource.books.stream().filter(b -> b.getTitle().contains(title)).findFirst().orElse(null);

        if(book == null) {
            System.out.println(ConsoleColors.RED + "Libro no encontrado");
            return null;
        } else {
            System.out.println(ConsoleColors.PURPLE + "______________________________________________________");
            System.out.println(ConsoleColors.PURPLE + "|  ID: " + book.getId());
            System.out.println(ConsoleColors.PURPLE + "|  Autor: " + book.getAuthor());
            System.out.println(ConsoleColors.PURPLE + "|  Genero: " + book.getGenre());
            System.out.println(ConsoleColors.PURPLE + "|  Disponible: " + (book.isAvailable() ? "Si" : "No"));
            System.out.println(ConsoleColors.PURPLE + "|  Reservado por: " + (book.getReservedBy() != null ? book.getReservedBy() : "Nadie"));
            System.out.println(ConsoleColors.PURPLE + "|____________________________________________________|");
        }

        return book;
    }

    @Override
    public void lendBook() {
        System.out.print(ConsoleColors.BLUE + "Ingrese el Email del usuario: ");
        String email = scanner.nextLine();
        User user = UserSource.users.stream().filter(u -> u.getEmail().contains(email)).findFirst().orElse(null);
        if(user == null) {
            System.out.println(ConsoleColors.RED + "Usuario no encontrado");
            return;
        }
        System.out.print(ConsoleColors.BLUE + "Ingrese el Titulo del libro: ");
        String title = scanner.nextLine();
        Book book = BookDataSource.books.stream().filter(b -> b.getTitle().contains(title)).findFirst().orElse(null);
        if(book == null) {
            System.out.println(ConsoleColors.RED + "Libro no encontrado");
            return;
        }
        if(!book.isAvailable()) {
            System.out.println(ConsoleColors.RED + "Libro no disponible");
            return;
        }
        book.setAvailable(false);
        book.setReservedBy(user.getEmail());
        user.getReservedBooks().add(book.getId());
        System.out.println(ConsoleColors.YELLOW + "Libro prestado con exito!");
    }

    @Override
    public void initBooks() {
        Book book1 = new Book();
        book1.setId(UUID.randomUUID().toString());
        book1.setTitle("El señor de los anillos");
        book1.setAuthor("J.R.R. Tolkien");
        book1.setGenre("Fantasia");
        book1.setAvailable(true);
        book1.setReservedBy(null);

        Book book2 = new Book();
        book2.setId(UUID.randomUUID().toString());
        book2.setTitle("Harry Potter");
        book2.setAuthor("J.K. Rowling");
        book2.setGenre("Fantasia");
        book2.setAvailable(true);
        book2.setReservedBy(null);

        Book book3 = new Book();
        book3.setId(UUID.randomUUID().toString());
        book3.setTitle("Cien años de soledad");
        book3.setAuthor("Gabriel Garcia Marquez");
        book3.setGenre("Realismo Magico");
        book3.setAvailable(true);
        book3.setReservedBy(null);

        BookDataSource.add(book1);
        BookDataSource.add(book2);
        BookDataSource.add(book3);
    }
}
