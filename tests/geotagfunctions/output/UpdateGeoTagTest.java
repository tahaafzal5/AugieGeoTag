package tests.geotagfunctions.output;

import src.GeoTagFunctions;
import jpeg.*;
import java.io.*;
import java.util.LinkedList;

public class UpdateGeoTagTest {
	
	private static JpegExif oldExif = null;
	private static JpegExif newExif = null;
	private static Entry[] oldIfd = null;
	private static Entry[] newIfd = null;
	private static PrintStream console = System.out;
	private static LinkedList<Entry> diffInOld = null;
	private static LinkedList<Entry> diffInNew = null;
	
	public static void main(String[] args) throws IOException {
		File jpeg = new File("./assets/iPhone-6.jpg");
		PrintStream fileOutput = new PrintStream(new File("./tests/geotagfunctions/output/print.txt"));
		System.setOut(fileOutput);
		System.setErr(fileOutput);
		final double latitude = -(60 + 30 / 60.0 + 40.88 / 3600.0);
		final double longitude = -(100 + 40 / 60.0 + 50.87 / 3600.0);
		GeoTagFunctions.updateGeoTagData(jpeg, latitude, longitude);
		File edittedJpeg = new File("./assets/results/editted-iPhone-6.jpg");
		
		oldExif = new JpegExif(jpeg);
		newExif = new JpegExif(edittedJpeg);
		
		checkIfd0();
		checkSubIfd();
		checkIfd1();
		checkGps();
	}
	
	//Output: different items in GPS IFD. GPS IFD is equal is printed if old jpeg and
	//new jpeg has same  GPS IFD
	private static void checkGps() {
		oldIfd = oldExif.getGpsIfd();
		newIfd = newExif.getGpsIfd();
		
		checkIfd();
		if(diffInOld.isEmpty() && diffInNew.isEmpty())
			console.println("\nGPS IFD is equal.");
		else {
			//print out the difference
			console.println("\nDifferent items in GPS IFD:");
			console.println("In old jpeg:");
			for(Entry e: diffInOld)
				console.println(e);
			console.println("In new jpeg:");
			for(Entry e: diffInNew)
				console.println(e);
		}
	}
	
	//Output: different items in IFD 1. IFD 1 is equal is printed if old jpeg and
	//new jpeg has same IFD 1
	private static void checkIfd1() {
		oldIfd = oldExif.getIfd1();
		newIfd = newExif.getIfd1();
		
		checkIfd();
		if(diffInOld.isEmpty() && diffInNew.isEmpty())
			console.println("\nIFD 1 is equal.");
		else {
			//print out the difference
			console.println("\nDifferent items in IFD 1:");
			console.println("In old jpeg:");
			for(Entry e: diffInOld)
				console.println(e);
			console.println("In new jpeg:");
			for(Entry e: diffInNew)
				console.println(e);
		}
	}
	
	//Output: different items in IFD0. Sub-IFD is equal is printed if old jpeg and
	//new jpeg has same sub-IFD
	private static void checkSubIfd() {
		oldIfd = oldExif.getSubIfd();
		newIfd = newExif.getSubIfd();
		
		checkIfd();
		if(diffInOld.isEmpty() && diffInNew.isEmpty())
			console.println("\nSub-IFD is equal.");
		else {
			//print out the difference
			console.println("\nDifferent items in sub-IFD:");
			console.println("In old jpeg:");
			for(Entry e: diffInOld)
				console.println(e);
			console.println("In new jpeg:");
			for(Entry e: diffInNew)
				console.println(e);
		}
	}
	
	//Output: different items in IFD0. IFD 0 is equal is printed if old jpeg and
	//new jpeg has same IFD 0
	private static void checkIfd0() {
		oldIfd = oldExif.getIfd0();
		newIfd = newExif.getIfd0();
		
		checkIfd();
		if(diffInOld.isEmpty() && diffInNew.isEmpty())
			console.println("\nIFD 0 is equal.");
		else {
			//print out the difference
			console.println("\nDifferent items in IFD 0:");
			console.println("In old jpeg:");
			for(Entry e: diffInOld)
				console.println(e);
			console.println("In new jpeg:");
			for(Entry e: diffInNew)
				console.println(e);
		}
	}
	
	//check whether IFD is copied
	//Post: two LinkedList diffInOld and diffInNew that contains different Entry in new and old jpeg
	private static void checkIfd() {
		//set up result variable
		diffInOld = new LinkedList<Entry>();
		diffInNew = new LinkedList<Entry>();
		
		for(Entry e : oldIfd)
			diffInOld.add(e);
		for(Entry e : newIfd)
			diffInNew.add(e);
		
		//remove same items in jpeg
		for(Entry newEntry : newIfd)
			for(Entry oldEntry : oldIfd)
				if(oldEntry.equals(newEntry)) {
					diffInOld.remove(oldEntry);
					diffInNew.remove(newEntry);
				}
	}
}
