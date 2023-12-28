package com.naukari.test.web.login;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.naukari.test.BaseTest;
import com.naukari.utils.Util;

import com.naukari.object.web.login.Login;

public class ForgotPasswordTestValidAndInValid extends BaseTest{

	@Test(dataProvider = "read", description = "To Verify that clicking the 'Forgot Password' link redirects the user to the password recovery page & the password recovery functionality steps following as per naukari.com")
	public void A01_toVerifyThatClickingTheForgotPasswordlinkRedirectsTheUserToThePasswordRecoveryPageAndThePasswordRecoveryFunctionality(String userName,
			String password, String emailId) throws InterruptedException
			 {

		Login login = new Login(driver);

		login.goTo();
	
		Assert.assertTrue(login.validationForLoginPageName());
		
		Assert.assertTrue(login.validationOfUserNameField());

		login.enterUserNameOnLoginPage(userName);

		Assert.assertTrue(login.validationOfPasswordField());

		login.enterPasswordOnLoginPage(password);

		Assert.assertTrue(login.validationOfLoginButton());
		
		login.clickOnLoginButton();
		
		Thread.sleep(2000);
		
		login.clickOnProfileSection();
	
		Thread.sleep(2000);
		
		login.clickOnLogOutOption();
		
		Thread.sleep(2000);
		
		login.goTo();
		
//		login.btnLoginFromDashboard();
		
		Thread.sleep(2000);

		Assert.assertTrue(login.validationOfForgotPasswordCta());
		
		login.clickOnForgotPasswordButton();	

		Thread.sleep(2000);

		Assert.assertTrue(login.validationForPageName());
				
		login.enterEmailOnForgotPasswordPage(emailId);

		Thread.sleep(2000);

		login.clickOnGoButton();

		Thread.sleep(2000);
		
//Note:		This method will give the exception for timeout because otp will be triggered on personal email.
		login.enterOTPonForgotPasswordPage();  //This is a static otp
		
		Thread.sleep(2000);

// Due to OTP validation submit button will not be worked		
//		Assert.assertTrue(login.validationOfSubmitCtaOnForgotPasswordPage());

//		login.clickOnSubmitButton();
		

/*	
 * 1. If user successfully reset the password 
 * 	  Then user can login with new password only
 * 	To verify that new password working or not run the First test case of Login Successfully class.
 */
		
	}

	@DataProvider(name = "read")
	public Object[][] readfile() throws IOException {
		return Util.readExcelFile(1, "LoginToNaukariPortal");
	}

}
