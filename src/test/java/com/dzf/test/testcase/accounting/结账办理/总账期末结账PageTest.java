package com.dzf.test.testcase.accounting.结账办理;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.结账办理.期末总账结账Page;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;

public class 总账期末结账PageTest implements ILogUtil{
	private AccountingMainPage mainPage;
	private 期末总账结账Page 总账期末结账page;
	
	@BeforeClass
	public void setup() throws Exception {
		logger.info("【LedgerFinalCheckoutPageTest】开始运行");
		mainPage = new AccountingMainPage();
		总账期末结账page = new 期末总账结账Page();
	}
	
	@Test(groups={"logged-in"},enabled=true)
	public void testCheckout() throws InterruptedException, MyException{
		
		//切换到12月31号
		mainPage.quickSwitch();
		
		mainPage.open总账期末结账();
		
		总账期末结账page.checkout();
		
		Thread.sleep(1500);
		
		assertEquals(总账期末结账page.getText("结账完成状态"),"是");
		
	}
	
}
