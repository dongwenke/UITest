package com.dzf.test.page.accounting.凭证管理;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.model.Voucher;
import com.dzf.test.model.Voucher.VoucherSubject;
import com.dzf.test.model.Voucher.科目方向;
import com.dzf.test.util.MyException;
import com.dzf.test.util.StringUtil;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

/**
 * @author 董文科 逻辑拆分：填制凭证和上边按钮不在同一个方法中
 */
public class 填制凭证Page extends Handler {

	public 填制凭证Page() {
		super();
		try {
			page = XMLUtil.convert("./config/page/accounting/凭证管理/" + this.getClass().getSimpleName() + ".xml",
					Page.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 填制凭证
	 */
	public void editVoucher(Voucher voucher) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("填制凭证");

			// 点击凭证标题
			// Reporter.log("点击凭证标题");
			click("凭证标题");

			// 初始化凭证表格
			// 列说明：列0：增加/删除行 列1：摘要 列2： 列3：会计科目 列4：会计科目-科目选择 列5：数量 列6：币种 列7：借方金额
			// 列8：贷方金额
			WebTableUtil VoucherTable = new WebTableUtil(getWebElement("凭证表格"));

			// 循环取voucher中的科目填写
			List<VoucherSubject> subjectList = voucher.getSubjectList();

			for (int i = 0; i < subjectList.size(); i++) {
				VoucherSubject subject = subjectList.get(i);

				// 填写摘要
				if (StringUtil.notNullAndEmpty(voucher.getSummary())) {
					System.out.println(1);
					// 点击第i行摘要输入框
					click(VoucherTable.getCell(i, 1));
					if (i == 0) {
						// 输入摘要
						input(getWebElement(VoucherTable.getCell(i, 1), By.tagName("input")), voucher.getSummary());
					}
				}

				// 填写科目
				if (StringUtil.notNullAndEmpty(subject.getName())) {
					// 点击会计科目输入框
					click(VoucherTable.getCell(i, 3));
					// 检查下拉框是否显示
					waitToDisplayed("会计科目提示框");

					input(getWebElement(VoucherTable.getCell(i, 3), By.tagName("input")), subject.getName());
					// 选择会计科目
					click(getWebElement(By.xpath(".//*[@id='COMBO_WRAP']/div[3]/div[1]/div[1]")));
				}

				if (subject.is启用库存()) {
					// 检查科目选项是否显示 id = isItem
					if (isDisplayed("科目属性")) {

						// 存货输入框输入存货名称.//*[@id='itemCH']/input
						input("存货输入框", subject.getStockGoodsName());

						// 点击存货选项一
						click("库存商品一");

						input("存货输入框", Keys.RETURN);
					}

					// 输入数量
					input(getWebElements(VoucherTable.getCell(i, 5), By.tagName("input")).get(0),
							String.valueOf(subject.getCount()));
					// 输入单价
					WebElement unitPrice = getWebElements(VoucherTable.getCell(i, 5), By.tagName("input")).get(1);
					input(unitPrice, String.valueOf(subject.getUnitPrice()));

					input(unitPrice, Keys.RETURN);

				}

				if (subject.is启用外币()) {

					// 选择币别
					// click(VoucherTable.getRow(i).findElement(By.xpath(".//select[@class='curr-code']")));

					new Select(VoucherTable.getRow(i).findElement(By.xpath(".//select[@class='curr-code']")))
							.selectByVisibleText(subject.getCurrency());

					// 输入汇率
					if (subject.getRate() != -1) {
						input(getWebElements(VoucherTable.getCell(i, 6), By.tagName("input")).get(0),
								String.valueOf(subject.getRate()));
					}

					// 输入原币
					if (subject.getOriMoney() != -1) {
						// Reporter.log("输入原币" + original);
						input(getWebElements(VoucherTable.getCell(i, 6), By.tagName("input")).get(1),
								String.valueOf(subject.getOriMoney()));
					}
				}

				click("凭证标题");

				// 填写金额
				String money = "";
				if (subject.getMoney() != -1) {
					money = String.valueOf(subject.getMoney());
				}

				if (subject.getMoney() != -2) {
					switch (subject.getDirection()) {
					case 借方:

						if ("".equals(getWebElement(VoucherTable.getCell(i, 7), By.xpath("./div")).getText())) {
							if ("".equals(money)) {
								money = String.valueOf(new Double(
										getWebElement(VoucherTable.getCell(i, 8), By.xpath("./div[1]")).getText())
										/ 100);

							}
							// 点击借方金额
							click(VoucherTable.getCell(i, 7));
							// 清空贷方金额
							input(getWebElement(VoucherTable.getCell(i, 7), By.tagName("input")), money);
							// 点击借方金额
							click(VoucherTable.getCell(i, 8));
							// 输入金额
							input(getWebElement(VoucherTable.getCell(i, 8), By.tagName("input")), "");
						}

						break;
					case 贷方:

						if ("".equals(getWebElement(VoucherTable.getCell(i, 8), By.xpath("./div")).getText())) {
							if ("".equals(money)) {
								money = String.valueOf(new Double(
										getWebElement(VoucherTable.getCell(i, 7), By.xpath("./div")).getText()) / 100);

							}
							// 点击借方金额
							click(VoucherTable.getCell(i, 7));
							// 清空借方金额
							input(getWebElement(VoucherTable.getCell(i, 7), By.tagName("input")), "");
							// 点击借方金额
							click(VoucherTable.getCell(i, 8));
							// 输入金额
							input(getWebElement(VoucherTable.getCell(i, 8), By.tagName("input")), money);
						}

						break;
					case 默认:

						if (subject.getMoney() != -1) {

							if (isDisplayed(getWebElement(VoucherTable.getCell(i, 7), By.tagName("input")))) {

								input(getWebElement(VoucherTable.getCell(i, 7), By.tagName("input")),
										String.valueOf(subject.getMoney()));
							} else if (isDisplayed(getWebElement(VoucherTable.getCell(i, 8), By.tagName("input")))) {

								input(getWebElement(VoucherTable.getCell(i, 8), By.tagName("input")),
										String.valueOf(subject.getMoney()));
							}
						}

						break;
					}

				}
			}

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("填制凭证失败！");
			throw e;
		}

	}

	/*
	 * 填制凭证并保存
	 */
	public boolean saveVoucher(Voucher voucher) throws MyException {
		try {
			boolean result = false;

			switchToDefaultContent();
			switchToFrame("填制凭证");

			editVoucher(voucher);

			// 点击保存按钮
			click("保存按钮");

			if (isDisplayed("期间损益结转完成提示")) {
				click("期间损益结转完成提示面板-确定按钮");
			}

			if (isDisplayed("打印按钮")) {
				result = true;
			}

			return result;
		} catch (MyException e) {
			Reporter.log("保存凭证失败！");
			throw e;
		}
	}

	/*
	 * 填制并保存凭证 警告：暂时只支持两行输入
	 */
	public boolean saveVoucher(String summary, String subject1, String rate, String original, String money,
			String subject2) throws InterruptedException, MyException {
		try {
			boolean result = false;

			switchToDefaultContent();
			switchToFrame("填制凭证");

			// 点击凭证标题
			// Reporter.log("点击凭证标题");
			click("凭证标题");

			// 初始化凭证表格
			// 列说明：列0：增加/删除行 列1：摘要 列2： 列3：会计科目 列4：会计科目-科目选择 列5：数量 列6：币种 列7：借方金额
			// 列8：贷方金额
			WebTableUtil table = new WebTableUtil(getWebElement("凭证表格"));

			// 点击第一行摘要输入框
			// Reporter.log("点击第一行摘要");
			click(table.getCell(0, 1));

			Thread.sleep(500);

			// 选择下拉框的内容
			// Reporter.log("第一行摘要输入框输入：" + summary);
			input(getWebElement(table.getCell(0, 1), By.tagName("input")), summary);

			// 点击会计科目输入框
			// Reporter.log("点击第一行会计科目");
			click(table.getCell(0, 3));

			Thread.sleep(500);

			// 检查下拉框是否显示
			if (isDisplayed("会计科目提示框") != true) {
				click("会计科目第一行");
			}

			if (subject1 != null) {
				// Reporter.log("第一行会计科目输入框输入" + subject1);
				input(getWebElement(table.getCell(0, 3), By.tagName("input")), subject1);
			}

			// 选择会计科目
			// Reporter.log("点击会计科目选项一");
			click("会计科目选项一");

			// 点击凭证标题
			click("凭证标题");

			if (isDisplayed(table.getCell(0, 5))) {

				// // 输入数量
				// if (num != null) {
				// input(getWebElements(table.getCell(0, 5),
				// By.tagName("input")).get(0), num);
				// }
				//
				// // 输入单价
				// if (unitprice != null) {
				// input(getWebElements(table.getCell(0, 5),
				// By.tagName("input")).get(1), unitprice);
				// }
			}

			boolean originalInput = false;
			if (isDisplayed(table.getCell(0, 6))) {

				// 输入汇率
				if (rate != null) {
					if (getWebElements(table.getCell(0, 6), By.tagName("input")).get(0).getAttribute("class")
							.contains("ui-input-disabled") != true) {
						// Reporter.log("输入汇率" + rate);
						input(getWebElements(table.getCell(0, 6), By.tagName("input")).get(0), rate);
					} else {
						logger.info("汇率已设置！");
					}
				}

				// 输入原币
				if (original != null) {
					// Reporter.log("输入原币" + original);
					input(getWebElements(table.getCell(0, 6), By.tagName("input")).get(1), original);
					originalInput = true;
				}
			}

			String moneyTmp = getText(getWebElement(table.getCell(0, 7), By.tagName("div")));

			click(table.getCell(0, 7));

			if (moneyTmp == null || moneyTmp.equals("")) {
				if (money != null) {
					moneyTmp = money.toString();
				} else {
					moneyTmp = "100";
				}
			} else {
				moneyTmp = String.valueOf(Integer.valueOf(moneyTmp) / 100);
			}

			// 如果原币没有输入则输入金额，否则不输入
			if (!originalInput) {
				// Reporter.log("借方金额输入框输入：" + moneyTmp);
				input(getWebElement(table.getCell(0, 7), By.tagName("input")), moneyTmp);
			}

			// 点击第二行的摘要输入框
			// Reporter.log("点击第二行的摘要");
			click(table.getCell(1, 1));

			// 点击第二行的会计科目输入框
			// Reporter.log("点击第二行的会计科目");
			click(table.getCell(1, 3));

			if (isDisplayed("会计科目提示框") != true) {
				click(table.getCell(1, 3));
			}

			if (subject2 != null) {
				// Reporter.log("第二行的会计科目输入框输入：" + subject2);
				input(getWebElement(table.getCell(1, 3), By.tagName("input")), subject2);
			}

			// 选择会计科目
			// Reporter.log("点击会计科目选项一");
			click("会计科目选项一");

			// 点击凭证标题
			click("凭证标题");

			// 点击金额输入框并输入金额
			// Reporter.log("点击第二行的贷方金额");
			click(table.getCell(1, 8));

			// Reporter.log("第二行的贷方金额输入框输入：" + moneyTmp);
			input(getWebElement(table.getCell(1, 8), By.tagName("input")), moneyTmp);

			// 点击保存按钮
			// Reporter.log("点击保存按钮");
			click("保存按钮");

			if (isDisplayed("期间损益结转完成提示")) {
				// Reporter.log("出现期间损益结转完成提示");
				// Reporter.log("点击期间损益结转完成提示面板-确定按钮");
				click("期间损益结转完成提示面板-确定按钮");
			}

			Thread.sleep(500);

			if (isDisplayed("打印按钮")) {
				result = true;
			}

			return result;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("填制凭证失败！");
			throw e;
		}
	}

	/*
	 * 常用模板 警告：通过编码选择凭证功能暂未实现
	 */
	public void commonTemplet(String templetCode, String money) throws InterruptedException, MyException {
		/*
		 * 1.登录成功后会打开主页面 2.点击凭证管理-填制凭证 3.切换到填制凭证页面进行操作
		 */
		switchToDefaultContent();
		switchToFrame(getWebElement("填制凭证iframe"));

		// 点击常用模板
		// Reporter.log("点击常用模版按钮");
		click("常用模板按钮");

		// 等待模板弹窗
		if (isDisplayed("模板弹窗") != true) {
			Thread.sleep(1000);
		}
		// 判断是否有模板
		if (getWebElements("模板列表") == null) {
			// 模板列表为空
			System.out.println("当前没有可用模板！");
			return;
		}

		// 输入金额100
		// Reporter.log("模板金额输入框输入：" + money);
		input("模板金额输入框", money);

		Thread.sleep(1000);

		// 点击第一个模板
		// Reporter.log("点击模版列表第一行");
		click("模板列表一");

		// 点击确定按钮
		// Reporter.log("点击确定按钮");
		click("确定模板按钮");

		Thread.sleep(1000);

		click("凭证标题");

		Thread.sleep(800);

		// 点击保存按钮
		// Reporter.log("点击保存按钮");
		click("保存按钮");

		Thread.sleep(2000);
	}

	/*
	 * 单据图片 说明：场景一暂不实现
	 */
	public boolean billImage() throws MyException {
		boolean result = false;
		switchToDefaultContent();
		switchToFrame(getWebElement("填制凭证iframe"));

		click("凭证标题");

		click("单据图片按钮");

		// 选择起始日期
		// 选择结束日期
		// 选择公司
		// 点击确定
		result = isDisplayed("单据图片面板-取消按钮");
		// Reporter.log("单据图片面板已显示：" + result);

		if (result) {
			// Reporter.log("点击单据图片面板-取消按钮");
			click("单据图片面板-取消按钮");
		}

		return result;
	}

	/*
	 * 填制凭证页面的按月复制
	 */
	public boolean copyByMonthOnFill(String originalDate, String targetDate) throws InterruptedException, MyException {
		/*
		 * 1.登录成功后会打开主页面 2.点击凭证管理-填制凭证 3.切换到填制凭证页面进行操作
		 */
		switchToDefaultContent();
		switchToFrame(getWebElement("填制凭证iframe"));

		click("按月复制按钮");

		doCopyByMonth(originalDate, targetDate);
		// getText(waitToDisplayElement(By.xpath("//div[contains(@class,'tips')]
		// "))).equals("复制成功");
		return true;
	}

	private void doCopyByMonth(String originalDate, String targetDate) throws MyException {

		waitToDisplayed("按月复制弹窗");

		// Reporter.log("点击开始年份选择按钮");
		click("开始年份选择按钮");

		click(getWebElement(By.xpath(
				"//div[starts-with(@id,'_easyui_combobox_i1') and text()='" + originalDate.split("-")[0] + "']")));

		// Reporter.log("点击开始月份选择按钮");
		click("开始月份选择按钮");

		click(getWebElement(By.xpath(
				"//div[starts-with(@id,'_easyui_combobox_i2') and text()='" + originalDate.split("-")[1] + "']")));

		// Reporter.log("点击目标年份选择按钮");
		click("目标年份选择按钮");

		click(getWebElement(By
				.xpath("//div[starts-with(@id,'_easyui_combobox_i3') and text()='" + targetDate.split("-")[0] + "']")));

		// Reporter.log("点击目标月份选择按钮");
		click("目标月份选择按钮");

		click(getWebElement(By
				.xpath("//div[starts-with(@id,'_easyui_combobox_i4') and text()='" + targetDate.split("-")[1] + "']")));

		// Reporter.log("点击确定按钮");
		click("确定按月复制按钮");

//		return waitToDisplayed("按月复制按钮");

	}

	/*
	 * 打印凭证
	 */
	public boolean print() throws InterruptedException, MyException {
		boolean result = false;
		switchToDefaultContent();
		switchToFrame(getWebElement("填制凭证iframe"));

		click("打印按钮");

		Thread.sleep(5000);

		String prePage = driver.getWindowHandle();

		// 切换选项卡
		Iterator<String> it = driver.getWindowHandles().iterator();// 获得所有窗口句柄报错
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			if (driver.getTitle().contains("print")) {
				result = true;
				break;
			}
		}

		logger.info(driver.getCurrentUrl());

		// 关闭当前打印窗口
		if (!driver.getWindowHandle().equals(prePage)) {
			driver.close();
			logger.info("当前打印窗口已关闭!");
		}

		// 切换回先前的页面
		driver.switchTo().window(prePage);
		logger.info("driver已切换到先前的页面！");

		return result;
	}

