import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheMovieDBTest {

    WebDriver driver;
    private static final String baseURL ="http://localhost:3000";



    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
    }


    @Test
    void testPageTitle() {
        driver.get(baseURL);
        String actualTitle = driver.getTitle();
        String expectedPageTitle = "Nuxt Movies";
        assertEquals(expectedPageTitle, actualTitle);

        //System.out.println("Homepage title is: " + actualTitle);

    }

    @Test
    void test1() {
            driver.get(baseURL);
        //explicit wait
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        // Wait for the element to be visible
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h1)[1]")));
            String expectedText = "Sonic the Hedgehog 3";
            String actualText = element.getText();
            assertEquals(expectedText,actualText, "Incorrect Text");

    }


    @Test
    void testPopularMoviesTitle() {
        driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        WebElement element = driver.findElement(By.xpath("//div[@data-v-inspector='components/carousel/Base.vue:21:5']"));
        String expectedText = "Popular Movies";
        String actualText = element.getText();
        assertEquals(expectedText,actualText, "Incorrect Title");
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
