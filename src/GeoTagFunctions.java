package src;

import java.io.*;

import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;

public class GeoTagFunctions {
    
	private static TiffImageMetadata exif;
	
  	//Return: true if passed file is a JPEG/JPG, false otherwise.
    //Output: Error message if the file reading process contains error.
  	public static boolean isJpeg(File file) {    
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

            //read the marker
            byte[] fileMarker = new byte[2];
            bufferedInputStream.read(fileMarker);
                
            bufferedInputStream.close();
                
            //check the file type
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

    //Pre: it will return true even if metadata does not exist.
    //Return: true if metadata read successfully
    //Output: error message if program throw Exception
    public static boolean readImageMeta(File jpeg) {
        try{
            //Get Metadata
            final ImageMetadata metadata = Imaging.getMetadata(jpeg);
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            
            if ( jpegMetadata != null )
                exif = jpegMetadata.getExif();

            return true;
        } catch(Exception exception) {
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

    /* protected ... boolean updateGeoTagData(...) {
        ...
    } */

    /* protected ... ... writeGeoTagData(...) {
        ...
    } */

    /* protected ... boolean removeGeoTagData(...) {
        ...
    } */

    /* protected ... boolean saveJpegImage(...) {
        ...
    } */
}
