package tests;

import java.io.*;

import src.GeoTagFunctions;

public class ReadImageMetadataTest {
    public static void main(String[] args) {
        File assetsFolder = new File("./assets");
        
        for (File jpeg : assetsFolder.listFiles())
            if (GeoTagFunctions.readImageMeta(jpeg))
                System.out.printf("%-30s %c \n", jpeg.getName(), '\u2713');
    }
}
