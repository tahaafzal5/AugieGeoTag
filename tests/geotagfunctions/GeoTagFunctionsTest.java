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
		
		testGetLongtitude();
		
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
		assertEquals(null, GeoTagFunctions.getGeoTagData(new File("./assets/results/editted-" + jpeg.getName())));
		
		jpeg = new File("./assets/Cannon-EOS-M50-no-geotag.JPG");
		remove = GeoTagFunctions.removeGeoTagData(jpeg);
		assertEquals(true, remove);
		assertEquals(null, GeoTagFunctions.getGeoTagData(new File("./assets/results/editted-" + jpeg.getName())));
		
		jpeg = new File("./assets/iPhone-6.JPG");
		remove = GeoTagFunctions.removeGeoTagData(jpeg);
		assertEquals(true, remove);
		assertEquals(null, GeoTagFunctions.getGeoTagData(new File("./assets/results/editted-" + jpeg.getName())));
		
		jpeg = new File("./assets/iPhone12-geotag.JPG");
		remove = GeoTagFunctions.removeGeoTagData(jpeg);
		assertEquals(true, remove);
		assertEquals(null, GeoTagFunctions.getGeoTagData(new File("./assets/results/editted-" + jpeg.getName())));
		
		jpeg = new File("./assets/iPhoneXS.jpeg");
		remove = GeoTagFunctions.removeGeoTagData(jpeg);
		assertEquals(true, remove);
		assertEquals(null, GeoTagFunctions.getGeoTagData(new File("./assets/results/editted-" + jpeg.getName())));
		
		deleteTestFiles();
	}
	
	//updateGeotag test
	private void testUpdateGeoTag()
	{
		File jpeg = new File("./assets/internet.jpg");
		String geotag = "Latitude: 50 degrees, 0 minutes, 0 seconds N, Longitude: 50 degrees, 0 minutes, 0 seconds E";
		boolean update = GeoTagFunctions.updateGeoTagData(jpeg, 50, 50);
		assertEquals(true, update);
		assertEquals(geotag, GeoTagFunctions.getGeoTagData(new File("./assets/results/editted-" + jpeg.getName())));
		
		jpeg = new File("./assets/Cannon-EOS-M50-no-geotag.JPG");
		geotag = "Latitude: 80 degrees, 0 minutes, 0 seconds N, Longitude: 80 degrees, 0 minutes, 0 seconds E";
		update = GeoTagFunctions.addGeoTagData(jpeg, 80, 80);
		assertEquals(true, update);
		assertEquals(geotag, GeoTagFunctions.getGeoTagData(new File("./assets/results/editted-" + jpeg.getName())));
		
		jpeg = new File("./assets/iPhone-6.JPG");
		geotag = "Latitude: 40 degrees, 30 minutes, 35.999 seconds N, Longitude: 40 degrees, 30 minutes, 35.999 seconds E";
		update = GeoTagFunctions.updateGeoTagData(jpeg, 40.51, 40.51);
		assertEquals(true, update);
		assertEquals(geotag, GeoTagFunctions.getGeoTagData(new File("./assets/results/editted-" + jpeg.getName())));
		
		jpeg = new File("./assets/iPhone12-geotag.JPG");
		geotag = "Latitude: 40 degrees, 30 minutes, 35.999 seconds N, Longitude: 40 degrees, 30 minutes, 35.999 seconds E";
		update = GeoTagFunctions.addGeoTagData(jpeg, 40.51, 40.51);
		assertEquals(true, update);
		assertEquals(geotag, GeoTagFunctions.getGeoTagData(new File("./assets/results/editted-" + jpeg.getName())));
		
		jpeg = new File("./assets/iPhoneXS.jpeg");
		geotag = "Latitude: 40 degrees, 30 minutes, 35.999 seconds S, Longitude: 40 degrees, 30 minutes, 35.999 seconds W";
		update = GeoTagFunctions.updateGeoTagData(jpeg, -40.51, -40.51);
		assertEquals(true, update);
		assertEquals(geotag, GeoTagFunctions.getGeoTagData(new File("./assets/results/editted-" + jpeg.getName())));
		
		deleteTestFiles();
	}
	
	//Post: delete all test files that has generated during test.
	private void deleteTestFiles()
	{
		File result = new File("./assets/results/editted-internet.jpg");
		result.delete();
		
		result = new File("./assets/results/editted-Cannon-EOS-M50-no-geotag.JPG");
		result.delete();
		
		result = new File("./assets/results/editted-iPhone-6.JPG");
		result.delete();
		
		result = new File("./assets/results/editted-iPhone12-geotag.JPG");
		result.delete();
		
		result = new File("./assets/results/editted-iPhoneXS.jpeg");
		result.delete();
	}
	
	//getGpsData test
	private void testGetGPSData()
	{
		String geotag = "Latitude: 43 degrees, 28 minutes, 2.814 seconds N, Longitude: 11 degrees, 53 minutes, 6.456 seconds E";
		String hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/internet.jpg"));
		assertEquals(geotag, hasGPS);
		geotag = "Latitude: 40 degrees, 0 minutes, 24.46 seconds N, Longitude: 116 degrees, 18 minutes, 4.57 seconds E";
		hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iPhone-6.JPG"));
		assertEquals(geotag, hasGPS);
		geotag = "Latitude: 43 degrees, 31 minutes, 30.79 seconds N, Longitude: 96 degrees, 44 minutes, 18.34 seconds W";
		hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iPhone12-geotag.JPG"));
		assertEquals(geotag, hasGPS);
		geotag = "Latitude: 36 degrees, 3 minutes, 28.4 seconds N, Longitude: 112 degrees, 7 minutes, 59.03 seconds W";
		hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iPhoneXS.jpeg"));
		assertEquals(geotag, hasGPS);
		geotag = "Latitude: 43 degrees, 51 minutes, 2.91 seconds N, Longitude: 103 degrees, 31 minutes, 53.97 seconds W";
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
	private void testGetLongtitude()
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
