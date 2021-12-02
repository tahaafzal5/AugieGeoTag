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
		boolean remove = GeoTagFunctions.removeGeoTagData(new File("./assets/internet.jpg"));
		assertEquals(remove, true);
		remove = GeoTagFunctions.removeGeoTagData(new File("./assets/Cannon-EOS-M50-no-geotag.JPG"));
		assertEquals(remove, true);
		remove = GeoTagFunctions.removeGeoTagData(new File("./assets/iPhone-6.JPG"));
		assertEquals(remove, true);
		remove = GeoTagFunctions.removeGeoTagData(new File("./assets/iPhone12-geotag.JPG"));
		assertEquals(remove, true);
		remove = GeoTagFunctions.removeGeoTagData(new File("./assets/iPhoneXS.jpeg"));
		assertEquals(remove, true);
	}
	
	//updateGeotag test
	private void testUpdateGeoTag()
	{
		boolean update = GeoTagFunctions.updateGeoTagData(new File("./assets/internet.jpg"), 0, 0);
		assertEquals(update, true);
		update = GeoTagFunctions.updateGeoTagData(new File("./assets/Cannon-EOS-M50-no-geotag.JPG"), 70, 60);
		assertEquals(update, true);
		update = GeoTagFunctions.updateGeoTagData(new File("./assets/iPhone-6.JPG"), -80, 80);
		assertEquals(update, true);
		update = GeoTagFunctions.updateGeoTagData(new File("./assets/iPhone12-geotag.JPG"), 40, -40);
		assertEquals(update, true);
		update = GeoTagFunctions.updateGeoTagData(new File("./assets/iPhoneXS.jpeg"), -50, -50);
		assertEquals(update, true);
	}
	
	//getGpsData test
	private void testGetGPSData()
	{
		String geotag = "Latitude: 43 degrees, 28 minutes, 2.814 seconds N, Longitude: 11 degrees, 53 minutes, 6.456 seconds E";
		String hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/internet.jpg"));
		assertEquals(hasGPS, geotag);
		geotag = "Latitude: 40 degrees, 0 minutes, 24.46 seconds N, Longitude: 116 degrees, 18 minutes, 4.57 seconds E";
		hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iPhone-6.JPG"));
		assertEquals(hasGPS, geotag);
		geotag = "Latitude: 43 degrees, 31 minutes, 30.79 seconds N, Longitude: 96 degrees, 44 minutes, 18.34 seconds W";
		hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iPhone12-geotag.JPG"));
		assertEquals(hasGPS, geotag);
		geotag = "Latitude: 36 degrees, 3 minutes, 28.4 seconds N, Longitude: 112 degrees, 7 minutes, 59.03 seconds W";
		hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iPhoneXS.jpeg"));
		assertEquals(hasGPS, geotag);
		geotag = "Latitude: 43 degrees, 51 minutes, 2.91 seconds N, Longitude: 103 degrees, 31 minutes, 53.97 seconds W";
		hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/Pixel-2-XL-2.jpg"));
		assertEquals(hasGPS, geotag);
		
		String noGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iPhone12-no-geotag.jpg"));
		assertEquals(noGPS, null);
		noGPS = GeoTagFunctions.getGeoTagData(new File("./assets/No-geotag.jpeg"));
		assertEquals(noGPS, null);
		noGPS = GeoTagFunctions.getGeoTagData(new File("./assets/Cannon-EOS-M50-no-geotag.JPG"));
		assertEquals(noGPS, null);
		noGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iphone11-notag.jpg"));
		assertEquals(noGPS, null);
		noGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iphone11-notag2.jpg"));
		assertEquals(noGPS, null);
	}
	
	//getGpsInfo test
	private void testGetGPSInfo()
	{
		GPSInfo validGPS = GeoTagFunctions.getGPSInfo(new File("./assets/internet.jpg"));
		assertEquals(validGPS != null, true);
		validGPS = GeoTagFunctions.getGPSInfo(new File("./assets/iPhone-6.JPG"));
		assertEquals(validGPS != null, true);
		validGPS = GeoTagFunctions.getGPSInfo(new File("./assets/iPhone12-geotag.JPG"));
		assertEquals(validGPS != null, true);
		validGPS = GeoTagFunctions.getGPSInfo(new File("./assets/iPhoneXS.jpeg"));
		assertEquals(validGPS != null, true);
		validGPS = GeoTagFunctions.getGPSInfo(new File("./assets/Pixel-2-XL-2.jpg"));
		assertEquals(validGPS != null, true);
		
		GPSInfo invalidGPS = GeoTagFunctions.getGPSInfo(new File("./assets/iPhone12-no-geotag.JPG"));
		assertEquals(invalidGPS == null, true);
		invalidGPS = GeoTagFunctions.getGPSInfo(new File("./assets/No-geotag.jpeg"));
		assertEquals(invalidGPS == null, true);
		invalidGPS = GeoTagFunctions.getGPSInfo(new File("./assets/Cannon-EOS-M50-no-geotag.JPG"));
		assertEquals(invalidGPS == null, true);
		invalidGPS = GeoTagFunctions.getGPSInfo(new File("./assets/iphone11-notag.jpg"));
		assertEquals(invalidGPS == null, true);
		invalidGPS = GeoTagFunctions.getGPSInfo(new File("./assets/iphone11-notag2.jpg"));
		assertEquals(invalidGPS == null, true);
	}
	
	//test getLongitude
	private void testGetLongtitude()
	{
		//degree, minute, second
		Double answer = 100 + 30 / 60.0 + 20.8 / 3600;
		Double withRef = GeoTagFunctions.getLongitude("100 30 20.8 E");
		assertEquals(withRef, answer);
		Double noRef = GeoTagFunctions.getLongitude("100 30 20.8");
		assertEquals(noRef, answer);
		answer= -answer;
		noRef = GeoTagFunctions.getLongitude("-100 -30 -20.8");
		assertEquals(noRef, answer);
						
		//degree, minute
		answer = 100 + 30.66 / 60;
		withRef = GeoTagFunctions.getLongitude("100 30.66 E");
		assertEquals(withRef, answer);
		noRef = GeoTagFunctions.getLongitude("100 30.66");
		assertEquals(noRef, answer);
		answer = -answer;
		noRef = GeoTagFunctions.getLongitude("-100 -30.66");
		assertEquals(noRef, answer);
						
		//degree only
		answer = 100.88;
		withRef = GeoTagFunctions.getLongitude("100.88 E");
		assertEquals(withRef, answer);
		noRef = GeoTagFunctions.getLongitude("100.88");
		assertEquals(noRef, answer);
		answer = -100.88;
		noRef = GeoTagFunctions.getLongitude("-100.88");
		assertEquals(noRef, answer);		
	}
	
	//test getLatitude
	private void testGetLatitude()
	{
		//degree, minute, second
		Double answer = 60 + 30 / 60.0 + 20.8 / 3600;
		Double withRef = GeoTagFunctions.getLatitude("60 30 20.8 N");
		assertEquals(withRef, answer);
		Double noRef = GeoTagFunctions.getLatitude("60 30 20.8");
		assertEquals(noRef, answer);
				
		//degree, minute
		answer = 60 + 30.66 / 60;
		withRef = GeoTagFunctions.getLatitude("60 30.66 N");
		assertEquals(withRef, answer);
		noRef = GeoTagFunctions.getLatitude("60 30.66");
		assertEquals(noRef, answer);
				
		//degree only
		answer = 60.88;
		withRef = GeoTagFunctions.getLatitude("60.88 N");
		assertEquals(withRef, answer);
		noRef = GeoTagFunctions.getLatitude("60.88");
		assertEquals(noRef, answer);
		answer = -60.88;
		noRef = GeoTagFunctions.getLatitude("-60.88");
		assertEquals(noRef, answer);
	}
	
	//test isJpeg
	private void testIsJpeg()
	{
		boolean true_result = GeoTagFunctions.isJpeg(new File("./assets/internet.jpg"));
		assertEquals(true_result, true);
		true_result = GeoTagFunctions.isJpeg(new File("./assets/Pixel-2-XL-2.jpg"));
		assertEquals(true_result, true);
		true_result = GeoTagFunctions.isJpeg(new File("./assets/No-geotag.jpeg"));
		assertEquals(true_result, true);
		true_result = GeoTagFunctions.isJpeg(new File("./assets/iPhone12-geotag.JPG"));
		assertEquals(true_result, true);
		true_result = GeoTagFunctions.isJpeg(new File("./assets/Cannon-EOS-M50-no-geotag.JPG"));
		assertEquals(true_result, true);
		
		boolean false_result = GeoTagFunctions.isJpeg(new File("./assets/MacBook-png.png"));
		assertEquals(false_result, false);
		false_result = GeoTagFunctions.isJpeg(new File("./assets/Not-JPEG-movie.MOV"));
		assertEquals(false_result, false);
		false_result = GeoTagFunctions.isJpeg(new File("./assets/results"));
		assertEquals(false_result, false);
		false_result = GeoTagFunctions.isJpeg(new File("./assets/IMG_1492.png"));
		assertEquals(false_result, false);
		false_result = GeoTagFunctions.isJpeg(new File("./assets/IMG_1685.png"));
		assertEquals(false_result, false);
	}
	
	//test openFile
	private void testOpenFile() throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("./tests/geotagfunctions/GeoTagFunctionsTest.txt"));
		File openFile = GeoTagFunctions.openFile(input);
		assertEquals(openFile != null, true);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(openFile != null, true);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(openFile != null, true);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(openFile != null, true);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(openFile != null, true);
		
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(openFile == null, true);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(openFile == null, true);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(openFile == null, true);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(openFile == null, true);
		openFile = GeoTagFunctions.openFile(input);
		assertEquals(openFile == null, true);
	}
}
