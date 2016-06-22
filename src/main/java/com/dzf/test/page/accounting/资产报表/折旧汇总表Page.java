package com.dzf.test.page.accounting.资产报表;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class 折旧汇总表Page extends Handler {

	private final String xmlfile = "./config/page/accounting/资产报表/" + this.getClass().getSimpleName() + ".xml";

	public 折旧汇总表Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	/*
	 * date 2016-05
	 */
	public boolean search(/* 日期 eg:2015-01 */String date,
			/* 资产属性 {固定资产，无形资产，全部} */String assetAttr, /* 资产类别编码 */String assetClassCode,
			/* 1级-5级 */String minLevel, /* 1级-5级 */String maxLevel) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("折旧汇总表");

			click("查询按钮");

			if (date != null && !date.equals("")) {
				click("查询面板-年份选择按钮");
				click(getWebElement("年份选择悬浮层").findElement(By.xpath("./div[text()='" + date.substring(0, 4) + "']")));
				click("查询面板-月份选择按钮");
				click(getWebElement("月份选择悬浮层").findElement(By.xpath("./div[text()='" + date.substring(5) + "']")));

			}

			if (assetAttr != null && !assetAttr.equals("")) {
				click("查询面板-资产属性选择按钮");
				click(getWebElement("资产属性选择悬浮层").findElement(By.xpath("./div[text()='" + assetAttr + "']")));
			}

			if (assetClassCode != null && !assetClassCode.equals("")) {
				click("查询面板-资产类别选择按钮");
				input("资产类别面板-输入框", assetClassCode);
				input("资产类别面板-输入框", Keys.RETURN);
				click("资产类别面板-确定按钮");
			}

			if (minLevel != null && !minLevel.equals("")) {
				input("查询面板-最小类别级次输入框", minLevel);
			}

			if (maxLevel != null && !maxLevel.equals("")) {
				input("查询面板-最大类别级次输入框", maxLevel);
			}

			click("查询面板-确定按钮");

			return false;
		} catch (MyException e) {
			Reporter.log("查询失败！");
			throw e;
		}

	}
}
