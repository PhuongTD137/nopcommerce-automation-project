package pageUIs.user;

public class ProductPageUI {
	public static final String PRODUCT_TITLE_LIST = "css=h2.product-title a";
	public static final String PRODUCT_NAME = "xpath=//h2[@class='product-title']/a[text()='%s']";
	public static final String PRODUCT_PRICE = "xpath=//h2//a[text()='%s']/ancestor::div[@class='details']//span[@class='price actual-price']";
	public static final String PRODUCT_PRICE_LIST = "css=div.prices span";
	public static final String CATEGORY_TITLE = "css=div.page-title h1";
	public static final String SORT_BY_DROPDOWN = "css=select#products-orderby";
	public static final String DISPLAY_DROPDOWN = "css=select#products-pagesize";
	public static final String PAGE_NUMBER = "xpath=//li[@class='individual-page']/a[@data-page='%s']";
	public static final String PAGING_NEXT_ICON = "css=div.pager li.next-page";
	public static final String PAGING_PREVIOUS_ICON = "css=div.pager li.previous-page";
	public static final String PAGING_ICON = "css=div.pager";
	public static final String PRODUCT_LINK = "xpath=//h2[@class='product-title']/a[text()='%s']";
	public static final String ADD_TO_COMPARE_ICON = "xpath=//a[text()='%s']/ancestor::div[@class='details']//button[@class='button-2 add-to-compare-list-button']";
	public static final String ADDED_TO_COMPARISON_MESSAGE = "xpath=//p[@class='content' and contains(string(),'The product has been added to your product comparison')]";
}
