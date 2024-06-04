package com.main;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.driver.DriverSetup;

public class Main {
	public String username = "priyamvadha2000"; 
	public String accesskey = "Ap5Qp8dPIv1ia09dZrwXTrz8uqpx40GciQboIypeQjHDMZVjGZ";
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;
	private DriverSetup driverSetup = new DriverSetup();

	@Test
	@Parameters({ "browser", "version", "platform" })
	public void scenario1(String browser, String version, String platform) {
		RemoteWebDriver driver = null;
		driver = driverSetup.startDriver(platform, browser, version, username, accesskey, gridURL);
		driver.get("https://www.lambdatest.com/selenium-playground/");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 6000);
		closeOnCookieBot(platform, webDriverWait);
		WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Simple Form Demo')]"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click();
		webDriverWait.until(ExpectedConditions.urlContains("simple-form-demo"));
		String ExpectedValue = "Welcome to LambdaTest for Selenium Exam";
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-message"))).sendKeys(ExpectedValue);
		driver.findElement(By.id("showInput")).click();
		String actualValue = driver.findElement(By.id("message")).getText();
		assertEquals(actualValue, ExpectedValue);
		driver.quit();
	}

	@Test
	@Parameters({ "browser", "version", "platform" })
	public void scenario2(String browser, String version, String platform) {
		RemoteWebDriver driver = null;
		driver = driverSetup.startDriver(platform, browser, version, username, accesskey, gridURL);
		driver.get("https://www.lambdatest.com/selenium-playground/");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 6000);
		closeOnCookieBot(platform, webDriverWait);
		WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Drag & Drop Sliders')]"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='slider3']/div/output")));
		String actualValue  = driver.findElement(By.xpath("//*[@id='slider3']/div/output")).getText();
		WebElement slider = driver.findElement(By.xpath("//*[@id='slider3']/div/input"));
		int desiredValue = 95;
		for(int i = Integer.parseInt(actualValue); i < desiredValue; i++) {
			slider.sendKeys(Keys.ARROW_RIGHT);
		}

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='slider3']/div/output")));
		actualValue  = 	driver.findElement(By.xpath("//*[@id='slider3']/div/output")).getText();
		assertEquals(Integer.parseInt(actualValue), desiredValue);
		driver.quit();
	}

	@Test
	@Parameters({ "browser", "version", "platform" })
	public void scenario3(String browser, String version, String platform) {
		RemoteWebDriver driver = null;
		driver = driverSetup.startDriver(platform, browser, version, username, accesskey, gridURL);
		driver.get("https://www.lambdatest.com/selenium-playground/");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 5000);
		closeOnCookieBot(platform, webDriverWait);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Input Form Submit')]")));
		driver.findElement(By.xpath("//a[contains(text(),'Input Form Submit')]")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#seleniumform > div.text-right.mt-20 > button"))).click();
		String actualErrMsg = driver.findElement(By.id("name")).getAttribute("validationMessage");
		String expectedErrMsg = "Please fill in the fields";
		new SoftAssert().assertEquals(actualErrMsg, expectedErrMsg);

		driver.findElement(By.id("name")).sendKeys("Priyam");
		driver.findElement(By.id("inputEmail4")).sendKeys("priyamvadha2000@gmail.com");
		driver.findElement(By.id("inputPassword4")).sendKeys("Priyam@123");
		driver.findElement(By.id("company")).sendKeys("LambdaTest");
		driver.findElement(By.id("websitename")).sendKeys("www.testLambdaTest.com");
		driver.findElement(By.id("inputCity")).sendKeys("Coimbatore");
		driver.findElement(By.id("inputAddress1")).sendKeys("700345");
		driver.findElement(By.id("inputAddress2")).sendKeys("Hari Street");
		driver.findElement(By.id("inputState")).sendKeys("AZ");
		driver.findElement(By.id("inputZip")).sendKeys("98765");

		Select select = new Select(driver.findElement(By.xpath("//form[@id='seleniumform']//select")));
		select.selectByVisibleText("United States");
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

		String actualMsg = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'success-msg')]"))).getText();
		String expectedMsg = "Thanks for contacting us, we will get back to you shortly.";
		assertEquals(actualMsg, expectedMsg);
		driver.quit();
	}
	
	public void closeOnCookieBot(String platform, WebDriverWait webDriverWait) {
		if(platform.contains("macOS")) {
			try {
				webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Allow all']"))).click();
			}
			catch(Exception e) {
			}
		}
	}
}
