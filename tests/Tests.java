package tests;

import src.GeoTagFunctions;

import java.util.*;
import java.io.*;

public class Tests {
    public static void main(String[] args) {

        // Testing isJpeg() method

        System.out.println("Testing isJpeg method:");
        
        File assetsFolder = new File("./assets");
        
        ArrayList<String> assetsFiles = new ArrayList<>();
        ArrayList<Boolean> expectedAnswers = new ArrayList<>();
        
        // for all the files in the assets folder of this project
        getAllFilesForFolder(assetsFolder, assetsFiles);
        addExpectedAnswers(expectedAnswers);
        
        Boolean result;
        for (int i = 0; i < assetsFiles.size(); i++) {
            String fileName = "./assets/" + assetsFiles.get(i);
            result = GeoTagFunctions.isJpeg(new File(fileName));
            
            if (result.equals(expectedAnswers.get(i))) {
                // tick mark
                System.out.println("\u2713");
            }
            else {
                // cross mark
                System.out.println("Test failed \u2717");
            }
        }
        System.out.println("Done testing isJpeg method");
        System.out.println("-------------------------------------------------------------------------");
    }
    
    // for the isJpeg method
    public static void getAllFilesForFolder(File folder, ArrayList<String> fileList) {
        for (File file : folder.listFiles()) {
            fileList.add(file.getName());
        }
    }
    
    // for the isJpeg method
    public static void addExpectedAnswers(ArrayList<Boolean> expectedAnswers) {
        expectedAnswers.add(true); expectedAnswers.add(true); expectedAnswers.add(true); expectedAnswers.add(true); expectedAnswers.add(true); expectedAnswers.add(false); expectedAnswers.add(true); expectedAnswers.add(true); expectedAnswers.add(true); expectedAnswers.add(true); expectedAnswers.add(true); expectedAnswers.add(false);
    }
}