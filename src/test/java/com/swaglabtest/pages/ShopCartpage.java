package com.swaglabtest.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
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

	/* ===================== BUTTONS ===================== */

	@FindBy(id = "continue-shopping")
	private WebElement continueButton;

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	/* ===================== ACTION METHODS ===================== */

	/**
	 * Validate single item purchase
	 */
	public List<WebElement> validateSinglePurchase() {
		driver.navigate().to("https://www.saucedemo.com/cart.html");

		// wait.until(ExpectedConditions.visibilityOfElementLocated(CART_CONTAINER));
		WebElement itemName = wait.until(ExpectedConditions.visibilityOfElementLocated(SAUCE_LABS_BACKPACK));
		WebElement itemPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(SAUCE_LABS_BACKPACK_PRICE));
		return Arrays.asList(itemName, itemPrice);

	}

	/**
	 * Validate multiple item purchase
	 */
	public List<WebElement> validateMultiplePurchase() {
		driver.navigate().to("https://www.saucedemo.com/cart.html");
        System.out.println("Na111");
		WebElement itemName1 = wait.until(ExpectedConditions.visibilityOfElementLocated(SAUCE_LABS_BACKPACK));
	    System.out.println("Na112");
		WebElement itemPrice1 = wait.until(ExpectedConditions.visibilityOfElementLocated(SAUCE_LABS_BACKPACK_PRICE));
	    System.out.println("Na1133");
		WebElement itemName2 = wait.until(ExpectedConditions.visibilityOfElementLocated(SAUCE_LABS_BOLT_TSHIRT));
	    System.out.println("Na114");
		WebElement itemPrice2 = wait.until(ExpectedConditions.visibilityOfElementLocated(SAUCE_LABS_BOLT_TSHIRT_PRICE));
	    System.out.println("Na115");

		return Arrays.asList(itemName1, itemPrice1, itemName2, itemPrice2);
	}

	/**
	 * Validate three item purchase (optional)
	 */
	public List<WebElement> validateThreeItemPurchase() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(CART_CONTAINER));

		WebElement Item1 = wait.until(ExpectedConditions.visibilityOfElementLocated(SAUCE_LABS_BACKPACK));
		WebElement Itemprice1 = wait.until(ExpectedConditions.visibilityOfElementLocated(SAUCE_LABS_BACKPACK_PRICE));
		WebElement Item2 = wait.until(ExpectedConditions.visibilityOfElementLocated(SAUCE_LABS_BOLT_TSHIRT));
		WebElement ItemPrice2 = wait.until(ExpectedConditions.visibilityOfElementLocated(SAUCE_LABS_BOLT_TSHIRT_PRICE));

		WebElement Item3 = wait.until(ExpectedConditions.visibilityOfElementLocated(SAUCE_LABS_ONESIE));
		WebElement Itemprice3 = wait.until(ExpectedConditions.visibilityOfElementLocated(SAUCE_LABS_ONESIE_PRICE));

		return Arrays.asList(Item1, Itemprice1, Item2, ItemPrice2, Item3, Itemprice3);
	}

	/* ===================== NAVIGATION ===================== */

	public void clickContinueShopping() {
		wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
	}

	public void clickCheckout() {
		wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
	}
}