package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.MayagicLocators;

public class MayagicMethods {
    public static void openURL(WebDriver driver) {
        driver.get("https://www.mayagic.ai/");
        driver.manage().window().maximize();
    }

    public static void verifyTitle(WebDriver driver) {
        String act_title = driver.getTitle();
        if (act_title.equals("MAYAGIC AI"))
            System.out.println("Title Found");
        else
            System.out.println("Title not Found");
    }

    public static void verifyLogoAndContactUs(WebDriver driver) {
        boolean logo = driver.findElement(By.xpath(MayagicLocators.LOGO_XPATH)).isDisplayed();
        boolean contactUs = driver.findElement(By.xpath(MayagicLocators.CONTACT_US_XPATH)).isDisplayed();
        
        if (logo && contactUs) {
            System.out.println("Logo and Contact Us Found");
        } else {
            System.out.println("Not Found");
        }
    }

    public static void verifySection(WebDriver driver, String sectionXpath, String sectionName) {
        boolean section = driver.findElement(By.xpath(sectionXpath)).isDisplayed();
        
        if (section) {
            System.out.println(sectionName + " Found");
        } else {
            System.out.println(sectionName + " Not Found");
        }
    }

    public static void verifySlide(WebDriver driver, String buttonXpath, String expectedUrl, String successMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean buttonVisible = false;
        int retries = 3;

        for (int attempt = 0; attempt < retries; attempt++) {
            try {
                WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buttonXpath)));
                if (button.isDisplayed()) {
                    buttonVisible = true;
                    button.click();
                    Thread.sleep(2000);
                    if (driver.getCurrentUrl().equals(expectedUrl)) {
                        System.out.println(successMessage);
                    } else {
                        System.out.println("URL mismatch for: " + successMessage);
                    }
                    break;
                }
            } catch (Exception e) {
                System.out.println("Retrying for slide: " + buttonXpath);
            }
        }

        if (!buttonVisible) {
            System.out.println("Button not found for slide: " + buttonXpath);
        }
    }

    public static void verifyContactUsForm(WebDriver driver) {
        try {
            // Click the 'Contact Us' link
            driver.findElement(By.xpath("//div[@id='pAb6ohlRDgEh']//a[@href='#contactus']/span[@class='brz-span brz-text__editor']")).click();

            // Wait until the contact form is visible (using explicit wait)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MayagicLocators.CONTACT_US_FORM_XPATH)));

            // Fill out the form
            driver.findElement(By.name("w8hCh9DVhEAA")).sendKeys("HarshitaMakode");
            driver.findElement(By.name("ipWuFLY0YkFW")).sendKeys("harshita_m@pursuitsoftware.biz");
            driver.findElement(By.name("t02edM6mNRBN")).sendKeys("Testing Purpose");

            // Locate the Submit button
            WebElement submitButton = driver.findElement(By.className("/html//section[@id='contactus']/div/div[2]/div//div[@class='brz-column__items brz-css-iEC9K brz-css-jW8BH']/div[4]/div/form[@action='https://www.mayagic.ai/wp-admin/admin-ajax.php?nonce=&action=brizy_submit_form']//button"));

            // Ensure the button is visible and scroll to it
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

            // Wait for the button to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(submitButton));

            // Attempt to click the button using different strategies
            boolean isClicked = false;
            try {
                // First try Actions class click
                Actions actions = new Actions(driver);
                actions.moveToElement(submitButton).click().perform();
                isClicked = true;
            } catch (ElementClickInterceptedException e) {
                System.out.println("Actions click intercepted. Trying JavaScript click...");
            }

            if (!isClicked) {
                try {
                    // Fallback to JavaScriptExecutor click
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
                    isClicked = true;
                } catch (Exception jsClickException) {
                    System.out.println("JavaScript click also failed: " + jsClickException.getMessage());
                }
            }

            // If still not clicked, throw an error
            if (!isClicked) {
                throw new Exception("Submit button could not be clicked by any method.");
            }

            // Wait for success message
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'brz-forms2__alert--success')]")));

            if (successMessage.getText().contains("YOUR EMAIL WAS SENT SUCCESSFULLY")) {
                System.out.println("Form Submitted Successfully: " + successMessage.getText());
            } else {
                System.out.println("Success message text does not match.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }



    
}
