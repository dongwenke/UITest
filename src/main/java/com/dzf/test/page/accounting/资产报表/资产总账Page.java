package com.dzf.test.page.accounting.资产报表;

import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.DatePickerUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class 资产总账Page extends Handler {

	private final String xmlfile = "./config/page/accounting/资产报表/" + this.getClass().getSimpleName() + ".xml";

	public 资产总账Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean search(/* 开始日期 eg:2015-01-01 */String beginDate,
			/* 开始日期 eg:2015-01-01 */String endDate, /* 资产属性 */String assetAttr) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("资产总账");

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

			if (assetAttr != null && !assetAttr.equals("")) {
				Reporter.log("点击固定资产输入框");
				click("查询面板-固定资产输入框");// *[@id='zcsx']/../span/input[1]

				Reporter.log("选择资产属性选项-" + assetAttr);
				click("查询面板-资产属性选项-" + assetAttr);// div[text()='固定资产'][contains(@class,'combobox-item')]
			}

			Reporter.log("点击确定按钮");
			click("查询面板-确定按钮");

			return !isDisplayed("查询面板-确定按钮");
		} catch (MyException e) {
			Reporter.log("查询失败！");
			throw e;
		}

	}

}
