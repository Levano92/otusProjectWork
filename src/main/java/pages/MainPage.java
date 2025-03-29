package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage extends AbsBasePage {

    public MainPage(WebDriver driver)  {
        super(driver, "");
    }

    private By btnTesting = By.xpath("(//*[contains(@class, 'sc-136q38f-1')])[9]");
    public void btnTestingClick () {
        WebElement registrationBtn  = driver.findElement(btnTesting);
        registrationBtn.click();

    }
    private By btnAllEvents = By.xpath("//*[contains(@class, 'sc-1vdp0yy-0') and contains(text(), 'Смотреть все')]");
    public void btnAllEventsClik (){
        WebElement allEventsBtn = driver.findElement(btnAllEvents);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", allEventsBtn);
        allEventsBtn.click();
    }
}
