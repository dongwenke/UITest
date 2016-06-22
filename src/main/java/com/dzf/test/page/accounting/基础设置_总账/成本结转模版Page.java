package com.dzf.test.page.accounting.基础设置_总账;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class 成本结转模版Page extends Handler {

	private final String xmlfile = "./config/page/accounting/基础设置_总账/" + this.getClass().getSimpleName() + ".xml";

	public 成本结转模版Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean add(/* 目标借方科目 */ String targetDebitSubjectName, /* 目标贷方科目 */String targetCreditSubjectName,
			/* 目标取数科目 */String targetFillSubjectName, /* 目标结转比例 */String targetTratio, /* 目标摘要 */String targetSummary,
			/* 目标结转类型 */String targetType) throws MyException {

		switchToDefaultContent();
		switchToFrame("成本结转模版");

		verifyNotNull2(targetDebitSubjectName, targetCreditSubjectName, targetFillSubjectName, targetTratio,
				targetSummary, targetType);

		click("增加按钮");

		editTemplet(targetDebitSubjectName, targetCreditSubjectName, targetFillSubjectName, targetTratio, targetSummary,
				targetType);

		return false;
	}

	public boolean modify(/* 原始借方科目 */String originDebitSubjectName, /* 原始贷方科目 */String originCreditSubjectName,
			/* 原始取数科目 */ String originFillSubjectName, /* 目标借方科目 */ String targetDebitSubjectName,
			/* 目标贷方科目 */String targetCreditSubjectName, /* 目标取数科目 */String targetFillSubjectName,
			/* 目标结转比例 */String targetTratio, /* 目标摘要 */String targetSummary, /* 目标结转类型 */String targetType)
			throws InterruptedException, MyException {

		switchToDefaultContent();
		switchToFrame("成本结转模版");

		// 判断原始内容全都不为空
		verifyNotNull1(originDebitSubjectName, originCreditSubjectName, originFillSubjectName);

		// 表格中查找该列
		// tr[child::td[@field='pk_creditaccount_name' and
		// div='库存商品']][child::td[@field='pk_debitaccount_name' and
		// div='材料销售成本']][child::td[@field='pk_fillaccount_name' and
		// div='销售材料收入']]
		// *[@id='dataGrid']/div/div/div/div[2]/div[2]/table
		click(new WebTableUtil(getWebElement("模版table")).getTr(originDebitSubjectName, originCreditSubjectName,
				originFillSubjectName));

		click("修改按钮");

		return false;
	}

	private void verifyNotNull1(/* 原始借方科目 */String originDebitSubjectName, /* 原始贷方科目 */String originCreditSubjectName,
			/* 原始取数科目 */ String originFillSubjectName) throws MyException {

		if (originDebitSubjectName == null || originDebitSubjectName.equals("")) {
			throw new MyException("原借方科目不能为空！");
		}
		if (originCreditSubjectName == null || originCreditSubjectName.equals("")) {
			throw new MyException("原贷方科目不能为空！");
		}
		if (originFillSubjectName == null || originFillSubjectName.equals("")) {
			throw new MyException("原取数科目不能为空！");
		}
	}

	private void verifyNotNull2(/* 目标借方科目 */ String targetDebitSubjectName, /* 目标贷方科目 */String targetCreditSubjectName,
			/* 目标取数科目 */String targetFillSubjectName, /* 目标结转比例 */String targetTratio, /* 目标摘要 */String targetSummary,
			/* 目标结转类型 */String targetType) throws MyException {

		if (targetDebitSubjectName == null || targetDebitSubjectName.equals("")) {
			throw new MyException("借方科目不能为空！");
		}
		if (targetCreditSubjectName == null || targetCreditSubjectName.equals("")) {
			throw new MyException("贷方科目不能为空！");
		}
		if (targetFillSubjectName == null || targetFillSubjectName.equals("")) {
			throw new MyException("取数科目不能为空！");
		}
		if (targetTratio == null || targetTratio.equals("")) {
			throw new MyException("结转比例不能为空！");
		}
		if (targetSummary == null || targetSummary.equals("")) {
			throw new MyException("摘要不能为空！");
		}
		if (targetType == null || targetType.equals("")) {
			throw new MyException("结转类型不能为空！");
		}
	}

	public boolean editTemplet( /* 目标借方科目 */ String targetDebitSubjectName, /* 目标贷方科目 */String targetCreditSubjectName,
			/* 目标取数科目 */String targetFillSubjectName, /* 目标结转比例 */String targetTratio, /* 目标摘要 */String targetSummary,
			/* 目标结转类型 */String targetType) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("成本结转模版");

			// 借方科目
			if (targetDebitSubjectName != null && targetDebitSubjectName.equals("")) {
				// input[following-sibling::*[@name='pk_debitaccount_name']]
				// 借方科目输入框输入
				input("借方科目输入框", targetDebitSubjectName);

				// 判断悬浮层是否有多个选项-如果有，抛出异常
				// div[div='1001_库存现金' and parent::*[contains(@style,'display:
				// block;')]]/div[@style='display: block;']

				List<WebElement> elementLIst = getWebElements("查询结果");
				if (elementLIst.size() != 1) {
					throw new MyException("找到0个或多个结果，请检查您的输入！");
				}
				// 点击悬浮层的第一个选项
				click(elementLIst.get(0));
			}

			// 贷方科目
			if (targetCreditSubjectName != null && targetCreditSubjectName.equals("")) {
				// input[following-sibling::*[@name='pk_debitaccount_name']]
				// 借方科目输入框输入
				input("借方科目输入框", targetCreditSubjectName);

				// 判断悬浮层是否有多个选项-如果有，抛出异常
				// div[div='1001_库存现金' and parent::*[contains(@style,'display:
				// block;')]]/div[@style='display: block;']

				List<WebElement> elementLIst = getWebElements("查询结果");
				if (elementLIst.size() != 1) {
					throw new MyException("找到0个或多个结果，请检查您的输入！");
				}
				// 点击悬浮层的第一个选项
				click(elementLIst.get(0));
			}

			// 取数科目
			if (targetFillSubjectName != null && targetFillSubjectName.equals("")) {
				// input[following-sibling::*[@name='pk_debitaccount_name']]
				// 借方科目输入框输入
				input("借方科目输入框", targetFillSubjectName);

				// 判断悬浮层是否有多个选项-如果有，抛出异常
				// div[div='1001_库存现金' and parent::*[contains(@style,'display:
				// block;')]]/div[@style='display: block;']

				List<WebElement> elementLIst = getWebElements("查询结果");
				if (elementLIst.size() != 1) {
					throw new MyException("找到0个或多个结果，请检查您的输入！");
				}
				// 点击悬浮层的第一个选项
				click(elementLIst.get(0));
			}

			input("结转比例输入框", targetTratio);

			input("摘要输入框", targetSummary);

			if (targetType != null && targetType.equals("")) {
				// targetType
				// span[following-sibling::input[@name='jztype']]/a
				// div[text()='销售成本结转' and contains(@class,'combobox-item')]
				click("结转类型选择按钮");

				click("结转类型选项-" + targetType);

			}

			click("修改面板-保存按钮");// *[@id='dlg-buttons']/a[1]

			return isDisplayed("修改面板-保存按钮");
			
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("编辑模版失败！");
			throw e;
		}
	}

}
