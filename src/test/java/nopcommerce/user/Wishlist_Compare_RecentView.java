package nopcommerce.user;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.ComparisonPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.ProductDetailPageObject;
import pageObjects.user.ProductPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.ShoppingCartPageObject;
import pageObjects.user.WishlistPageObject;
import utilities.DataHelper;

public class Wishlist_Compare_RecentView extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private ProductPageObject productPage;
	private WishlistPageObject wishlistPage;
	private ProductDetailPageObject productDetailPage;
	private ShoppingCartPageObject shoppingCartPage;
	private ComparisonPageObject comparisonPage;
	private String firstName, lastName, emailAddress, password;
	private String productName, productSKU, productPrice;
	private Map<String, String> product1, product2;

	@Parameters({ "browser", "userType", "environment" })
	@BeforeTest
	public void beforeTest(String browserName, String userType, String environmentName) {
		driver = getBrowserDriver(browserName, userType, environmentName);
		homePage = PageGeneratorManager.openHomePage(driver);

		// firstName = DataHelper.getDataHelper().getFirstName();
		// lastName = DataHelper.getDataHelper().getLastName();
		// emailAddress = DataHelper.getDataHelper().getEmailAddress();
		// password = DataHelper.getDataHelper().getPassword();
		// productName = "Asus N551JK-XO076H Laptop";
		//
		// registerPage = homePage.clickToRegisterLink();
		// registerPage.inputToFirstNameTextbox(firstName);
		// registerPage.inputToLastNameTextbox(lastName);
		// registerPage.inputToEmailTextbox(emailAddress);
		// registerPage.inputToPasswordTextbox(password);
		// registerPage.inputToConfirmPasswordTextbox(password);
		// registerPage.clickRegisterButton();
		// verifyEquals(registerPage.getSuccessRegistrationMessage(), "Your registration completed");
		// homePage = registerPage.clickContinueButton();
		// verifyTrue(homePage.isMyAccountLinkDisplayed());
		// homePage.clickLogoutLink();
		// loginPage = homePage.clickLoginLink();
		// loginPage.inputToEmailTextbox(emailAddress);
		// loginPage.inputToPasswordTextbox(password);
		// homePage = loginPage.clickLoginButton();
		// verifyTrue(homePage.isMyAccountLinkDisplayed());
	}

	// @Test
	public void TC_01_Add_To_Wishlist() {
		productPage = (ProductPageObject) homePage.clickToDynamicHeaderSubMenuLink(driver, "Computers", "Notebooks");
		productDetailPage = productPage.clickProductLink(productName);
		verifyEquals(productDetailPage.getProductName(), productName);
		productSKU = productDetailPage.getProductSKU();
		productPrice = productDetailPage.getProductPrice();
		productDetailPage.clickAddToWishlistButton();
		verifyTrue(productDetailPage.isAddToWishlistSuccessMessageDisplayed("The product has been added to your wishlist"));
		wishlistPage = productDetailPage.clickWishlistLinkInMessage();
		verifyEquals(wishlistPage.getProductSKUValue(productName), productSKU);
		verifyEquals(wishlistPage.getProductPriceValue(productName), productPrice);
		verifyEquals(wishlistPage.getProductQuantityValue(productName), "1");
		verifyTrue(wishlistPage.isTotalValueCorrect(productName));
	}

	// @Test
	public void TC_02_Remove_Product_From_Wishlist() {
		wishlistPage.clickRemoveAllProduct();
		verifyTrue(wishlistPage.getNoDataMessage().contains("The wishlist is empty!"));
		verifyTrue(wishlistPage.isProductUndisplayed());
	}

	// @Test
	public void TC_03_Add_Product_To_Cart_From_Wishlist() {
		productPage = (ProductPageObject) wishlistPage.clickToDynamicHeaderSubMenuLink(driver, "Computers", "Notebooks");
		productDetailPage = productPage.clickProductLink("Apple MacBook Pro 13-inch");
		productDetailPage.clickAddToWishlistButton();
		sleepInSecond(6);

		productPage = (ProductPageObject) productDetailPage.clickToDynamicHeaderSubMenuLink(driver, "Computers", "Notebooks");
		productDetailPage = productPage.clickProductLink("HP Envy 6-1180ca 15.6-Inch Sleekbook");
		productDetailPage.clickAddToWishlistButton();
		sleepInSecond(6);

		productPage = (ProductPageObject) productDetailPage.clickToDynamicHeaderSubMenuLink(driver, "Computers", "Notebooks");
		productDetailPage = productPage.clickProductLink("HP Spectre XT Pro UltraBook");
		productDetailPage.clickAddToWishlistButton();
		wishlistPage = productDetailPage.clickWishlistLinkInMessage();

		wishlistPage.selectAddToCartCheckbox(productName);
		wishlistPage.selectAddToCartCheckbox("HP Spectre XT Pro UltraBook");
		shoppingCartPage = wishlistPage.clickAddToCartButton();

		verifyTrue(shoppingCartPage.isProductNumberDecreasedAtWishlistLink());
		verifyTrue(shoppingCartPage.isProductNumberAddedToShoppingCartLink());

		// verifyEquals(shoppingCartPage.getProductSKUValue(productName), productSKU);
		// verifyEquals(shoppingCartPage.getProductPriceValue(productName), productPrice);
		// verifyEquals(shoppingCartPage.getProductQuantityValue(productName), "1");
		// verifyTrue(shoppingCartPage.isTotalValueCorrect(productName));
		// verifyTrue(shoppingCartPage.isProductNumberRemovedFromWishlistLink());
		// verifyTrue(shoppingCartPage.isProductNumberAddedToShoppingCartLink());

	}

	@Test
	public void TC_04_Add_Products_To_Compare() {
		productPage = (ProductPageObject) homePage.clickToDynamicHeaderSubMenuLink(driver, "Computers", "Notebooks");
		productPage.clickAddToCompareIcon("Apple MacBook Pro 13-inch");
		sleepInSecond(5);
		productPage.addProductInfoToProductsMap(product1, "Apple MacBook Pro 13-inch");
		sleepInSecond(5);
		verifyTrue(productPage.isAddedToComparisonMessageDisplayed());

		// productPage = (ProductPageObject) shoppingCartPage.clickToDynamicHeaderSubMenuLink(driver, "Computers", "Notebooks");
		productPage.clickAddToCompareIcon("Asus N551JK-XO076H Laptop");
		productPage.addProductInfoToProductsMap(product2, "Asus N551JK-XO076H Laptop");
		sleepInSecond(5);
		verifyTrue(productPage.isAddedToComparisonMessageDisplayed());

		comparisonPage = (ComparisonPageObject) productPage.clickToDynamicFooterLink(driver, "Compare products list");
		// verifyTrue(comparisonPage.isProductNameDisplayedCorrectly());
		// verifyTrue(comparisonPage.isProductPriceDislayedCorrectly());
	}

	@Test
	public void TC_05_View_Recently_Viewed_Product() {

	}

	@AfterTest
	public void afterTest() {

	}
}
