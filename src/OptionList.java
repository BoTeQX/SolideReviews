import java.util.ArrayList;

public class OptionList extends Option {
    private ArrayList<Option> options;
    public OptionList() {
        options = new ArrayList<>();
    }

    public void addOption(String text, MenuAction action) {
        options.add(new Option(text, option));
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
