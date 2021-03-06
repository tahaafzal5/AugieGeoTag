javac -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/hamcrest-core-1.3.jar:./lib/junit-4.13.2.jar */*.java */*/*.java */*/*/*.java
echo "Utility output test:"
sh ./tests/utility/output/test.sh
echo "Utility return value test:"
sh ./tests/utility/returnvalue/test.sh
echo "GeoTagFunctions test:"
sh ./tests/geotagfunctions/test.sh
echo "Library output test:"
sh ./tests/output/test.sh
echo "App test:"
sh ./tests/app/test.sh
