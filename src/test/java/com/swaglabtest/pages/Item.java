package com.swaglabtest.pages;

import org.openqa.selenium.By;

public class Item {
    private By name;
    private By price;
    private By button;

    public Item(By name, By price, By button) {
        this.name = name;
        this.price = price;
        this.button = button;
    }

    public By getName() { return name; }
    public By getPrice() { return price; }
    public By getButton() { return button; }
}
