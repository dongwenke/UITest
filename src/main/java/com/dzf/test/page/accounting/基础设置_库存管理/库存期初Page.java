package com.dzf.test.page.accounting.基础设置_库存管理;

import java.text.DecimalFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class 库存期初Page extends Handler {

	private final String xmlfile = "./config/page/accounting/基础设置_库存管理/" + this.getClass().getSimpleName() + ".xml";

	public 库存期初Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean add(String goodsname, String count, String cost, String comment) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("库存期初");

			// 检查code是否存在
			if (unitExist(goodsname)) {
				throw new MyException(goodsname + "已存在！");
			}

			// 如果不存在继续

			// 点击增加按钮
			click("增加按钮");

			// 调用edit方法
			boolean edit = edit(1, goodsname, count, cost, comment);

			return edit ? unitExist(goodsname) : false;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}

	public boolean modify(String originGoodsName,String goodsname, String count, String cost, String comment) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("库存期初");

			// 检查原始条目是否存在
			if (!unitExist(originGoodsName)) {
				throw new MyException(originGoodsName + "不存在！无法修改！");
			}

			// 如果不存在继续

			// 获取待修改项所在的row
			int rownum = Integer.valueOf(
					new WebTableUtil(getWebElement("商品列表table")).getTr(originGoodsName).getAttribute("datagrid-row-index"))
					+ 1;

			// 点击增加按钮
			click("修改按钮");

			// 调用edit方法
			boolean result = edit(rownum, goodsname, count, cost, comment);

			return result;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}

	public boolean delete(String originGoodsName) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("库存期初");

			// 检查原始条目是否存在
			if (!unitExist(originGoodsName)) {
				throw new MyException(originGoodsName + "不存在！删除失败！");
			}

			click(new WebTableUtil(getWebElement("商品列表table")).getTr(originGoodsName));

			click("删除按钮");
			// 调用edit方法

			click("确认删除按钮");// div[text()='你确定要删除吗?']/following-sibling::div/a[*='确定']

			return !unitExist(originGoodsName);
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}
	
	public String getTotalCost() throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("库存期初");

			Double total = 0.0;
			
			WebTableUtil t = new WebTableUtil(getWebElement("商品列表table"));
			
			for(int i=0;i<t.getRowCount();i++){
				total += new Double(t.getRow(i).findElement(By.xpath(".//td[@field='cb']/div")).getText().replace(",", ""));
			}
			
			return new DecimalFormat("#.00").format(total);
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加失败！");
			throw e;
		}
	}

	public boolean edit(int rownum,String goodsname, String count, String cost, String comment) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("库存期初");

			WebElement 数量输入框 = getWebElement(
					By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='sl']//input[following-sibling::input]"));
			WebElement 成本输入框 = getWebElement(
					By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='cb']//input[following-sibling::input]"));
			WebElement 备注输入框 = getWebElement(
					By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='bz']//input[following-sibling::input]"));
			
			// 科目选择按钮(//table)[4]//tr[4]//td[@field='kmmc']//a
			click(getWebElement(By.xpath("(//table)[4]//tr[" + rownum + "]//td[@field='spmc']//a")));
			click(new WebTableUtil(getWebElement("选择科目table")).getTr(goodsname));
			click("选择商品面板-保存按钮");

			input(数量输入框, count);

			input(成本输入框, cost);

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
