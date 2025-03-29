package factory;

import exeptions.BrowserNorSupportedExeption;
import factory.settings.ChromeSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    private String browser = System.getProperty("browser");

    public WebDriver create(){
        switch (browser) {
            case "chrome":{

                return new ChromeDriver((ChromeOptions) new ChromeSettings().settings());
            }
            case "FireFox":{
                return  new FirefoxDriver();

            }
        }
        throw new BrowserNorSupportedExeption(browser);
    }

    public void  webdravermanagerSetup(){
        switch (browser) {
            case "chrome":{

                WebDriverManager.chromedriver().setup();
                break;
            }
            case "FireFox":{
                WebDriverManager.firefoxdriver().setup();
                break;
            }
            default: {
                throw new BrowserNorSupportedExeption(browser);
            }
        }

    }
}
