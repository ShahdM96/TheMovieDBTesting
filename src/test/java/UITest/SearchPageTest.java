package UITest;

import org.example.DriverFactory;
import org.example.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.example.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SearchPageTest {
    WebDriver driver;
    HomePage home;

    private static final String baseURL = "http://localhost:3000";
    private static final String expectedPageTitle = "Nuxt Movies";
    private static final String expectedPageMessage = "Type something to search...";

    @BeforeEach
    public void setup() {
        driver = DriverFactory.getDriver();
        //driver.manage().window().maximize();
        //getDriver();
        home = new HomePage(driver);
    }

    @Test
    void testPageTitle() {
        String pageTitle = driver.getTitle();
        assertEquals(expectedPageTitle, pageTitle);
    }

    @Test
    void testPageMessage() throws InterruptedException {
        home.clickSearch();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        //Thread.sleep(5000);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[text-4xl=''][op50=''][text-center='']")));
        String pageMessageText = element.getText();

        assertEquals(expectedPageMessage, pageMessageText);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
