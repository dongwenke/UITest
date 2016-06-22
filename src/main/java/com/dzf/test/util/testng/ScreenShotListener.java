package com.dzf.test.util.testng;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.dzf.test.model.WebDriverModel;
import com.dzf.test.util.ScreenShotUtil;

/**
 * 
 * @author 董文科
 *
 */
public class ScreenShotListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		takeScreenShot(tr);
	}

	@Override
	public void onConfigurationFailure(ITestResult tr) {
		super.onConfigurationFailure(tr);
		takeScreenShot(tr);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onConfigurationFailure(tr);
		takeScreenShot(tr);
	}

	private void takeScreenShot(ITestResult tr) {

		System.out.println("OutputDirectory"+tr.getTestContext().getOutputDirectory());
		
		String snapshotDir = tr.getTestContext().getOutputDirectory() + "/snapshot";

		WebDriver driver;

		try {
			driver = WebDriverModel.driver;
		} catch (NoClassDefFoundError e) {
			Reporter.log("未初始化driver,截屏失败！");
			return;
		}

		File dir = new File(snapshotDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String filename = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_" + tr.getName() + ".png";
		String filePath = dir.getAbsolutePath() + "\\" + filename;

		new ScreenShotUtil(driver).takeScreenshot(filePath);

		Reporter.setCurrentTestResult(tr);

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		// 这里实现把图片链接直接输出到结果文件中，通过邮件发送结果则可以直接显示图片
		Reporter.log(
				"<a href='file:\\" + filePath + "'><img src='file:\\" + filePath + "' width='250' height='150'/></a>");

	}
}