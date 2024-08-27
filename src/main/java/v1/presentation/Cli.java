package v1.presentation;

import shared.ConsoleColors;
import shared.Constants;
import shared.Show;
import shared.UsageType;
import v1.adapter.BookAdapter;
import v1.adapter.UserAdapter;
import v1.port.BookPort;
import v1.port.UserPort;

import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Cli extends UsageType {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void start() {
        Show.welcome();
        Show.showMenu();
        UserPort userPort = new UserAdapter();
        BookPort bookPort = new BookAdapter();
        userPort.initUsers();
        bookPort.initBooks();
        System.out.print(ConsoleColors.BLUE + "Ingrese una opcion: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                bookModule();
                break;
            case 2:
                userModule();
                break;
            case 3:
                Show.goodbye();
                System.exit(0);
                break;
            default:
                System.out.println(ConsoleColors.RED + String.format(Constants.OPTION_OUT_OF_BOUNDS, 1, 3));
                break;
        }
    }

    @Override
    public void bookModule() {
        BookPort bookPort = new BookAdapter();
        AtomicInteger shouldContinue = new AtomicInteger(1);

        while (shouldContinue.get() == 1) {
            Show.showBookMenu();
            System.out.print(ConsoleColors.BLUE + "Ingrese una opcion: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    bookPort.createBook();
                    break;
                case 2:
                    bookPort.getBooks();
                    break;
                case 3:
                    bookPort.getBook();
                    break;
                case 4:
                    bookPort.lendBook();
                    break;
                case 5:
                    shouldContinue.set(0);
                    start();
                    break;
                default:
                    System.out.println(ConsoleColors.RED + String.format(Constants.OPTION_OUT_OF_BOUNDS, 1, 5));
                    break;
            }
        }
    }

    @Override
    public void userModule() {
        UserPort userPort = new UserAdapter();
        AtomicInteger isContinue = new AtomicInteger(1);

        while (isContinue.get() == 1) {
            Show.showUserMenu();
            System.out.print(ConsoleColors.BLUE + "Ingrese una opcion: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    userPort.createUser();
                    break;
                case 2:
                    userPort.getUsers();
                    break;
                case 3:
                    userPort.getUser();
                    break;
                case 4:
                    userPort.returnBook();
                    break;
                case 5:
                    isContinue.set(0);
                    start();
                    break;
                default:
                    System.out.println(ConsoleColors.RED + String.format(Constants.OPTION_OUT_OF_BOUNDS, 1, 5));
                    break;
            }
        }
    }
}
