package com.dzf.test.testscenery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.dzf.test.model.Voucher;
import com.dzf.test.model.Voucher.VoucherSubject;
import com.dzf.test.page.accounting.AccountingLoginPage;
import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.凭证管理.*;
import com.dzf.test.page.accounting.基础设置_库存管理.*;
import com.dzf.test.page.accounting.基础设置_总账.*;
import com.dzf.test.page.accounting.库存报表.库存成本表Page;
import com.dzf.test.page.accounting.日常业务.*;
import com.dzf.test.page.accounting.科目账表.*;
import com.dzf.test.page.accounting.结账办理.期末处理Page;
import com.dzf.test.page.accounting.财务账表.*;
import com.dzf.test.page.accounting.资产报表.*;
import com.dzf.test.page.accounting.资产管理.卡片管理Page;
import com.dzf.test.util.DateCalculatorUtil;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;

/**
 * @author Administrator
 * @function 13小企业 成本比例结转 启用外币 启用固定资产
 */
public class TestScenery3 implements ILogUtil {

	private Map<String, String> args = new HashMap<String, String>();

	private AccountingMainPage mainPage;

	private 利润表季报page 利润表季报;
	private 利润表Page 利润表;
	private 现金流量表Page 现金流量表;
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

	private 会计科目Page 会计科目;

	private 商品类别Page 商品类别;
	private 计量单位Page 计量单位;
	private 商品Page 商品;
	private 出库单Page 出库单;
	private 入库单Page 入库单;
	private 库存成本表Page 库存成本表;
	private 库存期初Page 库存期初;

	private 科目期初余额Page 科目期初余额;
	private 填制凭证Page 填制凭证;
	private 凭证管理Page 凭证管理;
	private 期末处理Page 期末处理;

	@BeforeClass
	public void initAllPages() throws Exception {
		// loginPage = new AccountingLoginPage();
		mainPage = new AccountingMainPage();

		利润表季报 = new 利润表季报page();
		利润表 = new 利润表Page();
		new 收入支出表Page();
		现金流量表 = new 现金流量表Page();
		new 业务活动表Page();
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

		会计科目 = new 会计科目Page();

		商品类别 = new 商品类别Page();
		计量单位 = new 计量单位Page();
		商品 = new 商品Page();
		出库单 = new 出库单Page();
		入库单 = new 入库单Page();
		库存成本表 = new 库存成本表Page();
		库存期初 = new 库存期初Page();

		科目期初余额 = new 科目期初余额Page();
		填制凭证 = new 填制凭证Page();
		new 卡片管理Page();
		凭证管理 = new 凭证管理Page();
		期末处理 = new 期末处理Page();

	}

	@BeforeTest
	@Parameters({ "用户名", "密码", "公司名称" })
	public void login(String username, String password, String company) throws Exception {

		Assert.assertTrue(new AccountingLoginPage().login(username, password, company));

	}

	@AfterTest
	public void logout() throws InterruptedException, MyException {
		mainPage.logout();
	}

	/*
	 * 设置会计科目 启用数量核算
	 */
	@Test(description = "启用数量核算")
	@Parameters({ "科目一", "科目二", "科目三" })
	public void modify会计科目(String subject1, String subject2, String subject3) throws InterruptedException, MyException {
		mainPage.openFrame("会计科目");
		会计科目.modify(subject1, null, null, "是", null, null, null);
		会计科目.modify(subject2, null, null, "是", null, null, null);
		会计科目.modify(subject3, null, null, "是", null, null, null);
	}

