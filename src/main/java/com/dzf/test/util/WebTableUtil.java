package com.dzf.test.util;

import java.util.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class WebTableUtil {
	private WebElement webTable;

	public WebTableUtil(WebElement webElement) {

		this.webTable = webElement;
	}

	// 得到表格的行数
	public int getRowCount() throws MyException {
		try {
			List<WebElement> rowCounts = webTable.findElements(By.tagName("tr"));
			return rowCounts.size();
		} catch (WebDriverException e) {
			throw new MyException("获取行数失败！", e);
		}
	}// 增加一个得到指定行癿列数，传入一个指定行数为参数，从 0 开始。交流方式 ryannj @126. com

	// 得到表格的行
	public WebElement getRow(int rowIdx) throws MyException {
		try {
			List<WebElement> rowCounts = webTable.findElements(By.tagName("tr"));
			// 得到对应的行
			return rowCounts.get(rowIdx);
		} catch (WebDriverException e) {
			throw new MyException("获取第" + rowIdx + "行失败！", e);
		}

	}

	// 得到指定行的列数
	public int getColCount(int rowIdx) throws MyException {
		try {
			List<WebElement> rowCounts = webTable.findElements(By.tagName("tr"));
			// 取得当前的tr
			WebElement rowNum = rowCounts.get(rowIdx);
			// 计算当前的td数
			List<WebElement> colCounts = rowNum.findElements(By.tagName("td"));

			return colCounts.size();
		} catch (WebDriverException e) {
			throw new MyException("获取第行" + rowIdx + "的列数失败！", e);
		}
	}// 增加一个得到指定单元格内容的方法，传入指定的行数，和列数作为参数。

	// 得到指定单元格的内容
	public WebElement getCell(int rowIdx, int colIdx) throws MyException {
		try {

			WebElement currentRow = getRow(rowIdx);

			List<WebElement> td = currentRow.findElements(By.tagName("td"));
			// 取得对应的单元格

			// highlightElementUtil.highlightElement(td.get(colIdx));

			return td.get(colIdx);
		} catch (WebDriverException e) {
			throw new MyException("获取单元格失败！行：" + rowIdx + "列：" + colIdx, e);
		}
	}

	// 得到指定单元格的内容
	public WebElement getCell(WebElement row, int colIdx) throws MyException {
		try {
			// 取得对应的单元格
			// highlightElementUtil.highlightElement(td.get(colIdx));

			return row.findElements(By.tagName("td")).get(colIdx);
		} catch (WebDriverException e) {
			throw new MyException("获取单元格失败！列：" + colIdx, e);
		}
	}

	/*
	 * 获取 包含keyword的tr行 返回的tr不再包含tr 如果需要的tr中包含table则不适用此方法
	 */
	public WebElement getTr(String... keyword) throws MyException {
		return getTr(0, keyword);
	}

	public WebElement getTr(int index, String... keyword) throws MyException {
		try {
			StringBuffer xpath = new StringBuffer(".//tr[");
			for (int i = 0; i < keyword.length; i++) {
				if (i == 0) {
					xpath.append("*='" + keyword[i] + "'");
				} else {
					xpath.append(" and *='" + keyword[i] + "'");
				}

			}
			xpath.append("]");

			List<WebElement> l = webTable.findElements(By.xpath(xpath.toString()));

			if (l.size() == 0) {
				
				StringBuilder errorMsg = new StringBuilder("没有找到包含");
				for (int i = 0; i < keyword.length; i++) {
					errorMsg.append(keyword[i] + ",");
				}
				errorMsg.append("的行");
				
				throw new MyException(errorMsg.toString());
			}

			return l.get(index);

		} catch (WebDriverException e) {
			throw new MyException("没有找到包含：" + keyword.toString() + "的行", e);
		}
	}

	/*
	 * 获取 包含keyword的tr行 返回的tr不再包含tr 如果需要的tr中包含table则不适用此方法
	 */
	public boolean hasTr(String... keyword) throws MyException {
		try {
			StringBuffer xpath = new StringBuffer(".//tr[");
			for (int i = 0; i < keyword.length; i++) {
				if (i == 0) {
					xpath.append("*='" + keyword[i] + "'");
				} else {
					xpath.append(" and *='" + keyword[i] + "'");
				}
			}
			xpath.append("]");

			return webTable.findElement(By.xpath(xpath.toString())).isDisplayed();
		} catch (WebDriverException e) {
			return false;
		}
	}
}// 增加一个得到表格中行数癿方法
