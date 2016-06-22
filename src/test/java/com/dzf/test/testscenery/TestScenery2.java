package com.dzf.test.testscenery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.dzf.test.page.accounting.AccountingLoginPage;
import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.凭证管理.凭证管理Page;
import com.dzf.test.page.accounting.凭证管理.填制凭证Page;
import com.dzf.test.page.accounting.基础设置_总账.会计科目Page;
import com.dzf.test.page.accounting.基础设置_总账.汇率档案Page;
import com.dzf.test.page.accounting.基础设置_总账.科目期初余额Page;
import com.dzf.test.page.accounting.科目账表.*;
import com.dzf.test.page.accounting.结账办理.期末处理Page;
import com.dzf.test.page.accounting.财务账表.*;
import com.dzf.test.page.accounting.资产报表.折旧汇总表Page;
import com.dzf.test.page.accounting.资产报表.资产与总账对账表Page;
import com.dzf.test.page.accounting.资产报表.资产总账Page;
import com.dzf.test.page.accounting.资产报表.资产折旧明细Page;
import com.dzf.test.page.accounting.资产报表.资产明细账Page;
import com.dzf.test.page.accounting.资产管理.卡片管理Page;
import com.dzf.test.page.accounting.资产管理.原值变更Page;
import com.dzf.test.page.accounting.资产管理.工作量管理Page;
import com.dzf.test.page.accounting.资产管理.资产清理Page;
import com.dzf.test.util.DateCalculatorUtil;
import com.dzf.test.util.DateUtil;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.RandomUtil;

/**
 * @author Administrator
 * @function 13小企业 成本比例结转 启用外币 启用固定资产
 */
public class TestScenery2 implements ILogUtil {
	// private AccountingLoginPage loginPage;
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

	private 折旧汇总表Page 折旧汇总表;
	private 资产明细账Page 资产明细账;
	private 资产与总账对账表Page 资产与总账对账表;
	private 资产折旧明细Page 资产折旧明细;
	private 资产总账Page 资产总账;

	private 汇率档案Page 汇率档案;
	private 会计科目Page 会计科目;
	private 科目期初余额Page 科目期初余额;
	private 填制凭证Page 填制凭证;
	private 卡片管理Page 卡片管理;

	private 原值变更Page 原值变更;
	private 资产清理Page 资产清理;
	private 工作量管理Page 工作量管理;

	private 凭证管理Page 凭证管理;
	private 期末处理Page 期末处理;

	public static Map<String, String> publicDate = new HashMap<>();

	@BeforeClass
	public void classSetUp() throws Exception {
		// loginPage = new AccountingLoginPage();
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

		折旧汇总表 = new 折旧汇总表Page();
		资产明细账 = new 资产明细账Page();
		资产与总账对账表 = new 资产与总账对账表Page();
		资产折旧明细 = new 资产折旧明细Page();
		资产总账 = new 资产总账Page();

		汇率档案 = new 汇率档案Page();
		会计科目 = new 会计科目Page();
		科目期初余额 = new 科目期初余额Page();
		填制凭证 = new 填制凭证Page();
		卡片管理 = new 卡片管理Page();

		原值变更 = new 原值变更Page();
		资产清理 = new 资产清理Page();
		工作量管理 = new 工作量管理Page();
		凭证管理 = new 凭证管理Page();
		期末处理 = new 期末处理Page();

	}

	@BeforeTest
	@Parameters({ "用户名", "密码", "公司名称" })
	public void setup(String username, String password, String company) throws Exception {
		Assert.assertTrue(new AccountingLoginPage().login(username, password, company));
	}

	@AfterTest(enabled = false)
	public void teardown() throws InterruptedException, MyException {
		mainPage.logout();
	}

	/*
	 * 设置汇率档案
	 */
	@Test(priority = 1)
	@Parameters({ "币种", "汇率", "折算模式", "是否浮动汇率", "备注" })
	public void test汇率档案(String currency, String rate, String translationMode, String isFloateRate, String comment)
			throws InterruptedException, MyException {
		mainPage.openFrame("汇率档案");
		汇率档案.add(currency, rate, translationMode, isFloateRate, comment);

		汇率档案.modify(currency, rate, translationMode, isFloateRate, comment);
	}

