package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.admin.AdminAddressDetailPageObject;
import pageObjects.admin.AdminCustomerListPageObject;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.admin.AdminNewCustomerPageObject;
import pageObjects.admin.AdminProductPageObject;
import pageObjects.user.AddressesPageObject;
import pageObjects.user.ChangePasswordPageObject;
import pageObjects.user.CheckoutPageObject;
import pageObjects.user.ComparisonPageObject;
import pageObjects.user.CustomerInfoPageObject;
import pageObjects.user.DownloadableProductsPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.OrderDetailPageObject;
import pageObjects.user.OrdersPageObject;
import pageObjects.user.ProductDetailPageObject;
import pageObjects.user.ProductPageObject;
import pageObjects.user.ProductReviewsPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.RewardPointsPageObject;
import pageObjects.user.SearchPageObject;
import pageObjects.user.ShoppingCartPageObject;
import pageObjects.user.SubscriptionsPageObject;
import pageObjects.user.WishlistPageObject;

public class PageGeneratorManager {

	public static HomePageObject openHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static RegisterPageObject openRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static LoginPageObject openLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static CustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		return new CustomerInfoPageObject(driver);
	}

	public static AddressesPageObject openAddressesPage(WebDriver driver) {
		return new AddressesPageObject(driver);
	}

	public static OrdersPageObject openOrdersPage(WebDriver driver) {
		return new OrdersPageObject(driver);
	}

	public static DownloadableProductsPageObject openDownloadableProductsPage(WebDriver driver) {
		return new DownloadableProductsPageObject(driver);
	}

	public static SubscriptionsPageObject openSubscriptionsPage(WebDriver driver) {
		return new SubscriptionsPageObject(driver);
	}

	public static RewardPointsPageObject openRewardPointsPage(WebDriver driver) {
		return new RewardPointsPageObject(driver);
	}

	public static ChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
		return new ChangePasswordPageObject(driver);
	}

	public static SearchPageObject openSearchPage(WebDriver driver) {
		return new SearchPageObject(driver);
	}

	public static ProductReviewsPageObject openProductReviewsPage(WebDriver driver) {
		return new ProductReviewsPageObject(driver);
	}

	public static ProductPageObject openProductPage(WebDriver driver) {
		return new ProductPageObject(driver);
	}

	public static ProductDetailPageObject openProductDetailPage(WebDriver driver) {
		return new ProductDetailPageObject(driver);
	}

	public static WishlistPageObject openWishlistPage(WebDriver driver) {
		return new WishlistPageObject(driver);
	}

	public static ShoppingCartPageObject openShoppingCartPage(WebDriver driver) {
		return new ShoppingCartPageObject(driver);
	}

	public static ComparisonPageObject openComparisonPage(WebDriver driver) {
		return new ComparisonPageObject(driver);
	}

	public static CheckoutPageObject openCheckoutPage(WebDriver driver) {
		return new CheckoutPageObject(driver);
	}

	public static OrderDetailPageObject openOrderDetailPage(WebDriver driver) {
		return new OrderDetailPageObject(driver);
	}

	public static AdminDashboardPageObject openAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

	public static AdminLoginPageObject openAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static AdminProductPageObject openAdminProductPage(WebDriver driver) {
		return new AdminProductPageObject(driver);
	}

	public static AdminCustomerListPageObject openAdminCustomerListPage(WebDriver driver) {
		return new AdminCustomerListPageObject(driver);
	}

	public static AdminNewCustomerPageObject openNewAdminCustomerPage(WebDriver driver) {
		return new AdminNewCustomerPageObject(driver);
	}

	public static AdminAddressDetailPageObject openAddressDetailPage(WebDriver driver) {
		return new AdminAddressDetailPageObject(driver);
	}
}
