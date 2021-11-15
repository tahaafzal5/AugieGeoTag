package tests;

import java.io.File;

import src.GeoTagFunctions;

public class WriteGeotagTest {

	public static void main(String[] args) {
		
		File assetsFolder = new File("./assets");
        File resultsFolder = new File("./results");
        resultsFolder.mkdir();
		
        //100 20 40.77 N
        //120 40 20.89 E
        double latitude = 100.0 + 30.0 / 60 + 40.77 / 3600;
        double longitude = 120.0 + 40.0 / 60 + 20.89 / 3600;
        
        for (File jpeg : assetsFolder.listFiles()) {
            File result = new File("./results/NE_" + jpeg.getName());
        	
        	if ( GeoTagFunctions.updateGeoTagData(jpeg, result, latitude, longitude) )
        		System.out.printf("%-30s %c \n", jpeg.getName(), '\u2713');
        	
        	result = new File("./results/SW_" + jpeg.getName());
        	
        	if ( GeoTagFunctions.updateGeoTagData(jpeg, result, -latitude, -longitude) )
        		System.out.printf("%-30s %c \n", jpeg.getName(), '\u2713');
        }
	}

}
