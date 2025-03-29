package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardOfCorse  extends  AbsBasePage{
    public CardOfCorse(WebDriver driver) {
        super(driver, "lessons/qa-auto-java-specialization/");
    }
    private String PartInformationlocator= "(//*[contains(@class, 'sc-3cb1l3-0')])[%s]";

    public WebElement getInformationPart(int part) {
        String informationPartlocatorWithNumber = String.format(PartInformationlocator, part);
        return driver.findElement(By.xpath(informationPartlocatorWithNumber));
    }

    public void getInformationPartText(int part, String text){
      String information =  getInformationPart(part).getText();
            assertEquals(text, information, "Неверные данные");

    }

}
