package com.swaglabtest.testcases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swaglabtest.pages.BaseClass;
import com.swaglabtest.pages.*;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class NewPurchaseTest extends BaseClass {

	BaseClass bs = new BaseClass();

	ItemPurchase purchase;
	MainLoginPage login;
	ShopCartpage shopcart;

	String itemName1;
	String itemPrice1;
	String itemName2;
	String itemPrice2;
	String itemName3;
	String itemPrice3;
	String itemName4;
	String itemPrice4;
	
	String shopcartitemName1;
	String shopcartitemPrice1;
	String shopcartitemName2;
	String shopcartitemPrice2;
	String shopcartitemName3;
	String shopcartitemPrice3;
	String shopcartitemName4;
	String shopcartitemPrice4;
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
	public void getTitle() {
		Assert.assertEquals(driver.getTitle(), "Swag Labs","Title does not match");
	}

	@Test(priority = 2)
	public void SigelItemPurchase()  {
		logger = report.createTest(excelp.getStringdata("Purchase", 1, 0));
		List<WebElement> ele = purchase.getOneItemOrder();
		itemName1 = ele.get(0).getText();
		itemPrice1 = ele.get(1).getText();
        
	    logger.info("Selected Item: " + itemName1);
	    logger.info("Selected Item Price: " + itemPrice1);
	    
	    String Expectedname1=excelp.getStringdata("Purchase", 1, 2);
	    String  Expectedprice1=excelp.getCurrencyData("Purchase", 1, 3);
	    
		Assert.assertEquals(Expectedname1,itemName1,"Selected Item " + itemName1 + " Expected " + Expectedname1);
		Assert.assertEquals(Expectedprice1,itemPrice1,"Selected Item price " + itemPrice1 + " Expected " + Expectedprice1);
       
		purchase.clickShopCart();
		
		shopcart = PageFactory.initElements(driver, ShopCartpage.class);
         
		List<WebElement> shopc = shopcart.validateSinglePurchase();
		shopcartitemName1 = shopc.get(0).getText();
		shopcartitemPrice1 = shopc.get(1).getText();
        
		 logger.info("ShopCart Item: " + shopcartitemName1);
		 logger.info("ShopCart Item Price: " + shopcartitemPrice1);
	
		Assert.assertEquals(Expectedname1,shopcartitemName1,"Shopping Cart display Item " + shopcartitemName1 + " Expected " + Expectedname1);
		Assert.assertEquals(Expectedprice1,shopcartitemPrice1,"Shopping Cart display Item " + shopcartitemPrice1 + " Expected " + Expectedprice1);
		
		driver.navigate().back();
	}

	@Test(priority = 3)
	public void multipleItemPurchase() {
		

		logger = report.createTest(excelp.getStringdata("Purchase", 2, 0));
		List<WebElement> ele = purchase.getAddNewItem();
		itemName1 = ele.get(0).getText();
		itemPrice1 = ele.get(1).getText();
		itemName2 = ele.get(2).getText();
		itemPrice2 = ele.get(3).getText();
		
		logger.info("Selected Item: " + itemName1);
	    logger.info("Selected Item Price: " + itemPrice1);
	    logger.info("Selected Item: " + itemName2);
	    logger.info("Selected Item Price: " + itemPrice2);
        
	    String Expectedname1=excelp.getStringdata("Purchase", 1, 2);
	    String Expectedprice1=excelp.getCurrencyData("Purchase", 1, 3);
	    String Expectedname2=excelp.getStringdata("Purchase", 2, 2);
	    String Expectedprice2=excelp.getCurrencyData("Purchase", 2, 3);
	    
		Assert.assertEquals(Expectedname1,itemName1,"Selected Item " + itemName1 + " Expected " + Expectedname1);
		Assert.assertEquals(Expectedprice1,itemPrice1,"Selected Item price " + itemPrice1 + " Expected " + Expectedprice1);
		Assert.assertEquals(Expectedname2,itemName2,"Selected Item " + itemName2 + " Expected " + Expectedname2);
		Assert.assertEquals(Expectedprice2,itemPrice2,"Selected Item price " + itemPrice2 + " Expected " + Expectedprice2);
	    
		purchase.clickShopCart();
	 
		shopcart = PageFactory.initElements(driver, ShopCartpage.class);
		List<WebElement> shopc = shopcart.validateMultiplePurchase();
		shopcartitemName1 = shopc.get(0).getText();
		shopcartitemPrice1 = shopc.get(1).getText();
		shopcartitemName2 = shopc.get(2).getText();
		shopcartitemPrice2 = shopc.get(3).getText();
		  
		 logger.info("ShopCart Item: " + shopcartitemName1);
		 logger.info("ShopCart Item Price: " + shopcartitemPrice1);
		 logger.info("ShopCart Item: " + shopcartitemName2);
		 logger.info("ShopCart Item Price: " + shopcartitemPrice2);
		 
		 
		 Assert.assertEquals(Expectedname1,shopcartitemName1,"Shopping Cart display Item " + shopcartitemName1 + " Expected " + Expectedname1);
		 Assert.assertEquals(Expectedprice1,shopcartitemPrice1,"Shopping Cart display Item " + shopcartitemPrice1 + " Expected " + Expectedprice1);
		 Assert.assertEquals(Expectedname2,shopcartitemName2,"Shopping Cart display Item " + shopcartitemName2 + " Expected " + Expectedname2);
		 Assert.assertEquals(Expectedprice2,shopcartitemPrice2,"Shopping Cart display Item " + shopcartitemPrice2 + " Expected " + Expectedprice2);
				
		 
		 driver.navigate().back();

	}
	
	
	@Test(priority = 4)
	public void threeItemPurchase() {
	

		logger = report.createTest(excelp.getStringdata("Purchase", 3, 0));
		List<WebElement> ele = purchase.getAddNewItemthree();
		itemName1 = ele.get(0).getText();
		itemPrice1 = ele.get(1).getText();
		itemName2 = ele.get(2).getText();
		itemPrice2 = ele.get(3).getText();
		itemName3 = ele.get(4).getText();
		itemPrice3 = ele.get(5).getText();
		
		
		logger.info("Selected Item: " + itemName1);
	    logger.info("Selected Item Price: " + itemPrice1);
	    logger.info("Selected Item: " + itemName2);
	    logger.info("Selected Item Price: " + itemPrice2);
	    logger.info("Selected Item: " + itemName3);
	    logger.info("Selected Item Price: " + itemPrice3);
	    
	    
	    String Expectedname1=excelp.getStringdata("Purchase", 1, 2);
	    String Expectedprice1=excelp.getCurrencyData("Purchase", 1, 3);
	    String Expectedname2=excelp.getStringdata("Purchase", 2, 2);
	    String Expectedprice2=excelp.getCurrencyData("Purchase", 2, 3);
	    String Expectedname3=excelp.getStringdata("Purchase", 3, 2);
	    String Expectedprice3=excelp.getCurrencyData("Purchase", 3, 3);
	    
		Assert.assertEquals(Expectedname1,itemName1,"Selected Item " + itemName1 + " Expected " + Expectedname1);
		Assert.assertEquals(Expectedprice1,itemPrice1,"Selected Item price " + itemPrice1 + " Expected " + Expectedprice1);
		Assert.assertEquals(Expectedname2,itemName2,"Selected Item " + itemName2 + " Expected " + Expectedname2);
		Assert.assertEquals(Expectedprice2,itemPrice2,"Selected Item price " + itemPrice2 + " Expected " + Expectedprice2);
		Assert.assertEquals(Expectedname3,itemName3,"Selected Item " + itemName3 + " Expected " + Expectedname3);
		Assert.assertEquals(Expectedprice3,itemPrice3,"Selected Item price " + itemPrice3 + " Expected " + Expectedprice3);
          
		purchase.clickShopCart();
	 
		shopcart = PageFactory.initElements(driver, ShopCartpage.class);
		List<WebElement> shopc = shopcart.validateThreeItemPurchase();
		shopcartitemName1 = shopc.get(0).getText();
		shopcartitemPrice1 = shopc.get(1).getText();
		shopcartitemName2 = shopc.get(2).getText();
		shopcartitemPrice2 = shopc.get(3).getText();
		shopcartitemName3 = shopc.get(4).getText();
		shopcartitemPrice3 = shopc.get(5).getText();
		
		  
		 logger.info("ShopCart Item: " + shopcartitemName1);
		 logger.info("ShopCart Item Price: " + shopcartitemPrice1);
		 logger.info("ShopCart Item: " + shopcartitemName2);
		 logger.info("ShopCart Item Price: " + shopcartitemPrice2);
		 logger.info("ShopCart Item: " + shopcartitemName3);
		 logger.info("ShopCart Item Price: " + shopcartitemPrice3);

		 
		 Assert.assertEquals(Expectedname1,shopcartitemName1,"Shopping Cart display Item " + shopcartitemName1 + " Expected " + Expectedname1);
		 Assert.assertEquals(Expectedprice1,shopcartitemPrice1,"Shopping Cart display Item " + shopcartitemPrice1 + " Expected " + Expectedprice1);
		 Assert.assertEquals(Expectedname2,shopcartitemName2,"Shopping Cart display Item " + shopcartitemName2 + " Expected " + Expectedname2);
		 Assert.assertEquals(Expectedprice2,shopcartitemPrice2,"Shopping Cart display Item " + shopcartitemPrice2 + " Expected " + Expectedprice2);
		 Assert.assertEquals(Expectedname3,shopcartitemName3,"Shopping Cart display Item " + shopcartitemName3 + " Expected " + Expectedname3);
		 Assert.assertEquals(Expectedprice3,shopcartitemPrice3,"Shopping Cart display Item " + shopcartitemPrice3 + " Expected " + Expectedprice3);
			
		 
		 driver.navigate().back();
		 
	}
	
	@Test(priority = 5)
	public void FourItemPurchase() {
	

		logger = report.createTest(excelp.getStringdata("Purchase", 4, 0));
		//System.out.println(excelp.getStringdata("Purchase", 4, 0));
		List<WebElement> ele = purchase.getAddNewItemFour();
		itemName1 = ele.get(0).getText();
		itemPrice1 = ele.get(1).getText();
		itemName2 = ele.get(2).getText();
		itemPrice2 = ele.get(3).getText();
		itemName3 = ele.get(4).getText();
		itemPrice3 = ele.get(5).getText();
		itemName4 = ele.get(6).getText();
		itemPrice4 = ele.get(7).getText();
		
		
		logger.info("Selected Item: " + itemName1);
	    logger.info("Selected Item Price: " + itemPrice1);
	    logger.info("Selected Item: " + itemName2);
	    logger.info("Selected Item Price: " + itemPrice2);
	    logger.info("Selected Item: " + itemName3);
	    logger.info("Selected Item Price: " + itemPrice3);
	    logger.info("Selected Item: " + itemName4);
	    logger.info("Selected Item Price: " + itemPrice4);
	    
	    
	    String Expectedname1=excelp.getStringdata("Purchase", 1, 2);
	    String Expectedprice1=excelp.getCurrencyData("Purchase", 1, 3);
	    String Expectedname2=excelp.getStringdata("Purchase", 2, 2);
	    String Expectedprice2=excelp.getCurrencyData("Purchase", 2, 3);
	    String Expectedname3=excelp.getStringdata("Purchase", 3, 2);
	    String Expectedprice3=excelp.getCurrencyData("Purchase", 3, 3);
	    String Expectedname4=excelp.getStringdata("Purchase", 4, 2);
	    String Expectedprice4=excelp.getCurrencyData("Purchase", 4, 3);
	    
		Assert.assertEquals(Expectedname1,itemName1,"Selected Item " + itemName1 + " Expected " + Expectedname1);
		Assert.assertEquals(Expectedprice1,itemPrice1,"Selected Item price " + itemPrice1 + " Expected " + Expectedprice1);
		Assert.assertEquals(Expectedname2,itemName2,"Selected Item " + itemName2 + " Expected " + Expectedname2);
		Assert.assertEquals(Expectedprice2,itemPrice2,"Selected Item price " + itemPrice2 + " Expected " + Expectedprice2);
		Assert.assertEquals(Expectedname3,itemName3,"Selected Item " + itemName3 + " Expected " + Expectedname3);
		Assert.assertEquals(Expectedprice3,itemPrice3,"Selected Item price " + itemPrice3 + " Expected " + Expectedprice3);
		Assert.assertEquals(Expectedname4,itemName4,"Selected Item " + itemName4 + " Expected " + Expectedname4);
		Assert.assertEquals(Expectedprice4,itemPrice4,"Selected Item price " + itemPrice4 + " Expected " + Expectedprice4);
	    

		purchase.clickShopCart();
	 
		shopcart = PageFactory.initElements(driver, ShopCartpage.class);
		List<WebElement> shopc = shopcart.validatefourItemPurchase();
		shopcartitemName1 = shopc.get(0).getText();
		shopcartitemPrice1 = shopc.get(1).getText();
		shopcartitemName2 = shopc.get(2).getText();
		shopcartitemPrice2 = shopc.get(3).getText();
		shopcartitemName3 = shopc.get(4).getText();
		shopcartitemPrice3 = shopc.get(5).getText();
		
		shopcartitemName4 = shopc.get(6).getText();
		shopcartitemPrice4 = shopc.get(7).getText();
		
		  
		 logger.info("ShopCart Item: " + shopcartitemName1);
		 logger.info("ShopCart Item Price: " + shopcartitemPrice1);
		 logger.info("ShopCart Item: " + shopcartitemName2);
		 logger.info("ShopCart Item Price: " + shopcartitemPrice2);
		 logger.info("ShopCart Item: " + shopcartitemName3);
		 logger.info("ShopCart Item Price: " + shopcartitemPrice3);
		 
		 logger.info("ShopCart Item: " + shopcartitemName4);
		 logger.info("ShopCart Item Price: " + shopcartitemPrice4);

		 
		 Assert.assertEquals(Expectedname1,shopcartitemName1,"Shopping Cart display Item " + shopcartitemName1 + " Expected " + Expectedname1);
		 Assert.assertEquals(Expectedprice1,shopcartitemPrice1,"Shopping Cart display Item " + shopcartitemPrice1 + " Expected " + Expectedprice1);
		 Assert.assertEquals(Expectedname2,shopcartitemName2,"Shopping Cart display Item " + shopcartitemName2 + " Expected " + Expectedname2);
		 Assert.assertEquals(Expectedprice2,shopcartitemPrice2,"Shopping Cart display Item " + shopcartitemPrice2 + " Expected " + Expectedprice2);
		 Assert.assertEquals(Expectedname3,shopcartitemName3,"Shopping Cart display Item " + shopcartitemName3 + " Expected " + Expectedname3);
		 Assert.assertEquals(Expectedprice3,shopcartitemPrice3,"Shopping Cart display Item " + shopcartitemPrice3 + " Expected " + Expectedprice3);
		 Assert.assertEquals(Expectedname4,shopcartitemName4,"Shopping Cart display Item " + shopcartitemName4 + " Expected " + Expectedname4);
		 Assert.assertEquals(Expectedprice4,shopcartitemPrice4,"Shopping Cart display Item " + shopcartitemPrice4 + " Expected " + Expectedprice4);
				
		 
     
		 
	}
	
	
	
	
	
	
	
	
	

}
