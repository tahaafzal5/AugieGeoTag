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

    protected static void handleUserMenuChoice(Scanner input) { 
        System.out.print("Please enter selection: ");
        int choice = 0; 
        while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 && choice != 7) {
            choice = input.nextInt();
            switch (choice) {
                case 1: GeoTagFunctions.openJpegImage();
                        break;
                case 2: GeoTagFunctions.writeGeoTagData();
                        break;
                case 3: GeoTagFunctions.updateGeoTagData();
                        break;
                case 4: GeoTagFunctions.removeGeoTagData();
                        break;
                case 5: displayMenu();
                        break;
                case 6: aboutProgram();
                        break;
                case 7: System.exit(0);
                default: System.out.print("Invalid option. Please enter option 1-7: ");
            }
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