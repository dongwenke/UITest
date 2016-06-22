package com.dzf.test.model;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import com.dzf.test.util.WebDriverFactory;

public class WebDriverModel {

	public static final WebDriver driver = WebDriverFactory.create();

	public WebDriverModel() {
		init();
	}

	public void init() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);// 识别元素时的超时时间
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);// 页面加载时的超时时间
		driver.manage().timeouts().setScriptTimeout(6, TimeUnit.SECONDS);// 异步脚本的超时时间
		driver.manage().window().maximize();
	}

}
