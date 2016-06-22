package com.dzf.test.page.manage;

import java.io.FileNotFoundException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.FileUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class ManageMainPage extends Handler {
	
	public ManageMainPage() {
		super();
		String xmlfile = "./config" + this.getClass().getName().replace("com.dzf.test", "").replace(".", "/") + ".xml";
		try {
			page = XMLUtil.convert(xmlfile, Page.class);
		} catch (FileNotFoundException e) {
			FileUtil.copyFile(templateXml, xmlfile);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean openMyCustomer() throws MyException{
		boolean result = false;
		/*
		 * 关闭我的客户
		 */
		closeLable("我的客户");
		
		switchToDefaultContent();
		/*
		 * 鼠标移到客户管理
		 */
		mouseMoveTo("客户管理菜单");
		/*
		 * 点击我的客户
		 */
		click("我的客户子菜单");
		/*
		 * 如果导航栏包含我的客户，返回成功
		 */
		if(isLableOpen("我的客户")){
			result = true;
		}
		
		return result;
	}
	
	public boolean open建账调整() throws MyException{
		boolean result = false;
		/*
		 * 关闭建账调整
		 */
		closeLable("建账调整");
		
		switchToDefaultContent();
		/*
		 * 鼠标移到客户管理
		 */
		mouseMoveTo("客户管理菜单");
		/*
		 * 点击建账调整
		 */
		click("建账调整子菜单");
		/*
		 * 如果导航栏包含建账调整，返回成功
		 */
		if(isLableOpen("建账调整")){
			result = true;
		}
		
		return result;
	}
	
	public boolean open用户管理() throws MyException{
		boolean result = false;
		/*
		 * 关闭用户管理
		 */
		closeLable("用户管理");
		
		switchToDefaultContent();
		/*
		 * 鼠标移到系统管理
		 */
		mouseMoveTo("系统管理菜单");
		/*
		 * 鼠标移到权限管理
		 */
		mouseMoveTo("权限管理子菜单");
		/*
		 * 点击用户管理
		 */
		click("用户管理孙菜单");
		/*
		 * 如果导航栏包含我的客户，返回成功
		 */
		if(isLableOpen("用户管理")){
			result = true;
		}
		
		return result;
	}
	
	public boolean open会计公司权限分配() throws MyException{
		boolean result = false;
		/*
		 * 关闭会计公司权限分配
		 */
		closeLable("会计公司权限分配");
		
		switchToDefaultContent();
		/*
		 * 鼠标移到系统管理
		 */
		mouseMoveTo("系统管理菜单");
		/*
		 * 鼠标移到权限管理
		 */
		mouseMoveTo("权限管理子菜单");
		/*
		 * 点击会计公司权限分配
		 */
		click("会计公司权限分配孙菜单");
		/*
		 * 如果导航栏包含我的客户，返回成功
		 */
		if(isLableOpen("会计公司权限分配")){
			result = true;
		}
		
		return result;
	}
	
	public void closeLable(String lableName) throws MyException {
		switchToDefaultContent();

		// 检查填制凭证是否显示如果显示关闭该标签页
		WebElement 标签栏 = getWebElement("全部标签页");

		List<WebElement> 按钮列表 = 标签栏.findElements(By.tagName("li"));

		for (WebElement tmp : 按钮列表) {
			List<WebElement> 按钮 = tmp.findElements(By.tagName("a"));

			String 按钮文字 = 按钮.get(0).getText();
			if (按钮文字.contains(lableName)) {
				按钮.get(1).click();
				System.out.println(lableName + "已关闭");
			}
		}
	}
	
	public boolean isLableOpen(String lableName) throws MyException{
		boolean result = false;
		
		switchToDefaultContent();

		// 检查填制凭证是否显示如果显示关闭该标签页
		WebElement 标签栏 = getWebElement("全部标签页");

		List<WebElement> 按钮列表 = 标签栏.findElements(By.tagName("li"));

		for (WebElement 按钮 : 按钮列表) {
			
			if (按钮.getText().contains(lableName)) {
				result = true;
				logger.info(lableName + "已打开");
			}
		}
		
		return result;
	}

}
