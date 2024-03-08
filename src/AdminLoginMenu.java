import java.util.Scanner;

public class AdminLoginMenu {
    private final Scanner scanner;

    public AdminLoginMenu() {
        scanner = new Scanner(System.in);
    }

    public static void printHeader() {
        System.out.println(Colors.PURPLE + "    _       _           _       ");
        System.out.println("   / \\   __| |_ __ ___ (_)_ __  ");
        System.out.println("  / _ \\ / _` | '_ ` _ \\| | '_ \\ ");
        System.out.println(" / ___ \\ (_| | | | | | | | | | |");
        System.out.println("/_/   \\_\\__,_|_| |_| |_|_|_| |_|" + Colors.RESET);
        System.out.println();
    }

    public void displayLoginMenu() {
        int attempts = 3;

        printHeader();

        while (attempts > 0) {
            System.out.println("[1] Login");
            System.out.println();
            System.out.println(Colors.RED + "[0] Back to Main Menu" + Colors.RESET);

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> login();
                case 0 -> {
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.displayMainMenu();
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("Login attempts exceeded. Exiting the program.");
        scanner.close();
    }

    private void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (isValidAdmin(username, password)) {
            System.out.println("Login successful. Welcome, Admin!");
            // Add admin-related functionality here
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private boolean isValidAdmin(String username, String password) {
        // Replace this with your actual admin credentials validation logic
        return username.equals("admin") && password.equals("admin");
    }
}
