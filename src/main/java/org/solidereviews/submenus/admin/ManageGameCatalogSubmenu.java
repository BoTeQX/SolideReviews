package org.solidereviews.submenus.admin;

import org.solidereviews.games.GameController;
import org.solidereviews.games.GameDisplayer;
import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.AdminMenu;

public class ManageGameCatalogSubmenu implements Menu {

        String title = "ADMIN MENU > Manage game catalog"; // Title of the submenu
        String[] menuItems = {"Add game", "Remove game", "Update game", "Add sale", "View game catalog"}; // Menu items


        @Override
        public String getTitle() {return title;} // Return the title of the submenu

        @Override
        public String[] getMenuItems() {
            return menuItems;
        } // Return the menu items

        // Use the user's choice that was entered in the menu to determine which method to call or menu to redirect to
        @Override
        public void processUserChoice(int choice) {
            switch (choice) {
                case 1 -> GameController.addGame(getTitle()); // if user chooses 1, call addGame method (to add a game to the catalog)(getTitle() is used to pass the title of the submenu to later know where the user came from)
                case 2 -> GameController.removeGame(); // if user chooses 2, call removeGame method (to remove a game from the catalog)
                case 3 -> GameController.updateGame(); // if user chooses 3, call updateGame method (to update a game in the catalog)
                case 4 -> GameController.addSale(); // if user chooses 4, call addSale method (to add a sale to a game)
                case 5 -> GameDisplayer.showAllGames(getTitle()); // if user chooses 5, call showAllGames method (to display all games in the catalog) (getTitle() is used to pass the title of the submenu to later know where the user came from)
                case 9 -> new AdminMenu().initiateMenu(); // if user chooses 9, the user is redirected to the AdminMenu
                case 0 -> closeProgram(); // if user chooses 0, the program is closed
                default -> System.out.println("Invalid choice. Please enter a valid option."); // if user chooses an invalid option, an error message is displayed
            }
        }

}
