package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NearEvents extends AbsBasePage{


    public NearEvents(WebDriver driver) {
        super(driver, "/events/near/");
    }

    private By eventsList = By.cssSelector(".js-dod_new_events");
    public int numberOfEvents(){
        WebElement block = driver.findElement(eventsList);
        List<WebElement> elements = block.findElements(By.xpath("./*"));
        return elements.size();
    }


    private String dateText =  "(//*[contains(@class, 'dod_new-event__date-text')])[%d]";

    public WebElement elementNumber(int part ,String elementLocator) {
        String informationPartlocatorWithNumber = String.format(elementLocator, part);
        return driver.findElement(By.xpath(informationPartlocatorWithNumber));
    }

    public LocalDateTime dateOfEvent(String dayMounth, String time) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String dateString = String.format("%s %d %s", dayMounth, currentYear, time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm", new Locale("ru"));
        return LocalDateTime.parse(dateString, formatter);
    }

    public void eventDаteCheck() {
        LocalDateTime currentDate = LocalDateTime.now();  // NEW
        for (int i = 1; i < numberOfEvents()*2; i += 2) {
            String date = elementNumber(i, dateText).getText();
            String time = elementNumber(i+1, dateText).getText();
            assert dateOfEvent(date, time).isAfter(currentDate) :  // NEW: isAfter вместо after
                    String.format("Событие %d-%d (%s %s) уже прошло. Текущая дата: %s",
                            i, i+1, date, time, currentDate);
        }
    }
    private By typeSelectorList = By.xpath("(//*[text()='Все мероприятия'])[1]");
    private By typeSelector = By.xpath("(//a[@title='Все мероприятия'])[1]");
    public void choseEventType(){
        WebElement typeSelectorListElement = driver.findElement(typeSelectorList);
        typeSelectorListElement.click();
        WebElement typeSelectorEelement = driver.findElement(typeSelector);
        typeSelectorEelement.click();
    }

    private  String eventType = "(//*[contains(@class, 'dod_new-type__text')])[%d]";

    public void eventTypeCheck() {
        for (int i = 1; i < numberOfEvents(); i++) {
            String eventTypeText = elementNumber( i, eventType).getText();
            assertEquals("Открытый вебинар",eventTypeText, "Неверные данные");

        }
    }
}
