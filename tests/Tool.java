package tests;

import java.io.*;
import src.GeoTagFunctions;
import jpeg.Jpeg;
import jpeg.JpegExif;

/*
	This is the test command line tool for write geotag and remove geotag.
	
	command type:
	-m remove for remove geotag, update for update geotag, 
	   verify for verify whether file is a jpeg, and print to print geotag (required)
	-i name of input file or folder in assets folder (required)
	-la latitude as a String (required when you select to update geotag)
	-lo longtitude as a String (required when you select to update geotag)
	-help print help menu
	
	**input flag order does not matter**

	remove geotag command sample:
	-m remove -i <file path under assets>
	update geotag command sample:
	-m update -i <file path under assets> -la <latitude> -lo <longitude>
	print geotag command sample:
	-m print -i <file path under assets>
	verify jpeg command sample:
	-m verify -i <file path under assets>
	print all tag command sample:
	-m tag -i <file path under assets>
*/
public class Tool {
	
	private static String mode = null;
		
	private static File jpeg = null;
	
	private static File assetsFolder = null;
	private static File resultsFolder = new File("./assets/results");
	
	/*
	null pointer is used here to determine whether 
	latitude and longitude are valid
	*/
	private static Double latitude = 0.0;
	private static Double longitude = 0.0;
	
	private static PrintStream console = System.out;
	private static PrintStream disable = new PrintStream(new OutputStream() {
			
		@Override 
        public void write(int b) { } 
			
	});

	//Pre: this is a command line tool for jpeg processing
	//Input: user input a command string
	//Output: a folder that contains all result image or a result image depends on users choice.
	public static void main(String[] args) {
		//disable print stream from GeoTagFunctions
		System.setOut(disable);
		
		if(args.length == 0) {
			console.println("No command received.");
			System.exit(0);
		}

		if(!resultsFolder.exists())
			resultsFolder.mkdir();
		
		analyseInput(args);
		
		if(mode != null) {	
			//This means it is a single file processing
			if(jpeg != null) {
				processFile();
			}
			//This means it is a folder processing
			else if (assetsFolder != null) {
				processFolder();
			} else
				console.println("Error on deciding process mode");
		}
		else
			console.println("Missing argument mode");
	}

	//Output: all images under user input folder will be processed and output in results folder
	public static void processFolder()
	{
		switch (mode)
		{
			case "remove":
				for (File f : assetsFolder.listFiles())
					if(GeoTagFunctions.removeGeoTagData(f))
						console.printf("Geotag in %s has been removed \n", f.getName());
					else
						console.printf("Failed to remove geotag in %s \n", f.getName());
				break;
			case "update":
				if(latitude == 0) {
					console.println("Latitude information missing");
					break;
				} 
				if(longitude == 0) {
					console.println("Longtitude information missing");
					break;
				}
				for (File f : assetsFolder.listFiles())
					if(GeoTagFunctions.updateGeoTagData(f, latitude, longitude))
						console.printf("Successfully update %s \n", f.getName());
					else
						console.printf("Failed to update geotag in %s \n", f.getName());
				break;
			default:
				console.println("Mode information error: should be \"update\" or \"remove\"");
				break;
			}
	}

