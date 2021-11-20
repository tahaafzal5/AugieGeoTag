package tests;

import java.io.*;
import src.GeoTagFunctions;
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
	
	//Pre: this is a command line tool for jpeg processing
	//Input: user input a command string
	//Output: a folder that contains all result image or a result image depends on users choice.
	public static void main(String[] args) {
		if(args.length == 0) {
			System.err.println("No command received.");
			System.exit(0);
		}
		
		String mode = null;
		
		File jpeg = null;
		
		File assetsFolder = null;
		File resultsFolder = new File("./assets/results");
		if(!resultsFolder.exists())
			resultsFolder.mkdir();
		
		//null pointer is used here to determine whether 
		//latitude and longitude are valid
		Double latitude = 0.0;
		Double longitude = 0.0;
		
		//Analyse input string
		for(int position = 0; position < args.length; ) {
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
						System.err.println("Error on anaylyse file type");
						System.exit(0);
					}
					position += 2;
					break;
				case "-la":
					if (mode.equals("remove")) {
						System.err.println("Remove mode error: Error argument latitude received.");
						System.exit(0);
					}	
					String latitude_info = "";
					while(position + 1 < args.length && !isCommand(args[position + 1])) {
						latitude_info += args[position + 1] + " ";
						position += 1;
					}
					latitude = GeoTagFunctions.getLatitude(latitude_info.trim());
					if(latitude == null) {
						System.err.println("Error latitude format.");
						System.exit(0);
					}
					
					//move to next section 
					position += 1;
					break;
				case "-lo":
					if (mode.equals("remove")) {
						System.err.println("Remove mode error: Error argument longtitude received.");
						System.exit(0);
					}
					String longitude_info = "";
					while(position + 1 < args.length && !isCommand(args[position + 1])) {
						longitude_info += args[position + 1] + " ";
						position += 1;
					}
					longitude = GeoTagFunctions.getLongitude(longitude_info.trim());
					if(longitude == null) {
						System.err.println("Error longtitude format.");
						System.exit(0);
					}
					
					//move to next section 
					position += 1;
					break;
				case "-help":
					System.out.println("-m remove for remove geotag, update for update geotag");
					System.out.println("-i name of input file or folder in assets folder");
					System.out.println("-o output file name (only for single file processing)");
					System.out.println("-la latitude as a String");
					System.out.println("-lo longtitude as a String");
					System.out.println("remove geotag:");
					System.out.println("-m remove -i <file path under assets>");
					System.out.println("update geotag:");
					System.out.println("-m update -i <file path under assets> -la <latitude> -lo <longitude>");
					System.out.println("print geotag:");
					System.out.println("-m print -i <file path under assets>");
					System.exit(0);
				default:
					System.err.println("Error on command type " + args[position]);
					System.exit(0);
			}
		}
		
		if(mode != null) {
			
			//This means it is a single file processing
			if(jpeg != null) {
				//process mode information
				switch(mode)
				{
					case "remove": 
						if( GeoTagFunctions.getGPSInfo(jpeg) != null )
							GeoTagFunctions.removeGeoTagData(jpeg);
						else
							System.err.println("There is no geotag in the image");
						break;
					case "update":
						if(latitude == 0) {
							System.err.println("Latitude information missing");
							System.exit(0);
						} 
						if(longitude == 0) {
							System.err.println("Longtitude information missing");
							System.exit(0);
						}
						GeoTagFunctions.updateGeoTagData(jpeg, latitude, longitude);
						break;
					case "print":
						if(GeoTagFunctions.getGPSInfo(jpeg) != null)
							System.out.println(GeoTagFunctions.getGeoTagData(jpeg));
						else
							System.err.println("There is not geotag in jpeg");
						System.exit(0);
					case "verify":
						if(GeoTagFunctions.isJpeg(jpeg))
							System.out.println("This is a jpeg/jpg");
						else
							System.out.println("This is not a jpeg/jpg");
						System.exit(0);
					case "tag":
						JpegExif exif = new JpegExif(jpeg);
						exif.print();
						System.exit(0);
					default:
						System.err.println("Mode information error: should be \"update\", \"remove\", or \"print\"");
						break;
				}
			}
			//This means it is a folder processing
			else if (assetsFolder != null) {
				switch (mode)
				{
					case "remove":
						for (File f : assetsFolder.listFiles())
							GeoTagFunctions.removeGeoTagData(f);
						break;
					case "update":
						if(latitude == 0) {
							System.err.println("Latitude information missing");
							System.exit(0);
						} 
						if(longitude == 0) {
							System.err.println("Longtitude information missing");
							System.exit(0);
						}
						for (File f : assetsFolder.listFiles())
							GeoTagFunctions.updateGeoTagData(f, latitude, longitude);
						break;
					default:
						System.err.println("Mode information error: should be \"update\" or \"remove\"");
						break;
				}
			}
		}
		else
			System.err.println("Missing argument mode");
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
