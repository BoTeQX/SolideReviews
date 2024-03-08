import java.util.Scanner;

class MainMenu {
    private final Scanner scanner;

    public MainMenu() {
        scanner = new Scanner(System.in);
    }

    private void printProgramName() {
        System.out.println(" _____         _  _      _            ______              _                      ");
        System.out.println("/  ___|       | |(_)    | |           | ___ \\            (_)                     ");
        System.out.println("\\ `--.   ___  | | _   __| |  ___      | |_/ / ___ __   __ _   ___ __      __ ___ ");
        System.out.println(" `--. \\ / _ \\ | || | / _` | / _ \\     |    / / _ \\\\ \\ / /| | / _ \\\\ \\ / \\ / / __|");
        System.out.println("/\\__/ /| (_) || || || (_| ||  __/     | |\\ \\|  __/ \\ V / | ||  __/ \\ V  V / \\__ \\");
        System.out.println("\\____/  \\___/ |_||_| \\__,_| \\___|     \\_| \\_/\\___|  \\_/  |_| \\___|  \\_/\\_/  |___/");
    }

    private void printHeader() {
        System.out.println(Colors.PURPLE +" __  __       _         __  __                  ");
        System.out.println("|  \\/  | __ _(_)_ __   |  \\/  | ___ _ __  _   _ ");
        System.out.println("| |\\/| |/ _` | | '_ \\  | |\\/| |/ _ \\ '_ \\| | | |");
        System.out.println("| |  | | (_| | | | | | | |  | |  __/ | | | |_| |");
        System.out.println("|_|  |_|\\__,_|_|_| |_| |_|  |_|\\___|_| |_|\\__,_|");
        System.out.println(Colors.RESET);
    }

    public void displayMainMenu() {
        printProgramName();
        printHeader();

        int choice;

        do {
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.println("3. Option 3");
            System.out.println();
            System.out.println(Colors.RED + "0. Exit" + Colors.RESET);

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> option1();
                case 2 -> option2();
                case 3 -> option3();
                case 0 -> System.out.println("Exiting the program. Goodbye!");
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);

        scanner.close();
    }


    private void option1() {
        System.out.println("You chose Option 1.");
    }

    private void option2() {
        System.out.println("You chose Option 2.");
    }

    private void option3() {
        System.out.println("You chose Option 3.");
    }
}
