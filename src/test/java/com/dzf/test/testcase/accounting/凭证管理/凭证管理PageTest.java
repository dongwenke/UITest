package com.dzf.test.testcase.accounting.凭证管理;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.凭证管理.凭证管理Page;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;

public class 凭证管理PageTest implements ILogUtil{
	private AccountingMainPage mainPage;
	private 凭证管理Page voucherManagePage;
	
	@BeforeClass
	public void setup() throws Exception {
		logger.info("【VoucherManagePageTest】开始运行");
		mainPage = new AccountingMainPage();
		voucherManagePage = new 凭证管理Page();
	}
	
	@Parameters({"开始年月","结束年月"})
	@Test(groups={"logged-in"},enabled=true)
	public void testAuditVoucher(String beginDate,String endDate) throws InterruptedException, MyException{
		mainPage.open凭证管理();
//		voucherManagePage.auditVoucher(beginDate,endDate);
		
		assertTrue(voucherManagePage.isDisplayed("操作提示"));
		
		Thread.sleep(3000);
		
	}
	
	@Parameters({"开始年月","结束年月"})
	@Test(groups={"logged-in"},enabled=true)
	public void testAccountVoucher(String beginDate,String endDate) throws InterruptedException, MyException{
		mainPage.open凭证管理();
//		voucherManagePage.accountVoucher(beginDate,endDate);
		
		assertTrue(voucherManagePage.isDisplayed("操作提示"));
		
		Thread.sleep(3000);
	}
	
}
