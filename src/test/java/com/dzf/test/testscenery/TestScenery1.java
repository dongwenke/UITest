package com.dzf.test.testscenery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.dzf.test.page.accounting.AccountingLoginPage;
import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.凭证管理.凭证管理Page;
import com.dzf.test.page.accounting.凭证管理.填制凭证Page;
import com.dzf.test.page.accounting.基础设置_总账.科目期初余额Page;
import com.dzf.test.page.accounting.科目账表.*;
import com.dzf.test.page.accounting.结账办理.期末处理Page;
import com.dzf.test.page.accounting.财务账表.*;
import com.dzf.test.util.DateCalculatorUtil;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;

public class TestScenery1 {
	private AccountingMainPage mainPage;

	private 利润表季报page 利润表季报;
	private 利润表Page 利润表;
	private 收入支出表Page 收入支出表;
	private 现金流量表Page 现金流量表;
	private 业务活动表Page 业务活动表;
	private 增值税和营业税月度申报对比表Page 增值税和营业税月度申报对比表;
	private 资产负债表Page 资产负债表;

	private 发生额及余额表Page 发生额及余额表;
	private 科目汇总表Page 科目汇总表;
	private 科目明细账Page 科目明细帐;
	private 科目总账Page 科目总账;
	private 数量金额明细账Page 数量金额明细帐;
	private 数量金额总账Page 数量金额总账;
	private 现金银行日记账Page 现金银行日记账;
	private 序时账Page 序时账;

	private 科目期初余额Page 科目期初余额;
	private 填制凭证Page 填制凭证;
	private 凭证管理Page 凭证管理;
	private 期末处理Page 期末处理;
	
	@BeforeClass
	public void initAllPages() throws Exception {
		mainPage = new AccountingMainPage();

		利润表季报 = new 利润表季报page();
		利润表 = new 利润表Page();
		收入支出表 = new 收入支出表Page();
		现金流量表 = new 现金流量表Page();
		业务活动表 = new 业务活动表Page();
		增值税和营业税月度申报对比表 = new 增值税和营业税月度申报对比表Page();
		资产负债表 = new 资产负债表Page();

		科目总账 = new 科目总账Page();
		科目明细帐 = new 科目明细账Page();
		现金银行日记账 = new 现金银行日记账Page();
		发生额及余额表 = new 发生额及余额表Page();
		序时账 = new 序时账Page();
		科目汇总表 = new 科目汇总表Page();
		数量金额明细帐 = new 数量金额明细账Page();
		数量金额总账 = new 数量金额总账Page();

		科目期初余额 = new 科目期初余额Page();
		填制凭证 = new 填制凭证Page();
		凭证管理 = new 凭证管理Page();
		期末处理 = new 期末处理Page();

	}

	@BeforeTest
	@Parameters({ "用户名", "密码", "公司名称" })
	public void login(String username, String password, String company) throws Exception {
		Assert.assertTrue(new AccountingLoginPage().login(username, password, company));
	}

	@AfterTest(enabled = false)
	public void logout() throws InterruptedException, MyException {
		mainPage.logout();
	}

	@Test(description = "设置科目期初", priority = 1)
	@Parameters({ "科目一", "币别一", "修改项一", "金额一", "科目二", "币别二", "修改项二", "金额二" })
	public void test期初试算平衡(String subject1, String currency1, String project1, String num1, String subject2,
			String currency2, String project2, String num2) throws InterruptedException, MyException {
		try {
			mainPage.openFrame("科目期初余额");
			科目期初余额.modify(subject1, currency1, num1, project1);
			科目期初余额.modify(subject2, currency2, num2, project2);

			科目期初余额.trialBalancing();
			科目期初余额.getBalanceInfo();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("期初试算平衡失败！");
			throw e;
		}

	}

	@Test(description = "期初试算平衡其它按钮", priority = 2)
	public void test期初试算平衡其它按钮() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("科目期初余额");

			科目期初余额.fixedAssetsSyncButton();

			科目期初余额.refresh();

