package com.dzf.test.page.accounting.日常业务;

import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.DatePickerUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class 入库单Page extends Handler {

	private final String xmlfile = "./config/page/accounting/日常业务/" + this.getClass().getSimpleName() + ".xml";

	public 入库单Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean search(/* 开始日期 eg:2015-01-01 */String beginDate,
			/* 开始日期 eg:2015-01-01 */String endDate, /* 商品名称 */String goodsname) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("入库单");

			click("查询按钮");

			if (beginDate != null && !beginDate.equals("")) {
				click("查询面板-起始日期选择按钮");

				new DatePickerUtil(getWebElement("查询面板-起始日期时间选择器")).choseDate(beginDate);
			}

			if (endDate != null && !endDate.equals("")) {
				click("查询面板-结束日期选择按钮");

				new DatePickerUtil(getWebElement("查询面板-结束日期时间选择器")).choseDate(endDate);
			}

			if (goodsname != null && !goodsname.equals("")) {
				click("查询面板-商品选择按钮");// *[@id='zcsx']/../span/input[1]

				click(new WebTableUtil(getWebElement("选择商品table")).getTr(goodsname));// div[text()='固定资产'][contains(@class,'combobox-item')]
			
				click("选择商品面板-确认按钮");
			}

			click("查询面板-确定按钮");

			return !isDisplayed("查询面板-确定按钮");
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询失败！");
			throw e;
		}

	}
}
