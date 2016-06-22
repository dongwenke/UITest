package com.dzf.test.page.accounting.基础设置_总账;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

/**
 * @author 董文科
 * @see 科目期初余额页面
 */
public class 科目期初余额Page extends Handler {

	private final String xmlfile = "./config/page/accounting/基础设置_总账/" + this.getClass().getSimpleName() + ".xml";

	public 科目期初余额Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	// 修改期初余额(是否平衡)
	public boolean modify(String subject, String currency, String num, String project)
			throws InterruptedException, MyException {
		try {
			boolean result = false;

			// 切换到科目期初余额
			switchToDefaultContent();

			switchToFrame(getWebElement("科目期初余额iframe"));

			switchCurrency(currency);

			// 点击科目所在行以选中
			click(getSubjectTr(subject));

			// 点击修改
			click("修改按钮");

			Thread.sleep(500);

			// 输入金额
			String field = "";
			switch (project) {
			case "原币本年期初":
				field = "ybnqc";
				break;
			case "本年期初金额":
				field = "nqc";
				break;
			case "原币本年借方发生":
				field = "ybnjf";
				break;
			case "本年借方发生金额":
				field = "njf";
				break;
			case "原币本年贷方发生":
				field = "ybndf";
				break;
			case "本年贷方发生金额":
				field = "ndf";
				break;
			case "原币本月期初":
				field = "ybmonthqc";
				break;
			case "本月期初金额":
				field = "monthqc";
				break;
			default:
				Reporter.log("未指定修改项！");
				return false;
			}
			input(getWebElement(By.xpath("//tr[*='" + subject + "']/td[@field='" + field + "']//span/input[1]")), num);

			// 点击保存
			click("保存按钮");

			Thread.sleep(1000);

			String flag = getText(getWebElement(By.xpath(".//tr[*='" + subject + "']/td[@field='" + field + "']")));

			// Reporter.log("判断保存的金额是否等于输入的金额");
			if (Double.parseDouble(flag.replace(",", "")) == Double.parseDouble(num)) {
				result = true;
			}

			return result;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			throw e;
		}
	}

	/*
	 * subject格式 1101_短期投资
	 * 
	 */
	public WebElement getSubjectTr(String subject) throws InterruptedException, MyException {

		String tableList[] = { "资产table", "负债table", "共同table", "所有者权益table", "成本table", "损益table" };
		for (String table : tableList) {

			switch (table) {
			case "资产table":
				// Reporter.log("点击资产");
				click("资产类别");
				break;
			case "负债table":
				// Reporter.log("点击负债");
				click("负债类别");
				break;
			case "共同table":
				// Reporter.log("点击共同");
				click("共同类别");
				break;
			case "所有者权益table":
				// Reporter.log("点击所有者权益");
				click("所有者权益类别");
				break;
			case "成本table":
				// Reporter.log("点击成本");
				click("成本类别");
				break;
			case "损益table":
				// Reporter.log("点击损益");
				click("损益类别");
				break;
			}

			Thread.sleep(800);

			// Reporter.log("获取所有包含" + subject + "的行");
			// 获取table中所有的包含subject的tr
			List<WebElement> el = getWebElement(table).findElements(By.xpath(".//tr[*='" + subject + "']"));

			if (0 != el.size()) {
				return el.get(0);
			}
			// Reporter.log("获取" + subject + "所在的行");
			// // 遍历
			// for (WebElement webElement : webElementList) {
			// if (webElement.findElements(By.tagName("tr")).size() == 0) {
			//// Reporter.log("获取成功！");
			// return webElement;
			// }
			// }
		}

		return null;
	}

	/*
	 * 点击试算平衡按钮 如果弹窗出现，不关闭
	 * 
	 */
	public boolean trialBalancing() throws MyException {

		switchToDefaultContent();
		// Reporter.log("切换到：科目期初余额iframe");
		switchToFrame(getWebElement("科目期初余额iframe"));

		// Reporter.log("点击试算平衡按钮");
		click("试算平衡按钮");

		// Reporter.log("返回是否显示试算平衡信息面板");
		boolean balance = isDisplayed("试算平衡信息面板-年平衡");
		// Reporter.log("年平衡："+balance);
		return balance;
	}

