# How to Compile the Program

The test tool use both commons imaging and my libraries. It is required to link them when compiling program.

## Compile and Run Script:

### How to Compile it:

```
javac -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar */*.java
```

### How to Use it:

#### Command Type:

* -m remove for remove geotag, update for update geotag, verify for verify whether file is a jpeg, and print to print geotag (required)
* -i name of input file or folder in assets folder (required)
* -la latitude as a String (required when you select to update geotag)
* -lo longtitude as a String (required when you select to update geotag)
* -help print help menu

#### Command Format:

* remove geotag command sample: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar -m remove -i <file path under assets>
```
* update geotag command sample: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar -m update -i <file path under assets> -la <latitude> -lo <longitude>
```
* print geotag command sample: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar -m print -i <file path under assets>
```
* verify jpeg command sample: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar -m verify -i <file path under assets>
```
* print all tag command sample: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar -m tag -i <file path under assets>
```
	

