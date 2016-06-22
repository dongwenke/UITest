package com.dzf.test.page.accounting.基础设置_库存管理;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class 商品Page extends Handler {

	private final String xmlfile = "./config/page/accounting/基础设置_库存管理/" + this.getClass().getSimpleName() + ".xml";

	public 商品Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean add(String subject, String code, String name, String shortName, String 分类, String 规格, String 型号,
			String unit, String comment) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("商品");

			// 检查code是否存在
			if (unitExist(code)) {
				throw new MyException(code + "已存在！");
			}

			// 如果不存在继续

			// 获取总的条数
			// *[@id='dataGrid']/form/div/div/div[2]/div[1]页脚总条数

			int rownum = new WebTableUtil(getWebElement("商品列表数table")).getRowCount() + 1;

			// 点击增加按钮
			click("增加按钮");

			// 调用edit方法
			boolean edit = edit(rownum, subject, code, name, shortName, 分类, 规格, 型号, unit, comment);

			return edit ? unitExist(code) : false;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}

	public boolean modify(String originCode, String subject, String code, String name, String shortName, String 分类,
			String 规格, String 型号, String unit, String comment) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("商品");

			// 检查原始条目是否存在
			if (!unitExist(originCode)) {
				throw new MyException(originCode + "不存在！无法修改！");
			}

			if (unitExist(code)) {
				throw new MyException(code + "已存在！");
			}
			// 如果不存在继续

			// 获取待修改项所在的row
			int rownum = Integer.valueOf(
					new WebTableUtil(getWebElement("商品列表table")).getTr(originCode).getAttribute("datagrid-row-index"))
					+ 1;

			// 点击增加按钮
			click("修改按钮");

			// 调用edit方法
			boolean result = edit(rownum, subject, code, name, shortName, 分类, 规格, 型号, unit, comment);

			return result;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}

	public boolean delete(String originCodeOrName) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("商品");

			// 检查原始条目是否存在
			if (!unitExist(originCodeOrName)) {
				throw new MyException(originCodeOrName + "不存在！删除失败！");
			}

			click(new WebTableUtil(getWebElement("商品列表table")).getTr(originCodeOrName));

			click("删除按钮");
			// 调用edit方法

			click("确认删除按钮");// div[text()='你确定要删除吗?']/following-sibling::div/a[*='确定']

			return !unitExist(originCodeOrName);
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}

	public boolean edit(int rownum, String subject, String code, String name, String shortName, String 分类, String 规格,
			String 型号, String unit, String comment) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("商品");

			WebElement 编码输入框 = getWebElement(
					By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='spbm']//input[following-sibling::input]"));
			WebElement 名称输入框 = getWebElement(
					By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='spmc']//input[following-sibling::input]"));
			WebElement 简称输入框 = getWebElement(
					By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='jc']//input[following-sibling::input]"));
			WebElement 规格输入框 = getWebElement(
					By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='gg']//input[following-sibling::input]"));
			WebElement 型号输入框 = getWebElement(
					By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='xh']//input[following-sibling::input]"));
			WebElement 备注输入框 = getWebElement(
					By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='bz']//input[following-sibling::input]"));

			// 科目选择按钮(//table)[4]//tr[4]//td[@field='kmmc']//a
			click(getWebElement(By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='kmmc']//a")));
			click(new WebTableUtil(getWebElement("选择科目table")).getTr(subject));
			click("选择科目面板-保存按钮");

			input(编码输入框, code);

			input(名称输入框, name);

			input(简称输入框, shortName);

			click(getWebElement(By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='splxmc']//a")));
			click(new WebTableUtil(getWebElement("选择分类table")).getTr(分类));
			click("选择分类面板-保存按钮");

			input(规格输入框, 规格);

			input(型号输入框, 型号);

			click(getWebElement(By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='jldw']//a")));
			click(new WebTableUtil(getWebElement("选择计量单位table")).getTr(unit));
			click("选择计量单位面板-保存按钮");

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
			new WebTableUtil(getWebElement("商品列表table")).getTr(codeOrName);
			exist = true;
		} catch (MyException e) {
		}
		return exist;
	}
}
