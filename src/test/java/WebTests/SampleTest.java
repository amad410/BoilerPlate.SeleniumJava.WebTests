package WebTests;

import Handlers.ExceptionHandler;
import Utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static com.google.common.truth.Truth.assertThat;

public class SampleTest extends BaseTest {

    @Test
    public void SampleTestCase() throws ExceptionHandler {
        System.out.println("Starting sample test");
        //Thread.sleep(10000);
        WaitUtils.WaitUntilLoaded(_driver,10);
        assertThat(_driver.findElement(By.name("q")).isDisplayed()).isTrue();
    }
}
