package main;

import factory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CardOfCorse;
import pages.MainPage;
import pages.NearEvents;
import pages.QACoursesPage;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainPage_Test {
    private static final Logger logger = LogManager.getLogger(MainPage_Test.class);
    private static WebDriverFactory webDriverFactory = new WebDriverFactory();

    private WebDriver driver = null;
    @BeforeAll
    public static void init(){

        webDriverFactory.webdravermanagerSetup();
    }
    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver =  webDriverFactory.create();
    }

    @Test
    public void numberTestingsCourses(){
        logger.info("Запуск теста: Проверка количества карточек курсов по тестировнию");
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        logger.info("Нажатие по кнопке 'Тестирование'");
        mainPage.btnTestingClick();
        logger.info("Нажатие по кнопке успешно");
        QACoursesPage qaCoursesPageoursesPage = new QACoursesPage(driver);
        logger.info("Подсчет Карточек");
        assertEquals(10, qaCoursesPageoursesPage.numberOfElements(), "Количество элементов не равно 10");

    }
    @Test
    public  void  goToInformationCard (){
        QACoursesPage qaCoursesPage = new QACoursesPage(driver);
        logger.info("Запуск теста: Переход по карточке курса");
        qaCoursesPage.open();
        qaCoursesPage.courseCardClick();
        assertEquals(
                "https://otus.ru/lessons/qa-auto-java-specialization/",
                driver.getCurrentUrl(),
                "Ссылка не соответствует ожидаемой"
        );

    }

    @Test
    public void informationOnCard(){
        CardOfCorse cardOfCorse = new CardOfCorse(driver);
        logger.info("Запуск теста: проверка информации на карточке курса");
        cardOfCorse.open();
        cardOfCorse.getInformationPartText(1, "26 марта");
        logger.info("Проверка даты выполнена");
        cardOfCorse.getInformationPartText(2, "Basic");
        logger.info("Проверка уровня выполнена");
        cardOfCorse.getInformationPartText(3, "10 месяцев");
        logger.info("Проверка длительности выполнена");
        cardOfCorse.getInformationPartText(4, "Онлайн");
        logger.info("Проверка способа обучения выполнена");

    }

    @Test
    public void  eventCalendar() throws ParseException {
        MainPage mainPage = new MainPage(driver);
        logger.info("Запуск теста: проверка даты на карточках событий");
        mainPage.open();
        logger.info("Нажатие на кнопку 'Смотреть все'");
        mainPage.btnAllEventsClik();
        NearEvents nearEvents = new NearEvents(driver);
        logger.info("Проверка дат на карточках");
        nearEvents.eventDаteCheck();
        logger.info("Проверка дат выполнена");

    }

    @Test
    public void sortEventCards(){
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        logger.info("Запуск теста: Сортировка карточке событий по типу Открытый вебинар");
        mainPage.btnAllEventsClik();
        NearEvents nearEvents = new NearEvents(driver);
        logger.info("Выбор типа Открытый вебинар");
        nearEvents.choseEventType();
        logger.info("Провека типа на всех карточках");
        nearEvents.eventTypeCheck();

    }

    @AfterEach
    public void tearDown () {
        if (driver != null) {
            driver.quit();
        }
    }

}
