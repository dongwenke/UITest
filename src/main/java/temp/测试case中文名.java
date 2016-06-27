package temp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.testng.*;
import org.testng.annotations.*;
//import org.uncommons.reportng.HTMLReporter;

//@Listeners({ HTMLReporter.class })
public class 测试case中文名 {

	public static void main(String[] args) {
		System.out.println(1);
	}

	@Test(priority=1)
	public void test1() {
		Reporter.log("step1");
		Reporter.log("step2");
		Reporter.log("step3");
		Assert.assertEquals(1, 2);
	}

	@Test(priority=2)
	public void test2() {
		Reporter.log("step1");
		Reporter.log("step2");
		Reporter.log("step3");
		Assert.assertEquals(2, 2);
	}

	@Test(priority=1)
	public void test3() {
		Reporter.log("step1");
		Reporter.log("step2");
		Reporter.log("step3");
		Assert.assertEquals(1, 1);
	}

	@AfterTest()
	public void test4() {
		Reporter.log("step1");
		Reporter.log("step2");
		Reporter.log("step3");
		Assert.assertEquals(1, 2);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		if (!result.isSuccess()) {
			catchExceptions(result);
		}
	}

	public void catchExceptions(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		System.out.println("result" + result);

		String methodName = result.getName();
		System.out.println(methodName);

		if (!result.isSuccess()) {
			File file = new File("");

			Reporter.setCurrentTestResult(result);

			System.out.println(file.getAbsolutePath());
			// Reporter.log(result.getName());

			String filePath = file.getAbsolutePath();
			filePath = filePath.replace("/opt/apache-tomcat-7.0.64/webapps", "http://172.18.44.114:8080");
			Reporter.log("<img src='file:\\" + filePath + "\\" + result.getName() + ".jpg' hight='100' width='100'/>");
			int width = 100;
			int height = 100;
			String s = "这是一张测试图片";
			File screenShotFile = new File(file.getAbsolutePath() + "/" + result.getName() + ".jpg");

			Font font = new Font("Serif", Font.BOLD, 10);
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = (Graphics2D) bi.getGraphics();
			g2.setBackground(Color.BLACK);
			g2.clearRect(0, 0, width, height);
			g2.setPaint(Color.RED);

			FontRenderContext context = g2.getFontRenderContext();
			Rectangle2D bounds = font.getStringBounds(s, context);
			double x = (width - bounds.getWidth()) / 2;
			double y = (height - bounds.getHeight()) / 2;
			double ascent = -bounds.getY();
			double baseY = y + ascent;

			g2.drawString(s, (int) x, (int) baseY);

			try {
				ImageIO.write(bi, "jpg", screenShotFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
