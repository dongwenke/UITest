package com.dzf.test.page.accounting.资产管理;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.model.Voucher;
import com.dzf.test.model.Voucher.VoucherSubject;
import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.凭证管理.填制凭证Page;
import com.dzf.test.util.DatePickerUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class 原值变更Page extends Handler {

	private final String xmlfile = "./config/page/accounting/资产管理/" + this.getClass().getSimpleName() + ".xml";

	public 原值变更Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	/*
	 * 默认不输入条件查询
	 */
	public boolean search() throws MyException {
		search(null, null, null, -1);
		return false;
	}

	/*
	 * 输入开始和结束日期查询
	 */
	public boolean search(String beginDate, String endDate) throws MyException {
		search(beginDate, endDate, null, -1);
		return false;
	}

	/*
	 * 输入开始日期结束日期和资产名称查询
	 */
	public boolean search(String beginDate, String endDate, String assetsName) throws MyException {
		search(beginDate, endDate, assetsName, -1);
		return false;
	}

	/*
	 * 输入开始日期结束日期卡片编码查询
	 */
	public boolean search(String beginDate, String endDate, int CardNum) throws MyException {
		search(beginDate, endDate, null, CardNum);
		return false;
	}

	/*
	 * 输入开始日期，结束日期，资产名称，卡片编码查询
	 */
	public boolean search(String beginDate, String endDate, String assetsName, int CardNum) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("原值变更");

			click("查询按钮");

			if (beginDate != null && !beginDate.equals("")) {
				click("查询面板-开始日期选择按钮");

				new DatePickerUtil(getWebElement("查询面板-开始日期选择器")).choseDate(beginDate);

			}
			if (endDate != null && !endDate.equals("")) {
				click("查询面板-结束日期选择按钮");

				new DatePickerUtil(getWebElement("查询面板-结束日期选择器")).choseDate(endDate);

			}
			if (assetsName != null && !assetsName.equals("")) {
				click("查询面板-资产选择按钮");
				new WebTableUtil(getWebElement("资产参照面板-资产列表table")).getTr(assetsName);
				click("资产参照面板-确认按钮");
			}

			if (CardNum != -1) {
				input("查询面板-卡片编码输入框", String.valueOf(CardNum));
			}

			click("查询面板-确定按钮");
			return false;
		} catch (MyException e) {
			Reporter.log("查询失败！");
			throw e;
		}
	}

	/*
	 * 添加原值变更 警告：变更记录列表无法区分who is who?
	 */
	public boolean add(String cardNameOrNum, String changeDate, String newOriginalValue) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("原值变更");

			// 获取当前变更记录列表数
			int oriCount = new WebTableUtil(getWebElement("变更记录列表数table")).getRowCount();

			click("增加按钮");

			click("新增面板-资产卡片选择按钮");

			click(new WebTableUtil(getWebElement("资产参照面板-资产列表table")).getTr(cardNameOrNum));

			click("资产参照面板-确认按钮");

			click("新增面板-变更日期选择按钮");
			new DatePickerUtil(getWebElement("新增面板-变更日期时间选择器")).choseDate(changeDate);

			double oriValue = new Double(getWebElement("变更前原值输入框").getAttribute("value").replace(",", ""));

			if (oriValue == new Double(newOriginalValue)) {
				Reporter.log("设定的变更后原值与变更前原值一致，未发生变更，自动变更为变更前原值+1");
				newOriginalValue = String.valueOf(new Double(newOriginalValue) + 1);
			}

			actionInput("新增面板-变更后原值输入框", newOriginalValue);

			click("新增面板-保存按钮");

			// 判断添加成功条件：变更记录列表新增一条
			return new WebTableUtil(getWebElement("变更记录列表数table")).getRowCount() == oriCount + 1;
		} catch (MyException e) {
			Reporter.log("查询失败！");
			throw e;
		}
	}

	/*
	 * 无法确定当前删除条件 暂不实现
	 */
	public boolean delete(String cardID, int index) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("原值变更");

			int oriCount = new WebTableUtil(getWebElement("变更记录列表数table")).getRowCount();

			click(new WebTableUtil(getWebElement("变更记录列表table")).getTr(index, cardID));

			boolean 已转总账 = new WebTableUtil(getWebElement("变更记录列表table")).getTr(index, cardID)
					.findElement(By.xpath("./td[@field='togl']//input")).getAttribute("checked").equals("true");

			if (已转总账) {
				throw new MyException("卡片：" + cardID + "的原值变更历史已转总账！删除失败！");
			}

			click("删除按钮");

			click("删除提示面板-确定按钮");

			return new WebTableUtil(getWebElement("变更记录列表数table")).getRowCount() == oriCount - 1;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("删除失败！");
			throw e;
		}
	}

	/*
	 * 警告：无法确定刷新成功的标志
	 */
	public boolean refresh() throws MyException {

		try {
			switchToDefaultContent();
			switchToFrame("原值变更");

			click("刷新按钮");

			return true;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("刷新失败！");
			throw e;
		}
	}

	/*
	 * 
	 */
	public boolean convertLedger(String cardID, int index) throws Exception {
		try {
			switchToDefaultContent();
			switchToFrame("原值变更");

			click(new WebTableUtil(getWebElement("变更记录列表table")).getTr(index, cardID));

			click("转总账按钮");

			AccountingMainPage mainPage = new AccountingMainPage();
			if (!mainPage.LableisOpened("填制凭证")) {
				throw new MyException("填制凭证页面未打开！转总账失败！");
			}

			// 查询卡片所在的资产科目 sub
			mainPage.openFrame("卡片管理");
			卡片管理Page 卡片管理 = new 卡片管理Page();
			卡片管理.search("2015-01-01", "2020-01-01", null, null, null, null, null, null);
			String assetSubject = 卡片管理.getCardAttr(cardID, "资产类别");

			// 填制凭证科目1等于所在的资产科目 固定资产/ + sub

			Voucher voucher = new Voucher("原值变更转总账,资产卡片" + cardID);

			VoucherSubject s1 = voucher.new VoucherSubject("固定资产/" + assetSubject, null, "-2", false, null, null, null,
					false, null, null, null);
			VoucherSubject s2 = voucher.new VoucherSubject("库存现金", null, "-2", false, null, null, null, false, null,
					null, null);

			List<VoucherSubject> subjectList = new ArrayList<VoucherSubject>();
			subjectList.add(s1);
			subjectList.add(s2);
			voucher.setSubjectList(subjectList);

			mainPage.switchToLable("填制凭证");
			填制凭证Page 填制凭证 = new 填制凭证Page();
			填制凭证.saveVoucher(voucher);

			mainPage.switchToLable("原值变更");

			switchToFrame("原值变更");

			return new WebTableUtil(getWebElement("变更记录列表table")).getTr(cardID)
					.findElement(By.xpath("./td[@field='togl']//input")).getAttribute("checked").equals("true");
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("转总账失败！");
			throw e;
		}
	}

	public boolean 联查凭证(String cardID, int index) throws Exception {

		try {
			switchToDefaultContent();
			switchToFrame("原值变更");

			WebElement target = new WebTableUtil(getWebElement("变更记录列表table")).getTr(index, cardID);
			
			boolean 已转总账 = target.findElement(By.xpath("./td[@field='togl']//input")).getAttribute("checked")
					.equals("true");

			if (!已转总账) {
				throw new MyException("卡片：" + cardID + "的原值变更历史尚未转总账！联查凭证失败！");
			}
			
			click(target);
			
			click("联查凭证按钮");

			AccountingMainPage mainPage = new AccountingMainPage();

			return mainPage.LableisOpened("凭证");
		} catch (MyException e) {
			Reporter.log("联查凭证失败！");
			throw e;
		}
	}
}
