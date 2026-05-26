package com.swaglabtest.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShopCartpage {
	WebDriver driver;
	WebDriverWait wait;

	public ShopCartpage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	}

	/* ===================== CART PAGE LOCATORS ===================== */

	private final By CART_CONTAINER = 
			By.xpath("//div[@id='cart_contents_container']");

	private final By SAUCE_LABS_BACKPACK = 
			By.xpath("//div[@class='inventory_item_name' and contains(text(),'Backpack')]");

	private final By SAUCE_LABS_BACKPACK_PRICE = 
			By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']");

	private final By SAUCE_LABS_BOLT_TSHIRT = 
			By.xpath("//div[@class='inventory_item_name' and contains(text(),'T-Shirt')]");


	private final By SAUCE_LABS_BOLT_TSHIRT_PRICE = 
			By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']");

	private final By SAUCE_LABS_ONESIE =
			By.xpath("//div[@class='inventory_item_name' and contains(text(),'Onesie')]");
	

	private final By SAUCE_LABS_ONESIE_PRICE = 
			By.xpath("//div[text()='Sauce Labs Onesie']/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']");

	//Sauce_Labs_Fleece_Jacket
	
   private final By Sauce_Labs_Fleece_Jacket=
		   By.xpath("//div[@class='inventory_item_name' and contains(text(),'Fleece')]");
   
   private final By Sauce_Labs_Fleece_Jacket_PRICE = 
		   By.xpath("//div[text()='Sauce Labs Fleece Jacket']/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']");
	/* ===================== BUTTONS ===================== */

	@FindBy(id = "continue-shopping")
	private WebElement continueButton;

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	/* ===================== ACTION METHODS ===================== */

	
	public Map<String, ItemCart> getShopCartItemsMap() {
		Map<String,ItemCart> CartItem = new HashMap<>();
		CartItem.put("Sauce_Labs_Backpack", new ItemCart(SAUCE_LABS_BACKPACK, SAUCE_LABS_BACKPACK_PRICE));
		CartItem.put("Sauce_Labs_Bolt_T_Shirt", new ItemCart(SAUCE_LABS_BOLT_TSHIRT, SAUCE_LABS_BOLT_TSHIRT_PRICE));
		CartItem.put("Sauce_Labs_Onesie", new ItemCart(SAUCE_LABS_ONESIE, SAUCE_LABS_ONESIE_PRICE));
		CartItem.put("Sauce_Labs_Fleece_Jacket", new ItemCart(Sauce_Labs_Fleece_Jacket, Sauce_Labs_Fleece_Jacket_PRICE));
		return CartItem;
	}
	
	public boolean verifyItemInCart(String ItemName)
	{
		boolean result=false;
		Map<String,ItemCart> cartitem=getShopCartItemsMap();
		ItemCart itemcart = cartitem.get(ItemName);
		
		if (itemcart != null) {
			WebElement itemNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(itemcart.getName()));
			WebElement itemPriceElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(itemcart.getPrice()));
			System.out.println("Item Name: " + itemNameElement.getText());
			System.out.println("Item Price: " + itemPriceElement.getText());
			result=true;
		} else {
			System.out.println("Item not found in cart: " + ItemName);
			result=false;
		}
		
		return result;	
	}
	
	
	public boolean verifyRemoveItemInCart(String itemName) {
	    try {
	        Map<String, ItemCart> cartItems = getShopCartItemsMap();
	        ItemCart itemCart = cartItems.get(itemName);

	        if (itemCart == null) {
	            return true; // not even in map = already removed
	        }

	        WebElement itemElement = wait.until(
	            ExpectedConditions.presenceOfElementLocated(itemCart.getName())
	        );

	        return !itemElement.isDisplayed();

	    } catch (TimeoutException | NoSuchElementException e) {
	        // element not found = successfully removed
	        return true;
	    }
	}
	
	
	
	
	/* ===================== NAVIGATION ===================== */

	public void clickContinueShopping() {
		wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
	}

	public void clickCheckout() {
		wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
	}
}