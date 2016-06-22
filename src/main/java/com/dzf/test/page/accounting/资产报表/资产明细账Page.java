package com.dzf.test.page.accounting.资产报表;

import org.openqa.selenium.Keys;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.DatePickerUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class 资产明细账Page extends Handler {

	private final String xmlfile = "./config/page/accounting/资产报表/" + this.getClass().getSimpleName() + ".xml";

	public 资产明细账Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean search(/* 开始日期 eg:2015-01-01 */String beginDate,
			/* 开始日期 eg:2015-01-01 */String endDate, /* 资产名称 */String assetName, /* 资产类别编码 */String assetClassCode,
			/* 资产属性 */String assetAttr) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("资产明细账");

			Reporter.log("点击查询按钮");
			click("查询按钮");

			if (beginDate != null && !beginDate.equals("")) {
				Reporter.log("点击起始日期选择按钮");
				click("查询面板-起始日期选择按钮");

				Reporter.log("选择日期：" + beginDate);
				new DatePickerUtil(getWebElement("查询面板-起始日期时间选择器")).choseDate(beginDate);
			}
			if (endDate != null && !endDate.equals("")) {
				Reporter.log("点击结束日期选择按钮");
				click("查询面板-结束日期选择按钮");

				Reporter.log("选择日期：" + endDate);
				new DatePickerUtil(getWebElement("查询面板-结束日期时间选择器")).choseDate(endDate);
			}

			if (assetName != null && !assetName.equals("")) {
				Reporter.log("资产名称输入：" + assetName);
				input("查询面板-资产名称输入框", assetName);
			}

			if (assetClassCode != null && !assetClassCode.equals("")) {
				Reporter.log("选择资产类别：" + assetClassCode);
				click("查询面板-资产类别选择按钮");
				input("资产类别面板-输入框", assetClassCode);
				input("资产类别面板-输入框", Keys.RETURN);
				click("资产类别面板-确定按钮");
			}

			if (assetClassCode != null && !assetClassCode.equals("")) {
				Reporter.log("资产属性输入框输入：" + assetAttr);
				input("查询面板-资产属性输入框", assetClassCode);
			}

			Reporter.log("点击确定按钮");
			click("查询面板-确定按钮");

			return !isDisplayed("查询面板-确定按钮");

		} catch (MyException e) {
			Reporter.log("查询失败");
			throw e;
		}

	}
}
