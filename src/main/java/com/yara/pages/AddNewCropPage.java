package com.yara.pages;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AddNewCropPage extends BasePage<AddNewCropPage> {
	final static Logger logger = LoggerFactory.getLogger(AddNewCropPage.class);

	@AndroidFindBy(accessibility = "title_nav_bar_add_crop")
	private MobileElement newCropTitleNavigationBar;

	@AndroidFindBy(accessibility = "navBarLeftButton_nav_bar_add_crop")
	private MobileElement newCropSelectLanguageMenuBtn;

	@AndroidFindBy(accessibility = "image_add_crop")
	private MobileElement newCropmainImageLocator;

	@AndroidFindBy(accessibility = "image_add_crop_first")
	private MobileElement newCropFirstInstructionImageLocator;

	// Track the N level of your rice throughout the season.
	@AndroidFindBy(accessibility = "txt_add_crop_first")
	private MobileElement newCropFirstInstructionTxt;

	@AndroidFindBy(accessibility = "image_add_crop_second")
	private MobileElement newCropSecondInstructionImageLocator;

	// Regular checks improve crop health and yield.
	@AndroidFindBy(accessibility = "txt_add_crop_second")
	private MobileElement newCropSecondInstructionTxt;

	@AndroidFindBy(accessibility = "image_add_crop_third")
	private MobileElement newCropThirdInstructionImageLocator;

	// Create multiple fields with different rice varieties.
	@AndroidFindBy(accessibility = "txt_add_crop_third")
	private MobileElement newCropThirdInstructionTxt;

	@AndroidFindBy(accessibility = "btnText_add_crop")
	private MobileElement addCropBtn;

	public AddNewCropPage(AndroidDriver<MobileElement> appiumDriver) {
		super(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
	}

	public void verifyNewCropScreenTitle() {
		Assert.assertEquals(getElementText(newCropTitleNavigationBar), "New Crop");
	}

	public void verifyFirstInstructionTxt() {
		Assert.assertEquals(getElementText(newCropFirstInstructionTxt),
				"Track the N level of your rice throughout the season.");
	}

	public void verifySecondInstructionTxt() {
		Assert.assertEquals(getElementText(newCropSecondInstructionTxt),
				"Regular checks improve crop health and yield.");
	}

	public void verifyThirdInstructionTxt() {
		Assert.assertEquals(getElementText(newCropThirdInstructionTxt),
				"Create multiple fields with different rice varieties.");
	}

	public void verifyAddCropButtonLblTxt() {
		Assert.assertEquals(getElementText(addCropBtn), "Add Crop");
	}

	public void verifyNewCropScreen() {
		logger.info("User verifies new crop screen");
		verifyNewCropScreenTitle();
		verifyFirstInstructionTxt();
		verifySecondInstructionTxt();
		verifyThirdInstructionTxt();
		verifyAddCropButtonLblTxt();
	}

	public AddCropDetailsPage clickOnAddCropBtn() {
		logger.info("User tap on Add Crop button");
		clickOnElement(addCropBtn);
		return new AddCropDetailsPage(appiumDriver);
	}
}
