package com.dzf.test.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScreenShotUtil {
	public SearchContext context;

	public ScreenShotUtil(SearchContext context) {
		this.context = context;
	}

	public void takeScreenshot(String screenPath) {
		try {
			File scrFile = ((TakesScreenshot) context).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(screenPath));
		} catch (IOException e) {
			System.out.println("Screen shot error: " + screenPath);
		}
	}

	public void takeScreenshot() {
		String screenName = String.valueOf(new Date().getTime()) + ".jpg";
		File dir = new File("test-output/snapshot");
		if (!dir.exists())
			dir.mkdirs();
		String screenPath = dir.getAbsolutePath() + "/" + screenName;
		takeScreenshot(screenPath);
	}
}