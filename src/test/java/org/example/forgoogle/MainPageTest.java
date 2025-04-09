package org.example.forgoogle;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class MainPageTest {
    private WebDriver driver;
    private final By searchFieldCss = By.cssSelector("#sb_form_q");
    private final String baseUrl = "https://www.bing.com/";
    private final String expectedUrl = "https://www.selenium.dev/";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseUrl);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testFirstLinkLeadsToSeleniumDev() {
        String input = "Selenium";
        WebElement searchField = driver.findElement(searchFieldCss);
        searchField.sendKeys(input);
        searchField.submit();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@class, 'tilk')]")));

        List<WebElement> results = driver.findElements(By.xpath("//a[contains(@class, 'tilk')]"));

        clickOnElement(results, 0);

        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl,
                "Первая ссылка не ведет на сайт https://www.selenium.dev/");
    }

    private void clickOnElement(List<WebElement> elements, int index) {
        elements.get(index).click();
        System.out.println("Клик по элементу: " + elements.get(index).getText());
    }
}