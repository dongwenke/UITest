package com.dzf.test.testcase.accounting.凭证管理;

import static org.testng.Assert.*;
import org.testng.annotations.*;

import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.PrintVoucherPage;
import com.dzf.test.page.accounting.凭证管理.填制凭证Page;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;

public class 填制凭证PageTest implements ILogUtil {

	private AccountingMainPage mainPage;
	private 填制凭证Page fillVoucherPage;
	private PrintVoucherPage printVoucherPage;

	@BeforeClass
	public void setup() throws Exception {
		logger.info("【FillVoucherPageTest】开始运行");
		mainPage = new AccountingMainPage();
		fillVoucherPage = new 填制凭证Page();
		printVoucherPage = new PrintVoucherPage();
	}

	@Parameters({ "科目名称", "汇率", "原币金额", "本位币金额" })
	@Test
	public void testFillVoucher(String subject, String rate, String original, String money)
			throws InterruptedException, MyException {
		// 打开填制凭证
		mainPage.open填制凭证();

//		boolean result = fillVoucherPage.saveVoucher(subject, rate, original, money);

//		assertTrue(result);

	}

	@Test(groups = { "logged-in" })
	public void testCommonTemplet() throws InterruptedException, MyException {
		// 填制凭证
		mainPage.open填制凭证();
//		try {
//			fillVoucherPage.commonTemplet();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		if (fillVoucherPage.isDisplayed("打印按钮")) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}

	}

	@Test(groups = { "logged-in" }, enabled = false)
	public void testBillImage() throws InterruptedException, MyException {
		// 填制凭证
		mainPage.open填制凭证();
		fillVoucherPage.billImage();

	}

	@Test(groups = { "logged-in" }, enabled = false)
	public void testCopyByMonthOnFill() throws InterruptedException, MyException {
		// 填制凭证
		mainPage.open填制凭证();
//		fillVoucherPage.copyByMonthOnFill();

	}

	@Test(groups = "handleSaved", dependsOnGroups = "saveVoucher", enabled = false)
	public void testPrintVoucher() throws MyException {
//		fillVoucherPage.print();

		printVoucherPage.printVoucher();

		// System.out.println(Handle.driver.getCurrentUrl());
		// System.out.println(Handle.driver.getTitle());
	}

	@AfterClass
	public void teardown() throws Exception {
		logger.info("【FillVoucherPageTest】运行完毕！");
	}

}
