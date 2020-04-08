package Utilities;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {

    private static WebDriver driver=null;

    public static WebDriver getDriver(){

        if(driver== null){
            ChromeDriverManager.chromedriver().setup();
            driver = new ChromeDriver(  );

        }

        return driver;
    }

    public static void closeDriver(){
        if (driver!=null){
            driver.quit();
            driver=null;
        }
    }
}