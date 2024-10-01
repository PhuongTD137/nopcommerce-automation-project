package pageUIs.admin;

public class AdminProductPageUI {
	public static final String DYNAMIC_TEXTBOX = "xpath=//input[contains(@name,'%s')]";
	public static final String DYNAMIC_DROPDOWN = "xpath=//select[contains(@name,'%s')]";
	public static final String SEARCH_CATEGORIES_CHECKBOX = "css=input#SearchIncludeSubCategories";
	public static final String SEARCH_BUTTON = "css=button#search-products";
	public static final String GO_BUTTON = "css=button#go-to-product-by-sku";
	public static final String TOTAL_PRODUCT_ROW = "css=tbody tr";
	public static final String DYNAMIC_CELL_VALUE_IN_TABLE = "xpath=//tbody//tr//td[%s]";
	public static final String COLUMN_INDEX_BY_COLUMN_NAME = "xpath=//thead//th//div[text()='%s']/parent::th/preceding-sibling::th";
	public static final String NO_DATA_AVAILABLE_MESSAGE = "xpath=//tbody/tr/td[text()='No data available in table']";
}
