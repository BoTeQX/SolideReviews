import java.util.ArrayList;
import java.util.Scanner;
class MainMenu extends Menu {
    private final Scanner scanner;

    public MainMenu() {
        scanner = new Scanner(System.in);
    }

    class OptionList {
        private ArrayList<Option> options;

        class Option {
            private String text;
            private MenuAction action;

            public Option(String text, MenuAction action) {
                this.text = text;
                this.action = action;
            }

            public String getText() {
                return text;
            }

            public MenuAction getAction() {
                return action;
            }
        }

        public OptionList() {
            options = new ArrayList<>();
        }

        public void addOption(String text, MenuAction action) {
            options.add(new Option(text, action));
        }

        public void displayOptions() {
            System.out.println("╭────> " + Colors.CYAN_BOLD_BRIGHT + "MAIN MENU " + Colors.RESET);
            System.out.println("│");
            for (int i = 0; i < options.size(); i++) {
                System.out.println("├── <" + Colors.BLUE_BOLD + (i + 1) + Colors.RESET + "> " + options.get(i).text);
            }
            System.out.println("│");
            System.out
                    .println("╰── <" + Colors.BLUE_BOLD + "0" + Colors.RESET + "> " + Colors.RED + "Exit"
                            + Colors.RESET);
            System.out.println();

        }
    }

    class Options {
        private Options() {
            // private constructor to hide the implicit public one
        }

        public static void option1() {
            GameMenu gameMenu = new GameMenu();
            gameMenu.displayGameMenu();
        }

        public static void option2() {
            System.out.println("You chose Option 2.");
        }

        public static void option3() {
            System.out.println("You chose Option 3.");
        }

        public static void option4() {
            AdminLoginMenu adminLogin = new AdminLoginMenu();
            adminLogin.displayLoginMenu();
        }
    }

    public void displayMainMenu() {

        OptionList menuOptions = new OptionList(); // Create an OptionList instance
        menuOptions.addOption("Game", () -> Options.option1());
        menuOptions.addOption("Option 2", () -> Options.option2());
        menuOptions.addOption("Option 3", () -> Options.option3());
        menuOptions.addOption("Admin", () -> Options.option4());

        int choice = -1;

        do {
            System.out.print("\033[H\033[2J"); // Clear the screen
            displayLogo(); // Display the program name
            menuOptions.displayOptions(); // Display the menu options

            System.out.print(Colors.PURPLE + "Enter your choice: " + Colors.RESET);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (choice > 0 && choice <= menuOptions.options.size()) {
                    menuOptions.options.get(choice - 1).getAction().execute();
                } else if (choice != 0) {
                    System.out.println("Invalid choice. Please enter a valid option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        } while (choice != 0);

        scanner.close();
    }
}