	public boolean fixedAssetsSync() throws MyException {
		try {
			switchToDefaultContent();

			switchToFrame(getWebElement("科目期初余额iframe"));

			click("固定资产期初同步按钮");

			click("询问面板-确定按钮");

			if (getText(waitToDisplayElement("提示信息")).equals("刷新成功！")) {
				return true;
			} else
				return false;

		} catch (MyException e) {
			Reporter.log("固定资产同步失败！");
			throw e;
		}
	}

	public boolean fixedAssetsSyncButton() throws MyException {
		try {
			switchToDefaultContent();

			switchToFrame(getWebElement("科目期初余额iframe"));

			click("固定资产期初同步按钮");
			if (isDisplayed("询问面板-确定按钮")) {

				click("询问面板-确定按钮");
				return true;
			} else
				return false;
		} catch (MyException e) {
			Reporter.log("固定资产同步失败！");
			throw e;
		}
	}

	public boolean refresh() throws MyException {
		try {
			boolean result = false;

			switchToDefaultContent();

			// Reporter.log("切换到：科目期初余额iframe");
			switchToFrame(getWebElement("科目期初余额iframe"));

			// Reporter.log("点击刷新按钮");
			click("刷新按钮");

//			if (getText(waitToDisplayElement("提示信息")).equals("刷新成功！")) {
//				// Reporter.log("提示刷新成功！");
//				result = true;
//			}

			return result;
		} catch (MyException e) {
			 Reporter.log(e.getMessage());
			Reporter.log("刷新失败！");
			throw e;
		}
	}

	public boolean print() throws InterruptedException, MyException {
		try {
			boolean result = false;

			switchToDefaultContent();

			// Reporter.log("切换到：科目期初余额iframe");
			switchToFrame(getWebElement("科目期初余额iframe"));

			// Reporter.log("点击打印按钮");
			click("打印按钮");

			Thread.sleep(500);

			// Reporter.log("点击打印面板的确定按钮");
			click("打印面板-确定按钮");

			Thread.sleep(5000);

			// Reporter.log("判断打印窗口是否已打开是则关闭");
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

			Thread.sleep(1000);

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
		} catch (MyException e) {
			// Reporter.log(e.getMessage());
			Reporter.log("打印失败！");
			throw e;
		}
	}

	public String getBalanceInfo() throws InterruptedException, MyException {

		try {
			switchToDefaultContent();

			// Reporter.log("切换到：科目期初余额iframe");
			switchToFrame(getWebElement("科目期初余额iframe"));

			// Reporter.log("获取试算平衡信息面板-年平衡信息");
			String result = getText("试算平衡信息面板-年平衡");

			// Reporter.log("点击试算平衡信息面板-取消按钮");
			click("试算平衡信息面板-取消按钮");

			return result;
		} catch (MyException e) {
			// Reporter.log(e.getMessage());
			Reporter.log("获取平衡信息失败！");
			throw e;
		}
	}

	/*
	 * 切换当前币别 警告：无法获取币别框中的文字
	 */
	public void switchCurrency(String currency) throws MyException {
		try {
			boolean result = false;
			switchToDefaultContent();

			// Reporter.log("切换到：科目期初余额iframe");
			switchToFrame(getWebElement("科目期初余额iframe"));
			// 如果当前币别是currency 返回

			// if (getText("币别输入框").contains(currency)) {
			// Reporter.log("当前币种无需切换！");
			// return result;
			// }

			// 点击币别选择按钮
			// Reporter.log("点击币别输入框");
			click("币别输入框");
			// 点击币别按钮
			// Reporter.log("选择" + currency);
			click("币别选项-" + currency);

			// 如果当前币别是currency 返回
			// if (getText("币别").contains(currency)) {
			// Reporter.log("当前币种无需切换！");
			// result = true;
			// }

			// return result;
		} catch (MyException e) {
			// Reporter.log(e.getMessage());
			Reporter.log("切换币别失败！");
			throw e;
		}
	}

}
