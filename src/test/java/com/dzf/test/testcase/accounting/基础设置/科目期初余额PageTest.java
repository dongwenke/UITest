package com.dzf.test.testcase.accounting.基础设置;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.基础设置_总账.科目期初余额Page;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;

public class 科目期初余额PageTest implements ILogUtil {

	private AccountingMainPage mainPage;
	private 科目期初余额Page kmqcPage;

	@BeforeClass
	public void setUp() throws Exception {
		logger.info("【"+this.getClass().getSimpleName()+"】开始运行");
		mainPage = new AccountingMainPage();
		kmqcPage = new 科目期初余额Page();
	}

	@Parameters({ "科目","金额" })
	@Test(groups = { "logged-in" }, enabled = true)
	public void testModify(String subject,String num) throws InterruptedException, MyException {
		// 打开科目期初余额
		mainPage.open科目期初余额();

		assertTrue(kmqcPage.modify(subject,null, num, null));

	}
	
	@AfterClass
	public void teardown() throws Exception {
		logger.info("【"+this.getClass().getSimpleName()+"】运行完毕！");
	}

}
