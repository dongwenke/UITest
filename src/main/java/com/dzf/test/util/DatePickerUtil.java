package com.dzf.test.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DatePickerUtil {

	private WebElement datepicker;

	public DatePickerUtil(WebElement datepicker) {
		this.datepicker = datepicker;
	}

	/*
	 * date 参数格式 2016-05-03
	 */
	public void choseDate(String date) {
		try {
			String 年月 = datepicker.findElement(By.xpath(".//div[@class='calendar-header']/div[@class='calendar-title']/span")).getText();

			String 月 = 年月.split(" ")[0];
			String 年 = 年月.substring(年月.length() - 4, 年月.length());

			String year = date.split("-")[0];
			String month = date.split("-")[1];
			String day = date.split("-")[2];

			// 判断当前年份并点击

			int z = (Integer.valueOf(year) - Integer.valueOf(年));

			if (z >= 0) {
				for (int i = 0; i < z; i++) {
					Thread.sleep(200);
					datepicker.findElement(By.xpath(".//div[@class='calendar-header']/div[contains(@class,'calendar-nextyear')]")).click();
				}
			} else {
				for (int i = 0; i < (-z); i++) {
					Thread.sleep(200);
					datepicker.findElement(By.xpath(".//div[@class='calendar-header']/div[contains(@class,'calendar-prevyear')]")).click();
				}
			}

			// 当前月份并点击
			int y = (Integer.valueOf(month) - Integer.valueOf(MonthToInt(月)));
			if (y >= 0) {
				for (int i = 0; i < y; i++) {
					Thread.sleep(200);
					datepicker.findElement(By.xpath(".//div[@class='calendar-header']/div[contains(@class,'calendar-nextmonth')]")).click();
				}
			} else {
				for (int i = 0; i < (-y); i++) {
					Thread.sleep(200);
					datepicker.findElement(By.xpath(".//div[@class='calendar-header']/div[contains(@class,'calendar-prevmonth')]")).click();
				}
			}

			// 判断当前日并点击

			// 去掉0；03
			if (day.startsWith("0")) {
				day = day.substring(1);
			}

			datepicker.findElement(By.xpath(".//div[@class='calendar-body']//tr/td[text()=" + day + "][not(contains(@class,'calendar-other-month'))]")).click();

		} catch (Exception e) {

		}
	}

	public String MonthToInt(String month) {

		switch (month) {
		case "一月":
			month = "1";
			break;
		case "二月":
			month = "2";
			break;
		case "三月":
			month = "3";
			break;
		case "四月":
			month = "4";
			break;
		case "五月":
			month = "5";
			break;
		case "六月":
			month = "6";
			break;
		case "七月":
			month = "7";
			break;
		case "八月":
			month = "8";
			break;
		case "九月":
			month = "9";
			break;
		case "十月":
			month = "10";
			break;
		case "十一月":
			month = "11";
			break;
		case "十二月":
			month = "12";
			break;
		default:
			System.out.println(month);
		}

		return month;
	}

	public static void main(String[] args) {

		System.setProperty("webdriver.firefox.bin", "D:/Program Files (x86)/Mozilla Firefox/firefox.exe");

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
		driver.get("http://172.16.2.142:8090/dzfadmin/");
		
		driver.findElement(By.xpath("//input[@name='date']/../input[@readonly='readonly']")).click();
		
		new DatePickerUtil(driver.findElement(By.xpath("html/body/div[7]"))).choseDate("2006-01-01");
		

	}

}
