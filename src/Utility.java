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

    /* protected ... void displayProcessing(...) {
        ...
    } */
}