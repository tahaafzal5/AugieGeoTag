package src;

import java.util.*;

public class Utility {

    // Output: Basic menu of the program to let the user do essential tasks
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
        Scanner displayHelpMenuScanner = new Scanner(System.in);
        
        System.out.println("Help Menu:");
        System.out.println("\t1. Open an image: This option allows you to select the specific image you would like");
        System.out.println(" to edit the GeoTag of. It must be the exact file name including the file tag.");
        System.out.println("\t2. Add GeoTag: This option allows you to add a GeoTag to the image you opened");
        System.out.println("in option 1. It will ask for a GeoTag in format of: ");
        System.out.println("\t3. Update GeoTag: This option allows you to remove an original GeoTag, and add a new one.");
        System.out.println(" Also known as updating. It will ask for a GeoTag in format of:");
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

    // Desc: depending on the user's choice from the main menu, this method calls the appropriate function
    protected static void handleUserMenuChoice(Scanner input) { 
        try {
            System.out.print("Please enter selection (1-7): ");
            int choice = 0; 
            while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 && choice != 7) {
                choice = input.nextInt();
                switch (choice) {
                    case 1: System.out.println("GeoTagFunctions.openJpegImage() should be called when it is implemented");
                            break;
                    case 2: System.out.println("GeoTagFunctions.writeGeoTagData() should be called when it is implemented");
                            break;
                    case 3: System.out.println("GeoTagFunctions.updateGeoTagData() should be called when it is implemented");
                            break;
                    case 4: System.out.println("GeoTagFunctions.removeGeoTagData() should be called when it is implemented");
                            break;
                    case 5: displayMenu();
                            break;
                    case 6: System.out.println("aboutProgram() should be called when it is implemented");
                            break;
                    case 7: System.exit(0);
                    default: System.out.print("Invalid option. Please enter option 1-7: ");
                }
            }
        }
        catch (InputMismatchException exception) {
            System.out.println("InputMisMatchException occurred. Please only enter integers.");
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

    // Desc: One of the options in the menu
    // Output: Displays basic information about this program
    public static void aboutProgram() {
        System.out.println("\nAugie GeoTag lets you add, edit, and remove GeoTag data from your JPEG/JPG images.");
        System.out.println("GitHub repo: https://github.com/tahaafzal5/AugieGeoTag");
        System.out.println("This program was developed by Taha, Taire, Zach, Tony, Sicheng and Zemenu.\n");
    }
}