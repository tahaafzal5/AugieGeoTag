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

    /* protected static void displayHelpMenu() {
        
    } */

    protected static void handleUserMenuChoice(String choice) {
        if (choice == "1")
            openJpegImage();
        else if (choice == "2")
            writeGeoTagData();
        else if (choice == "3")
            updateGeoTagData();
        else if (choice == "4")
            removeGeoTagData();
        else if (choice == "5")
            displayMenu();
        else if (choice == "6")
            aboutProgram();
        else if (choice == "7")
            System.exit(0);
        else {
            System.out.println("Invalid option. Please enter option 1-7.");
            displayMenu();
        }
    } 

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