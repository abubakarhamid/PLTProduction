package assignment.AbstractComponents;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import assignment.PageObjects.PdpPage;
import assignment.PageObjects.PlpPage;

public class AbstractComponent{
	public WebDriver driver;
	public static WebElement selectedItem;	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);				
	}
	public WebElement findElement(By findBy){
        return driver.findElement(findBy);                
	}
	public WebElement waitForElementToAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));                
	}
	public List<WebElement> waitForAllElementsToAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));                
	}
	public WebElement waitForElementToBeClickable(By findBy){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(findBy));                
	}	
	public WebElement selectOneRandom(List<WebElement> product) throws InterruptedException, IOException {
		List<String> textList = new ArrayList<>();
        for (WebElement element : product) {
            textList.add(element.getText());
        }
        textList.removeIf(text -> text.equalsIgnoreCase("EDIT") ||
        text.equalsIgnoreCase("BEAUTY") || text.equalsIgnoreCase("ACCESSORIES & HOME")
        || text.equalsIgnoreCase("SHOES")|| text.equalsIgnoreCase("Size Guide"));
        if(textList.isEmpty()) {
        	isItemOutOfStock();
        	return null;
        } 
        Random random = new Random();
        int index = random.nextInt(textList.size());
        selectedItem = product.get(index);
        selectedItem.click();
        return selectedItem; 
	}
	private void isItemOutOfStock() throws InterruptedException, IOException {
			//if (!selectedItem.getAttribute("class").contains("bg-gray-850")) {
			PdpPage onPdpPage = new PdpPage(driver);
    		PlpPage onPlpPage = new PlpPage(driver);
    		driver.navigate().back();
    		Thread.sleep(3000);
    		selectOneRandom(onPlpPage.product());
    		selectOneRandom(onPdpPage.size);
    		//throw new AssertionError("The selected item does not have the 'bg-gray-850' class after clicking.");
	        //}
    		//else {
    		//Thread.sleep(3000);
    		//System.out.println("Selected item: " + selectedItem.getText() + "is grey");
    		//}
	}
}
