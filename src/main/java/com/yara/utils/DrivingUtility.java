package com.yara.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class DrivingUtility {

	private static ResourceUtility resourceUtility;
	final static Logger logger = LoggerFactory.getLogger(DrivingUtility.class);
	static String portNumber;

	public static AndroidDriver<MobileElement> getDriver() throws MalformedURLException {

		resourceUtility = new ResourceUtility("config");
		String platformName = resourceUtility.getResource("platformName");
		String platformVersion = resourceUtility.getResource("platformVersion");
		String deviceName = resourceUtility.getResource("deviceName");
//		String deviceUDID = resourceUtility.getResource("deviceUDID");
		String appLocation = resourceUtility.getResource("appLocation");
		appLocation = System.getProperty("user.dir") + appLocation;
		String hostName = resourceUtility.getResource("hostName");
		portNumber = resourceUtility.getResource("portNumber");

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", platformName);
		desiredCapabilities.setCapability("platformVersion", platformVersion);
		desiredCapabilities.setCapability("deviceName", deviceName);
		desiredCapabilities.setCapability("app", appLocation);
		desiredCapabilities.setCapability("useNewWDA", true);
		desiredCapabilities.setCapability("noReset", false);
		desiredCapabilities.setCapability("fullReset", true);
		desiredCapabilities.setCapability("newCommandTimeout", "0");
		String appiumURL = "http://" + hostName + ":" + portNumber + "/wd/hub";
		return new AndroidDriver<MobileElement>(new URL(appiumURL), desiredCapabilities);
	}
}
