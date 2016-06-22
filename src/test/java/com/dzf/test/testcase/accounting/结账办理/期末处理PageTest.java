package com.dzf.test.testcase.accounting.结账办理;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.结账办理.期末处理Page;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.WebTableUtil;

public class 期末处理PageTest implements ILogUtil{
	private AccountingMainPage mainPage;
	private 期末处理Page 期末处理Page;
	
	@BeforeClass
	public void setup() throws Exception {
		logger.info("【QimochuliPageTest】开始运行");
		mainPage = new AccountingMainPage();
		期末处理Page = new 期末处理Page();
	}
	
	@Parameters({"损益结转开始年月","损益结转结束年月"})
	@Test(groups={"logged-in"},enabled=true)
	public void testQiJianSunYiJieZhuan(String beginDate,String endDate) throws InterruptedException, MyException{
		
		mainPage.open期末处理();
		
//		期末处理Page.期间损益结转(beginDate,endDate);
		
		WebElement table = 期末处理Page.getWebElement(By.xpath(".//*[@id='dataGrid']/div/div/div/div[2]/div[2]/table"));// /tbody/tr/td[6]/div/input
		
		WebTableUtil tableUtil = new WebTableUtil(table);
		
		System.out.println(tableUtil.getRowCount());
		
		for (int i = 0; i < tableUtil.getRowCount(); i++) {
			
			String value = tableUtil.getCell(i, 5).findElement(By.tagName("input")).getAttribute("value");
			
			logger.info(i+":"+tableUtil.getCell(i, 1).getText()+":"+value);
			
			if(value.equals("是") == false){
				assertTrue(false);
			}
		}
		
		assertTrue(true);
		
	}
	
	
}
