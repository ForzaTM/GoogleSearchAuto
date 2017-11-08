package Tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseDriver
{
    public WebDriver driver;
    public WebDriverWait wait;

    String driverType;

    public WebDriver choseDriver()
    {
        if (driverType.equals("ChromeDriver"))
        {
            ChromeDriverManager.getInstance().setup();
            return driver = new ChromeDriver();
        }
        else if (driverType.equals("FirefoxDriver"))
        {
            FirefoxDriverManager.getInstance().setup();
            return driver = new FirefoxDriver();
        }
        else if (driverType.equals("InternetExplorerDriver"))
        {
            InternetExplorerDriverManager.getInstance().setup();
            return driver = new InternetExplorerDriver();
        }
        else
        {
            return null;
        }
    }
}


