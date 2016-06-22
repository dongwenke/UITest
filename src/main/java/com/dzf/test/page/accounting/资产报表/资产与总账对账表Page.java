package com.dzf.test.page.accounting.资产报表;

import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.DatePickerUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class 资产与总账对账表Page extends Handler {
	private final String xmlfile = "./config/page/accounting/资产报表/" + this.getClass().getSimpleName() + ".xml";

	public 资产与总账对账表Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}
	
	public boolean search(/* 开始日期 eg:2015-01-01 */String date) throws MyException{
		try{
			switchToDefaultContent();
			switchToFrame("资产与总账对账表");
			
			click("查询按钮");
			
			if (date != null && !date.equals("")) {
				Reporter.log("选择日期：" + date);
				click("查询面板-日期选择按钮");
				new DatePickerUtil(getWebElement("查询面板-时间选择器")).choseDate(date);
			}
			
			
			click("查询面板-确定按钮");
			
			return false;
		}catch(MyException e){
			Reporter.log("");
			throw e;
		}
		
	}
}
