package com.dzf.test.page.accounting.财务账表;

import java.util.Iterator;

import org.openqa.selenium.WebDriverException;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class 资产负债表Page extends Handler {

	public 资产负债表Page() {
		super();
		try {
			page = XMLUtil.convert("./config/page/accounting/财务账表/" + this.getClass().getSimpleName() + ".xml",
					Page.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 查询
	 */
	public void search(String date, String company, boolean 包含未记账凭证, boolean 按往来科目明细分析填列)
			throws InterruptedException, MyException {

		switchToDefaultContent();
		switchToFrame(getWebElement("资产负债表iframe"));

		try {
			// 点击查询按钮
			click("查询按钮");
		} catch (WebDriverException e) {

		}

		// 点击确定按钮
		click("查询面板-确定按钮");
		Thread.sleep(1000);
	}

	/*
	 * 打印
	 */
	public boolean print() throws InterruptedException, MyException {

		boolean result = false;

		switchToDefaultContent();
		switchToFrame(getWebElement("资产负债表iframe"));

		click("打印按钮");
		// 选择模版
		// 设置边距
		// 选择制单人
		click("打印面板-打印按钮");

		Thread.sleep(5000);

		String prePage = driver.getWindowHandle();

		// 切换选项卡
		Iterator<String> it = driver.getWindowHandles().iterator();// 获得所有窗口句柄报错
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			if (driver.getTitle().contains("print")) {
				result = true;
				break;
			}
		}

		logger.info(driver.getCurrentUrl());

		// 关闭当前打印窗口
		if (!driver.getWindowHandle().equals(prePage)) {
			driver.close();
			logger.info("当前打印窗口已关闭!");
		}

		// 切换回先前的页面
		driver.switchTo().window(prePage);
		logger.info("driver已切换到先前的页面！");

		return result;
	}

	/*
	 * 导出
	 */
	public void export() throws MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("资产负债表iframe"));

		click("导出按钮");
	}
}