package com.dzf.test.page.accounting;

import java.io.IOException;
import java.util.Iterator;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class PrintVoucherPage extends Handler {
	
	public PrintVoucherPage(){
		super();
		try {
			page = XMLUtil.convert("./config/page/accounting/printVoucherPage.xml", Page.class.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printVoucher() throws MyException {
		
		String prePage = driver.getWindowHandle();

		//切换选项卡
		Iterator<String> it = driver.getWindowHandles().iterator();//获得所有窗口句柄报错
		while(it.hasNext()){
			driver.switchTo().window(it.next());
			if(driver.getTitle().contains("print")){
				break;
			}
		}
		
		logger.info(driver.getCurrentUrl());
		
//		Actions action = new Actions(driver);
//		action.moveByOffset(100, 5).perform();
//		action.sendKeys(Keys.CONTROL,"p").perform();
		
		try {
			//打印时使用foxit输出为pdf
			Runtime.getRuntime().exec("./tools/confirmPrint.exe");
//			Desktop.getDesktop().open(new File("tools/confirmPrint.exe"));
			//点击打印按钮
			click("打印按钮");
			Thread.sleep(2000);
			Runtime.getRuntime().exec("./tools/saveAsPDF.exe");
			Thread.sleep(2000);
			Runtime.getRuntime().exec("./tools/confirmSaveAsPDF.exe");
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("打印完毕！");
		
		//关闭当前打印窗口
		driver.close();
		
		System.out.println("当前打印窗口已关闭!");
		//切换回先前的页面
		driver.switchTo().window(prePage);
		System.out.println("driver已切换到先前的页面！");
		
	}

}
