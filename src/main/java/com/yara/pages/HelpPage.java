package com.yara.pages;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HelpPage extends BasePage<HelpPage> {

	final static Logger logger = LoggerFactory.getLogger(HelpPage.class);
	
	@AndroidFindBy(accessibility = "header_slide1")
	private MobileElement headerLocator;
	
	@AndroidFindBy(accessibility = "button_skipButton")
	private MobileElement helpScreenSkipBtn;

	public HelpPage(AndroidDriver<MobileElement> appiumDriver) {
		super(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
	}
	
	public SelectLeafColourPage clickOnSkipButton() {
		clickOnElement(helpScreenSkipBtn);
		return new SelectLeafColourPage(appiumDriver);
	}

}
