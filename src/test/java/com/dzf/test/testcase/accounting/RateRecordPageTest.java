package com.dzf.test.testcase.accounting;

import org.testng.Assert;
import org.testng.annotations.*;

import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.基础设置_总账.汇率档案Page;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;


public class RateRecordPageTest implements ILogUtil {
	private AccountingMainPage mainPage;
	private 汇率档案Page rateRecordPage;

	@BeforeClass
	public void setUp() throws Exception {
		logger.info("【RateRecordPageTest】开始运行！");
		mainPage = new AccountingMainPage();
		rateRecordPage = new 汇率档案Page();
	}

	@Parameters({"币种","汇率","折算模式","是否浮动汇率","备注"})
	@Test
	public void testAdd(String currency, String rate, String translationMode, String isFloateRate,String comment)
			throws InterruptedException, MyException {
		
		mainPage.open汇率档案();

		boolean result = rateRecordPage.add(currency, rate, translationMode, isFloateRate,comment);

		Assert.assertTrue(result);
	}

}
