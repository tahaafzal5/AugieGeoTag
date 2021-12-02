package tests.utility.returnvalue;

import java.util.*;
import java.io.*;
import org.junit.Test;

import src.Utility;

import static org.junit.Assert.assertEquals;

public class UtilityReturnValueTest {
	@Test
	public void UtilityReturnValueTests() throws FileNotFoundException {
		Scanner input = new Scanner(new File("./tests/utility/returnvalue/input.txt"));
		
		testHandleUserChoice(input);
		
		testHandleUserConfirmation(input);
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
	
	//test handle user confirmation
	private void testHandleUserConfirmation(Scanner input) {
		boolean yes = Utility.handleUserConfirmationChoice(input);
		assertEquals(yes, true);
		boolean not = Utility.handleUserConfirmationChoice(input);
		assertEquals(not, false);
	}
}
