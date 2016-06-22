package com.dzf.test.page.accounting.资产报表;

import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class 资产折旧明细Page extends Handler {
	private final String xmlfile = "./config/page/accounting/资产报表/" + this.getClass().getSimpleName() + ".xml";

	public 资产折旧明细Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean search(/* 开始日期 eg:2015-01 */String beginDate,
			/* 结束日期 eg:2015-01 */String endDate, /* 资产编码 */ String assetCode, /* 资产类别编码 */String assetClassCode,
			/* 资产属性 */String assetAttr) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("资产折旧明细");

			click("查询按钮");

			if (beginDate != null && !beginDate.equals("")) {
				if (Integer.valueOf(beginDate.substring(0, 4)) > Calendar.getInstance().get(Calendar.YEAR)) {
					Reporter.log("开始年份不能大于当前年份!自动使用当前年份替换");
					beginDate = Calendar.getInstance().get(Calendar.YEAR) + beginDate.substring(5);
				}
				
				click("查询面板-开始年份选择按钮");
				click(getWebElement("开始年份选择悬浮层")
						.findElement(By.xpath("./div[text()='" + beginDate.substring(0, 4) + "']")));
				click("查询面板-开始月份选择按钮");
				click(getWebElement("开始月份选择悬浮层")
						.findElement(By.xpath("./div[text()='" + beginDate.substring(5) + "']")));

			}

			if (endDate != null && !endDate.equals("")) {

				if (Integer.valueOf(endDate.substring(0, 4)) > Calendar.getInstance().get(Calendar.YEAR)) {
					Reporter.log("结束年份不能大于当前年份!自动使用当前年份替换");
					endDate = Calendar.getInstance().get(Calendar.YEAR) + endDate.substring(5);
				}
				click("查询面板-结束年份选择按钮");
				click(getWebElement("结束年份选择悬浮层")
						.findElement(By.xpath("./div[text()='" + endDate.substring(0, 4) + "']")));
				click("查询面板-结束月份选择按钮");
				click(getWebElement("结束月份选择悬浮层").findElement(By.xpath("./div[text()='" + endDate.substring(5) + "']")));

			}

			input("资产编码面板-输入框", assetCode);

			if (assetClassCode != null && !assetClassCode.equals("")) {
				click("查询面板-资产类别选择按钮");
				input("资产类别面板-输入框", assetClassCode);
				input("资产类别面板-输入框", Keys.RETURN);
				click("资产类别面板-确定按钮");
			}

			if (assetAttr != null && !assetAttr.equals("")) {
				input("查询面板-资产属性输入框", assetAttr);
			}

			click("查询面板-确定按钮");
			return !isDisplayed("查询面板-确定按钮");
		} catch (MyException e) {
			Reporter.log("查询失败！");
			throw e;
		}

	}
}
