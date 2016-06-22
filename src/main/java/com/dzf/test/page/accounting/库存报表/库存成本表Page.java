package com.dzf.test.page.accounting.库存报表;

import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.DatePickerUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class 库存成本表Page extends Handler {

	private final String xmlfile = "./config/page/accounting/库存报表/" + this.getClass().getSimpleName() + ".xml";

	public 库存成本表Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	/*
	 * 查询
	 */
	public boolean search(/* 截止日期 eg:2015-01-01 */String date, /* 商品名称 */String goodsname) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("库存成本表");

			click("查询按钮");

			if (date != null && !date.equals("")) {
				click("查询面板-日期选择按钮");

				new DatePickerUtil(getWebElement("查询面板-日期时间选择器")).choseDate(date);
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
