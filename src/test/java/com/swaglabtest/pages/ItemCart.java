package com.swaglabtest.pages;
import org.openqa.selenium.By;

public class ItemCart {
	 private By name;
	    private By price;

	    public ItemCart(By name, By price) {
	        this.name = name;
	        this.price = price;
	       
	    }

	    public By getName() { return name; }
	    public By getPrice() { return price; }
}