			科目期初余额.print();
		} catch (MyException e) {
			Reporter.log("期初试算平衡其它按钮操作失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = "test期初试算平衡", priority = 3)
	@Parameters({ "摘要", "科目一", "金额", "科目二" })
	public void test填制凭证(String summary, String subject1, String num, String subject2)
			throws InterruptedException, MyException {
		try {
			mainPage.openFrame("填制凭证");
			填制凭证.saveVoucher(summary, subject1, null, null, num, subject2);

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("填制凭证失败！");
			throw e;
		}

	}

	@Test(priority = 4)
	@Parameters({ "摘要", "科目一", "金额", "科目二", "原始日期", "目标日期" })
	public void test填制凭证其它功能(String summary, String subject1, String num, String subject2, String originalDate,
			String targetDate) throws InterruptedException, MyException {
		try {
			mainPage.openFrame("填制凭证");

			填制凭证.saveVoucher(summary, subject1, null, null, num, subject2);

			String now = new SimpleDateFormat("yyMMdd-hhms").format(new Date());
			填制凭证.saveAsCommonTemplet(now, "自动模版" + now);

			填制凭证.delete();

			mainPage.openFrame("填制凭证");
			填制凭证.billImage();

			填制凭证.copyByMonthOnFill(originalDate, targetDate);

			mainPage.openFrame("填制凭证");
			填制凭证.commonTemplet("", "100");

			填制凭证.copy();

			填制凭证.audit();

			填制凭证.revAudit();

			填制凭证.copyByMonthOnSaved(originalDate, targetDate);

			填制凭证.print();

			// 填制凭证.红字回冲();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("填制凭证其它功能操作失败！");
			throw e;
		}

	}

	@Test( dependsOnMethods = "test填制凭证", priority = 5)
	@Parameters({ "查询方式", "开始日期", "结束日期" })
	public void test凭证管理(String byDateOrPeriod, String beginDate, String endDate)
			throws InterruptedException, MyException {
		try {
			mainPage.openFrame("凭证管理");

			凭证管理.refresh();

			凭证管理.searchVoucher(byDateOrPeriod, beginDate, endDate, null, null, null, null, null, null, null, null);

			凭证管理.selectAll();

			凭证管理.auditVoucher();

			凭证管理.accountVoucher();

			凭证管理.unAccountVoucher();

			凭证管理.unAuditVoucher();

			凭证管理.print();

			// 凭证管理.export();

			凭证管理.deSelectAll();

			凭证管理.selectAll();

			凭证管理.auditVoucher();

			凭证管理.accountVoucher();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("凭证管理操作失败！");
			throw e;
		}

	}

	@Test( dependsOnMethods = "test凭证管理", priority = 6)
	@Parameters({ "开始日期" })
	public void test期末处理(String beginDate) throws InterruptedException, MyException, ParseException {
		try {
			String endDate = new SimpleDateFormat("yyyy-MM").format(new Date());

			mainPage.openFrame("期末处理");
			期末处理.search(null, beginDate, endDate, false, false);

			期末处理.selectAll();
			List<String> periodList = DateCalculatorUtil.dateToStrList(beginDate, endDate);
			for (String period : periodList) {
				期末处理.deSelectAll();
				期末处理.selectPeriod(period);
				期末处理.反成本结转();
				期末处理.反计提折旧();
				期末处理.反期间损益结转();
				期末处理.汇兑损益调整();
				期末处理.取消汇兑调整();
				期末处理.成本结转();
				期末处理.计提折旧();
				期末处理.期间损益结转();
			}

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("期末处理失败！");
			throw e;
		}

	}

	@Test(priority = 7)
	public void test科目总账() throws InterruptedException, MyException {

		try {
			mainPage.openFrame("科目总账");
			科目总账.search(null, null, null, null, null, null, null, null, false, false, null);
			科目总账.print();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询科目总账失败！");
			throw e;
		}

	}

	@Test(priority = 7)
	public void test科目明细帐() throws InterruptedException, MyException {

		try {
			mainPage.openFrame("科目明细账");
			科目明细帐.search(null, null, null, null, null, null, null, null, true, true, null);
			科目明细帐.print();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询科目明细帐失败！");
			throw e;
		}

	}

	@Test(priority = 7)
	public void test现金银行日记账() throws InterruptedException, MyException {

		try {
			mainPage.openFrame("现金银行日记账");
			现金银行日记账.search(null, null, null, null, null, null, null, null, true, true, null);
			现金银行日记账.print();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询现金银行日记账失败！");
			throw e;
		}

	}

	@Test(priority = 7)
	public void test发生额及余额表() throws InterruptedException, MyException {

		try {
			mainPage.openFrame("发生额及余额表");
			发生额及余额表.search(null, null, null, null, null, null, null, null, true, false, null);
			发生额及余额表.print();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询发生额及余额表失败！");
			throw e;
		}

	}

	@Test(priority = 7)
	public void test序时账() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("序时账");
			序时账.search(null, null, null, null, null, null, null, null, false, false, null);
			序时账.print();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询序时账失败！");
			throw e;
		}

	}

	@Test(priority = 6)
	public void test科目汇总表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("科目汇总表");
			科目汇总表.search(null, null, null, null, null, null, null, null, false, false, null);
			科目汇总表.print();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询科目汇总表失败！");
			throw e;
		}

	}

	@Test(priority = 6)
	public void test数量金额明细帐() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("数量金额明细账");
			数量金额明细帐.search(null, null, null, null, null, null, null, null, false, false, null);
			数量金额明细帐.print();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询数量金额明细帐失败！");
			throw e;
		}

	}

	@Test(priority = 7)
	public void test数量金额总账() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("数量金额总账");
			数量金额总账.search(null, null, null, null, null, null, null, null, false, false, null);
			数量金额总账.print();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询数量金额总账失败！");
			throw e;
		}

	}

	@Test(priority = 7)
	public void test利润表季报() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("利润表季报");
			利润表季报.search(null, null, null, true);
			利润表季报.print();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询利润表季报失败！");
			throw e;
		}

	}

	@Test(priority = 6)
	public void test利润表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("利润表");
			利润表.search(null, null, false);
			利润表.print();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询利润表失败！");
			throw e;
		}

	}

	/*
	 * 13小企业不包含收入支出表
	 */
	@Test(enabled = false)
	public void test收入支出表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("收入支出表");
			收入支出表.search(null, null, false);
			收入支出表.print();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询收入支出表失败！");
			throw e;
		}

	}

	@Test(priority = 7)
	public void test现金流量表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("现金流量表");
			现金流量表.search(null, null);
			现金流量表.print();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询现金流量表失败！");
			throw e;
		}

	}

	/*
	 * 13小企业不包含该项
	 */

	@Test(enabled = false, priority = 7)
	public void test业务活动表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("业务活动表");
			业务活动表.search(null, null, false);
			业务活动表.print();

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询业务活动表失败！");
			throw e;
		}

	}

	@Test(priority = 7)
	public void test增值税和营业税月度申报对比表() throws InterruptedException, MyException {

		try {
			mainPage.openFrame("增值税和营业税月度申报对比表");
			增值税和营业税月度申报对比表.search(null, null);
			增值税和营业税月度申报对比表.print();
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询增值税和营业税月度申报对比表失败！");
			throw e;
		}
	}

	@Test(priority = 7)
	public void test资产负债表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("资产负债表");
			资产负债表.search(null, null, false, false);
			资产负债表.print();
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询资产负债表");
			throw e;
		}

	}

}
