package tests.utility;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class UtilityTestRunner {

	public static void main(String[] args)
	{
		Result result = JUnitCore.runClasses(UtilityTest.class);
		
		System.setOut(System.out);
		
		for (Failure failure : result.getFailures())
		    System.out.println(failure.toString());
	}
}
