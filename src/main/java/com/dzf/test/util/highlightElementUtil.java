package com.dzf.test.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;

public class highlightElementUtil {
	// 高亮元素
	public static void highlightElement(WebElement webElement) {
		
		for (int i = 0; i < 3; i++) {
			WrapsDriver wrappedElement = (WrapsDriver) webElement;
			JavascriptExecutor driver = (JavascriptExecutor) wrappedElement.getWrappedDriver();
			// 为元素设置style来高亮
			try {
				driver.executeScript("arguments[0].setAttribute('style',arguments[1]);", webElement,
						"color: green; border: 2px solid red;");
				// 取消高亮将style清掉
				Thread.sleep(70);
				driver.executeScript("arguments[0].setAttribute('style',arguments[1]);", webElement, "");
				Thread.sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
