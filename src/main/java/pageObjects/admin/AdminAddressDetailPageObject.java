package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.admin.AdminAddressDetailPageUI;

public class AdminAddressDetailPageObject extends BasePage {
	WebDriver driver;

	public AdminAddressDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToDynamicTextboxByID(String idValue, String textValue) {
		waitForElementVisible(driver, AdminAddressDetailPageUI.DYNAMIC_TEXTBOX_BY_ID, idValue);
		sendKeyToElement(driver, AdminAddressDetailPageUI.DYNAMIC_TEXTBOX_BY_ID, textValue, idValue);
	}

	public void selectDynamicDropdownByID(String idValue, String option) {
		waitForElementClickable(driver, AdminAddressDetailPageUI.DYNAMIC_DROPDOWN_BY_ID, idValue);
		selectItemInDefaultDropdown(driver, AdminAddressDetailPageUI.DYNAMIC_DROPDOWN_BY_ID, option, idValue);
	}

	public void clickSaveButton() {
		waitForElementClickable(driver, AdminAddressDetailPageUI.SAVE_BUTTON);
		clickToElement(driver, AdminAddressDetailPageUI.SAVE_BUTTON);
	}

	public String getSuccessMessageText() {
		waitForElementVisible(driver, AdminAddressDetailPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, AdminAddressDetailPageUI.SUCCESS_MESSAGE);
	}

	public AdminNewCustomerPageObject clickBackToCustomerDetailLink() {
		waitForElementClickable(driver, AdminAddressDetailPageUI.BACK_TO_CUSTOMER_DETAIL_LINK);
		clickToElement(driver, AdminAddressDetailPageUI.BACK_TO_CUSTOMER_DETAIL_LINK);
		return PageGeneratorManager.openNewAdminCustomerPage(driver);
	}
}
