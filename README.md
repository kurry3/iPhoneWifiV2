# iPhoneWifiV2

About: 
  - Java project that uses Appium to interact with the GUI of a real iPhone and display the control center to toggle the wifi for testing purposes
  
Environment: 
- Appium: 5.0.4 , Appium Desktop: 1.8
  - !!!: originally Appium version was 6.0.0 but Thread.sleep results in the error "org.openqa.selenium.WebDriverException: java.net.SocketException: Connection reset". Issue does not resurface by downgrading Appium. 
- Selenium: 3.12
- Eclipse: 4., Java: 6.0
- XCode: 9.4


## **Installing Appium + WebDriverAgent for real device testing**
### **XCode WebDriverAgent**
  - Set Up: https://www.linkedin.com/pulse/appium-ver-171-setup-mac-os-sierra-ios-real-device-suraj-salunkhe
  - Signing - https://github.com/appium/appium-xcuitest-driver/blob/master/docs/real-device-config.md
  - **Important** - Apple Provisioning Profiles allow only **3** devices to be registered to the account. The devices cannot be changed until the end of the annual profile renewal.
  
  **When signing profile expires**
  - Open WebDriverAgent.xcode from directory:
     - /Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent
  - Change the **Bundle Identifier** for all targets found in **General** and **Build Settings**. Personally, I used:
    - com.iPhone.TestAutomation for WebDriverAgentLib and WebDriverAgentRunner
    - com.iPhone.TestAutomation.IntegrationTests for the IntegrationTests
    - com.iPhone.TestAutomation.IntegrationApp for the IntegrationApp
   - Select your account for signing found in **General**
    - Signing certificate expires in 7 days. So in 7 days, you will have to repeat the steps above and reinstall WebDriverAgent onto your device.
   - Terminal:
      - cd /Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent    
      - xcodebuild -project WebDriverAgent.xcodeproj -scheme WebDriverAgentRunner -destination 'id=<udid>' test
        - How to retrieve UDID of iPhone
          1. Connect phone and launch iTunes
          2. Phone > Summary > click information below Phone Number until the UDID is displayed
          OR in the terminal
           ```
           instruments -s devices
           ````
  
### **Appium**
  - Set Up 1: https://medium.com/@abrisad_it/setting-up-appium-with-java-on-os-x-83b4b59f1cfc
  - Set Up 2: http://www.automationtestinghub.com/appium-project-in-eclipse/
  - Right click project > Build Path > Configure Build Path > Add Library > Junit
  - Import pom.xml file 
      - will import Maven Dependencies necessary
  - Appium Server: http://www.automationtestinghub.com/appium-desktop/
  - **For Parallel Device Testing**
    1. Start 2 Appium Desktop servers
    2. Choose desired address, port, and select session override
    3. For IOS devices: choose different WebDriverAgent Ports for the servers. Will default to 8100.
    4. For Android devices: choose different bootstrap ports for the servers.
    5. Start Appium Session
  
  - **How to Launch Appium Desktop Inspector**
    1. Click the magnifying glass in the top right of Appium desktop
    2. Create a Custom Server
    3. Edit Desired Capabilities: 
      ```
      { 
          "platformName": "iOS",
          "platformVersion": "11.4",
          "deviceName" : " ",
          "automationName": "XCUITest",
          "udid": " ",
          "bundleId": " "
         }
      ```
    4. Start Session
    
    
    
### **About the Code**
 
       ```
      cap.setCapability("platformName", "iOS");
			cap.setCapability("platformVersion", "11.3");
			cap.setCapability("deviceName", deviceName);
			cap.setCapability("automationName", "XCUITest");
			cap.setCapability("udid", udid);
			cap.setCapability("bundleId", "com.yourcompany.Endpoint");
			cap.setCapability("wdaLocalPort", devicePort);

      ```

    
  - **How to Retrieve bundleId**
     - Self-Made Application: https://pspdfkit.com/guides/ios/current/faq/finding-the-app-bundle-id/
     - App Store Application: https://offcornerdev.com/bundleid.html
  - **Create and Run Appium Test with Maven**
      - https://stackoverflow.com/questions/24919159/how-to-create-in-eclipse-and-run-your-appium-test-on-the-ios-device-not-emulato


  
  
  





  
