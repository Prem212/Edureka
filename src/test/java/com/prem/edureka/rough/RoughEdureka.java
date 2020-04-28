package com.prem.edureka.rough;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RoughEdureka {

	public static WebDriver driver;
	public static WebDriverWait wait;

	public static void setup() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		driver.get("https://www.edureka.co/");	
		
	

	}

	public static void tearDown() {
		driver.quit();
	}
	@Test
	public static void login() throws InterruptedException {
		setup();
		driver.findElement(By.linkText("Log In")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#si_popup_email"))).sendKeys("prem");
		driver.findElement(By.cssSelector("#si_popup_passwd")).sendKeys("prem");
		
		
		
	}
	
//	@Test
	public static void navigateToCorporateTraining() {
		setup();
		driver.findElement(By.linkText("Corporate Training")).click();

	}
//	@Test
	public static void navigateToCourses() {
		setup();
		driver.findElement(By.cssSelector(".ga-allcourses [href='/all-courses']")).click();
	}

	public static void enterCloudComputingPage() {
		// driver.findElement(
		// By.cssSelector(".topcatlinks#tabscathome
		// .giTrackElementHeader#cloud-computing-certification-courses"))
		// .click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".topcatlinks#tabscathome .giTrackElementHeader#cloud-computing-certification-courses")))
				.click();

	}

//	@Test
	public static void assertCloudPageLinks() {
		setup();
		enterCloudComputingPage();
		List<WebElement> l = driver.findElements(By.cssSelector(" #tabscat >li"));
		wait.until(ExpectedConditions.visibilityOfAllElements(l));
		System.out.println(l.size());
		for (WebElement var : l) {
			System.out.println(var.getText());
		}
	}

	public static List<WebElement> getAllthecoursesOnCCPage() {

		setup();
		enterCloudComputingPage();
		driver.findElement(By.cssSelector(".load-more-course")).click();
		List<WebElement> l = driver.findElements(By.cssSelector(
				".category-best-selling.noprice .container .row  php .clp-course-tile .clp-course-details h3"));
		// List<WebElement> l = driver.findElements(By.cssSelector(
		// ".category-best-selling.noprice .container .row php
		// .col-lg-3.col-md-3.col-sm-6.col-xs-12.course-col.mobile-border > a"));
		return l;
	}

//	@Test
	public static void validateNoOfCoursesOnCCPage() {
		List<WebElement> l = getAllthecoursesOnCCPage();		
		Assert.assertEquals(l.size(), 16);
	}
//	@Test
	public static void validateOpenStackPrice() {
		List<WebElement> l = getAllthecoursesOnCCPage();
		driver.findElement(By.cssSelector("[href='/open-stack'] button")).click();
		String str = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".final_pr.discount_16031")))
				.getText();
		System.out.println(str);

	}
	
//	@Test
	public static void selectSeleniumCertiFromSearchBox() {
		setup();
		driver.findElement(By.cssSelector(".typeahead__container #search-inp-overlay-new")).sendKeys("selenium");
	}

}
