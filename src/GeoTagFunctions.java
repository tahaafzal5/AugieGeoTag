package src;

import java.io.*;
import java.util.*;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;

public class GeoTagFunctions {
    
	private static TiffImageMetadata exif = null;

    public static boolean openImage() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the file name (including the extension): ");
        String fileName = input.nextLine().strip().toLowerCase();
        
        try {
            File inputFile = new File("./assets/" + fileName);
            
            if (inputFile.exists()) {
                Utility.displayProcessing("open-image");
                Utility.displaySuccess("open-image");
                
                return true;
            }
            else {
                System.out.println("Image not found"); // To be replaced by Utility.displayError("open-image");
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            input.close();
        }
        return false;
    }
	
  	//Return: true if passed file is a JPEG/JPG, false otherwise.
    //Output: Error message if the file reading process contains error.
  	public static boolean isJpeg(File file) {    
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

            // read the marker
            byte[] fileMarker = new byte[2];
            bufferedInputStream.read(fileMarker);
                
            bufferedInputStream.close();
                
            // check the file type
            if ((fileMarker[0] & 0xFF) == 0xFF && (fileMarker[1] & 0xFF) == 0xD8)
                return true;
            else
                return false;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());

            return false;
        }
  	}

    //Pre: file passed in as argument should be JPEG/JPG
    //Return: true if metadata read successfully, false otherwise
    //Output: error message if program throw Exception
    //Reminder: exif might be a null. It is important to check it because there might be no metadata.
    public static boolean readImageMeta(File jpeg) {
        try {
            //Get Metadata
            final ImageMetadata metadata = Imaging.getMetadata(jpeg);
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            
            if (jpegMetadata != null)
                exif = jpegMetadata.getExif();

            return true;
        } 
        catch (Exception exception) {
            System.out.println(jpeg.getName() + ": " + exception.getMessage());
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

    /* protected ... ... removeGeoTagData(...) {
        ...
    } */
    
    //Pre: latitude and longtitude should be passed as two double value.
    //Return: return true if geotag is successfully written. false otherwise.
    //Output: a image with new geotag written in. If the wrriten process failed, no change would happen
    public static boolean updateGeoTagData(File jpeg, File result, double latitude, double longtitude) {
        try {
            TiffOutputSet outputSet = null;
            
            // if file does not contain any exif metadata, we create an empty
            // set of exif metadata. Otherwise, we keep all of the other existing tags.
            if (exif != null) {
                //get a copy of exif data to be muted.
                outputSet = exif.getOutputSet();
            } else {
            	outputSet = new TiffOutputSet();
            }
            
            outputSet.setGPSInDegrees(longtitude, latitude);
            
            return saveJpegImage(jpeg, result, outputSet);           	
        }
        catch (Exception exception) {
        	result.delete();
        	System.out.println(jpeg.getName() + ": " + exception.getMessage());
        	return false;
        }
    }
    
    //throw: ImageReadException and ImageWriteException would be thrown if ExifWriter 
    //		 has problem on reading and writing image. IOException would be thrown depends
    //		 on buffered output stream
    //Output: a jpeg file with new exif in outputSet is written.
    private static boolean saveJpegImage(File jpeg, File result, TiffOutputSet outputSet) 
    		throws ImageReadException, ImageWriteException, IOException {
    	
    	//Set up output stream
    	FileOutputStream fos = new FileOutputStream(result);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fos);
            
        //write exif meradata into new jpeg/jpg file.
        new ExifRewriter().updateExifMetadataLossless(jpeg, bufferedOutputStream, outputSet);
             
        return true;
    }
}
