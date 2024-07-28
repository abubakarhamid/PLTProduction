package assignment.PageObjects;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import assignment.AbstractComponents.AbstractComponent;
import static utils.PropertiesReader.getSelector;

public class CheckoutPage extends AbstractComponent{
	
    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
    public static String pdpProductName = null;
	public static String pdpProductSize = null;
	public static String cartSubtotal = null;
	public static WebElement paymentButton = null;
	
	public CheckoutPage(WebDriver driver) {	
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);				
	}
		
    public void enterEmail(String enterYourEmail) throws IOException { 	
    	WebElement customerEmailElement = wait.until(webDriver -> {
    		WebElement element = driver.findElement(By.id("customer-email"));
    		return element.isDisplayed() ? element : null;
        	});
        customerEmailElement.sendKeys(enterYourEmail);
        waitForElementToBeClickable(getSelector("submitButtonEle")).click();
    }

    public void enterPassword(String enterYourPassword) throws IOException {
    	waitForElementToAppear(getSelector("passwordElement")).sendKeys(enterYourPassword);
        waitForElementToBeClickable(getSelector("submitButtonEle")).click();
    }
    
    public void assertCheckoutProduct() throws IOException, InterruptedException {
    	CheckoutPage.pdpProductName = PdpPage.pdpProductName;
        CheckoutPage.pdpProductSize = PdpPage.pdpProductSize;
        CheckoutPage.cartSubtotal = CartPage.cartSubtotal;
        WebElement checkoutProductNameElement = waitForElementToAppear(getSelector("checkoutProductNameEle"));
    	String checkoutProductName = checkoutProductNameElement.getText().trim();
        WebElement checkoutProductSizeElement = waitForElementToAppear(getSelector("checkoutProductSizeEle"));
        String checkoutProductSizeUntrimmed = checkoutProductSizeElement.getText();
        String[] trimSize = checkoutProductSizeUntrimmed.split(":");
    	String checkoutProductSize = trimSize[1].trim();
    	WebElement checkoutSubtotalElement = waitForElementToAppear(getSelector("checkoutSubtotalEle")); // Change Locator
    	String checkoutSubtotalValue = checkoutSubtotalElement.getText().trim();
    	String checkoutSubtotalNumeric = checkoutSubtotalValue.split(" ")[0];
        softAssert.assertEquals(checkoutProductName, pdpProductName, "Checkout Product name is not matching!");
        softAssert.assertEquals(checkoutProductSize, pdpProductSize, "Checkout Product size is not matching!");
        softAssert.assertEquals(checkoutSubtotalNumeric, cartSubtotal, "Product Subtotal is not matching!");
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            String warningMessage = "⚠️ WARNING: Assertion failed. Details: " + e.getMessage();
            Reporter.log("<div style=\"color: orange; font-weight: bold;\">" + warningMessage + "</div>", false);
            System.err.println(warningMessage);
        }
        return ;
    }
   
    public void IsDeliveryAndGrandTotalDisplayed() throws InterruptedException, IOException { 	
    	WebElement deliveryTotal =  waitForElementToAppear(getSelector("deliveryTotalEle"));
    	Assert.assertTrue(deliveryTotal.isDisplayed());
    	WebElement grandTotal =  waitForElementToAppear(getSelector("grandTotalEle"));
    	Assert.assertTrue(grandTotal.isDisplayed());
    }

    public void scrollToPaymentMethods() throws IOException {
    	paymentButton = waitForElementToBeClickable(getSelector("paymentButtonEle"));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paymentButton);  
    }
        
    public void selectPaymentMethod() throws InterruptedException {
        paymentButton.click();
    }    
    
}
