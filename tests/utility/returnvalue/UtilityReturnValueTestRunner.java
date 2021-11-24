package tests.utility.returnvalue;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.*;

public class UtilityReturnValueTestRunner {

	public static void main(String[] args) throws FileNotFoundException
	{
		PrintStream console = System.out;
		PrintStream fileOutput = new PrintStream(new File("./tests/utility/returnvalue/print.txt"));
		System.setOut(fileOutput);
		System.setErr(fileOutput);
		Result result = JUnitCore.runClasses(UtilityReturnValueTest.class);
		
		for (Failure failure : result.getFailures())
		    console.println(failure.toString());
		
		console.println("Utility return value test result: " + result.wasSuccessful());
		console.close();
	}
}
