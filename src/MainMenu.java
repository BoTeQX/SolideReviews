import java.util.Scanner;

class MainMenu {
    private final Scanner scanner;

    public MainMenu() {
        scanner = new Scanner(System.in);
    }

    private void printProgramName() {
        System.out.println();
        System.out.println("      ██          ██      ");
        System.out.println("        ██      ██               _____         _  _      _            ______              _                      ");
        System.out.println("      ██████████████            /  ___|       | |(_)    | |           | ___ \\            (_)                     ");
        System.out.println("    ████  ██████  ████          \\ `--.   ___  | | _   __| |  ___      | |_/ / ___ __   __ _   ___ __      __ ___ ");
        System.out.println("  ██████████████████████         `--. \\ / _ \\ | || | / _` | / _ \\     |    / / _ \\\\ \\ / /| | / _ \\\\ \\ / \\ / / __|");
        System.out.println("  ██  ██████████████  ██        /\\__/ /| (_) || || || (_| ||  __/     | |\\ \\|  __/ \\ V / | ||  __/ \\ V  V / \\__ \\");
        System.out.println("  ██  ██          ██  ██        \\____/  \\___/ |_||_| \\__,_| \\___|     \\_| \\_/\\___|  \\_/  |_| \\___|  \\_/\\_/  |___/");
        System.out.println("        ████  ████        ");
        System.out.println();
    }

    public void displayMainMenu() {
        printProgramName();

        int choice = -1;

        do {
            System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "MAIN MENU " + Colors.RESET);
            System.out.println("│");
            System.out.println("├ <"+ Colors.BLUE_BOLD + "1" + Colors.RESET + "> Games");
            System.out.println("├ <"+ Colors.BLUE_BOLD + "2" + Colors.RESET + "> Option 2");
            System.out.println("├ <"+ Colors.BLUE_BOLD + "3" + Colors.RESET + "> Option 3");
            System.out.println("├ <"+ Colors.BLUE_BOLD + "4" + Colors.RESET + "> Admin");
            System.out.println("│");
            System.out.println("╰ <"+ Colors.BLUE_BOLD + "0" + Colors.RESET + "> " + Colors.RED + "Exit" + Colors.RESET);
            System.out.println();

            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
            }


            switch (choice) {
                case 1 -> openGameMenu();
                case 2 -> option2();
                case 3 -> option3();
                case 4 -> openAdminLoginScreen();
                case 0 -> System.out.println("Exiting the program. Goodbye!");
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);

        scanner.close();
    }


    private void openGameMenu() {
        GameMenu gameMenu = new GameMenu();
        gameMenu.displayGameMenu();
    }

    private void option2() {
        System.out.println("You chose Option 2.");
    }

    private void option3() {
        System.out.println("You chose Option 3.");
    }

    private void openAdminLoginScreen() {
        AdminLoginMenu adminLogin = new AdminLoginMenu();
        adminLogin.displayLoginMenu();

    }
}
