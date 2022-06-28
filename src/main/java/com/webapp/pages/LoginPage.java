package com.webapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	@FindBy(id="user-name")
	private WebElement username;
	@FindBy(id="password")
	private WebElement password;
	@FindBy(id="login-button")
	private WebElement loginbtn;
	@FindBy(xpath="//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/h3[1]")
	private WebElement loginerror;
	
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setUsername(String strUsername)
	{
		username.sendKeys(strUsername);
	}
	
	public void setPassword(String strPassword)
	{
		password.sendKeys(strPassword);
	}
	
	public void clickLogin()
	{
		loginbtn.click();
	}
	
	public String loginError()
	{
		return loginerror.getText();
	}
	
	public void clearFields()
	{
		username.clear();
		password.clear();
	}

}
