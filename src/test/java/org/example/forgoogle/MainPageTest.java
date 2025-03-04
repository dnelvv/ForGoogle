package org.example.forgoogle;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private final By searchFieldCss = By.cssSelector("#sb_form_q");

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        String input = "Selenium";

        WebElement searchField = driver.findElement(searchFieldCss);
        searchField.sendKeys(input);
        searchField.submit();

        WebElement searchPageField = driver.findElement(searchFieldCss);
        assertEquals(input, searchPageField.getAttribute("value"), "Введенное значение не найдено в поле поиска");
    }
}