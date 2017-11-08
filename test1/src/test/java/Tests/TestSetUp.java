package Tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class TestSetUp extends BaseDriver {

    String urlToOpen;
    String textForSearch;
    String domainForSearch;
    String amountOfPages;

    public Logger log = Logger.getLogger("googleSearchTest");
    public Logger logg = Logger.getLogger("TestSetUp");


    public void startTest() throws IOException
    {
        baseSettings("driverInUse", "url");

        logg.info("********* New Test Started *********");
        logg.info("Getting properties for test cases");
        logg.info("Open WebPage in browser with driver: " + driverType);
        driver = choseDriver();
        openUrl();
    }

    public void closeTest()
    {
        logg.info("********* Test Finished *********");
        this.driver.quit();
    }

    public void useWait()
    {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void baseSettings(String drivers, String urls)
    {
        PropertyConfigurator.configure("log4j.properties");

        Properties prop = new Properties();

        InputStream  input;

        try
        {
            input = new FileInputStream("config.properties");
            prop.load(input);
        }
        catch (IOException e)
        {
            logg.info(e);
        }

        driverType = prop.getProperty(drivers);
        urlToOpen = prop.getProperty(urls);
    }

    public void openUrl()
    {
        logg.info("Go To Url: " + urlToOpen);
        driver.get(urlToOpen);
    }

    public void Click(WebElement element)
    {
        element.click();
        logg.info(element.toString() + " - Click");
        useWait();
    }

    public void AssertTrue(boolean condition)
    {
        Assert.assertTrue(condition);
        logg.info("AssertTrue condition is " + condition);
    }

    public void waitForElement(By item) {
        wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(item));
    }

}