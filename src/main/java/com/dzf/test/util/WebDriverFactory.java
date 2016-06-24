package com.dzf.test.util;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WebDriverFactory {

	// 依据配置参数启动默认浏览器
	public static WebDriver create() {
		return create(PropertyUtil.prop.getProperty("browser"));
	}

	// 启动指定类型浏览器
	public static WebDriver create(String browserName) {
		WebDriver driver = null;

		if (browserName == null) {
			browserName = "";
		}

		browserName = browserName.trim().toLowerCase();

		System.out.println("打开浏览器：" + browserName);

		switch (browserName) {
		case "firefox":
			driver = createFirefox();
			break;
		case "chrome":
			driver = createChrome();
			break;
		case "ie":
			driver = createIE();
			break;
		case "htmlunit":
			driver = creatHtmlUnit();
			break;
		case "phantomjs":
			driver = creatPhantomJS();
			break;
		default:
			driver = createFirefox();
			break;

		}

		return driver;
	}

	// 启动firefox
	public static WebDriver createFirefox() {

		String binary = PropertyUtil.prop.getProperty("firefoxBinary");
		if (binary != null && !binary.equals("")) {
			System.setProperty("webdriver.firefox.bin", binary);
		}

		FirefoxProfile profile = null;

		String profileDir = PropertyUtil.prop.getProperty("firefoxProfileDir");
		if (profileDir != null && !profileDir.equals("")) {
			profile = new FirefoxProfile(new File(profileDir));
		}

		return new FirefoxDriver(profile);
	}

	public static WebDriver createChrome() {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver_v2.15.322448.exe");
		return new ChromeDriver();
	}

	public static WebDriver createIE() {
		System.setProperty("webdriver.ie.driver", "driver/IEDriverServer.exe");
		// 设置IE启动时的参数,去掉保护模式
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		return new InternetExplorerDriver(ieCapabilities);
	}

	public static WebDriver creatHtmlUnit() {
		return new HtmlUnitDriver();
	}

	public static WebDriver creatPhantomJS() {
		return new PhantomJSDriver();
	}

}
