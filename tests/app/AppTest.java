package tests.app;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

import src.GeoTagFunctions;
import src.App;

public class AppTest {
   
    private static PrintStream disable = new PrintStream(new OutputStream() {
			
		@Override 
        public void write(int b) { } 
			
	});
    
    @Test
    public void AppTest() throws FileNotFoundException
    {
        FileInputStream fis = new FileInputStream("./tests/app/input.txt"); 
        System.setIn(fis);
        
        App.main(null);

        System.setOut(disable);
        System.setErr(disable);

        File output = new File("./assets/results/edited-internet.jpg");
        assertEquals(null, GeoTagFunctions.getGeoTagData(output));
        output.delete();

        output = new File("./assets/results/edited-iPhone-6.jpg");
        String result = "Latitude & Longitude: 40° 30' 35.999\" N 40° 30' 35.999\" E";
        assertEquals(result, GeoTagFunctions.getGeoTagData(output));
        output.delete();

        output = new File("./assets/results/edited-No-geotag.jpeg");
        assertEquals(result, GeoTagFunctions.getGeoTagData(output));
        output.delete();

        File printContent = new File("./tests/app/print.txt");
        File answerContent = new File("./tests/app/answer.txt");
        Scanner printScanner = new Scanner(printContent);
        Scanner answerScanner = new Scanner(answerContent);

        while(printScanner.hasNextLine()){
            assertEquals(true, answerScanner.hasNextLine() );
            String print = printScanner.nextLine();
            String answer = answerScanner.nextLine();
            assertEquals(answer, print);
        }

        printScanner.close();
        answerScanner.close();
    }
}
