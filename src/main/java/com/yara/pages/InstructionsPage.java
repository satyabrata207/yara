package com.yara.pages;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class InstructionsPage extends BasePage<InstructionsPage>{
	
	final static Logger logger = LoggerFactory.getLogger(InstructionsPage.class);
	
	@AndroidFindBy(accessibility = "button_skipButton")
	private MobileElement instructionsPageSkipBtn;
	

	public InstructionsPage(AndroidDriver<MobileElement> appiumDriver) {
		super(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
	}
	
	public SelectLeafColourPage skipInstruction() {
		logger.info("User skips the instructions");
		clickOnElement(instructionsPageSkipBtn);
		return new SelectLeafColourPage(appiumDriver);
	}
	

}
