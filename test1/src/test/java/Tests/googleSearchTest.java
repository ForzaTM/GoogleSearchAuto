package Tests;


import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;


public class googleSearchTest extends TestSetUp
{

    //Use config.properties to change some major inputs in test


    @Test
    public void searchTest1()
    {
        GoogleSearchPage sPage = new GoogleSearchPage(driver);

        Assert.assertEquals(true, sPage.searchBar.getAttribute("className").contains("gsfi"));

        sPage.searchText(textForSearch);

        SearchResultPage srPage = new SearchResultPage(driver);

        AssertTrue(srPage.firstOfGoogleSearchResults.isDisplayed());

        Click(srPage.firstOfGoogleSearchResults);

        AssertTrue(driver.getTitle().toLowerCase().contains(textForSearch.toLowerCase()));
        log.info("Text: " + textForSearch + " persist in the title of new page");
    }

    @Test
    public void searchTest2()
    {
        GoogleSearchPage sPage = new GoogleSearchPage(driver);

        sPage.searchText(textForSearch);

        for (int i = 1; i <= Integer.parseInt(amountOfPages); i++)
        {
            SearchResultPage srPage = new SearchResultPage(driver);

            ArrayList<WebElement> linksInSearch = srPage.linksInSearchList();
            boolean result = false;

            for (WebElement dom : linksInSearch)
            {
                String textInElement = dom.findElement(srPage.linksInSS).getText();

                if (textInElement.contains(domainForSearch))
                {
                    result = true;
                    break;
                }
                else
                    {
                    continue;
                    }
            }
            if (!result && i != Integer.parseInt(amountOfPages))
            {
                log.info(domainForSearch + " is not present on page: " + i);

                srPage.switcPageClick(i+1);
            }
            else if (!result && i == Integer.parseInt(amountOfPages))
            {
                log.info(domainForSearch + " is not present on page: " + i);
                log.info("No domains like: " + domainForSearch + " within pages: " + 1 + " - " + amountOfPages);
                Assert.fail("No domains like: " + domainForSearch + " within pages: " + 1 + " - " + amountOfPages);
            }
            else
                {
                    log.info(domainForSearch + " is present on page: " + i);
                    break;
                }
        }
    }

    @Before
    public void Start() throws IOException {

        startTest();
        settingsForTest("text", "domain", "pages");
    }

    @After
    public void Close()
    {
        closeTest();
    }

    public void settingsForTest(String texts, String domains, String pages)
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
            log.info(e);
        }

        textForSearch = prop.getProperty(texts);
        domainForSearch = prop.getProperty(domains);
        amountOfPages = prop.getProperty(pages);
    }
}