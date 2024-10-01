package nopcommerce.admin;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.admin.AdminProductPageObject;
import pageObjects.user.LoginPageObject;
import reportConfig.ExtentTestManager;
import com.aventstack.extentreports.Status;

public class Product extends BaseTest {
	WebDriver driver;
	AdminLoginPageObject adminLoginPage;
	AdminDashboardPageObject adminDashboardPage;
	LoginPageObject loginPage;
	AdminProductPageObject adminProductPage;
	String productName, category, manufacturer;

	@Parameters({ "browser", "userType", "environment" })
	@BeforeTest
	public void beforeTest(String browserName, String userType, String environmentName) {
		driver = getBrowserDriver(browserName, userType, environmentName);
		loginPage = PageGeneratorManager.openLoginPage(driver);

		loginPage.inputToEmailTextbox("automationtesting@gmail.com");
		loginPage.inputToPasswordTextbox("automationtesting@gmail.com");
		loginPage.clickLoginButton();
		adminDashboardPage = PageGeneratorManager.openAdminDashboardPage(driver);
		adminDashboardPage.openAdminDynamicPageOnSideBarMenu(driver, "Catalog", "Products");
		adminProductPage = PageGeneratorManager.openAdminProductPage(driver);
		sleepInSecond(5);

		productName = "Lenovo IdeaCentre";
		category = "Computers";
		manufacturer = "Apple";
	}

	@Test
	public void TC_01_Search_With_Product_Name(Method method) {
		ExtentTestManager.getTest().log(Status.INFO, "Search with product name - Step 01: Input to 'Product name' textbox with value '" + productName + "'");
		adminProductPage.inputToDynamicTextbox("ProductName", productName);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name - Step 02: Click 'Search' button");
		adminProductPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name - Step 03: Verify product info is displayed");
		verifyEquals(adminProductPage.getDynamicCellValueInTable("Product name"), productName);
		verifyEquals(adminProductPage.getDynamicCellValueInTable("SKU"), "LE_IC_600");
		verifyEquals(adminProductPage.getDynamicCellValueInTable("Price"), "500");
		verifyEquals(adminProductPage.getDynamicCellValueInTable("Stock quantity"), "10000");
		verifyTrue(adminProductPage.isPublishedIconDisplayed("Published"));
		sleepInSecond(5);
	}

	@Test
	public void TC_02_Search_With_Product_Name_Parent_Category_Unchecked(Method method) {
		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and parent category without checking 'Search categories' checkbox - Step 01: Input to 'Product name' textbox with value: '" + productName + "'");
		adminProductPage.inputToDynamicTextbox("ProductName", productName);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and parent category without checking 'Search categories' checkbox - Step 02: Select 'Category' dropdown with option: '" + category + "'");
		adminProductPage.selectDynamicDropdownByLabelName("Category", category);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and parent category without checking 'Search categories' checkbox - Step 03: Uncheck 'Search Categories' checkbox");
		adminProductPage.uncheckSearchCategoriesCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and parent category without checking 'Search categories' checkbox - Step 04: Click 'Search' button");
		adminProductPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and parent category without checking 'Search categories' checkbox - Step 05: Verify 'No data available message' is displayed");
		verifyTrue(adminProductPage.isNoDataAvailableMessageDisplayed());
		sleepInSecond(5);
	}

