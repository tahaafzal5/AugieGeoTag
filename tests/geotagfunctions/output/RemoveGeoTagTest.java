package tests.geotagfunctions.output;

import src.GeoTagFunctions;
import jpeg.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class RemoveGeoTagTest {
	
	private static JpegExif oldExif = null;
	private static JpegExif newExif = null;
	private static Entry[] oldIfd = null;
	private static Entry[] newIfd = null;
	
	public static void main(String[] args) throws IOException {
		File jpeg = new File("./assets/iPhone-6.jpg");	
		GeoTagFunctions.removeGeoTagData(jpeg);
		File edittedJpeg = new File("./assets/results/editted-iPhone-6.jpg");
		
		oldExif = new JpegExif(jpeg);
		newExif = new JpegExif(edittedJpeg);
		
		checkIfd0();
		checkSubIfd();
		checkIfd1();
		checkGps();
	}
	
	private static void checkGps() {
		oldIfd = oldExif.getGpsIfd();
		newIfd = newExif.getGpsIfd();
		
		LinkedList<Entry> difference = checkIfd();
		if(difference.isEmpty())
			System.out.println("\nGPS data is successfully copied.");
		else {
			System.out.println("\nItems not copied in GPS data:");
			for(Entry e: difference)
				System.out.println(e);				
		}
	}
	
	private static void checkIfd1() {
		oldIfd = oldExif.getIfd1();
		newIfd = newExif.getIfd1();
		
		LinkedList<Entry> difference = checkIfd();
		if(difference.isEmpty())
			System.out.println("\nIFD1 is successfully copied.");
		else {
			System.out.println("\nItems not copied in IFD1:");
			for(Entry e: difference)
				System.out.println(e);				
		}
	}
	
	private static void checkSubIfd() {
		oldIfd = oldExif.getSubIfd();
		newIfd = newExif.getSubIfd();
		
		LinkedList<Entry> difference = checkIfd();
		if(difference.isEmpty())
			System.out.println("\nsub-IFD is successfully copied.");
		else {
			System.out.println("\nItems not copied in sub-IFD:");
			for(Entry e: difference)
				System.out.println(e);				
		}
	}
	
	private static void checkIfd0() {
		oldIfd = oldExif.getIfd0();
		newIfd = newExif.getIfd0();
		
		LinkedList<Entry> difference = checkIfd();
		if(difference.isEmpty())
			System.out.println("IFD 0 is successfully copied.");
		else {
			System.out.println("Items not copied in IFD0:");
			for(Entry e: difference)
				System.out.println(e);				
		}
	}
	
	//check whether IFD is copied
	//Return: a LinkedList which contains different Entry
	private static LinkedList<Entry> checkIfd() {
		//set up result variable
		LinkedList<Entry> difference = new LinkedList<Entry>();
		for(Entry oldEntry : oldIfd) {
			boolean same = false;
			for(Entry newEntry : newIfd)
				if(oldEntry.equals(newEntry))
					same = true;
			if(!same)
				difference.add(oldEntry);
		}
		return difference;
	}
}
