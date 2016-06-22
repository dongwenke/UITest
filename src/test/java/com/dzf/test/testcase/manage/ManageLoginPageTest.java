package com.dzf.test.testcase.manage;

import org.testng.Assert;
import org.testng.annotations.*;

import com.dzf.test.page.manage.ManageLoginPage;
import com.dzf.test.page.manage.ManageMainPage;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;

public class ManageLoginPageTest implements ILogUtil {
	private ManageLoginPage loginPage;
	private ManageMainPage mainPage;

	@BeforeClass
	public void setup() {
		loginPage = new ManageLoginPage();
		mainPage = new ManageMainPage();
	}

	@Test(groups = { "login" })
	@Parameters({ "用户名", "密码", "公司名称" })
	public boolean testLogin(String userName, String password, String company) throws InterruptedException,MyException {
		boolean result = false;

		loginPage.login(userName, password, company);

		if (mainPage.isDisplayed("用户信息")) {
			result = true;
		}
		
		Assert.assertTrue(result);

		return result;
	}

	@AfterClass
	public void teardown() throws Exception {
		logger.info("【" + this.getClass().getSimpleName() + "】运行完毕！");
	}
}