	@Test
	public void TC_03_Search_With_Product_Name_Parent_Category_Checked(Method method) {
		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and parent category with checking 'Search categories' checkbox - Step 01: Input to 'Product name' textbox with value '" + productName + "'");
		adminProductPage.inputToDynamicTextbox("ProductName", productName);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and parent category with checking 'Search categories' checkbox - Step 02: Select 'Category' dropdown with option: '" + category + "'");
		adminProductPage.selectDynamicDropdownByLabelName("Category", category);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and parent category with checking 'Search categories' checkbox - Step 03: Check 'Search categories' checkbox");
		adminProductPage.checkSearchCategoriesCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and parent category with checking 'Search categories' checkbox - Step 04: Click 'Search' button");
		adminProductPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and parent category with checking 'Search categories' checkbox - Step 05: Verify product info is displayed");
		verifyEquals(adminProductPage.getDynamicCellValueInTable("Product name"), productName);
		verifyEquals(adminProductPage.getDynamicCellValueInTable("SKU"), "LE_IC_600");
		verifyEquals(adminProductPage.getDynamicCellValueInTable("Price"), "500");
		verifyEquals(adminProductPage.getDynamicCellValueInTable("Stock quantity"), "10000");
		verifyTrue(adminProductPage.isPublishedIconDisplayed("Published"));
		sleepInSecond(5);
	}

	@Test
	public void TC_04_Search_With_Product_Name_Child_Category(Method method) {
		category = "Computers >> Desktops";
		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and child category - Step 01: Input to 'Product name' textbox with value '" + productName + "'");
		adminProductPage.inputToDynamicTextbox("ProductName", productName);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and child category - Step 02: Select 'Category' dropdown with option '" + category + "'");
		adminProductPage.selectDynamicDropdownByLabelName("Category", category);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and child category - Step 03: Uncheck 'Search categories' checkbox");
		adminProductPage.uncheckSearchCategoriesCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and child category - Step 04: Click 'Search' button");
		adminProductPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and child category - Step 05: Verify product info is displayed");
		verifyEquals(adminProductPage.getDynamicCellValueInTable("Product name"), productName);
		verifyEquals(adminProductPage.getDynamicCellValueInTable("SKU"), "LE_IC_600");
		verifyEquals(adminProductPage.getDynamicCellValueInTable("Price"), "500");
		verifyEquals(adminProductPage.getDynamicCellValueInTable("Stock quantity"), "10000");
		verifyTrue(adminProductPage.isPublishedIconDisplayed("Published"));
		sleepInSecond(5);
	}

	@Test
	public void TC_05_Search_With_Product_Name_Manufacturer(Method method) {
		category = "All";
		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and manufacturer - Step 01: Input to 'Product name' textbox with value '" + productName + "'");
		adminProductPage.inputToDynamicTextbox("ProductName", productName);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and manufacturer - Step 02: Select 'Category' dropdown with option: '" + category + "'");
		adminProductPage.selectDynamicDropdownByLabelName("Category", category);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and manufacturer - Step 03: Uncheck 'Search categories' checkbox");
		adminProductPage.uncheckSearchCategoriesCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and manufacturer - Step 04: Select 'Manufacturer' dropdown with option: '" + category + "'");
		adminProductPage.selectDynamicDropdownByLabelName("Manufacturer", manufacturer);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and manufacturer - Step 05: Click 'Search' button");
		adminProductPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Search with product name and manufacturer - Step 06: Verify 'No data available message' is displayed");
		verifyTrue(adminProductPage.isNoDataAvailableMessageDisplayed());
		sleepInSecond(5);
	}

	@Test
	public void TC_06_Go_Directly_To_Product_SKU(Method method) {
		ExtentTestManager.getTest().log(Status.INFO, "Go directly to product SKU - Step 01: Input to 'Go directly to product SKU' textbox with value '" + productName + "'");
		adminProductPage.inputToDynamicTextbox("GoDirectlyToSku", productName);

		ExtentTestManager.getTest().log(Status.INFO, "Go directly to product SKU - Step 02: Click 'Go' button");
		adminProductPage.clickGoButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Go directly to product SKU - Step 03: Verify product name is displayed");
		verifyEquals(adminProductPage.getDynamicCellValueInTable("Product name"), productName);
		sleepInSecond(5);
	}

	@AfterMethod
	public void afterMethod() {
		adminProductPage.refreshCurrentPage(driver);
		sleepInSecond(5);
	}

	@AfterTest
	public void afterTest() {
		closeBrowserDriver();
	}

}
