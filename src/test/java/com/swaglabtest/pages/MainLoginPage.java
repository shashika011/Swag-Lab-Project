package com.swaglabtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainLoginPage {
	
	WebDriver driver;
	
	public MainLoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	@FindBy(id="user-name") WebElement username;
	@FindBy(id="password") WebElement password;
	@FindBy(name="login-button") WebElement loginbutton;
	@FindBy(xpath="//h3[contains(text(),'Epic sadface: Username and password do not match any user in this service')]") WebElement errormessage;
	
	public void UserLogin(String uname, String pass)
	{
		username.sendKeys(uname);
		password.sendKeys(pass);
		loginbutton.click();
	}
	
	public boolean getErrorMessage()
	{
		return errormessage.isDisplayed();
	}
	
	public void ClearData()
	{
		username.clear();
		password.clear();
	}

}
