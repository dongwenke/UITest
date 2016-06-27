package com.dzf.test.model;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.dzf.test.util.PropertyUtil;
import com.dzf.test.util.WebDriverFactory;

public class WebDriverModel {

	public static final WebDriver driver = WebDriverFactory.create();

	public WebDriverModel() {
		init();
	}

	public void init() {
		driver.manage().timeouts().implicitlyWait(Integer.valueOf(PropertyUtil.prop.getProperty("implicitlyWait")),
				TimeUnit.SECONDS);// 识别元素时的超时时间
		driver.manage().timeouts().pageLoadTimeout(Integer.valueOf(PropertyUtil.prop.getProperty("pageLoadTimeout")),
				TimeUnit.SECONDS);// 页面加载时的超时时间
		driver.manage().timeouts().setScriptTimeout(Integer.valueOf(PropertyUtil.prop.getProperty("setScriptTimeout")),
				TimeUnit.SECONDS);// 异步脚本的超时时间
		driver.manage().window().maximize();
	}

}
