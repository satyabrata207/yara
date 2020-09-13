package com.yara.pages;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AddCropDetailsPage extends BasePage<AddCropDetailsPage> {

	final static Logger logger = LoggerFactory.getLogger(AddCropDetailsPage.class);

	@AndroidFindBy(accessibility = "title_nbNavBar")
	private MobileElement addCropDetailsNavigationBar;

	@AndroidFindBy(accessibility = "subtitle_nbNavBar")
	private MobileElement addCropDetailsSubTitleNavigationBar;

	@AndroidFindBy(xpath = "//*[@content-desc='textInput_txtFarmName']/android.view.ViewGroup[1]/android.widget.TextView")
	private MobileElement farmNameInputTxtBox;

	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"textInput_txtFarmName\"]")
	private MobileElement farmNameInputTxtBoxLbl;

	@AndroidFindBy(accessibility = "roundedButtonText_nbNavBar")
	private MobileElement addCropSaveBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"roundedButtonText_rblRiceVarieties\"]")
	private List<MobileElement> listOfRiceVarieties;

	@AndroidFindBy(xpath = "//*[@content-desc='roundedButtonText_rblGrowingPeriods']")
	private List<MobileElement> listOfGrowingPeriod;

	@AndroidFindBy(xpath = "(//*[@content-desc='roundedButtonText_rblFarmSizes']")
	private List<MobileElement> listOfFarmSize;

	@AndroidFindBy(accessibility = "txtTitleList_rblGrowingPeriods")
	private MobileElement growingPeriodsLbl;

	@AndroidFindBy(accessibility = "txtTitleList_rblFarmSizes")
	private MobileElement FarmSizeLbl;

	@AndroidFindBy(accessibility = "txtSowingDateTitle_dtSowingdate")
	private MobileElement sowingDateLbl;

	public AddCropDetailsPage(AndroidDriver<MobileElement> appiumDriver) {
		super(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
	}

	public void verifyAddCropScreenTitle() {
		logger.info("User verifies Add Crop screen");
		Assert.assertEquals(getElementText(addCropDetailsNavigationBar), "Add Crop");
	}

	public void addFarmName() {
		logger.info("User enters mandatory fields to add Crop");
		Assert.assertEquals(getElementText(farmNameInputTxtBox), "Name of your farm");
		long randomNumber = Math.round(Math.random() * 10000);
		String farmName = "Farm_" + randomNumber;
		setTestData("farmName", farmName);
		enterTextField(farmNameInputTxtBoxLbl, farmName);
	}

	public InstructionsPage clickOnSaveBtn() {
		logger.info("User tap on save button");
		clickOnElement(addCropSaveBtn);
		return new InstructionsPage(appiumDriver);
	}

	public void addCropDetails(String riceVarity, String growingPeriod, String farmSize) {
		addFarmName();
		hideDeviceKeyboard();
		waitForElement(sowingDateLbl);
		selectRiceVariety(riceVarity);
		selectGrowingPeriod(growingPeriod);
		SelectFarmSize(farmSize);
	}

	public void selectRiceVariety(String riceVarity) {
		for (int i = 0; i < listOfRiceVarieties.size(); i++) {
			if (listOfRiceVarieties.get(i).getText().equals(riceVarity)) {
				clickOnElement(listOfRiceVarieties.get(i));
				break;
			}
		}
	}

	public void selectGrowingPeriod(String growingPeriod) {

		for (int i = 0; i < listOfGrowingPeriod.size(); i++) {
			if (listOfGrowingPeriod.get(i).getText().equals(growingPeriod)) {
				clickOnElement(listOfGrowingPeriod.get(i));
				break;
			}
		}

	}

	public void SelectFarmSize(String farmSize) {
		for (int i = 0; i < listOfFarmSize.size(); i++) {
			if (listOfFarmSize.get(i).getText().equals(farmSize)) {
				clickOnElement(listOfFarmSize.get(i));
				break;
			}
		}
	}
}
