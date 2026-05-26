package com.swaglabtest.testcases;

import java.lang.reflect.Method;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swaglabtest.pages.BaseClass;
import com.swaglabtest.pages.ItemPurchase;
import com.swaglabtest.pages.MainLoginPage;
import com.swaglabtest.pages.ShopCartpage;

public class NewPurchaseTest extends BaseClass {

	BaseClass bs = new BaseClass();

	ItemPurchase purchase;
	MainLoginPage login;
	ShopCartpage shopcart;

	@BeforeMethod
	public void PageIninitialize(Method method) {

		if (purchase == null) {
			login = PageFactory.initElements(driver, MainLoginPage.class);
			login.UserLogin(excelp.getStringdata("Login", 2, 1), excelp.getStringdata("Login", 2, 2));
			purchase = PageFactory.initElements(driver, ItemPurchase.class);

		}

		String className = method.getDeclaringClass().getSimpleName();
		String methodName = method.getName();
		System.out.println("Running: " + className);
		logger = report.createTest(className + " - " + methodName);
		logger.info("Starting Test: " + className + " → " + methodName);

		System.out.println("Running Test: " + className + " → " + methodName);
	}

	@Test(priority = 1)
	public void newPurchasing() {

		for (int i = 1; i <= 5; i++) {
			if (excelp.getStringdata("Purchase", i, 1).trim().equals("Add Item")) {

				logger = report.createTest(excelp.getStringdata("Purchase", i, 0));
				logger.info("Selected Item Type" + excelp.getStringdata("Purchase", i, 1));
				logger.info("Selected Item " + excelp.getStringdata("Purchase", i, 2));
				logger.info("Selected Item Price " + excelp.getCurrencyData("Purchase", i, 3));

				boolean result = purchase.newPurchasing(excelp.getStringdata("Purchase", i, 4));
				Assert.assertEquals(result, true, "Item Not Found");
				purchase.clickShopCart();

				shopcart = PageFactory.initElements(driver, ShopCartpage.class);

				boolean resultshopcart = shopcart.verifyItemInCart(excelp.getStringdata("Purchase", i, 4));
				Assert.assertEquals(resultshopcart, true, "Item Not Found in cart");
				driver.navigate().back();
			} else if (excelp.getStringdata("Purchase", i, 1).trim().equals("Remove Item")) {
				logger = report.createTest(excelp.getStringdata("Purchase", i, 0));
				logger.info("Selected Item Type" + excelp.getStringdata("Purchase", i, 1));
				logger.info("Selected Item " + excelp.getStringdata("Purchase", i, 2));
				logger.info("Selected Item Price " + excelp.getCurrencyData("Purchase", i, 3));

				boolean result = purchase.DeletePurchasing(excelp.getStringdata("Purchase", i, 4));
				Assert.assertEquals(result, true, "Item was not removed from purchasing successfully.");
				boolean resultshopcart = shopcart.verifyRemoveItemInCart(excelp.getStringdata("Purchase", i, 4));
				Assert.assertEquals(resultshopcart, true, "Item was not removed from cart successfully.");
				driver.navigate().back();
			}

		}

	}

}
