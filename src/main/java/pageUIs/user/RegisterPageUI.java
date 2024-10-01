package pageUIs.user;

public class RegisterPageUI {
	public static final String FIRST_NAME_TEXTBOX = "id=FirstName";
	public static final String LAST_NAME_TEXTBOX = "id=LastName";
	public static final String EMAIL_TEXTBOX = "id=Email";
	public static final String GENDER_RADIO_BUTTON = "xpath=//span[@class='%s']/input";
	public static final String BIRTH_DATE_DROPDOWN = "xpath=//select[@name='DateOfBirthDay']";
	public static final String BIRTH_MONTH_DROPDOWN = "xpath=//select[@name='DateOfBirthMonth']";
	public static final String BIRTH_YEAR_DROPDOWN = "xpath=//select[@name='DateOfBirthYear']";
	public static final String NEWSLETTER_CHECKBOX = "id=Newsletter";
	public static final String PASSWORD_TEXTBOX = "id=Password";
	public static final String CONFIRM_PASSWORD_TEXTBOX = "id=ConfirmPassword";
	public static final String FIRST_NAME_ERROR_MESSAGE = "xpath=//span[@data-valmsg-for='FirstName']";
	public static final String LAST_NAME_ERROR_MESSAGE = "xpath=//span[@data-valmsg-for='LastName']";
	public static final String EMAIL_ERROR_MESSAGE = "xpath=//span[@data-valmsg-for='Email']";
	public static final String PASSWORD_ERROR_MESSAGE = "xpath=//span[@data-valmsg-for='Password']";
	public static final String CONFIRM_PASSWORD_ERROR_MESSAGE = "xpath=//span[@data-valmsg-for='ConfirmPassword']";
	public static final String REGISTER_BUTTON = "id=register-button";
	public static final String SUCCESS_MESSAGE = "css=div.result";
	public static final String CONTINUE_BUTTON = "xpath=//a[text()='Continue']";
	public static final String SUMMARY_ERROR_MESSAGE = "xpath=//div[contains(@class, 'validation-summary-errors')]//li";
}
