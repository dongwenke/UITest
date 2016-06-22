package com.dzf.test.testcase.accounting;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.dzf.test.page.accounting.AccountingLoginPage;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;

public class LoginPageTest implements ILogUtil {
	private AccountingLoginPage loginPage;

	public LoginPageTest() throws Exception {
		logger.info("【" + this.getClass().getSimpleName() + "】开始执行");
		loginPage = new AccountingLoginPage();
	}

	@Parameters({ "用户名", "密码", "公司名称" })
	@Test(groups = { "login" })
	public boolean testLogin(String userName, String password, String company)
			throws InterruptedException, MyException {
		boolean result = false;
		
		try {
			result = loginPage.login(userName, password, company);
			
		} catch (MyException e) {
			logger.error("登录失败！",e);
			
			Reporter.log(e.getMessage());
			Reporter.log("登录失败！");
			throw e;
		}

		Assert.assertTrue(result);

		return result;
	}

	@AfterClass
	public void teardown() throws Exception {
		logger.info("【LoginPageTest】运行完毕！");
	}

}
