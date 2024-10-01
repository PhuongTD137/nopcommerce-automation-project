package pageObjects.user;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.WishlistPageUI;

public class WishlistPageObject extends BasePage {
	WebDriver driver;
	public static int checkedQty = 0;
	public static int remainQty = 0;
	public static int allQty = 0;

	public WishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getProductSKUValue(String productName) {
		int rowNumber = getElementSize(driver, WishlistPageUI.DYNAMIC_PRODUCT_INFO_ROW, productName) + 1;
		waitForElementVisible(driver, WishlistPageUI.DYNAMIC_PRODUCT_SKU, String.valueOf(rowNumber));
		return getElementText(driver, WishlistPageUI.DYNAMIC_PRODUCT_SKU, String.valueOf(rowNumber));
	}

	public String getProductPriceValue(String productName) {
		int rowNumber = getElementSize(driver, WishlistPageUI.DYNAMIC_PRODUCT_INFO_ROW, productName) + 1;
		waitForElementVisible(driver, WishlistPageUI.DYNAMIC_PRODUCT_PRICE, String.valueOf(rowNumber));
		return getElementText(driver, WishlistPageUI.DYNAMIC_PRODUCT_PRICE, String.valueOf(rowNumber));
	}

	public String getProductQuantityValue(String productName) {
		int rowNumber = getElementSize(driver, WishlistPageUI.DYNAMIC_PRODUCT_INFO_ROW, productName) + 1;
		waitForElementVisible(driver, WishlistPageUI.DYNAMIC_PRODUCT_QTY, String.valueOf(rowNumber));
		return getElementAttribute(driver, WishlistPageUI.DYNAMIC_PRODUCT_QTY, "value", String.valueOf(rowNumber));
	}

	public boolean isTotalValueCorrect(String productName) {
		float quantity = Float.parseFloat(getProductQuantityValue(productName));
		float unitPrice = Float.parseFloat(getProductPriceValue(productName).replace("$", "").replace(",", ""));
		float total = quantity * unitPrice;
		System.out.println("total =" + total);

		int rowNumber = getElementSize(driver, WishlistPageUI.DYNAMIC_PRODUCT_INFO_ROW, productName) + 1;
		waitForElementVisible(driver, WishlistPageUI.DYNAMIC_PRODUCT_TOTAL, String.valueOf(rowNumber));
		float totalUI = Float.parseFloat(getElementText(driver, WishlistPageUI.DYNAMIC_PRODUCT_TOTAL, String.valueOf(rowNumber)).replace("$", "").replace(",", ""));
		System.out.println("totalUI=" + totalUI);
		return total == totalUI;
	}

	public void selectAddToCartCheckbox(String productName) {
		int rowNumber = getElementSize(driver, WishlistPageUI.DYNAMIC_PRODUCT_INFO_ROW, productName) + 1;
		waitForElementClickable(driver, WishlistPageUI.DYNAMIC_PRODUCT_CHECKBOX, String.valueOf(rowNumber));
		checkToDefaultCheckboxRadio(driver, WishlistPageUI.DYNAMIC_PRODUCT_CHECKBOX, String.valueOf(rowNumber));
	}

	public ShoppingCartPageObject clickAddToCartButton() {
		// đếm tổng quantity trong wishlist
		List<WebElement> allQuantity = getListWebElement(driver, WishlistPageUI.ALL_QUANTITY);
		for (WebElement quantity : allQuantity) {
			allQty += Integer.parseInt(quantity.getAttribute("value"));
		}
		System.out.println("all quantity= " + allQty);
		List<WebElement> selectedQuantity = getListWebElement(driver, WishlistPageUI.CHECKED_ROW_QUANTITY);
		for (WebElement quantity : selectedQuantity) {
			checkedQty += Integer.parseInt(quantity.getAttribute("value"));
		}
		System.out.println("checked quantity = " + checkedQty);
		remainQty = allQty - checkedQty;
		System.out.println("remain quantity = " + remainQty);
		waitForElementClickable(driver, WishlistPageUI.ADD_TO_CART_BUTTON);
		clickToElement(driver, WishlistPageUI.ADD_TO_CART_BUTTON);
		return PageGeneratorManager.openShoppingCartPage(driver);
	}

	public void clickRemoveAllProduct() {
		List<WebElement> allRemoveButtons = getListWebElement(driver, WishlistPageUI.ALL_REMOVE_BUTTON);
		waitForElementClickable(driver, WishlistPageUI.ALL_REMOVE_BUTTON);
		for (WebElement button : allRemoveButtons) {
			button.click();
		}
	}

	public String getNoDataMessage() {
		waitForElementVisible(driver, WishlistPageUI.NO_DATA_MESSAGE);
		return getElementText(driver, WishlistPageUI.NO_DATA_MESSAGE);
	}

	public boolean isProductUndisplayed() {
		waitForElementInvisible(driver, WishlistPageUI.PRODUCT_TABLE);
		return isElementUndisplayed(driver, WishlistPageUI.PRODUCT_TABLE);
	}

}
