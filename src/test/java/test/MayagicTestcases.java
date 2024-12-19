package test;

import org.testng.annotations.Test;
import helper.BaseTest;
import pages.MayagicMethods;
import utils.MayagicLocators;

public class MayagicTestcases extends BaseTest {

    @Test(priority = 1)
    public void TestCase_001() {
        MayagicMethods.openURL(driver);
        MayagicMethods.verifyTitle(driver);
        MayagicMethods.verifyLogoAndContactUs(driver);
    }

    @Test(priority = 2)
    public void TestCase_002() {
        MayagicMethods.openURL(driver);
        MayagicMethods.verifyTitle(driver);
        MayagicMethods.verifySlide(driver, MayagicLocators.BUTTON_XPATH_1, MayagicLocators.EXPECTED_URL_1, "Let's Explore section verified");
        MayagicMethods.verifySlide(driver, MayagicLocators.BUTTON_XPATH_2, MayagicLocators.EXPECTED_URL_2, "Learn More section verified");
        MayagicMethods.verifySlide(driver, MayagicLocators.BUTTON_XPATH_3, MayagicLocators.EXPECTED_URL_3, "Contact Us section verified");
    }

    @Test(priority = 3)
    public void TestCase_003() {
        MayagicMethods.openURL(driver);
        MayagicMethods.verifyTitle(driver);
        MayagicMethods.verifySection(driver, MayagicLocators.CURRENT_CHALLENGE_SECTION_XPATH, "Current Challenge Section");
    }

    @Test(priority = 4)
    public void TestCase_004() {
        MayagicMethods.openURL(driver);
        MayagicMethods.verifyTitle(driver);
        MayagicMethods.verifySection(driver, MayagicLocators.OUR_RESPONSE_SECTION_XPATH, "Our Response Section");
    }

    @Test(priority = 5)
    public void TestCase_005() {
        MayagicMethods.openURL(driver);
        MayagicMethods.verifyTitle(driver);
        MayagicMethods.verifySection(driver, MayagicLocators.ALL_IN_ONE_SECTION_XPATH, "All-in-one AI Powered Platform for P&C Insurance");
    }

    @Test(priority = 6)
    public void TestCase_006() {
        MayagicMethods.openURL(driver);
        MayagicMethods.verifyTitle(driver);
        MayagicMethods.verifyContactUsForm(driver);
    }
}
