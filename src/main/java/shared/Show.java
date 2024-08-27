package shared;

public class Show {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void welcome() {
        clearScreen();
        System.out.println(ConsoleColors.GREEN + "______________________________________________________");
        System.out.println(ConsoleColors.GREEN + "|       Bienvenido Sistema de Gestion de  Libros!    |");
        System.out.println(ConsoleColors.GREEN + "______________________________________________________");
    }

    public static void goodbye() {
        System.out.println("\n\n______________________________________________________");
        System.out.println("Adios!");
        System.out.println("______________________________________________________");
    }

    public static void showMenu() {
        System.out.println(ConsoleColors.GREEN + "|  1. Modulo de Libros                               |");
        System.out.println(ConsoleColors.GREEN + "|  2. Modulo de Usuarios                             |");
        System.out.println(ConsoleColors.GREEN + "|  3. Salir                                          |");
        System.out.println(ConsoleColors.GREEN + "|____________________________________________________|");

    }

    public static void showBookMenu() {
        clearScreen();
        System.out.println(ConsoleColors.GREEN + "______________________________________________________");
        System.out.println(ConsoleColors.GREEN + "|                      Libros!                       |");
        System.out.println(ConsoleColors.GREEN + "______________________________________________________");
        System.out.println(ConsoleColors.GREEN + "|  1. Agregar Libro                                  |");
        System.out.println(ConsoleColors.GREEN + "|  2. Listar Libros                                  |");
        System.out.println(ConsoleColors.GREEN + "|  3. Buscar Libro                                   |");
        System.out.println(ConsoleColors.GREEN + "|  4. Prestar Libro                                  |");
        System.out.println(ConsoleColors.GREEN + "|  5. Volver al Menu Principal                       |");
        System.out.println(ConsoleColors.GREEN + "|____________________________________________________|");
    }

    public static void showUserMenu() {
        clearScreen();
        System.out.println(ConsoleColors.GREEN + "______________________________________________________");
        System.out.println(ConsoleColors.GREEN + "|                      Usuarios!                      |");
        System.out.println(ConsoleColors.GREEN + "______________________________________________________");
        System.out.println(ConsoleColors.GREEN + "|  1. Agregar Usuario                                |");
        System.out.println(ConsoleColors.GREEN + "|  2. Listar Usuarios                                |");
        System.out.println(ConsoleColors.GREEN + "|  3. Buscar Usuario                                 |");
        System.out.println(ConsoleColors.GREEN + "|  4. Devolver libro                                 |");
        System.out.println(ConsoleColors.GREEN + "|  5. Volver al Menu Principal                       |");
        System.out.println(ConsoleColors.GREEN + "|____________________________________________________|");
    }
}
