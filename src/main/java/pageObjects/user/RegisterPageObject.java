package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public String getFirstNameErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
	}

	public String getLastNameErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getConfirmPasswordErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
	}

	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	public void inputToConfirmPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
	}

	public void selectGenderRadioButton(String gender) {
		waitForElementClickable(driver, RegisterPageUI.GENDER_RADIO_BUTTON, gender);
		checkToDefaultCheckboxRadio(driver, RegisterPageUI.GENDER_RADIO_BUTTON, gender);
	}

	public void selectBirthDateDropdown(String birthDate) {
		waitForElementClickable(driver, RegisterPageUI.BIRTH_DATE_DROPDOWN);
		selectItemInDefaultDropdown(driver, RegisterPageUI.BIRTH_DATE_DROPDOWN, birthDate);
	}

	public void selectBirthMonthDropdown(String birthMonth) {
		waitForElementClickable(driver, RegisterPageUI.BIRTH_MONTH_DROPDOWN);
		selectItemInDefaultDropdown(driver, RegisterPageUI.BIRTH_MONTH_DROPDOWN, birthMonth);
	}

	public void selectBirthYearDropdown(String birthYear) {
		waitForElementClickable(driver, RegisterPageUI.BIRTH_YEAR_DROPDOWN);
		selectItemInDefaultDropdown(driver, RegisterPageUI.BIRTH_YEAR_DROPDOWN, birthYear);
	}

	public void uncheckToNewsletterCheckbox() {
		waitForElementClickable(driver, RegisterPageUI.NEWSLETTER_CHECKBOX);
		uncheckToDefaultCheckbox(driver, RegisterPageUI.NEWSLETTER_CHECKBOX);
	}

	public String getSuccessRegistrationMessage() {
		waitForElementVisible(driver, RegisterPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUI.SUCCESS_MESSAGE);
	}

	public void refreshPage() {
		refreshCurrentPage(driver);
	}

	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public HomePageObject clickContinueButton() {
		waitForElementClickable(driver, RegisterPageUI.CONTINUE_BUTTON);
		clickToElement(driver, RegisterPageUI.CONTINUE_BUTTON);
		return PageGeneratorManager.openHomePage(driver);
	}

	public String getSummaryErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.SUMMARY_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.SUMMARY_ERROR_MESSAGE);
	}

}
