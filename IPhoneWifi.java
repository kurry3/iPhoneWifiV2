package iOSWifi3;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class iPhoneWifi implements Runnable{

	 String url;
	 String udid;
	 String deviceName;
	 String devicePort;
	 String bootstrapPort;
	 Integer wdaLocalPort;
	 String deviceType;

	    public iPhoneWifi(String url, String udid, String deviceName, Integer wdaLocalPort, String deviceType) {
	        this.url = url;
	        this.udid = udid;
	        this.deviceName = deviceName;
	        this.wdaLocalPort = wdaLocalPort;
		this.deviceType = deviceType;
	    }

	    IOSDriver<MobileElement> driver;
	    DesiredCapabilities cap = new DesiredCapabilities();

	    @SuppressWarnings("rawtypes")
		private void controlCenter() {
			cap.setCapability("platformName", "iOS");
			cap.setCapability("platformVersion", "11.4");
			cap.setCapability("deviceName", deviceName);
			cap.setCapability("automationName", "XCUITest");
			cap.setCapability("udid", udid);
			cap.setCapability("wdaLocalPort", wdaLocalPort);
			cap.setCapability("bundleId", "com.yourcompany.Endpoint");

			try {
				driver = (new IOSDriver<MobileElement>(new URL(url),cap));
			}catch (MalformedURLException e) {
				e.printStackTrace();
			}

	     		try {
				Thread.sleep(5*1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}

	        	System.out.println(url + " Test Started");
			Dimension d = driver.manage().window().getSize();
			int height = d.getHeight();
			int width = d.getWidth();
			//System.out.println(height);
			//System.out.println(width);

			TouchAction touchAction = new TouchAction(driver);
			//iPhoneX control center is accessed by pulling down from the top right corner of the screen
			if (deviceType.equals("iPhoneX")){
				System.out.println("an iPhoneX");
				touchAction.press(PointOption.point(width, 0)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(width/2, height/2)).release().perform();
			}else{
			//control center is accessed by pulling up from the bottom of the screen
				System.out.println(deviceType);
				touchAction.press(PointOption.point(width/2, height)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(width/2, -height)).release().perform();
			}
			try {
				Thread.sleep(2*1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}

			driver.findElementByAccessibilityId("wifi-button").click();
			try {
				Thread.sleep(2*1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}

			driver.quit();
			System.out.println("DONE");
	    }

	    public static void main(String args[]) {
	        //Runnable r2 = new AppiumParallelTest("http://127.0.0.1:3005/wd/hub", "f2164f6fed275ba15386c7eebc99b2926ad398c6", "RF's iPhone", "8200"); //device id of second mobile device
		//host url, udid, "device name", wdalocalport, "deviceType NO SPACE" ie: iPhoneX
		Runnable r2 = new iPhoneWifi(args[0], args[1], args[2], Integer.valueOf(args[3]), args[4]);
	    	System.out.println("iPhone Instance Created");
	        new Thread(r2).start();
	    }

	    @Override
	    public void run() {
	        controlCenter();

	    }
	}
