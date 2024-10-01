package pageUIs.user;

public class CheckoutPageUI {
	public static final String SHIP_TO_SAME_ADDRESS_CHECKBOX = "id=ShipToSameAddress";
	public static final String DYNAMIC_TEXTBOX = "xpath=//input[@id='%s']";
	public static final String DYNAMIC_DROPDOWN = "xpath=//select[@id='%s']";
	public static final String BA_CONTINUE_BUTTON = "css=#billing-buttons-container button.new-address-next-step-button";
	public static final String SA_CONTINUE_BUTTON = "css=#shipping-buttons-container button.new-address-next-step-button";

	public static final String DYNAMIC_RADIO_BUTTON = "xpath=//label[contains(text(),'%s')]/preceding-sibling::input";
	public static final String CHECKED_SHIPPING_METHOD_LABEL = "xpath=//div[@class='section shipping-method']//input[@checked='checked']/following-sibling::label";
	public static final String SM_CONTINUE_BUTTON = "css=button.shipping-method-next-step-button";
	public static final String PM_CONTINUE_BUTTON = "css=button.payment-method-next-step-button";
	public static final String PM_INFO_TEXT = "css=div.info td";
	public static final String PI_CONTINUE_BUTTON = "css=button.payment-info-next-step-button";

	public static final String ALL_INFO_TEXT = "xpath=//div[@class='%s']//li";
	public static final String CO_PAYMENT_METHOD_TEXT = "css=li.payment-method span.value";
	public static final String CO_SHIPPING_METHOD_TEXT = "css=li.shipping-method span.value";
	public static final String CO_GIFT_WRAPPING_VALUE_TEXT = "css=div.cart-options div.selected-checkout-attributes";
	public static final String CO_SUB_TOTAL_VALUE_TEXT = "css=tr.order-subtotal span";
	public static final String CO_SHIPPING_FEE_VALUE_TEXT = "css=tr.shipping-cost span.value-summary";
	public static final String CO_TAX_VALUE_TEXT = "css=tr.tax-value td.cart-total-right span";
	public static final String CO_REWARD_POINT_VALUE_TEXT = "css=tr.earn-reward-points span.value-summary";
	public static final String CO_TOTAL_VALUE_TEXT = "css=tr.order-total span.value-summary>strong";

	public static final String ALL_ROW = "xpath=//table[@class='cart']//tbody//tr";
	public static final String SKU_BY_ROW_NUMBER = "xpath=//table[@class='cart']//tbody//tr[%s]/td[@class='sku']/span";
	public static final String PRODUCT_NAME_BY_ROW_NUMBER = "xpath=//table[@class='cart']//tbody//tr[%s]/td[@class='product']/a";
	public static final String PRICE_BY_ROW_NUMBER = "xpath=//table[@class='cart']//tbody//tr[%s]/td[@class='unit-price']/span";
	public static final String QUANTITY_BY_ROW_NUMBER = "xpath=//table[@class='cart']//tbody//tr[%s]/td[@class='quantity']/span";
	public static final String TOTAL_BY_ROW_NUMBER = "xpath=//table[@class='cart']//tbody//tr[%s]/td[@class='subtotal']//span";
	public static final String CO_CONFIRM_BUTTON = "css=div#checkout-step-confirm-order button.confirm-order-next-step-button";

	public static final String SUCCESS_PAGE_TITLE = "css=div.page-title h1";
	public static final String SUCCESS_MESSAGE = "css=div.order-completed div.title strong";
	public static final String ORDER_NUMBER_TEXT = "css=div.order-number strong";
	public static final String SO_CONTINUE_BUTTON = "css=button.order-completed-continue-button";
}
