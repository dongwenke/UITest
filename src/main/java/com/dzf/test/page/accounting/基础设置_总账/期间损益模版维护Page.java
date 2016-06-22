package com.dzf.test.page.accounting.基础设置_总账;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class 期间损益模版维护Page extends Handler {

	private final String xmlfile = "./config/page/accounting/基础设置_总账/" + this.getClass().getSimpleName() + ".xml";

	public 期间损益模版维护Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean modify() throws InterruptedException, MyException {

		switchToDefaultContent();

		driver.switchTo().frame(getWebElement("会计科目iframe"));

		if (getWebElement("资产-其他货币资金-外汇核算币种").getText().contains("美元")) {
			return true;
		}

		click("其它货币资金");

		click("修改按钮");
		
		Thread.sleep(500);
		
		click("是否外汇核算选择按钮");

		click("是否外汇核算选项是");
		
		new Actions(driver).sendKeys(getWebElement(By.xpath(".//*[@id='accform']/table/tbody/tr[3]/th")),Keys.DOWN).perform();

		click("外汇核算币种选择按钮");

		click("外汇核算币种选项-美元复选框");

		click("外汇核算币种选择面板-确定按钮");

		click("科目修改面板-保存按钮");

		Thread.sleep(1000);

		click("刷新按钮");

		Thread.sleep(500);

		if (getWebElement("资产-其他货币资金-外汇核算币种").getText().contains("美元")) {
			return true;
		} else {
			return false;
		}
	}

}
