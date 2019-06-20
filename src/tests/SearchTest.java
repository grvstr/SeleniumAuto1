package tests;

import org.testng.annotations.Test;
import pageObjects.SearchPage;
 
public class SearchTest extends BaseTest {
    @Test (priority = 0)
    public void searchTest () throws InterruptedException {
 
        //*************PAGE INSTANTIATIONS*************
        SearchPage searchPage = new SearchPage(driver);
 
        //*************PAGE METHODS********************
        searchPage.goToSearchPage()
        .filterFirstRegistration("2015")
        .sortPriceDesc()
        .verifySortPriceDesc()
        .verifyFirstRegistration("2015");
        
    }
}