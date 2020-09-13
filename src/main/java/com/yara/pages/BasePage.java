package com.yara.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class BasePage<T extends BasePage<T>> extends LoadableComponent<T> {
	
	
	final static Logger logger = LoggerFactory.getLogger(BasePage.class);
	private int timeInSeccond = 30;
	public AndroidDriver<MobileElement> appiumDriver;

	public BasePage(AndroidDriver<MobileElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
	}

	/**
	 * method to check element presence
	 */
	public boolean doesElementPresent(WebElement element) {
		logger.info("[COMMAND] DOES ELEMENT PRESENT FOR LOCATOR : " + element.toString());
		WebDriverWait wait = new WebDriverWait(appiumDriver, 1);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			logger.info("[RESPONSE] ELEMENT IS PRESENT FOR LOCATOR : " + element.getText());
			return true;
		} catch (Exception e) {
			logger.warn("[RESPONSE] ELEMENT IS NOT PRESENT FOR LOCATOR : " + element.toString());
			return false;
		}
	}
	
	/**
	 * method to check element presence by element text
	 */
	public boolean doesElementPresent(String elementText) {
		logger.info("[COMMAND] DOES ELEMENT PRESENT FOR LOCATOR : " + elementText);
		WebDriverWait wait = new WebDriverWait(appiumDriver, 1);
		try {
			wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElement(By.id(elementText))));
			logger.info("[RESPONSE] ELEMENT IS PRESENT FOR LOCATOR : " + elementText);
			return true;
		} catch (Exception e) {
			logger.warn("[RESPONSE] ELEMENT IS NOT PRESENT FOR LOCATOR : " + elementText);
			return false;
		}
	}

	/**
	 * method to wait for element presence
	 */
	public void waitForElement(WebElement element) {
		waitForElement(element, timeInSeccond);
	}

	/**
	 * method to wait for element presence
	 */
	public void waitForElement(WebElement element, int timeInSeccond) {
		logger.info("[COMMAND] WAIT FOR ELEMENT : " + element.toString());
		WebDriverWait wait = new WebDriverWait(appiumDriver, timeInSeccond);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			logger.info("[RESPONSE] WAIT FOR ELEMENT, ELEMENT IS PRESENT FOR LOCATOR : " + element.getText());
		} catch (Exception e) {
			logger.error("[RESPONSE] WAIT FOR ELEMENT, ELEMENT IS NOT PRESENT FOR LOCATOR : " + element.toString() + e);
			Assert.fail();
		}
	}

	/**
	 * method to click on the element
	 */
	public void clickOnElement(WebElement element) {
		logger.info("[COMMAND] CLICK ON ELEMENT FOR LOCATOR :" + element.toString());
		waitForElement(element);
		WebDriverWait wait = new WebDriverWait(appiumDriver, timeInSeccond);
		try {
			WebElement waitElement = wait.until(ExpectedConditions.elementToBeClickable(element));
			waitElement.click();
			logger.info("[RESPONSE] CLICKED ON ELEMENT FOR LOCATOR : " + element.toString());
		} catch (Exception e) {
			logger.error("[RESPONSE] FAILED TO CLICK ON ELEMENT FOR LOCATOR : " + element.toString() + e);
			Assert.fail();
		}
	}


	/**
	 * method to enter text into textElement
	 */
	public void enterTextField(WebElement element, String value) {
		logger.info("[COMMAND] ENTER TEXT VALUE \"" + value + "\" FOR LOCATOR : " + element.toString());
		try {
			waitForElement(element);
			element.click();
			element.clear();
			element.sendKeys(value);
			logger.info("[RESPONSE] ENTERED TEXT VALUE \"" + value + "\" FOR LOCATOR : " + element.toString());
		} catch (Exception e) {
			logger.error(
					"[RESPONSE] FAILED TO ENTERED TEXT VALUE \"" + value + "\" FOR LOCATOR : " + element.toString());
			Assert.fail();
		}
	}

	/**
	 * method to clear value
	 */
	public void clearValue(WebElement element) {
		logger.info("[COMMAND] CLEAR VALUE FOR ELEMENT :" + element.toString());
		try {
			waitForElement(element);
			element.clear();
			element.clear();
			logger.info("[RESPONSE] CLEARED VALUE FOR ELEMENT :" + element.toString());
		} catch (Exception e) {
			logger.warn("[RESPONSE] UNABLE TO CLEARED VALUE FOR ELEMENT :" + element.toString());
		}
	}


	/**
	 * method to wait for specific seconds
	 */
	public void waitUntil(int waitingSeconds) {
		logger.info("[COMMAND] WAIT FOR : " + waitingSeconds + " sec");
		try {
			Thread.sleep(waitingSeconds * 1000);
			logger.info("[RESPONSE] WAITED FOR : " + waitingSeconds + " sec");
		} catch (InterruptedException e) {
		}
	}

	/**
	 * method to get element text
	 */
	public String getElementText(WebElement element) {
		logger.info("[COMMAND] GET ELEMENT TEXT FOR ELEMENT : " + element.toString());
		String elemtText = null;
		try {
			waitForElement(element);
			elemtText = element.getText();
			logger.info("[RESPONSE] ELEMENT TEXT FOR ELEMENT : " + element.toString() + " IS : " + elemtText);
			return elemtText;
		} catch (Exception e) {
			logger.error("[RESPONSE] UNABLE TO GET ELEMENT TEXT FOR ELEMENT : " + element.toString());
			return elemtText;
		}
	}

	/**
	 * method to generate random identifier
	 */
	public String randomIdentifier() {
		logger.info("[COMMAND] GENERATE RANDOM IDENTIFIER");
		final String lexicon = "abcdefghijklmnopqrstuvwxyz";
		final java.util.Random rand = new java.util.Random();
		final Set<String> identifiers = new HashSet<String>();
		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(5) + 5;
			for (int i = 0; i < length; i++) {
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			}
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		logger.info("[RESPONSE] GENERATED RANDOM IDENTIFIER AS : " + builder.toString());
		return builder.toString();
	}

	/**
	 * method to get test data
	 */
	public String getTestData(String key) {
		logger.info("[COMMAND] GET TEST DATA FOR :\"" + key + "\"");
		String testDataValue = null;
		try {
			FileReader reader = new FileReader("./data/testData.properties");
			Properties p = new Properties();
			p.load(reader);
			testDataValue = p.getProperty(key);
			logger.info("[RESPONSE] TEST DATA FOR :\"" + key + "\" IS " + testDataValue);
			return testDataValue;
		} catch (Exception ex) {
			logger.info("[RESPONSE] TEST DATA FOR :\"" + key + "\" IS " + testDataValue + ex);
			return testDataValue;
		}
	}

	/**
	 * method to Set test data
	 */
	public void setTestData(String key, String data) {
		logger.info("[COMMAND] SET TEST DATA FOR KEY :\"" + key + "\" AND DATA : \"" + data + "\"");
		FileOutputStream fileOut = null;
		FileInputStream fileIn = null;
		try {
			Properties configProperty = new Properties();
			File file = new File("./data/testData.properties");
			fileIn = new FileInputStream(file);
			configProperty.load(fileIn);
			configProperty.setProperty(key, data);
			fileOut = new FileOutputStream(file);
			configProperty.store(fileOut, "Test Data");
			logger.info("[RESPONSE] SET TEST DATA FOR KEY :\"" + key + "\" AND DATA : \"" + data + "\"");
		} catch (Exception ex) {
			logger.warn("[RESPONSE] FAILED TO SET TEST DATA FOR KEY :\"" + key + "\" AND DATA : \"" + data + "\"" + ex);
		} finally {
			try {
				fileOut.close();
			} catch (IOException ex) {
				logger.error("", ex);
			}
		}
	}

	/**
	 * method to get message data
	 */
	public String getMessageData(String key) {
		logger.info("[COMMAND] GET MESSAGE DATA FOR :\"" + key + "\"");
		String messageData = null;
		try {
			FileReader reader = new FileReader("./data/messages.properties");
			Properties p = new Properties();
			p.load(reader);
			messageData = p.getProperty(key);
			logger.info("[RESPONSE] TEST DATA FOR :\"" + key + "\" IS " + messageData);
			return messageData;
		} catch (Exception ex) {
			logger.info("[RESPONSE] TEST DATA FOR :\"" + key + "\" IS " + messageData + ex);
			return messageData;
		}
	}

	/**
	 * method to set message data
	 */
	public void setMessageData(String key, String data) {
		logger.info("[COMMAND] SET MESSAGE DATA FOR KEY :\"" + key + "\" AND DATA : \"" + data + "\"");
		FileOutputStream fileOut = null;
		FileInputStream fileIn = null;
		try {
			Properties configProperty = new Properties();
			File file = new File("./data/messages.properties");
			fileIn = new FileInputStream(file);
			configProperty.load(fileIn);
			configProperty.setProperty(key, data);
			fileOut = new FileOutputStream(file);
			configProperty.store(fileOut, "Message Data");
			logger.info("[RESPONSE] SET MESSAGE DATA FOR KEY :\"" + key + "\" AND DATA : \"" + data + "\"");
		} catch (Exception ex) {
			logger.warn("[RESPONSE] FAILED TO SET TEST DATA FOR KEY :\"" + key + "\" AND DATA : \"" + data + "\"" + ex);
		} finally {
			try {
				fileOut.close();
			} catch (IOException ex) {
				logger.error("", ex);
			}
		}
	}

	
	/**
	 * method to hide keyboard
	 */
	public void hideDeviceKeyboard() {
		logger.info("[COMMAND] HIDE DEVICE KEYBOARD");
		try {
			if (appiumDriver.isKeyboardShown()) {
				appiumDriver.hideKeyboard();
				logger.info("[RESPONSE] HIDE DEVICE KEYBOARD");
			}
		} catch (NotFoundException e) {
			logger.warn("[RESPONSE] DEVICE KEYBOARD NOT VISIBLE");
		}
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}

	
}