	/*
	 * 添加商品类别
	 */
	@Test(description = "添加商品类别", dependsOnMethods = "modify会计科目")
	@Parameters({ "商品类别编码", "商品类别名称", "备注" })
	public void addGoodsClasses(String code, String name, String comment) throws MyException, InterruptedException {
		try {
			mainPage.openFrame("商品类别");
			商品类别.add(code, name, comment);

		} catch (MyException e) {
			logger.error("增加商品类别失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("增加商品类别失败！");
			throw e;
		}
	}

	/*
	 * 设置计量单位
	 */
	@Test(description = "设置计量单位", dependsOnMethods = "addGoodsClasses")
	@Parameters({ "计量单位编码", "计量单位名称", "备注" })
	public void addUnitOfMeasurement(String code, String name, String comment)
			throws MyException, InterruptedException {
		try {
			mainPage.openFrame("计量单位");
			计量单位.add(code, name, comment);

		} catch (MyException e) {
			logger.error("增加计量单位失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("增加计量单位失败！");
			throw e;
		}
	}

	/*
	 * 添加商品
	 */
	@Test(description = "添加商品", dependsOnMethods = "addUnitOfMeasurement")
	@Parameters({ "商品科目", "商品编码", "商品名称", "商品简称", "商品分类", "商品规格", "商品型号", "单位", "备注" })
	public void addGoods(String subject, String code, String name, String shortName, String 分类, String 规格, String 型号,
			String unit, String comment) throws MyException, InterruptedException {
		try {
			mainPage.openFrame("商品");
			商品.add(subject, code, name, shortName, 分类, 规格, 型号, unit, comment);
			// 商品.add("库存商品", "10", "老母鸡", "老母鸡", "海鲜", null, null, "只", null);

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("增加商品失败！");
			throw e;
		}
	}

	/*
	 * 设置库存期初
	 */
	@Test(description = "设置库存期初", dependsOnMethods = "addGoods")
	@Parameters({ "商品名称", "数量", "成本", "备注" })
	public void set库存期初(String goodsname, String count, String cost, String comment)
			throws MyException, InterruptedException {
		try {
			mainPage.openFrame("库存期初");
			库存期初.add(goodsname, count, cost, comment);

			args.put("库存期初", 库存期初.getTotalCost());

		} catch (MyException e) {
			logger.error("设置库存期初失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("设置库存期初失败！");
			throw e;
		}
	}

	/*
	 * 期初试算平衡
	 */
	@Test(dependsOnMethods = { "set库存期初" })
	@Parameters({ "科目一", "修改项一", "科目二", "修改项二" })
	public void test期初试算平衡(String subject1, String project1, String subject2, String project2)
			throws InterruptedException, MyException {
		try {
			mainPage.openFrame("科目期初余额");

			科目期初余额.modify(subject1, "人民币", args.get("库存期初"), project1);

			科目期初余额.modify(subject2, "人民币", args.get("库存期初"), project2);

			科目期初余额.trialBalancing();

			// Assert.assertEquals(科目期初余额.getBalanceInfo(), "平衡", "期初是否平衡");

		} catch (MyException e) {
			logger.error("期初试算平衡失败！", e);
			Reporter.log("期初试算平衡失败！");
			throw e;
		}
	}

	@Test(dependsOnMethods = { "test期初试算平衡" }, groups = "填制凭证")
	@Parameters({ "凭证摘要", "科目一名称", "科目一方向", "科目一是否启用库存", "库存商品名称", "数量", "单价", "科目二名称", "科目二方向", "科目二金额" })
	public void test填制购入凭证(String 摘要, String 科目1, String 科目1方向, boolean 科目1启用库存, String 科目1stockGoodsName,
			String 科目1count, String 科目1unitPrice, String 科目2, String 科目2方向, String 科目2money)
					throws InterruptedException, MyException {
		try {
			mainPage.openFrame("填制凭证");

			Voucher voucher = new Voucher(摘要);

			VoucherSubject s1 = voucher.new VoucherSubject(科目1, 科目1方向, null, false, null, null, null, 科目1启用库存,
					科目1stockGoodsName, 科目1count, 科目1unitPrice);
			VoucherSubject s2 = voucher.new VoucherSubject(科目2, 科目2方向, 科目2money, false, null, null, null, false, null,
					null, null);

			List<VoucherSubject> subjectList = new ArrayList<VoucherSubject>();
			subjectList.add(s1);
			subjectList.add(s2);
			voucher.setSubjectList(subjectList);

			填制凭证.saveVoucher(voucher);

		} catch (MyException e) {
			logger.error("填制凭证操作失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("填制凭证操作失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = { "test期初试算平衡" })
	@Parameters({ "凭证摘要", "科目一名称", "科目一方向", "科目一是否启用库存", "库存商品名称", "数量", "单价", "科目二名称", "科目二方向", "科目二金额" })
	public void test填制卖出凭证(String 摘要, String 科目1, String 科目1方向, boolean 科目1启用库存, String 科目1stockGoodsName,
			String 科目1count, String 科目1unitPrice, String 科目2, String 科目2方向, String 科目2money)
					throws InterruptedException, MyException {
		try {
			mainPage.openFrame("填制凭证");

			Voucher voucher = new Voucher(摘要);

			VoucherSubject s1 = voucher.new VoucherSubject(科目1, 科目1方向, null, false, null, null, null, 科目1启用库存,
					科目1stockGoodsName, 科目1count, 科目1unitPrice);
			VoucherSubject s2 = voucher.new VoucherSubject(科目2, 科目2方向, 科目2money, false, null, null, null, false, null,
					null, null);

			List<VoucherSubject> subjectList = new ArrayList<VoucherSubject>();
			subjectList.add(s1);
			subjectList.add(s2);
			voucher.setSubjectList(subjectList);

			填制凭证.saveVoucher(voucher);

		} catch (MyException e) {
			logger.error("填制凭证操作失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("填制凭证操作失败！");
			throw e;
		}

	}

	@Test(dependsOnMethods = "test填制购入凭证", description = "填完购入凭证直接查询入库单")
	@Parameters({ "开始日期", "结束日期", "商品名称" })
	public void test入库单(/* 开始日期 eg:2015-01-01 */String beginDate,
			/* 开始日期 eg:2015-01-01 */String endDate, /* 商品名称 */String goodsname)
					throws MyException, InterruptedException {
		try {
			mainPage.openFrame("入库单");
			入库单.search(beginDate, endDate, goodsname);

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询入库单失败！");
			throw e;
		}
	}

	@Test(dependsOnGroups = { "填制凭证" }, description = "凭证审核记账")
	@Parameters({ "查询方式", "开始日期", "结束日期" })
	public void auditAndAccountVouchers(String byDateOrPeriod, String beginDate, String endDate)
			throws InterruptedException, MyException {
		try {
			mainPage.openFrame("凭证管理");

			凭证管理.refresh();

			凭证管理.searchVoucher(byDateOrPeriod, beginDate, endDate, null, null, null, null, null, null, null, null);

			凭证管理.selectAll();

			凭证管理.auditVoucher();

			凭证管理.accountVoucher();

		} catch (MyException e) {
			logger.error("凭证管理操作失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("凭证管理操作失败！");
			throw e;
		}
	}

	@Test(description = "期末处理", dependsOnMethods = { "auditAndAccountVouchers" })
	@Parameters({ "开始日期", "结束日期" })
	public void test期末处理(String beginDate, String endDate) throws InterruptedException, MyException, ParseException {

		try {
			mainPage.openFrame("期末处理");
			期末处理.search(null, beginDate, endDate, false, false);

			List<String> periodList = DateCalculatorUtil.dateToStrList(beginDate, endDate);
			for (String period : periodList) {
				期末处理.deSelectAll();
				期末处理.selectPeriod(period);
				期末处理.成本结转();
			}

			期末处理.selectAll();
			// 期末处理.反成本结转();
			期末处理.计提折旧();
			// 期末处理.反计提折旧();

			期末处理.期间损益结转();
			// 期末处理.反期间损益结转();

		} catch (MyException e) {
			logger.error("期末处理失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("期末处理失败！");
			throw e;
		}

	}

	/*
	 * 
	 */
	@Test(dependsOnMethods = "test期末处理")
	@Parameters({ "开始日期", "结束日期", "商品名称" })
	public void test出库单(/* 开始日期 eg:2015-01-01 */String beginDate,
			/* 开始日期 eg:2015-01-01 */String endDate, /* 商品名称 */String goodsname)
					throws MyException, InterruptedException {
		try {
			mainPage.openFrame("出库单");
			出库单.search(beginDate, endDate, goodsname);

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查询出库单失败！");
			throw e;
		}
	}

	@Test(dependsOnMethods = "test期末处理")
	@Parameters({ "截止日期", "商品名称" })
	public void test库存成本表(/* 截止日期 eg:2015-01-01 */String date, /* 商品名称 */String goodsname)
			throws MyException, InterruptedException {
		try {
			mainPage.openFrame("库存成本表");
			库存成本表.search(date, goodsname);

		} catch (MyException e) {
			logger.error("查询库存成本表失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("查询库存成本表失败！");
			throw e;
		}
	}

	
	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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

	@Test(dependsOnMethods = { "test期末处理" })
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
