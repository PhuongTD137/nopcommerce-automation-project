package pageObjects.admin;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.admin.AdminNewCustomerPageUI;

public class AdminNewCustomerPageObject extends BasePage {
	WebDriver driver;

	public AdminNewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToDynamicTextbox(String idValue, String textValue) {
		waitForElementVisible(driver, AdminNewCustomerPageUI.DYNAMIC_TEXTBOX_BY_ID, idValue);
		sendKeyToElement(driver, AdminNewCustomerPageUI.DYNAMIC_TEXTBOX_BY_ID, textValue, idValue);
	}

	public void selectRadioButton(String gender) {
		waitForElementClickable(driver, AdminNewCustomerPageUI.GENDER_RADIO_BUTTON, gender);
		checkToDefaultCheckboxRadio(driver, AdminNewCustomerPageUI.GENDER_RADIO_BUTTON, gender);
	}

	public void selectCustomerRolesDropdown(String customerRole) {
		waitForElementClickable(driver, AdminNewCustomerPageUI.CUSTOMER_ROLE_DROPDOWN);

		List<WebElement> deleteIcons = getListWebElement(driver, AdminNewCustomerPageUI.DELETE_ICON_AT_SELECTED_OPTION);
		for (WebElement icon : deleteIcons) {
			icon.click();
		}
		sleepInSecond(1);

		sendKeyToElement(driver, AdminNewCustomerPageUI.CUSTOMER_ROLE_TEXTBOX_AT_DROPDOWN, customerRole);

		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		List<WebElement> allOptions = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(AdminNewCustomerPageUI.ALL_OPTIONS_IN_DROPDOWN)));
		for (WebElement option : allOptions) {
			if (option.getText().equalsIgnoreCase(customerRole)) {
				option.click();
			}
		}

		pressKeyToElement(driver, AdminNewCustomerPageUI.CUSTOMER_ROLE_TEXTBOX_AT_DROPDOWN, Keys.ENTER);
		pressKeyToElement(driver, AdminNewCustomerPageUI.CUSTOMER_ROLE_TEXTBOX_AT_DROPDOWN, Keys.ESCAPE);
	}

	// 2
	public void selectCustomerRolesDropdown(String[] cusRole) {
		waitForElementClickable(driver, AdminNewCustomerPageUI.CUSTOMER_ROLE_DROPDOWN);

		List<WebElement> deleteIcons = getListWebElement(driver, AdminNewCustomerPageUI.DELETE_ICON_AT_SELECTED_OPTION);
		for (WebElement icon : deleteIcons) {
			icon.click();
		}
		sleepInSecond(1);

		for (String role : cusRole) {
			sendKeyToElement(driver, AdminNewCustomerPageUI.CUSTOMER_ROLE_TEXTBOX_AT_DROPDOWN, role);

			WebDriverWait explicitWait = new WebDriverWait(driver, 30);
			List<WebElement> allOptions = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(AdminNewCustomerPageUI.ALL_OPTIONS_IN_DROPDOWN)));
			for (WebElement option : allOptions) {
				if (option.getText().equalsIgnoreCase(role)) {
					option.click();
				}
			}

		}

		pressKeyToElement(driver, AdminNewCustomerPageUI.CUSTOMER_ROLE_TEXTBOX_AT_DROPDOWN, Keys.ENTER);
		pressKeyToElement(driver, AdminNewCustomerPageUI.CUSTOMER_ROLE_TEXTBOX_AT_DROPDOWN, Keys.ESCAPE);
	}

	public void selectActiveCheckbox() {
		waitForElementClickable(driver, AdminNewCustomerPageUI.ACTIVE_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, AdminNewCustomerPageUI.ACTIVE_CHECKBOX);
	}

	public void clickSaveAndContinueEditButton() {
		waitForElementClickable(driver, AdminNewCustomerPageUI.SAVE_AND_CONTINUE_EDIT_BUTTON);
		clickToElement(driver, AdminNewCustomerPageUI.SAVE_AND_CONTINUE_EDIT_BUTTON);
	}

	public void inputToAdminCommentTextArea(String textValue) {
		waitForElementVisible(driver, AdminNewCustomerPageUI.ADMIN_COMMENT_TEXTAREA);
		sendKeyToElement(driver, AdminNewCustomerPageUI.ADMIN_COMMENT_TEXTAREA, textValue);
	}

	public String getDynamicTextboxValue(String idValue) {
		waitForElementVisible(driver, AdminNewCustomerPageUI.DYNAMIC_TEXTBOX_BY_ID, idValue);
		return getElementAttribute(driver, AdminNewCustomerPageUI.DYNAMIC_TEXTBOX_BY_ID, "value", idValue).trim();
	}

	public String getCheckedGenderValue() {
		waitForElementVisible(driver, AdminNewCustomerPageUI.CHECKED_GENDER_RADIO_BUTTON);
		return getElementAttribute(driver, AdminNewCustomerPageUI.CHECKED_GENDER_RADIO_BUTTON, "value").trim();
	}

	public String getStringOfSelectedCustomerRoleList() {
		List<WebElement> selectedItems = getListWebElement(driver, AdminNewCustomerPageUI.SELECTED_ITEM_IN_CUSTOMER_ROLE_DROPDOWN);
		ArrayList<String> selectedList = new ArrayList<String>();

		for (WebElement item : selectedItems) {
			selectedList.add(item.getText().trim().replace("×", "").replace("\n", ""));
		}
		return selectedList.toString();
	}

	public String getAdminCommentTextAreaValue() {
		waitForElementVisible(driver, AdminNewCustomerPageUI.ADMIN_COMMENT_TEXTAREA);
		return getElementText(driver, AdminNewCustomerPageUI.ADMIN_COMMENT_TEXTAREA).trim();
	}

	public boolean isSuccessMessageDisplayed(String message) {
		waitForElementVisible(driver, AdminNewCustomerPageUI.SUCCESS_MESSAGE, message);
		return isElementDisplayed(driver, AdminNewCustomerPageUI.SUCCESS_MESSAGE, message);
	}

	public AdminCustomerListPageObject clickBackToCustomerListLink() {
		waitForElementClickable(driver, AdminNewCustomerPageUI.BACK_TO_CUSTOMER_LIST_LINK);
		clickToElement(driver, AdminNewCustomerPageUI.BACK_TO_CUSTOMER_LIST_LINK);
		return PageGeneratorManager.openAdminCustomerListPage(driver);
	}

	public AdminCustomerListPageObject clickSaveButton() {
		waitForElementClickable(driver, AdminNewCustomerPageUI.SAVE_BUTTON);
		clickToElement(driver, AdminNewCustomerPageUI.SAVE_BUTTON);
		return PageGeneratorManager.openAdminCustomerListPage(driver);
	}

	public void clickOnDynamicPanelByID(String name) {
		waitForElementClickable(driver, AdminNewCustomerPageUI.DYNAMIC_PANEL_BY_ID, name);
		scrollToElementByJS(driver, AdminNewCustomerPageUI.DYNAMIC_PANEL_BY_ID, name);
		sleepInSecond(3);
		clickToElementByJS(driver, AdminNewCustomerPageUI.DYNAMIC_PANEL_BY_ID, name);
	}

	public AdminAddressDetailPageObject clickAddNewAddressButton() {
		waitForElementClickable(driver, AdminNewCustomerPageUI.ADD_NEW_ADDRESS_BUTTON);
		clickToElement(driver, AdminNewCustomerPageUI.ADD_NEW_ADDRESS_BUTTON);
		return PageGeneratorManager.openAddressDetailPage(driver);
	}

	public String getTextOfAddressInfoRowInTable(String tableID) {
		waitForElementVisible(driver, AdminNewCustomerPageUI.ROW_BY_TABLE_ID, tableID);
		return getElementText(driver, AdminNewCustomerPageUI.ROW_BY_TABLE_ID, tableID);
	}

	public AdminAddressDetailPageObject clickOnEditButtonByRowIndex(String rowIndex) {
		waitForElementClickable(driver, AdminNewCustomerPageUI.EDIT_ADDRESS_BUTTON_BY_ROW_INDEX, rowIndex);
		clickToElement(driver, AdminNewCustomerPageUI.EDIT_ADDRESS_BUTTON_BY_ROW_INDEX, rowIndex);
		return PageGeneratorManager.openAddressDetailPage(driver);
	}

	public void clickOnDeleteButtonByRowIndex(String rowNumber) {
		waitForElementClickable(driver, AdminNewCustomerPageUI.DELETE_ADDRESS_BUTTON_BY_ROW_INDEX, rowNumber);
		clickToElement(driver, AdminNewCustomerPageUI.DELETE_ADDRESS_BUTTON_BY_ROW_INDEX, rowNumber);
		waitForAlertPresence(driver);
		acceptAlert(driver);
	}

	public String getAlertMessageInTableByTableID(String tableID) {
		waitForElementVisible(driver, AdminNewCustomerPageUI.ALERT_MESSAGE_IN_TABLE_BY_TABLE_ID, tableID);
		return getElementText(driver, AdminNewCustomerPageUI.ALERT_MESSAGE_IN_TABLE_BY_TABLE_ID, tableID);
	}
}
