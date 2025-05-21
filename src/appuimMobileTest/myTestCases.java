package appuimMobileTest;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Driver;
import java.util.List;
import java.util.Map;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class myTestCases {

	DesiredCapabilities caps = new DesiredCapabilities();
	AndroidDriver driver;

	@BeforeTest
	public void mySetup() {

		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "abc");
		File myApplicationCalculator = new File("src/myApplication/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP, myApplicationCalculator.getAbsolutePath());

	}

	@BeforeMethod
	public void beforeEachTest() throws MalformedURLException {

		URI uri = URI.create("http://127.0.0.1:4723/wd/hub");
		URL url = uri.toURL();
		driver = new AndroidDriver(url, caps);

	}

	@Test(priority = 1)
	public void myTest() throws IOException {

		driver.findElement(By.id("com.google.android.calculator:id/digit_2")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_div")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();

		String actualResult = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		assertEquals(actualResult, "5");

		java.util.Date date = new java.util.Date();
		String fileName = date.toString().replace(":", "-");

		TakesScreenshot ts = (TakesScreenshot) driver;

		File fileOfScreenshot = ts.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(fileOfScreenshot, new File("src/screenshot/" + fileName + ".jpg"));
	}

	@Test(priority = 2)
	public void clickAllNumber() throws IOException {
		java.util.Date date = new java.util.Date();
		String fileName = date.toString().replace(":", "-");
		List<WebElement> allNumber = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < allNumber.size(); i++) {

			if (allNumber.get(i).getDomAttribute("resource-id").contains("digit")) {
				allNumber.get(i).click();

			}

			TakesScreenshot ts = (TakesScreenshot) driver;

			File fileOfScreenshot = ts.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(fileOfScreenshot, new File("src/screenshot/" + fileName + ".jpg"));

		}

	}

}
