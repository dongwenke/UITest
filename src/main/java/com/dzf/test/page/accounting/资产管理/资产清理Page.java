package com.dzf.test.page.accounting.资产管理;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.凭证管理.填制凭证Page;
import com.dzf.test.util.DatePickerUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.StringUtil;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class 资产清理Page extends Handler {

	private final String xmlfile = "./config/page/accounting/资产管理/" + this.getClass().getSimpleName() + ".xml";

	public 资产清理Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	/*
	 * 只查询资产卡片输入框，精确查询
	 */
	public boolean searchByCard(String beginDate, String endDate, String assetCode) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("资产清理");

			click("查询按钮");

			if (StringUtil.notNullAndEmpty(beginDate)) {
				click("查询面板-开始日期选择按钮");
				new DatePickerUtil(getWebElement("查询面板-开始日期选择器")).choseDate(beginDate);
			}
			if (StringUtil.notNullAndEmpty(endDate)) {
				click("查询面板-结束日期选择按钮");
				new DatePickerUtil(getWebElement("查询面板-结束日期选择器")).choseDate(endDate);
			}

			if (StringUtil.notNullAndEmpty(assetCode)) {
				click("查询面板-资产卡片选择按钮");
				new WebTableUtil(getWebElement("资产卡片面板-卡片列表table")).getTr(assetCode);
				click("资产卡片面板-确认按钮");
			}

			click("查询面板-确定按钮");

			return true;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询失败！");
			throw e;
		}
	}

	/*
	 * 只查询资产卡片输入框，精确查询
	 */
	public boolean searchByCardNumber(String beginDate, String endDate, String assetCode) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("资产清理");

			click("查询按钮");

			if (StringUtil.notNullAndEmpty(beginDate)) {
				click("查询面板-开始日期选择按钮");
				new DatePickerUtil(getWebElement("查询面板-开始日期选择器")).choseDate(beginDate);
			}
			if (StringUtil.notNullAndEmpty(endDate)) {
				click("查询面板-结束日期选择按钮");
				new DatePickerUtil(getWebElement("查询面板-结束日期选择器")).choseDate(endDate);
			}

			if (!assetCode.equals("-1")) {
				input("查询面板-卡片编码输入框", assetCode);
			}

			click("查询面板-确定按钮");

			return true;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询失败！");
			throw e;
		}
	}

	/*
	 * 删除资产清理记录，已转总账不能删除
	 */
	public boolean delete(String assetCode) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("资产清理");

			click(new WebTableUtil(getWebElement("清理记录列表table")).getTr(assetCode));

			click("删除按钮");

			return new WebTableUtil(getWebElement("清理记录列表table")).hasTr(assetCode);
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询失败！");
			throw e;
		}
	}

	public boolean convertLedger(String assetCode) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("资产清理");

			click(new WebTableUtil(getWebElement("清理记录列表table")).getTr(assetCode));

			boolean 已转总账 = new WebTableUtil(getWebElement("清理记录列表table")).getTr(assetCode)
					.findElement(By.xpath("./td[@field='bzzz']/div")).getText().equals("是");
			if(已转总账){
				Reporter.log("卡片"+assetCode+"已经转总账！转总账失败！");
				return true;
//				throw new MyException("卡片"+assetCode+"已经转总账！转总账失败！");
			}

			click("转总账按钮");

			return new WebTableUtil(getWebElement("清理记录列表table")).getTr(assetCode)
					.findElement(By.xpath("./td[@field='bzzz']/div")).getText().equals("是");
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询失败！");
			throw e;
		}
	}

	public boolean refresh() throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("资产清理");

			click("刷新按钮");

			return true;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询失败！");
			throw e;
		}
	}

	public boolean print() throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("资产清理");

			return true;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询失败！");
			throw e;
		}
	}

	public boolean 联查凭证(String assetCode) throws Exception {
		try {
			switchToDefaultContent();
			switchToFrame("资产清理");

			boolean 已转总账 = new WebTableUtil(getWebElement("清理记录列表table")).getTr(assetCode)
					.findElement(By.xpath("./td[@field='bzzz']/div")).getText().equals("是");

			if (!已转总账) {
				Reporter.log("未转总账！");
				click("联查凭证按钮");
			} else {
				click(new WebTableUtil(getWebElement("清理记录列表table")).getTr(assetCode));

				click("联查凭证按钮");
			}

			boolean result = false;
			if (new AccountingMainPage().LableisOpened("凭证")) {
				result = 已转总账 ? true : false;

				switchToFrame("资产清理");
			} else {
				result = 已转总账 ? false : true;
			}

			return result;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询失败！");
			throw e;
		}
	}
}
