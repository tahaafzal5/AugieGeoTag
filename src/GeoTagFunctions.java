package src;

import java.io.*;

public class GeoTagFunctions {
    
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

    /* protected ... ... readImageMeta(...) {
        ...
    } */

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
