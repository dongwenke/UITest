package com.dzf.test.page.accounting.基础设置_库存管理;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class 计量单位Page extends Handler {

	private final String xmlfile = "./config/page/accounting/基础设置_库存管理/" + this.getClass().getSimpleName() + ".xml";

	public 计量单位Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean add(String code, String name, String comment) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("计量单位");

			// 检查code是否存在
			if (unitExist(code)) {
				throw new MyException(code + "已存在！");
			}

			// 检查name是否存在
			if (unitExist(name)) {
				// 如果已存在，返回true
				throw new MyException(name + "已存在！");
			}

			// 如果不存在继续
			
			int rownum = new WebTableUtil(getWebElement("单位列表数table")).getRowCount() + 1;
			
			// 点击增加按钮
			click("增加按钮");

			// 调用edit方法
			boolean edit = edit(rownum, code, name, comment);

			return edit?unitExist(name):false;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}

	public boolean modify(String originCodeOrName, String code, String name, String comment) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("计量单位");

			// 检查原始条目是否存在
			if (!unitExist(originCodeOrName)) {
				throw new MyException(originCodeOrName + "不存在！无法修改！");
			}

			if (unitExist(code)) {
				throw new MyException(code + "已存在！");
			}

			// 检查name是否存在
			if (unitExist(name)) {
				// 如果已存在，返回true
				throw new MyException(name + "已存在！");
			}

			// 如果不存在继续
			
			//获取待修改项所在的row
			int rownum =Integer.valueOf(new WebTableUtil(getWebElement("单位列表table")).getTr(originCodeOrName).getAttribute("datagrid-row-index"))+1;
			// 点击增加按钮
			click("修改按钮");

			// 调用edit方法
			edit(rownum, code, name, comment);

			return unitExist(name);
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}

	public boolean delete(String originCodeOrName) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("计量单位");

			// 检查原始条目是否存在
			if (!unitExist(originCodeOrName)) {
				throw new MyException(originCodeOrName + "不存在！删除失败！");
			}

			click(new WebTableUtil(getWebElement("单位列表table")).getTr(originCodeOrName));

			click("删除按钮");
			// 调用edit方法

			click("确认删除按钮");//div[text()='你确定要删除吗?']/following-sibling::div/a[*='确定']

			return !unitExist(originCodeOrName);
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}

	public boolean edit(int rownum, String code, String name, String comment)
			throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("计量单位");
			
			WebElement 编码输入框 = getWebElement(
					By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='jldwbm']//input[following-sibling::input]"));

			WebElement 名称输入框 = getWebElement(
					By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='jldwmc']//input[following-sibling::input]"));
			;

			WebElement 备注输入框 = getWebElement(
					By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='bz']//input[following-sibling::input]"));
			;
			
			input(编码输入框, code);

			input(名称输入框, name);

			input(备注输入框, comment);

			click("保存按钮");

			return !isDisplayed("保存按钮");

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("编辑失败！");
			throw e;
		}

	}

	public boolean unitExist(String codeOrName) {
		boolean exist = false;
		try {
			new WebTableUtil(getWebElement("单位列表table")).getTr(codeOrName);
			exist = true;
		} catch (MyException e) {
		}
		// (//table)[4]
		return exist;
	}

}
