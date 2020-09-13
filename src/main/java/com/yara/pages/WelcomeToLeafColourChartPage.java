package com.yara.pages;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WelcomeToLeafColourChartPage extends BasePage<WelcomeToLeafColourChartPage> {
	final static Logger logger = LoggerFactory.getLogger(WelcomeToLeafColourChartPage.class);

	@AndroidFindBy(accessibility = "text_welcome_to")
	private MobileElement welcomeToHeadingTxt;

	@AndroidFindBy(accessibility = "txtLeafColorChart")
	private MobileElement leafColourChartHeadingTxt;

	@AndroidFindBy(accessibility = "txt_smart_way_to_measure")
	private MobileElement leafColourChartDescriptionTxt;

	@AndroidFindBy(accessibility = "btnText_welcome_screen_continue")
	private MobileElement AgreenAndContinueBtn;

	// By tapping the button you agree to the Yara Digital Farming Terms. To find
	// out what personal data we collect and how we use it, please visit our Digital
	// Farming Privacy Policy
	@AndroidFindBy(xpath = "//*[@content-desc='text_agree_and_Continue']/android.widget.TextView")
	private MobileElement privacyPolicyLinkTx;

	@AndroidFindBy(accessibility = "txt_version")
	private MobileElement applicationVersionLbl;

	public WelcomeToLeafColourChartPage(AndroidDriver<MobileElement> appiumDriver) {
		super(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
	}

	public void verifyLeafColourChartScreenTitle() {
		Assert.assertEquals(getElementText(welcomeToHeadingTxt) + " " + getElementText(leafColourChartHeadingTxt),
				"Welcome to Leaf Colour Chart");
	}

	public void verifyLeafColourChartDescriptionText() {
		Assert.assertEquals(getElementText(leafColourChartDescriptionTxt),
				"A smart way to measure and track the nitrogen level in your rice field.");
	}

	public void verifyAgreeAndContinueButtonLbl() {
		Assert.assertEquals(getElementText(AgreenAndContinueBtn), "Agree and Continue");
	}

	public void verifyPrivacyPolicyLinkTxt() {
		Assert.assertEquals(getElementText(privacyPolicyLinkTx),
				"By tapping the button you agree to the Yara Digital Farming Terms. To find out what personal data we collect and how we use it, please visit our Digital Farming Privacy Policy");
	}

	public void verifyApplicationVersionLbl() {
		Assert.assertEquals(getElementText(applicationVersionLbl), "Version: 0.0.1");
	}

	public void verifyLeafColourChartScreen() {
		logger.info("User verifies Welcome to Leaf Colour Chart screen");
		verifyLeafColourChartScreenTitle();
		verifyLeafColourChartDescriptionText();
		verifyAgreeAndContinueButtonLbl();
		verifyPrivacyPolicyLinkTxt();
		verifyApplicationVersionLbl();
	}
	
	public AddNewCropPage clickOnAgreeAndContinueBtn() {
		logger.info("User select Agree and continue button");
		clickOnElement(AgreenAndContinueBtn);
		return new AddNewCropPage(appiumDriver);
	}
}
