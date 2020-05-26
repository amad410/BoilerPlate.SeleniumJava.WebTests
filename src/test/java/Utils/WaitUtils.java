package Utils;

import Handlers.ExceptionHandler;
import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


public class WaitUtils {

    private static BrowserFactory _browserFactory;

    public static Boolean WaitUntilLoaded(WebDriver driver,long duration) throws ExceptionHandler {
        Boolean readyState = false;
        try{

            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                    .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

            readyState = wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver) {
                    JavascriptExecutor js = (JavascriptExecutor)driver;
                    return (js).executeScript("return document.readyState").equals("complete");
                }
            });
            return readyState;
        }
        catch (Exception ex){
            //System.out.println("Page not loaded within time " + String.valueOf(duration));
            throw new ExceptionHandler("Page not loaded within time " + String.valueOf(duration),ex);
            //return false;
        }

    }
    public static Boolean IsElementPresent(WebDriver driver,By locator, long duration) throws ExceptionHandler {
        Boolean found = false;
        try{

            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                    .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

            found = wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return driver.findElements(locator).size() >= 1;
                }
            });
            return found;
        }
        catch (Exception ex){
            //System.out.println("Locating element with locator "  + String.valueOf(locator) + " cannot be found within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Locating element with locator "  + String.valueOf(locator) + " cannot be found within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public static Boolean IsElementVisible(WebDriver driver,By locator, long duration) throws ExceptionHandler {
        Boolean visible = false;
        try{
            if(IsElementPresent(driver, locator, duration) == true)
            {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                visible = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return driver.findElement(locator).isDisplayed();
                    }
                });

            }


            return visible;
        }
        catch (Exception ex){
            //System.out.println("Element with locator "  + locator.toString() + " is not visible within duration " + String.valueOf(duration));
            throw new ExceptionHandler("Element with locator "  + locator.toString() + " is not visible within duration " + String.valueOf(duration),ex);
            //return false;
        }
    }
    public static Boolean IsElementEnabled(WebDriver driver,By locator, long duration) throws ExceptionHandler {
        Boolean enabled = false;
        try{
            if(IsElementVisible(driver, locator, duration) == true)
            {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                enabled = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return driver.findElement(locator).isEnabled();
                    }
                });

            }


            return enabled;
        }
        catch (Exception ex){
            //System.out.println("Element with locator "  + locator.toString() + " is not enabled within duration " + String.valueOf(duration));
            throw new ExceptionHandler("Element with locator "  + locator.toString() + " is not enabled within duration " + String.valueOf(duration),ex);
            //return false;
        }
    }
    public static WebElement WaitForElementPresent(WebDriver driver,By locator, long duration) throws ExceptionHandler {
        WebElement elem = null;
        try{

            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                    .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

            elem = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(locator);
                }
            });
            return elem;
        }
        catch (Exception ex){
            //System.out.println("Locating element with locator "  + locator.toString() + " cannot be found within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Locating element with locator "  + locator.toString() + " cannot be found within duration" + String.valueOf(duration),ex);
            //return null;
        }
    }
    public static Boolean WaitForElementClickable(WebDriver driver,By locator, long duration) throws ExceptionHandler {
        Boolean clickable = false;
        try{
            if(IsElementVisible(driver, locator, duration) == true)
            {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                clickable = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return driver.findElement(locator).isEnabled();
                    }
                });
            }

            return clickable;
        }
        catch (Exception ex){
            //System.out.println("Element with locator "  + locator.toString() + " not clickable within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Element with locator "  + locator.toString() + " not clickable within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public static Boolean WaitForElementToHaveText(WebDriver driver,By locator, String text, long duration) throws ExceptionHandler {
        Boolean textFound = false;
        try{
            if(IsElementPresent(driver, locator, duration) == true)
            {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                textFound = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return driver.findElement(locator).getText().contains(text) || driver.findElement(locator).getAttribute("innerText").contains(text);
                    }
                });
            }

            return textFound;
        }
        catch (Exception ex){
            //System.out.println("Element with locator "  + locator.toString() + " does not contain text " + text + " within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Element with locator "  + locator.toString() + " does not contain text " + text + " within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public static Boolean WaitForElementToNotHaveText(WebDriver driver,By locator, String text, long duration) throws ExceptionHandler {
        Boolean textNotFound = false;
        try{
            if(IsElementPresent(driver, locator, duration) == true)
            {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                textNotFound = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return !driver.findElement(locator).getText().contains(text) || !driver.findElement(locator).getAttribute("innerText").contains(text);
                    }
                });
            }

            return textNotFound;
        }
        catch (Exception ex){
            //System.out.println("Element with locator "  + locator.toString() + " contains text " + text + " within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Element with locator "  + locator.toString() + " contains text " + text + " within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public static Boolean WaitForElementTextToBe(WebDriver driver,By locator, String text, long duration) throws ExceptionHandler {
        Boolean textFound = false;
        try {
            if (IsElementPresent(driver, locator, duration) == true) {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration, TimeUnit.SECONDS)
                        .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                textFound = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return driver.findElement(locator).getText().equals(text);
                    }
                });
            }

            return textFound;
        }
        catch (Exception ex) {
            //System.out.println("Element with locator " + locator.toString() + " does not contain text " + text + " within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Element with locator " + locator.toString() + " does not contain text " + text + " within duration" + String.valueOf(duration),ex);
           // return false;
        }

    }
    public static Boolean WaitForElementTextNotToBe(WebDriver driver,By locator, String text, long duration) throws ExceptionHandler {
        Boolean textNotFound = false;
        try {
            if (IsElementPresent(driver, locator, duration) == true) {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration, TimeUnit.SECONDS)
                        .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                textNotFound = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return !driver.findElement(locator).getText().equals(text);
                    }
                });
            }

            return textNotFound;
        }
        catch (Exception ex) {
            //System.out.println("Element with locator " + locator.toString() + " contains text " + text + " within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Element with locator " + locator.toString() + " contains text " + text + " within duration" + String.valueOf(duration),ex);
            //return false;
        }

    }
    public static Boolean WaitForElementClassToNotContain(WebDriver driver,By locator, String classText, long duration) throws ExceptionHandler {
        Boolean classNotContain = false;
        try {
            if (IsElementPresent(driver, locator, duration) == true) {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration, TimeUnit.SECONDS)
                        .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                classNotContain = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return !driver.findElement(locator).getAttribute("class").contains(classText);
                    }
                });
            }

            return classNotContain;
        }
        catch (Exception ex) {
            //System.out.println("Element with locator " + locator.toString() + " class contains text " + classText + " within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Element with locator " + locator.toString() + " class contains text " + classText + " within duration" + String.valueOf(duration),ex);
            //return false;
        }

    }
    public static Boolean WaitForElementAttributeContains(WebDriver driver,By locator, String attribute, String value, long duration) throws ExceptionHandler {
        Boolean attributeContains = false;
        try {
            if (IsElementPresent(driver, locator, duration) == true) {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration, TimeUnit.SECONDS)
                        .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                attributeContains = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return driver.findElement(locator).getAttribute(attribute).contains(value);
                    }
                });
            }

            return attributeContains;
        }
        catch (Exception ex) {
            //System.out.println("Element with locator " + locator.toString() + " attribute  " + attribute + " does not contain value " + value + " within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Element with locator " + locator.toString() + " attribute  " + attribute + " does not contain value " + value + " within duration" + String.valueOf(duration),ex);
            //return false;
        }

    }
    public static Boolean WaitForElementAttributeNotContains(WebDriver driver,By locator, String attribute, String value, long duration) throws ExceptionHandler {
        Boolean attributeNotContains = false;
        try {
            if (IsElementPresent(driver, locator, duration) == true) {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration, TimeUnit.SECONDS)
                        .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                attributeNotContains = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return !driver.findElement(locator).getAttribute(attribute).contains(value);
                    }
                });
            }

            return attributeNotContains;
        }
        catch (Exception ex) {
            //System.out.println("Element with locator " + locator.toString() + " attribute  " + attribute + " contains value " + value + " within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Element with locator " + locator.toString() + " attribute  " + attribute + " contains value " + value + " within duration" + String.valueOf(duration),ex);
            //return false;
        }

    }
    public static List<WebElement> WaitForElementsPresent(WebDriver driver, By locator, long duration) throws ExceptionHandler {
        List<WebElement> elems = null;
        try{
            if(IsElementPresent(driver, locator, duration) == true)
            {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                elems = wait.until(new Function<WebDriver, List<WebElement>>() {
                    public List<WebElement> apply(WebDriver driver) {
                        return driver.findElements(locator);
                    }
                });
            }
            return elems;
        }
        catch (Exception ex){
            //System.out.println("Locating elements with locator "  + locator.toString() + " cannot be found within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Locating elements with locator "  + locator.toString() + " cannot be found within duration" + String.valueOf(duration),ex);
            //return null;
        }
    }
    public Boolean WaitForElementNotPresent(WebDriver driver,By locator, long duration) throws ExceptionHandler {
        Boolean notFound = false;
        try{

           if(IsElementPresent(driver, locator, duration) == false)
           {
               notFound = true;
           }
            return notFound;
        }
        catch (Exception ex){
            //System.out.println("Locating element with locator "  + locator.toString() + " cannot be found within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Locating element with locator "  + locator.toString() + " cannot be found within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public Boolean WaitForElementToBeSelected(WebDriver driver,By locator, long duration) throws ExceptionHandler {
        Boolean selected = false;
        try{

            if(IsElementPresent(driver, locator, duration) == false)
            {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                selected = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return driver.findElement(locator).isSelected();
                    }
                });
            }
            return selected;
        }
        catch (Exception ex){
            //System.out.println("Locating element with locator "  + locator.toString() + " is not selected within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Locating element with locator "  + locator.toString() + " is not selected within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public Boolean WaitForElementToNotBeSelected(WebDriver driver,By locator, long duration) throws ExceptionHandler {
        Boolean notSelected = false;
        try{

            if(IsElementPresent(driver, locator, duration) == false)
            {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                notSelected = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return !driver.findElement(locator).isSelected();
                    }
                });
            }
            return notSelected;
        }
        catch (Exception ex){
            //System.out.println("Locating element with locator "  + locator.toString() + " is selected within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Locating element with locator "  + locator.toString() + " is selected within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public Boolean WaitForElementAmountsToBe(WebDriver driver,By locator, int amount, long duration) throws ExceptionHandler {
        Boolean amountsMatch = false;
        try{

            if(IsElementPresent(driver, locator, duration) == false)
            {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                amountsMatch = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return driver.findElements(locator).size() == amount;
                    }
                });
            }
            return amountsMatch;
        }
        catch (Exception ex){
            //System.out.println("Locating element with locator "  + locator.toString() + " amount does not match " + String.valueOf(amount) + " within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Locating element with locator "  + locator.toString() + " amount does not match " + String.valueOf(amount) + " within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public Boolean WaitForElementAmountsToNotBe(WebDriver driver,By locator, int amount, long duration) throws ExceptionHandler {
        Boolean amountsNotMatch = false;
        try{

            if(IsElementPresent(driver, locator, duration) == false)
            {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                amountsNotMatch = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return driver.findElements(locator).size() < amount;
                    }
                });
            }
            return amountsNotMatch;
        }
        catch (Exception ex){
            //System.out.println("Locating element with locator "  + locator.toString() + " amount does match " + String.valueOf(amount) + " within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Locating element with locator "  + locator.toString() + " amount does match " + String.valueOf(amount) + " within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public Boolean ClickAndWaitForCondition(WebDriver driver,By locator, By conditionLocator, long duration) throws ExceptionHandler {
        Boolean conditionMet = false;
        try{

            if(WaitForElementClickable(driver, locator, duration) == false)
            {
                for(int i = 0; i <=2; i++){
                    driver.findElement(locator).click();

                    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                            .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                    conditionMet = wait.until(new Function<WebDriver, Boolean>() {
                        public Boolean apply(WebDriver driver) {
                            return driver.findElements(conditionLocator).size() >0 || driver.findElement(conditionLocator).isDisplayed();
                        }
                    });
                    if(conditionMet == true)
                        break;
                }

            }
            return conditionMet;
        }
        catch (Exception ex){
            //System.out.println("Locating element after click with locator "  + conditionLocator.toString() + " not found within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Locating element after click with locator "  + conditionLocator.toString() + " not found within duration" + String.valueOf(duration),ex);
            //return false;
        }

    }
    public Boolean WaitForElementAmountsToBeGreaterThan(WebDriver driver,By locator, int amount, long duration) throws ExceptionHandler {
        Boolean greaterThan = false;
        try{

            if(IsElementPresent(driver, locator, duration) == false)
            {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                greaterThan = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return driver.findElements(locator).size() > amount;
                    }
                });
            }
            return greaterThan;
        }
        catch (Exception ex){
            //System.out.println("Locating element with locator "  + locator.toString() + " amount not greater than " + String.valueOf(amount) + " within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Locating element with locator "  + locator.toString() + " amount not greater than " + String.valueOf(amount) + " within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public Boolean WaitForElementOptionText(WebDriver driver,By locator, String text, long duration) throws ExceptionHandler {
        Boolean textFound = false;
        try{

            if(IsElementPresent(driver, locator, duration) == false)
            {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                textFound = wait.until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        Boolean found = false;
                        List<WebElement> optionsList = driver.findElement(locator).findElements(By.cssSelector("option"));
                        for(WebElement element: optionsList){
                            if(element.getText().equals(text)){
                                found = true;
                                break;
                            }
                        }
                        return found;
                    }
                });
            }
            return textFound;
        }
        catch (Exception ex){
            //System.out.println("Options of element with locator "  + locator.toString() + " does not contain " + text + " within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Options of element with locator "  + locator.toString() + " does not contain " + text + " within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public static WebElement WaitForElementVisible(WebDriver driver,By locator, long duration) throws ExceptionHandler {
        WebElement elem = null;
        try{

            if(IsElementVisible(driver, locator, duration) == true)
            {

                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                elem = wait.until(new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(locator);
                    }
                });
            }

            return elem;
        }
        catch (Exception ex){
            //System.out.println("Element with locator "  + locator.toString() + " not visible within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Element with locator "  + locator.toString() + " not visible within duration" + String.valueOf(duration),ex);
            //return null;
        }
    }
    public static List<WebElement> WaitForElementsVisible(WebDriver driver, By locator, long duration) throws ExceptionHandler {
        List<WebElement> elems = null;
        try{
            if(IsElementVisible(driver, locator, duration) == true)
            {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration,TimeUnit.SECONDS)
                        .pollingEvery(500,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

                elems = wait.until(new Function<WebDriver, List<WebElement>>() {
                    public List<WebElement> apply(WebDriver driver) {
                        return driver.findElements(locator);
                    }
                });
            }


            return elems;
        }
        catch (Exception ex){
            //System.out.println("Locating elements with locator "  + locator.toString() + " cannot be found within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Locating elements with locator "  + locator.toString() + " cannot be found within duration" + String.valueOf(duration),ex);
            //return null;
        }
    }
    public Boolean WaitForElementNotVisible(WebDriver driver,By locator, long duration) throws ExceptionHandler {
        Boolean notVisible = false;
        try{

            if(IsElementPresent(driver, locator, duration) == false)
            {
                if(IsElementVisible(driver, locator, duration) == false)
                {
                    notVisible = true;
                }

            }
            return notVisible;
        }
        catch (Exception ex){
            //System.out.println("Locating element with locator "  + locator.toString() + " cannot be found visible within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Locating element with locator "  + locator.toString() + " cannot be found visible within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public static Boolean IsFrameElementDisplayed(WebDriver driver,By targetedFrame, long duration) throws ExceptionHandler {
        Boolean found = false;
        try{

            if(IsElementPresent(driver, targetedFrame, duration) == true)
            {
               found = true;
            }

            return found;
        }
        catch (Exception ex){
            //System.out.println("Frame Element with locator "  + targetedFrame.toString() + " cannot be found within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Frame Element with locator "  + targetedFrame.toString() + " cannot be found within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public static void WaitToSwitchToIFrame(WebDriver driver,By targetedFrame, long duration) throws ExceptionHandler {
        try{
            if(IsElementPresent(driver, targetedFrame, duration) == true)
            {
                TryToSwitchToFrame(driver,targetedFrame, duration);
            }
        }
        catch (Exception ex){
            //System.out.println("Frame Element with locator "  + targetedFrame.toString() + " cannot be switched to within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Frame Element with locator "  + targetedFrame.toString() + " cannot be switched to within duration" + String.valueOf(duration),ex);
        }
    }
    public static Boolean TryToSwitchToFrame(WebDriver driver,By targetedFrame, long duration) throws ExceptionHandler {
        WebElement elem = null;
        try{

            if(TryFindElement(driver, targetedFrame, elem) == true)
            {
                return true;
            }
            for(WebElement frame: driver.findElements(By.tagName("iframe")))
            {
                driver.switchTo().frame(frame);
                if (TryToSwitchToFrame(driver, targetedFrame, duration))
                    return true;
            }
            driver.switchTo().defaultContent();

            return false;
        }
        catch (Exception ex){
            //System.out.println("Frame Element with locator "  + targetedFrame.toString() + " cannot be found within duration" + String.valueOf(duration));
            throw new ExceptionHandler("Frame Element with locator "  + targetedFrame.toString() + " cannot be found within duration" + String.valueOf(duration),ex);
            //return false;
        }
    }
    public static Boolean TryFindElement(WebDriver driver,By targetedFrame, WebElement elem) throws ExceptionHandler {
        try{
            elem = driver.findElement(targetedFrame);
        }
        catch(Exception ex)
        {
            //System.out.println("Frame Element with locator "  + targetedFrame.toString() + " cannot be found");
            throw new ExceptionHandler("Frame Element with locator "  + targetedFrame.toString() + " cannot be found",ex);
            //return false;
        }
        return true;
    }

}
