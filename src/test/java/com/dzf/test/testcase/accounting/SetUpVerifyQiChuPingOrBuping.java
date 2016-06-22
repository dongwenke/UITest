package com.dzf.test.testcase.accounting;

import org.testng.annotations.*;

import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.凭证管理.凭证管理Page;
import com.dzf.test.page.accounting.结账办理.期末总账结账Page;
import com.dzf.test.util.MyException;
import com.dzf.test.page.accounting.结账办理.期末处理Page;

/**
 * 验证期初平与不平
 */
public class SetUpVerifyQiChuPingOrBuping {
	
	/*
	 * 验证期初平与不平
	 */
	@Parameters({"开始年月","结束年月"})
	@BeforeTest
	public void setUp(String beginDate, String endDate) throws Exception{
		
		AccountingMainPage mainPage = new AccountingMainPage();
		mainPage.quickSwitch();
		//反总账结转
		mainPage.open总账期末结账();
		new 期末总账结账Page().unCheckout();
		//反期间损益结转
		mainPage.open期末处理();
		new 期末处理Page().反期间损益结转();
		//取消记账
		mainPage.open凭证管理();
		凭证管理Page vmp = new 凭证管理Page();
		vmp.unAccountVoucher();
		//取消审核
		vmp.unAuditVoucher();
		
		
	}

}
