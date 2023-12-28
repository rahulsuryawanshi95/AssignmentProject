package com.naukari.object.web.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.naukari.object.BasePage;

public class Login extends BasePage {

	@FindBy(xpath = "//input[@id='usernameField']")
	private WebElement fdusernmae;

	@FindBy(xpath = "//input[@id='passwordField']|//input[@type='password']")
	private WebElement fdpassword;

	@FindBy(xpath = "//button[@type='submit'][1]")
	private WebElement btnLogin;

	@FindBy(xpath = "//img[@alt='naukri user profile img']")
	private WebElement btnProfileSection;

	@FindBy(xpath = "//a[normalize-space()='Logout']")
	private WebElement btnLogOut;

	@FindBy(xpath = "//a[@id='login_Layer']")
	private WebElement btnLoginFromDashboard;

	@FindBy(xpath = "//*[text()='Forgot Password?']")
	private WebElement btnForgotPassword;

	@FindBy(xpath = "//strong[@class='hdn']")
	private WebElement txtForForgotPasswordPage;

	@FindBy(xpath = "//strong[normalize-space()='Login']")
	private WebElement txtForLoginPage;

	@FindBy(xpath = "//*[@type='email']|//*[@id='usernameField']")
	private WebElement fdEnterEmailForForgotPassword;

	@FindBy(xpath = "//button[normalize-space()='GO']")
	private WebElement btnGo;

	@FindBy(xpath = "//input[@id='otpField']")
	private WebElement fdEnterOTPforForgotPassword;

	@FindBy(xpath = "//button[normalize-space()='Submit']")
	private WebElement btnSubmitOnForgotPassword;

	@FindBy(xpath = "//span[@class='col s12 commonErrorMsg']")
	private WebElement txtForInvalidCredentialsOnLoginPage;

	@FindBy(xpath = "")
	private WebElement btnSaveFromSavePopup;

	public Login(WebDriver webdriver) {
		super(webdriver);
		// TODO Auto-generated constructor stub
	}

	public void goTo() {
		setUrl("https://www.naukri.com/nlogin/login");
		maxWindow();
		System.out.println(getTitle());
		System.out.println(getCurrentUrl());
	}

	public boolean validationOfUserNameField() throws InterruptedException {
		waitForElementLoad(fdusernmae);
		sleep(2000);
		return isElementEnabled(fdusernmae);
	}

	public void enterUserNameOnLoginPage(String userName) throws InterruptedException {
		waitForElementLoad(fdusernmae);
		clear(fdusernmae);
		fillText(fdusernmae, userName);
	}

	public boolean validationOfPasswordField() throws InterruptedException {
		return isElementEnabled(fdpassword);
	}

	public void enterPasswordOnLoginPage(String password) {
		waitForElementLoad(fdpassword);
		clear(fdpassword);
		fillText(fdpassword, password);
	}

	public boolean validationOfLoginButton() {
		return isElementEnabled(btnLogin);
	}

	public void clickOnLoginButton() throws InterruptedException {
		waitForElementLoad(btnLogin);
		click(btnLogin);
	}

	public boolean validationOfForgotPasswordCta() {
		return isElementEnabled(btnForgotPassword);
	}

	public void clickOnProfileSection() {
		waitForElementLoad(btnProfileSection);
		click(btnProfileSection);
	}

	public void clickOnLogOutOption() {
		waitForElementLoad(btnLogOut);
		click(btnLogOut);
	}

	public void btnLoginFromDashboard() {
		waitForElementLoad(btnLoginFromDashboard);
		click(btnLoginFromDashboard);
	}

	public void clickOnForgotPasswordButton() throws InterruptedException {
		waitForElementLoad(btnForgotPassword);
		click(btnForgotPassword);
	}

	public boolean validationForLoginPageName() {
		waitForElementLoad(txtForLoginPage);
		String actualErrorMessage = getText(txtForLoginPage);
		System.out.println("Login page name: " + actualErrorMessage);
		Assert.assertEquals(actualErrorMessage, "Login");
		System.out.println("Above page name is correct.");
		return true;

	}

	public boolean validationForPageName() {
		waitForElementLoad(txtForForgotPasswordPage);
		String actualErrorMessage = getText(txtForForgotPasswordPage);
		System.out.println("password Forrget page name: " + actualErrorMessage);
		Assert.assertEquals(actualErrorMessage, "Forgot Password");
		System.out.println("Above page name is correct.");
		return true;
	}

	public void enterEmailOnForgotPasswordPage(String emailId) {
		waitForElementVisibility(fdEnterEmailForForgotPassword);
		waitForElementLoad(fdEnterEmailForForgotPassword);
		clear(fdEnterEmailForForgotPassword);
		fillText(fdEnterEmailForForgotPassword, emailId);
	}

	public void clickOnGoButton() throws InterruptedException {
		waitForElementLoad(btnGo);
		click(btnGo);
	}

	public void enterOTPonForgotPasswordPage() throws InterruptedException {
		waitForElementLoad(fdEnterOTPforForgotPassword);
		clear(fdEnterOTPforForgotPassword);
		sleep(5000);
		fdEnterOTPforForgotPassword.sendKeys("123456");
		scrollDownOnWeb(fdEnterOTPforForgotPassword);
	}

	public boolean validationOfSubmitCtaOnForgotPasswordPage() {
		waitForElementLoad(btnSubmitOnForgotPassword);
		return isElementEnabled(btnSubmitOnForgotPassword);
	}

	public void clickOnSubmitButton() throws InterruptedException {
		waitForElementLoad(btnSubmitOnForgotPassword);
		click(btnSubmitOnForgotPassword);
	}

	public void enterInvalidPasswordOnLoginPage() {
		waitForElementLoad(fdpassword);
		clear(fdpassword);
		fdpassword.sendKeys("rahul@123");
	}

	public void enterInvalidUserNameOnLoginPage() {
		waitForElementLoad(fdusernmae);
		clear(fdusernmae);
		fdusernmae.sendKeys("rahulsuryawanshi");
	}

	public boolean validationsForErrorMessage() {
		waitForElementLoad(txtForInvalidCredentialsOnLoginPage);
		String actualErrorMessage = getText(txtForInvalidCredentialsOnLoginPage);
		System.err.println(actualErrorMessage);
		Assert.assertEquals(actualErrorMessage, "Invalid details. Please check the Email ID - Password combination.");
		System.out.println("Above Error Message for Invalid credentials on Login page is correct.");
		return true;
	}

//	This method not in scope because save pop-up not coming while login with credentials.
	public void clickOnSaveButtonFromSavePopup() {
		waitForElementLoad(btnSaveFromSavePopup);
		click(btnSaveFromSavePopup);
	}

}
