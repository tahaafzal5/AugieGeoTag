package tests.geotagfunctions;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata.GPSInfo;
import src.GeoTagFunctions;
import java.util.*;
import java.io.*;

public class GeoTagFunctionsTest {
	@Test
	public void GeoTagFunctionsTests() throws FileNotFoundException
	{
		testOpenFile();
		
		testIsJpeg();
		
		testGetLatitude();
		
		testGetLongitude();
		
		testGetGPSInfo();
		
		testGetGPSData();
		
		testUpdateGeoTag();
		
		testRemoveGeoTag();
	}
	
	//removeGeotag test
	private void testRemoveGeoTag()
	{
		File jpeg = new File("./assets/internet.jpg");
		boolean remove = GeoTagFunctions.removeGeoTagData(jpeg);
		assertEquals(true, remove);
		assertEquals(null, GeoTagFunctions.getGeoTagData(new File("./assets/results/edited-" + jpeg.getName())));
		
		jpeg = new File("./assets/Cannon-EOS-M50-no-geotag.JPG");
		remove = GeoTagFunctions.removeGeoTagData(jpeg);
		assertEquals(true, remove);
		assertEquals(null, GeoTagFunctions.getGeoTagData(new File("./assets/results/edited-" + jpeg.getName())));
		
		jpeg = new File("./assets/iPhone-6.JPG");
		remove = GeoTagFunctions.removeGeoTagData(jpeg);
		assertEquals(true, remove);
		assertEquals(null, GeoTagFunctions.getGeoTagData(new File("./assets/results/edited-" + jpeg.getName())));
		
		jpeg = new File("./assets/iPhone12-geotag.JPG");
		remove = GeoTagFunctions.removeGeoTagData(jpeg);
		assertEquals(true, remove);
		assertEquals(null, GeoTagFunctions.getGeoTagData(new File("./assets/results/edited-" + jpeg.getName())));
		
		jpeg = new File("./assets/Pixel-2-XL.jpg");
		remove = GeoTagFunctions.removeGeoTagData(jpeg);
		assertEquals(true, remove);
		assertEquals(null, GeoTagFunctions.getGeoTagData(new File("./assets/results/edited-" + jpeg.getName())));
		
		deleteTestFiles();
	}
	
	//updateGeotag test
	private void testUpdateGeoTag()
	{
		File jpeg = new File("./assets/internet.jpg");
		String geotag = "Latitude & Longitude: 50° 0' 0\" N 50° 0' 0\" E";
		boolean update = GeoTagFunctions.updateGeoTagData(jpeg, 50, 50);
		assertEquals(true, update);
		assertEquals(geotag, GeoTagFunctions.getGeoTagData(new File("./assets/results/edited-" + jpeg.getName())));
		
		jpeg = new File("./assets/Cannon-EOS-M50-no-geotag.JPG");
		geotag = "Latitude & Longitude: 80° 0' 0\" N 80° 0' 0\" E";
		update = GeoTagFunctions.addGeoTagData(jpeg, 80, 80);
		assertEquals(true, update);
		assertEquals(geotag, GeoTagFunctions.getGeoTagData(new File("./assets/results/edited-" + jpeg.getName())));
		
		jpeg = new File("./assets/iPhone-6.JPG");
		geotag = "Latitude & Longitude: 40° 30' 35.999\" N 40° 30' 35.999\" E";
		update = GeoTagFunctions.updateGeoTagData(jpeg, 40.51, 40.51);
		assertEquals(true, update);
		assertEquals(geotag, GeoTagFunctions.getGeoTagData(new File("./assets/results/edited-" + jpeg.getName())));
		
		jpeg = new File("./assets/iPhone12-geotag.JPG");
		geotag = "Latitude & Longitude: 40° 30' 35.999\" N 40° 30' 35.999\" E";
		update = GeoTagFunctions.addGeoTagData(jpeg, 40.51, 40.51);
		assertEquals(true, update);
		assertEquals(geotag, GeoTagFunctions.getGeoTagData(new File("./assets/results/edited-" + jpeg.getName())));
		
		jpeg = new File("./assets/Pixel-2-XL.jpg");
		geotag = "Latitude & Longitude: 40° 30' 35.999\" S 40° 30' 35.999\" W";
		update = GeoTagFunctions.updateGeoTagData(jpeg, -40.51, -40.51);
		assertEquals(true, update);
		assertEquals(geotag, GeoTagFunctions.getGeoTagData(new File("./assets/results/edited-" + jpeg.getName())));
		
		deleteTestFiles();
	}
	
