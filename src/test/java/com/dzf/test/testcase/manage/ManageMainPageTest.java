package com.dzf.test.testcase.manage;

import org.testng.annotations.*;

import com.dzf.test.page.manage.ManageMainPage;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;

public class ManageMainPageTest implements ILogUtil {
	private ManageMainPage mainPage;

	@BeforeClass
	public void setup() {
		mainPage = new ManageMainPage();
	}

	@Test
	public boolean testOpenMyCustomer() throws InterruptedException, MyException {
		boolean result = false;

		mainPage.openMyCustomer();
		mainPage.open用户管理();
		mainPage.open建账调整();
		mainPage.open会计公司权限分配();

		return result;
	}

	@AfterClass
	public void teardown() throws Exception {
		logger.info("【" + this.getClass().getSimpleName() + "】运行完毕！");
	}
}
