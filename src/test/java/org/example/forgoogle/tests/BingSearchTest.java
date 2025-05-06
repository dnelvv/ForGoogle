package org.example.forgoogle.tests;

import org.example.forgoogle.pages.MainPage;
import org.example.forgoogle.pages.ResultsPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BingSearchTest {
    private static final String SELENIUM = "Selenium";
    private WebDriver driver;
    MainPage mainPage;
    ResultsPage resultsPage;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.bing.com/");
        mainPage = new MainPage(driver);
        resultsPage = new ResultsPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchResultsTest() {
        mainPage.sendText(SELENIUM);
        resultsPage.clickElement(0);
        goToLastTab();

        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(),
                "Открылась не верная ссылка");
    }

    @Test
    public void searchFieldTest() {
        mainPage.sendText(SELENIUM);
        assertEquals(SELENIUM, resultsPage.getTextFromSearchField(), "Текст не совпал");
    }

    private void goToLastTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

}