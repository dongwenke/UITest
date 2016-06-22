package com.dzf.test.model;

import java.util.ArrayList;
import java.util.List;

public class Voucher {
	String summary;
	List<VoucherSubject> subjectList = new ArrayList<VoucherSubject>();

	public Voucher(String summary) {
		this.summary = summary;
	}

	public Voucher(String summary, VoucherSubject... voucherSubject) {
		this.summary = summary;

		for (int i = 0; i < voucherSubject.length; i++) {
			subjectList.add(i, voucherSubject[i]);
		}

	}

	public String getSummary() {
		return summary;
	}

	public void setSubjectList(List<VoucherSubject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<VoucherSubject> getSubjectList() {
		return subjectList;
	}

	public class VoucherSubject {
		/*
		 * base 属性
		 */

		/* 科目名称 */
		String name;
		/* 科目方向 */
		科目方向 direction;
		/* 金额 */
		double money;

		/*
		 * 额外属性
		 */

		boolean 启用外币;

		/* 外币简称 */
		String currency;
		/* 汇率 */
		double rate;
		/* 原币 */
		double oriMoney;

		boolean 启用库存;

		/* 库存商品名称 */
		String stockGoodsName;
		/* 商品数量 */
		double count;
		/* 单价 */
		double unitPrice;

		public VoucherSubject(String name, String direction, String money, boolean 启用外币, String currency, String rate,
				String oriMoney, boolean 启用库存, String stockGoodsName, String count, String unitPrice) {

			this.name = name;

			if (direction != null) {
				switch (direction) {
				case "借方":
					this.direction = 科目方向.借方;
					break;
				case "贷方":
					this.direction = 科目方向.贷方;
					break;
				case "默认":
					this.direction = 科目方向.默认;
					break;
				case "":
					this.direction = 科目方向.默认;
					break;
				}
			} else {
				this.direction = 科目方向.默认;
			}

			if (money == null || money == "") {
				this.money = -1;
			} else {
				this.money = new Double(money);
			}

			this.启用外币 = 启用外币;

			this.currency = currency;

			if (rate == null || rate == "") {
				this.rate = -1;
			} else {
				this.rate = new Double(rate);
			}

			if (oriMoney == null || oriMoney == "") {
				this.oriMoney = -1;
			} else {
				this.oriMoney = new Double(oriMoney);
			}
			this.启用库存 = 启用库存;

			this.stockGoodsName = stockGoodsName;

			if (count == null || count == "") {
				this.count = -1;
			} else {
				this.count = new Double(count);
			}

			if (unitPrice == null || unitPrice == "") {
				this.unitPrice = -1;
			} else {
				this.unitPrice = new Double(unitPrice);
			}

		}

		public String getName() {
			return name;
		}

		public 科目方向 getDirection() {
			return direction;
		}

		public double getMoney() {
			return money;
		}

		public boolean is启用外币() {
			return 启用外币;
		}

		public String getCurrency() {
			return currency;
		}

		public double getRate() {
			return rate;
		}

		public double getOriMoney() {
			return oriMoney;
		}

		public boolean is启用库存() {
			return 启用库存;
		}

		public String getStockGoodsName() {
			return stockGoodsName;
		}

		public double getCount() {
			return count;
		}

		public double getUnitPrice() {
			return unitPrice;
		}

	}

	public enum 科目方向 {
		借方, 贷方, 默认;
	}
}