	//Output: information print on the sreen based on user choice
	//		  image output based on user choice
	public static void processFile()
	{
		//process mode information
		switch(mode)
		{
			case "remove": 
				if( GeoTagFunctions.getGPSInfo(jpeg) != null ) {
					if (GeoTagFunctions.removeGeoTagData(jpeg))
						console.printf("Geotag in %s has been removed \n", jpeg.getName());
					else
						console.printf("Failed to remove geotag in %s \n", jpeg.getName());
				}
				else
					console.println("There is no geotag in the image");
				break;
			case "update":
				if(latitude == 0) {
					console.println("Latitude information missing");
					break;
				} 
				if(longitude == 0) {
					console.println("Longtitude information missing");
					break;
				}
				if (GeoTagFunctions.updateGeoTagData(jpeg, latitude, longitude))
					console.printf("Geotag in %s has updated to: \n%s \n", jpeg.getName(), GeoTagFunctions.getGeoTagData(jpeg));
				else 
					console.printf("Failed to update geotag in %s \n", jpeg.getName());
				break;
			case "print":
				if(GeoTagFunctions.getGPSInfo(jpeg) != null)
					console.println(GeoTagFunctions.getGeoTagData(jpeg));
				else
					console.println("There is not geotag in jpeg");
				break;
			case "verify":
				if(GeoTagFunctions.isJpeg(jpeg))
					console.println("This is a jpeg/jpg");
				else
					console.println("This is not a jpeg/jpg");
				break;
			case "tag":
				try {
					Jpeg jpegFile = new Jpeg(jpeg);
					System.setOut(console);
					jpegFile.exif.print();
				} catch (Exception e) {
					console.println(e.getMessage());
				}
				break;
			default:
				console.println("Mode information error: should be \"update\", \"remove\", or \"print\"");
				break;
		}
	}

	//Post: Analyse input and set associate value to global variables
	public static void analyseInput(String[] args)
	{
		//Analyse input string
		for(int position = 0; position < args.length; ) {
			if(position + 1 >= args.length) {
				console.println("Missing argument after flag " + args[position]);
				System.exit(0);
			}
			switch(args[position])
			{
				case "-m":
					mode = args[position + 1]; 
					position += 2;
					break;
				case "-i":
					File temp = new File("./assets/" +args[position + 1]);
					if(temp.isFile())
						jpeg = temp;
					else if(temp.isDirectory())
						assetsFolder = temp;
					else {
						console.println("Error on anaylyse file type");
						System.exit(0);
					}
					position += 2;
					break;
				case "-la":
					if (mode.equals("remove")) {
						console.println("Remove mode error: Error argument latitude received.");
						System.exit(0);
					}	
					String latitude_info = "";
					while(position + 1 < args.length && !isCommand(args[position + 1])) {
						latitude_info += args[position + 1] + " ";
						position += 1;
					}
					latitude = GeoTagFunctions.getLatitude(latitude_info.trim());
					if(latitude == null) {
						console.println("Error latitude format.");
						System.exit(0);
					}
					
					//move to next section 
					position += 1;
					break;
				case "-lo":
					if (mode.equals("remove")) {
						console.println("Remove mode error: Error argument longtitude received.");
						System.exit(0);
					}
					String longitude_info = "";
					while(position + 1 < args.length && !isCommand(args[position + 1])) {
						longitude_info += args[position + 1] + " ";
						position += 1;
					}
					longitude = GeoTagFunctions.getLongitude(longitude_info.trim());
					if(longitude == null) {
						console.println("Error longtitude format.");
						System.exit(0);
					}
					
					//move to next section 
					position += 1;
					break;
				case "-help":
					printHelp();
					System.exit(0);
				default:
					console.println("Error on command type " + args[position]);
					System.exit(0);
			}
		}
	}

	//Output: help menu printed on the screen
	public static void printHelp(){
		console.println("-m remove for remove geotag, update for update geotag");
		console.println("-i name of input file or folder in assets folder");
		console.println("-o output file name (only for single file processing)");
		console.println("-la latitude as a String");
		console.println("-lo longtitude as a String");
		console.println("remove geotag:");
		console.println("-m remove -i <file path under assets>");
		console.println("update geotag:");
		console.println("-m update -i <file path under assets> -la <latitude> -lo <longitude>");
		console.println("print geotag:");
		console.println("-m print -i <file path under assets>");
		console.println("verify jpeg command sample:");
		console.println("-m verify -i <file path under assets>");
		console.println("print all tag command sample:");
		console.println("-m tag -i <file path under assets>");
	}

	public static boolean isCommand(String cmd){
		switch(cmd)
		{
			case "-m": case "-i": case "-o":
			case "-la": case "-lo": case"-help":
				return true;
			default:
				return false;
		}
	}
}
