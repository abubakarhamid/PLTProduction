package assignment.PageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import assignment.AbstractComponents.AbstractComponent;
import static utils.PropertiesReader.getSelector;

public class PdpPage extends AbstractComponent{
	
    WebDriver driver;	
    public static String pdpProductName = null;
    public static String pdpProductSize = null;
    
    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
	public PdpPage(WebDriver driver) {	
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);				
	}

	@FindBy(xpath = "//*[@class='hidden lg:block']//button[not(contains(@class, 'PDPSizes_isOutOfStock__4vvyc')) and not(@data-event-label)]")
	public List<WebElement> size;

	public void storePdpNameAndSize() throws IOException {
		WebElement pdpProductNameEle = waitForElementToAppear(getSelector("pdpProductNameElements"));
		WebElement pdpProductSizeEle = waitForElementToAppear(getSelector("pdpSelectedSizeElement"));
		pdpProductName = pdpProductNameEle.getText().trim();
		pdpProductSize = pdpProductSizeEle.getText().trim();  
	}
		
    public void addProductToBag() throws IOException {
    	waitForElementToBeClickable(getSelector("addToBagElement")).click();
    }

    public void checkCartCount() throws IOException {
    	WebElement cartCountElement = waitForElementToAppear(getSelector("cartCountElements"));
        String cartCountText = cartCountElement.getText().trim();
        Assert.assertNotEquals(cartCountText, "0", "Cart count is zero!");    
    }

    public void viewBag() throws IOException {
        waitForElementToBeClickable(getSelector("viewBagElement")).click();
        return;
    }    
}  