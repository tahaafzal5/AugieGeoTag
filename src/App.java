package src;

import java.util.Scanner;
import java.io.*;

import org.apache.commons.imaging.formats.tiff.TiffImageMetadata.GPSInfo;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int userMenuChoice = 0;

        boolean isFileOpen = false;
        File openedFile = null;
        boolean isJpeg = false;
        File jpegImage = null;
        boolean userConfirmationChoice = false;
        String geoTagData = null;
        GPSInfo gpsInfo = null;

        String latitudeString = null;
        String longitudeString = null;
        Double latitude = null;
        Double longitude = null;

        while (userMenuChoice != 8) {
            Utility.displayMenu();
            userMenuChoice = Utility.handleUserMenuChoice(input);

            switch (userMenuChoice) {
                case 1: if (!isFileOpen) {
                            Utility.askConfirmation("open-file");
                            userConfirmationChoice = Utility.handleUserConfirmationChoice(input);
                            
                            if (userConfirmationChoice) {
                                openedFile = GeoTagFunctions.openFile(input);
                                
                                if (openedFile != null) {
                                    isJpeg = GeoTagFunctions.isJpeg(openedFile);
                                    
                                    if (isJpeg) {
                                        isFileOpen = true;

                                        jpegImage = openedFile;
                                    
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
                        }
                        else {
                            Utility.displayError("check-multiple");
                        }
                        break;

                case 2: if (jpegImage != null) {
                            gpsInfo = GeoTagFunctions.getGPSInfo(jpegImage);

                            if (gpsInfo == null) {
                                Utility.askConfirmation("add-geotag");
                                userConfirmationChoice = Utility.handleUserConfirmationChoice(input);

                                if (userConfirmationChoice) {
                                    System.out.print("Enter the latitude: ");
                                    latitudeString = input.nextLine();

                                    latitude = GeoTagFunctions.getLatitude(latitudeString);
                                    while (latitude == null) {
                                        System.out.print("Re-enter the latitude in the correct format: ");
                                        latitudeString = input.nextLine();

                                        latitude = GeoTagFunctions.getLatitude(latitudeString);
                                    }

                                    System.out.print("Enter the longitude: ");
                                    longitudeString = input.nextLine();

                                    longitude = GeoTagFunctions.getLongitude(longitudeString);
                                    while (longitude == null) {
                                        System.out.print("Re-enter the longitude in the correct format: ");
                                        longitudeString = input.nextLine();

                                        longitude = GeoTagFunctions.getLongitude(longitudeString);
                                    }

                                    GeoTagFunctions.addGeoTagData(jpegImage, latitude, longitude);
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

                case 3: if (jpegImage != null) {
                            gpsInfo = GeoTagFunctions.getGPSInfo(jpegImage);

                            if (gpsInfo != null) {
                                Utility.askConfirmation("update-geotag");
                                userConfirmationChoice = Utility.handleUserConfirmationChoice(input);

                                if (userConfirmationChoice) {
                                    System.out.print("Enter the latitude: ");
                                    latitudeString = input.nextLine();

                                    latitude = GeoTagFunctions.getLatitude(latitudeString);
                                    while (latitude == null) {
                                        System.out.print("Re-enter the latitude in the correct format: ");
                                        latitudeString = input.nextLine();

                                        latitude = GeoTagFunctions.getLatitude(latitudeString);
                                    }

                                    System.out.print("Enter the longitude: ");
                                    longitudeString = input.nextLine();

                                    longitude = GeoTagFunctions.getLongitude(longitudeString);
                                    while (longitude == null) {
                                        System.out.print("Re-enter the longitude in the correct format: ");
                                        longitudeString = input.nextLine();

                                        longitude = GeoTagFunctions.getLongitude(longitudeString);
                                    }

                                    GeoTagFunctions.updateGeoTagData(jpegImage, latitude, longitude);
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

                case 4: if (jpegImage != null) {
                            Utility.askConfirmation("remove-geotag");
                            userConfirmationChoice = Utility.handleUserConfirmationChoice(input);

                            if (userConfirmationChoice) {
                                gpsInfo = GeoTagFunctions.getGPSInfo(jpegImage);

                                if (gpsInfo != null)
                                    GeoTagFunctions.removeGeoTagData(jpegImage);
                            }
                        }
                        else {
                            Utility.displayError("no-file");
                        }
                        break;

                case 5: Utility.askConfirmation("clear-state");
                        userConfirmationChoice = Utility.handleUserConfirmationChoice(input);

                        if (userConfirmationChoice) {
                            Utility.displayProcessing("clear-state");
                            
                            input = new Scanner(System.in);
                            userMenuChoice = 0;
                    
                            isFileOpen = false;
                            openedFile = null;
                            isJpeg = false;
                            jpegImage = null;
                            userConfirmationChoice = false;
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

                case 6: Utility.displayHelpMenu();
                        break;

                case 7: Utility.aboutProgram();
                        break;
                        
                case 8: Utility.exitProgram();
            }
        }

        input.close();
    }
}