package com.naukari.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.naukari.utils.Util;


public class BaseTest {
	public WebDriver driver;

	@BeforeClass
	@Parameters({"browserName"})
	public void beforeClass(ITestContext context, @Optional String browserName)
			throws IOException {
		System.out.println("I am in baseClass1");

		switch (browserName) {

		case "chrome":
			driver = Util.getWebdriver(browserName);
			context.setAttribute("Driver", driver);
			break;
			
		case "firefox":
			driver = Util.getWebdriver(browserName);
			context.setAttribute("Driver", driver);
			break;	

		}

	}
	@AfterClass
	@Parameters({ "browserName"})
	public void afterClass() throws IOException {
		driver.close();
//		driver.quit();
		System.out.println("Driver closed");
	}

}
