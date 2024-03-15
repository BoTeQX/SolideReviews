public class Option implements MenuAction {
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
