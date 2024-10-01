package nopcommerce.admin;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.admin.AdminAddressDetailPageObject;
import pageObjects.admin.AdminCustomerListPageObject;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminNewCustomerPageObject;
import pageObjects.user.LoginPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Customer extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	AdminDashboardPageObject adminDashboardPage;
	AdminCustomerListPageObject adminCustomerListPage;
	AdminNewCustomerPageObject adminNewCustomerPage;
	AdminAddressDetailPageObject adminAddressDetailPage;
	DataHelper dataHelper;
	String email, password, firstName, lastName, fullName, gender, companyName, cusRoleString, adminComment;
	String[] cusRole;
	String country, state, city, address, zipCode, phoneNumber;
	String editedEmail, editedFirstName, editedLastName, editedFullName, editedCompanyName, editedAdminComment;

	@Parameters({ "browser", "userType", "environment" })
	@BeforeTest
	public void beforeTest(String browserName, String userType, String environmentName) {
		driver = getBrowserDriver(browserName, userType, environmentName);
		loginPage = PageGeneratorManager.openLoginPage(driver);
		loginPage.inputToEmailTextbox("automationtesting@gmail.com");
		loginPage.inputToPasswordTextbox("automationtesting@gmail.com");
		loginPage.clickLoginButton();
		adminDashboardPage = PageGeneratorManager.openAdminDashboardPage(driver);
		adminDashboardPage.openAdminDynamicPageOnSideBarMenu(driver, "Customers", "Customers");
		adminCustomerListPage = PageGeneratorManager.openAdminCustomerListPage(driver);
		sleepInSecond(5);

		dataHelper = DataHelper.getDataHelper();
		email = dataHelper.getEmailAddress();
		password = "auto@123";
		firstName = dataHelper.getFirstName();
		lastName = dataHelper.getLastName();
		fullName = firstName + " " + lastName;
		gender = "F";
		companyName = dataHelper.getCompanyName();
		cusRoleString = Arrays.toString(cusRole);
		adminComment = "Add new customer";
		cusRole = new String[] { "Guests" };

		country = "Vietnam";
		city = "Ho Chi Minh";
		state = "Hồ Chí Minh";
		address = "23 Thach Lam";
		zipCode = "700000";
		phoneNumber = "0997099077";

		editedEmail = "edit_" + this.email;
		editedFirstName = "edit_" + this.firstName;
		editedLastName = "edit_" + this.lastName;
		editedFullName = editedFirstName + " " + editedLastName;
		editedCompanyName = "edit_" + this.companyName;
		editedAdminComment = "Edit customer (Guest)";
	}

	@Test
	public void TC_01_Create_New_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Create new customer");
		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 1: Click on 'Add new' button");
		adminNewCustomerPage = adminCustomerListPage.clickToAddNewButton();
		sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 2: Input to the 'Email' textbox with value: '" + email + "'");
		adminNewCustomerPage.inputToDynamicTextbox("Email", email);
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 3: Input to the the 'Password' textbox with value: '" + password + "'");
		adminNewCustomerPage.inputToDynamicTextbox("Password", password);
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 4: Input to the 'First name' textbox with value: '" + firstName + "'");
		adminNewCustomerPage.inputToDynamicTextbox("FirstName", firstName);
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 5: Input to the 'Last name' textbox with value: '" + lastName + "'");
		adminNewCustomerPage.inputToDynamicTextbox("LastName", lastName);
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 6: Select the 'Gender' radio button with value: '" + gender + "'");
		adminNewCustomerPage.selectRadioButton(gender);
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 7: Input to the 'Company' textbox with value: '" + companyName + "'");
		adminNewCustomerPage.inputToDynamicTextbox("Company", companyName);
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 8: Select the 'Customer Role' dropdown with value: '" + cusRoleString + "'");
		adminNewCustomerPage.selectCustomerRolesDropdown(cusRole);
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 9: Check the 'Active' checkbox");
		adminNewCustomerPage.selectActiveCheckbox();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 10: Input to the 'Admin Comment' text area with value: '" + adminComment + "'");
		adminNewCustomerPage.inputToAdminCommentTextArea(adminComment);
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 11: Click on the 'Save and Continue edit' button");
		adminNewCustomerPage.clickSaveAndContinueEditButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 12: Verify that the success message is displayed");
		verifyTrue(adminNewCustomerPage.isSuccessMessageDisplayed("The new customer has been added successfully."));

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 13: Verify the customer info is displayed");
		verifyEquals(adminNewCustomerPage.getDynamicTextboxValue("Email"), email);
		verifyEquals(adminNewCustomerPage.getDynamicTextboxValue("FirstName"), firstName);
		verifyEquals(adminNewCustomerPage.getDynamicTextboxValue("LastName"), lastName);
		verifyEquals(adminNewCustomerPage.getDynamicTextboxValue("Company"), companyName);
		verifyEquals(adminNewCustomerPage.getCheckedGenderValue(), gender);
		verifyTrue(adminNewCustomerPage.getStringOfSelectedCustomerRoleList().equals(cusRoleString));
		verifyEquals(adminNewCustomerPage.getAdminCommentTextAreaValue(), adminComment);

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 14: Click on the 'Back to customer list' link");
		adminCustomerListPage = adminNewCustomerPage.clickBackToCustomerListLink();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 15: Select from the 'Customer role' dropdown with value: '" + cusRoleString + "'");
		adminCustomerListPage.selectCustomerRoleDropdown(cusRole);
		adminCustomerListPage.clickSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Create new customer - Step 16: Verify the customer info is displayed in the customer list table");
		verifyEquals(adminCustomerListPage.getTextAtDynamicCellValueInTable(fullName, "Name"), fullName);
		// verifyTrue(adminCustomerListPage.getTextAtDynamicCellValueInTable(fullName, "Email").equals(email));
		verifyTrue(adminCustomerListPage.getTextAtDynamicCellValueInTable(fullName, "Company name").equals(companyName));
		verifyTrue(cusRoleString.contains(adminCustomerListPage.getTextAtDynamicCellValueInTable(fullName, "Customer roles")));
	}

	@Test
	public void TC_02_Search_Customer_With_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search customer with email");
		ExtentTestManager.getTest().log(Status.INFO, "Search customer with email - Step 1: Input to the 'Email' textbox with value: '" + email + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchEmail", email);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with email - Step 2: Select from the 'Customer role' dropdown with value: '" + cusRoleString + "'");
		adminCustomerListPage.selectCustomerRoleDropdown(cusRole);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with email - Step 3: Click on the 'Search' button");
		adminCustomerListPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with email - Step 4: Verify that only one row is displayed in the customer list table");
		verifyEquals(adminCustomerListPage.getNumberOfRowDisplayed(), 1);
		verifyTrue(adminCustomerListPage.getGridInfoText().contains("1-1 of 1 items"));
	}

	@Test
	public void TC_03_Search_Customer_With_First_Name_And_Last_Name(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search customer with first name and last name");
		ExtentTestManager.getTest().log(Status.INFO, "Search customer with first name and last name - Step 1: Input to the 'First name' textbox with value: '" + firstName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchFirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with first name and last name - Step 2: Input to the 'First name' textbox with value: '" + lastName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchLastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with first name and last name - Step 3: Select from the 'Customer role' dropdown with value: '" + cusRoleString + "'");
		adminCustomerListPage.selectCustomerRoleDropdown(cusRole);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with first name and last name - Step 4: Click on the 'Search' button");
		adminCustomerListPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with first name and last name - Step 4: Verify that only one row is displayed in the customer list table");
		verifyEquals(adminCustomerListPage.getNumberOfRowDisplayed(), 1);
		verifyTrue(adminCustomerListPage.getGridInfoText().contains("1-1 of 1 items"));
	}

	@Test
	public void TC_04_Search_Customer_With_Company(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search customer with first name and last name");
		ExtentTestManager.getTest().log(Status.INFO, "Search customer with company name - Step 1: Input to the 'Company' textbox with value: '" + companyName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchCompany", companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with company name - Step 2: Select from the 'Customer role' dropdown with value: '" + cusRoleString + "'");
		adminCustomerListPage.selectCustomerRoleDropdown(cusRole);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with company name - Step 4: Click on the 'Search' button");
		adminCustomerListPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with company name - Step 5: Verify that only one row is displayed in the customer list table");
		verifyEquals(adminCustomerListPage.getNumberOfRowDisplayed(), 1);
		verifyTrue(adminCustomerListPage.getGridInfoText().contains("1-1 of 1 items"));
	}

	@Test
	public void TC_05_Search_Customer_With_Full_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search customer with first name and last name");
		ExtentTestManager.getTest().log(Status.INFO, "Search customer with full data - Step 1: Input to the 'Email' textbox with value: '" + email + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchEmail", email);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with full data - Step 2: Input to the 'First name' textbox with value: '" + firstName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchFirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with full data - Step 3: Input to the 'Last name' textbox with value: '" + lastName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchLastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with full data - Step 4: Input to the 'Company' textbox with value: '" + companyName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchCompany", companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with full data - Step 5: Select from the 'Customer role' dropdown with value: '" + cusRoleString + "'");
		adminCustomerListPage.selectCustomerRoleDropdown(cusRole);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with full data - Step 6: Click on the 'Search' button");
		adminCustomerListPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Search customer with company name - Step 7: Verify that only one row is displayed in the customer list table");
		verifyEquals(adminCustomerListPage.getNumberOfRowDisplayed(), 1);
		verifyTrue(adminCustomerListPage.getGridInfoText().contains("1-1 of 1 items"));
		sleepInSecond(3);
	}

	@Test
	public void TC_06_Edit_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Edit customer");
		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 1: Input to the 'Email' textbox with value: '" + email + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchEmail", email);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 2: Input to the 'First name' textbox with value: '" + firstName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchFirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 3: Input to the 'Last name' textbox with value: '" + lastName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchLastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 4: Input to the 'Company' textbox with value: '" + companyName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchCompany", companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 5: Select from the 'Customer role' dropdown with value: '" + cusRoleString + "'");
		adminCustomerListPage.selectCustomerRoleDropdown(cusRole);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 6: Click on the 'Search' button");
		adminCustomerListPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 7: Navigate to 'Customer detail' page by clicking 'Edit customer' button");
		adminNewCustomerPage = adminCustomerListPage.clickToEditButtonInTable();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 8: Input to the 'Email' textbox with value: '" + editedEmail + "'");
		adminNewCustomerPage.inputToDynamicTextbox("Email", editedEmail);
		sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 9: Input to the 'First name' textbox with value: '" + editedFirstName + "'");
		adminNewCustomerPage.inputToDynamicTextbox("FirstName", editedFirstName);
		sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 10: Input to the 'Last name' textbox with value: '" + editedLastName + "'");
		adminNewCustomerPage.inputToDynamicTextbox("LastName", editedLastName);
		sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 11: Input to the 'Company' textbox with value: '" + editedCompanyName + "'");
		adminNewCustomerPage.inputToDynamicTextbox("Company", editedCompanyName);
		sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 12: Input to the 'Admin Comment' text area with value: '" + editedAdminComment + "'");
		adminNewCustomerPage.inputToAdminCommentTextArea(editedAdminComment);
		sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 13: Click on 'Save' button");
		adminCustomerListPage = adminNewCustomerPage.clickSaveButton();
		sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 14: Verify the success message is displayed");
		verifyTrue(adminCustomerListPage.getSuccessMessageText().contains("The customer has been updated successfully."));
		sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 15: Input to the 'Email' textbox with value: '" + editedEmail + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchEmail", editedEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 16: Input to the 'First name' textbox with value: '" + editedFirstName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchFirstName", editedFirstName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 16: Input to the 'Last name' textbox with value: '" + editedLastName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchLastName", editedLastName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 17: Input to the 'Company' textbox with value: '" + editedCompanyName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchCompany", editedCompanyName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 18: Select from the 'Customer role' dropdown with value: '" + cusRoleString + "'");
		adminCustomerListPage.selectCustomerRoleDropdown(cusRole);
		sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 19: Click on 'Search' button");
		adminCustomerListPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 20: Verify that only one row is displayed in the customer list table");
		verifyEquals(adminCustomerListPage.getNumberOfRowDisplayed(), 1);
		sleepInSecond(2);
		verifyTrue(adminCustomerListPage.getGridInfoText().contains("1-1 of 1 items"));
		sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, "Edit customer - Step 21: Verify that edited customer info is displayed in the customer list table");
		verifyEquals(adminCustomerListPage.getTextAtDynamicCellValueInTable(fullName, "Name"), editedFullName);
		// verifyTrue(adminCustomerListPage.getTextAtDynamicCellValueInTable(fullName, "Email").equals(email));
		verifyTrue(adminCustomerListPage.getTextAtDynamicCellValueInTable(fullName, "Company name").equals(editedCompanyName));
		String cusRoleString = Arrays.toString(cusRole);
		verifyTrue(cusRoleString.contains(adminCustomerListPage.getTextAtDynamicCellValueInTable(fullName, "Customer roles")));
		sleepInSecond(3);
	}

	@Test
	public void TC_07_Add_New_Address_In_Customer_Detail(Method method) {
		email = "hienng@gmail.com";
		firstName = "Hien";
		lastName = "Nguyen";
		companyName = "ABC";

		ExtentTestManager.startTest(method.getName(), "Add new address");
		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 1: Input to the 'Email' textbox with value: '" + editedEmail + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchEmail", editedEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 2: Input to the 'First name' textbox with value: '" + editedFirstName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchFirstName", editedFirstName);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 3: Input to the 'LastName' textbox with value: '" + editedLastName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchLastName", editedLastName);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 4: Input to the 'Company' textbox with value: '" + editedCompanyName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchCompany", editedCompanyName);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 5: Select from the 'Customer role' dropdown with value: '" + cusRoleString + "'");
		adminCustomerListPage.selectCustomerRoleDropdown(cusRole);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 6: Click on the 'Search' button");
		adminCustomerListPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 7: Click on the 'Edit' button of the customer row in table");
		adminNewCustomerPage = adminCustomerListPage.clickToEditButtonInTable();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 8: Click on 'Addresses' panel");
		adminNewCustomerPage.clickOnDynamicPanelByID("customer-address");

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 9: Click on 'Add new address' button to navigate to 'Address detail' page");
		adminAddressDetailPage = adminNewCustomerPage.clickAddNewAddressButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 10: Input to the 'First name' textbox with value: '" + firstName + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 11: Input to the 'Last name' textbox with value: '" + lastName + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 12: Input to the 'Email' textbox with value: '" + email + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_Email", email);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 13: Input to the 'Company' textbox with value: '" + companyName + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_Company", companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 14: Select from the 'Country' dropdown with value: '" + country + "'");
		adminAddressDetailPage.selectDynamicDropdownByID("Address_CountryId", country);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 15: Select from the 'State/Province' dropdown with value: '" + state + "'");
		adminAddressDetailPage.selectDynamicDropdownByID("Address_StateProvinceId", state);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 16: Input to the 'City' textbox with value: '" + city + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_City", city);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 17: Input to the 'Address 1' textbox with value: '" + address + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_Address1", address);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 18: Input to the 'Zip code' textbox with value: '" + zipCode + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_ZipPostalCode", zipCode);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 19: Input to the 'Phone number' textbox with value: '" + phoneNumber + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_PhoneNumber", phoneNumber);

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 20: Click on the 'Save' button");
		adminAddressDetailPage.clickSaveButton();

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 21: Verify that success message is displayed");
		verifyTrue(adminAddressDetailPage.getSuccessMessageText().contains("The new address has been added successfully."));

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 22: Click on the 'Back to customer detail' link to navigate to 'Customer detail' page");
		adminNewCustomerPage = adminAddressDetailPage.clickBackToCustomerDetailLink();

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 23: Verify that 'Address info' is displayed");
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(firstName));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(lastName));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(email));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(phoneNumber));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(country));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(state));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(city));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(address));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(zipCode));

		ExtentTestManager.getTest().log(Status.INFO, "Add new address - Step 24: Click on 'Back to customer list' link to navigate to 'Customer list' page");
		adminCustomerListPage = adminNewCustomerPage.clickBackToCustomerListLink();
	}

	@Test
	public void TC_08_Edit_Address_In_Customer_Detail(Method method) {
		email = "linhtran@gmail.com";
		firstName = "Linh";
		lastName = "Tran";
		companyName = "XYZ";
		country = "Vietnam";
		state = "Đà Nẵng";
		city = "Da Nang";
		address = "23 Ngu Hanh Son";
		zipCode = "730000";
		phoneNumber = "0960660666";

		ExtentTestManager.startTest(method.getName(), "Edit address");
		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 1: Input to the 'Email' textbox with value: '" + editedEmail + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchEmail", editedEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 2: Input to the 'First name' textbox with value: '" + editedFirstName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchFirstName", editedFirstName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 3: Input to the 'Last name' textbox with value: '" + editedLastName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchLastName", editedLastName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 4: Input to the 'Company' textbox with value: '" + editedCompanyName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchCompany", editedCompanyName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 5: Select from the 'Customer role' dropdown with value: '" + cusRoleString + "'");
		adminCustomerListPage.selectCustomerRoleDropdown(cusRole);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 6: Click on the 'Search' button");
		adminCustomerListPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 7: Click on the 'Edit' button of the customer row in the table");
		adminNewCustomerPage = adminCustomerListPage.clickToEditButtonInTable();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 8: Click on the 'Addresses' panel");
		adminNewCustomerPage.clickOnDynamicPanelByID("customer-address");

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 9: Click on the 'Edit' button of the address row in the table");
		adminAddressDetailPage = adminNewCustomerPage.clickOnEditButtonByRowIndex("1");
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 10: Input to the 'First name' textbox with value: '" + firstName + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 11: Input to the 'Last name' textbox with value: '" + lastName + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 12: Input to the 'Email' textbox with value: '" + email + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_Email", email);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 13: Input to the 'Company' textbox with value: '" + companyName + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_Company", companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 14: Select from the 'Country' dropdown with value: '" + country + "'");
		adminAddressDetailPage.selectDynamicDropdownByID("Address_CountryId", country);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 15: Select from the 'State/Province' dropdown with value: '" + state + "'");
		adminAddressDetailPage.selectDynamicDropdownByID("Address_StateProvinceId", state);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 16: Input to the 'City' textbox with value: '" + city + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_City", city);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 17: Input to the 'Address 1' textbox with value: '" + address + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_Address1", address);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 18: Input to the 'Zip code' textbox with value: '" + zipCode + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_ZipPostalCode", zipCode);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 19: Input to the 'Phone number' textbox with value: '" + phoneNumber + "'");
		adminAddressDetailPage.inputToDynamicTextboxByID("Address_PhoneNumber", phoneNumber);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 20: Click on the 'Save' button");
		adminAddressDetailPage.clickSaveButton();

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 21: Verify that the success message is displayed");
		verifyTrue(adminAddressDetailPage.getSuccessMessageText().contains("The address has been updated successfully."));

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 22: Click on the 'Back to customer detail' link");
		adminNewCustomerPage = adminAddressDetailPage.clickBackToCustomerDetailLink();

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 23: Click on the 'Addresses' panel");
		adminNewCustomerPage.clickOnDynamicPanelByID("customer-address");

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 24: Verify that edited 'Address info' is displayed");
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(firstName));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(lastName));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(email));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(phoneNumber));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(country));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(state));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(city));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(address));
		verifyTrue(adminNewCustomerPage.getTextOfAddressInfoRowInTable("customer-address").contains(zipCode));

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 25: Click on the 'Back to customer list' link");
		adminCustomerListPage = adminNewCustomerPage.clickBackToCustomerListLink();
	}

	@Test
	public void TC_09_Delete_Address_In_Customer_Detail(Method method) {
		ExtentTestManager.startTest(method.getName(), "Delete address");
		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 1: Input to the 'Email' textbox with value: '" + editedEmail + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchEmail", editedEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 2: Input to the 'First name' textbox with value: '" + editedFirstName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchFirstName", editedFirstName);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 3: Input to the 'Last name' textbox with value: '" + editedLastName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchLastName", editedLastName);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 4: Input to the 'Company' textbox with value: '" + editedCompanyName + "'");
		adminCustomerListPage.inputToDynamicTextbox("SearchCompany", editedCompanyName);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 5: Select from the 'Customer role' dropdown with value: '" + cusRoleString + "'");
		adminCustomerListPage.selectCustomerRoleDropdown(cusRole);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 6: Click on the 'Search' button");
		adminCustomerListPage.clickSearchButton();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 7: Click on the 'Edit' button of the customer row in the table");
		adminNewCustomerPage = adminCustomerListPage.clickToEditButtonInTable();
		sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 8: Click on the 'Addresses' panel");
		adminNewCustomerPage.clickOnDynamicPanelByID("customer-address");

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 9: Click on the 'Delete' button of the address row in the table");
		adminNewCustomerPage.clickOnDeleteButtonByRowIndex("1");
		sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 10: Verify that the 'No data available in table' message is displayed");
		verifyEquals(adminNewCustomerPage.getAlertMessageInTableByTableID("customer-address"), "No data available in table");
	}

	@AfterMethod
	public void afterMethod() {
		adminCustomerListPage.refreshCurrentPage(driver);
		sleepInSecond(7);
	}

	@AfterTest
	public void afterTest() {
		closeBrowserDriver();
	}
}
