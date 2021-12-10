# Command Line Tool Instruction

## Command Type:

* -m     remove for remove geotag, update for update geotag, verify for verify whether file is a jpeg, and print to print geotag (required)
* -i     name of input file or folder in assets folder (required)
* -la    latitude as a String (required when you select to update geotag)
* -lo    longitude as a String (required when you select to update geotag)
* -help  print help menu

## Latitude and Longitude Format Supported by the Command Line Tool

Degree, Minute, Second: 
* Longitude: 100 30 40.88 E Latitude: 40 20 20.54 N
* Longitude: 100 30 40.88 Latitude: 40 20 20.54 (This means 100 30 40.88 E, 40 20 20.54 N)
* Longitude: -100 -30 -40.88 Latitude: -40 -20 -20.54 (This means 100 30 40.88 W, 40 20 20.54 S)

Degree, Minute:
* Longitude: 100 30.88 E Latitude: 40 20.66 N
* Longitude: 100 30.88 Latitude: 40 20.66 (This means 100 30.88 E, 40 20.66 N)
* Longitude: -100 -30.88 Latitude: -40 -20.66 (This means 100 30.88 W, 40 20.66 S)

Degree Only:
* Longitude: 100.87 E Latitude: 40.66 N
* Longitude: 100.87 Latitude: 40.66 (This means 100.87 E, 40.66 N)
* Longitude: -100.87 Latitude: -40.66 (This means 100.87 W, 40.66 S)
* Notice: -100.87 W is not allowed here. Please use either direction reference or negative value to represent W and S.

## Command Format:

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
* Process all files in assets
```
add . after -i (-i .)
```
* flag order does not matter here
```
They do the same thing:
-m tag -i <file path under assets>
-i <file path under assets> -m tag
```

## Example Usage:

* remove geotag in internet.jpg: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar tests/Tool -m remove -i internet.jpg
```
* remove geotag for all jpeg in folder source under assets: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar tests/Tool -m remove -i source
```
* remove geotag for all jpeg in assets: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar tests/Tool -m remove -i .
```
* update geotag in internet.jpg to 100 E 80 N: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar tests/Tool -m update -i internet.jpg -lo 100 -la 80
```
* update geotag for all jpeg in folder assets to 100 E 80 N: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar tests/Tool -m update -i . -lo 100 -la 80
```
* update geotag for all jpeg in folder source under assets to 100 E 80 N: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar tests/Tool -m update -i source -lo 100 -la 80
```
* print geotag stored in internet.jpg: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar tests/Tool -m print -i internet.jpg
```
* verify internet.jpg is a jpeg or not: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar tests/Tool -m verify -i internet.jpg
```
* print all tag in internet.jpg: 
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar tests/Tool -m tag -i Internet.jpg
```
## 2. JUnit Test Instruction

### Run All Test by Executing Shell Script allTest.sh
```
sh ./tests/allTest.sh
```
- There are also shell script available for an individual test. Please go to associate test folder and run it.
