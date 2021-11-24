package tests.utilityTest;

import src.Utility;
import java.io.*;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UtilityTest {
	
	@Test
	public void UtilityTest() throws FileNotFoundException {
		
		// output content to a file
        PrintStream ps = new PrintStream(new File("./tests/utilityTest/UtilityTestResult.txt"));
        System.setOut(ps);
        System.setErr(ps);
        Scanner input = new Scanner(new File("./tests/utilityTest/input.txt"));
		
        Utility.displayMenu();
        
        Utility.displayHelpMenu();
        
        testHandleUserChoice(input);
		
		testAskConfirmation();
		
		testHandleUserConfirmation(input);
		
		testDisplayError();
		
		testDisplaySuccess();
		
		testDisplayProcessing();
		
		Utility.aboutProgram();
		
		Utility.exitProgram();
	}
	
	//test handleUserChoice
	private void testHandleUserChoice(Scanner input) {
		//test 1-7
        int choice = Utility.handleUserMenuChoice(input);
        assertEquals(choice, 1);
        choice = Utility.handleUserMenuChoice(input);
        assertEquals(choice, 2);
        choice = Utility.handleUserMenuChoice(input);
        assertEquals(choice, 3);
        choice = Utility.handleUserMenuChoice(input);
        assertEquals(choice, 4);
        choice = Utility.handleUserMenuChoice(input);
        assertEquals(choice, 5);
        choice = Utility.handleUserMenuChoice(input);
        assertEquals(choice, 6);
        choice = Utility.handleUserMenuChoice(input);
        assertEquals(choice, 7);
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
	
	//test handle user confirmation
	private void testHandleUserConfirmation(Scanner input) {
		boolean yes = Utility.handleUserConfirmationChoice(input);
		assertEquals(yes, true);
		boolean not = Utility.handleUserConfirmationChoice(input);
		assertEquals(not, false);
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