	/*
	 * 设置会计科目 启用外币
	 */
	@Test(priority = 2)
	@Parameters({ "科目", "币别" })
	public void test会计科目(String subject, String currency) throws InterruptedException, MyException {
		mainPage.openFrame("会计科目");
		会计科目.modify(subject, currency);
	}

	/*
	 * 期初录入固定资产卡片
	 */
	@Test(priority = 3)
	@Parameters({ "录入期间", "资产编码", "资产名称", "资产类别", "开始使用日期", "折旧方式", "预计使用年限", "固定(无形)资产科目", "结算科目", "折旧(摊销)科目",
			"折旧(摊销)费用科目", "原值", "是否期初", "期初信息备注" })
	public void test期初录入固定资产卡片(String date, String code, String name, String classesCode, String useDate,
			String depreciationMethod, String life, String assetSubject, String settlementSubject,
			String depreciationSubject, String depreciationCostSubject, String originalValue, boolean 是否期初,
			String 期初信息备注) throws MyException, InterruptedException {
		try {
			mainPage.openFrame("卡片管理");
			卡片管理.search("2010-01-01", "2020-12-31", null, null, null, null, null, null);

			if (code == null || code.equals("")) {
				// code = new
				// SimpleDateFormat("MdHmsSSS").format(Calendar.getInstance().getTime());
				code = RandomUtil.getRandomCharAndNumr(10);
			}

			for (int i = 0; i < 1; i = 0) {
				if (卡片管理.existAssetCode(code)) {
					Reporter.log("固定资产编码" + code + "已存在，使用新的随机编码");
					code = RandomUtil.getRandomCharAndNumr(10);
				} else {
					break;
				}
			}

			boolean addedCard = 卡片管理.add(date, code, name, classesCode, useDate, depreciationMethod, life, assetSubject,
					settlementSubject, depreciationSubject, depreciationCostSubject, originalValue, 是否期初, 期初信息备注);

			if (!addedCard) {
				throw new MyException("添加失败！");
			}

			String cardID = 卡片管理.getCardCode();
			String assetCode = 卡片管理.getAssetCode();

			publicDate.put("期初录入的固定资产卡片编码", cardID);
			publicDate.put("期初录入的固定资产卡片资产编码", assetCode);

			// Assert.assertTrue(added, "卡片已添加，编号：" + 卡片管理.getCardCode());

		} catch (MyException e) {
			logger.error("固定资产卡片管理操作失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("固定资产卡片管理操作失败！");
			throw e;
		}
	}

	/*
	 * 期初试算平衡
	 */
	@Test(/* dependsOnMethods = { "test汇率档案", "test会计科目", "test期初录入固定资产卡片" } */priority = 4)
	@Parameters({ "科目一", "币别一", "修改项一", "金额一", "科目二", "币别二", "修改项二", "金额二" })
	public void test期初试算平衡(String subject1, String currency1, String project1, String num1, String subject2,
			String currency2, String project2, String num2) throws InterruptedException, MyException {
		try {
			mainPage.openFrame("科目期初余额");
			科目期初余额.modify(subject1, currency1, num1, project1);
			科目期初余额.modify(subject2, currency2, num2, project2);

			科目期初余额.fixedAssetsSync();

			科目期初余额.trialBalancing();
			科目期初余额.getBalanceInfo();
			// Assert.assertEquals(科目期初余额.getBalanceInfo(), "平衡", "期初是否平衡");

		} catch (MyException e) {
			logger.error("期初试算平衡失败！", e);
			Reporter.log("期初试算平衡失败！");
			throw e;
		}

	}

	@Test( dependsOnMethods = { "test期初试算平衡" } ,priority = 5)
	@Parameters({ "摘要", "科目一", "汇率", "原币", "金额", "科目二" })
	public void test填制凭证(String summary, String subject1, String rate, String original, String num, String subject2)
			throws InterruptedException, MyException {
		try {
			mainPage.openFrame("填制凭证");
			填制凭证.saveVoucher(summary, subject1, rate, original, num, subject2);

			String now = new SimpleDateFormat("yyMMdd-hhms").format(new Date());
			填制凭证.saveAsCommonTemplet(now, "自动模版" + now);

		} catch (MyException e) {
			logger.error("填制凭证操作失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("填制凭证操作失败！");
			throw e;
		}

	}

	/*
	 * 期初录入固定资产卡片
	 */
	@Test( dependsOnMethods = { "test期初试算平衡" },priority = 6)
	@Parameters({ "录入期间", "资产编码", "资产名称", "资产类别", "开始使用日期", "折旧方式", "预计使用年限", "固定(无形)资产科目", "结算科目", "折旧(摊销)科目",
			"折旧(摊销)费用科目", "原值", "是否期初", "期初信息备注" })
	public void test期中录入固定资产卡片工作量法(String date, String code, String name, String classesCode, String useDate,
			String depreciationMethod, String life, String assetSubject, String settlementSubject,
			String depreciationSubject, String depreciationCostSubject, String originalValue, boolean 是否期初,
			String 期初信息备注) throws MyException, InterruptedException {
		try {
			mainPage.openFrame("卡片管理");

			卡片管理.search("2010-01-01", "2020-12-31", null, null, null, null, null, null);

			// if (code == null || code.equals("")) {
			// code = new
			// SimpleDateFormat("yyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
			// }
			//
			// if (卡片管理.existAssetCode(code)) {
			// Assert.assertTrue(false, "资产编码已存在！增加失败！");
			// }

			if (code == null || code.equals("")) {
				// code = new
				// SimpleDateFormat("MdHmsSSS").format(Calendar.getInstance().getTime());
				code = RandomUtil.getRandomCharAndNumr(10);
			}

			for (int i = 0; i < 1; i = 0) {
				if (卡片管理.existAssetCode(code)) {
					Reporter.log("固定资产编码" + code + "已存在，使用新的随机编码");
					code = RandomUtil.getRandomCharAndNumr(10);
				} else {
					break;
				}
			}

			卡片管理.add(date, code, name, classesCode, useDate, depreciationMethod, life, assetSubject, settlementSubject,
					depreciationSubject, depreciationCostSubject, originalValue, 是否期初, 期初信息备注);
			String cardID = 卡片管理.getCardCode();
			String assetCode = 卡片管理.getAssetCode();

			卡片管理.convertLedgerOnCardShow();

			卡片管理.assetCleanOnCardShow();

			publicDate.put("期中录入的固定资产卡片编码", cardID);
			publicDate.put("期中录入的固定资产卡片资产编码", assetCode);

			// Assert.assertTrue(true, "卡片：" + cardNumber + "已添加并转总账");
		} catch (MyException e) {
			Reporter.log("固定资产卡片管理操作失败！");
			throw e;
		}
	}

	@Test(dependsOnMethods = { "test期初录入固定资产卡片" }, priority = 6)
	@Parameters({ "卡片编号", "变更日期", "变更后原值" })
	public void test原值变更(String cardID, String changeDate, String newOriginalValue) throws Exception {
		try {
			mainPage.openFrame("原值变更");

			if (publicDate.get("期初录入的固定资产卡片编码") != null) {
				cardID = publicDate.get("期初录入的固定资产卡片编码");
			}

			changeDate = DateUtil.getCurrentDay();

			原值变更.add(cardID, changeDate, newOriginalValue);
			原值变更.search(changeDate, changeDate);
			原值变更.convertLedger(cardID, 0);
			原值变更.联查凭证(cardID, 0);

		} catch (MyException e) {
			Reporter.log("原值变更操作失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期中录入固定资产卡片工作量法" }, priority = 7)
	@Parameters({ "开始日期", "结束日期", "资产编码" })
	public void test资产清理(String beginDate, String endDate, String cardID) throws Exception {
		try {
			mainPage.openFrame("资产清理");

			if (publicDate.get("期中录入的固定资产卡片编码") != null) {
				cardID = publicDate.get("期中录入的固定资产卡片编码");
			}

			资产清理.searchByCardNumber(beginDate, endDate, cardID);
			资产清理.searchByCard(beginDate, endDate, cardID);
			资产清理.convertLedger(cardID);
			资产清理.联查凭证(cardID);

		} catch (MyException e) {
			Reporter.log("资产清理操作失败！");
			throw e;
		}
	}

	@Test(dependsOnMethods = { "test期中录入固定资产卡片工作量法" }, priority = 7)
	@Parameters({ "查询期间", "资产编码", "本月工作量" })
	public void test工作量管理(String periodDate, String assetCode, double 本月工作量) throws Exception {
		try {
			mainPage.openFrame("工作量管理");

			if (publicDate.get("期中录入的固定资产卡片资产编码") != null) {
				assetCode = publicDate.get("期中录入的固定资产卡片资产编码");
			}

			工作量管理.search(periodDate, null);
			工作量管理.modify(assetCode, 本月工作量);

		} catch (MyException e) {
			Reporter.log("工作量管理操作失败！");
			throw e;
		}

	}

	@Test( dependsOnMethods = { "test填制凭证" },priority = 8)
	@Parameters({ "查询方式", "开始日期" })
	public void test凭证管理(String byDateOrPeriod, String beginDate) throws InterruptedException, MyException {
		try {
			mainPage.openFrame("凭证管理");

			凭证管理.refresh();

			凭证管理.searchVoucher(byDateOrPeriod, beginDate, DateUtil.getCurrentMonth(), null, null, null, null, null,
					null, null, null);

			凭证管理.selectAll();

			凭证管理.auditVoucher();

			凭证管理.accountVoucher();

			// 凭证管理.unAccountVoucher();

			// 凭证管理.unAuditVoucher();

			凭证管理.print();

			// 凭证管理.export();

			凭证管理.deSelectAll();

		} catch (MyException e) {
			Reporter.log("凭证管理操作失败！");
			throw e;
		}
	}

	@Test(description = "期末处理" , dependsOnMethods = { "test凭证管理" } , priority = 10)
	@Parameters({ "开始日期", "结束日期" })
	public void test期末处理(String beginDate, String endDate) throws InterruptedException, MyException, ParseException {

		try {
			mainPage.openFrame("期末处理");

			endDate = DateUtil.getCurrentMonth();

			期末处理.search(null, beginDate, endDate, false, false);

			List<String> periodList = DateCalculatorUtil.dateToStrList(beginDate, endDate);
			for (String period : periodList) {
				期末处理.deSelectAll();
				期末处理.selectPeriod(period);
				期末处理.成本结转();

				期末处理.计提折旧();

				期末处理.汇兑损益调整();
				期末处理.取消汇兑调整();
			}

			期末处理.selectAll();
			// 期末处理.计提折旧();

			// 期末处理.deSelectAll();
			// 期末处理.selectPeriod(periodList.get(0));
			// 期末处理.汇兑损益调整();

			期末处理.期间损益结转();

		} catch (MyException e) {
			logger.error("期末处理失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("期末处理失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test科目总账() throws InterruptedException, MyException {

		try {
			mainPage.openFrame("科目总账");
			科目总账.search(null, null, null, null, null, null, null, null, false, false, null);
			科目总账.print();

		} catch (MyException e) {
			logger.error("查询科目总账失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询科目总账失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test科目明细帐() throws InterruptedException, MyException {

		try {
			mainPage.openFrame("科目明细账");
			科目明细帐.search(null, null, null, null, null, null, null, null, true, true, null);
			科目明细帐.print();

		} catch (MyException e) {
			logger.error("查询科目明细帐失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询科目明细帐失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test现金银行日记账() throws InterruptedException, MyException {

		try {
			mainPage.openFrame("现金银行日记账");
			现金银行日记账.search(null, null, null, null, null, null, null, null, true, true, null);
			现金银行日记账.print();

		} catch (MyException e) {
			logger.error("查询现金银行日记账失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询现金银行日记账失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test发生额及余额表() throws InterruptedException, MyException {

		try {
			mainPage.openFrame("发生额及余额表");
			发生额及余额表.search(null, null, null, null, null, null, null, null, true, false, null);
			发生额及余额表.print();

		} catch (MyException e) {
			logger.error("查询发生额及余额表失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询发生额及余额表失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test序时账() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("序时账");
			序时账.search(null, null, null, null, null, null, null, null, false, false, null);
			序时账.print();

		} catch (MyException e) {
			logger.error("查询序时账失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询序时账失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test科目汇总表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("科目汇总表");
			科目汇总表.search(null, null, null, null, null, null, null, null, false, false, null);
			科目汇总表.print();

		} catch (MyException e) {
			logger.error("查询科目汇总表失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询科目汇总表失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test数量金额明细帐() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("数量金额明细账");
			数量金额明细帐.search(null, null, null, null, null, null, null, null, false, false, null);
			数量金额明细帐.print();

		} catch (MyException e) {
			logger.error("查询数量金额明细帐失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询数量金额明细帐失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test数量金额总账() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("数量金额总账");
			数量金额总账.search(null, null, null, null, null, null, null, null, false, false, null);
			数量金额总账.print();

		} catch (MyException e) {
			logger.error("查询数量金额总账失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询数量金额总账失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test利润表季报() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("利润表季报");
			利润表季报.search(null, null, null, true);
			利润表季报.print();

		} catch (MyException e) {
			logger.error("查询利润表季报失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询利润表季报失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test利润表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("利润表");
			利润表.search(null, null, false);
			利润表.print();

		} catch (MyException e) {
			logger.error("查询利润表失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询利润表失败！");
			throw e;
		}

	}

	/*
	 * 13小企业不包含收入支出表
	 */
	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test收入支出表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("收入支出表");
			收入支出表.search(null, null, false);
			收入支出表.print();

		} catch (MyException e) {
			logger.error("查询收入支出表失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询收入支出表失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test现金流量表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("现金流量表");
			现金流量表.search(null, null);
			现金流量表.print();

		} catch (MyException e) {
			logger.error("查询现金流量表失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询现金流量表失败！");
			throw e;
		}

	}

	/*
	 * 13小企业不包含该项
	 */

	@Test(dependsOnMethods = { "test期末处理" }, enabled = false, priority = 11)
	public void test业务活动表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("业务活动表");
			业务活动表.search(null, null, false);
			业务活动表.print();

		} catch (MyException e) {
			logger.error("查询业务活动表失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询业务活动表失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test增值税和营业税月度申报对比表() throws InterruptedException, MyException {

		try {
			mainPage.openFrame("增值税和营业税月度申报对比表");
			增值税和营业税月度申报对比表.search(null, null);
			增值税和营业税月度申报对比表.print();
		} catch (MyException e) {
			logger.error("查询增值税和营业税月度申报对比表失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询增值税和营业税月度申报对比表失败！");
			throw e;
		}
	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test资产负债表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("资产负债表");
			资产负债表.search(null, null, false, false);
			资产负债表.print();
		} catch (MyException e) {
			logger.error("查询资产负债表失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询资产负债表失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test折旧汇总表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("折旧汇总表");
			折旧汇总表.search("2016-01", null, null, null, null);

		} catch (MyException e) {
			logger.error("查询折旧汇总表失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询折旧汇总表失败");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test资产明细账() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("资产明细账");
			资产明细账.search("2016-01-01", "2018-01-01", null, null, null);

		} catch (MyException e) {
			logger.error("查询资产明细账失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询资产明细账失败");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test资产与总账对账表() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("资产与总账对账表");
			资产与总账对账表.search("2016-01-01");

		} catch (MyException e) {
			logger.error("查询资产与总账对账表失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询资产与总账对账表失败");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test资产折旧明细() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("资产折旧明细");
			资产折旧明细.search("2016-01", "2016-12", null, null, null);

		} catch (MyException e) {
			logger.error("查询资产折旧明细失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询资产折旧明细失败");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期末处理" }, priority = 11)
	public void test资产总账() throws InterruptedException, MyException {
		try {
			mainPage.openFrame("资产总账");
			资产总账.search("2016-01-01", "2018-01-01", null);

		} catch (MyException e) {
			logger.error("查询资产总账失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询资产总账失败！");
			throw e;
		}

	}

}
