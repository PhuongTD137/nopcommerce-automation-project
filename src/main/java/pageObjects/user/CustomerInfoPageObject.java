package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.CustomerInfoPageUI;

public class CustomerInfoPageObject extends BasePage {
	WebDriver driver;

	public CustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectGenderRadioButton(String gender) {
		waitForElementClickable(driver, CustomerInfoPageUI.GENDER_RADIO_BUTTON, gender);
		checkToDefaultCheckboxRadio(driver, CustomerInfoPageUI.GENDER_RADIO_BUTTON, gender);
	}

	public void inputToDynamicTextbox(String labelName, String textValue) {
		waitForElementVisible(driver, CustomerInfoPageUI.DYNAMIC_TEXTBOX, labelName);
		sendKeyToElement(driver, CustomerInfoPageUI.DYNAMIC_TEXTBOX, textValue, labelName);
	}

	public void selectDynamicDropdown(String labelName, String textValue) {
		waitForElementClickable(driver, CustomerInfoPageUI.DYNAMIC_DROPDOWN, labelName);
		selectItemInDefaultDropdown(driver, CustomerInfoPageUI.DYNAMIC_DROPDOWN, textValue, labelName);
	}

	public void clickSaveButton() {
		waitForElementClickable(driver, CustomerInfoPageUI.SAVE_BUTTON);
		clickToElement(driver, CustomerInfoPageUI.SAVE_BUTTON);
	}

	public String getUpdatedMessage() {
		waitForElementVisible(driver, CustomerInfoPageUI.UPDATED_MESSAGE);
		return getElementText(driver, CustomerInfoPageUI.UPDATED_MESSAGE);
	}

}
