echo "------------"
java -cp .:./lib/hamcrest-core-1.3.jar:./lib/junit-4.13.2.jar tests.utilityTest.UtilityTestRunner
echo "Running Junit"
echo "-------------"
echo "difference between test and answer (empty means no difference):"
diff ./tests/utilityTest/UtilityTestAnswer.txt ./tests/utilityTest/UtilityTestResult.txt