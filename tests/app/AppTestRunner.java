package tests.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AppTestRunner {
	public static void main(String[] args) throws FileNotFoundException
	{
        PrintStream console = System.out;
		PrintStream fileOutput = new PrintStream(new File("./tests/app/answer.txt"));
		System.setOut(fileOutput);
		System.setErr(fileOutput);
        Result result = JUnitCore.runClasses(AppTest.class);

		fileOutput.close();

		for (Failure failure : result.getFailures())
			console.println(failure.getTrimmedTrace());

		console.println("App output test result: " + result.wasSuccessful());

		console.close();
	}
}