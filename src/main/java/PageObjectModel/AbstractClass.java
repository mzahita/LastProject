package PageObjectModel;

import Utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class AbstractClass {

    private WebDriver driver = Driver.getDriver();

    WebDriverWait wait = new WebDriverWait(driver,10);

    public void clickFunctionality(WebElement clickButton){
        wait.until(ExpectedConditions.elementToBeClickable(clickButton));
        clickButton.click();
    }

    public void sendKeysFunction(WebElement sendKeysButton, String value) {
        wait.until(ExpectedConditions.visibilityOf(sendKeysButton));
        sendKeysButton.sendKeys(value);
    }

    public void selectFunctionality(String sValue ,WebElement selectValue){
        Select select = new Select(selectValue);
        wait.until(ExpectedConditions.elementToBeSelected(selectValue));
        select.selectByValue(sValue);
    }

    public void sleep(int num){
        try {
            Thread.sleep(num * 1000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public int randomNumber(int max){
        Random rnd = new Random();

        int myRandom = rnd.nextInt(max);

        return myRandom;

    }

    public void clickRandomNum(List<WebElement> listOfElements){

        int rnd = randomNumber(listOfElements.size());

        clickFunctionality(listOfElements.get(rnd));

    }

    public void switchWindow(){

        Set<String> windows = driver.getWindowHandles();

        for (String myWin : windows){
            driver.switchTo().window(myWin);
        }

    }

    public void selectFromDropdown(WebElement dropdown){
        Select select = new Select(dropdown);
        List  <WebElement> max = select.getOptions();
        int random = randomNumber(max.size()-1);
        select.selectByIndex(random+1);
        sleep(1);
    }

    public void typeinInputList(WebElement inputs,String value){
        sendKeysFunction(inputs,value);
    }


    public void verifyURL(String expectedResult){
        sleep(3);
        String URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains( expectedResult )  );
    }

    public void betweenNumbers(int min , int max , List<WebElement> priceList){
        for (int i = 0;i<priceList.size();i++){

            String priceSTR = priceList.get(i).getText();
            double priceDouble = Double.parseDouble(priceSTR);

//            System.out.println(priceDouble);
            if (min<=priceDouble && priceDouble<=max){
                Assert.assertTrue("numbers are between the range",true);
            }else {
                Assert.fail();
            }
        }
    }
}
