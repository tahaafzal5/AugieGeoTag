package src;

import java.io.*;
import java.util.*;

import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;

import jpeg.Jpeg;
import jpeg.JpegOutputSet;

import org.apache.commons.imaging.formats.tiff.TiffImageMetadata.GPSInfo;
import org.apache.commons.imaging.common.RationalNumber;

public class GeoTagFunctions {
    
	private static TiffImageMetadata exif = null;

    // Pre: an open and working Scanner object
    // Return: the file the user wants to open if it exists, null otherwise
    // Output: meaningful messages about processing and success when opening the file
    public static File openFile(Scanner input) {
        System.out.print("Enter the file name (including the extension): ");
        String fileName = input.nextLine().strip();
        
        try {
            File file = new File("./assets/" + fileName);
            
            Utility.displayProcessing("find-file");
            if (file.exists()) {
                Utility.displaySuccess("open-file");

                return file;
            }
            else {
                Utility.displayError("find-file");
            }
        } 
        catch (Exception e) {
            Utility.displayError("find-file");
            System.err.println(e.getMessage());
        }

        return null;
    }
	
    // Pre: an open file
  	// Return: true if passed file is a JPEG/JPG, false otherwise.
    // Output: Error message if the file reading process contains error.
  	public static boolean isJpeg(File file) {    
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

            // read the marker
            byte[] fileMarker = new byte[2];
            bufferedInputStream.read(fileMarker);
                
            bufferedInputStream.close();
                
            // check the file type
            Utility.displayProcessing("check-jpeg");
            if ((fileMarker[0] & 0xFF) == 0xFF && (fileMarker[1] & 0xFF) == 0xD8) {
                Utility.displaySuccess("check-jpeg");

                return true;
            }
            else {
                Utility.displayError("check-jpeg");
                
                return false;
            }
        }
        catch (Exception exception) {
            Utility.displayError("check-jpeg");
            System.err.println(exception.getMessage());

            return false;
        }
  	}

    // Pre: file passed in as argument should be JPEG/JPG
    // Return: true if metadata read successfully, false otherwise
    // Output: error message if program throw Exception
    // Reminder: exif might be a null. It is important to check it because there might be no metadata.
    public static boolean readImageMeta(File jpeg) {
        try {
            // get Metadata
            final ImageMetadata metadata = Imaging.getMetadata(jpeg);
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            
            Utility.displayProcessing("read-metadata");
            if (jpegMetadata != null) {
                exif = jpegMetadata.getExif();
            }

            Utility.displaySuccess("read-metadata");

            return true;
        } 
        catch (Exception exception) {
            System.err.println(jpeg.getName() + ": " + exception.getMessage());

            Utility.displayError("read-metadata");
            return false;
        }
    }
    
    //Return: null if latitude is not with -90 to 90.
    //        a Double value if it is a valid latitude
    public static Double getLatitude(String input) {
        Scanner coordScanner = new Scanner(input);
        
    	// skip all numeric value
    	while (coordScanner.hasNextDouble())
            coordScanner.nextDouble();
    	
        // check direction if exists
        if (coordScanner.hasNext()) {
    		char direction = coordScanner.next().toLowerCase().charAt(0);
    		
            if (direction != 'n' && direction != 's') {
    			System.err.println("Error: for direction use N or S");
    			
                coordScanner.close();
    		
                return null;
    		}
    	}
    	coordScanner.close();
    	
    	// range check
    	Double latitude = getCoordinate(input);
        
        if (latitude == null)
            return null;
        
        if (latitude < -90 || latitude > 90) {
        	System.err.println("Latitude should be within -90 to 90");
        	
            return null;
        }

        return latitude;
    }

    //Return: null if longitude is not with -180 to 180.
    //        a Double value if it is a valid longitude
    public static Double getLongitude(String input) {
        Scanner coordScanner = new Scanner(input);
    	
        // skip all numeric value
    	while (coordScanner.hasNextDouble())
            coordScanner.nextDouble();
    	
    	// check direction if exists
        if (coordScanner.hasNext()) {
    		char direction = coordScanner.next().toLowerCase().charAt(0);
    		
            if (direction != 'e' && direction != 'w') {
    			System.err.println("Error: for direction use E or W");
    			
                coordScanner.close();
    			
                return null;
    		}
    	}
    	coordScanner.close();
    	
        // range check
    	Double longitude = getCoordinate(input);
        
        if (longitude == null)
        	return null;
        
        if (longitude < -180 || longitude > 180) {
        	System.err.println("Longitude should be within -180 to 180");
        	
            return null;
        }

        return longitude;
    }

    //Pre: each should be separated by white space (example: 100 30 20.99 N)
    //Return: a double that represents passed latitude or longitude coordinate
    //  	  N and E would be a positive value. S and W would be negative value.
    //Reminder: Null pointer would be returned if format is wrong.
    //Support format example:
    //	  100 30 20.99 N
    //	  100 40.99 S
    //	  100.88 W
    //    100 30 20.99  (you can type in positive or negative to represent the direction)
    //    -100 -30 -20.99
    //	  100 40.99
    //    -100 -40.99
    //	  100.88
    //	  -100.88
    private static Double getCoordinate(String input) {
    	final int MINUTES_PER_DEGREE = 60;
    	final int SECONDS_PER_DEGREE = 3600;
    	
    	double result = 0;
    	
    	Scanner coordScanner = new Scanner(input);
    	
    	// get degree
    	if (coordScanner.hasNextDouble())
    		result += coordScanner.nextDouble();
    	else {
    		System.err.println("Unable to read degrees.");
    		coordScanner.close();
    		
            return null;
    	}
    	
    	// get minute if it exists
    	if (coordScanner.hasNextDouble()) {
    		result += (coordScanner.nextDouble() / MINUTES_PER_DEGREE);
    	}
    	
    	// get second if it exists
    	if (coordScanner.hasNextDouble())
    		result += (coordScanner.nextDouble() / SECONDS_PER_DEGREE);
    	
    	// if the direction is N or E, result should be positive.
    	// if the direction is S or W, result should be negative.
    	if (coordScanner.hasNext()) {
    		String direction = coordScanner.next();
    		
            if (result < 0) {
    			System.err.println("Please use either negative value or directon reference.");
    			coordScanner.close();
    			
                return null;
    		}

    		if (direction.equals("S") || direction.equals("W"))
    			result = -result;
        }

    	coordScanner.close();

    	return result;
    }

    // Pre: exif should be read
    // Return: true if exif has GPS info, false otherwise
    // Output: error message if program throw Exception
    public static GPSInfo getGPSInfo(File jpeg) {
        Utility.displayProcessing("get-GPS");
        
        readImageMeta(jpeg);
        GPSInfo geotag = null;
        
        try {
            geotag = exif.getGPS();
            Utility.displaySuccess("get-GPS");    
        }
        catch (Exception e) {
            Utility.displayError("get-GPS");
            System.err.println(e.getMessage());
        }

        if (geotag == null)
            Utility.displayError("no-geotag");

        return geotag;
    }

    // Pre: Image is a jpeg
    // Return: String containing geotag data 
    public static String getGeoTagData(File jpeg) {
        final GPSInfo GPSInfo = getGPSInfo(jpeg);
        
        // check if GPS info exists
        if (GPSInfo != null) {
            StringBuilder geotagData = new StringBuilder();

            final RationalNumber latitudeDegrees = GPSInfo.latitudeDegrees;
            final RationalNumber latitudeMinutes = GPSInfo.latitudeMinutes;
            final RationalNumber latitudeSeconds = GPSInfo.latitudeSeconds;
            final String latitudeRef = GPSInfo.latitudeRef;
            
            final RationalNumber longitudeDegrees = GPSInfo.longitudeDegrees;
            final RationalNumber longitudeMinutes = GPSInfo.longitudeMinutes;
            final RationalNumber longitudeSeconds = GPSInfo.longitudeSeconds;
            final String longitudeRef = GPSInfo.longitudeRef;

            geotagData.append("Latitude: ");
            geotagData.append(latitudeDegrees.toDisplayString() + " degrees, ");
            geotagData.append(latitudeMinutes.toDisplayString() + " minutes, ");
            geotagData.append(latitudeSeconds.toDisplayString() + " seconds ");
            geotagData.append(latitudeRef + ", ");

            geotagData.append("Longitude: ");
            geotagData.append(longitudeDegrees.toDisplayString() + " degrees, ");
            geotagData.append(longitudeMinutes.toDisplayString() + " minutes, ");
            geotagData.append(longitudeSeconds.toDisplayString() + " seconds ");
            geotagData.append(longitudeRef);

            return geotagData.toString();
        }
        // return null if GPS info does not exist 
        else
            return null;
    }

    public static boolean addGeoTagData(File jpeg, double latitude, double longitude) {
        return updateGeoTagData(jpeg, latitude, longitude);
    }

    // Pre: This function save image in results folder under asserts
    // Return: return true if geotag is successfully updated. false otherwise
    // Output: a image without geotag. If the writing process failed, no change would happen
    public static boolean updateGeoTagData(File jpeg, double latitude, double longitude) {
    	File resultsFolder = new File("./assets/results");
        
        if (!resultsFolder.exists())
            resultsFolder.mkdir();
    	
        File result = new File("./assets/results/editted-" + jpeg.getName());
    	
        try {
        	Jpeg image = new Jpeg(jpeg);
            JpegOutputSet outputSet = new JpegOutputSet(image);  
            
            return outputSet.updateGeoTag(result, latitude, longitude);
        }
        catch (Exception exception) {
            Utility.displayError("update-geotag");
        	result.delete();
        	System.err.println(jpeg.getName() + ": " + exception.getMessage());

        	return false;
        }
    }
    
    // Pre: this method will not fail if there is not geotag in image
    // Return: return true if geotag is successfully removed. false otherwise
    // Output: a image without geotag. If the writing process failed, no change would happen
    public static boolean removeGeoTagData(File jpeg) {
        File resultsFolder = new File("./assets/results");

        if (!resultsFolder.exists())
            resultsFolder.mkdir();

        File resultFile = new File("./assets/results/editted-" + jpeg.getName());

    	try {
    		Jpeg image = new Jpeg(jpeg);
            JpegOutputSet outputSet = new JpegOutputSet(image);  
            
            return outputSet.removeGeoTag(resultFile);
    	}
        catch (Exception exception) {
    		Utility.displayError("remove-geotag");
        	resultFile.delete();
        	
            System.err.println(jpeg.getName() + ": " + exception.getMessage());
            
    		return false;
    	}
    }
}
