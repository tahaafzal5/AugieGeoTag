package src;

import java.io.*;
import java.util.*;

import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;

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

    /* protected ... boolean hasGeoTagData(...) {
        ...
    } */

    /* protected ... ... getGeoTagData(...) {
        ...
    } */

    /* protected ... void printGeoTagData(...) {
        ...
    } */
    
    // Pre: This function save image in results folder under asserts
    // Return: return true if geotag is successfully updated. false otherwise
    // Output: a image without geotag. If the writing process failed, no change would happen
    public static boolean updateGeoTagData(File jpeg, double latitude, double longtitude) {
    	File resultsFolder = new File("./assets/results");
        if (!resultsFolder.exists()) resultsFolder.mkdir();
    	File result = new File("./assets/results/" + jpeg.getName());
    	return updateGeoTagData(jpeg, result, latitude, longtitude);
    }
    
    // Pre: This function save image in results folder under asserts
    // Return: return true if geotag is successfully removed. false otherwise
    // Output: a image without geotag. If the writing process failed, no change would happen
    public static boolean removeGeoTagData(File jpeg) {
    	File resultsFolder = new File("./assets/results");
        if (!resultsFolder.exists()) resultsFolder.mkdir();
    	File result = new File("./assets/results/" + jpeg.getName());
    	return removeGeoTagData(jpeg, result);
    }
    
    // Pre: this method will not fail if there is not geotag in image
    // Return: return true if geotag is successfully removed. false otherwise
    // Output: a image without geotag. If the writing process failed, no change would happen
    public static boolean removeGeoTagData(File jpeg, File result) {
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
            } else {
            	outputSet = new TiffOutputSet();
            	
            	//avoid empty exif data that cause null pointer error in output
            	outputSet.getOrCreateExifDirectory();
            }
    		
    		//Remove latitude and longtitude value.
    		Utility.displayProcessing("remove-geotag");
    		outputSet.removeField(LATITUDE_REFERENCE_TAG);
            outputSet.removeField(LATITUDE_TAG);
            outputSet.removeField(LONGTITUDE_REFERENCE_TAG);
            outputSet.removeField(LONGTITUDE_TAG);
            Utility.displaySuccess("remove-geotag");
            
            return saveJpegImage(jpeg, result, outputSet);           	
    	} catch (Exception exception) {
    		Utility.displayError("remove-geotag");
        	result.delete();
        	System.out.println(jpeg.getName() + ": " + exception.getMessage());
    		return false;
    	}
    }
    
    // Pre: latitude and longtitude should be passed as two double value
    // Return: return true if geotag is successfully written. false otherwise
    // Output: a image with new geotag written in. If the writing process failed, no change would happen
    public static boolean updateGeoTagData(File jpeg, File result, double latitude, double longtitude) {
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
            outputSet.setGPSInDegrees(longtitude, latitude);
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
                
            // write exif meradata into new jpeg/jpg file
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
