package com.dzf.test.page.accounting.基础设置_总账;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class 个性化设置Page extends Handler {

	private final String xmlfile = "./config/page/accounting/基础设置_总账/" + this.getClass().getSimpleName() + ".xml";

	public 个性化设置Page() throws Exception {
		super();

		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean save() throws InterruptedException, MyException {

		switchToDefaultContent();

		driver.switchTo().frame(getWebElement(""));

		return false;
	}

}
