package tests.geotagfunctions.returnvalue;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.*;

public class GeoTagFunctionsTestRunner {

	public static void main(String[] args) throws FileNotFoundException
	{
		PrintStream console = System.out;
		PrintStream fileOutput = new PrintStream(new File("./tests/geotagfunctions/returnvalue/print.txt"));
		System.setOut(fileOutput);
		System.setErr(fileOutput);
		Result result = JUnitCore.runClasses(GeoTagFunctionsTest.class);
		
		for (Failure failure : result.getFailures())
		    console.println(failure.toString());
		
		console.println("GeotagFunctions return value test result: " + result.wasSuccessful());
		console.close();
	}
}
