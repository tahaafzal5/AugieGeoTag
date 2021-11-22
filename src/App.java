package src;

import java.util.Scanner;
import java.io.*;

import org.apache.commons.imaging.formats.tiff.TiffImageMetadata.GPSInfo;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int userMenuChoice = 0;

        File openedFile = null;
        boolean isJpeg = false;
        File jpegImage = null;
        boolean userConfirmationChoice = false;
        String geoTagData = null;

        while (userMenuChoice != 7) {
            Utility.displayMenu();
            userMenuChoice = Utility.handleUserMenuChoice(input);

            switch (userMenuChoice) {
                case 1: Utility.askConfirmation("open-file");
                        userConfirmationChoice = Utility.handleUserConfirmationChoice(input);
                        
                        if (userConfirmationChoice) {
                            openedFile = GeoTagFunctions.openFile(input);
                            
                            if (openedFile != null) {
                                isJpeg = GeoTagFunctions.isJpeg(openedFile);
                                
                                if (isJpeg) {
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
        
                        break;
                        
                case 2: break; // TO DO
                case 3: break; // TO DO
                case 4: if (jpegImage != null) {
                            Utility.askConfirmation("remove-geotag");
                            userConfirmationChoice = Utility.handleUserConfirmationChoice(input);

                            if (userConfirmationChoice) {
                                GPSInfo gpsInfo = GeoTagFunctions.getGPSInfo(jpegImage);

                                if (gpsInfo != null)
                                    GeoTagFunctions.removeGeoTagData(jpegImage);
                            }
                        }
                        else {
                            Utility.displayError("no-file");
                        }
                
                        break;
                case 5: Utility.displayHelpMenu();
                        break;
                case 6: Utility.aboutProgram();
                        break;
                case 7: Utility.exitProgram();
            }
        }

        input.close();
    }
}