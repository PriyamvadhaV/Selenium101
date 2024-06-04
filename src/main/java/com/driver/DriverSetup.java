package com.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSetup {
	public RemoteWebDriver driver;

	public RemoteWebDriver startDriver(String os, String browser, String version, String username, String accesskey, String gridURL) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("version", version);
        capabilities.setCapability("platform", os); 
        capabilities.setCapability("build", "LambdaTestSelenium101");
        capabilities.setCapability("name", "LambdaTest_Selenium101_Certification");
        capabilities.setCapability("network", true);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("video", true);
        capabilities.setCapability("console", true);
        capabilities.setCapability("w3c", true);
        capabilities.setCapability("terminal", true);
        
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		
        return driver;
	}
}
