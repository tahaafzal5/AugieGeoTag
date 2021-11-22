# How to Compile the Program

The test tool use both commons imaging and my libraries. It is required to link them when compiling program.

## Compile and Run Script:

### How to compile it:

```
javac -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar */*.java
```
### How to use it:
* command type:
	-m remove for remove geotag, update for update geotag, 
	   verify for verify whether file is a jpeg, and print to print geotag (required)
	-i name of input file or folder in assets folder (required)
	-la latitude as a String (required when you select to update geotag)
	-lo longtitude as a String (required when you select to update geotag)
	-help print help menu
