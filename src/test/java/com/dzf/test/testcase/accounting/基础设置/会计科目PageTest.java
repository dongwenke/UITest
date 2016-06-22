package com.dzf.test.testcase.accounting.基础设置;

import org.testng.Assert;
import org.testng.annotations.*;

import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.基础设置_总账.AccountingSubjectSetPage;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;

/**
 * @author 董文科
 * @see 会计科目设置页用例
 */
public class 会计科目PageTest implements ILogUtil{
	private AccountingMainPage mainPage;
	private AccountingSubjectSetPage subjectSetPage;
	
	@BeforeClass
	public void setUp() throws Exception{
		logger.info("【AccountingSubjectSetPageTest】开始运行");
		mainPage = new AccountingMainPage();
		subjectSetPage = new AccountingSubjectSetPage();
	}
	
	@Test
	public void testModify() throws InterruptedException, MyException{
		mainPage.open会计科目();
		
		boolean result = subjectSetPage.modify();
		
		Assert.assertTrue(result);
		
	}

}
