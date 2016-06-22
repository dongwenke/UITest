package com.dzf.test.page.accounting.基础设置_库存管理;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class 商品类别Page extends Handler {

	private final String xmlfile = "./config/page/accounting/基础设置_库存管理/" + this.getClass().getSimpleName() + ".xml";

	public 商品类别Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean add(String code, String name, String comment) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("商品类别");

			// 检查code是否存在
			if (classExist(code)) {
				throw new MyException(code + "已存在！");
			}

			// 检查name是否存在
			if (classExist(name)) {
				// 如果已存在，返回true
				throw new MyException(name + "已存在！");
			}

			// 如果不存在继续
			// 点击增加按钮
			click("增加按钮");
			// 调用edit方法

			return edit(code, name, comment)?classExist(name):false;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}

	public boolean modify(String originCodeOrName, String code, String name, String comment) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("商品类别");

			// 检查原始条目是否存在
			if (!classExist(originCodeOrName)) {
				throw new MyException(originCodeOrName + "不存在！无法修改！");
			}

			if (classExist(code)) {
				throw new MyException(code + "已存在！");
			}

			// 检查name是否存在
			if (classExist(name)) {
				// 如果已存在，返回true
				throw new MyException(name + "已存在！");
			}

			click(new WebTableUtil(getWebElement("分类列表table")).getTr(originCodeOrName));

			// 如果不存在继续
			// 点击增加按钮
			click("修改按钮");
			// 调用edit方法

			return edit(code, name, comment)?classExist(name):false;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}

	public boolean delete(String originCodeOrName) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("商品类别");

			// 检查原始条目是否存在
			if (!classExist(originCodeOrName)) {
				throw new MyException(originCodeOrName + "不存在！删除失败！");
			}

			click(new WebTableUtil(getWebElement("分类列表table")).getTr(originCodeOrName));

			click("删除按钮");
			// 调用edit方法

			click("确认删除按钮");// div[text()='确认删除?']/following-sibling::div/a[*='确定']

			return !classExist(originCodeOrName);
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}

	public boolean refresh() throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("商品类别");

			click("刷新按钮");

			return true;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}

	private boolean edit(String code, String name, String comment) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("商品类别");

			input("修改分类面板-编码输入框", code);
			input("修改分类面板-名称输入框", name);
			input("修改分类面板-备注输入框", comment);

			click("修改分类面板-保存按钮");

			return !isDisplayed("修改分类面板-保存按钮");

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("编辑失败！");
			throw e;
		}

	}

	public boolean classExist(String codeOrName) {
		boolean exist = false;
		try {
			new WebTableUtil(getWebElement("分类列表table")).getTr(codeOrName);
			exist = true;
		} catch (MyException e) {
		}
		// (//table)[4]
		return exist;
	}

}
