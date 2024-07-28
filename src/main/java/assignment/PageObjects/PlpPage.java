package assignment.PageObjects;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import assignment.AbstractComponents.AbstractComponent;
import static utils.PropertiesReader.getSelector;

public class PlpPage extends AbstractComponent{
	
    WebDriver driver;
    
    public PlpPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);				
	}
	
	public List<WebElement> product() throws IOException {
		WebElement productElements = waitForElementToAppear(getSelector("productsList"));
        return productElements.findElements(By.tagName("a"));
    }
			
    public void plpPageIsDisplayed() throws IOException {
	    WebElement productElements = waitForElementToAppear(getSelector("productsList"));
	    Assert.assertTrue(productElements.isDisplayed());
    }
		
}
