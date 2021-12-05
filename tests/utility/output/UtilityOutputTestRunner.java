package tests.utility.output;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.*;

public class UtilityOutputTestRunner {

	public static void main(String[] args) throws FileNotFoundException
	{
		PrintStream console = System.out;
		PrintStream fileOutput = new PrintStream(new File("./tests/utility/output/print.txt"));
		System.setOut(fileOutput);
		System.setErr(fileOutput);
		Result result = JUnitCore.runClasses(UtilityOutputTest.class);

		for (Failure failure : result.getFailures())
		    console.println(failure.toString());
		
		console.println("Utility output test result: " + result.wasSuccessful());
		console.close();
	}
}
