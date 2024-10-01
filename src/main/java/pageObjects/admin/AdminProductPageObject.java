package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.AdminProductPageUI;

public class AdminProductPageObject extends BasePage {
	WebDriver driver;

	public AdminProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToDynamicTextbox(String fieldName, String value) {
		waitForElementVisible(driver, AdminProductPageUI.DYNAMIC_TEXTBOX, fieldName);
		sendKeyToElement(driver, AdminProductPageUI.DYNAMIC_TEXTBOX, value, fieldName);
	}

	public void clickSearchButton() {
		waitForElementClickable(driver, AdminProductPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminProductPageUI.SEARCH_BUTTON);
	}

	public void selectDynamicDropdownByLabelName(String labelName, String option) {
		waitForElementClickable(driver, AdminProductPageUI.DYNAMIC_DROPDOWN, labelName);
		selectItemInDefaultDropdown(driver, AdminProductPageUI.DYNAMIC_DROPDOWN, option, labelName);
	}

	public void uncheckSearchCategoriesCheckbox() {
		waitForElementClickable(driver, AdminProductPageUI.SEARCH_CATEGORIES_CHECKBOX);
		uncheckToDefaultCheckbox(driver, AdminProductPageUI.SEARCH_CATEGORIES_CHECKBOX);
	}

	public boolean isNoDataAvailableMessageDisplayed() {
		waitForElementVisible(driver, AdminProductPageUI.NO_DATA_AVAILABLE_MESSAGE);
		return isElementDisplayed(driver, AdminProductPageUI.NO_DATA_AVAILABLE_MESSAGE);
	}

	public void checkSearchCategoriesCheckbox() {
		waitForElementClickable(driver, AdminProductPageUI.SEARCH_CATEGORIES_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, AdminProductPageUI.SEARCH_CATEGORIES_CHECKBOX);
	}

	public void clickGoButton() {

	}

	public String getDynamicCellValueInTable(String columnName) {
		int colNumber = getElementSize(driver, AdminProductPageUI.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		waitForElementVisible(driver, AdminProductPageUI.DYNAMIC_CELL_VALUE_IN_TABLE, String.valueOf(colNumber));
		return getElementText(driver, AdminProductPageUI.DYNAMIC_CELL_VALUE_IN_TABLE, String.valueOf(colNumber));
	}

	public boolean isPublishedIconDisplayed(String columnName) {
		int colNumber = getElementSize(driver, AdminProductPageUI.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		waitForElementVisible(driver, AdminProductPageUI.DYNAMIC_CELL_VALUE_IN_TABLE, String.valueOf(colNumber));
		return isElementDisplayed(driver, AdminProductPageUI.DYNAMIC_CELL_VALUE_IN_TABLE, String.valueOf(colNumber));
	}
}