	/*
	 * 审核凭证
	 */
	public boolean audit() throws MyException {
		click("审核按钮");

		return isDisplayed("已审核章");
	}

	/*
	 * 复制已保存凭证
	 */
	public void copy() throws InterruptedException, MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("填制凭证iframe"));

		click("复制按钮");

		Thread.sleep(1000);

		click("保存按钮");
	}

	/*
	 * 删除凭证
	 */
	public boolean delete() throws InterruptedException, MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("填制凭证iframe"));

		// Reporter.log("点击删除按钮");
		click("删除按钮");

		Thread.sleep(500);

		// Reporter.log("点击取消按钮");
		click(getWebElement(getWebElement("删除警告面板"), By.xpath("./div[2]/div[4]/a[2]")));

		Thread.sleep(500);

		return false;
	}

	/*
	 * 凭证保存完毕后页面的按月复制
	 */
	public boolean copyByMonthOnSaved(String originalDate, String targetDate) throws InterruptedException, MyException {
		/*
		 * 1.登录成功后会打开主页面 2.点击凭证管理-填制凭证 3.切换到填制凭证页面进行操作
		 */
		switchToDefaultContent();
		switchToFrame(getWebElement("填制凭证iframe"));

		// Reporter.log("点击按月复制按钮");
		click("按月复制按钮onSaved");

		doCopyByMonth(originalDate, targetDate);

		return true;
//		return getText(waitToDisplayElement(By.xpath("//div[contains(@class,'tips')] "))).equals("复制成功");
	}

	/*
	 * 保存为常用凭证模版
	 */
	public boolean saveAsCommonTemplet(String templetCode, String templetName) throws MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("填制凭证iframe"));

		// Reporter.log("点击保存为常用凭证模版按钮");
		click("保存为常用凭证模版按钮");

		// Reporter.log("模版编码输入框输入：" + templetCode);
		input("模版编码输入框", templetCode);

		// Reporter.log("模版名称输入框输入：" + templetCode);
		input("模版名称输入框", templetName);

		// Reporter.log("点击保存按钮");
		click("新增模版面板-保存按钮");

		return false;
	}

	public boolean revAudit() throws MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("填制凭证iframe"));

		// Reporter.log("点击反审核按钮");
		click("反审核按钮");

		return !isDisplayed("已审核章");
	}

	/*
	 * 暂时去掉了，bug改好之后再重写
	 */
	public boolean 红字回冲() {
		// TODO Auto-generated method stub
		return false;
	}

	public void 转总账(String useDate) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("填制凭证");

			// 修改日期使用日期
			input("日期输入框", useDate);
			input("日期输入框", Keys.RETURN);

			click("保存按钮");

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("转总账失败！");
			throw e;
		}
	}

}
