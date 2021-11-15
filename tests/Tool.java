package tests;

import java.io.File;
import src.GeoTagFunctions;

public class Tool {
	
	//Pre: this is a command line tool for jpeg processing
	//Input: user input a command string
	//Output: a folder that contains all result image or a result image depends on users choice.
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("No command received.");
			System.exit(0);
		}
		
		String mode = null;
		
		File jpeg = null;
		File result = null;
		
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
						System.out.println("Error on anaylyse file type");
						System.exit(0);
					}
					position += 2;
					break;
				case "-o":
					result = new File("./assets/results/" + args[position + 1]);
					position += 2;
					break;
				case "-la":
					if (mode.equals("remove")) {
						System.out.println("Remove mode error: Error argument latitude received.");
						System.exit(0);
					}	
					String latitude_info = "";
					while(position + 1 < args.length && args[position + 1].charAt(0) != '-') {
						latitude_info += args[position + 1] + " ";
						position += 1;
					}
					latitude = GeoTagFunctions.getCoordinate(latitude_info.trim());
					if(latitude == null) {
						System.out.println("Error latitude format.");
						System.exit(0);
					}
					
					//move to next section 
					position += 1;
					break;
				case "-lo":
					if (mode.equals("remove")) {
						System.out.println("Remove mode error: Error argument longtitude received.");
						System.exit(0);
					}
					String longitude_info = "";
					while(position + 1 < args.length && args[position + 1].charAt(0) != '-') {
						longitude_info += args[position + 1] + " ";
						position += 1;
					}
					longitude = GeoTagFunctions.getCoordinate(longitude_info.trim());
					if(longitude == null) {
						System.out.println("Error longtitude format.");
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
					System.exit(0);
				default:
					System.out.println("Error on command type " + args[position]);
					System.exit(0);
			}
		}
		
		if(mode != null) {
			
			//This means it is a single file processing
			if(jpeg != null) {
				switch(mode)
				{
					case "remove": 
						//output in default way
						if(result == null)
							GeoTagFunctions.removeGeoTagData(jpeg);
						//Output in user defined way
						else
							GeoTagFunctions.removeGeoTagData(jpeg, result);
						break;
					case "update":
						if(latitude == 0) {
							System.out.println("Latitude information missing");
							System.exit(0);
						} 
						if(longitude == 0) {
							System.out.println("Longtitude information missing");
							System.exit(0);
						}
						//output in default way
						if(result == null)
							GeoTagFunctions.updateGeoTagData(jpeg, latitude, longitude);
						//Output in user defined way
						else
							GeoTagFunctions.updateGeoTagData(jpeg, result, latitude, longitude);
						break;
					default:
						System.out.println("Mode information error: should be \"update\" or \"remove\"");
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
							System.out.println("Latitude information missing");
							System.exit(0);
						} 
						if(longitude == 0) {
							System.out.println("Longtitude information missing");
							System.exit(0);
						}
						for (File f : assetsFolder.listFiles())
							GeoTagFunctions.updateGeoTagData(f, latitude, longitude);
						break;
					default:
						System.out.println("Mode information error: should be \"update\" or \"remove\"");
						break;
				}
			}
		}
		else
			System.out.println("Missing argument mode");
	}

}
