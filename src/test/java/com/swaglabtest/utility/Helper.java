package com.swaglabtest.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper {

	public static String CaptureScreenShot(WebDriver driver, String PageName) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + timeStamp + "_" + PageName + ".png";// "_login.png";

		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File(screenshotPath));
			// System.out.println("✅ Screenshot captured: " + screenshotPath);
		} catch (Exception e) {
			// System.out.println("❌ Could not capture the screenshot: " + e.getMessage());
		}

		// ✅ Always return the actual path
		return screenshotPath;
	}

	public static String getCurrentDateTime() {
		DateFormat currentDateformat = new SimpleDateFormat("MM_dd_yyyy HH_mm_ss");
		Date crrent = new Date();
		return currentDateformat.format(crrent);

	}

	public void HandleFrames(WebDriver driver) {

	}

	public void HandleWindows(WebDriver driver) {

	}

	public void HandleJavaExecutor(WebDriver driver) {

	}
}
