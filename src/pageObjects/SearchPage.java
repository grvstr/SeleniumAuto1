package pageObjects;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    By priceListBy = By.xpath("//div[@data-qa-selector=\"price\"]");
    By yearListBy = By.xpath("//ul[@data-qa-selector=\"spec-list\"]/li[1]");
    
    //*********Page Methods*********
    public SearchPage goToSearchPage (){
        driver.get(url);
        return this;
    }
    
    public SearchPage sortPriceDesc () throws InterruptedException{
    	selectFromDropdown(sortDropdownBy, "H�chster Preis");
    	Thread.sleep(1000); //TODO
    	return this;
    }
    
    public SearchPage filterFirstRegistration (String year){
    	click(firstRegistrationBy);
    	selectFromDropdown(firstRegistrationDropdownBy, year);
    	return this;
    }
    
    public SearchPage verifySortPriceDesc (){
    	ArrayList<String> obtainedList = new ArrayList<>(); 
    	List<WebElement> elementList= driver.findElements(priceListBy);
    	
    	for(WebElement we:elementList){
     	   obtainedList.add(we.getText());
     	}
    	
     	ArrayList<String> sortedList = new ArrayList<>();   
     	for(String s:obtainedList){
     		sortedList.add(s);
     	}
     	Collections.sort(sortedList);
     	Collections.reverse(sortedList);
     	
     	Assert.assertTrue(sortedList.equals(obtainedList));

    	return this;
    }
    
    public SearchPage verifyFirstRegistration (String year){
    	List<WebElement> elementList= driver.findElements(yearListBy);
    	
    	for(WebElement we:elementList){
     	   Assert.assertTrue(Integer.parseInt(we.getText().substring(5)) >= Integer.parseInt(year));
     	}
    	
    	return this;
    }
}
