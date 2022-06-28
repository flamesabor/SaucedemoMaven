package com.webapp.scripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.webapp.constants.AutomationConstants;
import com.webapp.pages.LoginPage;
import com.webapp.utilities.ExcelUtility;

public class TestClass extends TestBaseclass{
	LoginPage objLogin;
	
	@Test(priority=1, description= "Login attempt with valid credentials")
	public void verifyValidLogin() throws IOException{
		objLogin = new LoginPage(driver); 
		String username = ExcelUtility.getCellData(0, 0);
		String password = ExcelUtility.getCellData(0, 1);
		objLogin.setUsername(username);
		objLogin.setPassword(password);
		objLogin.clickLogin();
		String expUrl = AutomationConstants.HOMEPAGEURL;
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
		
	}
	
	@Test (priority=2, description = "Login attempt with username and password fields blank")
	public void blankFieldLogin() throws IOException, Exception{
		objLogin = new LoginPage(driver);
		driver.get(prop.getProperty("url"));
		Thread.sleep(2000);
		objLogin.clickLogin();
		String errormsg = objLogin.loginError();
		String expErrormsg = AutomationConstants.USERERROR;
		Assert.assertEquals(errormsg, expErrormsg);
	}
	
	@Test (priority=3, description="Login attempt with password field empty")
	public void noPasswordLogin() throws IOException, Exception{
		objLogin = new LoginPage(driver);
		objLogin.clearFields();
		String username = ExcelUtility.getCellData(0, 0);
		objLogin.setUsername(username);
		Thread.sleep(2000);
		objLogin.clickLogin();
		String errormsg = objLogin.loginError();
		String expErrormsg = AutomationConstants.PASSERROR;
		Assert.assertEquals(errormsg, expErrormsg);
	}
	
	@Test (priority=4, description="Login attempt with valid username and incorrect password")
	public void invalidLogin() throws IOException, Exception
	{
		objLogin = new LoginPage(driver);
		objLogin.clearFields();
		String username = ExcelUtility.getCellData(0, 0);
		objLogin.setUsername(username);
		objLogin.setPassword("test");
		objLogin.clickLogin();
		String errormsg = objLogin.loginError();
		String expError = AutomationConstants.NOMATCHERROR;
		Assert.assertEquals(errormsg, expError);		
	}
	
	@Test (priority=5, description="Login attempt using locked out user credentials")
	public void lockedoutUser() throws IOException, Exception
	{
		objLogin = new LoginPage(driver);
		objLogin.clearFields();
		String username = ExcelUtility.getCellData(1, 0);
		String password = ExcelUtility.getCellData(1, 1);
		objLogin.setUsername(username);
		objLogin.setPassword(password);
		objLogin.clickLogin();
		String errormsg = objLogin.loginError();
		String expError = AutomationConstants.LOCKERROR;
		Assert.assertEquals(errormsg, expError);	
	}
	
	@AfterTest
	public void close()
	{
		driver.close();
	}
}
