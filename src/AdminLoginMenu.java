
import java.util.Scanner;

public class AdminLoginMenu {
    private final Scanner scanner;

    public AdminLoginMenu() {
        scanner = new Scanner(System.in);
    }

    public void displayLoginMenu() {
        int attempts = 3;
        int choice = -1;
        while (attempts > 0) {

            System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "ADMIN " + Colors.RESET);
            System.out.println("│");
            System.out.println("├ <" + Colors.BLUE_BOLD + "1" + Colors.RESET + "> Login");
            System.out.println("│");
            System.out.println("├ <" + Colors.BLUE_BOLD + "9" + Colors.RESET + "> Back to Main Menu");
            System.out
                    .println("╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + "> " + Colors.RED + "Exit" + Colors.RESET);
            System.out.println();

            System.out.print(Colors.PURPLE + "Enter your choice: " + Colors.RESET);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
            }

            switch (choice) {
                case 1 -> login();
                case 9 -> {
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.displayMainMenu();
                }
                case 0 -> {
                    System.out.println("Exiting the program. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("Login attempts exceeded. Exiting the program.");
        scanner.close();
    }

    private void login() {
        scanner.nextLine();
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
