package com.yara.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SelectLeafColourPage extends BasePage<SelectLeafColourPage> {

	final static Logger logger = LoggerFactory.getLogger(SelectLeafColourPage.class);

	@AndroidFindBy(accessibility = "title_mockLeafColorScreen")
	private MobileElement selectLeafColourTitle;

	@AndroidFindBy(accessibility = "txt_leafPlace_mockLeafColorScreen")
	private MobileElement leafPlaceInstructionTxt;

	@AndroidFindBy(accessibility = "circledCheck_mockLeafColorScreen_2")
	private MobileElement leafColourOne;

	@AndroidFindBy(accessibility = "circledCheck_mockLeafColorScreen_3")
	private MobileElement leafColourTwo;

	@AndroidFindBy(accessibility = "circledCheck_mockLeafColorScreen_4")
	private MobileElement leafColourThree;

	@AndroidFindBy(accessibility = "circledCheck_mockLeafColorScreen_5")
	private MobileElement leafColourFour;

	@AndroidFindBy(accessibility = "txt_leafColor_mockLeafColorScreen")
	private MobileElement leafColourInstructionTxt;

	@AndroidFindBy(accessibility = "circleCheckedImg")
	private MobileElement LeafColourCheckedCircleLocator;

	@AndroidFindBy(accessibility = "btnText_btnNextLeaf")
	private MobileElement nextLeafBtn;

	@AndroidFindBy(accessibility = "btnText_btnDone")
	private MobileElement doneBtn;
	
	@AndroidFindBy(accessibility = "txt_almostdone")
	private MobileElement samplingDoneLocator;

	public SelectLeafColourPage(AndroidDriver<MobileElement> appiumDriver) {
		super(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
	}

	public void verifySelectLeafColourTitle(String str) {
		Assert.assertEquals(getElementText(selectLeafColourTitle), str);
	}

	public void selectLeafColour() {
		logger.info("User select 10 times the leave colour");
		int i = 0;
		List<MobileElement> leafColourElement = Arrays.asList(leafColourOne, leafColourTwo, leafColourThree,
				leafColourFour);
		Random randomGenerator = new Random();
		for (i = 0; i < 10; i++) {
			String selectLeafColourTitle = String.format("Leaf %d Of 10", (i + 1));
			verifySelectLeafColourTitle(selectLeafColourTitle);
			clickOnElement(leafColourElement.get(randomGenerator.nextInt(4)));
			if (i == 9) {
				break;
			}
			clickOnElement(nextLeafBtn);
		}
		clickOnDoneButton();
	}

	public void clickOnDoneButton() {
		clickOnElement(doneBtn);
	}
	
	public void verifyLeavesVarietySampled() {
		logger.info("User verifies for leaves sample");
		Assert.assertEquals(samplingDoneLocator.getText(), "Thank You");
	}

}