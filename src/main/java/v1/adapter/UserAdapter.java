package v1.adapter;

import shared.ConsoleColors;
import v1.datasource.BookDataSource;
import v1.datasource.UserSource;
import v1.model.Book;
import v1.model.User;
import v1.port.UserPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserAdapter implements UserPort {

    Scanner scanner = new Scanner(System.in);

    public UserAdapter() {
    }

    @Override
    public void createUser() {
        User user = new User();
        System.out.print(ConsoleColors.BLUE + "Ingrese el nombre del usuario: ");
        user.setName(scanner.nextLine());
        System.out.print(ConsoleColors.BLUE + "Ingrese el email del usuario: ");
        user.setEmail(scanner.nextLine());

        User userFound = UserSource.users.stream().filter(u -> u.getEmail().equals(user.getEmail())).findFirst().orElse(null);

        if(userFound != null) {
            System.out.println(ConsoleColors.RED + "El usuario ya existe");
            return;
        }

        // default values
        user.setActive(true);
        user.setId(UUID.randomUUID().toString());
        user.setReservedBooks(new ArrayList<>());

        UserSource.add(user);

        System.out.println(ConsoleColors.YELLOW + "Usuario creado con exito!");
    }

    @Override
    public List<User> getUsers() {
        List<User> users = UserSource.users;

        System.out.println(ConsoleColors.PURPLE + "______________________________________________________");
        System.out.println(ConsoleColors.PURPLE + "|                      Usuarios!                      |");
        System.out.println(ConsoleColors.PURPLE + "______________________________________________________");

        users.forEach(user -> {
            System.out.println(ConsoleColors.PURPLE + "|  ID: " + user.getId());
            System.out.println(ConsoleColors.PURPLE + "|  Nombre: " + user.getName());
            System.out.println(ConsoleColors.PURPLE + "|  Email: " + user.getEmail());
            System.out.println(ConsoleColors.PURPLE + "|  Activo: " + user.isActive());
            System.out.println(ConsoleColors.PURPLE + "|  Libros Reservados: " + user.getReservedBooks().size());
            System.out.println(ConsoleColors.PURPLE + "|____________________________________________________|");
        });

        return users;
    }

    @Override
    public User getUser() {
        System.out.print(ConsoleColors.BLUE + "Ingrese el Email del usuario: ");
        String email = scanner.nextLine();
        User user = UserSource.users.stream().filter(u -> u.getEmail().contains(email)).findFirst().orElse(null);

        if (user == null) {
            System.out.println(ConsoleColors.RED + "Usuario no encontrado");
        } else {

            List<String> titles = BookDataSource.getBookTitlesFromIds(user.getReservedBooks());
            String titlesString = String.join(", ", titles);

            System.out.println(ConsoleColors.PURPLE + "______________________________________________________");
            System.out.println(ConsoleColors.PURPLE + "|  ID: " + user.getId());
            System.out.println(ConsoleColors.PURPLE + "|  Nombre: " + user.getName());
            System.out.println(ConsoleColors.PURPLE + "|  Email: " + user.getEmail());
            System.out.println(ConsoleColors.PURPLE + "|  Activo: " + user.isActive());
            System.out.println(ConsoleColors.PURPLE + "|  Libros Reservados: " + titlesString);
            System.out.println(ConsoleColors.PURPLE + "|____________________________________________________|");
        }

        return user;
    }

    @Override
    public void returnBook() {
        System.out.print(ConsoleColors.BLUE + "Ingrese el Email del usuario: ");
        String email = scanner.nextLine();
        User user = UserSource.users.stream().filter(u -> u.getEmail().contains(email)).findFirst().orElse(null);
        if (user == null) {
            System.out.println(ConsoleColors.RED + "Usuario no encontrado");
        } else {
            System.out.print(ConsoleColors.BLUE + "Ingrese el Titulo del libro: ");
            String title = scanner.nextLine();
            Book book = BookDataSource.books.stream().filter(b -> b.getTitle().contains(title)).findFirst().orElse(null);
            if (book == null) {
                System.out.println(ConsoleColors.RED + "Libro no encontrado");
                return;
            }
            if(user.getReservedBooks().contains(book.getId())) {
                user.getReservedBooks().remove(book.getId());
                book.setReservedBy(null);
                book.setAvailable(true);
                System.out.println(ConsoleColors.YELLOW + "Libro devuelto con exito!");
            } else {
                System.out.println(ConsoleColors.RED + "El usuario no tiene reservado este libro");
            }
        }
    }

    @Override
    public void initUsers() {
        User user1 = new User();
        user1.setId(UUID.randomUUID().toString());
        user1.setName("Juan Perez");
        user1.setEmail("gualterosjohn40@gmail.com");
        user1.setActive(true);
        user1.setReservedBooks(new ArrayList<>());

        UserSource.add(user1);
    }
}
