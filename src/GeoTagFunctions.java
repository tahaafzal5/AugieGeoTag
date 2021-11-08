import java.io.*;

public class GeoTagFunctions {
    
    //Throw: FileNotFoundException if the file does not exist. IOException if the program cannot read the file
  	//Return: true if passed file is a JPEG/JPG; false other wise.
  	public static boolean isJpeg(File f) throws IOException, FileNotFoundException
  	{    
  		if(!f.exists()) throw new FileNotFoundException("File does not exists");
        BufferedInputStream buff = new BufferedInputStream(new FileInputStream(f));
              
        //read the marker to make sure it is a jpeg
        byte[] file_marker = new byte[2];
        buff.read(file_marker);
              
        buff.close();
              
        //check the file type and exif validation
        if( (file_marker[0] & 0xFF) == 0xFF && (file_marker[1] & 0xFF) == 0xD8 )
        	return true;
        else return false;
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
