package pageObjects.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.OrderDetailPageUI;

public class OrderDetailPageObject extends BasePage {
	WebDriver driver;

	public OrderDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getOrderNumberText() {
		waitForElementVisible(driver, OrderDetailPageUI.ORDER_NUMBER_TEXT);
		return getElementText(driver, OrderDetailPageUI.ORDER_NUMBER_TEXT).trim();
	}

	public String getOrderStatusTex() {
		waitForElementVisible(driver, OrderDetailPageUI.ORDER_STATUS_TEXT);
		return getElementText(driver, OrderDetailPageUI.ORDER_STATUS_TEXT).trim();
	}

	public String getOrderTotalText() {
		waitForElementVisible(driver, OrderDetailPageUI.ORDER_TOTAL_TEXT);
		return getElementText(driver, OrderDetailPageUI.ORDER_TOTAL_TEXT).trim();
	}

	public String getOrderDateText() {
		String formattedDate = null;
		waitForElementVisible(driver, OrderDetailPageUI.ORDER_DATE_TEXT);
		String dateText = getElementText(driver, OrderDetailPageUI.ORDER_DATE_TEXT).trim().substring(12);

		SimpleDateFormat inputFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("M/dd/yyyy");

		try {
			Date date = inputFormat.parse(dateText);
			formattedDate = outputFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formattedDate;
	}

	public String getBillingAddressText() {
		waitForElementVisible(driver, OrderDetailPageUI.BILLING_ADDRESS_INFO_TEXT);
		return getElementText(driver, OrderDetailPageUI.BILLING_ADDRESS_INFO_TEXT).trim();
	}

	public String getShippingAddressText() {
		waitForElementVisible(driver, OrderDetailPageUI.SHIPPING_ADDRESS_INFO_TEXT);
		return getElementText(driver, OrderDetailPageUI.SHIPPING_ADDRESS_INFO_TEXT).trim();
	}

	public String getPaymentMethodText() {
		waitForElementVisible(driver, OrderDetailPageUI.PAYMENT_METHOD_TEXT);
		return getElementText(driver, OrderDetailPageUI.PAYMENT_METHOD_TEXT).trim();
	}

	public String getShippingMethodText() {
		waitForElementVisible(driver, OrderDetailPageUI.SHIPPING_METHOD_TEXT);
		return getElementText(driver, OrderDetailPageUI.SHIPPING_METHOD_TEXT).trim();
	}

	public ArrayList<Map<String, String>> saveProductInfoToList() {
		ArrayList<Map<String, String>> products = new ArrayList<>();
		int rowNumber = getElementSize(driver, OrderDetailPageUI.ALL_ROW);
		for (int i = 1; i <= rowNumber; i++) {
			Map<String, String> productInfo = new HashMap<>();
			productInfo.put("SKU", getElementText(driver, OrderDetailPageUI.DYNAMIC_CELL_BY_ROW_NUMBER_AND_COLUMN_NAME, String.valueOf(i), "sku"));
			productInfo.put("Name", getElementText(driver, OrderDetailPageUI.DYNAMIC_CELL_BY_ROW_NUMBER_AND_COLUMN_NAME, String.valueOf(i), "product"));
			productInfo.put("Price", getElementText(driver, OrderDetailPageUI.DYNAMIC_CELL_BY_ROW_NUMBER_AND_COLUMN_NAME, String.valueOf(i), "unit-price"));
			productInfo.put("Quantity", getElementText(driver, OrderDetailPageUI.DYNAMIC_CELL_BY_ROW_NUMBER_AND_COLUMN_NAME, String.valueOf(i), "quantity"));
			productInfo.put("Total", getElementText(driver, OrderDetailPageUI.DYNAMIC_CELL_BY_ROW_NUMBER_AND_COLUMN_NAME, String.valueOf(i), "total"));
			products.add(productInfo);
		}
		for (Map<String, String> map : products) {
			System.out.println(map);
		}
		return products;
	}

	public String getGiftWrappingText() {
		waitForElementVisible(driver, OrderDetailPageUI.GIFT_WRAPPING_TEXT);
		return getElementText(driver, OrderDetailPageUI.GIFT_WRAPPING_TEXT);
	}

	public String getDynamicOrderSummaryText(String labelName) {
		waitForElementVisible(driver, OrderDetailPageUI.DYNAMIC_SUMMARY_ORDER_TEXT, labelName);
		return getElementText(driver, OrderDetailPageUI.DYNAMIC_SUMMARY_ORDER_TEXT, labelName);
	}

	public ShoppingCartPageObject clickReOrderButton() {
		waitForElementClickable(driver, OrderDetailPageUI.RE_ORDER_BUTTON);
		clickToElement(driver, OrderDetailPageUI.RE_ORDER_BUTTON);
		return PageGeneratorManager.openShoppingCartPage(driver);
	}
}
