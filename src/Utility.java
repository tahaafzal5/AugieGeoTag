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

    public static void displayProcessing(String type) {
        switch (type) {
            case "opening-image":
                System.out.println("Opening image..."); break;
            case "reading-metadata":
                System.out.println("Reading image metadata..."); break;
            case "checking-jpeg":
                System.out.println("Checking if the image is a JPEG/JPG...");
            case "reading-geotag":
                System.out.println("Reading the geotag in the image..."); break;
            case "removing-geotag":
                System.out.println("Removing geotag from the image..."); break;
            case "writing-metadata":
                System.out.println("Writing the metadata in the image..."); break;
            case "saving-image":
                System.out.println("Saving the image..."); break;
            default:
                System.out.println("Processing...");
        }
    }
}