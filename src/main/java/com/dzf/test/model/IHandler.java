package com.dzf.test.model;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.dzf.test.util.MyException;

/**
 * @author Administrator 操作页面元素的接口，定义操作元素的规范
 */
public interface IHandler {

	public static final String templateXml = "./config/page/template.xml";

	/**
	 * 打开页面，如果浏览器未打开，将会先打开浏览器再打开页面
	 */
	public void open();

	/**
	 * 关闭浏览器，同时会关闭所有页面
	 */
	public void quit();

	/**
	 * 获取当前页面的标题
	 * 
	 * @return 返回当前页面的标题
	 */
	public String getTitle();

	/**
	 * 获取元素显示的文本
	 * 
	 * @param elementName
	 * @return 配置文件中的元素name
	 */
	public String getText(String elementName) throws MyException;

	/**
	 * 获取元素显示的文本
	 * @param webElement
	 * @return 元素显示的文本
	 * @throws MyException
	 */
	public String getText(WebElement webElement) throws MyException;

	/**
	 * 通过元素在配置文件中的name获取元素
	 * @param name
	 *            配置文件中的元素name
	 * @return WebElement
	 * @throws MyException
	 */
	public WebElement getWebElement(String name) throws MyException;

	/**
	 * 通过元素的定位方式和值获取元素
	 * @param locator
	 * @return	返回WebElement元素
	 * @throws MyException
	 */
	public WebElement getWebElement(By locator) throws MyException;

	/**
	 * 通过元素在配置文件中的name获取所有符合条件的元素列表
	 * @param name
	 * @return 元素列表
	 * @throws MyException
	 */
	public List<WebElement> getWebElements(String name) throws MyException;

	/**
	 * 通过元素的定位方式和值获取所有符合条件的元素列表
	 * @param locator
	 * @return 元素列表
	 * @throws MyException
	 */
	public List<WebElement> getWebElements(By locator) throws MyException;

	/**
	 * 将Element元素反射为By对象
	 * @param element
	 * @return	元素的位置对象By locator
	 * @throws Exception
	 */
	public By by(Element element) throws Exception;

	/**
	 * 将字符输入指定的元素中
	 * @param elementName
	 * @param inputText
	 * @throws MyException
	 */
	public void input(String elementName, String inputText) throws MyException;

	/**
	 * 将字符输入指定的元素中
	 * @param webElement
	 * @param inputText
	 * @throws MyException
	 */
	public void input(WebElement webElement, String inputText) throws MyException;

	/**
	 * 将按键输入指定的元素中
	 * @param elementName
	 * @param key
	 * @throws MyException
	 */
	public void input(String elementName, Keys key) throws MyException;

	/**
	 * 将按键输入指定的元素中
	 * @param webElement
	 * @param key
	 * @throws MyException
	 */
	public void input(WebElement webElement, Keys key) throws MyException;

	/**
	 * @param elementName
	 * @throws MyException
	 */
	public void click(String elementName) throws MyException;

	/**
	 * @param webElement
	 * @throws MyException
	 */
	public void click(WebElement webElement) throws MyException;

	/**
	 * @param elementName
	 * @return
	 * @throws MyException
	 */
	public boolean isDisplayed(String elementName) throws MyException;

	/**
	 * @param webElement
	 * @return
	 * @throws MyException
	 */
	public boolean isDisplayed(WebElement webElement) throws MyException;

	/**
	 * @param locator
	 * @return
	 * @throws MyException
	 */
	public boolean isDisplayed(By locator) throws MyException;

	/**
	 * @param elementName
	 * 
	 * @throws MyException
	 */
	public void mouseMoveTo(String elementName) throws MyException;

	/**
	 * @param height
	 */
	public void scrollTo(int height);

	/**
	 * @param webElement
	 */
	void scrollTo(WebElement webElement);

	/**
	 * 
	 */
	public void switchToDefaultContent();

	/**
	 * @param frameElement
	 * @throws MyException
	 */
	public void switchToFrame(WebElement frameElement) throws MyException;

	/**
	 * @param nameOrId
	 * @throws MyException
	 */
	public void switchToFrame(String nameOrId) throws MyException;

	/**
	 * @param index
	 * @throws MyException
	 */
	public void switchToFrame(int index) throws MyException;

	/**
	 * @param locator
	 * @return
	 */
	public boolean isElementExsit(By locator);

	/**
	 * @param webElement
	 * @return
	 * @throws MyException
	 */
	public boolean waitToDisplayed(WebElement webElement) throws MyException;

	/**
	 * @param elementName
	 * @return
	 * @throws MyException
	 */
	public boolean waitToDisplayed(String elementName) throws MyException;

	/**
	 * @param locator
	 * @return
	 * @throws MyException
	 */
	public boolean waitToDisplayed(By locator) throws MyException;

	/**
	 * @param webElement
	 * @param locator
	 * @return
	 * @throws MyException
	 */
	WebElement getWebElement(WebElement webElement, By locator) throws MyException;

	/**
	 * @param webElement
	 * @param locator
	 * @return
	 * @throws MyException
	 */
	List<WebElement> getWebElements(WebElement webElement, By locator) throws MyException;

	/**
	 * @param locator
	 * @return
	 * @throws MyException
	 */
	boolean isSelected(By locator) throws MyException;

	/**
	 * @param webElement
	 * @return
	 * @throws MyException
	 */
	boolean isSelected(WebElement webElement) throws MyException;

	/**
	 * @param elementName
	 * @return
	 * @throws MyException
	 */
	boolean isSelected(String elementName) throws MyException;

	/**
	 * @param webElement
	 * @return
	 * @throws MyException
	 */
	boolean waitToBeClickable(WebElement webElement) throws MyException;

	/**
	 * @param locator
	 * @return
	 * @throws MyException
	 */
	boolean waitToBeClickable(By locator) throws MyException;

	/**
	 * @param elementName
	 * @return
	 * @throws MyException
	 */
	boolean waitToBeClickable(String elementName) throws MyException;

	/**
	 * @param elementName
	 * @return
	 * @throws MyException
	 */
	WebElement waitToDisplayElement(String elementName) throws MyException;

	/**
	 * @param locator
	 * @return
	 * @throws MyException
	 */
	WebElement waitToDisplayElement(By locator) throws MyException;

	/**
	 * @param elementName
	 * @param string
	 * @throws MyException
	 */
	void actionInput(String elementName, String string) throws MyException;

	/**
	 * @param webElement
	 * @param inputText
	 * @throws MyException
	 */
	void actionInput(WebElement webElement, String inputText) throws MyException;

}
