package pageUIs.user;

public class ProductDetailPageUI {
	public static final String PRODUCT_TITLE = "css=div.product-name h1";
	public static final String PRODUCT_SKU = "id=sku-5";
	public static final String PRODUCT_PRICE = "css=div.product-price span";
	public static final String ADD_TO_WISHLIST_BUTTON = "css=div.add-to-wishlist button";
	public static final String ADD_TO_COMPARE_BUTTON = "css=div.compare-products button";
	public static final String DYNAMIC_MESSAGE = "xpath=//p[@class='content' and contains(string(),'%s')]";
	// public static final String RESULT_MESSAGE = "css=p.content";
	public static final String WISHLIST_LINK_ON_MESSAGE = "css=p.content a";
	public static final String PROCESSOR_DROPDOWN = "css=div.attributes select#product_attribute_1";
	public static final String RAM_DROPDOWN = "css=div.attributes select#product_attribute_2";
	public static final String HDD_RADIO_BUTTON = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static final String OS_RADIO_BUTTON = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static final String SOFTWARE_CHECKBOX = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static final String ADD_TO_CART_BUTTON = "css=div.add-to-cart button";
	public static final String QUANTITY_TEXTBOX = "css=div.add-to-cart input";
	public static final String SHOPPING_CART_LINK = "css=a.ico-cart";
	public static final String PRODUCT_INFO_AT_SHOPPING_CART = "xpath=//div[@class='mini-shopping-cart']//div[@class='attributes']";
	public static final String PRODUCT_PRICE_AT_SHOPPING_CART = "css=div.price span";
	public static final String PRODUCT_QUANTITY_AT_SHOPPING_CART = "css=div.quantity span";
	public static final String SUB_TOTAL_AT_SHOPPING_CART = "css=div.totals strong";
	public static final String COUNT_MESSAGE_AT_SHOPPING_CART = "css=div.count";
	public static final String QUANTITY_DISPLAYED_AT_SHOPPING_CART_LINK = "css=a.ico-cart span.cart-qty";
	public static final String CLOSE_MESSAGE_ICON = "css=span.close";
}
