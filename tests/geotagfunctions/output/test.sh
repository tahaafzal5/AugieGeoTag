echo "Update geotag in iPhone-6.jpg:"
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar tests/geotagfunctions/output/UpdateGeoTagTest
echo "-------------"
echo "Remove geotag in iPhone-6.jpg:"
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar tests/geotagfunctions/output/RemoveGeoTagTest
echo "-------------"