package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class QACoursesPage extends AbsBasePage {
    private By coursesBlok = By.cssSelector(".sc-18q05a6-1");
    private By courseCard = By.xpath("(//*[contains(@class, 'sc-zzdkm7-0') and contains(@class, 'dzoQdH')])[1]");

    public QACoursesPage(WebDriver driver) {
        super(driver, "/catalog/courses?categories=testing");
    }



    public int numberOfElements(){
        WebElement block = driver.findElement(coursesBlok);
        List<WebElement> elements = block.findElements(By.xpath("./*"));
        return elements.size();
    }

    public void courseCardClick(){
        WebElement card = driver.findElement(courseCard);

        card.click();

    }


}
