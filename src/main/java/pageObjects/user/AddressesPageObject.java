package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.AddressesPageUI;

public class AddressesPageObject extends BasePage {
	WebDriver driver;

	public AddressesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickAddNewButton() {
		waitForElementClickable(driver, AddressesPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AddressesPageUI.ADD_NEW_BUTTON);
	}

	public void inputToDynamicTextbox(String labelName, String textValue) {
		waitForElementVisible(driver, AddressesPageUI.DYNAMIC_TEXTBOX, labelName);
		sendKeyToElement(driver, AddressesPageUI.DYNAMIC_TEXTBOX, textValue, labelName);
	}

	public void selectDynamicDropdown(String labelName, String option) {
		waitForElementClickable(driver, AddressesPageUI.DYNAMIC_DROPDOWN, labelName);
		selectItemInDefaultDropdown(driver, AddressesPageUI.DYNAMIC_DROPDOWN, option, labelName);
	}

	public void clickSaveButton() {
		waitForElementClickable(driver, AddressesPageUI.SAVE_BUTTON);
		clickToElement(driver, AddressesPageUI.SAVE_BUTTON);
	}

	public String getSuccessMessage() {
		waitForElementVisible(driver, AddressesPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, AddressesPageUI.SUCCESS_MESSAGE);
	}

	public String getDynamicInfoText(String labelName) {
		waitForElementVisible(driver, AddressesPageUI.DYNAMIC_INFO_TEXT, labelName);
		return getElementText(driver, AddressesPageUI.DYNAMIC_INFO_TEXT, labelName);
	}

	public void clickCloseIcon() {
		waitForElementClickable(driver, AddressesPageUI.CLOSE_ICON);
		clickToElement(driver, AddressesPageUI.CLOSE_ICON);
	}
}
