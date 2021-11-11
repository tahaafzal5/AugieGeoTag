package src;

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

    public static void displaySuccess(String type) {
        switch (type) {
            case "open-image":
                System.out.println("Image opened successfully"); break;
            case "read-metadata":
                System.out.println("Metadata read successfully"); break;
            case "check-jpeg":
                System.out.println("Checked JPEG/JPG successfully"); break;
            case "read-geotag":
                System.out.println("Read geotag successfully"); break;
            case "remove-geotag":
                System.out.println("Removed geotag successfully"); break;
            case "write-geotag":
                System.out.println("Wrote geotag successfully"); break;
            case "save-image":
                System.out.println("Image saved successfully"); break;
            default:
                System.out.println("Success");
        }
    } 

    public static void displayProcessing(String type) {
        switch (type) {
            case "open-image":
                System.out.println("Opening image..."); break;
            case "read-metadata":
                System.out.println("Reading image metadata..."); break;
            case "check-jpeg":
                System.out.println("Checking if the image is a JPEG/JPG..."); break;
            case "read-geotag":
                System.out.println("Reading the geotag in the image..."); break;
            case "remove-geotag":
                System.out.println("Removing geotag from the image..."); break;
            case "write-metadata":
                System.out.println("Writing the metadata in the image..."); break;
            case "save-image":
                System.out.println("Saving the image..."); break;
            default:
                System.out.println("Processing...");
        }
    }
}