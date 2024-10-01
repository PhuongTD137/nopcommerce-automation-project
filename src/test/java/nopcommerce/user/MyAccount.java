package nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.AddressesPageObject;
import pageObjects.user.ChangePasswordPageObject;
import pageObjects.user.CustomerInfoPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.ProductReviewsPageObject;
import pageObjects.user.RegisterPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class MyAccount extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private AddressesPageObject addressesPage;
	private ChangePasswordPageObject changePasswordPage;
	private ProductReviewsPageObject productReviewsPage;
	private String firstName, lastName, gender, emailAddress, password, newPassword, companyName, birthDate, birthMonth, birthYear;
	private String city, address1, address2, zipCode, phoneNumber, faxNumber, country, state;
	private String url;

	@Parameters({ "browser", "userType", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String userType, String environmentName) {
		driver = getBrowserDriver(browserName, userType, environmentName);
		homePage = PageGeneratorManager.openHomePage(driver);
		this.url = homePage.getPageUrl(driver);

		firstName = DataHelper.getDataHelper().getFirstName();
		lastName = DataHelper.getDataHelper().getLastName();
		gender = "Male";
		companyName = DataHelper.getDataHelper().getCompanyName();
		emailAddress = DataHelper.getDataHelper().getEmailAddress();
		password = DataHelper.getDataHelper().getPassword();
		newPassword = DataHelper.getDataHelper().getPassword();
		birthDate = "15";
		birthMonth = "September";
		birthYear = "2003";

		city = "Da Nang";
		address1 = "123 Le Lai";
		address2 = "234 Le Loi";
		zipCode = "700000";
		phoneNumber = DataHelper.getDataHelper().getPhoneNumber();
		faxNumber = DataHelper.getDataHelper().getPhoneNumber();
		country = "Vietnam";
		state = "Hồ Chí Minh";

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
	}

	@Test
	public void TC_01_Update_Customer_Info(Method method) {
		ExtentTestManager.startTest(method.getName(), "Update Customer Info");
		ExtentTestManager.getTest().log(Status.INFO, "Update Customer Info - Step 1: Navigate to 'My Account' link");
		customerInfoPage = homePage.clickMyAccountLink();

		ExtentTestManager.getTest().log(Status.INFO, "Update Customer Info - Step 2: Select from 'Gender' radio button with value '" + gender + "'");
		customerInfoPage.selectGenderRadioButton(gender);

		ExtentTestManager.getTest().log(Status.INFO, "Update Customer Info - Step 3: Input to 'First name' textbox with value '" + firstName + "'");
		customerInfoPage.inputToDynamicTextbox("FirstName", "Linh");

		ExtentTestManager.getTest().log(Status.INFO, "Update Customer Info - Step 4: Input to 'Last name' textbox with value '" + lastName + "'");
		customerInfoPage.inputToDynamicTextbox("LastName", "Nguyen");

		ExtentTestManager.getTest().log(Status.INFO, "Update Customer Info - Step 5: Input to 'Company' textbox with value '" + companyName + "'");
		customerInfoPage.inputToDynamicTextbox("Company", "ABC");

		ExtentTestManager.getTest().log(Status.INFO, "Update Customer Info - Step 9: Click 'Save' button");
		customerInfoPage.clickSaveButton();

		ExtentTestManager.getTest().log(Status.INFO, "Update Customer Info - Step 10: Verify the customer info has been updated successfully");
		verifyEquals(customerInfoPage.getUpdatedMessage(), "The customer info has been updated successfully.");
	}

	@Test
	public void TC_02_Add_Address(Method method) {
		ExtentTestManager.startTest(method.getName(), "Add Address");
		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 1: Navigate to 'Addresses' page");
		addressesPage = (AddressesPageObject) customerInfoPage.openDynamicMyAccountLink(driver, "Addresses");

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 2: Click 'Add new' button");
		addressesPage.clickAddNewButton();

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 3: Input to 'First name' textbox with value '" + firstName + "'");
		addressesPage.inputToDynamicTextbox("First name", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 4: Input to 'Last name' textbox with value '" + lastName + "'");
		addressesPage.inputToDynamicTextbox("Last name", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 5: Input to 'Email' textbox with value '" + emailAddress + "'");
		addressesPage.inputToDynamicTextbox("Email", emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 6: Input to 'Company' textbox with value '" + companyName + "'");
		addressesPage.inputToDynamicTextbox("Company", companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 7: Input to 'City' textbox with value '" + city + "'");
		addressesPage.inputToDynamicTextbox("City", city);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 8: Input to 'Address 1' textbox with value '" + address1 + "'");
		addressesPage.inputToDynamicTextbox("Address 1", address1);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 8: Input to 'Address 2' textbox with value '" + address2 + "'");
		addressesPage.inputToDynamicTextbox("Address 2", address2);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 9: Input to 'Zip/postal code' textbox with value '" + zipCode + "'");
		addressesPage.inputToDynamicTextbox("Zip / postal code", zipCode);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 10: Input to 'Phone number' textbox with value '" + phoneNumber + "'");
		addressesPage.inputToDynamicTextbox("Phone number", phoneNumber);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 11: Input to 'Fax number' textbox with value '" + faxNumber + "'");
		addressesPage.inputToDynamicTextbox("Fax number", faxNumber);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 12: Input to 'Country' dropdown with value '" + country + "'");
		addressesPage.selectDynamicDropdown("Country", country);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 13: Input to 'State' dropdown with value '" + state + "'");
		addressesPage.selectDynamicDropdown("State / province", state);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 14: Click 'Save' button");
		addressesPage.clickSaveButton();

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 15: Verify success message is displayed");
		Assert.assertEquals(addressesPage.getSuccessMessage(), "The new address has been added successfully.");

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 16: Verify customer info is displayed correctly");
		Assert.assertTrue(addressesPage.getDynamicInfoText("name").contains(firstName + " " + lastName));
		Assert.assertTrue(addressesPage.getDynamicInfoText("email").contains(emailAddress));
		Assert.assertTrue(addressesPage.getDynamicInfoText("phone").contains(phoneNumber));
		Assert.assertTrue(addressesPage.getDynamicInfoText("fax").contains(faxNumber));
		Assert.assertTrue(addressesPage.getDynamicInfoText("country").contains(country));
		Assert.assertTrue(addressesPage.getDynamicInfoText("city").contains(city));
		Assert.assertTrue(addressesPage.getDynamicInfoText("address1").contains(address1));
		Assert.assertTrue(addressesPage.getDynamicInfoText("address2").contains(address2));
		Assert.assertTrue(addressesPage.getDynamicInfoText("zippostalcode").contains(zipCode));
		addressesPage.clickCloseIcon();
	}

	@Test
	public void TC_03_Change_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Change Password");
		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 1: Navigate to 'Change Password' page");
		changePasswordPage = (ChangePasswordPageObject) addressesPage.openDynamicMyAccountLink(driver, "Change password");

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 2: Input to 'Old password' textbox with value '" + password + "'");
		changePasswordPage.inputToDynamicTextbox("Old password", password);

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 3: Input to 'New password' textbox with value '" + newPassword + "'");
		changePasswordPage.inputToDynamicTextbox("New password", newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 4: Input to 'Confirm password' textbox with value '" + newPassword + "'");
		changePasswordPage.inputToDynamicTextbox("Confirm password", newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 5: Click 'Change password' button");
		changePasswordPage.clickChangePasswordButton();

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 6: Verify success message is displayed");
		verifyEquals(changePasswordPage.getSuccessMessage(), "Password was changed");
		changePasswordPage.clickToCloseIcon();

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 7: Navigate to 'Home' page");
		changePasswordPage.openPageUrl(driver, url);
		homePage = PageGeneratorManager.openHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 8: Click 'Log out' link");
		homePage.clickLogoutLink();

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 9: Click 'Log in' link");
		loginPage = homePage.clickLoginLink();

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 10: Input to 'Email' textbox with value '" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 11: Input to 'Password' textbox with old password '" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 12: Click 'Log in' button");
		homePage = loginPage.clickLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 13: Verify log in unsuccessful");
		verifyEquals(loginPage.getSummaryErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 14: Input to 'Email' textbox with value '" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 15: Input to 'Password' textbox with new password '" + newPassword + "'");
		loginPage.inputToPasswordTextbox(newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 16: Click 'Log in' button");
		homePage = loginPage.clickLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Change Password - Step 17: Verify log in successfully and 'My Account' link is displayed");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}

	// @Test
	public void TC_04_Add_Product_Review() {
		// productReviewsPage = (ProductReviewsPageObject) changePasswordPage.openDynamicPage(driver, "My product reviews");
	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}
}
