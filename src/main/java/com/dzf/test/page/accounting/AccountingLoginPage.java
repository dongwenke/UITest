package com.dzf.test.page.accounting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.AuthCodeUtil;
import com.dzf.test.util.DatePickerUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.PropertyUtil;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class AccountingLoginPage extends Handler {
	private final String xmlfile = "./config/page/accounting/" + this.getClass().getSimpleName() + ".xml";

	public AccountingLoginPage() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	/**
	 * 不使用日期登录，默认使用当前日期
	 */
	public boolean login(String username, String password, String company) throws InterruptedException, MyException {
		return login(username, password, company, null);
	}

	/**
	 * @param username
	 * @param password
	 * @param company
	 * @param date
	 * @return
	 * @throws InterruptedException
	 * @throws MyException
	 */
	public boolean login(String username, String password, String company, String date)
			throws InterruptedException, MyException {
		try {
			boolean result = false;
			// 尝试使用cookie登录
			result = loginByCookies(username, password, company, date);

			if (!result) {
				result = loginNormal(username, password, company, date);
			}

			// 如果登录成功，添加cookie
			if (result) {
				addcookies(username, company, date);
			}

			return result;
		} catch (MyException e) {
			logger.error("登录失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("登录失败！");
			throw e;
		}
	}

	/**
	 * @param username
	 * @param password
	 * @param company
	 * @param date
	 * @return
	 */
	public boolean loginByCookies(String username, String password, String company, String date) {

		try {
			this.open();

			getCookies(username, company, date);

			this.open();

			boolean login = isDisplayed("用户信息");

			if (!login) {
				if (isDisplayed(By.xpath("// div[text()='被其它用户强制退出！']"))) {
					getWebElement(By.xpath("// div[text()='被其它用户强制退出！']/following-sibling::div/a[*='确定']")).click();
				}
			}

			return login;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	/**
	 * @param name
	 * @param password
	 * @param company
	 * @param date
	 * @return
	 * @throws InterruptedException
	 * @throws MyException
	 */
	public boolean loginNormal(String name, String password, String company, String date)
			throws InterruptedException, MyException {
		// 打开登录页面
		this.open();

		if (date != null && !date.equals("")) {
			new DatePickerUtil(getWebElement("选择日期时间选择器")).choseDate(date);
		}

		// 输入用户名
		input("用户名输入框", name);

		// 输入密码
		input("密码输入框", password);

		// 输入验证码
		String authCodeRetryCount = PropertyUtil.prop.getProperty("authCodeRetryCount");

		int count;
		if (null == authCodeRetryCount || "".equals(authCodeRetryCount)) {
			count = 1;
		} else if (Integer.valueOf(authCodeRetryCount) < 0) {
			count = 1000000;
		} else {
			count = Integer.valueOf(authCodeRetryCount);
		}

		for (int i = 0; i < count; i++) {
			try {
				WebElement authcode = getWebElement(By.id("codeImg"));
				input("验证码输入框", AuthCodeUtil.getAuthCode(driver, authcode));
			} catch (ElementNotFoundException e) {
				System.out.println("验证码未启用");
				// Reporter.log("未启用验证码！");
			}

			// 点击登录按钮
			click("登录按钮");

			if (!isDisplayed(By.xpath("//div[contains(.,'验证码错误')]"))) {
				break;
			} else {
				if (isDisplayed("已登录提示") || isDisplayed("选择公司面板-公司列表")) {
					break;
				}
			}
		}

		// 判断该用户是否已登录
		if (isDisplayed("已登录提示")) {

			// 如果显示则点击确定
			click("确认强制退出");

		}

		Thread.sleep(500);

		// // Reporter.log("选择公司面板-搜索框输入：" + company);
		// input("选择公司面板-搜索输入框", company);
		// // Reporter.log("选择公司面板-搜索框输入：回车");
		// input("选择公司面板-搜索输入框", Keys.RETURN);

		WebTableUtil table = new WebTableUtil(getWebElement("选择公司面板-公司列表"));

		click(table.getTr(company));

		// 点击确定
		click("确定所选公司");

		return waitToDisplayed("用户信息");
	}

	/**
	 * @param username
	 * @param company
	 * @param date
	 */
	private void addcookies(String username, String company, String date) {

		try {
			File file = new File("config/cookies.data");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			for (Cookie ck : driver.manage().getCookies()) {
				bw.write(username + ";" + company + ";" + date + ";" + ck.getName() + ";" + ck.getValue() + ";"
						+ ck.getDomain() + ";" + ck.getPath() + ";" + ck.getExpiry() + ";" + ck.isSecure());
				bw.newLine();
			}
			bw.flush();
			bw.close();
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("cookie write to file");
		}
	}

	/**
	 * @param username
	 * @param company
	 * @param date
	 * @throws Exception
	 */
	public void getCookies(String username, String company, String date) throws Exception {

		File file = new File("config/cookies.data");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			StringTokenizer str = new StringTokenizer(line, ";");
			while (str.hasMoreTokens()) {
				String uname = str.nextToken();
				String comp = str.nextToken();
				String cdate = str.nextToken();

				String name = str.nextToken();
				String value = str.nextToken();
				String domain = str.nextToken();
				String path = str.nextToken();
				Date expiry = null;
				String dt = str.nextToken();

				if (!dt.equals("null")) {
					// expiry = new Date(dt);
					expiry = new SimpleDateFormat().parse(dt);
					// System.out.println();
				}

				boolean isSecure = new Boolean(str.nextToken()).booleanValue();

				if (date == null) {
					date = "null";
				}

				if (uname.equals(username) && comp.equals(company) && cdate.equals(date)) {
					Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
					driver.manage().addCookie(ck);
				}
			}
		}
		br.close();
		fr.close();
	}

}
