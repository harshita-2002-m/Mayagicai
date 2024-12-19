package helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private Properties properties = new Properties();

    // Load properties from the config file
    private void loadProperties() {
        try (FileInputStream fileInput = new FileInputStream("config.properties")) {
            properties.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Initialize WebDriver based on browser type
    private void initializeDriver() {
        String browser = properties.getProperty("browser", "edge").toLowerCase(); // Default to Edge
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
            default:
                driver = new EdgeDriver();
                break;
        }
    }

    @BeforeMethod
    public void setup() {
        loadProperties();
        initializeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit(); // Ensure the driver is quit after each test
        }
    }
}
