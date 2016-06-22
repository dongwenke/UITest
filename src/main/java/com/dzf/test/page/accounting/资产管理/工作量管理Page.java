package com.dzf.test.page.accounting.资产管理;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.DatePickerUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.StringUtil;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class 工作量管理Page extends Handler {

	private final String xmlfile = "./config/page/accounting/资产管理/" + this.getClass().getSimpleName() + ".xml";

	public 工作量管理Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean search(String periodDate, String company) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("工作量管理");

			click("查询按钮");

			if (StringUtil.notNullAndEmpty(periodDate)) {
				click("查询面板-期间年份选择按钮");
				
				click(getWebElement(By.xpath("//div[starts-with(@id,'_easyui_combobox_i1') and text()='"+periodDate.split("-")[0]+"']")));
				
				click("查询面板-期间月份选择按钮");
				
				click(getWebElement(By.xpath("//div[starts-with(@id,'_easyui_combobox_i2') and text()='"+periodDate.split("-")[1]+"']")));
			}

			if (StringUtil.notNullAndEmpty(company)) {
				click("查询面板-公司选择按钮");
				click(new WebTableUtil(getWebElement("选择公司面板-公司列表table")).getTr(company));
				click("选择公司面板-确认按钮");
			}

			click("查询面板-确定按钮");

			return true;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询失败！");
			throw e;
		}
	}

	public boolean modify(String assetCode, double 本月工作量) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("工作量管理");

			click(new WebTableUtil(getWebElement("工作量列表table")).getTr(assetCode));

			click("修改按钮");

			WebElement 本月工作量输入框 = new WebTableUtil(getWebElement("工作量列表table")).getTr(assetCode)
					.findElement(By.xpath("./td[@field='bygzl']//input[following-sibling::input]"));
			actionInput(本月工作量输入框, String.valueOf(本月工作量));

			click("保存按钮");

			String 已输入的本月工作量 = new WebTableUtil(getWebElement("工作量列表table")).getTr(assetCode)
					.findElement(By.xpath("./td[@field='bygzl']/div")).getText();

			return new Double(已输入的本月工作量.replace(",", "")) == 本月工作量;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询失败！");
			throw e;
		}
	}

	public boolean refresh() throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("工作量管理");

			click("刷新按钮");
			return true;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询失败！");
			throw e;
		}
	}

	public boolean cancel() throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("工作量管理");
			click("取消按钮");
			return true;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询失败！");
			throw e;
		}
	}

}
