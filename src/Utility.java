import java.util.Scanner;

public class Utility {
    protected static void displayMenu() {
        System.out.println("Augie GeoTag Menu:");
        System.out.println("\t1. Open an image");
        System.out.println("\t2. Add GeoTag");
        System.out.println("\t3. Update GeoTag");
        System.out.println("\t4. Remove GeoTag");
        System.out.println("\t5. Help Menu");
        System.out.println("\t6. About");
        System.out.println("\t7. Exit");
    }

    protected static void displayHelpMenu() {
        Scanner displayHelpMenuScanner = Scanner(System.in);
        System.out.println("Help Menu:");
        System.out.println("\t1. Open an image: This option allows you to select the specific image you would like to edit the GeoTag of. It must be the exact file name including the file tag.");
        System.out.println("\t2. Add GeoTag: This option allows you to add a GeoTag to the image you opened in option 1. It will ask for a GeoTag in format of:  ");
        System.out.println("\t3. Update GeoTag: This option allows you to remove an original GeoTag, and add a new one. Also known as updating. It will ask for a GeoTag in format of: ");
        System.out.println("\t4. Remove GeoTag: This function allows you to remove the GeoTag on the image you opened in option 1.");
        System.out.println("\t6. About: This GeoTag editing program was developed by Taha, Taire, Zach, Tony, Sicheng and Zemenu.");
        System.out.println("Press Enter key to return to main menu.");
        if (displayHelpMenuScanner.nextLine().equals("")) {
            displayMenu();
        }
        else {
            displayHelpMenu();
        }
        
    } 

    /* protected ... ... handleUserMenuChoice(...) {
        ...
    } */

    /* protected ... void askConfirmation(...) {

    } */

    /* protected ... ... handleUserConfirmationChoice(...) {
        ...
    } */

    /* protected ... void displayError(...) {
        ...
    } */

    /* protected ... void displaySuccess(...) {
        ...
    } */

    /* protected ... void displayProcessing(...) {
        ...
    } */
}