package tests.utility.output;

import src.Utility;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.Scanner;

import org.junit.Test;

public class UtilityOutputTest {
	
	@Test
	public void UtilityOutputTests() throws FileNotFoundException{
		
        Utility.displayMenu();
        
        Utility.displayHelpMenu();
		
		testAskConfirmation();
		
		testDisplayError();
		
		testDisplaySuccess();
		
		testDisplayProcessing();
		
		Utility.aboutProgram();
		
		File printContent = new File("./tests/utility/output/print.txt");
        File answerContent = new File("./tests/utility/output/answer.txt");
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
	
	//test askConfirmation
	private void testAskConfirmation() {
		Utility.askConfirmation("find-file");
		Utility.askConfirmation("open-file");
		Utility.askConfirmation("read-geotag");
		Utility.askConfirmation("remove-geotag");
		Utility.askConfirmation("write-geotag");
		Utility.askConfirmation("save-image");
		Utility.askConfirmation("default");
	}
	
	//test displayError
	private void testDisplayError() {
		Utility.displayError("find-file");
		Utility.displayError("open-file");
		Utility.displayError("no-file");
		Utility.displayError("check-jpeg");
		Utility.displayError("check-multiple");
		Utility.displayError("read-metadata");
		Utility.displayError("remove-geotag");
		Utility.displayError("no-geotag");
		Utility.displayError("update-geotag");
		Utility.displayError("add-geotag");
		Utility.displayError("save-image");
		Utility.displayError("get-GPS");
		Utility.displayError("default");
	}
	
	//test displaySuccess
	private void testDisplaySuccess() {
		Utility.displaySuccess("find-file");
		Utility.displaySuccess("open-file");
		Utility.displaySuccess("read-metadata");
		Utility.displaySuccess("check-jpeg");
		Utility.displaySuccess("read-geotag");
		Utility.displaySuccess("remove-geotag");
		Utility.displaySuccess("write-geotag");
		Utility.displaySuccess("save-image");
		Utility.displaySuccess("get-GPS");
		Utility.displaySuccess("default");
	}
	
	private void testDisplayProcessing() {
		Utility.displayProcessing("find-file");
		Utility.displayProcessing("open-file");
		Utility.displayProcessing("read-metadata");
		Utility.displayProcessing("check-jpeg");
		Utility.displayProcessing("read-geotag");
		Utility.displayProcessing("remove-geotag");
		Utility.displayProcessing("write-metadata");
		Utility.displayProcessing("save-image");
		Utility.displayProcessing("get-GPS");
		Utility.displayProcessing("default");
	}
}
