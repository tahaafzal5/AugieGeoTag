java -cp .:./lib/hamcrest-core-1.3.jar:./lib/junit-4.13.2.jar tests.utility.output.UtilityOutputTestRunner
echo "difference between test and answer (empty means no difference):"
diff ./tests/utility/output/UtilityTestAnswer.txt ./tests/utility/output/UtilityTestResult.txt
echo "-------------"