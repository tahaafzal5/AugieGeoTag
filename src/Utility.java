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

    public static void displayErrorMethod(String type){
        switch (type) {
            case "check-jpeg": 
                System.out.println("Error opening image, check that you uploaded an acceptable file format (JPEG/JPG)"); break;
            case "check-exists":
                System.out.println("File not found, please open a valid image"); break;
            case "check-multiple":
                System.out.println("Error: You are attempting to open more than 1 image, please open only one image at a time"); break;
            case "remove-geotag":
                System.out.println("Error: Geotag does not exist on given image, please open a different image"); break;
            case "update-geotag": 
                System.out.println("Error: Geotag does not exist on given image, please open a different image"); break;
            case "add-geotag":
                System.out.println("Error: Geotag already exists on given image, please open a different image"); break;
        }
    }

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