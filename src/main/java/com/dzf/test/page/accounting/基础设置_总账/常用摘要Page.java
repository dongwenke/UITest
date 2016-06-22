package com.dzf.test.page.accounting.基础设置_总账;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class 常用摘要Page extends Handler {

	private final String xmlfile = "./config/page/accounting/基础设置_总账/" + this.getClass().getSimpleName() + ".xml";

	public 常用摘要Page() throws Exception {
		super();

		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean add() throws InterruptedException, MyException {

		switchToDefaultContent();

		driver.switchTo().frame(getWebElement(""));

		if (getWebElement("").getText().contains("")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean edit() throws InterruptedException {
		return false;
	}

	public boolean delete() throws InterruptedException {
		return false;
	}

	public boolean refresh() throws InterruptedException {
		return false;
	}

}
