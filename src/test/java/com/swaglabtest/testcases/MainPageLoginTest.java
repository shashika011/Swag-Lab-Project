package com.swaglabtest.testcases;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swaglabtest.pages.BaseClass;
import com.swaglabtest.pages.MainLoginPage;

public class MainPageLoginTest extends BaseClass{
	
	BaseClass bs= new BaseClass();
	
	MainLoginPage login;

	@BeforeMethod
	public void PageIninitialize(Method method) {
		login = PageFactory.initElements(driver, MainLoginPage.class); 
		//System.out.println(method.getName());
		String className = method.getDeclaringClass().getSimpleName();
		String methodName = method.getName();

		logger = report.createTest(className + " - " + methodName);
		logger.info("Starting Test: " + className + " → " + methodName);
	}

	
	@Test(priority = 1)
	public void UserLogingWithInvalidCredentials()
	{
		logger = report.createTest(excelp.getStringdata("Login", 1, 0));
		login.UserLogin(excelp.getStringdata("Login", 1, 1), excelp.getStringdata("Login", 1, 2));
		logger.info("username: "+excelp.getStringdata("Login", 1, 1));
		logger.info("password: "+excelp.getStringdata("Login", 1, 2));
		
		boolean errormesssage=login.getErrorMessage();

		
		if (errormesssage==true)
		{logger.fail("Test Fail");}
		else { logger.pass("Test Pass");}
	   
	    driver.navigate().refresh();
	}

	@Test(priority = 2)
	public void UserLogingWithvalidCredentials() throws InterruptedException
	{
		MainLoginPage mainlogin=PageFactory.initElements(driver,MainLoginPage.class);
		mainlogin.ClearData();
		logger = report.createTest(excelp.getStringdata("Login", 2, 0));
		mainlogin.UserLogin(excelp.getStringdata("Login", 2, 1), excelp.getStringdata("Login", 2, 2));
		logger.info("username: "+excelp.getStringdata("Login", 2, 1));
		logger.info("password: "+excelp.getStringdata("Login", 2, 2));
		
		System.out.println(driver.getCurrentUrl());
		if (driver.getCurrentUrl().contains("https://www.saucedemo.com/inventory.html"))
		{logger.pass("Test Pass");
		}else {logger.fail("Test Fail");}
			
	}

}
