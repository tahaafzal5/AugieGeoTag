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

        File output = new File("./assets/results/editted-internet.jpg");
        assertEquals(null, GeoTagFunctions.getGeoTagData(output));
        output = new File("./assets/results/editted-iPhone-6.jpg");
        String result = "Latitude: 40 degrees, 30 minutes, 35.999 seconds N, Longitude: 40 degrees, 30 minutes, 35.999 seconds E";
        assertEquals(result, GeoTagFunctions.getGeoTagData(output));
        output = new File("./assets/results/editted-No-geotag.jpg");
        assertEquals(result, GeoTagFunctions.getGeoTagData(output));

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
