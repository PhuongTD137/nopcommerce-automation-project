package pageUIs.user;

public class ShoppingCartPageUI {
	public static final String NUMBER_AT_WISHLIST_LINK = "css=a.ico-wishlist span.wishlist-qty";
	public static final String NUMBER_AT_SHOPPING_CART_LINK = "css=a.ico-cart span.cart-qty";
	public static final String EDIT_BUTTON_BY_PRODUCT_NAME = "xpath=//a[@class='product-name' and text()='%s']/parent::td//a[text()='Edit']";
	public static final String SHOPPING_CART_COUNT_MESSAGE = "css=div.flyout-cart div.count";
	public static final String ORDER_SUMMARY_MESSAGE = "css=div.order-summary-content div";
	public static final String REMOVE_ICON_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::td//following-sibling::td//button[@class='remove-btn']";
	public static final String SHOPPING_CART_LINK = "css=a.ico-cart";
	public static final String ALL_TOTAL_VALUE_CELL = "css=.product-subtotal";
	public static final String ALL_ROW = "xpath=//table[@class='cart']//tbody//tr";
	public static final String SKU_BY_ROW_NUMBER = "xpath=//table[@class='cart']//tbody//tr[%s]/td[@class='sku']/span";
	public static final String PRODUCT_NAME_BY_ROW_NUMBER = "xpath=//table[@class='cart']//tbody//tr[%s]/td[@class='product']/a";
	public static final String PRICE_BY_ROW_NUMBER = "xpath=//table[@class='cart']//tbody//tr[%s]/td[@class='unit-price']/span";
	public static final String QUANTITY_BY_ROW_NUMBER = "xpath=//table[@class='cart']//tbody//tr[%s]/td[@class='quantity']//input";
	public static final String TOTAL_BY_ROW_NUMBER = "xpath=//table[@class='cart']//tbody//tr[%s]/td[@class='subtotal']//span";

	public static final String GIFT_WRAPPING_DROPDOWN = "css=.cart-options #checkout_attribute_1";
	public static final String GIFT_WRAPPING_TEXT = "css=.cart-options .selected-checkout-attributes";
	public static final String SUB_TOTAL_VALUE_TEXT = "css=.order-subtotal .value-summary";
	public static final String SHIPPING_COST_TEXT = "css=.shipping-cost .value-summary";
	public static final String TAX_VALUE_TEXT = "css=.tax-value .value-summary";
	public static final String REWARD_POINT_TEXT = "css=.earn-reward-points span";
	public static final String TOTAL_VALUE_TEXT = "css=.order-total .value-summary>strong";
	public static final String TERM_OF_SERVICE_CHECKBOX = "css=.terms-of-service>input";
	public static final String CHECKOUT_BUTTON = "css=.checkout-buttons>#checkout";
}
