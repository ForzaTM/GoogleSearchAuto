package Tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;


public class GoogleSearchPage extends TestSetUp
{
    WebElement searchBar;

    String SEARCHFIELD_PATH = ".//input[contains(@role,'combobox')]";

    By searchField = By.xpath(SEARCHFIELD_PATH);

    public GoogleSearchPage (WebDriver driver)
    {
        this.driver = driver;

        searchBar = driver.findElement(searchField);
    }

    public void searchText(String text)
    {
        searchBar.sendKeys(text);
        searchBar.sendKeys(Keys.ENTER);
        log.info("Text: " + text + " successfully entered");
        waitForElement(searchField);
    }
}