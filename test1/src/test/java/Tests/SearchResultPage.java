package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;


public class SearchResultPage extends TestSetUp
{
    WebElement searchBar;
    WebElement googleSearchResults;
    WebElement firstOfGoogleSearchResults;
    WebElement switchPage;
    WebElement navigationSection;
    WebElement linksInSField;

    String NAVIGATIONSECTIONPATH_PATH = "tbody";
    String SEARCHFIELD_PATH = ".//input[contains(@role,'combobox')]";
    String SEARCHRESULT_PATH = "search";
    String FIRSTOFSEARCHRESULTS_PATH = ".//a[contains(@href, '')][1]";
    String LINKSINSEARCHFIELD_PATH = "div.f.kv._SWb";
    String LINKSINSEARCHSECTION_PATH = "cite";


    By searchField = By.xpath(SEARCHFIELD_PATH);
    By googleSearchRes = By.id(SEARCHRESULT_PATH);
    By firstOfGoogleSearchRes = By.xpath(FIRSTOFSEARCHRESULTS_PATH);
    By navigationSect = By.tagName(NAVIGATIONSECTIONPATH_PATH);
    By linksInSF = By.cssSelector(LINKSINSEARCHFIELD_PATH);
    By linksInSS = By.tagName(LINKSINSEARCHSECTION_PATH);


    public SearchResultPage (WebDriver driver)
    {
        this.driver = driver;

        searchBar = driver.findElement(searchField);
        googleSearchResults = driver.findElement(googleSearchRes);
        firstOfGoogleSearchResults = googleSearchResults.findElement(firstOfGoogleSearchRes);
        navigationSection = driver.findElement(navigationSect);
        linksInSField = driver.findElement(linksInSF);
    }

    public void switcPageClick(Integer pagenum)
    {
        switchPage = navigationSection.findElement(By.xpath(".//a[contains(@aria-label, 'Page " + pagenum + "')]"));
        switchPage.click();
        waitForElement(googleSearchRes);
    }

    public ArrayList<WebElement> linksInSearchList ()
    {
        return new ArrayList<WebElement> (googleSearchResults.findElements(linksInSF));
    }
}