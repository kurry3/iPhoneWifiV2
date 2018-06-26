package iOSWifi3;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
//import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.TouchAction;

public class AppiumParallelTest implements Runnable{
	
	 String url;
	 String udid;
	 String deviceName;
	 String devicePort;
	   
	    public AppiumParallelTest(String url, String udid, String deviceName, String devicePort) {
	        this.url = url;
	        this.udid = udid;
	        this.deviceName = deviceName;
	        this.devicePort = devicePort;
	    }
	   
	    IOSDriver<MobileElement> driver;
	    DesiredCapabilities cap = new DesiredCapabilities();
	   
	   
	    private void controlCenter() {
			cap.setCapability("platformName", "iOS");
			cap.setCapability("platformVersion", "11.3");
			cap.setCapability("deviceName", deviceName);
			cap.setCapability("automationName", "XCUITest");
			cap.setCapability("udid", udid);
			cap.setCapability("bundleId", "com.yourcompany.Endpoint");
			//cap.setCapability("useNewWDA", true);
			cap.setCapability("wdaLocalPort", devicePort);
	       
	        try {
	            driver = new IOSDriver<MobileElement>(new URL(url), cap);
	            Thread.sleep(10000);
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	       
	        System.out.println(url + " Test Started");
			//System.out.println(tldriver.get().manage());
			Dimension d = driver.manage().window().getSize();
			int height = d.getHeight();
			int width = d.getWidth();
			System.out.println(height);
			System.out.println(width);

			TouchAction touchAction = new TouchAction(driver);
			touchAction.press(width/2, height).waitAction(Duration.ofMillis(500)).moveTo(width/2, -height).release().perform();

			try {
				Thread.sleep(2*1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}

			int count = 0;

			do {
				driver.findElementByAccessibilityId("wifi-button").click();
				count = count + 1;
				try {
					Thread.sleep(6000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				System.out.println(count);
			}while (count <= 5);
			System.out.println(deviceName + " Test Complete" );
			driver.quit();
	    }
	   
	    public static void main(String args[]) {
	    	Runnable r1 = new AppiumParallelTest("http://127.0.0.1:3001/wd/hub", "0a1364a47e9b044d605558130817ad53a9b5208c", "RF's iPhone7", "8300"); //device id of first mobile device
	        System.out.println("iPhone7 Instance Created" );
	        Runnable r2 = new AppiumParallelTest("http://127.0.0.1:3005/wd/hub", "f2164f6fed275ba15386c7eebc99b2926ad398c6", "RF's iPhone", "8200"); //device id of second mobile device
	        System.out.println("iPhone8 Instance Created");
	        new Thread(r1).start();
	        new Thread(r2).start();
	    }
	 
	    @Override
	    public void run() {
	        controlCenter();
	    }
	}

