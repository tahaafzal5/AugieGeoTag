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