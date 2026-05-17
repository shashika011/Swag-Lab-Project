package com.swaglabtest.pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

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
	
	private final By shopcart = 
			By.xpath("//*[@class='shopping_cart_link']");
	private final By shopping_cart_badge =
			By.xpath("//*[@class='shopping_cart_badge']");
	
	
	//---- Action Methods-----//
	//-- sinlgle item purchase--//
	
	public List<WebElement> getOneItemOrder() {
		WebElement itemname=wait.until(ExpectedConditions.elementToBeClickable(Sauce_Labs_Backpack));
		WebElement itemPrice=wait.until(ExpectedConditions.elementToBeClickable(Sauce_Labs_Backpack_Price));
		WebElement AddItemButton=wait.until(ExpectedConditions.elementToBeClickable(Add_Sauce_Labs_Backpack_button));
		AddItemButton.click();
	    return Arrays.asList(itemname, itemPrice);
	}
	
	//-- multiple item purchase--//
	
	public List<WebElement> getAddNewItem() {
		// FIRST ITEM
	    WebElement itemname = wait.until(ExpectedConditions.elementToBeClickable(Sauce_Labs_Backpack));
	    WebElement itemPrice = wait.until(ExpectedConditions.elementToBeClickable(Sauce_Labs_Backpack_Price));
	    WebElement AddItemButton = wait.until(ExpectedConditions.elementToBeClickable(Add_Sauce_Labs_Backpack_button));
	    if (AddItemButton.getText().equals("Add to cart")) {
	        AddItemButton.click();
	    }
	    // Second item
	    WebElement itemname1 = wait.until(ExpectedConditions.elementToBeClickable(Sauce_Labs_Bolt_T_Shirt));
	    WebElement itemPrice1 = wait.until(ExpectedConditions.elementToBeClickable(Sauce_Labs_Bolt_T_Shirt_Price));
	    WebElement AddItemButton1 = wait.until(ExpectedConditions.elementToBeClickable(Add_Sauce_Labs_Bolt_T_Shirt_Price_button));
	    if (AddItemButton1.getText().equals("Add to cart")) {
	        AddItemButton1.click();
	    }

	    return Arrays.asList(itemname, itemPrice, itemname1, itemPrice1);
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
