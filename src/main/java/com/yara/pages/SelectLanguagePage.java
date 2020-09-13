package com.yara.pages;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.yara.tests.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SelectLanguagePage extends BasePage<SelectLanguagePage> {
	final static Logger logger = LoggerFactory.getLogger(SelectLanguagePage.class);

	@AndroidFindBy(accessibility = "title_nav_bar_select_language")
	private MobileElement selectLanguageTitleNavigationBar;

	@AndroidFindBy(accessibility = "please_sel_language")
	private MobileElement selectLanguageLbl;

	@AndroidFindBy(accessibility = "title_select_language_en")
	private MobileElement selectLanguageEnglishLbl;

	@AndroidFindBy(accessibility = "btnText_Select_select_language_en")
	private MobileElement selectLanguageEnglishBtnTxt;

	@AndroidFindBy(accessibility = "title_select_language_hi")
	private MobileElement selectLanguageHindiLbl;

	@AndroidFindBy(accessibility = "subTitle_select_language_hi")
	private MobileElement selectLanguageHindiSubTitleLbl;

	@AndroidFindBy(accessibility = "btnText_Select_select_language_hi")
	private MobileElement selectLanguageHindiBtnTxt;

	@AndroidFindBy(accessibility = "title_select_language_ta")
	private MobileElement selectLanguageTamilLbl;

	@AndroidFindBy(accessibility = "subTitle_select_language_ta")
	private MobileElement selectLanguageTamilSubTitleLbl;

	@AndroidFindBy(accessibility = "btnText_Select_select_language_ta")
	private MobileElement selectLanguageTamilBtnTxt;

	public SelectLanguagePage(AndroidDriver<MobileElement> appiumDriver) {
		super(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
	}

	public void verifySelectLanuageLbl() {
		Assert.assertEquals(getElementText(selectLanguageTitleNavigationBar), "Welcome To Leaf Colour Chart");
	}

	public void verifySelectLanguageTitleLbl() {
		Assert.assertEquals(getElementText(selectLanguageLbl), "PLEASE SELECT YOUR LANGUAGE");
	}

	public void verifySelectLanguageEnglishLbl() {
		Assert.assertEquals(getElementText(selectLanguageEnglishLbl), "English");
	}

	public WelcomeToLeafColourChartPage clickToSelectEnglishLanguage() {
		logger.info("User Select English lanuage");
		clickOnElement(selectLanguageEnglishBtnTxt);
		return new WelcomeToLeafColourChartPage(appiumDriver);
	}
	
	public void verifySelectLanguageScreen() {
		logger.info("User verify Select Language Screen");
		verifySelectLanuageLbl();
	}

}
