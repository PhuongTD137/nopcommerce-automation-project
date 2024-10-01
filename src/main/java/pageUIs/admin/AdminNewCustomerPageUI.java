package pageUIs.admin;

public class AdminNewCustomerPageUI {
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String ADMIN_COMMENT_TEXTAREA = "id=AdminComment";
	public static final String GENDER_RADIO_BUTTON = "xpath=//input[@name='Gender' and @value = '%s']";
	public static final String CUSTOMER_ROLE_DROPDOWN = "xpath=//label[text()='Customer roles']//ancestor::div[@class='form-group row']//ul";
	public static final String DELETE_ICON_AT_SELECTED_OPTION = "css=span.select2-selection__choice__remove";
	public static final String ALL_OPTIONS_IN_DROPDOWN = "xpath=//ul[@id='select2-SelectedCustomerRoleIds-results']//li";
	public static final String OPTION_BY_NAME = "xpath=//ul[@id='select2-SelectedCustomerRoleIds-results']//li[text()='%s']";
	public static final String CUSTOMER_ROLE_TEXTBOX_AT_DROPDOWN = "xpath=//label[text()='Customer roles']//ancestor::div[@class='form-group row']//ul//input";
	public static final String ACTIVE_CHECKBOX = "id=Active";
	public static final String SAVE_AND_CONTINUE_EDIT_BUTTON = "xpath=//button[@name='save-continue']";
	public static final String SUCCESS_MESSAGE = "xpath=//div[contains(@class,'alert-success') and contains(text(),'%s')]";
	public static final String CHECKED_GENDER_RADIO_BUTTON = "xpath=//input[@name = 'Gender' and @checked]";
	public static final String SELECTED_ITEM_IN_CUSTOMER_ROLE_DROPDOWN = "css=li.select2-selection__choice";
	public static final String BACK_TO_CUSTOMER_LIST_LINK = "xpath=//a[text()='back to customer list']";
	public static final String SAVE_BUTTON = "xpath=//button[@name='save']";
	public static final String DYNAMIC_PANEL_BY_ID = "xpath=//div[@id='%s']";
	public static final String ADD_NEW_ADDRESS_BUTTON = "xpath=//button[contains(text(),'Add new address')]";
	// public static final String DYNAMIC_CELL_BY_TABLE_ID_AND_COLUMN_INDEX = "xpath=//div[@id='%s']//tbody//td[%s]";
	// public static final String COLUMN_BY_TABLE_ID_AND_COLUMN_NAME = "xpath=//div[@id='%s']//thead/tr/th[text()='%s']/preceding-sibling::th";
	public static final String ROW_BY_TABLE_ID = "xpath=//div[@id='%s']//tbody/tr[1]";
	public static final String EDIT_ADDRESS_BUTTON_BY_ROW_INDEX = "xpath=//tbody/tr[%s]//td/a[contains(text(),'Edit')]";
	public static final String DELETE_ADDRESS_BUTTON_BY_ROW_INDEX = "xpath=//tbody/tr[1]/td/a[text()='Delete']";
	public static final String ALERT_MESSAGE_IN_TABLE_BY_TABLE_ID = "xpath=//table[contains(@id,'%s')]//tbody//td";
}
