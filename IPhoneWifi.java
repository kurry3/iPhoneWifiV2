package test.java.IPhoneWifi;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

//import org.testng.annotations.*;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.Point;
//import org.openqa.selenium.interactions.internal.TouchAction;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
//import io.appium.java_client.ios.*;
import io.appium.java_client.ios.IOSDriver;
//import io.appium.java_client.touch.WaitOptions;
//import io.appium.java_client.ios.IOSElement;
//import io.appium.java_client.touch.ActionOptions;
//import io.appium.java_client.touch.offset.*;
import io.appium.java_client.TouchAction;

public class IPhoneWifi{
	
	
	
	private static AppiumDriver<MobileElement> driver;
	
	@BeforeAll
	public static void ixchariotLaunch() throws Exception {
		System.setProperty("webdriver.http.factory", "apache");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "iOS");
		cap.setCapability("platformVersion", "11.3");
		//cap.setCapability("platformVersion", driver.getCapabilities().getCapability("platformVersion"));
		//cap.setCapability("deviceName",driver.getCapabilities().getCapability("deviceName"));
		cap.setCapability("deviceName", "RF's iPhone");
		cap.setCapability("automationName", "XCUITest");
		cap.setCapability("udid", "f2164f6fed275ba15386c7eebc99b2926ad398c6");
		//cap.setCapability("udid", driver.getCapabilities().getCapability("udid"));
		cap.setCapability("bundleId", "com.yourcompany.Endpoint");
		
		try {
			driver = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4727/wd/hub"),cap);
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Appium Session Started");
	}
	
    @SuppressWarnings("rawtypes")
	@Test
    void myFirstTest() {
		System.out.println("Test Started");
		
		Dimension d = driver.manage().window().getSize();
		int height = d.getHeight();
		int width = d.getWidth();
		System.out.println(height);
		System.out.println(width);

		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Object> swipeObj = new HashMap<>();
		swipeObj.put("fromY", height);
		swipeObj.put("fromX", width/2);
		swipeObj.put("toX", width/2);
		swipeObj.put("toY", -height);
		swipeObj.put("duration", 0.5);
		js.executeScript("mobile:dragFromToForDuration", swipeObj);
		HashMap<String,Object> params = new HashMap<>();
		params.put("direction", "up");
		js.executeScript("mobile:swipe", params);
		HashMap<String,Object> scrollParams = new HashMap<>();
		
		
		scrollParams.put("direction","down");
		js.executeScript("mobile:scroll", scrollParams);*/
		
		TouchAction touchAction = new TouchAction(driver);
		touchAction.press(width/2, height).waitAction(Duration.ofMillis(500)).moveTo(width/2, -height).release().perform();

		try {
			Thread.sleep(2*1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		/* if (driver.getInstance()findElement(By.id("WiFi")).isEnabled()){
		 * 	click("WiFi", Attribute.NAME);
		 * }
		 * else {
		 * 	click("WiFi", Attribute.NAME);
		 * }
		 * 
		 * */
		
		int count = 0;
		/*do {
			count = count + 1;
			System.out.println(count);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
		}while (count <= 10);*/
		do {
			driver.findElementByAccessibilityId("wifi-button").click();
			count = count + 1;
			//touchAction.waitAction(WaitOptions.waitOptions(Duration.ofMillis(5*1000)));
			try {
				Thread.sleep(4900);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}while (count <= 5);
		
		
    }
   
	@AfterAll
	public static void tearDown() throws Exception{
		/*try {
			Thread.sleep(10*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}*/
		driver.quit();
	}

}
