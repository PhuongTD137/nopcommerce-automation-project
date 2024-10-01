package pageUIs.admin;

public class AdminCustomerListPageUI {
	public static final String ADD_NEW_BUTTON = "css=a.btn-primary";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String CUSTOMER_ROLE_DROPDOWN = "xpath=//ul[@class='select2-selection__rendered']";
	public static final String CUSTOMER_ROLE_TEXTBOX = "xpath=//ul[@class='select2-selection__rendered']//input";
	public static final String ALL_CUSTOMER_ROLE_DELETE_ICON = "css=span.select2-selection__choice__remove";
	public static final String ALL_OPTIONS_IN_CUSTOMER_DROPDOWN = "css=span.select2-results>ul li";
	public static final String SEARCH_BUTTON = "css=button#search-customers";
	public static final String ROW_BY_CUSTOMER_NAME = "xpath=//tbody//tr//td[text()='%s']/parent::tr//preceding-sibling::tr";
	public static final String COLUMN_BY_COLUMN_NAME = "xpath=//thead//th/div[text()='%s']/parent::th/preceding-sibling::th";
	public static final String DYNAMIC_CELL_BY_ROW_INDEX_COLUMN_INDEX = "xpath=//tbody//tr[%s]//td[%s]";
	public static final String ROW_DISPLAYED_IN_TABLE = "xpath=//tbody//tr/td[not(@class='dataTables_empty')]/parent::tr";
	public static final String ITEM_DISPLAYED_TEXT = "id=customers-grid_info";
	public static final String EDIT_BUTTON_ON_ROW = "css=td.button-column a";
	public static final String SUCCESS_MESSAGE = "css=div.alert-success";
}
