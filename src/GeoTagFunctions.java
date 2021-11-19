package src;

import java.io.*;
import java.util.*;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata.GPSInfo;
import org.apache.commons.imaging.formats.tiff.TiffField;

public class GeoTagFunctions {
    
	private static TiffImageMetadata exif = null;

    // Pre: an open and working Scanner object
    // Return: the file the user wants to open if it exists, null otherwise
    // Output: meaningful messages about processing and success when opening the file
    public static File openFile(Scanner input) {
        System.out.print("Enter the file name (including the extension): ");
        String fileName = input.nextLine().strip().toLowerCase();
        
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
            System.out.println(e.getMessage());
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
            System.out.println(exception.getMessage());

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
            System.out.println(jpeg.getName() + ": " + exception.getMessage());

            Utility.displayError("read-metadata");
            return false;
        }
    }
    
    //Return: null if latitude is not with -90 to 90.
    //        a Double value if it is a valid latitude
    public static Double getLatitude(String input)
    {
        //check direction if exists
    	Scanner coordScanner = new Scanner(input);
    	//skip all numeric value
    	while(coordScanner.hasNextDouble())
    		coordScanner.nextDouble();
    	if(coordScanner.hasNext()) {
    		char direction = coordScanner.next().toLowerCase().charAt(0);
    		if(direction != 'n' && direction != 's') {
    			System.out.println("Error direction: use N or S");
    			coordScanner.close();
    			return null;
    		}
    	}
    	coordScanner.close();
    	
    	//range check
    	Double latitude = getCoordinate(input);
        if(latitude == null)
            return null;
        if(latitude < -90 || latitude > 90) {
        	System.out.println("Latitude should be within -90 to 90");
        	return null;
        }
        return latitude;
    }

    //Return: null if longitude is not with -180 to 180.
    //        a Double value if it is a valid longitude
    public static Double getLongitude(String input)
    {
    	//check direction if exists
    	Scanner coordScanner = new Scanner(input);
    	//skip all numeric value
    	while(coordScanner.hasNextDouble())
    		coordScanner.nextDouble();
    	if(coordScanner.hasNext()) {
    		char direction = coordScanner.next().toLowerCase().charAt(0);
    		if(direction != 'e' && direction != 'w') {
    			System.out.println("Error direction: use E or W");
    			coordScanner.close();
    			return null;
    		}
    	}
    	coordScanner.close();
    	
    	Double longitude = getCoordinate(input);
        if(longitude == null)
        	return null;
        if(longitude < -180 || longitude > 180) {
        	System.out.println("Longitude should be within -180 to 180");
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
    	
    	//get degree
    	if ( coordScanner.hasNextDouble() )
    		result += coordScanner.nextDouble();
    	else {
    		System.out.println("Unable to read degree.");
    		coordScanner.close();
    		return null;
    	}
    	
    	//get minute if it exists
    	if( coordScanner.hasNextDouble() )
    		result += coordScanner.nextDouble() / MINUTES_PER_DEGREE;
    	
    	//get second if it exists
    	if( coordScanner.hasNextDouble() )
    		result += coordScanner.nextDouble() / SECONDS_PER_DEGREE;
    	
    	//if the direction is N or E, result should be positive.
    	//if the direction is S or W, result should be negative.
    	if( coordScanner.hasNext()) {
    		String direction = coordScanner.next();
    		if (result < 0) {
    			System.out.println("Please use either negative value or directon reference.");
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
    public static GPSInfo getGPSInfo(File jpeg){
        
        Utility.displayProcessing("get-GPS");
        readImageMeta(jpeg);
        GPSInfo geotag = null;
        try {
            geotag = exif.getGPS();
            Utility.displaySuccess("get-GPS");
            
        } catch (Exception e) {
            Utility.displayError("get-GPS");
        }
        return geotag;
    }

    //Pre: Image is a jpeg
    //Return: String containing geotag data 
    public static String getGeoTagData(File jpeg) { 
        
	final GPSInfo GPSInfo = getGPSInfo(jpeg);
        
	    //Check if GPS info exists
        if (GPSInfo != null) {
            final String geotagData = "";
            final String latitude_Ref = GPSInfo.latitudeRef;
            final RationalNumber latitude_Degrees = GPSInfo.latitudeDegrees;
            final String longitude_Ref = GPSInfo.longitudeRef;
            final RationalNumber longitude_Degrees = GPSInfo.longitudeDegrees ;

            //Add geotag info into string to be returned
            geotagData.concat(latitude_Degrees.toString() + " ");
            geotagData.concat(latitude_Ref + " ");
            geotagData.concat(longitude_Degrees.toString());
            geotagData.concat(longitude_Ref.toString() + " ");
            return geotagData;
        }

        //Return null if GPS info does not exist 
        else return null;
    } 
    
    // Pre: This function save image in results folder under asserts
    // Return: return true if geotag is successfully updated. false otherwise
    // Output: a image without geotag. If the writing process failed, no change would happen
    public static boolean updateGeoTagData(File jpeg, double latitude, double longitude) {
    	File resultsFolder = new File("./assets/results");
        
        if (!resultsFolder.exists())
            resultsFolder.mkdir();
    	
        File result = new File("./assets/results/" + jpeg.getName());
    	
        return updateGeoTagData(jpeg, result, latitude, longitude);
    }
    
    // Pre: This function save image in results folder under asserts
    // Return: return true if geotag is successfully removed. false otherwise
    // Output: a image without geotag. If the writing process failed, no change would happen
    public static boolean removeGeoTagData(File jpeg) {
    	File resultsFolder = new File("./assets/results");

        if (!resultsFolder.exists())
            resultsFolder.mkdir();

    	File result = new File("./assets/results/" + jpeg.getName());
    	
        return removeGeoTagData(jpeg, result);
    }
    
    // Pre: this method will not fail if there is not geotag in image
    // Return: return true if geotag is successfully removed. false otherwise
    // Output: a image without geotag. If the writing process failed, no change would happen
    private static boolean removeGeoTagData(File jpeg, File result) {
    	try {
    		final int LATITUDE_REFERENCE_TAG = 1;
    	    final int LATITUDE_TAG = 2;
    	    final int LONGTITUDE_REFERENCE_TAG = 3;
    	    final int LONGTITUDE_TAG = 4;
    		
    	    //copy the original information
    	    GeoTagFunctions.readImageMeta(jpeg);
    		
    	    TiffOutputSet outputSet = null;
    		
    		if (exif != null) {
                //get a copy of exif data to be muted.
                outputSet = exif.getOutputSet();
            } 
            else {
            	outputSet = new TiffOutputSet();
            	
            	//avoid empty exif data that cause null pointer error in output
            	outputSet.getOrCreateExifDirectory();
            }
    		
    		//Remove latitude and longitude value.
    		Utility.displayProcessing("remove-geotag");

    		outputSet.removeField(LATITUDE_REFERENCE_TAG);
            outputSet.removeField(LATITUDE_TAG);
            outputSet.removeField(LONGTITUDE_REFERENCE_TAG);
            outputSet.removeField(LONGTITUDE_TAG);
            
            Utility.displaySuccess("remove-geotag");
            
            return saveJpegImage(jpeg, result, outputSet);           	
    	}
        catch (Exception exception) {
    		Utility.displayError("remove-geotag");
        	result.delete();
        	
            System.out.println(jpeg.getName() + ": " + exception.getMessage());
    		return false;
    	}
    }
    
    // Pre: latitude and longitude should be passed as two double value
    // Return: return true if geotag is successfully written. false otherwise
    // Output: a image with new geotag written in. If the writing process failed, no change would happen
    private static boolean updateGeoTagData(File jpeg, File result, double latitude, double longitude) {
        try {
        	//copy the original information
    	    GeoTagFunctions.readImageMeta(jpeg);
        	
    	    TiffOutputSet outputSet = null;
            
            // if file does not contain any exif metadata, we create an empty
            // set of exif metadata. Otherwise, we keep all of the other existing tags.
            if (exif != null) {
                //get a copy of exif data to be muted.
                outputSet = exif.getOutputSet();
            } 
            else {
            	outputSet = new TiffOutputSet();
            }
            
            Utility.displayProcessing("update-geotag");
            outputSet.setGPSInDegrees(longitude, latitude);
            Utility.displaySuccess("update-geotag");
            
            return saveJpegImage(jpeg, result, outputSet);           	
        }
        catch (Exception exception) {
            Utility.displayError("update-geotag");
        	result.delete();
        	System.out.println(jpeg.getName() + ": " + exception.getMessage());

        	return false;
        }
    }
    
    // Pre: open files
    // Output: a jpeg file with new exif in outputSet is written. Nothing would change if method failed
    private static boolean saveJpegImage(File jpeg, File result, TiffOutputSet outputSet){
        try {
            // set up output stream
            FileOutputStream fos = new FileOutputStream(result);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fos);
                
            // write exif metadata into new jpeg/jpg file
            Utility.displayProcessing("save-image");
            new ExifRewriter().updateExifMetadataLossless(jpeg, bufferedOutputStream, outputSet);
            
            Utility.displaySuccess("save-image");
            
            return true;
        } 
        catch (Exception e) {
            Utility.displayError("save-image");
            result.delete();
            System.out.println(jpeg.getName() + ": " + e.getMessage());
            
            return false;
        }
    }
}
