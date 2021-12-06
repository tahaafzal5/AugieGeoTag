package src;

import java.util.Scanner;
import java.io.*;

import org.apache.commons.imaging.formats.tiff.TiffImageMetadata.GPSInfo;

public class App {
    public static void main(String[] args) {

        // initializing all the needed variables
        // these need to be reset when program state is reset
        Scanner input = new Scanner(System.in);
        int userMenuChoice = 0;

        boolean isFileOpen = false;
        File openedFile = null;
        boolean isJpeg = false;
        File jpegImage = null;
        boolean userConfirmationChoice = false;
        boolean operationResult = false;

        String geoTagData = null;
        GPSInfo gpsInfo = null;

        String latitudeString = null;
        String longitudeString = null;
        Double latitude = null;
        Double longitude = null;

        // continuous loop until the user exits the program
        while (userMenuChoice != 8) {

            // the main menu is displayed in the beginning and after every operation
            Utility.displayMenu();
            userMenuChoice = Utility.handleUserMenuChoice(input);

            switch (userMenuChoice) {
                // open an image
                case 1: if (!isFileOpen) {
                            openedFile = GeoTagFunctions.openFile(input);
                            
                            // user is only allowed to open one image at a time
                            if (openedFile != null) {
                                // check if the opened file is a jpeg image or not
                                isJpeg = GeoTagFunctions.isJpeg(openedFile);
                                
                                if (isJpeg) {
                                    // file is considered open if it is a jpeg
                                    isFileOpen = true;

                                    jpegImage = openedFile;
                                
                                    // checking if the jpeg image has geoTagData or not
                                    geoTagData = GeoTagFunctions.getGeoTagData(jpegImage);

                                    if (geoTagData != null) {
                                        System.out.println(jpegImage.getName() + " has the following GeoTagData:");
                                        System.out.println(geoTagData);
                                    }
                                    else {
                                        System.out.println(jpegImage.getName() + " has no GeoTagData.");
                                    }
                                }
                            }
                        }
                        else {
                            Utility.displayError("check-multiple");
                        }
                        break;

                // Add GeoTag
                //      a jpeg image has to be open to add a GeoTag
                case 2: if (jpegImage != null) {
                            // check if GPSInfo exists on the jpeg image
                            gpsInfo = GeoTagFunctions.getGPSInfo(jpegImage);

                            // user can only add a GeoTag if it does not exist already
                            if (gpsInfo == null) {
                                // ask for and handle confirmation
                                Utility.askConfirmation("add-geotag");
                                userConfirmationChoice = Utility.handleUserConfirmationChoice(input);

                                if (userConfirmationChoice) {
                                    // have user enter the latitude
                                    System.out.print("Enter the latitude: ");
                                    latitudeString = input.nextLine();
                                    
                                    latitude = GeoTagFunctions.getLatitude(latitudeString);
                                    // validate the input to be the correct format
                                    while (latitude == null) {
                                        System.out.print("Re-enter the latitude in the correct format: ");
                                        latitudeString = input.nextLine();

                                        latitude = GeoTagFunctions.getLatitude(latitudeString);
                                    }

                                    // have user enter the longitude
                                    System.out.print("Enter the longitude: ");
                                    longitudeString = input.nextLine();

                                    longitude = GeoTagFunctions.getLongitude(longitudeString);
                                    // validate the input to be the correct format
                                    while (longitude == null) {
                                        System.out.print("Re-enter the longitude in the correct format: ");
                                        longitudeString = input.nextLine();

                                        longitude = GeoTagFunctions.getLongitude(longitudeString);
                                    }

                                    // if all good, geoTagData is added to the jpeg image
                                    operationResult = GeoTagFunctions.addGeoTagData(jpegImage, latitude, longitude);

                                    // was the geotag added successfully or not
                                    if (operationResult) {
                                        Utility.displaySuccess("add-geotag");
                                    }
                                    else {
                                        Utility.displayError("add-geotag");
                                    }
                                }
                            }
                            else {
                                Utility.displayError("add-geotag");
                            }
                        }
                        else {
                            Utility.displayError("no-file");
                        }
                        break;

                // Update GeoTag
                //      a jpeg image has to be open to add a GeoTag
                case 3: if (jpegImage != null) {
                            // check if GPSInfo exists on the jpeg image
                            gpsInfo = GeoTagFunctions.getGPSInfo(jpegImage);

                            // user can only update a GeoTag if it exists already
                            if (gpsInfo != null) {
                                // ask for and handle confirmation
                                Utility.askConfirmation("update-geotag");
                                userConfirmationChoice = Utility.handleUserConfirmationChoice(input);

                                if (userConfirmationChoice) {
                                    // have user enter the latitude
                                    System.out.print("Enter the latitude: ");
                                    latitudeString = input.nextLine();

                                    latitude = GeoTagFunctions.getLatitude(latitudeString);
                                    // validate the input to be the correct format
                                    while (latitude == null) {
                                        System.out.print("Re-enter the latitude in the correct format: ");
                                        latitudeString = input.nextLine();
                                        
                                        latitude = GeoTagFunctions.getLatitude(latitudeString);
                                    }
                                    
                                    // have user enter the longitude
                                    System.out.print("Enter the longitude: ");
                                    longitudeString = input.nextLine();
                                    
                                    longitude = GeoTagFunctions.getLongitude(longitudeString);
                                    // validate the input to be the correct format
                                    while (longitude == null) {
                                        System.out.print("Re-enter the longitude in the correct format: ");
                                        longitudeString = input.nextLine();

                                        longitude = GeoTagFunctions.getLongitude(longitudeString);
                                    }

                                    // if all good, geoTagData in the jpeg image is updated
                                    operationResult = GeoTagFunctions.updateGeoTagData(jpegImage, latitude, longitude);

                                    // was the geotag updated successfully or not
                                    if (operationResult) {
                                        Utility.displaySuccess("update-geotag");
                                    }
                                    else {
                                        Utility.displayError("update-geotag");
                                    }
                                }
                            }
                            else {
                                Utility.displayError("update-geotag");
                            }
                        }
                        else {
                            Utility.displayError("no-file");
                        }
                        break;

                // Remove GeoTag
                //      a jpeg image has to be open to remove a GeoTag
                case 4: if (jpegImage != null) {
                            // check if GPSInfo exists on the jpeg image
                            gpsInfo = GeoTagFunctions.getGPSInfo(jpegImage);

                            if (gpsInfo != null) {
                                // ask for and handle confirmation
                                Utility.askConfirmation("remove-geotag");
                                userConfirmationChoice = Utility.handleUserConfirmationChoice(input);

                                if (userConfirmationChoice) {
                                    // if all good, geoTagData is removed from the jpeg image
                                    operationResult = GeoTagFunctions.removeGeoTagData(jpegImage);
                                    
                                    // was the geotag removed successfully or not
                                    if (operationResult) {
                                        Utility.displaySuccess("remove-geotag");
                                    }
                                    else {
                                        Utility.displayError("remove-geotag");
                                    }
                                }
                            }
                            else {
                                Utility.displayError("no-geotag");
                            }
                        }
                        else {
                            Utility.displayError("no-file");
                        }
                        break;

                // Clear Program State
                //      ask for and handle confirmation
                case 5: Utility.askConfirmation("clear-state");
                        userConfirmationChoice = Utility.handleUserConfirmationChoice(input);

                        if (userConfirmationChoice) {
                            Utility.displayProcessing("clear-state");
                            
                            // all the needed variables are reset here
                            userMenuChoice = 0;
                    
                            isFileOpen = false;
                            openedFile = null;
                            isJpeg = false;
                            jpegImage = null;
                            userConfirmationChoice = false;
                            operationResult = false;

                            geoTagData = null;
                            gpsInfo = null;

                            latitudeString = null;
                            longitudeString = null;
                            latitude = null;
                            longitude = null;
    
                            Utility.displaySuccess("clear-state");
                        }
                        else {
                            System.out.println("Program state not cleared.");
                        }
                        break;

                // Help Menu
                //      display help menu for this program
                case 6: Utility.displayHelpMenu();
                        break;

                // About
                //      display basic information about this program
                case 7: Utility.aboutProgram();
                        break;

                // Exit
                //      break the main continuous loop and exit the program 
                case 8:
                        break;
            }
        }

        input.close();
    }
}