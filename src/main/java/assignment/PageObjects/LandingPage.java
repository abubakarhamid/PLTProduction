package assignment.PageObjects;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import static utils.PropertiesReader.getSelector;
import assignment.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{	
    WebDriver driver;
    
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);			
	}
	
	@FindBy(xpath = "//ul[@id=\"frame-header-nav\"]/li")
    public List<WebElement> category;
	
	public void openStoreAndAcceptCookies( String url) throws IOException, InterruptedException {
		driver.get(url);
		Assert.assertTrue(driver.getCurrentUrl().contains("prettylittlething"));
		Thread.sleep(500);
		try {
        WebElement acceptCookiesBtn = waitForElementToAppear(getSelector("CookiePopUp"));
		acceptCookiesBtn.click();
		} catch(Exception e) {
	        System.out.println("Cookies popup did not appear. Proceeding with the test.");
	    }
	}
		
}
