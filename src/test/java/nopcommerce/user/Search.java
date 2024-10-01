package nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.SearchPageObject;
import utilities.DataHelper;

public class Search extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private SearchPageObject searchPage;
	private String firstName, lastName, emailAddress, password;

	@Parameters({ "browser", "userType", "environment" })
	@BeforeTest
	public void beforeTest(String browserName, String userType, String environmentName) {
		driver = getBrowserDriver(browserName, userType, environmentName);
		homePage = PageGeneratorManager.openHomePage(driver);

		firstName = DataHelper.getDataHelper().getFirstName();
		lastName = DataHelper.getDataHelper().getLastName();
		emailAddress = DataHelper.getDataHelper().getEmailAddress();
		password = DataHelper.getDataHelper().getPassword();

		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickRegisterButton();
		verifyEquals(registerPage.getSuccessRegistrationMessage(), "Your registration completed");
		homePage = registerPage.clickContinueButton();
		verifyTrue(homePage.isMyAccountLinkDisplayed());
		loginPage = homePage.clickLoginLink();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();

	}

	@Test
	public void TC_01_Empty_Data() {
		log.info("Search with empty data - Step 01: Navigate to the 'Search' page");
		searchPage = (SearchPageObject) homePage.clickToDynamicFooterLink(driver, "Search");

		log.info("Search with empty data - Step 02: Leave the 'Search key word' field blank.");
		searchPage.inputToSearchKeyWordTextbox("");

		log.info("Search with empty data - Step 03: Click 'Search' button");
		searchPage.clickSearchButton();

		log.info("Search with empty data - Step 04: Verify that the warning message is displayed");
		Assert.assertEquals(searchPage.getWarningMessage(), "Search term minimum length is 3 characters");
	}

	@Test
	public void TC_02_Data_Not_Exist() {
		log.info("Search with data not exist - Step 01: Input non-existent data into the 'Search key word' textbox.");
		searchPage.inputToSearchKeyWordTextbox("Macbook Pro 2050");

		log.info("Search with data not exist - Step 02: Click 'Search' button");
		searchPage.clickSearchButton();

		log.info("Search with data not exist - Step 03: Verify that the result message is displayed");
		Assert.assertEquals(searchPage.getNoResultMessage(), "No products were found that matched your criteria.");
	}

	@Test
	public void TC_03_Product_Name_Relative() {
		log.info("Relative search by product name - Step 01: Input into 'Search key word' textbox with the value 'Lenovo'");
		searchPage.inputToSearchKeyWordTextbox("Lenovo");

		log.info("Relative search by product name - Step 02: Click 'Search' button");
		searchPage.clickSearchButton();

		log.info("Relative search by product name - Step 03: Verify that product title contains the value 'Lenovo'");
		Assert.assertTrue(searchPage.isProductTitleDisplayedContain("Lenovo"));
	}

	@Test
	public void TC_04_Product_Name_Absolute() {
		log.info("Absolute search by product name - Step 01: Input into 'Search key word' textbox with the value 'Thinkpad X1 Carbon'");
		searchPage.inputToSearchKeyWordTextbox("Thinkpad X1 Carbon");

		log.info("Absolute search by product name - Step 02: Click the 'Search' button");
		searchPage.clickSearchButton();

		log.info("Absolute search by product name - Step 03: Verify that the product title contains the value 'Thinkpad X1 Carbon'");
		Assert.assertTrue(searchPage.isProductTitleDisplayedContain("Thinkpad X1 Carbon"));
	}

	@Test
	public void TC_05_Parent_Categories() {
		log.info("Search by parent category - Step 01: Input 'Apple MacBook Pro' into the 'Search key word' textbox.");
		searchPage.inputToSearchKeyWordTextbox("Apple MacBook Pro");

		log.info("Search by parent category - Step 02: Check the 'Advanced search' checkbox");
		searchPage.checkToAdvancedSearchCheckbox();

		log.info("Search by parent category - Step 03: Select the 'Category' dropdown where the value is 'Computers'");
		searchPage.selectCategoryDropdown("Computers");

		log.info("Search by parent category - Step 04: Uncheck the 'Search sub category' checkbox");
		searchPage.uncheckToSearchSubCategoryCheckbox();

		log.info("Search by parent category - Step 05: Click the 'Seach' button");
		searchPage.clickSearchButton();

		log.info("Search by parent category - Step 06: Verify that the 'No result message' is displayed");
		verifyEquals(searchPage.getNoResultMessage(), "No products were found that matched your criteria.");
	}

	@Test
	public void TC_06_Sub_Categories() {
		log.info("Search by sub category - Step 01: Enter 'Apple MacBook Pro' into the 'Search key word' textbox");
		searchPage.inputToSearchKeyWordTextbox("Apple MacBook Pro");

		log.info("Search by sub category - Step 02: Check the 'Advanced search' checkbox");
		searchPage.checkToAdvancedSearchCheckbox();

		log.info("Search by sub category - Step 03: Select the 'Category' dropdown with the value 'Computers'");
		searchPage.selectCategoryDropdown("Computers");

		log.info("Search by sub category - Step 04: Check the 'Search sub category' checkbox");
		searchPage.checkToSearchSubCategoryCheckbox();

		log.info("Search by sub category - Step 05: Click the 'Search' button");
		searchPage.clickSearchButton();

		log.info("Search by sub category - Step 06: Verify that the product title contains the value 'Apple MacBook Pro 13-inch'");
		verifyTrue(searchPage.isProductTitleDisplayedContain("Apple MacBook Pro 13-inch"));
	}

	@Test
	public void TC_07_Incorrect_Manufacturer() {
		log.info("Search with incorrect manufacturer - Step 01: Enter 'Apple MacBook Pro' into the 'Search key word' textbox");
		searchPage.inputToSearchKeyWordTextbox("Apple MacBook Pro");

		log.info("Search with incorrect manufacturer - Step 02: Check the 'Advanced search' checkbox");
		searchPage.checkToAdvancedSearchCheckbox();

		log.info("Search with incorrect manufacturer - Step 03: Select the 'Category' dropdown with the value 'Computers'");
		searchPage.selectCategoryDropdown("Computers");

		log.info("Search with incorrect manufacturer - Step 04: Check the 'Search sub category' checkbox");
		searchPage.checkToSearchSubCategoryCheckbox();

		log.info("Search with incorrect manufacturer - Step 05: Select the 'Manufacturer' dropdown with the value 'HP'");
		searchPage.selectManufacturerDropdown("HP");

		log.info("Search with incorrect manufacturer - Step 06: Click the 'Search' button");
		searchPage.clickSearchButton();

		log.info("Search with incorrect manufacturer - Step 07: Verify that 'No result' message is displayed");
		verifyEquals(searchPage.getNoResultMessage(), "No products were found that matched your criteria.");
	}

	@Test
	public void TC_08_Correct_Manufacturer() {
		log.info("Search with correct manufacturer - Step 01: Enter 'Apple MacBook Pro' into the 'Search key word' textbox");
		searchPage.inputToSearchKeyWordTextbox("Apple MacBook Pro");

		log.info("Search with correct manufacturer - Step 02: Check the 'Advanced search' checkbox");
		searchPage.checkToAdvancedSearchCheckbox();

		log.info("Search with correct manufacturer - Step 03: Select the 'Category' dropdown with the value 'Computers'");
		searchPage.selectCategoryDropdown("Computers");

		log.info("Search with correct manufacturer - Step 04: Check the 'Search sub category' checkbox");
		searchPage.checkToSearchSubCategoryCheckbox();

		log.info("Search with incorrect manufacturer - Step 05: Select the 'Manufacturer' dropdown with the value 'Apple'");
		searchPage.selectManufacturerDropdown("Apple");

		log.info("Search with incorrect manufacturer - Step 06: Click the 'Search' button");
		searchPage.clickSearchButton();

		log.info("Search with incorrect manufacturer - Step 07: Verify that 'No result' message is displayed");
		verifyTrue(searchPage.isProductTitleDisplayedContain("Apple MacBook Pro 13-inch"));
	}

	@AfterTest
	public void afterTest() {
		closeBrowserDriver();
	}

}
