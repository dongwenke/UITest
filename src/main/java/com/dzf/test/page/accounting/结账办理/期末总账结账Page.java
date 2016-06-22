package com.dzf.test.page.accounting.结账办理;

import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

/**
 * @author Administrator
 * @function 期末总帐结账
 */
public class 期末总账结账Page extends Handler {

	public 期末总账结账Page() {
		super();
		try {
			page = XMLUtil.convert("./config/page/accounting/ZongzhangQimojiezhangPage.xml", Page.class.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkout() throws InterruptedException, MyException {
		// 切换到总账期末结账iframe
		driver.switchTo().defaultContent();
		driver.switchTo().frame(getWebElement("总账期末结账iframe"));

		mouseMoveTo("期间查询");

		click("查询确定按钮");
		Thread.sleep(500);
		click("全选复选框");

		// 利润结转
		// 审核凭证

		// 结账
		click("结账按钮");

	}

	public void unCheckout() throws InterruptedException , MyException{
		// 切换到总账期末结账iframe
		driver.switchTo().defaultContent();
		driver.switchTo().frame(getWebElement("总账期末结账iframe"));

		mouseMoveTo("期间查询");

		click("查询确定按钮");
		Thread.sleep(500);
		click("全选复选框");

		// 结账
		click("反结账按钮");

	}

	private String getCompany() throws Exception {
		try{
		return new AccountingMainPage().getText("公司名称");
		}catch(MyException e){
			Reporter.log("获取公司名称失败！");
			throw e;
		}
	}
}
