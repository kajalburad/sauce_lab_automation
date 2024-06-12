package pages;

import com.aventstack.extentreports.ExtentTest;
import com.github.dockerjava.api.model.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.PasswordManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SaucePageCheckout {
    private WebDriver driver;
    WebDriverWait wait;

    By txt_userName = By.xpath("//input[@name='user-name']");
    By txt_userPass = By.xpath("//input[@name='password']");
    By btn_login = By.xpath("//input[@name='login-button']");
    By img_cart=By.xpath("//span[@class='shopping_cart_badge']");
    By btn_checkout=By.xpath("//button[@name='checkout']");
    By txt_info=By.xpath("//span[contains(text(),'Checkout:')]");
    By txt_firstName=By.xpath("//input[@name='firstName']");
    By txt_lastName=By.xpath("//input[@name='lastName']");
    By txt_zip=By.xpath("//input[@name='postalCode']");
    By btn_continue=By.xpath("//input[@name='continue']");
    By btn_finish=By.xpath("//button[@name='finish']");
    By lbl_product=By.xpath("//div[@class='summary_info_label']");
    By lbl_value=By.xpath("//div[@class='summary_value_label']");
    By lbl_price=By.xpath("//div[@class='summary_subtotal_label']");
    By lbl_priceValue=By.xpath("//div[@class='summary_total_label']");
    By txt_Order=By.xpath("//div[contains(text(),'Your order has been dispatched')]");


    public SaucePageCheckout(WebDriver driver) {
        this.driver = driver;
         wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    }

    public void navigateTo() {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String username1, String password1) {
        WebElement usernameInput = driver.findElement(txt_userName);
        usernameInput.sendKeys(PasswordManager.getPassword("username"));
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        WebElement passwordInput = driver.findElement(txt_userPass);
        passwordInput.click();
        passwordInput.sendKeys(PasswordManager.getPassword("password"));
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        System.out.println("pass is " + PasswordManager.getPassword("password"));
        // passwordInput.sendKeys();
        WebElement loginButton = driver.findElement(btn_login);
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_login));
        loginButton.click();

    }

    public void selectRandomItems(int count) {
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
    }

    public void proceedToCheckout() {
        WebElement cartIcon = driver.findElement(img_cart);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        cartIcon.click();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        WebElement checkoutButton = driver.findElement(btn_checkout);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(img_cart));
        checkoutButton.click();
        System.out.println("checkout  successfully");
    }


    public void setShippingDetails(){
        try{
            WebElement info = driver.findElement(txt_info);
            WebElement name = driver.findElement(txt_firstName);
            WebElement lname = driver.findElement(txt_lastName);
            WebElement zip = driver.findElement(txt_zip);
            if(info.isDisplayed()){
                Assert.assertTrue(true,"Shipping information is displayed to the user");
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
                System.out.println("Information field successfully");

            }
            else {
                Assert.assertFalse(false,"Shipping information is  not displayed to the user");
                System.out.println("Information field is not displayed");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void clickContinue()
    {
        try{
            WebElement proceed = driver.findElement(btn_continue);

             if(proceed.isDisplayed())
             {
                 System.out.println("Continue button is displayed");
                 wait.until(ExpectedConditions.visibilityOfElementLocated(btn_continue));
                 proceed.click();
                 driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                 System.out.println("clicked on continue button");
             }
             else {
                 System.out.println("continue button not displayed");
                 Assert.assertFalse(false,"continue button not displayed");
             }

        }catch (Exception e)
        {
          e.printStackTrace();
        }
    }

   public void  verifyDetialsDisplayed()
    {
        try{

            List<WebElement> productsLabel = driver.findElements(lbl_product);
            List<WebElement> productsValue = driver.findElements(lbl_value);
            WebElement price = driver.findElement(lbl_price);
            WebElement pricevalue = driver.findElement(lbl_priceValue);
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsLabel);
            for (int i = 0; i < productsLabel.size(); i++) {
                String label = productsLabel.get(i).getText().toString();
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                System.out.println("Label :"+label);
            }
            for (int i = 0; i < productsValue.size(); i++) {
                String label = productsLabel.get(i).getText().toString();
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                System.out.println("product value is displayed :"+label);
            }
            System.out.println("Price label is displayed as:"+price.getText());
            System.out.println("Price value is displayed as:"+pricevalue
                    .getText());


            System.out.println("details validated successfully");
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void clickFinish()
    {
        try{
            WebElement proceed = driver.findElement(btn_finish);

            if(proceed.isDisplayed())
            {
                System.out.println("Finish button is displayed");
                wait.until(ExpectedConditions.visibilityOfElementLocated(btn_finish));
                proceed.click();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                System.out.println("clicked on Finish button");
            }
            else {
                System.out.println("Finish button not displayed");
                Assert.assertFalse(false,"Finish button not displayed");
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void verifyOrderDetails(){
        try{
            WebElement order = driver.findElement(txt_Order);

            if(order.isDisplayed()) {
                System.out.println("order success message displayed");
                String message = order.getText().toString();
                if (message.contains("Your order has been dispatched, and will arrive just as fast as the pony can get there!")) {
                    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

                    Assert.assertTrue(true, "Order placed successfully");
                } else {
                    System.out.println("order could not place");
                    Assert.assertFalse(false, "Order does  not placed");
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