	//Post: delete all test files that has generated during test.
	private void deleteTestFiles()
	{
		File result = new File("./assets/results/edited-internet.jpg");
		result.delete();
		
		result = new File("./assets/results/edited-Cannon-EOS-M50-no-geotag.JPG");
		result.delete();
		
		result = new File("./assets/results/edited-iPhone-6.JPG");
		result.delete();
		
		result = new File("./assets/results/edited-iPhone12-geotag.JPG");
		result.delete();
		
		result = new File("./assets/results/edited-Pixel-2-XL.jpg");
		result.delete();
	}
	
	//getGpsData test
	private void testGetGPSData()
	{
		String geotag = "Latitude & Longitude: 43° 28' 2.814\" N 11° 53' 6.456\" E";
		String hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/internet.jpg"));
		assertEquals(geotag, hasGPS);
		geotag = "Latitude & Longitude: 40° 0' 24.46\" N 116° 18' 4.57\" E";
		hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iPhone-6.JPG"));
		assertEquals(geotag, hasGPS);
		geotag = "Latitude & Longitude: 43° 31' 30.79\" N 96° 44' 18.34\" W";
		hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iPhone12-geotag.JPG"));
		assertEquals(geotag, hasGPS);
		geotag = "Latitude & Longitude: 36° 3' 28.4\" N 112° 7' 59.03\" W";
		hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iPhoneXS.jpeg"));
		assertEquals(geotag, hasGPS);
		geotag = "Latitude & Longitude: 43° 51' 2.91\" N 103° 31' 53.97\" W";
		hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/Pixel-2-XL-2.jpg"));
		assertEquals(geotag, hasGPS);
		
		String noGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iPhone12-no-geotag.jpg"));
		assertEquals(null, noGPS);
		noGPS = GeoTagFunctions.getGeoTagData(new File("./assets/No-geotag.jpeg"));
		assertEquals(null, noGPS);
		noGPS = GeoTagFunctions.getGeoTagData(new File("./assets/Cannon-EOS-M50-no-geotag.JPG"));
		assertEquals(null, noGPS);
		noGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iphone11-notag.jpg"));
		assertEquals(null, noGPS);
		noGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iphone11-notag2.jpg"));
		assertEquals(null, noGPS);
	}
	
	//getGpsInfo test
	private void testGetGPSInfo()
	{
		GPSInfo validGPS = GeoTagFunctions.getGPSInfo(new File("./assets/internet.jpg"));
		assertEquals(true, validGPS != null);
		validGPS = GeoTagFunctions.getGPSInfo(new File("./assets/iPhone-6.JPG"));
		assertEquals(true, validGPS != null);
		validGPS = GeoTagFunctions.getGPSInfo(new File("./assets/iPhone12-geotag.JPG"));
		assertEquals(true, validGPS != null);
		validGPS = GeoTagFunctions.getGPSInfo(new File("./assets/iPhoneXS.jpeg"));
		assertEquals(true, validGPS != null);
		validGPS = GeoTagFunctions.getGPSInfo(new File("./assets/Pixel-2-XL-2.jpg"));
		assertEquals(true, validGPS != null);
		
		GPSInfo invalidGPS = GeoTagFunctions.getGPSInfo(new File("./assets/iPhone12-no-geotag.JPG"));
		assertEquals(true, invalidGPS == null);
		invalidGPS = GeoTagFunctions.getGPSInfo(new File("./assets/No-geotag.jpeg"));
		assertEquals(true, invalidGPS == null);
		invalidGPS = GeoTagFunctions.getGPSInfo(new File("./assets/Cannon-EOS-M50-no-geotag.JPG"));
		assertEquals(true, invalidGPS == null);
		invalidGPS = GeoTagFunctions.getGPSInfo(new File("./assets/iphone11-notag.jpg"));
		assertEquals(true, invalidGPS == null);
		invalidGPS = GeoTagFunctions.getGPSInfo(new File("./assets/iphone11-notag2.jpg"));
		assertEquals(true, invalidGPS == null);
	}
	
