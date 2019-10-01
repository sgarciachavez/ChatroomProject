package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.PropertyManager;

import java.util.concurrent.TimeUnit;

public class LoginTest {


    public static void main(String[] args) throws Exception{

        String url = PropertyManager.getInstance().getProperty("url");
        String chromedriver = PropertyManager.getInstance().getProperty("chromedriver");
        String username = PropertyManager.getInstance().getProperty("username");

        System.out.println("Login in Test");
        System.setProperty("webdriver.chrome.driver", chromedriver);
        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        WebElement userinput = driver.findElement(By.id("username"));
        WebElement loginbutton = driver.findElement(By.id("loginbutton"));
        userinput.sendKeys(username);
        Thread.sleep(3000);
        loginbutton.click();
        Thread.sleep(3000);
        driver.close();

    }

}
