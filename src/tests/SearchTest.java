package tests;

import org.testng.annotations.Test;
import pageObjects.SearchPage;
 
public class SearchTest extends BaseTest {
    @Test
    public void searchTest () {
 
        //*************PAGE INSTANTIATIONS*************
        SearchPage searchPage = new SearchPage(driver);
 
        //*************PAGE METHODS********************
        searchPage.goToSearchPage()
                .filterFirstRegistration("2015")
                .sortPriceDesc()
                .validateFirstRegistrationAndPriceDesc("2015");
        
    }
}