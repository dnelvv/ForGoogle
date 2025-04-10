package org.example.forgoogle.tests;

import org.example.forgoogle.pages.MainPage;
import org.example.forgoogle.pages.ResultsPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BingSearchTest {
    private WebDriver driver;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchResultsTest() {
        String input = "Selenium";
        MainPage mainSearchSecond = new MainPage(driver);
        mainSearchSecond.sendText(input);

        ResultsPage mainResultsSecond = new ResultsPage(driver);
        mainResultsSecond.clickElement(0);

        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(),
                "Открылась не верная ссылка");
    }

    @Test
    public void searchFieldTest() {
        String input = "Selenium";
        MainPage mainSearch = new MainPage(driver);
        mainSearch.sendText(input);

        ResultsPage mainResults = new ResultsPage(driver);
        assertEquals(input, mainResults.getTextFromSearchField(), "Текст не совпал");
    }

}