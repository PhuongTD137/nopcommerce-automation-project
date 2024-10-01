package pageObjects.user;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.ProductDetailPageUI;

public class ProductDetailPageObject extends BasePage {
	WebDriver driver;

	public ProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getProductName() {
		waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_TITLE);
		return getElementText(driver, ProductDetailPageUI.PRODUCT_TITLE);
	}

	public String getProductSKU() {
		waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_SKU);
		return getElementText(driver, ProductDetailPageUI.PRODUCT_SKU);
	}

	public String getProductPrice() {
		waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_PRICE);
		return getElementText(driver, ProductDetailPageUI.PRODUCT_PRICE);
	}

	public void clickAddToWishlistButton() {
		waitForElementClickable(driver, ProductDetailPageUI.ADD_TO_WISHLIST_BUTTON);
		clickToElement(driver, ProductDetailPageUI.ADD_TO_WISHLIST_BUTTON);
	}

	public WishlistPageObject clickWishlistLinkInMessage() {
		waitForElementClickable(driver, ProductDetailPageUI.WISHLIST_LINK_ON_MESSAGE);
		clickToElement(driver, ProductDetailPageUI.WISHLIST_LINK_ON_MESSAGE);
		return PageGeneratorManager.openWishlistPage(driver);
	}

	public boolean isAddToWishlistSuccessMessageDisplayed(String message) {
		waitForElementVisible(driver, ProductDetailPageUI.DYNAMIC_MESSAGE, message);
		return isElementDisplayed(driver, ProductDetailPageUI.DYNAMIC_MESSAGE, message);
	}

	public void selectAtProcessorDropdown(String processorName) {
		waitForElementClickable(driver, ProductDetailPageUI.PROCESSOR_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductDetailPageUI.PROCESSOR_DROPDOWN, processorName);
	}

	public void selectAtRAMDropdown(String ramName) {
		waitForElementClickable(driver, ProductDetailPageUI.RAM_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductDetailPageUI.RAM_DROPDOWN, ramName);
	}

	public void selectHDDRadioButton(String hddName) {
		waitForElementClickable(driver, ProductDetailPageUI.HDD_RADIO_BUTTON, hddName);
		checkToDefaultCheckboxRadio(driver, ProductDetailPageUI.HDD_RADIO_BUTTON, hddName);
	}

	public void selectOSRadioButton(String osName) {
		waitForElementClickable(driver, ProductDetailPageUI.OS_RADIO_BUTTON, osName);
		checkToDefaultCheckboxRadio(driver, ProductDetailPageUI.OS_RADIO_BUTTON, osName);
	}

	public void selectSoftwareCheckbox(String softwareName) {
		waitForElementClickable(driver, ProductDetailPageUI.SOFTWARE_CHECKBOX, softwareName);
		uncheckToDefaultCheckbox(driver, ProductDetailPageUI.SOFTWARE_CHECKBOX, softwareName);
		checkToDefaultCheckboxRadio(driver, ProductDetailPageUI.SOFTWARE_CHECKBOX, softwareName);
	}

	public void clickAddToCartOrUpdateButton() {
		waitForElementClickable(driver, ProductDetailPageUI.ADD_TO_CART_BUTTON);
		clickToElement(driver, ProductDetailPageUI.ADD_TO_CART_BUTTON);
	}

	public boolean isResultMessageDisplayed(String message) {
		waitForElementVisible(driver, ProductDetailPageUI.DYNAMIC_MESSAGE, message);
		return isElementDisplayed(driver, ProductDetailPageUI.DYNAMIC_MESSAGE, message);
	}

	public List<String> saveProductInfoToList(String processorName, String ramName, String hddName, String osName, String... softwareNames) {
		List<String> productInfoTempList = new ArrayList<String>();
		productInfoTempList.add(processorName);
		productInfoTempList.add(ramName);
		productInfoTempList.add(hddName);
		productInfoTempList.add(osName);
		for (String softwareName : softwareNames) {
			productInfoTempList.add(softwareName);
		}
		return productInfoTempList;
	}

	public void hoverMouseToShoppingCartLink() {
		waitForElementVisible(driver, ProductDetailPageUI.SHOPPING_CART_LINK);
		scrollToElementByJS(driver, ProductDetailPageUI.SHOPPING_CART_LINK);
		hoverMouseToElement(driver, ProductDetailPageUI.SHOPPING_CART_LINK);
	}

	public List<String> getProductInfoAtShoppingCartLinkIntoList() {
		waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_INFO_AT_SHOPPING_CART);
		WebElement productInfo = getWebElement(driver, ProductDetailPageUI.PRODUCT_INFO_AT_SHOPPING_CART);
		String[] info = productInfo.getText().trim().split("\\r?\\n");

		List<String> productInfoTempList = new ArrayList<>();
		for (String item : info) {
			item = item.replaceFirst("Processor:", "").replaceFirst("RAM:", "").replaceFirst("HDD:", "").replaceFirst("OS:", "").replaceFirst("Software:", "").trim();
			productInfoTempList.add(item);
		}
		return productInfoTempList;
	}

	public void inputToQuantityTextbox(String number) {
		waitForElementVisible(driver, ProductDetailPageUI.QUANTITY_TEXTBOX);
		sendKeyToElement(driver, ProductDetailPageUI.QUANTITY_TEXTBOX, number);
	}

	public String getProductPriceFromShoppingCartLink() {
		waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_PRICE_AT_SHOPPING_CART);
		return getElementText(driver, ProductDetailPageUI.PRODUCT_PRICE_AT_SHOPPING_CART);
	}

	public String getProductQuantityFromShoppingCartLink() {
		waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_QUANTITY_AT_SHOPPING_CART);
		return getElementText(driver, ProductDetailPageUI.PRODUCT_QUANTITY_AT_SHOPPING_CART);
	}

	public boolean isSubTotalCorrect() {
		float expectedTotal, displayedTotal;
		float price = Float.parseFloat(getElementText(driver, ProductDetailPageUI.PRODUCT_PRICE_AT_SHOPPING_CART).replace(",", "").replace("$", ""));
		int quantity = Integer.parseInt(getElementText(driver, ProductDetailPageUI.PRODUCT_QUANTITY_AT_SHOPPING_CART));

		expectedTotal = price * quantity;
		displayedTotal = Float.parseFloat(getElementText(driver, ProductDetailPageUI.SUB_TOTAL_AT_SHOPPING_CART).replace(",", "").replace("$", ""));
		return expectedTotal == displayedTotal;
	}

	public String getCountItemMessageAtShoppingCartLink() {
		waitForElementVisible(driver, ProductDetailPageUI.COUNT_MESSAGE_AT_SHOPPING_CART);
		return getElementText(driver, ProductDetailPageUI.COUNT_MESSAGE_AT_SHOPPING_CART).trim();
	}

	public String getQuantityDiplayedAtShoppingCartLink() {
		waitForElementVisible(driver, ProductDetailPageUI.QUANTITY_DISPLAYED_AT_SHOPPING_CART_LINK);
		return getElementText(driver, ProductDetailPageUI.QUANTITY_DISPLAYED_AT_SHOPPING_CART_LINK).replace("(", "").replace(")", "");
	}

	public ShoppingCartPageObject clickShoppingCartLink() {
		waitForElementClickable(driver, ProductDetailPageUI.SHOPPING_CART_LINK);
		clickToElement(driver, ProductDetailPageUI.SHOPPING_CART_LINK);
		return PageGeneratorManager.openShoppingCartPage(driver);
	}

	public void unselectSoftwareCheckbox(String softwareName) {
		waitForElementClickable(driver, ProductDetailPageUI.SOFTWARE_CHECKBOX, softwareName);
		uncheckToDefaultCheckbox(driver, ProductDetailPageUI.SOFTWARE_CHECKBOX, softwareName);
	}

	public void closeResultMessageBar() {
		waitForElementClickable(driver, ProductDetailPageUI.CLOSE_MESSAGE_ICON);
		clickToElement(driver, ProductDetailPageUI.CLOSE_MESSAGE_ICON);
	}

}
