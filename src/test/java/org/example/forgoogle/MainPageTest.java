package org.example.forgoogle;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
        List<WebElement> searchResults = driver.findElements(By.xpath(
                "//a[@class='tilk' and @href='https://www.selenium.dev/']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.attributeContains(By.cssSelector("h2 > a[href]"), "href", "selenium"),
                ExpectedConditions.elementToBeClickable(By.cssSelector("h2 > a[href]"))
        ));
        List results = driver.findElements(By.cssSelector("h2 > a[href]"));

        if (!searchResults.isEmpty()) {
            searchResults.get(0).click();
            System.out.println("Первая ссылка была найдена и кликнута.");
        } else {
            System.out.println("Результатов поиска по заданному селектору не найдено.");
        }
        clickOnElement(searchResults, 0);

        if (!searchResults.isEmpty()) {
            String linkUrl = searchResults.get(0).getAttribute("href");
            assertEquals("https://www.selenium.dev/", linkUrl,
                    "Ссылка не ведет на \"https://www.selenium.dev/\" ");
            System.out.println("Первая ссылка ведет на правильный сайт");
        } else {
            System.out.println("Результаты не найдены");
        }
        WebElement searchPageField = driver.findElement(searchFieldCss);
        assertEquals(input, searchPageField.getAttribute("value"), "Введенное значение не найдено в поле поиска");
    }

    public void clickOnElement(List<WebElement> searchResults, int index) {
        searchResults.get(index).click();
        System.out.println("Клик по элементу:" + searchResults.get(index).getText());
    }
}