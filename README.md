Refer to my git repository wifitoggle (https://github.com/kurry3/wifiToggle) on how to set up the environment with Xcode and Appium.

Terminal:
 ```
 $javac -d bin -sourcepath src -cp "lib/*" src/path/to/file.java
 $java -cp bin:"lib/*" path.to.file "appiumServerURL" "udid" "Device Name" "WebDriverAgent Port" "deviceType"
 ```
 
Terminal Example:
 ```
 $javac -d bin -sourcepath src -cp "lib/*" src/iOSWifi3/iPhoneWifi.java
 $java -cp bin:"lib/*" iOSWifi3.iPhoneWifi http://127.0.0.1:4723/wd/hub xxxxx "RF's iPhone" 8100 "iPhoneX"
 ```
