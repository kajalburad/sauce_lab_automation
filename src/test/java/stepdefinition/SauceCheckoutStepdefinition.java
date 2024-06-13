package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SaucePageCheckout;
import utilities.PasswordManager;

import java.util.concurrent.TimeUnit;

public class SauceCheckoutStepdefinition extends PasswordManager {

    private WebDriver driver;
    private SaucePageCheckout sauceDemoPage;

    //This step will launch the chrome driver
    @Given("User is on Sauce Labs demo website")
    public void userIsOnSauceLabsWebsite() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Shubham\\Downloads\\chromedriver-win64-fial\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        sauceDemoPage = new SaucePageCheckout(driver);
        sauceDemoPage.navigateTo();
    }

    // This step will login and  select 3 random items and add to cart
    @When("User selects 3 random items")
    public void userSelectsRandomItems() {
        sauceDemoPage.login("test", "test");
        sauceDemoPage.selectRandomItems(3);
    }

    // This step will proceed to checkout
    @And("User proceeds to checkout")
    public void userProceedsToCheckout() {
        sauceDemoPage.proceedToCheckout();
    }

// This step will validate the order details

    @Then("Order should be confirmed successfully")
    public void orderShouldBeConfirmed() {
        sauceDemoPage.verifyOrderDetails();
    }


    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    // This step will add shipping data

    @And("User fills shipping information")
    public void userFillsShippingInformation() {
        sauceDemoPage.setShippingDetails();
        sauceDemoPage.clickContinue();
    }
    // This step will  complete the checkout process

    @And("User completes the checkout process")
    public void userCompletesTheCheckoutProcess() {
        sauceDemoPage.verifyDetialsDisplayed();
        sauceDemoPage.clickFinish();
    }
}