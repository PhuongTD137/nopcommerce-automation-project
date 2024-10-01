package pageUIs.user;

public class WishlistPageUI {
	public static final String DYNAMIC_PRODUCT_SKU = "xpath=//tbody//tr[%s]//td[@class='sku']/span";
	public static final String DYNAMIC_PRODUCT_PRICE = "xpath=//tbody//tr[%s]//td[@class='unit-price']/span";
	public static final String DYNAMIC_PRODUCT_QTY = "xpath=//tbody//tr[%s]//td[@class='quantity']/input";
	public static final String DYNAMIC_PRODUCT_TOTAL = "xpath=//tbody//tr[%s]//td[@class='subtotal']/span";
	// public static final String DYNAMIC_PRODUCT_IMG = "";
	public static final String DYNAMIC_PRODUCT_CHECKBOX = "xpath=//tbody/tr[%s]//td[@class='add-to-cart']/input";
	public static final String DYNAMIC_PRODUCT_INFO_ROW = "xpath=//tbody//td/a[text()='%s']/ancestor::tr/preceding-sibling::tr";
	public static final String WISHLIST_SHARE_LINK = "css=div.share-info a";
	public static final String BODY_MESSAGE = "css=div.page-body div";
	public static final String ADD_TO_CART_BUTTON = "css=button.wishlist-add-to-cart-button";
	public static final String ALL_QUANTITY = "css=td.quantity input";
	public static final String CHECKED_ROW_QUANTITY = "xpath=//tbody/tr/td[@class='add-to-cart']/input[@data-gtm-form-interact-field-id]/parent::td/following-sibling::td[@class='quantity']/input";
	public static final String NO_DATA_MESSAGE = "css=div.no-data";
	public static final String ALL_REMOVE_BUTTON = "css=button.remove-btn";
	public static final String PRODUCT_TABLE = "css=table.cart";
}
