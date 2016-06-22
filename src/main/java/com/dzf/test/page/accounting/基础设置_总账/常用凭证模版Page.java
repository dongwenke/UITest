package com.dzf.test.page.accounting.基础设置_总账;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.model.TemplateSubject;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class 常用凭证模版Page extends Handler {

	private final String xmlfile = "./config/page/accounting/基础设置_总账/" + this.getClass().getSimpleName() + ".xml";

	public 常用凭证模版Page() throws Exception {
		super();

		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean add(String code, String name, String comment, List<TemplateSubject> subjectList)
			throws InterruptedException, MyException {

		switchToDefaultContent();
		switchToFrame(getWebElement("常用凭证模板iframe"));

		// 点击增加按钮
		click("增加按钮");

		if (isDisplayed("保存按钮")) {
			return true;
		}
		return false;
	}

	public boolean modify(String templateCode) throws InterruptedException, MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("常用凭证模板iframe"));

		// 模版编码列
		List<WebElement> webElementList = getWebElements(By.xpath(".//td[@field='mbbm']"));
		for (WebElement webElement : webElementList) {
			if (webElement.findElement(By.tagName("div")).getText().equals(templateCode)) {
				click(webElement);
			}
		}

		// 点击增加按钮
		click("修改按钮");

		if (isDisplayed("保存按钮")) {
			return true;
		}
		return false;
	}

	public boolean delete() throws InterruptedException, MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("常用凭证模板iframe"));
		return false;
	}

	public boolean cardshow() throws InterruptedException, MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("常用凭证模板iframe"));

		return false;
	}

	public boolean addrow() {
		return false;
	}

	public boolean removerow() {
		return false;
	}
}
