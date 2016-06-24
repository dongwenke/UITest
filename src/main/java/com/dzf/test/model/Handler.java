package com.dzf.test.model;

import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Reporter;

import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.StringUtil;
import com.dzf.test.util.highlightElementUtil;


/**
 * 操作页面元素的类，所有页面都继承该类
 */
public class Handler extends WebDriverModel implements IHandler, ILogUtil {
	public Page page;

	@Override
	public void open() {
		Reporter.log("打开页面：" + page.getUrl());
		driver.get(page.getUrl());
	}

	@Override
	public void quit() {
		Reporter.log("关闭浏览器!");
		driver.quit();
	}

	@Override
	public WebElement getWebElement(String elementName) throws MyException {
		try {
			return driver.findElement(by(getElement(elementName)));
		} catch (WebDriverException e) {
			throw new MyException("元素未找到：" + elementName, e);
		}
	}

	@Override
	public WebElement getWebElement(By locator) throws MyException {
		try {
			// highlightElementUtil.highlightElement(element);
			return driver.findElement(locator);
		} catch (WebDriverException e) {
			Reporter.log("元素未找到：" + locator);
			throw new MyException("元素未找到：" + locator, e);
		}
	}

	@Override
	public WebElement getWebElement(WebElement webElement, By locator) throws MyException {
		try {
			return webElement.findElement(locator);
		} catch (WebDriverException e) {
			throw new MyException("元素未找到：" + locator, e);
		}
	}

	@Override
	public List<WebElement> getWebElements(WebElement webElement, By locator) throws MyException {
		try {
			return webElement.findElements(locator);
		} catch (WebDriverException e) {
			// Reporter.log("元素未找到：" + locator);
			throw new MyException("元素未找到：" + locator, e);
		}
	}

	@Override
	public List<WebElement> getWebElements(String elementName) throws MyException {
		try {
			return driver.findElements(by(getElement(elementName)));
		} catch (WebDriverException | NullPointerException e) {
			// Reporter.log("【" + elementName + "】 未找到！");
			throw new MyException("元素未找到：" + elementName, e);
		}
	}

	@Override
	public List<WebElement> getWebElements(By locator) throws MyException {
		try {
			return driver.findElements(locator);
		} catch (WebDriverException e) {
			// Reporter.log("【" + locator + "】 未找到！");
			throw new MyException("元素未找到：" + locator, e);
		}

	}

