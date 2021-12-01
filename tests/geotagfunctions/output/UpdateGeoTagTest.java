package tests.geotagfunctions.output;

import src.GeoTagFunctions;
import jpeg.*;
import java.io.*;
import java.util.LinkedList;

public class UpdateGeoTagTest {
	
	private static JpegExif oldExif = null;
	private static JpegExif newExif = null;
	private static LinkedList<Entry> oldIfd = null;
	private static LinkedList<Entry> newIfd = null;
	private static PrintStream console = System.out;
	private static LinkedList<Entry> diffInOld = null;
	private static LinkedList<Entry> diffInNew = null;
	
	public static void main(String[] args) throws IOException {
		File jpeg = new File("./assets/Cannon-EOS-M50-no-geotag.JPG");
		PrintStream fileOutput = new PrintStream(new File("./tests/geotagfunctions/output/print.txt"));
		System.setOut(fileOutput);
		System.setErr(fileOutput);
		final double latitude = -(60 + 30 / 60.0 + 40.88 / 3600.0);
		final double longitude = -(100 + 40 / 60.0 + 50.87 / 3600.0);
		GeoTagFunctions.updateGeoTagData(jpeg, latitude, longitude);
		File edittedJpeg = new File("./assets/results/editted-" + jpeg.getName());
		
		Jpeg oldJpeg = new Jpeg(jpeg);
		Jpeg newJpeg = new Jpeg(edittedJpeg);
		oldExif = oldJpeg.exif;
		newExif = newJpeg.exif;
		
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
		
		if(oldIfd == null && newIfd == null){
			console.println("\nGPS IFD is not available in both jpeg");
			return;
		} else if (oldIfd == null){
			console.println("\nGPS IFD is not available in old jpeg");
			console.println("GPS IFD in new jpeg:");
			for(Entry e : newIfd)
				console.println(e);
			return;
		} else if(newIfd == null){
			console.println("\nGPS IFD is not available in new jpeg");
			console.println("GPS IFD in old jpeg:");
			for(Entry e : oldIfd)
				console.println(e);
			return;
		}

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
		
		if(oldIfd == null && newIfd == null){
			console.println("\nIFD 1 is not available in both jpeg");
			return;
		} else if (oldIfd == null){
			console.println("\nIFD 1 is not available in old jpeg");
			console.println("IFD 1 in new jpeg:");
			for(Entry e : newIfd)
				console.println(e);
			return;
		} else if(newIfd == null){
			console.println("\nIFD 1 is not available in new jpeg");
			console.println("IFD 1 in old jpeg:");
			for(Entry e : oldIfd)
				console.println(e);
			return;
		}
		
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
		
		if(oldIfd == null && newIfd == null){
			console.println("\nSub-IFD is not available in both jpeg");
			return;
		} else if (oldIfd == null){
			console.println("\nSub-IFD is not available in old jpeg");
			console.println("Sub-IFD in new jpeg:");
			for(Entry e : newIfd)
				console.println(e);
			return;
		} else if(newIfd == null){
			console.println("\nSub-IFD is not available in new jpeg");
			console.println("Sub-IFD in old jpeg:");
			for(Entry e : oldIfd)
				console.println(e);
			return;
		}
		
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
		
		if(oldIfd == null && newIfd == null){
			console.println("\nIFD 0 is not available in both jpeg");
			return;
		} else if (oldIfd == null){
			console.println("\nIFD 0 is not available in old jpeg");
			console.println("IFD 0 in new jpeg:");
			for(Entry e : newIfd)
				console.println(e);
			return;
		} else if(newIfd == null){
			console.println("\nIFD 0 is not available in new jpeg");
			console.println("IFD 0 in old jpeg:");
			for(Entry e : oldIfd)
				console.println(e);
			return;
		}
		
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
