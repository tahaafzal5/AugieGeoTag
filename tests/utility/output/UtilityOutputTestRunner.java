package tests.utility.output;

import java.io.*;
import org.junit.runner.JUnitCore;

public class UtilityOutputTestRunner {

	public static void main(String[] args) throws FileNotFoundException
	{
		PrintStream fileOutput = new PrintStream(new File("./tests/utility/output/print.txt"));
		System.setOut(fileOutput);
		System.setErr(fileOutput);
		JUnitCore.runClasses(UtilityOutputTest.class);
	}
}
