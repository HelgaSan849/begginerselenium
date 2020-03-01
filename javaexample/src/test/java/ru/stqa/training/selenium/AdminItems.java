package ru.stqa.training.selenium;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class AdminItems {
    public WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void testMenuItems() {
        LoginAdmin();
        List<WebElement> allElements = driver.findElements(By.cssSelector("ul#box-apps-menu li#app-"));
        int allElementOnPage = allElements.size();
        for (int i = 0; i <= allElementOnPage; i++) {
            WebElement item = driver.findElement(By.xpath(".//span[@class='name']"));
            item.click();
            List<WebElement> allDocs = driver.findElements(By.xpath("./ul[@class='docs']/li[@id]"));
            int allDocsOnPage = allDocs.size();
            if(allDocsOnPage > 0) {
                for (int j = 0; j <= allDocsOnPage; j++) {
                    WebElement itemDocs = driver.findElement(By.xpath("./ul[@class='docs']/li[@id]" + j + "]"));
                    itemDocs.click();
                    driver.findElement(By.xpath("//h1"));
                }
            }
        }
    }

    private void LoginAdmin() {
        driver.get("http://localhost/litecart/admin/");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

