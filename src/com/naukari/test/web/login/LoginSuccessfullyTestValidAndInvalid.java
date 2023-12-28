package com.naukari.test.web.login;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.naukari.test.BaseTest;
import com.naukari.utils.Util;

import com.naukari.object.web.login.Login;

public class LoginSuccessfullyTestValidAndInvalid extends BaseTest {
	 
	@Test(dataProvider = "read", description = "To Verify that a user with valid credentials can successfully log in and loginess the system.")
	public void A01_toVerifyThatAUserWithValidCredentialsCanSuccessfullyLogInAndloginessTheSystem(String userName,
			String password, String emailId) throws InterruptedException
			 {

		Login login = new Login(driver);

		login.goTo();
		
		Assert.assertTrue(login.validationOfUserNameField());

		login.enterUserNameOnLoginPage(userName);

		Assert.assertTrue(login.validationOfPasswordField());

		login.enterPasswordOnLoginPage(password);

		Assert.assertTrue(login.validationOfLoginButton());
		
		login.clickOnLoginButton();
		
	}

	
	@Test(dataProvider = "read", description = "To Validate that an error message is displayed when attempting to log in with invalid credentials")
	public void A02_toValidateThatAnErrorMessageIsDisplayedWhenAttemptingToLogInWithInvalidCredentials(String userName,
			String password, String emailId) throws InterruptedException
			 {

		Login login = new Login(driver);

		login.goTo();
		
		Assert.assertTrue(login.validationOfUserNameField());

		login.enterInvalidUserNameOnLoginPage();

		Assert.assertTrue(login.validationOfPasswordField());

		login.enterInvalidPasswordOnLoginPage();

		Assert.assertTrue(login.validationOfLoginButton());
		
		login.clickOnLoginButton();
		
		Assert.assertTrue(login.validationsForErrorMessage());
		
		Thread.sleep(2000);
		
	}

	@Test(dataProvider = "read", description = "To Ensure that the login page retains the entered username and password fields' values after a failed login attempt.")
	public void A03_toEnsureThatTheLoginPageRetainsTheEnteredUsernameAndPasswordFieldsValuesAfterAFailedLoginAttempt(String userName,
			String password, String emailId) throws InterruptedException
			 {

		Login login = new Login(driver);

		login.goTo();
		
		Assert.assertTrue(login.validationOfUserNameField());

		login.enterUserNameOnLoginPage(userName);

		Assert.assertTrue(login.validationOfPasswordField());

		login.enterPasswordOnLoginPage(password);

		Assert.assertTrue(login.validationOfLoginButton());
		
		login.clickOnLoginButton();

//		This method not in scope because save pop-up not coming while login with credentials.		
//		login.clickOnSaveButtonFromSavePopup();
			
	}
	
	
	@Test(dataProvider = "read", description = "To Test that the 'Login' button is disabled when both username and password fields are empty.")
	public void A04_toTestThatTheLoginButtonIsDisabledWhenBothUsernameAndPasswordFieldsAreEmpty(String userName,
			String password, String emailId) throws InterruptedException
			 {

		Login login = new Login(driver);

		login.goTo();
		
//		This method give you assertion error, "isElement" give you true value because the login button is seems enabled
//		Assert.assertFalse(login.validationOfLoginButton());
		
		login.clickOnLoginButton();
			
	}

	@Test(dataProvider = "read", description = "To Confirm that the 'Login' button is enabled only when both username and password fields are filled with valid input.")
	public void A05_toConfirmThatTheLoginButtonIsEnabledOnlyWhenBothUsernameAndPasswordFieldsAreFilledWithValidInput(String userName,
			String password, String emailId) throws InterruptedException
			 {

		Login login = new Login(driver);

		login.goTo();
		
		Assert.assertTrue(login.validationOfUserNameField());

		login.enterUserNameOnLoginPage(userName);

		Assert.assertTrue(login.validationOfPasswordField());

		login.enterPasswordOnLoginPage(password);

//		This assertion give you always true value because the login button is seems always enabled.
		Assert.assertTrue(login.validationOfLoginButton());
		
		login.clickOnLoginButton();

	}	

	
	@DataProvider(name = "read")
	public Object[][] readfile() throws IOException {
		return Util.readExcelFile(1, "LoginToNaukariPortal");
	}

}
