package pageUIs.user;

public class OrderDetailPageUI {
	public static final String ORDER_NUMBER_TEXT = "css=div.order-number strong";
	public static final String ORDER_DATE_TEXT = "css=li.order-date";
	public static final String ORDER_STATUS_TEXT = "css=li.order-status";
	public static final String ORDER_TOTAL_TEXT = "css=li.order-total";
	public static final String BILLING_ADDRESS_INFO_TEXT = "css=div.billing-info ul.info-list";
	public static final String SHIPPING_ADDRESS_INFO_TEXT = "css=div.shipping-info ul.info-list";
	public static final String PAYMENT_METHOD_TEXT = "css=li.payment-method span.value";
	public static final String SHIPPING_METHOD_TEXT = "css=li.shipping-method span.value";
	public static final String DYNAMIC_CELL_BY_ROW_NUMBER_AND_COLUMN_NAME = "xpath=//tbody/tr[%s]/td[@class='%s']";
	public static final String ALL_ROW = "css=table.data-table tbody tr";
	public static final String GIFT_WRAPPING_TEXT = "css=div.selected-checkout-attributes";
	public static final String DYNAMIC_SUMMARY_ORDER_TEXT = "xpath=//label[contains(text(),'%s')]/parent::td/following-sibling::td";
	public static final String RE_ORDER_BUTTON = "css=button.re-order-button";
}
