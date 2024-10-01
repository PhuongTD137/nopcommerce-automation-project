package nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.HomePageObject;
import pageObjects.user.ProductPageObject;

public class Sort_Display_Paging extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private ProductPageObject productPage;

	@Parameters({ "browser", "userType", "environment" })
	@BeforeTest
	public void beforeTest(String browserName, String userType, String environmentName) {
		driver = getBrowserDriver(browserName, userType, environmentName);
		homePage = PageGeneratorManager.openHomePage(driver);
	}

	@Test
	public void TC_01_Name_A_To_Z() {
		log.info("Sort By Name: A to Z - Step 01: Click 'Notebooks' sub-menu");
		productPage = (ProductPageObject) homePage.clickToDynamicHeaderSubMenuLink(driver, "Computers", "Notebooks");

		log.info("Sort By Name: A to Z - Step 02: Verify category title is displayed with value 'Notebooks'");
		verifyEquals(productPage.getCategoryTitle(), "Notebooks");

		log.info("Sort By Name: A to Z - Step 03: Select the 'Sort by' dropdown with value 'Name: A to Z'");
		productPage.selectSortByDropdown("Name: A to Z");
		sleepInSecond(3);

		log.info("Sort By Name: A to Z - Step 04: Verify the product list is sorted by name in alphabetical order (A to Z)");
		verifyTrue(productPage.isProductListSortedByNameAToZ());
	}

	@Test
	public void TC_02_Name_Z_To_A() {
		log.info("Sort By Name: Z to A - Step 01: Select the 'Sort by' dropdown with value 'Name: Z to A'");
		productPage.selectSortByDropdown("Name: Z to A");
		sleepInSecond(3);

		log.info("Sort By Name: Z to A - Step 02: Verify the product list is sorted by name in alphabetical order (Z to A)");
		verifyTrue(productPage.isProductListSortedByNameZToA());
	}

	@Test
	public void TC_03_Price_Low_To_High() {
		log.info("Sort By Price: Low to high - Step 01: Select the 'Sort by' dropdown with value 'Price: Low to High'");
		productPage.selectSortByDropdown("Price: Low to High");
		sleepInSecond(3);

		log.info("Sort By Name: Low to high - Step 02: Verify the product list is sorted by price in ascending order (Low to high)");
		verifyTrue(productPage.isProductPriceSortedByPriceLowToHigh());
	}

	@Test
	public void TC_04_Price_High_To_Low() {
		log.info("Sort By Price: High to low - Step 01: Select the 'Sort by' dropdown with value 'Price: High to Low'");
		productPage.selectSortByDropdown("Price: High to Low");
		sleepInSecond(3);

		log.info("Sort By Price: High to low - Step 02: Verify the product list is sorted by price in descending order (High to low)");
		verifyTrue(productPage.isProductPriceSortedByPriceHighToLow());
	}

	@Test
	public void TC_05_Display_3_Products_Per_Page() {
		log.info("Display 3 products per page - Step 01: Select 'Display' with value '3'");
		productPage.selectDisplayDropdown("3");
		sleepInSecond(3);

		log.info("Display 3 products per page - Step 02: Verify display equal to or less than 3 products");
		verifyTrue(productPage.isProductSizeLessThanOrEqual(3));

		log.info("Display 3 products per page - Step 03: Click 'Page' number with value '2'");
		productPage.clickToPageNumber("2");

		log.info("Display 3 products per page - Step 04: Verify 'Previous' icon is displayed");
		verifyTrue(productPage.isPreviousIconDisplayed());

		log.info("Display 3 products per page - Step 05: Click 'Page' number with value '1'");
		productPage.clickToPageNumber("1");

		log.info("Display 3 products per page - Step 06: Verify 'Next' icon is displayed");
		verifyTrue(productPage.isNextIconDisplayed());
	}

	@Test
	public void TC_06_Display_6_Products_Per_Page() {
		log.info("Display 6 products per page - Step 01: Select 'Display' with value '6'");
		productPage.selectDisplayDropdown("6");
		sleepInSecond(3);

		log.info("Display 6 products per page - Step 02: Verify display equal to or less than 6 products");
		verifyTrue(productPage.isProductSizeLessThanOrEqual(6));

		log.info("Display 6 products per page - Step 03: Verify 'Paging' icon is not displayed");
		verifyTrue(productPage.isPagingIconUndisplayed());
	}

	@Test
	public void TC_07_Display_9_Products_Per_Page() {
		log.info("Display 9 products per page - Step 01: Select 'Display' with value '9'");
		productPage.selectDisplayDropdown("9");
		sleepInSecond(3);

		log.info("Display 9 products per page - Step 02: Verify display equal to or less than 9 products");
		verifyTrue(productPage.isProductSizeLessThanOrEqual(9));

		log.info("Display 9 products per page - Step 03: Verify 'Paging' icon is not displayed");
		verifyTrue(productPage.isPagingIconUndisplayed());
	}

	@AfterTest
	public void afterTest() {
		closeBrowserDriver();
	}

}
