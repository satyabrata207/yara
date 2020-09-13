package com.yara.tests;

import org.testng.annotations.Test;

import com.yara.pages.SelectLanguagePage;

public class LeavesSamplingTest extends BaseTest {
	
	@Test
	public void samplingLeavesVarietyTest() {
		selectLanguagePage = new SelectLanguagePage(appiumDriver);
		selectLanguagePage.verifySelectLanguageScreen();
		welcomeToLeafColourChartPage = selectLanguagePage.clickToSelectEnglishLanguage();
		welcomeToLeafColourChartPage.verifyLeafColourChartScreen();
		addNewCropPage = welcomeToLeafColourChartPage.clickOnAgreeAndContinueBtn();
		addNewCropPage.verifyNewCropScreen();
		addCropDetailsPage = addNewCropPage.clickOnAddCropBtn();
		addCropDetailsPage.verifyAddCropScreenTitle();
		addCropDetailsPage.addFarmName();
		instructionsPage = addCropDetailsPage.clickOnSaveBtn();
		selectLeafColourPage = instructionsPage.skipInstruction();
		selectLeafColourPage.selectLeafColour();
		selectLeafColourPage.verifyLeavesVarietySampled();
	}

}
