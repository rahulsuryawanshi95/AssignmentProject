package com.naukari.object;

import java.util.List;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public final static int TIMEOUT = 120;
	public WebDriverWait wait;
	public WebDriver webdriver;

	public BasePage(WebDriver driver) {
		this.webdriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, TIMEOUT);

	}

	public void waitForElementLoad(List<WebElement> webElement) {
		wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
	}


	public void sleep(long milsec) {
		try {
			Thread.sleep(milsec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void waitForElementLoad(WebElement webElement) {
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	public void waitForElementVisibility(WebElement webElement) {
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public void waitForElementInVisibility(WebElement webElement) {
		wait.until(ExpectedConditions.invisibilityOf((webElement)));
	}

	public void waitForElementVisibility(WebElement webElement, String text) {
		wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
	}

	public void clear(WebElement e) {
		e.clear();
	}

	public void fillText(WebElement e, String text) {
		e.sendKeys(text);
	}

	public String getText(WebElement el) {

		try {
			waitForElementVisibility(el);
			return el.getText();
		} catch (StaleElementReferenceException e) {
			waitForElementLoad(el);
			sleep(3000);
			return el.getText();
		}
	}

	public String getTextWithNoWait(WebElement el) {
		return el.getText();
	}

	protected void submit(WebElement el) {
		el.submit();
	}

	public String getCurrentUrl() {
		return webdriver.getCurrentUrl();
	}

	public void setUrl(String URL) {
		webdriver.get(URL);
	}

	public String getTitle() {
		return webdriver.getTitle();
	}

	public void click(WebElement el) {
		try {
			waitForElementLoad(el);
			el.click();
		} catch (StaleElementReferenceException | ElementClickInterceptedException e1) {
			sleep(3000);
			el.click();
		}
	}
	

	public boolean isElementSelected(WebElement webElement) {
		return webElement.isSelected();
	}

	public boolean isElementEnabled(WebElement webElement) {
		return webElement.isEnabled();
	}

	public boolean isElementDisplayed(WebElement webElement) {
		return webElement.isDisplayed();
	}

	public void rightClick(WebElement e) {
		Actions action = new Actions(webdriver);
		waitForElementLoad(e);
		try {
			// right click function from ACTION
			action.contextClick(e).perform();

		} catch (StaleElementReferenceException e1) {
			waitForElementLoad(e);
			sleep(3000);
		}
	}

	public void scrollDownOnWeb(WebElement value) {
		JavascriptExecutor js = (JavascriptExecutor) webdriver;
		js.executeScript("arguments[0].scrollIntoView();", value);
	}

	public void navigateToUrl(String URL) {
		webdriver.navigate().to(URL);
	}


	public void maxWindow() {
		webdriver.manage().window().maximize();
	}

}