	@Override
	public By by(Element element) {
		By by = null;
		if (element != null) {
			try {
				Class<By> classType = By.class;
				Method method = classType.getMethod(element.getMethod(), String.class);
				by = (By) method.invoke(classType, element.getValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return by;
	}

	@Override
	public void actionInput(String elementName, String string) throws MyException {
		try {
			Reporter.log(elementName + ":输入：" + string);

			WebElement inputElement = getWebElement(elementName);

			inputElement.clear();

			Actions a = new Actions(driver);
			for (String s : StringUtil.stringToStringArray(string)) {
				a.sendKeys(s);
				try {
					Thread.sleep(70);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			a.perform();

		} catch (WebDriverException e) {
			Reporter.log(elementName + ":输入：" + string + "失败！");
			throw new MyException("【" + elementName + "】 输入失败！", e);
		}
	}

	@Override
	public void actionInput(WebElement webElement, String inputText) throws MyException {
		try {
			Reporter.log("输入：" + inputText);

			webElement.clear();
			new Actions(driver).sendKeys(webElement, inputText).perform();

		} catch (WebDriverException e) {
			throw new MyException("【" + webElement + "】 输入失败！", e);
		}

	}

	@Override
	public void input(String elementName, String string) throws MyException {
		try {
			Reporter.log(elementName + ":输入：" + string);
			WebElement element = getWebElement(elementName);

			element.clear();

			for (String s : StringUtil.stringToStringArray(string)) {
				element.sendKeys(s);
				try {
					Thread.sleep(70);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (WebDriverException e) {
			Reporter.log(elementName + ":输入：" + string + "失败！");
			throw new MyException("【" + elementName + "】 输入失败！", e);
		}
	}

	@Override
	public void input(String elementName, Keys key) throws MyException {
		try {
			getWebElement(elementName).sendKeys(key);
		} catch (WebDriverException e) {
			throw new MyException("【" + elementName + "】 输入失败！", e);
		}
	}

	@Override
	public void input(WebElement webElement, String inputText) throws MyException {
		try {
			Reporter.log("输入：" + inputText);

			webElement.clear();
			for (String s : StringUtil.stringToStringArray(inputText)) {
				webElement.sendKeys(s);
				try {
					Thread.sleep(70);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (WebDriverException e) {
			throw new MyException("【" + webElement + "】 输入失败！", e);
		}

	}

	@Override
	public void input(WebElement webElement, Keys key) throws MyException {
		try {
			webElement.sendKeys(key);
		} catch (WebDriverException e) {
			throw new MyException("【" + webElement + "】 输入失败！", e);
		}
	}

	@Override
	public void click(String elementName) throws MyException {
		try {
			Thread.sleep(700);

			Reporter.log("点击：" + elementName);
			// WebElement webElement = new WebDriverWait(driver, 2)
			// .until(ExpectedConditions.elementToBeClickable(by(page.getElement(elementName))));
			WebElement webElement = getWebElement(elementName);

			try {
				webElement.click();
			} catch (ElementNotVisibleException e) {
				scrollTo(webElement);
				webElement.click();
			}

			// highlightElementUtil.highlightElement(element);
			Thread.sleep(500);
		} catch (WebDriverException e) {
			Reporter.log("点击：" + elementName + "失败！");
			throw new MyException("【" + elementName + "】 点击失败！", e);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void click(WebElement webElement) throws MyException {
		try {

			Thread.sleep(700);

			String elementText = webElement.getText();
			if (elementText != null && !elementText.equals("")) {
				Reporter.log("点击：" + elementText.replaceAll("\n", "_"));
			}

			// highlightElementUtil.highlightElement(webElement);

			try {
				webElement.click();
			} catch (ElementNotVisibleException e) {
				scrollTo(webElement);
				webElement.click();
			}

			Thread.sleep(500);

		} catch (WebDriverException e) {

			Reporter.log("【" + webElement.toString() + "】点击失败！");
			throw new MyException("【" + webElement.toString() + "】点击失败！", e);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public WebElement waitToDisplayElement(String elementName) throws MyException {
		try {
			final By locator = by(getElement(elementName));
			return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (WebDriverException e) {
			throw new MyException(elementName + "没有显示！", e);
		}
	}

	@Override
	public WebElement waitToDisplayElement(By locator) throws MyException {
		try {
			return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (WebDriverException e) {
			throw new MyException(locator + "没有显示！", e);
		}
	}

	@Override
	public boolean waitToDisplayed(String elementName) throws MyException {
		try {
			By locator = by(getElement(elementName));
			return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(locator))
					.isDisplayed();
		} catch (WebDriverException e) {
			throw new MyException(elementName + "没有显示！", e);
		}

	}

	@Override
	public boolean waitToDisplayed(WebElement webElement) throws MyException {
		try {
			return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
		} catch (WebDriverException e) {
			throw new MyException(webElement + "没有显示！", e);
		}
	}

	@Override
	public boolean waitToDisplayed(By locator) throws MyException {
		try {
			return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(locator))
					.isDisplayed();
		} catch (WebDriverException e) {
			throw new MyException(locator + "没有显示！", e);
		}
	}

	@Override
	public boolean waitToBeClickable(String elementName) throws MyException {
		try {
			final By locator = by(getElement(elementName));
			return new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {

				ExpectedCondition<WebElement> visibilityOfElementLocated = ExpectedConditions
						.visibilityOfElementLocated(locator);

				@Override
				public Boolean apply(WebDriver driver) {
					WebElement element = visibilityOfElementLocated.apply(driver);
					try {
						if (element != null) {
							return element.isEnabled();
						} else {
							return false;
						}
					} catch (StaleElementReferenceException e) {
						return false;
					}
				}
			});

		} catch (WebDriverException e) {
			throw new MyException(elementName + "没有显示！", e);
		}
	}

	@Override
	public boolean waitToBeClickable(final WebElement webElement) throws MyException {
		try {
			return new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {

				ExpectedCondition<WebElement> visibilityOfElement = ExpectedConditions.visibilityOf(webElement);

				@Override
				public Boolean apply(WebDriver driver) {
					WebElement element = visibilityOfElement.apply(driver);
					try {
						if (element != null) {
							return element.isEnabled();
						} else {
							return false;
						}
					} catch (StaleElementReferenceException e) {
						return false;
					}
				}
			});

		} catch (WebDriverException e) {

			throw new MyException(webElement + "没有显示！", e);
		}
	}

	@Override
	public boolean waitToBeClickable(final By locator) throws MyException {
		try {
			return new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {

				ExpectedCondition<WebElement> visibilityOfElementLocated = ExpectedConditions
						.visibilityOfElementLocated(locator);

				@Override
				public Boolean apply(WebDriver driver) {
					WebElement element = visibilityOfElementLocated.apply(driver);
					try {
						if (element != null) {
							return element.isEnabled();
						} else {
							return false;
						}
					} catch (StaleElementReferenceException e) {
						return false;
					}
				}

			});
		} catch (WebDriverException e) {

			throw new MyException(locator + "没有显示！", e);
		}
	}

	@Override
	public boolean isDisplayed(String elementName) throws MyException {
		try {
			return new WebDriverWait(driver, 1)
					.until(ExpectedConditions.visibilityOfElementLocated(by(page.getElement(elementName))))
					.isDisplayed();

		} catch (WebDriverException e) {
			return false;
		}
	}

	@Override
	public boolean isDisplayed(WebElement webElement) {
		return webElement.isDisplayed();
	}

	@Override
	public boolean isDisplayed(By locator) throws MyException {
		try {
			return new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOfElementLocated(locator))
					.isDisplayed();
		} catch (WebDriverException e) {
			return false;
		}
	}

	@Override
	public boolean isSelected(By locator) throws MyException {
		return getWebElement(locator).isSelected();
	}

	@Override
	public boolean isSelected(String elementName) throws MyException {
		return getWebElement(elementName).isSelected();
	}

	@Override
	public boolean isSelected(WebElement webElement) throws MyException {
		return webElement.isSelected();
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	@Override
	public String getText(String elementName) throws MyException {
		try {
			return getText(getWebElement(elementName));
		} catch (WebDriverException e) {
			// Reporter.log("获取【" + elementName + "】的文本失败");
			throw new MyException("获取【" + elementName + "】的文本失败", e);
		}
	}

	public String getText(WebElement webElement) {
		return webElement.getText();
	}

	@Override
	public void mouseMoveTo(String elementName) throws MyException {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(getWebElement(elementName)).perform();
		} catch (WebDriverException e) {
			Reporter.log("鼠标移动到：" + elementName + "失败");
			throw new MyException("鼠标移动到：" + elementName + "失败！", e);
		}

	}

	@Override
	public void scrollTo(int height) {
		try {
			String setscroll = "document.documentElement.scrollTop=" + height;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(setscroll);
		} catch (WebDriverException e) {
			Reporter.log("Fail to set the scroll.");
			throw e;
		}
	}

	@Override
	public void scrollTo(WebElement webElement) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", webElement);
	}

	@Override
	public void switchToDefaultContent() throws WebDriverException {
		driver.switchTo().defaultContent();
	}

	@Override
	public void switchToFrame(WebElement frameElement) throws MyException {
		try {
			driver.switchTo().frame(frameElement);
		} catch (WebDriverException e) {
			Reporter.log("切换到frame：" + frameElement + "失败！");
			throw new MyException("切换到frame：" + frameElement + "失败！", e);
		}
	}

	@Override
	public void switchToFrame(String nameOrId) throws MyException {
		try {
			driver.switchTo().frame(nameOrId);
		} catch (WebDriverException e) {
			Reporter.log("切换到frame：" + nameOrId + "失败！");
			throw new MyException("切换到frame：" + nameOrId + "失败！", e);
		}

	}

	@Override
	public void switchToFrame(int index) throws MyException {
		try {
			driver.switchTo().frame(index);
		} catch (WebDriverException e) {
			Reporter.log("切换到frame：" + index + "失败！");
			throw new MyException("切换到frame：" + index + "失败！", e);
		}
	}

	@Override
	public boolean isElementExsit(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			return null != element;
		} catch (NoSuchElementException e) {
			System.out.println("Element:" + locator.toString() + " is not exsit!");
			return false;
		}
	}

	public Element getElement(String elementName) {
		Element element = page.getElement(elementName);
		if (element == null) {
			Reporter.log(elementName + "不存在！");
			throw new NullPointerException(elementName + "不存在！");
		}
		return element;
	}
}
