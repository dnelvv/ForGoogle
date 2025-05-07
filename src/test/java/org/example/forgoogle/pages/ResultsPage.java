package org.example.forgoogle.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ResultsPage {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    @FindBy(xpath = "//a[contains(@class, 'tilk')][contains(@href, 'selenium.dev')]")
    private List<WebElement> results;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickElement(int num) {
        webDriverWait.until(ExpectedConditions.visibilityOf(results.get(num)));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(results.get(num)))
                .click();
        System.out.println("Нажатие на результат под номером: " + num);
    }

    public String getTextFromSearchField() {
        webDriverWait.until(ExpectedConditions.visibilityOf(searchField));
        String searchValue = searchField.getAttribute("value");
        System.out.println("В строке поиска текст " + searchValue);
        return searchValue;
    }
}