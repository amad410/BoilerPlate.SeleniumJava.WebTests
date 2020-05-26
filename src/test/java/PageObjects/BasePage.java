package PageObjects;

import Utils.BrowserFactory;
import org.openqa.selenium.*;

public class BasePage {

    protected WebDriver _Driver;

    public BasePage(WebDriver _driver){
        setDriver(_driver);
    }

    public void setDriver(WebDriver _driver){
        _Driver = _driver;

    }
    public WebDriver getDriver(){
        return _Driver;

    }
    public String Title(){
        return _Driver.getTitle();
    }
    public void Click(By locator){
        try{
            _Driver.findElement(locator).click();
        }
        catch(ElementClickInterceptedException ex){
            JavascriptExecutor js = (JavascriptExecutor)_Driver;
            WebElement elem = _Driver.findElement(locator);
            js.executeScript("arguments[0].click();", elem);

        }
        catch(ElementNotInteractableException ex){
            JavascriptExecutor js = (JavascriptExecutor)_Driver;
            WebElement elem = _Driver.findElement(locator);
            js.executeScript("arguments[0].click();", elem);

        }
        catch(StaleElementReferenceException ex){
            _Driver.findElement(locator).click();
        }

    }
    public void SelectOption(By ElementLocator, By OptionLocator){
        try{

        }
        catch(ElementNotSelectableException ex){

        }


    }
    public void ScrollWindow(String xCord, String yCord){
        JavascriptExecutor js = (JavascriptExecutor)_Driver;
        js.executeScript(String.format("windows.scrollBy({0},{1})", xCord, yCord));
    }
    public void EnterText(By locator, String text){
        try{
            _Driver.findElement(locator).sendKeys(text);
        }
         catch(StaleElementReferenceException ex){
            _Driver.findElement(locator).sendKeys(text);
        }catch (ElementNotInteractableException ex){
            WebElement elem = _Driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor)_Driver;
            js.executeScript(String.format("arguments[0].value='{0}';",text), elem);
        }
    }
    public void NavigateBack(){
        _Driver.navigate().back();
    }
    public void NavigateForward(){
        _Driver.navigate().forward();
    }

}
