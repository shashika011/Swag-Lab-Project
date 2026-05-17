package com.swaglabtest.utility;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	public static WebDriver startApplication(WebDriver driver, String browserName, String appurl) {
	//	System.out.println("AA");
		if ("chrome".equalsIgnoreCase(browserName)) {

		    WebDriverManager.chromedriver()
		            .clearDriverCache()
		            .setup();


	    ChromeOptions options = new ChromeOptions();
	  
	    /*
		  options.addArguments("--headless=new"); // headless mode for Linux
		    options.addArguments("--no-sandbox");   // required in CI
		    options.addArguments("--disable-dev-shm-usage"); // avoid /dev/shm issues
		    options.addArguments("--remote-allow-origins=*");
		    options.addArguments("--disable-notifications");
		    options.addArguments("--disable-extensions");
		    options.addArguments("--disable-popup-blocking");
		    options.addArguments("--window-size=1920,1080"); // necessary in headless
*/
		  
	    Map<String, Object> prefs = new HashMap<>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    prefs.put("profile.password_manager_leak_detection", false);

	    options.setExperimentalOption("prefs", prefs);

	    // REQUIRED for GitHub Actions / Linux
	    options.addArguments("--headless=new");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-dev-shm-usage");
	    options.addArguments("--window-size=1920,1080");

	    // optional stability flags
	    options.addArguments("--disable-gpu");
	    options.addArguments("--remote-allow-origins=*");

     
		    driver = new ChromeDriver(options);
        } else if ("firefox".equalsIgnoreCase(browserName)) {

        	FirefoxOptions options = new FirefoxOptions();
        	options.addArguments("--headless");
        	options.addArguments("--disable-gpu");

        	WebDriverManager.firefoxdriver().setup();

        	driver = new FirefoxDriver(options);
        	driver.manage().window().maximize();

        } else if ("edge".equalsIgnoreCase(browserName)) {

            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        } else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.get(appurl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public static void closeconnection(WebDriver driver) {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Ignoring driver quit exception");
            }
        }
    }
}