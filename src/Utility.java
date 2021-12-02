package src;

import java.util.*;

public class Utility {

    // Output: Basic menu of the program to let the user do essential tasks
    public static void displayMenu() {
        System.out.println("\nAugie GeoTag Menu:");
        System.out.println("\t1. Open an image");
        System.out.println("\t2. Add GeoTag");
        System.out.println("\t3. Update GeoTag");
        System.out.println("\t4. Remove GeoTag");
        System.out.println("\t5. Clear Program State");
        System.out.println("\t6. Help Menu");
        System.out.println("\t7. About");
        System.out.println("\t8. Exit");
    }

    // Pre: an open and working Scanner object
    // Output: Shows a help menu about Augie GeoTag explaining what each menu option does
    public static void displayHelpMenu() {
        System.out.println("\nHelp Menu:");

        System.out.println("1. Open an image:\t Lets you select the specific image you would like to edit");
        System.out.println("\t\t\t the GeoTag of. It must be the exact file name including the file extension.");
        System.out.println("2. Add GeoTag:\t\t Lets you add a GeoTag to the image you opened with option 1.");
        System.out.println("\t\t\t It will ask for latitude and longitude in this format: <<<<< TO ADD >>>>>");
        System.out.println("3. Update GeoTag:\t Allows you to remove an original GeoTag and add a new one on the image you opened with option 1.");
        System.out.println("\t\t\t It will ask for latitude and longitude in this format: <<<<< TO ADD >>>>>");
        System.out.println("4. Remove GeoTag:\t Lets you remove the GeoTag on the image you opened with option 1.");
        System.out.println("5. Clear Program State:\t Resets the program to its initial state. You should do this before opening a new image.");
        System.out.println("6. Help Menu:\t\t Shows this menu.");
        System.out.println("7. About:\t\t Shows the purpose, GitHub repo link, and the developer names for this project.");
        System.out.println("8. Exit:\t\t Safely exits the program.");
    }

    // Pre: an open and working Scanner object
    // Desc: depending on the user's choice from the main menu, this method calls the appropriate function
    public static int handleUserMenuChoice(Scanner input) { 
        System.out.print("\nPlease enter selection (1-8): ");
        Integer choice = 0;
        
        try {
            while (true) {
                choice = Integer.parseInt(String.valueOf(input.nextLine().strip()));

                while (choice < 1 || choice > 8) {
                    System.out.print("Invalid option. Please enter option 1-8: ");
                    choice = Integer.parseInt(String.valueOf(input.nextLine().strip()));
                }

                return choice;
            }
        }
        catch (NumberFormatException exception) {
            System.err.println("NumberFormatException occured");
        }
        catch (Exception exception) {
            System.err.println("Exception occured");
        }

        // base case is exiting the program 
        return 8;
    } 

    public static void askConfirmation(String type) {
        switch (type) {
            case "find-file":
                System.out.println("Are you sure you want to find the file?"); break;
            case "open-file":
                System.out.println("Are you sure you want to open a file?"); break;
            case "read-geotag":
                System.out.println("Are you sure you want to read the GeoTag?"); break;
            case "remove-geotag":
                System.out.println("Are you sure you want to remove the GeoTag?"); break;
            case "write-geotag":
                System.out.println("Are you sure you want to write a GeoTag?"); break;
            case "update-geotag":
                System.out.println("Are you sure you want to update the GeoTag?"); break;
            case "save-image":
                System.out.println("Are you sure you want to save the image?"); break;
            case "clear-state":
                System.out.println("Are you sure you want to clear program state?"); break;
                
            default:
                System.out.println("Are you sure you want to do this?");
        }
    }
    
