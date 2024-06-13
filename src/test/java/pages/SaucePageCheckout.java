package pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.ExtentTestManager;
import utilities.PasswordManager;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SaucePageCheckout {
    private final WebDriver driver;
    WebDriverWait wait;
    private ExtentTest test;

    By txt_userName = By.xpath("//input[@name='user-name']");
    By txt_userPass = By.xpath("//input[@name='password']");
    By btn_login = By.xpath("//input[@name='login-button']");
    By img_cart = By.xpath("//span[@class='shopping_cart_badge']");
    By btn_checkout = By.xpath("//button[@name='checkout']");
    By txt_info = By.xpath("//span[contains(text(),'Checkout:')]");
    By txt_firstName = By.xpath("//input[@name='firstName']");
    By txt_lastName = By.xpath("//input[@name='lastName']");
    By txt_zip = By.xpath("//input[@name='postalCode']");
    By btn_continue = By.xpath("//input[@name='continue']");
    By btn_finish = By.xpath("//button[@name='finish']");
    By lbl_product = By.xpath("//div[@class='summary_info_label']");
    By lbl_value = By.xpath("//div[@class='summary_value_label']");
    By lbl_price = By.xpath("//div[@class='summary_subtotal_label']");
    By lbl_priceValue = By.xpath("//div[@class='summary_total_label']");
    By txt_Order = By.xpath("//div[contains(text(),'Your order has been dispatched')]");


    public SaucePageCheckout(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        ExtentReports extent = ExtentTestManager.getInstance();
        test = extent.createTest("Sauce Lab Automation Checkout ", "Checkout feature");

    }


    /* Method Name : navigateTo
     Description : Navigate to url in browser
      arguments : no
      author :Kajal Burad
     */

    public void navigateTo() {

        driver.get(PasswordManager.getPassword("url"));
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        test.pass("Navigated successfully to url "+PasswordManager.getPassword("url"));
      //

    }

    /* Method Name : login
     Description : To check the login fields and success login validation
      arguments : no
      author :Kajal Burad
     */
    public void login(String username1, String password1) {
        try {

            WebElement usernameInput = driver.findElement(txt_userName);
            WebElement passwordInput = driver.findElement(txt_userPass);
            WebElement loginButton = driver.findElement(btn_login);
            if (usernameInput.isDisplayed() && passwordInput.isDisplayed() && loginButton.isDisplayed()) {
                System.out.println("Login form is displayed");
                usernameInput.sendKeys(PasswordManager.getPassword("username"));
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                passwordInput.click();
                passwordInput.sendKeys(PasswordManager.getPassword("password"));
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                System.out.println("pass is " + PasswordManager.getPassword("password"));
                wait.until(ExpectedConditions.visibilityOfElementLocated(btn_login));
                loginButton.click();
                System.out.println("Clicked on login button success");
                Assert.assertTrue(true, "Login success");
                test.pass("User login successfully");
            } else {
                System.out.println("Login form is not displayed");
                Assert.assertFalse(false, "Login not success");
                test.fail("User login failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     /* Method Name : selectRandomItems
     Description : To select any 3 random items
      arguments : int type count
      author :Kajal Burad
     */

    public void selectRandomItems(int count) {
        try {
            List<WebElement> products = driver.findElements(By.className("inventory_item"));
            Random rand = new Random();
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            for (int i = 0; i < count; i++) {
                int randomIndex = rand.nextInt(products.size());
                WebElement product = products.get(randomIndex);
                WebElement addButton = product.findElement(By.className("btn_inventory"));
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                addButton.click();
                products.remove(randomIndex);
            }
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            System.out.println("Products added successfully");
            test.pass("Products added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            test.fail("Product not added");
        }
    }

    /* Method Name : proceedToCheckout
    Description : To click on cart and proceed to checkout
     arguments :None
     author :Kajal Burad
    */
    public void proceedToCheckout() {
        try {
            WebElement cartIcon = driver.findElement(img_cart);

            if (cartIcon.isDisplayed()) {
                System.out.println("Cart Icon is displayed successfyully'");
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                cartIcon.click();
                System.out.println("Clicked on cart icon");
            }
            WebElement checkoutButton = driver.findElement(btn_checkout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(img_cart));
            checkoutButton.click();
            System.out.println("checkout  successfully");
            test.pass("Checkout Successful");

        } catch (Exception e) {
            e.printStackTrace();
            test.fail("Checkout failed");
        }
    }

      /* Method Name :setShippingDetails
    Description : To fill the shipping details
     arguments :None
     author :Kajal Burad
    */

    public void setShippingDetails() {
        try {
            WebElement info = driver.findElement(txt_info);
            WebElement name = driver.findElement(txt_firstName);
            WebElement lname = driver.findElement(txt_lastName);
            WebElement zip = driver.findElement(txt_zip);
            if (info.isDisplayed()) {
                Assert.assertTrue(true, "Shipping information is displayed to the user");
                wait.until(ExpectedConditions.visibilityOfElementLocated(txt_firstName));
                name.click();
                name.sendKeys("John");
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

                lname.click();
                lname.sendKeys("Doe");
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

                zip.click();
                zip.sendKeys("422009");
                wait.until(ExpectedConditions.visibilityOfElementLocated(txt_zip));
                System.out.println("Information filled successfully");
                test.pass("Shipping information filled successfully");
            } else {
                Assert.assertFalse(false, "Shipping information is  not displayed to the user");
                System.out.println("Information field is not displayed");
                test.fail("Shipping information filling failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        /* Method Name :clickContinue
    Description : To click on continue button
     arguments :None
     author :Kajal Burad
    */

    public void clickContinue() {
        try {
            WebElement proceed = driver.findElement(btn_continue);

            if (proceed.isDisplayed()) {
                System.out.println("Continue button is displayed");
                wait.until(ExpectedConditions.visibilityOfElementLocated(btn_continue));
                proceed.click();
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                System.out.println("clicked on continue button");
                test.pass("User is able to click on continue");
            } else {
                System.out.println("continue button not displayed");
                Assert.assertFalse(false, "continue button not displayed");
                test.pass("User is not able to click on continue");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      /* Method Name :verifyDetialsDisplayed
    Description : To verify the details displayed while placing order
     arguments :None
     author :Kajal Burad
    */

    public void verifyDetialsDisplayed() {
        try {

            List<WebElement> productsLabel = driver.findElements(lbl_product);
            List<WebElement> productsValue = driver.findElements(lbl_value);
            WebElement price = driver.findElement(lbl_price);
            WebElement pricevalue = driver.findElement(lbl_priceValue);
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsLabel);
            for (int i = 0; i < productsLabel.size(); i++) {
                String label = productsLabel.get(i).getText();
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                System.out.println("Label :" + label);
            }
            for (int i = 0; i < productsValue.size(); i++) {
                String label = productsLabel.get(i).getText();
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                System.out.println("product value is displayed :" + label);
            }
            System.out.println("Price label is displayed as:" + price.getText());
            System.out.println("Price value is displayed as:" + pricevalue.getText());


            System.out.println("details validated successfully");
            test.pass("User is able to see product details");
        } catch (Exception e) {
            e.printStackTrace();
            test.pass("User unable to see product details");
        }

    }
      /* Method Name :clickFinish
    Description : To click on finish button
     arguments :None
     author :Kajal Burad
    */

    public void clickFinish() {
        try {
            WebElement proceed = driver.findElement(btn_finish);

            if (proceed.isDisplayed()) {
                System.out.println("Finish button is displayed");
                wait.until(ExpectedConditions.visibilityOfElementLocated(btn_finish));
                proceed.click();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                System.out.println("clicked on Finish button");
                test.pass("User is able to click on finish button");
            } else {
                System.out.println("Finish button not displayed");
                Assert.assertFalse(false, "Finish button not displayed");
                test.pass("User is ubable to click on Finish button");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Method Name :verifyOrderDetails
    Description : To verify the order is successfully placed
     arguments :None
     author :Kajal Burad
    */
    public void verifyOrderDetails() {
        try {
            WebElement order = driver.findElement(txt_Order);

            if (order.isDisplayed()) {
                System.out.println("order success message displayed");
                String message = order.getText();
                if (message.contains("Your order has been dispatched, and will arrive just as fast as the pony can get there!")) {
                    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

                    Assert.assertTrue(true, "Order placed successfully");
                    test.pass("User is able to place order successfully");
                } else {
                    System.out.println("order could not place");
                    Assert.assertFalse(false, "Order does  not placed");
                    test.fail("User is unable to place order");
                }
            }
            ExtentTestManager.getInstance().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
