package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.ChangePasswordPageUI;

public class ChangePasswordPageObject extends BasePage {
	WebDriver driver;

	public ChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToDynamicTextbox(String labelName, String textValue) {
		waitForElementVisible(driver, ChangePasswordPageUI.DYNAMIC_TEXTBOX, labelName);
		sendKeyToElement(driver, ChangePasswordPageUI.DYNAMIC_TEXTBOX, textValue, labelName);
	}

	public void clickChangePasswordButton() {
		waitForElementClickable(driver, ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
		clickToElement(driver, ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
	}

	public String getSuccessMessage() {
		waitForElementVisible(driver, ChangePasswordPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, ChangePasswordPageUI.SUCCESS_MESSAGE);
	}

	public void clickToCloseIcon() {
		waitForElementClickable(driver, ChangePasswordPageUI.CLOSE_ICON);
		clickToElement(driver, ChangePasswordPageUI.CLOSE_ICON);
	}

}