    // Pre: an open and working Scanner object
    // Input: 'y' for yes and 'n' for no to confirm action
    // Output: message for the user asking if they are sure. Another message if wrong choice is entered
    // Return: true if user confirms action, false otherwise
    public static boolean handleUserConfirmationChoice(Scanner input) {  
        System.out.print("'Y' for Yes & 'N' for No: ");
        
        try {
            while (true) {
                Character userResponse = input.nextLine().strip().toLowerCase().charAt(0);
        
                if (userResponse == 'y')
                    return true;
                else if (userResponse == 'n')
                    return false;
                else
                    System.out.print("Wrong choice. Enter 'Y' for Yes & 'N' for No: ");
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    // Pre: the argument type must be one of the cases in the switch-cases to get a meaningful message
    // Output: a meaningful message based on the error type
    public static void displayError(String type) {
        switch (type) {
            case "find-file":
                System.err.println("Error: The file you are trying to open does not exist in this project's assets folder."); break;
            case "open-file":
                System.err.println("Error: Can't open the file you are trying to open."); break;
            case "no-file":
                System.err.println("Error: No file is open to perform this operation on. Open a file first."); break;
            case "check-jpeg": 
                System.err.println("Error: Check that you uploaded an acceptable file format (JPEG/JPG)."); break;
            case "check-multiple":
                System.err.println("Error: You are attempting to open more than 1 image, please open only one image at a time or clear program state to open another image."); break;
            case "read-metadata":
                System.err.println("Error: Can't read the metadata of this file."); break;
            case "remove-geotag":
                System.err.println("Error: Can't remove GeoTag from this image."); break;
            case "no-geotag":
                System.err.println("Error: GeoTag does not exist in this image, please open a different image or try adding the GeoTag.");
            case "update-geotag": 
                System.err.println("Error: GeoTag does not exist in this image, please open a different image or try adding the GeoTag."); break;
            case "add-geotag":
                System.err.println("Error: GeoTag already exists in this image, please open a different image or try updating the GeoTag."); break;
            case "save-image":
            	System.err.println("Error: Can't output the image."); break;
            case "get-GPS":
                System.err.println("Error: Can't get the GPS data from the image."); break;
            case "clear-state":
                System.err.println("Error: Can't clear program state."); break;

            default:
                System.err.println("Error.");
        }
    }

    // Pre: the argument type must be one of the cases in the switch-cases to get a meaningful message
    // Output: a meaningful message based on the success type
    public static void displaySuccess(String type) {
        switch (type) {
            case "find-file":
                System.out.println("Success: File found successfully"); break;
            case "open-file":
                System.out.println("Success: File opened successfully"); break;
            case "read-metadata":
                System.out.println("Success: Metadata read successfully"); break;
            case "check-jpeg":
                System.out.println("Success: Checked JPEG/JPG successfully"); break;
            case "read-geotag":
                System.out.println("Success: Read GeoTag successfully"); break;
            case "remove-geotag":
                System.out.println("Success: Removed GeoTag successfully"); break;
            case "write-geotag":
                System.out.println("Success: Wrote GeoTag successfully"); break;
            case "update-geotag":
                System.out.println("Success: Updated GeoTag successfully"); break;
            case "save-image":
                System.out.println("Success: Image saved successfully"); break;
            case "get-GPS":
                System.out.println("Success: GPS data retrieved successfully"); break;
            case "clear-state":
                System.out.println("Success: Program state cleared successfully"); break;

            default:
                System.out.println("Success");
        }
    } 

    // Pre: the argument type must be one of the cases in the switch-cases to get a meaningful message
    // Output: a meaningful message based on the processing type
    public static void displayProcessing(String type) {
        switch (type) {
            case "find-file":
                System.out.println("Finding file..."); break;
            case "open-file":
                System.out.println("Opening file..."); break;
            case "read-metadata":
                System.out.println("Reading image metadata..."); break;
            case "check-jpeg":
                System.out.println("Checking if the image is a JPEG/JPG..."); break;
            case "read-geotag":
                System.out.println("Reading the GeoTag in the image..."); break;
            case "remove-geotag":
                System.out.println("Removing GeoTag from the image..."); break;
            case "update-geotag":
                System.out.println("Updating GeoTag in the image..."); break;
            case "write-metadata":
                System.out.println("Writing the metadata to the image..."); break;
            case "save-image":
                System.out.println("Saving the image..."); break;
            case "get-GPS":
                System.out.println("Getting the GPS data from the image..."); break;
            case "clear-state":
                System.out.println("Clearing program state..."); break;

            default:
                System.out.println("Processing...");
        }
    }

    // Desc: One of the options in the menu
    // Output: Displays basic information about this program
    public static void aboutProgram() {
        System.out.println("\nAugie GeoTag lets you add, edit, and remove GeoTag data from your JPEG/JPG images.");
        System.out.println("GitHub repo: https://github.com/tahaafzal5/AugieGeoTag");
        System.out.println("This program was developed by Taha, Taire, Zach, Tony, Sicheng, and Zemenu.");
    }

    // <<<<<<<<<<< TO DO >>>>>>>>>>>>>>>>
    public static void exitProgram() {
        System.exit(0);
    }
}