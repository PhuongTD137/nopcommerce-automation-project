package pageObjects.user;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.user.SearchPageUI;

public class SearchPageObject extends BasePage {
	WebDriver driver;

	public SearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToSearchKeyWordTextbox(String textValue) {
		waitForElementVisible(driver, SearchPageUI.SEARCH_KEYWORD_TEXTBOX);
		sendKeyToElement(driver, SearchPageUI.SEARCH_KEYWORD_TEXTBOX, textValue);
	}

	public void clickSearchButton() {
		waitForElementClickable(driver, SearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, SearchPageUI.SEARCH_BUTTON);
	}

	public String getNoResultMessage() {
		waitForElementVisible(driver, SearchPageUI.NO_RESULT_MESSAGE);
		return getElementText(driver, SearchPageUI.NO_RESULT_MESSAGE);
	}

	public String getWarningMessage() {
		waitForElementVisible(driver, SearchPageUI.WARNING_MESSAGE);
		return getElementText(driver, SearchPageUI.WARNING_MESSAGE);
	}

	public boolean isProductTitleDisplayedContain(String verifyText) {
		waitForElementVisible(driver, SearchPageUI.PRODUCT_TITLE);
		List<WebElement> titleList = getListWebElement(driver, SearchPageUI.PRODUCT_TITLE);
		boolean check = false;
		for (WebElement title : titleList) {
			if (title.getText().trim().contains(verifyText)) {
				check = true;
			} else {
				check = false;
				break;
			}
		}
		return check;
	}

	public void checkToAdvancedSearchCheckbox() {
		waitForElementClickable(driver, SearchPageUI.ADVANCED_SEARCH_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, SearchPageUI.ADVANCED_SEARCH_CHECKBOX);
	}

	public void selectCategoryDropdown(String option) {
		waitForElementClickable(driver, SearchPageUI.CATEGORY_DROPDOWN);
		selectItemInDefaultDropdown(driver, SearchPageUI.CATEGORY_DROPDOWN, option);
	}

	public void checkToSearchSubCategoryCheckbox() {
		waitForElementClickable(driver, SearchPageUI.SEARCH_SUB_CATEGORY_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, SearchPageUI.SEARCH_SUB_CATEGORY_CHECKBOX);
	}

	public void uncheckToSearchSubCategoryCheckbox() {
		waitForElementClickable(driver, SearchPageUI.SEARCH_SUB_CATEGORY_CHECKBOX);
		uncheckToDefaultCheckbox(driver, SearchPageUI.SEARCH_SUB_CATEGORY_CHECKBOX);
	}

	public void selectManufacturerDropdown(String option) {
		waitForElementClickable(driver, SearchPageUI.MANUFACTURER_DROPDOWN);
		selectItemInDefaultDropdown(driver, SearchPageUI.MANUFACTURER_DROPDOWN, option);
	}
}
