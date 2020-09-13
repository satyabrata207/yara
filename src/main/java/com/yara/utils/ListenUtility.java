package com.yara.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.yara.tests.BaseTest;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

public class ListenUtility extends ExtentITestListenerClassAdapter {

    private static final Logger logger = LogManager.getLogger(ListenUtility.class);

    @Override
    public void onStart(ITestContext context) {
        super.onStart(context);
        logger.info("\n\n**********STARTING SUITE : " + context.getSuite().getName() + " is being initiated.**********\n");
    }

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        logger.info("\n\nSTARTING TEST : " + result.getName() + " is being initiated.\n");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        logger.info("\n\nPASSED : " + result.getName() + " has been passed!\n");
    }

    @Override
	public void onTestFailure(ITestResult result) {
		System.out.println("\n***** FAILED : " + result.getName() + " test has failed *****");
		String timestamp = new SimpleDateFormat("dd_MMM_yyyy_HH_mm").format(new Date());
		String methodName = result.getMethod().getMethodName();
		
		String filePath = System.getProperty("user.dir") + "/test-output/ScreenShot";
		File screenshotsDir = new File(filePath);
		if (!screenshotsDir.exists()) {
			screenshotsDir.mkdirs();
		}
		String fileName = filePath + File.separator + timestamp + "_" + methodName + ".jpg";
		try {
			File scrFile = ((TakesScreenshot) BaseTest.appiumDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(fileName));
			System.out.println(
					"***Placed screen shot in " + fileName + " ***\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest(result).skip(result.getThrowable());
        logger.fatal("\n\nSKIPPED : " + result.getName() + " has been skipped!", result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        super.onFinish(context);
        logger.info("\n\nFINISHED SUITE : " + context.getSuite().getName() + " is being terminated.\n");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        super.onTestFailedButWithinSuccessPercentage(result);
    }

}
