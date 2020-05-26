package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {
    /*
    This WebDriverFactory class will initialize the requested browser and maintain that instance
    *throughout the test run.
     */
    private static BrowserFactory instance = null;

    ThreadLocal<WebDriver> _webDriver = new ThreadLocal<WebDriver>();

    private BrowserFactory(){  }

    public static BrowserFactory getInstance(){
        if(instance == null){
            instance = new BrowserFactory();
        }
        return instance;
    }


    public final void setDriver(String browserName){
        switch(browserName){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                _webDriver.set(new ChromeDriver());
               // _driver = new ChromeDriver();
                //_webDriver.manage().window().maximize();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                _webDriver.set(new InternetExplorerDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                _webDriver.set(new FirefoxDriver());
                break;


        }
    }
    public WebDriver getDriver(){
        return _webDriver.get();
    }
}
