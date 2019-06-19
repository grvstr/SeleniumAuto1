package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchPage extends BasePage{
	
	//*********Constructor*********
    public SearchPage(WebDriver driver) {
        super(driver);
    }
    
    //*********Page Variables*********
    String url = "https://www.autohero.com/de/search/";
 
    //*********Web Elements*********
    By firstRegistrationBy = By.xpath("//div[@data-qa-selector=\"filter-year\"]");
    By firstRegistrationDropdownBy = By.xpath("//div[@data-qa-selector=\"filter-year\"]//select");
    By sortDropdownBy = By.xpath("//div[@data-qa-selector=\"sort-select\"]//select");
 
    //*********Page Methods*********
    public SearchPage goToSearchPage (){
        driver.get(url);
        return this;
    }
    
    public SearchPage sortPriceDesc (){
    	selectFromDropdown(sortDropdownBy, "Höchster Preis");
    	return this;
    }
    
    public SearchPage filterFirstRegistration (String year){
    	click(firstRegistrationBy);
    	selectFromDropdown(firstRegistrationDropdownBy, "2015");
    	return this;
    }
}
