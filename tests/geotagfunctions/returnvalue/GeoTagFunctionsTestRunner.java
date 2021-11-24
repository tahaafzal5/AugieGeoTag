package tests.geotagfunctions.returnvalue;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class GeoTagFunctionsTestRunner {

	public static void main(String[] args)
	{
		Result result = JUnitCore.runClasses(GeoTagFunctionsTest.class);
				
		for (Failure failure : result.getFailures())
		    System.out.println(failure.toString());
		
		System.out.println("-----------");
		System.out.println("GeotagFunctions return value test result: " + result.wasSuccessful());
	}
}
