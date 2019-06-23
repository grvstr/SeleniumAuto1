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
    By nextButtonBy = By.xpath("//span[@aria-label=\"Next\"]");
    By nextButtonDisabledBy = By.xpath("//span[@aria-label=\"Next\"]/ancestor::li[@class=\"disabled\"]");
    
    //*********Page Methods*********
    public SearchPage goToSearchPage (){
        driver.get(url);
        return this;
    }
    
    public SearchPage sortPriceDesc () {
    	selectFromDropdown(sortDropdownBy, "Höchster Preis");
    	waitForProgressbarToDisappear();
    	return this;
    }
    
    public SearchPage filterFirstRegistration (String year){
    	click(firstRegistrationBy);
    	selectFromDropdown(firstRegistrationDropdownBy, year);
    	waitForProgressbarToDisappear();
    	return this;
    }
    
    public SearchPage goToNextPage () {
    	click(nextButtonBy);
    	waitForProgressbarToDisappear();
    	return this;
    }
    
    //GET
    public ArrayList<Integer> getPriceList () {
    	ArrayList<Integer> obtainedList = new ArrayList<>(); 
    	List<WebElement> elementList= driver.findElements(priceListBy);
    	
    	for(WebElement we:elementList){
     	   obtainedList.add(Integer.parseInt(we.getText().split(" ")[0].replaceAll("\\.", "")));
     	}
    	
    	return obtainedList;
    }
    
    public ArrayList<Integer> getFirstRegistrationList (){
    	
    	ArrayList<Integer> obtainedList = new ArrayList<>(); 
    	List<WebElement> elementList= driver.findElements(yearListBy);
    	
    	for(WebElement we:elementList){
     	   obtainedList.add(Integer.parseInt(we.getText().substring(5)));
     	}
    	
    	return obtainedList;
    }
    
    //VALIDATE
    
    public SearchPage validateFirstRegistrationAndPriceDesc (String year) {
    	
    	//getting list of first registrations and prices
    	ArrayList<Integer> firstRegistrationList = getFirstRegistrationList();
    	ArrayList<Integer> priceList = getPriceList();
    	
    	//if there are more than one page, we will iterate through all of them
    	while (driver.findElements(nextButtonDisabledBy).size() < 1) {
    		goToNextPage();
    		firstRegistrationList.addAll(getFirstRegistrationList());
    		priceList.addAll(getPriceList());
    	}
    	
    	//and finally validate them
    	validateFirstRegistration(year, firstRegistrationList);
    	validateSortPriceDesc(priceList);
    	
    	return this;
    }
    
    public SearchPage validateFirstRegistration (String year, ArrayList<Integer> firstRegistrationList){
    	
    	for(Integer we:firstRegistrationList){
     	   Assert.assertTrue(we >= Integer.parseInt(year));
     	}
    	
    	return this;
    }

    public SearchPage validateSortPriceDesc (ArrayList<Integer> obtainedList){
    	
     	ArrayList<Integer> sortedList = new ArrayList<>();
     	
     	for(Integer s:obtainedList){
     		sortedList.add(s);
     	}
     	
     	Collections.sort(sortedList);
     	Collections.reverse(sortedList);
     	
     	Assert.assertTrue(sortedList.equals(obtainedList));

    	return this;
    }
}
