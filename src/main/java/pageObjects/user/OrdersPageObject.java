package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.OrdersPageUI;

public class OrdersPageObject extends BasePage {
	WebDriver driver;

	public OrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getOrderNumberText() {
		waitForElementVisible(driver, OrdersPageUI.ORDER_NUMBER_TEXT);
		return getElementText(driver, OrdersPageUI.ORDER_NUMBER_TEXT).trim();
	}

	public String getOrderStatusText() {
		waitForElementVisible(driver, OrdersPageUI.ORDER_STATUS_TEXT);
		return getElementText(driver, OrdersPageUI.ORDER_STATUS_TEXT).trim();
	}

	public String getOrderTotalText() {
		waitForElementVisible(driver, OrdersPageUI.ORDER_TOTAL_TEXT);
		return getElementText(driver, OrdersPageUI.ORDER_TOTAL_TEXT).trim();
	}

	public String getOrderDateText() {
		waitForElementVisible(driver, OrdersPageUI.ORDER_DATE_TEXT);
		return getElementText(driver, OrdersPageUI.ORDER_DATE_TEXT).trim();
	}

	public OrderDetailPageObject clickToOrderDetailLinkByOrderNumber(String orderNumber) {
		waitForElementClickable(driver, OrdersPageUI.DETAIL_BUTTON_BY_ORDER_NUMBER, orderNumber);
		clickToElement(driver, OrdersPageUI.DETAIL_BUTTON_BY_ORDER_NUMBER, orderNumber);
		return PageGeneratorManager.openOrderDetailPage(driver);
	}
}
