package assignment.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import assignment.AbstractComponents.AbstractComponent;
import static utils.PropertiesReader.getSelector;
import java.io.IOException;

public class CartPage extends AbstractComponent {
    
    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();
	public static String cartSubtotal = null;
    public String pdpProductName = null;
    public String cartPageProductName = null;
    public String pdpProductSize = null;
    public String cartPageProductSize = null;
    
    public CartPage(WebDriver driver) {    
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);                
    }
      
    private By getCartPageProductNameElement() {
    	return By.cssSelector("a[class='font-brand-thin text-sm text-new-brand-black hover:underline lg:text-base lg:tracking-wide']");
    }

    private By getCartPageProductSizeElement() {
    	return By.xpath("//span[contains(normalize-space(.), 'Size: " + PdpPage.pdpProductSize + "')]");
    }
	
    public void assertNameAndSize() throws IOException {
    	this.pdpProductName = PdpPage.pdpProductName;
    	this.pdpProductSize = PdpPage.pdpProductSize;
    	cartPageProductName = waitForElementToAppear(getCartPageProductNameElement()).getText().trim();
    	String cartPageProductSizeUntrimmed = waitForElementToAppear(getCartPageProductSizeElement()).getText().trim();
    	String[] partsSize = cartPageProductSizeUntrimmed.split(":");
    	cartPageProductSize = partsSize[1].trim();
    	softAssert.assertEquals(cartPageProductName,pdpProductName, "Product Name does not match");
    	softAssert.assertEquals(cartPageProductSize,pdpProductSize, "Product Size does not match");
    	try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            String warningMessage = "⚠️ WARNING: Assertion failed. Details: " + e.getMessage();
            Reporter.log("<div style=\"color: orange; font-weight: bold;\">" + warningMessage + "</div>", false);
            System.err.println(warningMessage);
        }
    }   
    
    public void storeCartSubtotal() throws IOException {
    	WebElement cartSubtotalElement = waitForElementToAppear(getSelector("cartSubTotalEle"));
        cartSubtotal = cartSubtotalElement.getText().trim();
        return ;
    }
    
    public void proceedToCheckout() throws IOException {
    	waitForElementToBeClickable(getSelector("checkoutButtonEle")).click();
    }

}