	//test getLongitude
	private void testGetLongitude()
	{
		//degree, minute, second
		Double answer = 100 + 30 / 60.0 + 20.8 / 3600;
		Double withRef = GeoTagFunctions.getLongitude("100 30 20.8 E");
		assertEquals(answer, withRef);
		Double noRef = GeoTagFunctions.getLongitude("100 30 20.8");
		assertEquals(answer, noRef);
		answer= -answer;
		noRef = GeoTagFunctions.getLongitude("-100 -30 -20.8");
		assertEquals(answer, noRef);
						
		//degree, minute
		answer = 100 + 30.66 / 60;
		withRef = GeoTagFunctions.getLongitude("100 30.66 E");
		assertEquals(answer, withRef);
		noRef = GeoTagFunctions.getLongitude("100 30.66");
		assertEquals(answer, noRef);
		answer = -answer;
		noRef = GeoTagFunctions.getLongitude("-100 -30.66");
		assertEquals(answer, noRef);
						
		//degree only
		answer = 100.88;
		withRef = GeoTagFunctions.getLongitude("100.88 E");
		assertEquals(answer, withRef);
		noRef = GeoTagFunctions.getLongitude("100.88");
		assertEquals(answer, noRef);
		answer = -100.88;
		noRef = GeoTagFunctions.getLongitude("-100.88");
		assertEquals(answer, noRef);
	}
	
	//test getLatitude
	private void testGetLatitude()
	{
		//degree, minute, second
		Double answer = 60 + 30 / 60.0 + 20.8 / 3600;
		Double withRef = GeoTagFunctions.getLatitude("60 30 20.8 N");
		assertEquals(answer, withRef);
		Double noRef = GeoTagFunctions.getLatitude("60 30 20.8");
		assertEquals(answer, noRef);
				
		//degree, minute
		answer = 60 + 30.66 / 60;
		withRef = GeoTagFunctions.getLatitude("60 30.66 N");
		assertEquals(answer, withRef);
		noRef = GeoTagFunctions.getLatitude("60 30.66");
		assertEquals(answer, noRef);
				
		//degree only
		answer = 60.88;
		withRef = GeoTagFunctions.getLatitude("60.88 N");
		assertEquals(answer, withRef);
		noRef = GeoTagFunctions.getLatitude("60.88");
		assertEquals(answer, noRef);
		answer = -60.88;
		noRef = GeoTagFunctions.getLatitude("-60.88");
		assertEquals(answer, noRef);
	}
	
	//test isJpeg
	private void testIsJpeg()
	{
		boolean trueResult = GeoTagFunctions.isJpeg(new File("./assets/internet.jpg"));
		assertEquals(true, trueResult);
		trueResult = GeoTagFunctions.isJpeg(new File("./assets/Pixel-2-XL-2.jpg"));
		assertEquals(true, trueResult);
		trueResult = GeoTagFunctions.isJpeg(new File("./assets/No-geotag.jpeg"));
		assertEquals(true, trueResult);
		trueResult = GeoTagFunctions.isJpeg(new File("./assets/iPhone12-geotag.JPG"));
		assertEquals(true, trueResult);
		trueResult = GeoTagFunctions.isJpeg(new File("./assets/Cannon-EOS-M50-no-geotag.JPG"));
		assertEquals(true, trueResult);
		
		boolean falseResult = GeoTagFunctions.isJpeg(new File("./assets/MacBook-png.png"));
		assertEquals(false, falseResult);
		falseResult = GeoTagFunctions.isJpeg(new File("./assets/Not-JPEG-movie.MOV"));
		assertEquals(false, falseResult);
		falseResult = GeoTagFunctions.isJpeg(new File("./assets/results"));
		assertEquals(false, falseResult);
		falseResult = GeoTagFunctions.isJpeg(new File("./assets/IMG_1492.png"));
		assertEquals(false, falseResult);
		falseResult = GeoTagFunctions.isJpeg(new File("./assets/IMG_1685.png"));
		assertEquals(false, falseResult);
	}
	
	//test openFile
	private void testOpenFile() throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("./tests/geotagfunctions/GeoTagFunctionsTest.txt"));
		File openFile = GeoTagFunctions.openFile(input);
		assertEquals(true, openFile != null);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(true, openFile != null);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(true, openFile != null);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(true, openFile != null);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(true, openFile != null);
		
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(true, openFile == null);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(true, openFile == null);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(true, openFile == null);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(true, openFile == null);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(true, openFile == null);
	}
}
