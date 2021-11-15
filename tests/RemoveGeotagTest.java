package tests;

import java.io.File;

import src.GeoTagFunctions;

public class RemoveGeotagTest {

	public static void main(String[] args) {
		
		File assetsFolder = new File("./assets");
        File resultsFolder = new File("./results");
        resultsFolder.mkdir();
        	
        for (File jpeg : assetsFolder.listFiles()) {
        	File result = new File("./results/" + jpeg.getName());
        	
        	if ( GeoTagFunctions.removeGeoTagData(jpeg, result) )
        		System.out.printf("%-30s %c \n", jpeg.getName(), '\u2713');
        }
	}

}
