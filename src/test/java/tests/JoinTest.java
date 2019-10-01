package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.PropertyManager;

import java.util.concurrent.TimeUnit;

public class JoinTest {

    public static void main(String[] args) throws Exception{

        String url = PropertyManager.getInstance().getProperty("url");
        String chromedriver = PropertyManager.getInstance().getProperty("chromedriver");
        String username = PropertyManager.getInstance().getProperty("username");
        String username2 = PropertyManager.getInstance().getProperty("username2");
        String hiMary = PropertyManager.getInstance().getProperty("hiMary");
        String hiJohn = PropertyManager.getInstance().getProperty("hiJohn");

        System.out.println("Join Test");
        System.setProperty("webdriver.chrome.driver", chromedriver);
        WebDriver driver = new ChromeDriver();

        //John Login
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        WebElement userinput = driver.findElement(By.id("username"));
        WebElement loginbutton = driver.findElement(By.id("loginbutton"));
        userinput.sendKeys(username);
        Thread.sleep(2000);
        loginbutton.click();
        //Login First

        Thread.sleep(1000);

        //Mary Login
        WebDriver driver2 = new ChromeDriver();
        driver2.get(url);
        driver2.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        WebElement user2input = driver2.findElement(By.id("username"));
        WebElement login2button = driver2.findElement(By.id("loginbutton"));
        user2input.sendKeys(username2);
        Thread.sleep(2000);
        login2button.click();



        WebElement fromJohn = driver.findElement(By.id("msg"));
        WebElement sendbutton = driver.findElement(By.id("sendbutton"));

        WebElement fromMary = driver2.findElement(By.id("msg"));
        WebElement sendbutton2 = driver2.findElement(By.id("sendbutton"));

        fromJohn.sendKeys(hiMary);
        Thread.sleep(3000);
        sendbutton.click();

        fromMary.sendKeys(hiJohn);
        Thread.sleep(3000);
        sendbutton2.click();

        //Give the user/test time to observe the changes
        Thread.sleep(20000);
        driver.close();
        driver2.close();

    }

}
