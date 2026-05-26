package com.swaglabtest.testcases;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
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
	public void UserLogingWithCredentials()
	{
		for (int i = 0; i <= 2; i++) {
			logger = report.createTest(excelp.getStringdata("Login", i, 0));
			if (excelp.getStringdata("Login", i, 0) == "Invaid Cradentials") {
				login.UserLogin(excelp.getStringdata("Login", i, 1), excelp.getStringdata("Login", i, 2));
				logger.info("username: " + excelp.getStringdata("Login", i, 1));
				logger.info("password: " + excelp.getStringdata("Login", i, 2));
				boolean errormesssage = login.getErrorMessage();
				Assert.assertEquals(errormesssage, true, "Error message should be displayed for invalid credentials.");
				driver.navigate().refresh();
			}
			else if(excelp.getStringdata("Login", i, 0) == "Valid Cradentials")
			{
				login.UserLogin(excelp.getStringdata("Login", i, 1), excelp.getStringdata("Login", i, 2));
				logger.info("username: " + excelp.getStringdata("Login", i, 1));
				logger.info("password: " + excelp.getStringdata("Login", i, 2));
				String getcurURL=driver.getCurrentUrl();
				Assert.assertEquals(getcurURL,"https://www.saucedemo.com/inventory.html", "User should be redirected to inventory page after successful login.");
				driver.navigate().refresh();
			}
		}
	}
}
