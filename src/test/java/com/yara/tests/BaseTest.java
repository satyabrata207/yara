package com.yara.tests;

import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.yara.pages.AddCropDetailsPage;
import com.yara.pages.AddNewCropPage;
import com.yara.pages.InstructionsPage;
import com.yara.pages.SelectLanguagePage;
import com.yara.pages.SelectLeafColourPage;
import com.yara.pages.WelcomeToLeafColourChartPage;
import com.yara.utils.DrivingUtility;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest {
	final static Logger logger = LoggerFactory.getLogger(BaseTest.class);
	public static AndroidDriver<MobileElement> appiumDriver;
	
	public SelectLanguagePage selectLanguagePage;
	WelcomeToLeafColourChartPage welcomeToLeafColourChartPage;
	AddNewCropPage addNewCropPage;
	AddCropDetailsPage addCropDetailsPage;
	InstructionsPage instructionsPage;
	SelectLeafColourPage selectLeafColourPage;

	@BeforeSuite
	public void startAppiumDriver() throws MalformedURLException {
		logger.info("Starting appium driver ...\n");
		appiumDriver = DrivingUtility.getDriver();
		logger.info("Application launches successfully \n");
	}
	
	@BeforeTest
	public void unlockDevice() {
		boolean isLocked = appiumDriver.isDeviceLocked();
		if(isLocked) {
			logger.info("Device is locked");
			appiumDriver.unlockDevice();
		} else {
			logger.info("Device is unlocked");
		}
		
	}
	
	@AfterSuite
	public void afterSuite() {
		appiumDriver.quit();
	}
}
