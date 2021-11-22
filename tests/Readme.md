# Command Line Tool Instruction

The command line tool use both commons imaging library and library from Sicheng. It is required to link them when compiling program.

## How to Compile it:

```
javac -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar */*.java
```

## How to Use it:

### Command Type:

* -m     remove for remove geotag, update for update geotag, verify for verify whether file is a jpeg, and print to print geotag (required)
* -i     name of input file or folder in assets folder (required)
* -la    latitude as a String (required when you select to update geotag)
* -lo    longtitude as a String (required when you select to update geotag)
* -help  print help menu

### Command Format:

* remove geotag command format: 
```
-m remove -i <file/folder path under assets>
```
* update geotag command format: 
```
-m update -i <file/folder path under assets> -la <latitude> -lo <longitude>
```
* print geotag command format: 
```
-m print -i <file path under assets>
```
* verify jpeg command format: 
```
-m verify -i <file path under assets>
```
* print all tag command format: 
```
-m tag -i <file path under assets>
```

### Example Usage:

* remove geotag in internet.jpg: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar tests/Tool -m remove -i internet.jpg
```
* remove geotag in folder source: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar tests/Tool -m remove -i source
```
* update geotag in internet.jpg: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar tests/Tool -m update -i internet.jpg -lo 100 -la 80
```
* update geotag in folder source: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar tests/Tool -m update -i source -lo 100 -la 80
```
* print geotag stored in internet.jpg: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar tests/Tool -m print -i internet.jpg
```
* verify internet.jpg is a jpeg or not: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar tests/Tool -m verify -i internet.jpg
```
* print all tag in internet.jpg: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar tests/Tool -m tag -i Internet.jpg
```
