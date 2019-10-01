package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.PropertyManager;

import java.util.concurrent.TimeUnit;

public class ChatTest {

    public static void main(String[] args) throws Exception{

        String url = PropertyManager.getInstance().getProperty("url");
        String chromedriver = PropertyManager.getInstance().getProperty("chromedriver");
        String username = PropertyManager.getInstance().getProperty("username");
        String msgText = PropertyManager.getInstance().getProperty("msg");

        System.out.println("Chat Test");
        System.setProperty("webdriver.chrome.driver", chromedriver);
        WebDriver driver = new ChromeDriver();

        //Login First and join chat
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        WebElement userinput = driver.findElement(By.id("username"));
        WebElement loginbutton = driver.findElement(By.id("loginbutton"));
        userinput.sendKeys(username);
        Thread.sleep(2000);
        loginbutton.click();
        //Login First

        Thread.sleep(2000);
        WebElement msg = driver.findElement(By.id("msg"));
        WebElement sendbutton = driver.findElement(By.id("sendbutton"));

        msg.sendKeys(msgText);
        Thread.sleep(2000);
        sendbutton.click();

        Thread.sleep(3000);
        driver.close();

    }

}
