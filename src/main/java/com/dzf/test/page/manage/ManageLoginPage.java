package com.dzf.test.page.manage;

import java.io.FileNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.FileUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class ManageLoginPage extends Handler {

	public ManageLoginPage() {
		super();
		String xmlfile = "./config" + this.getClass().getName().replace("com.dzf.test", "").replace(".", "/") + ".xml";
		try {
			page = XMLUtil.convert(xmlfile, Page.class);
		} catch (FileNotFoundException e) {
			FileUtil.copyFile(templateXml, xmlfile);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean login(String name, String password, String company) throws InterruptedException,MyException {
		this.open();

		// 输入用户名
		input("用户名输入框", name);
		// 输入密码
		input("密码输入框", password);
		// 点击登录按钮
		click("登录按钮");

		if (isDisplayed("已登录提示")) {
			// 如果显示则点击确定
			click("确认强制退出");
		}

		Thread.sleep(1500);

		input("选择公司面板-搜索输入框", company);

		input("选择公司面板-搜索输入框", Keys.RETURN);

		WebTableUtil table = new WebTableUtil(getWebElement("选择公司面板-公司列表"));
		boolean hasCompany = false;
		for (int i = 0; i < table.getRowCount(); i++) {
			String companytmp = table.getCell(i, 1).findElement(By.tagName("div")).getText();
			if (companytmp.equals(company)) {
				hasCompany = true;
				click(table.getRow(i));
			}
		}

		if (hasCompany != true) {
			System.out.println("没有找到公司：" + company);
			return false;
		}

		// 点击确定
		click("确定所选公司");

		return false;
	}
}
