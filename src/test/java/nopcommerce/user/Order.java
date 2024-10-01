package nopcommerce.user;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.CheckoutPageObject;
import pageObjects.user.CustomerInfoPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.OrderDetailPageObject;
import pageObjects.user.OrdersPageObject;
import pageObjects.user.ProductDetailPageObject;
import pageObjects.user.ProductPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.ShoppingCartPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Order extends BaseTest {
	WebDriver driver;
	String firstName, lastName, emailAddress, password;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	LoginPageObject loginPage;
	ProductPageObject productPage;
	ProductDetailPageObject productDetailPage;
	ShoppingCartPageObject shoppingCartPage;
	CheckoutPageObject checkoutPage;
	CustomerInfoPageObject customerInfoPage;
	OrdersPageObject orderPage;
	OrderDetailPageObject orderDetailPage;
	String productName, processorName, ramName, hddName, osName, softwareName1, softwareName2, softwareName3, productPrice, quantity;

	String baFirstName, baLastName, baEmail, baCountry, baState, baCity, baAddress1, baZipCode, baPhoneNumber;
	String saFirstName, saLastName, saEmail, saCountry, saState, saCity, saAddress1, saZipCode, saPhoneNumber;
	String subTotal, total, rewardPoint, giftWrapping;
	String paymentMethod, shippingMethod, shippingFee;
	List<String> inputtedBillingAddressInfo, inputtedShippingAddressInfo, displayedBillingAddressInfo, displayedShippingAddressInfo;
	ArrayList<Map<String, String>> productListAtShoppingCart, productListAtConfirmOrder, productListAtOrderDetail;
	String creditCardType, cardHolderName, creditCardNumber, expirationMonth, expirationYear, cardCode;

	String orderNumber;
	String orderDate, orderStatus, orderTotal;

	@Parameters({ "browser", "userType", "environment" })
	@BeforeTest
	public void beforeTest(String browserName, String userType, String environmentName) {
		driver = getBrowserDriver(browserName, userType, environmentName);
		homePage = PageGeneratorManager.openHomePage(driver);

		firstName = DataHelper.getDataHelper().getFirstName();
		lastName = DataHelper.getDataHelper().getLastName();
		emailAddress = DataHelper.getDataHelper().getEmailAddress();
		password = DataHelper.getDataHelper().getPassword();

		productName = "Build your own computer";
		processorName = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		ramName = "4GB [+$20.00]";
		hddName = "320 GB";
		osName = "Vista Premium [+$60.00]";
		softwareName1 = "Microsoft Office [+$50.00]";
		softwareName2 = "Acrobat Reader [+$10.00]";
		softwareName3 = "Total Commander [+$5.00]";
		quantity = "1";

		baFirstName = "Na";
		baLastName = "Nguyen";
		baEmail = "nana123@gmail.com";
		baCity = "Hà Nội";
		baState = "Hà Nội";
		baCountry = "Vietnam";
		baAddress1 = "123 PVT";
		baZipCode = "70000";
		baPhoneNumber = "0987878675";

		saFirstName = "Van";
		saLastName = "Le";
		saEmail = "vanle456@gmail.com";
		saCity = "Alaska";
		saState = "Alaska";
		saCountry = "United States of America";
		saAddress1 = "678 HC";
		saZipCode = "10000";
		saPhoneNumber = "035645789";

		paymentMethod = "Check / Money Order";
		shippingMethod = "Ground";

		creditCardType = "Visa";
		creditCardNumber = "4929672692797182";
		cardHolderName = DataHelper.getDataHelper().getCardHolderName();
		expirationMonth = "08";
		expirationYear = "2026";
		cardCode = "583";

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
		homePage.clickLogoutLink();
		loginPage = homePage.clickLoginLink();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();
		verifyTrue(homePage.isMyAccountLinkDisplayed());

	}

	@Test
	public void TC_01_Add_Product_To_Cart(Method method) {
		ExtentTestManager.startTest(method.getName(), "");
		ExtentTestManager.getTest().log(Status.INFO, "");
		productPage = (ProductPageObject) homePage.clickToDynamicHeaderSubMenuLink(driver, "Computer", "Desktops");

		ExtentTestManager.getTest().log(Status.INFO, "");
		productDetailPage = productPage.clickProductLink(productName);

		ExtentTestManager.getTest().log(Status.INFO, "");
		productDetailPage.selectAtProcessorDropdown(processorName);

		ExtentTestManager.getTest().log(Status.INFO, "");
		productDetailPage.selectAtRAMDropdown(ramName);

		ExtentTestManager.getTest().log(Status.INFO, "");
		productDetailPage.selectHDDRadioButton(hddName);

		ExtentTestManager.getTest().log(Status.INFO, "");
		productDetailPage.selectOSRadioButton(osName);

		ExtentTestManager.getTest().log(Status.INFO, "");
		productDetailPage.selectSoftwareCheckbox(softwareName1);

		ExtentTestManager.getTest().log(Status.INFO, "");
		productDetailPage.selectSoftwareCheckbox(softwareName2);

		ExtentTestManager.getTest().log(Status.INFO, "");
		productDetailPage.selectSoftwareCheckbox(softwareName3);

		ExtentTestManager.getTest().log(Status.INFO, "");
		productDetailPage.inputToQuantityTextbox(quantity);

		ExtentTestManager.getTest().log(Status.INFO, "");
		productDetailPage.clickAddToCartOrUpdateButton();

		ExtentTestManager.getTest().log(Status.INFO, "");
		verifyTrue(productDetailPage.isResultMessageDisplayed("The product has been added to your shopping cart"));
		sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "");
		List<String> productInfoAtProductDetailPage, productInfoAtShoppingCartLink;
		productInfoAtProductDetailPage = productDetailPage.saveProductInfoToList(processorName, ramName, hddName, osName, softwareName1, softwareName2, softwareName3);
		productPrice = productDetailPage.getProductPrice();

		ExtentTestManager.getTest().log(Status.INFO, "");
		productDetailPage.hoverMouseToShoppingCartLink();
		productInfoAtShoppingCartLink = productDetailPage.getProductInfoAtShoppingCartLinkIntoList();

		verifyEquals(productDetailPage.getCountItemMessageAtShoppingCartLink(), "There are " + quantity + " item(s) in your cart.");
		verifyEquals(productDetailPage.getQuantityDiplayedAtShoppingCartLink(), quantity);
		verifyEquals(productInfoAtProductDetailPage, productInfoAtShoppingCartLink);
		verifyEquals(productDetailPage.getProductPriceFromShoppingCartLink(), productPrice);
		verifyEquals(productDetailPage.getProductQuantityFromShoppingCartLink(), quantity);
		verifyTrue(productDetailPage.isSubTotalCorrect());

	}

	// @Test
	public void TC_02_Edit_Product_In_Cart() {
		shoppingCartPage = productDetailPage.clickShoppingCartLink();
		productDetailPage = shoppingCartPage.clickEditButton(productName);

		processorName = "2.2 GHz Intel Pentium Dual-Core E2200";
		ramName = "8GB [+$60.00]";
		hddName = "400 GB [+$100.00]";
		osName = "Vista Home [+$50.00]";
		quantity = "2";

		productDetailPage.selectAtProcessorDropdown(processorName);
		productDetailPage.selectAtRAMDropdown(ramName);
		productDetailPage.selectHDDRadioButton(hddName);
		productDetailPage.selectOSRadioButton(osName);
		productDetailPage.unselectSoftwareCheckbox(softwareName2);
		productDetailPage.unselectSoftwareCheckbox(softwareName3);
		productDetailPage.inputToQuantityTextbox(quantity);

		productDetailPage.clickAddToCartOrUpdateButton();
		verifyTrue(productDetailPage.isResultMessageDisplayed("The product has been added to your shopping cart"));
		productDetailPage.closeResultMessageBar();
		List<String> productInfoAtProductDetailPage, productInfoAtShoppingCartLink;
		productInfoAtProductDetailPage = productDetailPage.saveProductInfoToList(processorName, ramName, hddName, osName, softwareName1);
		productPrice = productDetailPage.getProductPrice();

		productDetailPage.hoverMouseToShoppingCartLink();
		sleepInSecond(5);
		productInfoAtShoppingCartLink = productDetailPage.getProductInfoAtShoppingCartLinkIntoList();

		verifyEquals(productDetailPage.getCountItemMessageAtShoppingCartLink(), "There are " + quantity + " item(s) in your cart.");
		verifyEquals(productDetailPage.getQuantityDiplayedAtShoppingCartLink(), quantity);
		verifyEquals(productInfoAtProductDetailPage, productInfoAtShoppingCartLink);
		verifyEquals(productDetailPage.getProductPriceFromShoppingCartLink(), productPrice);
		verifyEquals(productDetailPage.getProductQuantityFromShoppingCartLink(), quantity);
		verifyTrue(productDetailPage.isSubTotalCorrect());
	}

	// @Test
	public void TC_03_Remove_Product_From_Cart() {
		shoppingCartPage = productDetailPage.clickShoppingCartLink();
		shoppingCartPage.clickRemoveIcon(productName);
		verifyEquals(shoppingCartPage.getMessageAtShoppingCartPage(), "Your Shopping Cart is empty!");
		shoppingCartPage.hoverMouseToShoppingCartLink();
		verifyEquals(shoppingCartPage.getMessageAtShoppingCartLink(), "You have no items in your shopping cart.");
	}

	@Test
	public void TC_04_Update_Cart() {
		productName = "Lenovo Thinkpad Carbon Laptop";
		productPage = (ProductPageObject) productDetailPage.clickToDynamicHeaderSubMenuLink(driver, "Computers", "Notebooks");
		sleepInSecond(3);
		productDetailPage = productPage.clickProductLink(productName);
		productDetailPage.inputToQuantityTextbox("5");
		productDetailPage.clickAddToCartOrUpdateButton();
		sleepInSecond(5);
		// shoppingCartPage = productDetailPage.clickShoppingCartLink();
		// productDetailPage = shoppingCartPage.clickEditButton(productName);
		productDetailPage.hoverMouseToShoppingCartLink();
		// verifyTrue(productDetailPage.isSubTotalCorrect());
		homePage = productDetailPage.returnToHomePage(driver);
	}

	@Test
	public void TC_05_Checkout_Order_By_Cheque() {
		productPage = (ProductPageObject) homePage.clickToDynamicHeaderSubMenuLink(driver, "Computers", "Notebooks");
		productDetailPage = productPage.clickProductLink("Apple MacBook Pro");
		productDetailPage.clickAddToCartOrUpdateButton();
		sleepInSecond(5);
		productDetailPage.clickToDynamicHeaderSubMenuLink(driver, "Computers", "Notebooks");
		productDetailPage = productPage.clickProductLink("Asus Laptop");
		productDetailPage.clickAddToCartOrUpdateButton();
		sleepInSecond(5);
		shoppingCartPage = productDetailPage.clickShoppingCartLink();
		shoppingCartPage.selectGiftWrappingDropdown("Yes [+$10.00]");
		sleepInSecond(5);
		verifyTrue(shoppingCartPage.isSubTotalDisplayedCorrectly());
		verifyTrue(shoppingCartPage.isTotalDisplayedCorrectly());
		verifyTrue(shoppingCartPage.isPointEarnedDisplayedCorrectly());
		subTotal = shoppingCartPage.getDisplayedSubTotalText();
		total = shoppingCartPage.getDisplayedTotalText();
		rewardPoint = shoppingCartPage.getDisplayedRewardPointText();
		giftWrapping = shoppingCartPage.getGiftWrappingText();

		productListAtShoppingCart = shoppingCartPage.saveListOfProductInfo();
		shoppingCartPage.checkAgreeWithTheTermCheckbox();
		checkoutPage = shoppingCartPage.clickCheckoutButton();

		checkoutPage.uncheckShipToTheSameAddressCheckbox();
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_FirstName", baFirstName);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_LastName", baLastName);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_Email", baEmail);
		checkoutPage.selectDynamicDropdown("BillingNewAddress_CountryId", baCountry);
		checkoutPage.selectDynamicDropdown("BillingNewAddress_StateProvinceId", baState);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_City", baCity);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_Address1", baAddress1);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_ZipPostalCode", baZipCode);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_PhoneNumber", baPhoneNumber);
		inputtedBillingAddressInfo = checkoutPage.saveInfoToList(baFirstName + " " + baLastName, baEmail, baPhoneNumber, baCountry, baCity, baAddress1, baZipCode);
		checkoutPage.clickContinueButtonAtBillingAddressSection();

		checkoutPage.selectDynamicDropdown("shipping-address-select", "New Address");
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_FirstName", saFirstName);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_LastName", saLastName);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_Email", saEmail);
		checkoutPage.selectDynamicDropdown("ShippingNewAddress_CountryId", saCountry);
		checkoutPage.selectDynamicDropdown("ShippingNewAddress_StateProvinceId", saState);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_City", saCity);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_Address1", saAddress1);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_ZipPostalCode", saZipCode);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_PhoneNumber", saPhoneNumber);
		inputtedShippingAddressInfo = checkoutPage.saveInfoToList(saFirstName + " " + saLastName, saEmail, saPhoneNumber, saCountry, saCity, saAddress1, saZipCode);
		checkoutPage.clickContinueButtonAtShippingAddressSection();

		checkoutPage.checkToDynamicRadioButton(shippingMethod);
		shippingFee = checkoutPage.getShippingFeeText();
		checkoutPage.clickContinueButtonAtShippingMethodSection();

		checkoutPage.checkToDynamicRadioButton(paymentMethod);
		checkoutPage.clickContinueButtonAtPaymentMethodSection();

		verifyEquals(checkoutPage.getPaymentInformationText(),
				"Mail Personal or Business Check, Cashier's Check or money order to:\n\n" + "NOP SOLUTIONS\n" + "your address here,\n" + "New York, NY 10001\n" + "USA\n"
						+ "Notice that if you pay by Personal or Business Check, your order may be held for up to " + "10 days after we receive your check to allow enough time for the check to clear. "
						+ "If you want us to ship faster upon receipt of your payment, then we recommend your " + "send a money order or Cashier's check.\n" + "P.S. You can edit this text from admin panel.");
		checkoutPage.clickContinueButtonAtPaymentInformationSection();

		displayedBillingAddressInfo = checkoutPage.saveDisplayedInfoIntoList("billing-info");
		displayedShippingAddressInfo = checkoutPage.saveDisplayedInfoIntoList("shipping-info");
		productListAtConfirmOrder = checkoutPage.saveListOfProductInfo();
		verifyEquals(productListAtShoppingCart, productListAtConfirmOrder);
		verifyEquals(displayedBillingAddressInfo, inputtedBillingAddressInfo);
		verifyEquals(displayedShippingAddressInfo, inputtedShippingAddressInfo);
		verifyEquals(checkoutPage.getDisplayedShippingMethodText(), shippingMethod);
		verifyEquals(checkoutPage.getDisplayedPaymentMethodText(), paymentMethod);
		verifyEquals(checkoutPage.getSubTotalDisplayedAtConfirmOrder(), subTotal);
		verifyEquals(checkoutPage.getShippingFeeDisplayedAtConfirmOrder(), shippingFee);
		verifyTrue(checkoutPage.isTotalValueAtConfirmOrderCorrect());
		verifyEquals(checkoutPage.getPointDisplayedAtConfirmOrder(), rewardPoint);
		checkoutPage.clickConfirmButtonAtConfirmOrder();
		sleepInSecond(5);

		// verifyEquals(checkoutPage.getSuccessMessageTitle(), "Thank you");
		verifyEquals(checkoutPage.getSuccessMessage(), "Your order has been successfully processed!");
		verifyTrue(checkoutPage.isOrderNumberDisplayed());
		orderNumber = checkoutPage.getOrderNumberText();
		homePage = checkoutPage.clickContinueButtonAtSuccessfulOrder();

		customerInfoPage = homePage.clickMyAccountLink();
		orderPage = (OrdersPageObject) customerInfoPage.openDynamicMyAccountLink(driver, "Orders");
		verifyTrue(orderPage.getOrderNumberText().contains(orderNumber));
		orderStatus = orderPage.getOrderStatusText();
		orderTotal = orderPage.getOrderTotalText();
		orderDate = orderPage.getOrderDateText();

		orderDetailPage = orderPage.clickToOrderDetailLinkByOrderNumber(orderNumber);
		verifyTrue(orderDetailPage.getOrderNumberText().contains(orderNumber));
		verifyTrue(orderDetailPage.getOrderStatusTex().contains(orderStatus));
		verifyTrue(orderDetailPage.getOrderTotalText().contains(orderTotal));
		verifyTrue(orderDate.contains(orderDetailPage.getOrderDateText()));

		verifyTrue(orderDetailPage.getBillingAddressText().contains(baFirstName));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baLastName));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baPhoneNumber));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baCountry));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baCity));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baAddress1));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baZipCode));

		verifyTrue(orderDetailPage.getShippingAddressText().contains(saFirstName));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(saLastName));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(saPhoneNumber));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(saCountry));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(saCity));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(saAddress1));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(saZipCode));

		verifyTrue(orderDetailPage.getPaymentMethodText().contains(paymentMethod));
		verifyTrue(orderDetailPage.getShippingMethodText().contains(shippingMethod));

		productListAtOrderDetail = orderDetailPage.saveProductInfoToList();
		System.out.println("productListAtOrderDetail:  \n" + productListAtOrderDetail.toString());
		System.out.println("=============================");
		System.out.println("productListAtConfirmOrder:  \n" + productListAtConfirmOrder.toString());
		System.out.println("=============================");
		System.out.println("productListAtShoppingCart:  \n" + productListAtShoppingCart.toString());
		// verifyEquals(productListAtOrderDetail, productListAtConfirmOrder);

		System.out.println("gift " + giftWrapping);
		System.out.println("subtotal " + subTotal);
		System.out.println("total " + total);

		verifyTrue(orderDetailPage.getGiftWrappingText().contains(giftWrapping));
		verifyTrue(orderDetailPage.getDynamicOrderSummaryText("Sub-Total").contains(subTotal));
		verifyTrue(orderDetailPage.getDynamicOrderSummaryText("Shipping").contains(shippingFee));
		verifyTrue(orderDetailPage.getDynamicOrderSummaryText("Order Total").contains(total));
	}

	@Test
	public void TC_06_Checkout_Order_By_Card() {
		paymentMethod = "Credit Card";
		shippingMethod = "Next Day Air";
		productPage = (ProductPageObject) homePage.clickToDynamicHeaderSubMenuLink(driver, "Computers", "Notebooks");
		productDetailPage = productPage.clickProductLink("HP Envy 15.6-Inch Sleekbook");
		productDetailPage.clickAddToCartOrUpdateButton();
		sleepInSecond(5);

		shoppingCartPage = productDetailPage.clickShoppingCartLink();
		shoppingCartPage.selectGiftWrappingDropdown("No");
		sleepInSecond(5);
		verifyTrue(shoppingCartPage.isSubTotalDisplayedCorrectly());
		verifyTrue(shoppingCartPage.isTotalDisplayedCorrectly());
		verifyTrue(shoppingCartPage.isPointEarnedDisplayedCorrectly());
		subTotal = shoppingCartPage.getDisplayedSubTotalText();
		total = shoppingCartPage.getDisplayedTotalText();
		rewardPoint = shoppingCartPage.getDisplayedRewardPointText();
		giftWrapping = shoppingCartPage.getGiftWrappingText();

		productListAtShoppingCart = shoppingCartPage.saveListOfProductInfo();
		shoppingCartPage.checkAgreeWithTheTermCheckbox();
		checkoutPage = shoppingCartPage.clickCheckoutButton();

		checkoutPage.uncheckShipToTheSameAddressCheckbox();
		checkoutPage.selectDynamicDropdown("billing-address-select", "New Address");
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_FirstName", baFirstName);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_LastName", baLastName);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_Email", baEmail);
		checkoutPage.selectDynamicDropdown("BillingNewAddress_CountryId", baCountry);
		checkoutPage.selectDynamicDropdown("BillingNewAddress_StateProvinceId", baState);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_City", baCity);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_Address1", baAddress1);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_ZipPostalCode", baZipCode);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_PhoneNumber", baPhoneNumber);
		inputtedBillingAddressInfo = checkoutPage.saveInfoToList(baFirstName + " " + baLastName, baEmail, baPhoneNumber, baCountry, baCity, baAddress1, baZipCode);
		checkoutPage.clickContinueButtonAtBillingAddressSection();

		checkoutPage.clickContinueButtonAtShippingAddressSection();

		checkoutPage.checkToDynamicRadioButton(shippingMethod);
		shippingFee = checkoutPage.getShippingFeeText();
		checkoutPage.clickContinueButtonAtShippingMethodSection();

		checkoutPage.checkToDynamicRadioButton(paymentMethod);
		checkoutPage.clickContinueButtonAtPaymentMethodSection();

		checkoutPage.selectDynamicDropdown("CreditCardType", "Visa");
		checkoutPage.inputToDynamicTextbox("CardholderName", cardHolderName);
		checkoutPage.inputToDynamicTextbox("CardNumber", creditCardNumber);
		checkoutPage.selectDynamicDropdown("ExpireMonth", expirationMonth);
		checkoutPage.selectDynamicDropdown("ExpireYear", expirationYear);
		checkoutPage.inputToDynamicTextbox("CardCode", cardCode);
		checkoutPage.clickContinueButtonAtPaymentInfoSection();

		displayedBillingAddressInfo = checkoutPage.saveDisplayedInfoIntoList("billing-info");
		displayedShippingAddressInfo = checkoutPage.saveDisplayedInfoIntoList("shipping-info");
		productListAtConfirmOrder = checkoutPage.saveListOfProductInfo();
		verifyEquals(productListAtShoppingCart, productListAtConfirmOrder);
		verifyEquals(displayedBillingAddressInfo, inputtedBillingAddressInfo);
		verifyEquals(displayedShippingAddressInfo, inputtedBillingAddressInfo);
		verifyEquals(checkoutPage.getDisplayedShippingMethodText(), shippingMethod);
		verifyEquals(checkoutPage.getDisplayedPaymentMethodText(), paymentMethod);
		verifyEquals(checkoutPage.getSubTotalDisplayedAtConfirmOrder(), subTotal);
		verifyEquals(checkoutPage.getShippingFeeDisplayedAtConfirmOrder(), shippingFee);
		verifyTrue(checkoutPage.isTotalValueAtConfirmOrderCorrect());
		verifyEquals(checkoutPage.getPointDisplayedAtConfirmOrder(), rewardPoint);
		checkoutPage.clickConfirmButtonAtConfirmOrder();

		verifyEquals(checkoutPage.getSuccessMessage(), "Your order has been successfully processed!");
		verifyTrue(checkoutPage.isOrderNumberDisplayed());
		orderNumber = checkoutPage.getOrderNumberText();
		homePage = checkoutPage.clickContinueButtonAtSuccessfulOrder();

		customerInfoPage = homePage.clickMyAccountLink();
		orderPage = (OrdersPageObject) customerInfoPage.openDynamicMyAccountLink(driver, "Orders");
		verifyTrue(orderPage.getOrderNumberText().contains(orderNumber));
		orderStatus = orderPage.getOrderStatusText();
		orderTotal = orderPage.getOrderTotalText();
		orderDate = orderPage.getOrderDateText();

		orderDetailPage = orderPage.clickToOrderDetailLinkByOrderNumber(orderNumber);
		verifyTrue(orderDetailPage.getOrderNumberText().contains(orderNumber));
		verifyTrue(orderDetailPage.getOrderStatusTex().contains(orderStatus));
		verifyTrue(orderDetailPage.getOrderTotalText().contains(orderTotal));
		verifyTrue(orderDate.contains(orderDetailPage.getOrderDateText()));

		verifyTrue(orderDetailPage.getBillingAddressText().contains(baFirstName));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baLastName));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baPhoneNumber));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baCountry));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baCity));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baAddress1));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baZipCode));

		verifyTrue(orderDetailPage.getShippingAddressText().contains(baFirstName));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(baLastName));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(baPhoneNumber));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(baCountry));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(baCity));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(baAddress1));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(baZipCode));

		verifyTrue(orderDetailPage.getPaymentMethodText().contains(paymentMethod));
		verifyTrue(orderDetailPage.getShippingMethodText().contains(shippingMethod));

		productListAtOrderDetail = orderDetailPage.saveProductInfoToList();
		verifyEquals(productListAtOrderDetail, productListAtConfirmOrder);

		System.out.println("gift " + giftWrapping);
		System.out.println("subtotal " + subTotal);
		System.out.println("total " + total);

		verifyTrue(orderDetailPage.getGiftWrappingText().contains(giftWrapping));
		verifyTrue(orderDetailPage.getDynamicOrderSummaryText("Sub-Total").contains(subTotal));
		verifyTrue(orderDetailPage.getDynamicOrderSummaryText("Shipping").contains(shippingFee));
		verifyTrue(orderDetailPage.getDynamicOrderSummaryText("Order Total").contains(total));

		sleepInSecond(10);
	}

	// @Test
	public void TC_07_Re_Order_Product() {
		shoppingCartPage = orderDetailPage.clickReOrderButton();
		shoppingCartPage.inputToQuantityCellByRowNumber("1", "7");
		sleepInSecond(3);

		shoppingCartPage.selectGiftWrappingDropdown("No");
		sleepInSecond(5);
		verifyTrue(shoppingCartPage.isSubTotalDisplayedCorrectly());
		verifyTrue(shoppingCartPage.isTotalDisplayedCorrectly());
		verifyTrue(shoppingCartPage.isPointEarnedDisplayedCorrectly());
		subTotal = shoppingCartPage.getDisplayedSubTotalText();
		total = shoppingCartPage.getDisplayedTotalText();
		rewardPoint = shoppingCartPage.getDisplayedRewardPointText();
		giftWrapping = shoppingCartPage.getGiftWrappingText();

		productListAtShoppingCart = shoppingCartPage.saveListOfProductInfo();
		shoppingCartPage.checkAgreeWithTheTermCheckbox();
		checkoutPage = shoppingCartPage.clickCheckoutButton();

		checkoutPage.uncheckShipToTheSameAddressCheckbox();
		checkoutPage.selectDynamicDropdown("billing-address-select", "New Address");
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_FirstName", baFirstName);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_LastName", baLastName);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_Email", baEmail);
		checkoutPage.selectDynamicDropdown("BillingNewAddress_CountryId", baCountry);
		checkoutPage.selectDynamicDropdown("BillingNewAddress_StateProvinceId", baState);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_City", baCity);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_Address1", baAddress1);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_ZipPostalCode", baZipCode);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_PhoneNumber", baPhoneNumber);
		inputtedBillingAddressInfo = checkoutPage.saveInfoToList(baFirstName + " " + baLastName, baEmail, baPhoneNumber, baCountry, baCity, baAddress1, baZipCode);
		checkoutPage.clickContinueButtonAtBillingAddressSection();

		checkoutPage.clickContinueButtonAtShippingAddressSection();

		checkoutPage.checkToDynamicRadioButton(shippingMethod);
		shippingFee = checkoutPage.getShippingFeeText();
		checkoutPage.clickContinueButtonAtShippingMethodSection();

		checkoutPage.checkToDynamicRadioButton(paymentMethod);
		checkoutPage.clickContinueButtonAtPaymentMethodSection();

		checkoutPage.selectDynamicDropdown("CreditCardType", "Visa");
		checkoutPage.inputToDynamicTextbox("CardholderName", cardHolderName);
		checkoutPage.inputToDynamicTextbox("CardNumber", creditCardNumber);
		checkoutPage.selectDynamicDropdown("ExpireMonth", expirationMonth);
		checkoutPage.selectDynamicDropdown("ExpireYear", expirationYear);
		checkoutPage.inputToDynamicTextbox("CardCode", cardCode);
		checkoutPage.clickContinueButtonAtPaymentInfoSection();

		displayedBillingAddressInfo = checkoutPage.saveDisplayedInfoIntoList("billing-info");
		displayedShippingAddressInfo = checkoutPage.saveDisplayedInfoIntoList("shipping-info");
		productListAtConfirmOrder = checkoutPage.saveListOfProductInfo();
		verifyEquals(productListAtShoppingCart, productListAtConfirmOrder);
		verifyEquals(displayedBillingAddressInfo, inputtedBillingAddressInfo);
		verifyEquals(displayedShippingAddressInfo, inputtedBillingAddressInfo);
		verifyEquals(checkoutPage.getDisplayedShippingMethodText(), shippingMethod);
		verifyEquals(checkoutPage.getDisplayedPaymentMethodText(), paymentMethod);
		verifyEquals(checkoutPage.getSubTotalDisplayedAtConfirmOrder(), subTotal);
		verifyEquals(checkoutPage.getShippingFeeDisplayedAtConfirmOrder(), shippingFee);
		verifyTrue(checkoutPage.isTotalValueAtConfirmOrderCorrect());
		verifyEquals(checkoutPage.getPointDisplayedAtConfirmOrder(), rewardPoint);
		sleepInSecond(5);
		// checkoutPage.acceptAlert(driver);
		checkoutPage.clickConfirmButtonAtConfirmOrder();

		verifyEquals(checkoutPage.getSuccessMessage(), "Your order has been successfully processed!");
		verifyTrue(checkoutPage.isOrderNumberDisplayed());
		orderNumber = checkoutPage.getOrderNumberText();
		homePage = checkoutPage.clickContinueButtonAtSuccessfulOrder();

		customerInfoPage = homePage.clickMyAccountLink();
		orderPage = (OrdersPageObject) customerInfoPage.openDynamicMyAccountLink(driver, "Orders");
		verifyTrue(orderPage.getOrderNumberText().contains(orderNumber));
		orderStatus = orderPage.getOrderStatusText();
		orderTotal = orderPage.getOrderTotalText();
		orderDate = orderPage.getOrderDateText();

		orderDetailPage = orderPage.clickToOrderDetailLinkByOrderNumber(orderNumber);
		verifyTrue(orderDetailPage.getOrderNumberText().contains(orderNumber));
		verifyTrue(orderDetailPage.getOrderStatusTex().contains(orderStatus));
		verifyTrue(orderDetailPage.getOrderTotalText().contains(orderTotal));
		verifyTrue(orderDate.contains(orderDetailPage.getOrderDateText()));

		verifyTrue(orderDetailPage.getBillingAddressText().contains(baFirstName));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baLastName));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baPhoneNumber));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baCountry));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baCity));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baAddress1));
		verifyTrue(orderDetailPage.getBillingAddressText().contains(baZipCode));

		verifyTrue(orderDetailPage.getShippingAddressText().contains(baFirstName));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(baLastName));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(baPhoneNumber));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(baCountry));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(baCity));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(baAddress1));
		verifyTrue(orderDetailPage.getShippingAddressText().contains(baZipCode));

		verifyTrue(orderDetailPage.getPaymentMethodText().contains(paymentMethod));
		verifyTrue(orderDetailPage.getShippingMethodText().contains(shippingMethod));

		productListAtOrderDetail = orderDetailPage.saveProductInfoToList();
		verifyEquals(productListAtOrderDetail, productListAtConfirmOrder);

		System.out.println("gift " + giftWrapping);
		System.out.println("subtotal " + subTotal);
		System.out.println("total " + total);

		verifyTrue(orderDetailPage.getGiftWrappingText().contains(giftWrapping));
		verifyTrue(orderDetailPage.getDynamicOrderSummaryText("Sub-Total").contains(subTotal));
		verifyTrue(orderDetailPage.getDynamicOrderSummaryText("Shipping").contains(shippingFee));
		verifyTrue(orderDetailPage.getDynamicOrderSummaryText("Order Total").contains(total));
	}

	@AfterTest
	public void afterTest() {
		closeBrowserDriver();
	}
}
