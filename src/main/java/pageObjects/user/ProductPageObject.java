package pageObjects.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.ProductPageUI;

public class ProductPageObject extends BasePage {
	WebDriver driver;
	Map<String, Map<String, String>> productsMap = new HashMap<>();

	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getCategoryTitle() {
		waitForElementVisible(driver, ProductPageUI.CATEGORY_TITLE);
		return getElementText(driver, ProductPageUI.CATEGORY_TITLE);
	}

	public void selectSortByDropdown(String option) {
		waitForElementClickable(driver, ProductPageUI.SORT_BY_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUI.SORT_BY_DROPDOWN, option);
	}

	public boolean isProductListSortedByNameAToZ() {
		ArrayList<String> productListOnScreen = new ArrayList<String>();
		List<WebElement> productList = getListWebElement(driver, ProductPageUI.PRODUCT_TITLE_LIST);

		for (WebElement product : productList) {
			productListOnScreen.add(product.getText());
		}

		ArrayList<String> productListSorted = new ArrayList<String>();
		for (WebElement product : productList) {
			productListSorted.add(product.getText());
		}

		Collections.sort(productListSorted);

		return productListSorted.equals(productListOnScreen);
	}

	public boolean isProductListSortedByNameZToA() {
		ArrayList<String> productListUI = new ArrayList<String>();
		List<WebElement> productNameList = getListWebElement(driver, ProductPageUI.PRODUCT_TITLE_LIST);

		for (WebElement product : productNameList) {
			productListUI.add(product.getText());
		}

		ArrayList<String> productListSorted = new ArrayList<String>();
		for (WebElement product : productNameList) {
			productListSorted.add(product.getText());
		}

		Collections.sort(productListSorted);
		Collections.reverse(productListSorted);

		return productListSorted.equals(productListUI);
	}

	public boolean isProductPriceSortedByPriceLowToHigh() {
		List<WebElement> productPriceList = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_LIST);
		ArrayList<Float> productPriceListUI = new ArrayList<Float>();

		for (WebElement productPrice : productPriceList) {
			productPriceListUI.add(Float.parseFloat(productPrice.getText().replace("$", "").replace(",", "")));
		}

		ArrayList<Float> productPriceListSorted = new ArrayList<Float>(productPriceListUI);

		Collections.sort(productPriceListSorted);

		return productPriceListSorted.equals(productPriceListUI);
	}

	public boolean isProductPriceSortedByPriceHighToLow() {
		List<WebElement> productPriceList = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_LIST);
		ArrayList<Float> productPriceListUI = new ArrayList<Float>();

		for (WebElement productPrice : productPriceList) {
			productPriceListUI.add(Float.parseFloat(productPrice.getText().replace("$", "").replace(",", "")));
		}

		ArrayList<Float> productPriceListSorted = new ArrayList<Float>(productPriceListUI);

		Collections.sort(productPriceListSorted);
		Collections.reverse(productPriceListSorted);

		return productPriceListSorted.equals(productPriceListUI);
	}

	public void selectDisplayDropdown(String option) {
		waitForElementClickable(driver, ProductPageUI.DISPLAY_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUI.DISPLAY_DROPDOWN, option);
	}

	public boolean isProductSizeLessThanOrEqual(int number) {
		int productSize = getElementSize(driver, ProductPageUI.PRODUCT_TITLE_LIST);
		if (productSize <= number) {
			return true;
		} else {
			return false;
		}
	}

	public void clickToPageNumber(String pageNumber) {
		waitForElementClickable(driver, ProductPageUI.PAGE_NUMBER, pageNumber);
		clickToElement(driver, ProductPageUI.PAGE_NUMBER, pageNumber);
	}

	public boolean isPreviousIconDisplayed() {
		waitForElementVisible(driver, ProductPageUI.PAGING_PREVIOUS_ICON);
		return isElementDisplayed(driver, ProductPageUI.PAGING_PREVIOUS_ICON);
	}

	public boolean isNextIconDisplayed() {
		waitForElementVisible(driver, ProductPageUI.PAGING_NEXT_ICON);
		return isElementDisplayed(driver, ProductPageUI.PAGING_NEXT_ICON);
	}

	public boolean isPagingIconUndisplayed() {
		waitForElementInvisible(driver, ProductPageUI.PAGING_ICON);
		return isElementUndisplayed(driver, ProductPageUI.PAGING_ICON);
	}

	public ProductDetailPageObject clickProductLink(String productName) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_LINK, productName);
		scrollToElementByJS(driver, ProductPageUI.PRODUCT_LINK, productName);
		clickToElement(driver, ProductPageUI.PRODUCT_LINK, productName);
		return PageGeneratorManager.openProductDetailPage(driver);
	}

	public void clickAddToCompareIcon(String productName) {
		waitForElementClickable(driver, ProductPageUI.ADD_TO_COMPARE_ICON, productName);
		System.out.println("scroll to element");
		scrollToElementByJS(driver, ProductPageUI.ADD_TO_COMPARE_ICON, productName);
		System.out.println("click to element");
		highlightElement(driver, ProductPageUI.ADD_TO_COMPARE_ICON, productName);
		clickToElementByJS(driver, ProductPageUI.ADD_TO_COMPARE_ICON, productName);
	}

	public void addProductInfoToProductsMap(Map<String, String> productMapName, String productTitle) {
		// String productName = getElementText(driver, ProductPageUI.PRODUCT_NAME, productTitle);
		productMapName = new HashMap<>();
		String productPrice = getElementText(driver, ProductPageUI.PRODUCT_PRICE, productTitle).trim();
		productMapName.put("Name", productTitle);
		productMapName.put("Price", productPrice);
		productsMap.put(productTitle, productMapName);

		System.out.println("Danh sách sản phẩm:");
		System.out.println("Tên \t\t Giá");
		for (Map.Entry<String, Map<String, String>> entry : productsMap.entrySet()) {
			String productNameOfMap = entry.getKey();
			Map<String, String> productInfo = entry.getValue();
			System.out.println("Product: " + productNameOfMap);
			for (Map.Entry<String, String> productEntry : productInfo.entrySet()) {
				String key = productEntry.getKey();
				String value = productEntry.getValue();
				System.out.println("\t" + key + ": " + value);
			}
		}
		System.out.println("------------------");

	}

	public boolean isAddedToComparisonMessageDisplayed() {
		waitForElementVisible(driver, ProductPageUI.ADDED_TO_COMPARISON_MESSAGE);
		return isElementDisplayed(driver, ProductPageUI.ADDED_TO_COMPARISON_MESSAGE);
	}
}
