package com.dzf.test.page.accounting;

import org.openqa.selenium.*;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class AccountingMainPage extends Handler {
	private final String xmlfile = "./config/page/accounting/" + this.getClass().getSimpleName() + ".xml";

	public AccountingMainPage() throws Exception {
		super();

		page = XMLUtil.convert(xmlfile, Page.class.getName());
	}

	public String getUserInfo() throws MyException {
		try {
			switchToDefaultContent();

			return getText("用户信息");
		} catch (MyException e) {
			Reporter.log("获取用户信息失败！");
			throw e;
		}
	}

	public boolean quickSwitch() throws MyException, InterruptedException {
		boolean result = false;
		try {
			switchToDefaultContent();

			// Reporter.log("点击快速切换按钮");
			click("快速切换按钮");

			Thread.sleep(1000);

			// Reporter.log("点击日期选择按钮");
			click("快速切换-日期选择按钮");

			Thread.sleep(1000);

			// Reporter.log("点击年月选择按钮");
			click("快速切换-年月选择按钮");

			Thread.sleep(1000);

			// Reporter.log("点击十二月");
			click("快速切换-十二月");

			Thread.sleep(1000);

			// Reporter.log("点击31号");
			click("快速切换-31号");

			Thread.sleep(1000);

			// Reporter.log("点击确定按钮");
			click("快速切换-确定按钮");

			Thread.sleep(1500);

		} catch (WebDriverException e) {
			throw e;
		}
		return result;
	}

	@Deprecated
	public boolean open填制凭证() throws InterruptedException, MyException {
		boolean result = false;

		try {
			switchToDefaultContent();
			// 关闭填制凭证
			// closeLable("填制凭证");
			closeLable("填制凭证");
			// 打开填制凭证
			// 凭证管理是否显示
			if (isDisplayed("凭证管理") != true) {
				click("总账");
			}
			mouseMoveTo("凭证管理");

			click("填制凭证悬浮");

			mouseMoveTo("logo");
			Thread.sleep(800);

			result = LableisOpened("填制凭证");
		} catch (WebDriverException e) {
			throw e;
		}
		return result;
	}

	@Deprecated
	public boolean open凭证管理() throws InterruptedException, MyException {
		boolean result = false;

		try {
			switchToDefaultContent();

			closeLable("凭证管理");

			mouseMoveTo("凭证管理");

			click("凭证管理悬浮");

			mouseMoveTo("logo");

			Thread.sleep(800);

			result = LableisOpened("凭证管理");

		} catch (WebDriverException e) {
			throw e;
		}
		return result;

	}

	@Deprecated
	public boolean open科目期初余额() throws InterruptedException, MyException {

		try {
			boolean result = false;
			switchToDefaultContent();
			// 关闭科目期初余额
			// Reporter.log("关闭科目期初余额frame");
			closeLable("科目期初余额");

			// Reporter.log("左侧凭证管理是否已显示");
			if (!isDisplayed("凭证管理")) {
				// Reporter.log("总账没有打开");

				// Reporter.log("点击总账");
				click("总账");
			}

			// Reporter.log("鼠标移动到基础设置");
			mouseMoveTo("基础设置-总账");

			// Reporter.log("点击悬浮菜单-科目期初余额");
			click("科目期初余额悬浮");

			// Reporter.log("鼠标移动到logo上");
			mouseMoveTo("logo");

			Thread.sleep(800);

			result = LableisOpened("科目期初余额");

			return result;
		} catch (WebDriverException e) {
			throw e;
		}

	}

	@Deprecated
	public boolean open期末处理() throws InterruptedException, MyException {

		boolean result = false;
		try {
			switchToDefaultContent();
			// 关闭科目期初余额
			// Reporter.log("关闭期末处理frame");
			closeLable("期末处理");

			// Reporter.log("检查凭证管理是否显示");
			if (!isDisplayed("凭证管理")) {
				// Reporter.log("凭证管理没有显示");

				// Reporter.log("点击总账");
				click("总账");
			}

			// Reporter.log("鼠标移动到结账办理");
			mouseMoveTo("结账办理");

			// Reporter.log("点击悬浮菜单-期末处理");
			click("期末处理悬浮");

			// Reporter.log("鼠标移动到logo");
			mouseMoveTo("logo");
			Thread.sleep(800);

			result = LableisOpened("期末处理");
		} catch (WebDriverException e) {
			throw e;
		}
		return result;
	}

	@Deprecated
	public boolean open总账期末结账() throws InterruptedException, MyException {

		boolean result = false;
		try {
			switchToDefaultContent();

			// Reporter.log("关闭总账期末结账");
			closeLable("总账期末结账");

			// Reporter.log("检查凭证管理是否显示");
			if (!isDisplayed("凭证管理")) {
				// Reporter.log("凭证管理没有显示");

				// Reporter.log("点击总账");
				click("总账");
			}
			// Reporter.log("鼠标移动到结账办理");
			mouseMoveTo("结账办理");

			// Reporter.log("点击悬浮菜单-总账期末结账");
			click("总账期末结账悬浮");

			// Reporter.log("鼠标移动到logo");
			mouseMoveTo("logo");

			Thread.sleep(800);

			result = LableisOpened("总账期末结账");

		} catch (WebDriverException e) {
			throw e;
		}
		return result;
	}

	@Deprecated
	public boolean open会计科目() throws MyException, InterruptedException {
		boolean result = false;
		try {
			switchToDefaultContent();

			// Reporter.log("关闭会计科目");
			closeLable("会计科目");

			// Reporter.log("检查凭证管理是否显示");
			if (!isDisplayed("凭证管理")) {
				// Reporter.log("凭证管理未显示");

				// Reporter.log("点击总账");
				click("总账");
			}

			// Reporter.log("鼠标移动到基础设置");
			mouseMoveTo("基础设置-总账");

			// Reporter.log("点击悬浮菜单-会计科目");
			click("会计科目悬浮");

			// Reporter.log("鼠标移动到logo");
			mouseMoveTo("logo");

			Thread.sleep(1500);

			result = LableisOpened("总账期末结账");

		} catch (WebDriverException e) {
			throw e;
		}
		return result;
	}

	@Deprecated
	public boolean open汇率档案() throws MyException, InterruptedException {
		boolean result = false;
		try {
			switchToDefaultContent();

			// Reporter.log("检查汇率档案是否显示是则关闭");
			closeLable("汇率档案");

			// Reporter.log("检查总账是否已打开");
			if (!isDisplayed("凭证管理")) {
				// Reporter.log("总账未打开");

				// Reporter.log("点击总账");
				click("总账");
			}

			// Reporter.log("鼠标移动到基础设置");
			mouseMoveTo("基础设置-总账");

			// Reporter.log("点击悬浮菜单-汇率档案");
			click("汇率档案悬浮");

			// Reporter.log("鼠标移动到logo");
			mouseMoveTo("logo");

			Thread.sleep(1000);
			result = LableisOpened("总账期末结账");

		} catch (WebDriverException e) {
			throw e;
		}

		return result;
	}

	@Deprecated
	public void open发生额及余额表() throws InterruptedException, MyException {

		switchToDefaultContent();

		// Reporter.log("关闭发生额及余额表");
		closeLable("发生额及余额表");

		// Reporter.log("检查总账是否打开");
		if (!isDisplayed("凭证管理")) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		// Reporter.log("鼠标移动到科目帐表");
		mouseMoveTo("科目账表");

		// Reporter.log("点击悬浮菜单-发生额及余额表");
		click("发生额及余额表悬浮");

		// Reporter.log("鼠标移动到logo");
		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open科目汇总表() throws InterruptedException, MyException {

		switchToDefaultContent();

		// Reporter.log("关闭科目汇总表");
		closeLable("科目汇总表");

		// Reporter.log("检查总账是否已打开");
		if (isDisplayed("凭证管理") != true) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		// Reporter.log("鼠标移动到科目帐表");
		mouseMoveTo("科目账表");

		// Reporter.log("点击悬浮菜单-科目汇总表");
		click("科目汇总表悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open科目明细账() throws InterruptedException, MyException {

		switchToDefaultContent();

		// Reporter.log("关闭科目明细账");
		closeLable("科目明细账");

		// Reporter.log("检查总账是否已打开");
		if (isDisplayed("凭证管理") != true) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		// Reporter.log("鼠标移动到科目帐表");
		mouseMoveTo("科目账表");

		// Reporter.log("点击科目明细账");
		click("科目明细账悬浮");

		// Reporter.log("鼠标移动到logo");
		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open科目总账() throws InterruptedException, MyException {

		switchToDefaultContent();

		// Reporter.log("关闭科目总账");
		closeLable("科目总账");

		// Reporter.log("检查总账是否显示");
		if (isDisplayed("凭证管理") != true) {
			// Reporter.log("总账未显示");

			// Reporter.log("点击总账");
			click("总账");
		}

		// Reporter.log("鼠标移动到科目账表");
		mouseMoveTo("科目账表");

		// Reporter.log("点击科目总账");
		click("科目总账悬浮");

		// Reporter.log("鼠标移动到logo");
		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open数量金额明细账() throws InterruptedException, MyException {

		switchToDefaultContent();

		// Reporter.log("关闭数量金额明细账");
		closeLable("数量金额明细账");

		// Reporter.log("检查总账是否已打开");
		if (isDisplayed("凭证管理") != true) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		// Reporter.log("鼠标移动到科目帐表");
		mouseMoveTo("科目账表");

		// Reporter.log("点击数量金额明细账");
		click("数量金额明细账悬浮");

		// Reporter.log("鼠标移动到logo");
		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open数量金额总账() throws InterruptedException, MyException {

		switchToDefaultContent();

		// Reporter.log("关闭数量金额总账frame");
		closeLable("数量金额总账");

		// Reporter.log("检查总账是否已打开");
		if (isDisplayed("凭证管理") != true) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		// Reporter.log("鼠标移动到科目账表");
		mouseMoveTo("科目账表");

		// Reporter.log("点击数量金额总账");
		click("数量金额总账悬浮");

		// Reporter.log("鼠标移动到logo");
		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open现金银行日记账() throws InterruptedException, MyException {

		switchToDefaultContent();

		// Reporter.log("关闭现金/银行日记账");
		closeLable("现金/银行日记账");

		// Reporter.log("检查总账是否已打开");
		if (!isDisplayed("凭证管理")) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		// Reporter.log("鼠标移动到科目账表");
		mouseMoveTo("科目账表");

		// Reporter.log("点击现金/银行日记账");
		click("现金银行日记账悬浮");

		// Reporter.log("鼠标移动到logo");
		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open序时账() throws InterruptedException, MyException {

		switchToDefaultContent();

		// Reporter.log("关闭序时账");
		closeLable("序时账");

		// Reporter.log("检查总账是否已打开");
		if (!isDisplayed("凭证管理")) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		// Reporter.log("鼠标移动到科目账表");
		mouseMoveTo("科目账表");

		click("序时账悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open利润表季报() throws InterruptedException, MyException {

		switchToDefaultContent();

		// Reporter.log("关闭利润表季报");
		closeLable("利润表季报");

		// Reporter.log("检查总账是否已打开");
		if (!isDisplayed("凭证管理")) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		// Reporter.log("鼠标移动到财务账表");
		mouseMoveTo("财务账表");

		click("利润表季报悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open利润表() throws InterruptedException, MyException {

		switchToDefaultContent();

		// Reporter.log("关闭利润表");
		closeLable("利润表");

		// Reporter.log("检查总账是否已打开");
		if (!isDisplayed("凭证管理")) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		mouseMoveTo("财务账表");

		click("利润表悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1000);
	}

	@Deprecated
	public void open收入支出表() throws InterruptedException, MyException {

		switchToDefaultContent();

		// Reporter.log("关闭收入支出表");
		closeLable("收入支出表");

		// Reporter.log("检查总账是否已打开");
		if (!isDisplayed("凭证管理")) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}
		mouseMoveTo("财务账表");

		click("收入支出表悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open现金流量表() throws InterruptedException, MyException {

		switchToDefaultContent();

		// Reporter.log("关闭现金流量表");
		closeLable("现金流量表");

		// Reporter.log("检查总账是否已打开");
		if (!isDisplayed("凭证管理")) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		mouseMoveTo("财务账表");

		click("现金流量表悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open业务活动表() throws InterruptedException, MyException {

		switchToDefaultContent();

		closeLable("业务活动表");

		// Reporter.log("检查总账是否已打开");
		if (!isDisplayed("凭证管理")) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		mouseMoveTo("财务账表");

		click("业务活动表悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open增值税和营业税月度申报对比表() throws InterruptedException, MyException {

		switchToDefaultContent();

		closeLable("增值税和营业税月度申报对比表");

		// Reporter.log("检查总账是否已打开");
		if (!isDisplayed("凭证管理")) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		mouseMoveTo("财务账表");

		click("增值税和营业税月度申报对比表悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open资产负债表() throws InterruptedException, MyException {

		switchToDefaultContent();

		closeLable("资产负债表");

		// Reporter.log("检查总账是否已打开");
		if (!isDisplayed("凭证管理")) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		mouseMoveTo("财务账表");

		click("资产负债表悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open常用凭证模板() throws InterruptedException, MyException {

		switchToDefaultContent();

		closeLable("常用凭证模板");

		// Reporter.log("检查总账是否已打开");
		if (!isDisplayed("凭证管理")) {
			// Reporter.log("总账未打开");

			// Reporter.log("点击总账");
			click("总账");
		}

		mouseMoveTo("基础设置-总账");

		click("常用凭证模板悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open卡片管理() throws InterruptedException, MyException {

		switchToDefaultContent();

		closeLable("卡片管理");

		// Reporter.log("检查固定资产是否已打开");
		if (!isDisplayed("资产管理")) {
			// Reporter.log("固定资产");

			// Reporter.log("点击固定资产");
			click("固定资产");
		}

		mouseMoveTo("资产管理");

		click("卡片管理悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open原值变更() throws InterruptedException, MyException {

		switchToDefaultContent();

		closeLable("原值变更");

		// Reporter.log("检查固定资产是否已打开");
		if (!isDisplayed("资产管理")) {
			// Reporter.log("固定资产");

			// Reporter.log("点击固定资产");
			click("固定资产");
		}

		mouseMoveTo("资产管理");

		click("原值变更悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open资产清理() throws InterruptedException, MyException {

		switchToDefaultContent();

		closeLable("资产清理");

		// Reporter.log("检查固定资产是否已打开");
		if (!isDisplayed("资产管理")) {
			// Reporter.log("固定资产");

			// Reporter.log("点击固定资产");
			click("固定资产");
		}

		mouseMoveTo("资产管理");

		click("资产清理悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open工作量管理() throws InterruptedException, MyException {

		switchToDefaultContent();

		closeLable("工作量管理");

		// Reporter.log("检查固定资产是否已打开");
		if (!isDisplayed("资产管理")) {
			// Reporter.log("固定资产");

			// Reporter.log("点击固定资产");
			click("固定资产");
		}

		mouseMoveTo("资产管理");

		click("工作量管理悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open折旧汇总表() throws InterruptedException, MyException {

		switchToDefaultContent();

		closeLable("折旧汇总表");

		// Reporter.log("检查固定资产是否已打开");
		if (!isDisplayed("资产管理")) {
			// Reporter.log("固定资产");

			// Reporter.log("点击固定资产");
			click("固定资产");
		}

		mouseMoveTo("资产报表");

		click("折旧汇总表悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open资产明细账() throws InterruptedException, MyException {

		switchToDefaultContent();

		closeLable("资产明细账");

		// Reporter.log("检查固定资产是否已打开");
		if (!isDisplayed("资产管理")) {
			// Reporter.log("固定资产");

			// Reporter.log("点击固定资产");
			click("固定资产");
		}

		mouseMoveTo("资产报表");

		click("资产明细账悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open资产与总账对账表() throws InterruptedException, MyException {

		switchToDefaultContent();

		closeLable("资产与总账对账表");

		// Reporter.log("检查固定资产是否已打开");
		if (!isDisplayed("资产管理")) {
			// Reporter.log("固定资产未打开");

			// Reporter.log("点击固定资产");
			click("固定资产");
		}

		mouseMoveTo("资产报表");

		click("资产与总账对账表悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open资产总账() throws InterruptedException, MyException {

		switchToDefaultContent();

		closeLable("资产总账");

		// Reporter.log("检查固定资产是否已打开");
		if (!isDisplayed("资产管理")) {
			// Reporter.log("固定资产");

			// Reporter.log("点击固定资产");
			click("固定资产");
		}

		mouseMoveTo("资产报表");

		click("资产总账悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	@Deprecated
	public void open资产折旧明细() throws InterruptedException, MyException {

		switchToDefaultContent();

		closeLable("资产折旧明细");

		// Reporter.log("检查固定资产是否已打开");
		if (!isDisplayed("资产管理")) {
			// Reporter.log("固定资产未打开");

			// Reporter.log("点击固定资产");
			click("固定资产");
		}

		mouseMoveTo("资产报表");

		click("资产折旧明细悬浮");

		mouseMoveTo("logo");

		Thread.sleep(1500);
	}

	public boolean LableisOpened(String lableName) throws MyException {
		try {
			switchToDefaultContent();

			// 检查填制凭证是否显示如果显示关闭该标签页
			return isDisplayed(By.xpath("//*[@id='main']/div[1]/div[3]/ul//span[text()='" + lableName + "']"));

		} catch (WebDriverException e) {
			throw e;
		}

	}

	public void closeLable(String lableName) throws MyException {
		try {
			switchToDefaultContent();

			// 检查填制凭证是否显示如果显示关闭该标签页
			if (LableisOpened(lableName)) {
				// 从标签栏中找是否有lableName的按钮
				WebElement 按钮 = getWebElement(
						By.xpath(".//*[@id='main']/div[1]/div[3]/ul//span[text()='" + lableName + "']"));

				// 点击按钮上的关闭按钮
				click(getWebElement(按钮, By.xpath("../../a[2]")));

				// Reporter.log(lableName + "已关闭：" + !LableisOpened(lableName));
			}

		} catch (MyException e) {
			throw e;
		}
	}

	public boolean logout() throws MyException {
		try {
			switchToDefaultContent();

			// Reporter.log("鼠标移动到用户信息按钮");
			mouseMoveTo("用户信息与退出");

			// Reporter.log("点击退出按钮");
			click("退出按钮");

			// Reporter.log("判断用户名输入框是否显示并返回");
			return isDisplayed("用户名输入框");

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("退出失败！");
			throw e;
		}

	}

	/*
	 * 打开Frame 参数 frameName
	 */
	public boolean openFrame(String frameName) throws MyException {

		switchToDefaultContent();

		closeLable(frameName);

		String parent = "";

		// 确定frame的父级
		switch (frameName) {
		case "卡片管理":
		case "原值变更":
		case "资产清理":
		case "工作量管理":
			parent = "资产管理";
			break;
		case "折旧汇总表":
		case "资产明细账":
		case "资产与总账对账表":
		case "资产折旧明细":
		case "资产总账":
			parent = "资产报表";
			break;
		case "填制凭证":
		case "凭证管理":
			parent = "凭证管理";
			break;
		case "发生额及余额表":
		case "科目汇总表":
		case "科目明细账":
		case "科目总账":
		case "数量金额明细账":
		case "数量金额总账":
		case "现金银行日记账":
		case "序时账":
			parent = "科目账表";
			break;
		case "期末处理":
		case "总账期末结账":
			parent = "结账办理";
			break;
		case "常用凭证模版":
		case "常用摘要":
		case "成本结转模版":
		case "个性化设置":
		case "公司资产与总账对照表":
		case "汇兑损益结转模版":
		case "汇率档案":
		case "会计科目":
		case "计提折旧清理凭证模版":
		case "科目期初余额":
		case "科目期末结账查询":
		case "利润结转模版":
		case "期间损益模版维护":
			parent = "基础设置-总账";
			break;
		case "利润表季报":
		case "利润表":
		case "收入支出表":
		case "现金流量表":
		case "业务活动表":
		case "增值税和营业税月度申报对比表":
		case "资产负债表":
			parent = "财务账表";
			break;
		case "商品类别":
		case "计量单位":
		case "商品":
		case "库存期初":
			parent = "基础设置-库存管理";
			break;
		case "库存成本表":
			parent = "库存报表";
			break;
		case "入库单":
		case "出库单":
			parent = "日常业务";
			break;
		default:
			Reporter.log(frameName + "有误！");
			return false;
		}

		String assetsclass = "";

		// 确定父级所在的资产类别
		switch (parent) {
		case "凭证管理":
		case "结账办理":
		case "科目账表":
		case "财务账表":
		case "纳税申报":
		case "基础设置-总账":
		case "数据维护":
			assetsclass = "总账";
			break;
		case "资产管理":
		case "资产报表":
			assetsclass = "固定资产";
			break;
		case "基础设置-库存管理":
		case "日常业务":
		case "库存报表":
			assetsclass = "库存管理";
			break;
		default:
			Reporter.log("没有找到" + parent + "的上级");
			// return false;
		}

		System.out.println("frameName:" + frameName + "；parent:" + parent + "；assetsclass:" + assetsclass);

		// Reporter.log("检查" + assetsclass + "是否已打开");
		if (!isDisplayed(parent)) {
			// Reporter.log(assetsclass + "未打开");

			// Reporter.log("点击" + assetsclass);
			click(assetsclass);
		}

		// Reporter.log("鼠标移动到：" + parent);
		mouseMoveTo(parent);

		// Reporter.log("点击" + frameName);
		click(frameName + "悬浮");

		mouseMoveTo("logo");

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return LableisOpened(frameName);

		// return false;
	}

	public void switchToLable(String lableName) throws MyException {
		try {
			switchToDefaultContent();

			if (LableisOpened(lableName)) {
				click(getWebElement(By.xpath("//*[@id='main']/div[1]/div[3]/ul//span[text()='" + lableName + "']")));
			}

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("切换到" + lableName + "失败！");
			throw e;
		}
	}
}
