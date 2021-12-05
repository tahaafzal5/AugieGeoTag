package tests.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class OutputSetTestRunner {
	public static void main(String[] args) throws FileNotFoundException
	{
		PrintStream console = System.out;
		PrintStream fileOutput = new PrintStream(new File("./tests/output/print.txt"));
		System.setOut(fileOutput);
		System.setErr(fileOutput);
		Result result = JUnitCore.runClasses(OutputSetTest.class);

		fileOutput.close();

		for (Failure failure : result.getFailures())
			console.println(failure.getTrimmedTrace());

		console.println("Library output test result: " + result.wasSuccessful());

		console.close();
	}
}