package pageObjects.user;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.ShoppingCartPageUI;

public class ShoppingCartPageObject extends BasePage {
	WebDriver driver;

	public ShoppingCartPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductNumberDecreasedAtWishlistLink() {
		int numberAtWl = Integer.parseInt(getElementText(driver, ShoppingCartPageUI.NUMBER_AT_WISHLIST_LINK).replace("(", "").replace(")", ""));
		return numberAtWl == WishlistPageObject.remainQty;
	}

	public boolean isProductNumberAddedToShoppingCartLink() {
		int numberAtWl = Integer.parseInt(getElementText(driver, ShoppingCartPageUI.NUMBER_AT_SHOPPING_CART_LINK).replace("(", "").replace(")", ""));
		return numberAtWl == WishlistPageObject.checkedQty;
	}

	public ProductDetailPageObject clickEditButton(String productName) {
		waitForElementClickable(driver, ShoppingCartPageUI.EDIT_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(driver, ShoppingCartPageUI.EDIT_BUTTON_BY_PRODUCT_NAME, productName);
		return PageGeneratorManager.openProductDetailPage(driver);
	}

	public void clickRemoveIcon(String productName) {
		waitForElementClickable(driver, ShoppingCartPageUI.REMOVE_ICON_BY_PRODUCT_NAME, productName);
		clickToElement(driver, ShoppingCartPageUI.REMOVE_ICON_BY_PRODUCT_NAME, productName);
	}

	public String getMessageAtShoppingCartPage() {
		waitForElementVisible(driver, ShoppingCartPageUI.ORDER_SUMMARY_MESSAGE);
		return getElementText(driver, ShoppingCartPageUI.ORDER_SUMMARY_MESSAGE).trim();
	}

	public void hoverMouseToShoppingCartLink() {
		waitForElementVisible(driver, ShoppingCartPageUI.SHOPPING_CART_LINK);
		hoverMouseToElement(driver, ShoppingCartPageUI.SHOPPING_CART_LINK);
	}

	public String getMessageAtShoppingCartLink() {
		waitForElementVisible(driver, ShoppingCartPageUI.SHOPPING_CART_COUNT_MESSAGE);
		return getElementText(driver, ShoppingCartPageUI.SHOPPING_CART_COUNT_MESSAGE).trim();
	}

	public void selectGiftWrappingDropdown(String textValue) {
		waitForElementClickable(driver, ShoppingCartPageUI.GIFT_WRAPPING_DROPDOWN);
		selectItemInDefaultDropdown(driver, ShoppingCartPageUI.GIFT_WRAPPING_DROPDOWN, textValue);
	}

	public float convertStringToFloat(WebElement element, String... replacedValues) {
		String elementText = element.getText();
		for (String replacedValue : replacedValues) {
			elementText = elementText.replace(replacedValue, "").trim();
		}
		return Float.parseFloat(elementText);
	}

	public boolean isSubTotalDisplayedCorrectly() {
		waitForElementVisible(driver, ShoppingCartPageUI.SUB_TOTAL_VALUE_TEXT);

		List<WebElement> allSubTotal = getListWebElement(driver, ShoppingCartPageUI.ALL_TOTAL_VALUE_CELL);
		float expectedSubTotalValue = 0;
		for (WebElement subTotal : allSubTotal) {
			expectedSubTotalValue += convertStringToFloat(subTotal, "$", ",");
		}

		float giftWrappingFeeValue;
		if (getGiftWrappingText().equals("Gift wrapping: Yes [+$10.00]")) {
			giftWrappingFeeValue = convertStringToFloat(getWebElement(driver, ShoppingCartPageUI.GIFT_WRAPPING_TEXT), "Gift wrapping: Yes [+$", "]");
		} else {
			giftWrappingFeeValue = 0.00f;
		}

		expectedSubTotalValue += giftWrappingFeeValue;

		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
		String formattedExpectedSubTotal = currencyFormatter.format(expectedSubTotalValue);
		String displayedSubTotal = getDisplayedSubTotalText();

		return formattedExpectedSubTotal.equals(displayedSubTotal);
	}

	public boolean isTotalDisplayedCorrectly() {
		float subTotal = convertStringToFloat(getWebElement(driver, ShoppingCartPageUI.SUB_TOTAL_VALUE_TEXT), "$", ",");
		float shipping = convertStringToFloat(getWebElement(driver, ShoppingCartPageUI.SHIPPING_COST_TEXT), "$");
		float tax = convertStringToFloat(getWebElement(driver, ShoppingCartPageUI.TAX_VALUE_TEXT), "$");
		float expectedTotal = subTotal + shipping + tax;

		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
		String formattedExpectedTotal = currencyFormatter.format(expectedTotal);

		String displayedTotal = getDisplayedTotalText();

		return formattedExpectedTotal.equals(displayedTotal);
	}

	public boolean isPointEarnedDisplayedCorrectly() {
		float total = convertStringToFloat(getWebElement(driver, ShoppingCartPageUI.TOTAL_VALUE_TEXT), "$", ",");
		total = Math.round(total);
		int point = (int) total / 10;
		String expectedPointText = String.valueOf(point) + " points";
		String displayedPointText = getDisplayedRewardPointText();

		return expectedPointText.equals(displayedPointText);
	}

	public void checkAgreeWithTheTermCheckbox() {
		waitForElementClickable(driver, ShoppingCartPageUI.TERM_OF_SERVICE_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, ShoppingCartPageUI.TERM_OF_SERVICE_CHECKBOX);
	}

	public CheckoutPageObject clickCheckoutButton() {
		waitForElementClickable(driver, ShoppingCartPageUI.CHECKOUT_BUTTON);
		checkToDefaultCheckboxRadio(driver, ShoppingCartPageUI.CHECKOUT_BUTTON);
		return PageGeneratorManager.openCheckoutPage(driver);
	}

	public String getDisplayedSubTotalText() {
		waitForElementVisible(driver, ShoppingCartPageUI.SUB_TOTAL_VALUE_TEXT);
		return getElementText(driver, ShoppingCartPageUI.SUB_TOTAL_VALUE_TEXT).trim();
	}

	public String getDisplayedTotalText() {
		waitForElementVisible(driver, ShoppingCartPageUI.TOTAL_VALUE_TEXT);
		return getElementText(driver, ShoppingCartPageUI.TOTAL_VALUE_TEXT).trim();
	}

	public String getDisplayedRewardPointText() {
		waitForElementVisible(driver, ShoppingCartPageUI.REWARD_POINT_TEXT);
		return getElementText(driver, ShoppingCartPageUI.REWARD_POINT_TEXT).trim();
	}

	public String getGiftWrappingText() {
		waitForElementVisible(driver, ShoppingCartPageUI.GIFT_WRAPPING_TEXT);
		return getElementText(driver, ShoppingCartPageUI.GIFT_WRAPPING_TEXT).trim();
	}

	public ArrayList<Map<String, String>> saveListOfProductInfo() {
		ArrayList<Map<String, String>> products = new ArrayList<Map<String, String>>();
		int rowNumber = getListWebElement(driver, ShoppingCartPageUI.ALL_ROW).size();
		for (int i = 1; i <= rowNumber; i++) {
			Map<String, String> productMap = new HashMap<>();
			productMap.put("SKU", getSKUValueByRowNumber(i));
			productMap.put("Name", getProductNameByRowNumber(i));
			productMap.put("Price", getProductPriceByRowNumber(i));
			productMap.put("Quantity", getProductQuantityByRowNumber(i));
			productMap.put("Total", getProductTotalByRowNumber(i));
			products.add(productMap);
		}
		System.out.println("thong tin product at SC:");
		for (Map<String, String> map : products) {
			System.out.println(map);
		}
		return products;
	}

	public String getSKUValueByRowNumber(int rowNumber) {
		return getElementText(driver, ShoppingCartPageUI.SKU_BY_ROW_NUMBER, String.valueOf(rowNumber)).trim();
	}

	public String getProductNameByRowNumber(int rowNumber) {
		return getElementText(driver, ShoppingCartPageUI.PRODUCT_NAME_BY_ROW_NUMBER, String.valueOf(rowNumber)).trim();
	}

	public String getProductPriceByRowNumber(int rowNumber) {
		return getElementText(driver, ShoppingCartPageUI.PRICE_BY_ROW_NUMBER, String.valueOf(rowNumber)).trim();
	}

	public String getProductQuantityByRowNumber(int rowNumber) {
		return getElementAttribute(driver, ShoppingCartPageUI.QUANTITY_BY_ROW_NUMBER, "value", String.valueOf(rowNumber)).trim();
	}

	public String getProductTotalByRowNumber(int rowNumber) {
		return getElementText(driver, ShoppingCartPageUI.TOTAL_BY_ROW_NUMBER, String.valueOf(rowNumber)).trim();
	}

	public void inputToQuantityCellByRowNumber(String rowNumber, String quantity) {
		waitForElementVisible(driver, ShoppingCartPageUI.QUANTITY_BY_ROW_NUMBER, rowNumber);
		editAttributeValue(driver, ShoppingCartPageUI.QUANTITY_BY_ROW_NUMBER, "value", quantity, rowNumber);
		pressKeyToElement(driver, ShoppingCartPageUI.QUANTITY_BY_ROW_NUMBER, Keys.ENTER, rowNumber);
	}

}
