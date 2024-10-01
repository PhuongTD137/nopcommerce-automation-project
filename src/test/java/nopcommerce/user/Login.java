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
import pageObjects.user.LoginPageObject;
import pageObjects.user.RegisterPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Login extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private String firstName, lastName, emailAddress, unregisteredEmail, invalidEmail, password, wrongPassword;

	@Parameters({ "browser", "userType", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String userType, String environmentName) {
		driver = getBrowserDriver(browserName, userType, environmentName);
		homePage = PageGeneratorManager.openHomePage(driver);

		firstName = DataHelper.getDataHelper().getFirstName();
		lastName = DataHelper.getDataHelper().getLastName();
		emailAddress = DataHelper.getDataHelper().getEmailAddress();
		unregisteredEmail = DataHelper.getDataHelper().getEmailAddress();
		invalidEmail = "linh123";
		password = DataHelper.getDataHelper().getPassword();
		wrongPassword = DataHelper.getDataHelper().getPassword();

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
		verifyTrue(homePage.isRegisterLinkDisplayed());

	}

	@Test
	public void TC_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login with empty data");
		ExtentTestManager.getTest().log(Status.INFO, "Login with empty data - Step 01: Navigate to 'Login' page");
		loginPage = homePage.clickLoginLink();

		ExtentTestManager.getTest().log(Status.INFO, "Login with empty data - Step 02: Click 'Login' button");
		loginPage.clickLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login with empty data - Step 03: Verify email error message is displayed");
		verifyEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
	}

	@Test
	public void TC_02_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login with invalid email");
		ExtentTestManager.getTest().log(Status.INFO, "Login with invalid email - Step 01: Input to 'Email' textbox with value'" + invalidEmail + "'");
		loginPage.inputToEmailTextbox(invalidEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login with invalid email - Step 02: Input to 'Password' textbox with value'" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Login with invalid email - Step 03: Click 'Login' button");
		loginPage.clickLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login with invalid email - Step 04: Verify email error message is displayed");
		verifyEquals(loginPage.getEmailErrorMessage(), "Please enter a valid email address.");
	}

	@Test
	public void TC_03_Unregistered_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login with unregistered email");
		ExtentTestManager.getTest().log(Status.INFO, "Login with unregistered email - Step 01: Input to 'Email' textbox with value'" + unregisteredEmail + "'");
		loginPage.inputToEmailTextbox(unregisteredEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login with unregistered email - Step 02: Input to 'Password' textbox with value'" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Login with unregistered email - Step 03: Click 'Login' button");
		loginPage.clickLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login with unregistered email - Step 04: Verify summary error message is displayed");
		verifyEquals(loginPage.getSummaryErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void TC_04_Not_Input_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login with no password");
		ExtentTestManager.getTest().log(Status.INFO, "Login with no password - Step 01: Input to 'Email' textbox with value'" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Login with no password - Step 02: Input to 'Password' textbox with value is blank");
		loginPage.inputToPasswordTextbox("");

		ExtentTestManager.getTest().log(Status.INFO, "Login with no password - Step 03: Click 'Login' button");
		loginPage.clickLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login with no password - Step 04: Verify summary error message is displayed");
		verifyEquals(loginPage.getSummaryErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_05_Wrong_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login with wrong password");
		ExtentTestManager.getTest().log(Status.INFO, "Login with wrong password - Step 01: Input to 'Email' textbox with value'" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Login with wrong password - Step 02: Input to 'Password' textbox with value'" + wrongPassword + "'");
		loginPage.inputToPasswordTextbox(wrongPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Login with wrong password - Step 03: Click 'Login' button");
		loginPage.clickLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login with wrong password - Step 04: Verify summary error message is displayed");
		verifyEquals(loginPage.getSummaryErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_06_Success_Login(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login successfully");
		ExtentTestManager.getTest().log(Status.INFO, "Login successfully - Step 01: Input to 'Email' textbox with value'" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Login successfully - Step 02: Input to 'Password' textbox with value'" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Login successfully - Step 03: Click 'Login' button");
		loginPage.clickLoginButton();
		homePage = PageGeneratorManager.openHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Login successfully - Step 04: Verify 'My Account' link is displayed");
		verifyTrue(homePage.isMyAccountLinkDisplayed());

	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}

}
