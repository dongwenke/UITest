package com.dzf.test.testcase;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.annotations.*;

import com.dzf.test.page.accounting.AccountingLoginPage;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;

public class SuiteConfig implements ILogUtil {

//	@BeforeSuite
	@Parameters({"用户名","密码","公司名称"})
	public void suiteSetup(String username,String password,String company) throws Exception {
		
		logger.info("Suite初始化！");
		
		try {
			Assert.assertTrue(new AccountingLoginPage().login(username, password, company));
			
		} catch (MyException e) {
			logger.error("登录失败！",e);
			Reporter.log(e.getMessage());
			Reporter.log("登录失败！");
			throw e;
		}
//		logger.info("登录成功："+new LoginPageTest().testLogin(username,password,company));
	}

	@AfterSuite
	public void suiteTearDown() {
		logger.info("Suite运行完毕！");
//		WebDriverModel.driver.quit();
	}

	public static void main(String[] args) {
		TestNG testng = new TestNG();
		testng.run();
	}
	
}
