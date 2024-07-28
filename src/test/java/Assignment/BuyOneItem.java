package Assignment;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import assignment.PageObjects.CartPage;
import assignment.PageObjects.CheckoutPage;
import assignment.PageObjects.LandingPage;
import assignment.PageObjects.PdpPage;
import assignment.PageObjects.PlpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import assignment.AbstractComponents.AbstractComponent;
public class BuyOneItem extends AbstractComponent{
    public BuyOneItem() {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public static WebDriver driver;
	public static String url= "https://www.prettylittlething.com/";
	public static String yourEmail = "abutest+2008@pltautomation.com";
	public static String yourPassword = "Boohoo@123";	
	@BeforeClass
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
	}
	@Test
	public void BuyOneItemTestCase() throws InterruptedException, IOException {
			LandingPage onLandingPage = new LandingPage(driver);
            PlpPage onPlpPage = new PlpPage(driver);
            PdpPage onPdpPage = new PdpPage(driver);
            CartPage onCartPage = new CartPage(driver);
            CheckoutPage onCheckoutPage = new CheckoutPage(driver);
            
            onLandingPage.openStoreAndAcceptCookies(url);
    		selectOneRandom(onLandingPage.category);
            onPlpPage.plpPageIsDisplayed();
            selectOneRandom(onPlpPage.product());
            selectOneRandom(onPdpPage.size);
	        onPdpPage.storePdpNameAndSize();
            onPdpPage.addProductToBag();
            onPdpPage.checkCartCount();        
            onPdpPage.viewBag();
            onCartPage.assertNameAndSize();
            onCartPage.storeCartSubtotal();	
            onCartPage.proceedToCheckout();
            onCheckoutPage.enterEmail(yourEmail);
            onCheckoutPage.enterPassword(yourPassword);
            onCheckoutPage.assertCheckoutProduct();
            onCheckoutPage.IsDeliveryAndGrandTotalDisplayed();
            onCheckoutPage.scrollToPaymentMethods();
            onCheckoutPage.selectPaymentMethod();
	}	
	@AfterClass
    public void teardown() {
		if (driver != null) {
            driver.quit();
        }        
	}			
}
	
	

	









