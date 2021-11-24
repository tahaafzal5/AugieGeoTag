package tests.geoTagFunctionsTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata.GPSInfo;
import src.GeoTagFunctions;
import java.util.*;
import java.io.*;

public class GeoTagFunctionsTest {
	@Test
	public void GeoTagFunctionsTest() throws FileNotFoundException
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
	}
	
	//updateGeotag test
	private void testUpdateGeoTag()
	{
		boolean update = GeoTagFunctions.updateGeoTagData(new File("./assets/internet.jpg"), 0, 0);
		assertEquals(update, true);
	}
	
	//getGpsData test
	private void testGetGPSData()
	{
		String geotag = "Latitude: 43 degrees, 28 minutes, 2.814 seconds N, Longitude: 11 degrees, 53 minutes, 6.456 seconds E";
		String hasGPS = GeoTagFunctions.getGeoTagData(new File("./assets/internet.jpg"));
		assertEquals(hasGPS, geotag);
		String noGPS = GeoTagFunctions.getGeoTagData(new File("./assets/iPhone12-no-geotag.jpg"));
		assertEquals(noGPS, null);
	}
	
	//getGpsInfo test
	private void testGetGPSInfo()
	{
		GPSInfo validGPS = GeoTagFunctions.getGPSInfo(new File("./assets/internet.jpg"));
		assertEquals(validGPS != null, true);
		GPSInfo invalidGPS = GeoTagFunctions.getGPSInfo(new File("./assets/iPhone12-no-geotag.JPG"));
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
						
		//degree, minute
		answer = 100 + 30.66 / 60;
		withRef = GeoTagFunctions.getLongitude("100 30.66 E");
		assertEquals(withRef, answer);
		noRef = GeoTagFunctions.getLongitude("100 30.66");
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
		boolean false_result = GeoTagFunctions.isJpeg(new File("./assets/MacBook-png.png"));
		assertEquals(true_result, true);
		assertEquals(false_result, false);
	}
	
	//test openFile
	private void testOpenFile() throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("./tests/geoTagFunctionsTest/GeoTagFunctionsTest.txt"));
		File openFile = GeoTagFunctions.openFile(input);
		assertEquals(openFile != null, true);
	}
}
