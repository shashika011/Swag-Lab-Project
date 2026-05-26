package com.swaglabtest.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ItemPurchase {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public ItemPurchase(WebDriver driver)
	{
		this.driver=driver;
	     wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	}
	
	/*----------- list of Elements-----*/

	private final By Sauce_Labs_Backpack=
			 By.xpath("//a[@id='item_4_title_link']/div");
	private final By Sauce_Labs_Backpack_Price=
			By.xpath("//a[@id='item_4_title_link']/parent::*/following-sibling::*/div");
	private final By Add_Sauce_Labs_Backpack_button = 
			By.xpath("//a[@id='item_4_title_link']/parent::*/following-sibling::*/button");	
	
	private final By Sauce_Labs_Bolt_T_Shirt = 
			By.xpath("//a[@id='item_1_title_link']/div");
	private final By Sauce_Labs_Bolt_T_Shirt_Price = 
			By.xpath("//a[@id='item_1_title_link']/parent::*/following-sibling::*/div");
	private final By Add_Sauce_Labs_Bolt_T_Shirt_Price_button = 
			By.xpath("//a[@id='item_1_title_link']/parent::*/following-sibling::*/button");
	
	private final By Sauce_Labs_Onesie = 
			By.xpath("//a[@id='item_2_title_link']/div");
	private final By Sauce_Labs_Onesie_Price =
			By.xpath("//a[@id='item_2_title_link']/parent::*/following-sibling::*/div");
	private final By Add_Sauce_Labs_Onesie_button =
			By.xpath("//a[@id='item_2_title_link']/parent::*/following-sibling::*/button");
	
	private final By Test_allTheThing_T_Shirt_Red = 
			By.xpath("//a[@id='item_3_title_link']/div");
	private final By Test_allTheThing_T_Shirt_Red_Price =
			By.xpath("//a[@id='item_3_title_link']/parent::*/following-sibling::*/div");
	private final By Add_Test_allTheThing_T_Shirt_Red_Button = 
			By.xpath("//a[@id='item_3_title_link']/parent::*/following-sibling::*/button");
	
	private final By Sauce_Labs_Fleece_Jacket = 
	 By.xpath("//a[@id='item_5_title_link']/div");
	private final By Sauce_Labs_Fleece_Jacket_Price =
			By.xpath("//a[@id='item_5_title_link']/parent::div/following-sibling::div/div");
	private final By Sauce_Labs_Fleece_Jacket_button = 
			By.xpath("//a[@id='item_5_title_link']/parent::div/following-sibling::div/button");
	
	
	
	
	private final By shopcart = 
			By.xpath("//*[@class='shopping_cart_link']");
	private final By shopping_cart_badge =
			By.xpath("//*[@class='shopping_cart_badge']");
	
	
	//---- Action Methods-----//
	
	
		public Map<String, Item> getItemsMap() {

		    Map<String, Item> items = new HashMap<>();

		    items.put("Sauce_Labs_Backpack", new Item(
		            Sauce_Labs_Backpack,
		            Sauce_Labs_Backpack_Price,
		            Add_Sauce_Labs_Backpack_button));

		    items.put("Sauce_Labs_Bolt_T_Shirt", new Item(
		            Sauce_Labs_Bolt_T_Shirt,
		            Sauce_Labs_Bolt_T_Shirt_Price,
		            Add_Sauce_Labs_Bolt_T_Shirt_Price_button));

		    items.put("Sauce_Labs_Onesie", new Item(
		            Sauce_Labs_Onesie,
		            Sauce_Labs_Onesie_Price,
		            Add_Sauce_Labs_Onesie_button));

		    items.put("Sauce_Labs_Fleece_Jacket", new Item(
		            Sauce_Labs_Fleece_Jacket,
		            Sauce_Labs_Fleece_Jacket_Price,
		            Sauce_Labs_Fleece_Jacket_button));

		    items.put("Test_allTheThing_T_Shirt_Red", new Item(
		            Test_allTheThing_T_Shirt_Red,
		            Test_allTheThing_T_Shirt_Red_Price,
		            Add_Test_allTheThing_T_Shirt_Red_Button));

		    return items;
		}

		
		public boolean newPurchasing(String itemName) {
			boolean result =false;
		    
			Map<String, Item> items = getItemsMap();
			Item item = items.get(itemName);

		    if (item == null) {
		        System.out.println("Item not found: " + itemName);
		        result=false;
		    }
		    WebElement nameElement = wait.until(
		            ExpectedConditions.visibilityOfElementLocated(item.getName()));
            System.out.println(nameElement);
		    WebElement priceElement = wait.until(
		            ExpectedConditions.visibilityOfElementLocated(item.getPrice()));

		    WebElement buttonElement = wait.until(
		            ExpectedConditions.elementToBeClickable(item.getButton()));

		    System.out.println("Add Item: " + nameElement.getText());
		    System.out.println("Add Price: " + priceElement.getText());

		    if (buttonElement.getText().equalsIgnoreCase("Add to cart")) {
		        buttonElement.click();
		        result=true;
		    }
			return result;
		}
		
		
		public boolean DeletePurchasing(String ItemName)
		{  Map<String, Item> items = getItemsMap();
		   Item item = items.get(ItemName);
		   boolean result =false;
		   
			 if (item == null) {
			        System.out.println("Item not found: " + ItemName);
			        result=false;
			    }
			 WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(item.getButton()));
			 
			 if(buttonElement.getText().equals("Remove"))
			 {
				 System.out.println("Remove Item: " +ItemName);
				 buttonElement.click();
			        result=true;
			 }
			 ;
			return result;
		}
	
	public void acceptAlert()
	{
		driver.switchTo().alert().accept();
	}
	
	public void clickShopCart()
	{
		 WebElement itemcart=wait.until(ExpectedConditions.elementToBeClickable(shopcart));
		 itemcart.click();
	}
	
	public String ShopCartBatch()
	{
		WebElement shopping_cart_b=wait.until(ExpectedConditions.elementToBeClickable(shopping_cart_badge));
		return shopping_cart_b.getText();
	}
	
	
}
