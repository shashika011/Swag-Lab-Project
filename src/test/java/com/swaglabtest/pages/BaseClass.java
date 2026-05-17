package com.swaglabtest.pages;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.swaglabtest.utility.BrowserFactory;
import com.swaglabtest.utility.ConfigProvider;
import com.swaglabtest.utility.ExcelDataProvider;
import com.swaglabtest.utility.Helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

    public static WebDriver driver;
    public static ExcelDataProvider excelp;
    public static ConfigProvider config;
    public static ExtentReports report;
    public static ExtentTest logger;
    public static ExtentSparkReporter spark;

    // ✅ Logger for debugging
    private static final Logger log = LogManager.getLogger(BaseClass.class);

    @BeforeSuite
    public void setupSuite() {
        log.info("Starting Test Suite setup...");
        
        File file = new File("Reports/AutomationReport.html");
        if (file.exists()) {
            file.delete();
        }

        if (excelp == null) {
            excelp = new ExcelDataProvider();
            log.info("ExcelDataProvider initialized");
        }

        if (config == null) {
            config = new ConfigProvider();
            log.info("ConfigProvider initialized");
        }

        String reportPath = System.getProperty("user.dir") + "/Reports/" + Helper.getCurrentDateTime()
                + "_AutomationReport.html";
        spark = new ExtentSparkReporter(reportPath);
        
        reportPath = System.getProperty("user.dir") + "/Reports/" +"AutomationReport.html";
        spark = new ExtentSparkReporter(reportPath);

        try {
            // Load external Extent configuration XML
            spark.loadXMLConfig(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml");
            log.info("Extent Reports XML configuration loaded");
        } catch (IOException e) {
            log.error("Error loading Extent Report XML configuration", e);
        }

        report = new ExtentReports();
        report.attachReporter(spark);

        log.info("Extent Report initialized at: " + reportPath);
    }

    @BeforeClass
    @Parameters("browser")
    public void setupClass(@Optional("") String browserFromXML) {

        log.info("Initializing browser...");

        if (config == null) {
            config = new ConfigProvider();
        }

        // Priority: XML > Config
        String browser = (browserFromXML != null && !browserFromXML.isEmpty())
                ? browserFromXML
                : config.getBrowser();

        log.info("Browser selected: " + browser);

        driver = BrowserFactory.startApplication(driver, browser, config.getURL());

        if (driver == null) {
            log.fatal("Driver initialization failed!");
            throw new RuntimeException("Driver initialization failed!");
        }

        log.info("Browser initialized successfully: " + browser);
    }
    
    @BeforeMethod
    public void startTest(Method method) {
        // Create a test in Extent Report for every test method
        logger = report.createTest(method.getName());
        logger.info("Starting Test: " + method.getName());
        log.info("Test started: " + method.getName());
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                String screenshotPath = Helper.CaptureScreenShot(driver, testName);
                logger.fail("Test Failed: " + result.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                log.error("Test Failed: " + testName, result.getThrowable());

            } else if (result.getStatus() == ITestResult.SUCCESS) {
                logger.pass("Test Passed");
                log.info("Test Passed: " + testName);

            } else {
                logger.skip("Test Skipped");
                log.warn("Test Skipped: " + testName);
            }
        } catch (Exception e) {
            log.error("Error in AfterMethod handling", e);
        }
    }

    @AfterClass
    public void teardownClass() {
        BrowserFactory.closeconnection(driver);
        log.info("Browser closed for class");
        //BrowserFactory.closeconnection(driver);
    }

    @AfterSuite(alwaysRun = true)
    public void teardownSuite() {
        report.flush();
        log.info("Extent Report flushed");
    }
}