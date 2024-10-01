package pageUIs.user;

public class OrdersPageUI {
	public static final String ORDER_NUMBER_TEXT = "css=div.order-item strong";
	public static final String ORDER_STATUS_TEXT = "css=div.order-item span.order-status";
	public static final String ORDER_DATE_TEXT = "css=div.order-item span.order-date";
	public static final String ORDER_TOTAL_TEXT = "css=div.order-item span.order-total";
	public static final String DETAIL_BUTTON_BY_ORDER_NUMBER = "xpath=//strong[contains(text(),'%s')]/ancestor::div[@class='section order-item']//button";

}
