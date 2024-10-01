package pageObjects.admin;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.admin.AdminCustomerListPageUI;

public class AdminCustomerListPageObject extends BasePage {
	WebDriver driver;

	public AdminCustomerListPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminNewCustomerPageObject clickToAddNewButton() {
		waitForElementClickable(driver, AdminCustomerListPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminCustomerListPageUI.ADD_NEW_BUTTON);
		return PageGeneratorManager.openNewAdminCustomerPage(driver);
	}

	public void selectCustomerRoleDropdown(String[] cusRoles) {
		waitForElementClickable(driver, AdminCustomerListPageUI.ALL_CUSTOMER_ROLE_DELETE_ICON);
		List<WebElement> deleteIcons = getListWebElement(driver, AdminCustomerListPageUI.ALL_CUSTOMER_ROLE_DELETE_ICON);
		for (WebElement icon : deleteIcons) {
			icon.click();
		}

		for (String role : cusRoles) {
			sendKeyToElement(driver, AdminCustomerListPageUI.CUSTOMER_ROLE_TEXTBOX, role);
			pressKeyToElement(driver, AdminCustomerListPageUI.CUSTOMER_ROLE_TEXTBOX, Keys.ENTER);
			pressKeyToElement(driver, AdminCustomerListPageUI.CUSTOMER_ROLE_TEXTBOX, Keys.ESCAPE);
		}
	}

	public void clickSearchButton() {
		waitForElementClickable(driver, AdminCustomerListPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminCustomerListPageUI.SEARCH_BUTTON);
	}

	public String getTextAtDynamicCellValueInTable(String fullName, String columnName) {
		int rowNumber = getElementSize(driver, AdminCustomerListPageUI.ROW_BY_CUSTOMER_NAME, fullName) + 1;
		int colNumber = getElementSize(driver, AdminCustomerListPageUI.COLUMN_BY_COLUMN_NAME, columnName) + 1;
		waitForElementVisible(driver, AdminCustomerListPageUI.DYNAMIC_CELL_BY_ROW_INDEX_COLUMN_INDEX, String.valueOf(rowNumber), String.valueOf(colNumber));
		System.out.println(getElementText(driver, AdminCustomerListPageUI.DYNAMIC_CELL_BY_ROW_INDEX_COLUMN_INDEX, String.valueOf(rowNumber), String.valueOf(colNumber)).trim());
		return getElementText(driver, AdminCustomerListPageUI.DYNAMIC_CELL_BY_ROW_INDEX_COLUMN_INDEX, String.valueOf(rowNumber), String.valueOf(colNumber)).trim();
	}

	public void inputToDynamicTextbox(String idValue, String textValue) {
		waitForElementVisible(driver, AdminCustomerListPageUI.DYNAMIC_TEXTBOX_BY_ID, idValue);
		sendKeyToElement(driver, AdminCustomerListPageUI.DYNAMIC_TEXTBOX_BY_ID, textValue, idValue);
	}

	public int getNumberOfRowDisplayed() {
		int rowNumber = getElementSize(driver, AdminCustomerListPageUI.ROW_DISPLAYED_IN_TABLE);
		return rowNumber;
	}

	public String getGridInfoText() {
		waitForElementVisible(driver, AdminCustomerListPageUI.ITEM_DISPLAYED_TEXT);
		return getElementText(driver, AdminCustomerListPageUI.ITEM_DISPLAYED_TEXT);
	}

	public AdminNewCustomerPageObject clickToEditButtonInTable() {
		waitForElementClickable(driver, AdminCustomerListPageUI.EDIT_BUTTON_ON_ROW);
		clickToElement(driver, AdminCustomerListPageUI.EDIT_BUTTON_ON_ROW);
		return PageGeneratorManager.openNewAdminCustomerPage(driver);
	}

	public String getSuccessMessageText() {
		waitForElementVisible(driver, AdminCustomerListPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, AdminCustomerListPageUI.SUCCESS_MESSAGE).trim();
	}
}
