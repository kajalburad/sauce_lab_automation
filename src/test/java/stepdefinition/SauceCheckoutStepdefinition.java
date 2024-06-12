package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SaucePageCheckout;
import utilities.PasswordManager;

import java.util.concurrent.TimeUnit;

public class SauceCheckoutStepdefinition extends PasswordManager {

    private WebDriver driver;
    private SaucePageCheckout sauceDemoPage;

    @Given("User is on Sauce Labs demo website")
    public void userIsOnSauceLabsWebsite() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Shubham\\Downloads\\chromedriver-win64-fial\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        sauceDemoPage = new SaucePageCheckout(driver);
        sauceDemoPage.navigateTo();
    }

    @When("User selects 3 random items")
    public void userSelectsRandomItems() {
        sauceDemoPage.login("standard_user", "secret_sauce");
        sauceDemoPage.selectRandomItems(3);
    }

    @And("User proceeds to checkout")
    public void userProceedsToCheckout() {
        sauceDemoPage.proceedToCheckout();
    }

    // Implement other step definitions

    @Then("Order should be confirmed successfully")
    public void orderShouldBeConfirmed() {
        sauceDemoPage.verifyOrderDetails();
    }


    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @And("User fills shipping information")
    public void userFillsShippingInformation() {
        sauceDemoPage.setShippingDetails();
        sauceDemoPage.clickContinue();
    }

    @And("User completes the checkout process")
    public void userCompletesTheCheckoutProcess() {
        sauceDemoPage.verifyDetialsDisplayed();
        sauceDemoPage.clickFinish();
    }
}