package nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Register extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String firstName, lastName, gender, emailAddress, invalidEmail, birthDate, birthMonth, birthYear, password, invalidPassword, wrongPassword;

	@Parameters({ "browser", "userType", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String userType, String environmentName) {
		driver = getBrowserDriver(browserName, userType, environmentName);
		homePage = PageGeneratorManager.openHomePage(driver);

		firstName = DataHelper.getDataHelper().getFirstName();
		lastName = DataHelper.getDataHelper().getLastName();
		gender = "female";
		emailAddress = DataHelper.getDataHelper().getEmailAddress();
		invalidEmail = "a123@";
		birthDate = "17";
		birthMonth = "December";
		birthYear = "2000";
		password = DataHelper.getDataHelper().getPassword();
		invalidPassword = "12345";
		wrongPassword = DataHelper.getDataHelper().getPassword();
	}

	@Test
	public void TC_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Register with empty data");
		ExtentTestManager.getTest().log(Status.INFO, "Register with empty data - Step 01: Navigate to 'Register' page");
		registerPage = homePage.clickToRegisterLink();

		ExtentTestManager.getTest().log(Status.INFO, "Register with empty data - Step 02: Click 'Register' button");
		registerPage.clickRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register with empty data - Step 03: Verify first name error message is displayed");
		verifyEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");

		ExtentTestManager.getTest().log(Status.INFO, "Register with empty data - Step 04: Verify last name error message is displayed");
		verifyEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");

		ExtentTestManager.getTest().log(Status.INFO, "Register with empty data - Step 05: Verify email error message is displayed");
		verifyEquals(registerPage.getEmailErrorMessage(), "Email is required.");

		ExtentTestManager.getTest().log(Status.INFO, "Register with empty data - Step 07: Verify confirm password error message is displayed");
		verifyEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
	}

	@Test
	public void TC_02_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Register with invalid email");
		ExtentTestManager.getTest().log(Status.INFO, "Register with invalid email - Step 01: Input to 'First name' textbox with value '" + firstName + "'");
		registerPage.inputToFirstNameTextbox(firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register with invalid email - Step 02: Input to 'Last name' textbox with value '" + lastName + "'");
		registerPage.inputToLastNameTextbox(lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register with invalid email - Step 03: Input to 'Email' textbox with value '" + invalidEmail + "'");
		registerPage.inputToEmailTextbox(invalidEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Register with invalid email - Step 04: Input to 'Password' textbox with value '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Register with invalid email - Step 05: Input to 'Confirm password' textbox with value '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Register with invalid email - Step 06: Click 'Register' button");
		registerPage.clickRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register with invalid email - Step 07: Verify email error message is displayed");
		verifyEquals(registerPage.getEmailErrorMessage(), "Please enter a valid email address.");
	}

	@Test
	public void TC_03_Password_Less_Than_6_Characters(Method method) {
		ExtentTestManager.startTest(method.getName(), "Register with password less than 6 characters");
		ExtentTestManager.getTest().log(Status.INFO, "Register with password less than 6 characters - Step 01: Input to 'First name' textbox with value '" + firstName + "'");
		registerPage.inputToFirstNameTextbox(firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register with password less than 6 characters - Step 02: Input to 'Last name' textbox with value '" + lastName + "'");
		registerPage.inputToLastNameTextbox(lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register with password less than 6 characters - Step 03: Input to 'Email' textbox with value '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Register with password less than 6 characters - Step 04: Input to 'Password' textbox with value '" + invalidPassword + "'");
		registerPage.inputToPasswordTextbox(invalidPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register with password less than 6 characters - Step 05: Input to 'Confirm Password' textbox with value '" + invalidPassword + "'");
		registerPage.inputToConfirmPasswordTextbox(invalidPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register with password less than 6 characters - Step 06: Click 'Register' button");
		registerPage.clickRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register with password less than 6 characters - Step 07: Verify password error message is displayed");
		verifyEquals(registerPage.getPasswordErrorMessage(), "<p>must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
	}

	@Test
	public void TC_04_Confirm_Password_Is_Wrong(Method method) {
		ExtentTestManager.startTest(method.getName(), "Register with confirm password is wrong");
		ExtentTestManager.getTest().log(Status.INFO, "Register with confirm password is wrong - Step 01: Input to 'First name' textbox with value '" + firstName + "'");
		registerPage.inputToFirstNameTextbox(firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register with confirm password is wrong - Step 02: Input to 'Last name' textbox with value '" + lastName + "'");
		registerPage.inputToLastNameTextbox(lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register with confirm password is wrong - Step 03: Input to 'Email' textbox with value '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Register with confirm password is wrong - Step 04: Input to 'Password' textbox with value '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Register with confirm password is wrong - Step 05: Input to 'Confirm password' textbox with value '" + wrongPassword + "'");
		registerPage.inputToConfirmPasswordTextbox(wrongPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register with confirm password is wrong - Step 06: Click 'Register' button");
		registerPage.clickRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register with confirm password is wrong - Step 07: Verify confirm password error message is displayed");
		verifyEquals(registerPage.getConfirmPasswordErrorMessage(), "The password and confirmation password do not match.");
	}

	@Test
	public void TC_05_Success_Message(Method method) {
		ExtentTestManager.startTest(method.getName(), "Register successfully");
		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 01: Input to 'First name' textbox with value '" + firstName + "'");
		registerPage.inputToFirstNameTextbox(firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 02: Input to 'Last name' textbox with value '" + lastName + "'");
		registerPage.inputToLastNameTextbox(lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 03: Input to 'Email' textbox with value '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 04: Input to 'Password' textbox with value '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 05: Input to 'Confirm password' textbox with value '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		// ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 06: Select 'Gender' radio button with value '" + gender + "'");
		// registerPage.selectGenderRadioButton(gender);
		//
		// ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 07: Select 'Birth date' dropdown with value '" + birthDate + "'");
		// registerPage.selectBirthDateDropdown(birthDate);
		//
		// ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 08: Select 'Birth month' dropdown with value '" + birthMonth + "'");
		// registerPage.selectBirthMonthDropdown(birthMonth);
		//
		// ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 09: Select 'Birth year' dropdown with value '" + birthYear + "'");
		// registerPage.selectBirthYearDropdown(birthYear);

		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 10: Uncheck 'Newsletter' checkbox");
		registerPage.uncheckToNewsletterCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 11: Click 'Register' button");
		registerPage.clickRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 12: Verify success message is displayed");
		verifyEquals(registerPage.getSuccessRegistrationMessage(), "Your registration completed");

		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 13: Click 'Continue' button to return to Home page");
		homePage = registerPage.clickContinueButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 14: Verify 'My account' link is displayed");
		verifyTrue(homePage.isMyAccountLinkDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 15: Click 'Log out' link");
		homePage.clickLogoutLink();

		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 16: Verify 'Register' link is displayed");
		verifyTrue(homePage.isRegisterLinkDisplayed());
	}

	@Test
	public void TC_06_Existing_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Register successfully");
		ExtentTestManager.getTest().log(Status.INFO, "Register with existing email - Step 01: Click 'Register' link");
		registerPage = homePage.clickToRegisterLink();

		ExtentTestManager.getTest().log(Status.INFO, "Register with existing email - Step 02: Input to 'FirstName' textbox with value '" + firstName + "'");
		registerPage.inputToFirstNameTextbox(firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register with existing email - Step 03: Input to 'LastName' textbox with value '" + lastName + "'");
		registerPage.inputToLastNameTextbox(lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register with existing email - Step 04: Input to 'Email' textbox with value '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Register with existing email - Step 05: Input to 'Password' textbox with value '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Register with existing email - Step 06: Input to 'Confirm password' textbox with value '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Register with existing email - Step 07: Click 'Register' button");
		registerPage.clickRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register with existing email - Step 08: Verify summary error message is displayed");
		verifyEquals(registerPage.getSummaryErrorMessage(), "The specified email already exists");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
