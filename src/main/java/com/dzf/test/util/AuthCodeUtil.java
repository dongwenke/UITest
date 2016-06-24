package com.dzf.test.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class AuthCodeUtil {
	
	/*
	 * 防止实例化
	 */
	private AuthCodeUtil(){}

	public static byte[] takeScreenshot(WebDriver driver) throws IOException {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		return ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
		// TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		// return takesScreenshot.getScreenshotAs(OutputType.BYTES);
	}

	public static BufferedImage createElementImage(WebDriver driver, WebElement webElement) throws IOException {
		// 获得webElement的位置和大小。
		Point location = webElement.getLocation();
		Dimension size = webElement.getSize();
		// 创建全屏截图。
		BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(takeScreenshot(driver)));
		// 截取webElement所在位置的子图。
		BufferedImage croppedImage = originalImage.getSubimage(location.getX(), location.getY(), size.getWidth(),
				size.getHeight());
		return croppedImage;
	}

	public static String getAuthCode(WebDriver driver, WebElement element) {
		try {
			BufferedImage bi = createElementImage(driver, element);
			Tesseract instance = new Tesseract(); // JNA Interface Mapping
			String result = instance.doOCR(bi);
			result = result.replace(" ", "").replace("\n", "");
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TesseractException e) {
			e.printStackTrace();
		}
		return null;
	}
}
