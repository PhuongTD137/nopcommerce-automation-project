package pageUIs.user;

public class BasePageUI {
	public static final String DYNAMIC_MY_ACCOUNT_NAV_LINK = "xpath=//div[@class='side-2']//a[text()='%s']";
	public static final String DYNAMIC_FOOTER_LINK = "xpath=//div[@class='footer']//a[text()='%s']";
	public static final String DYNAMIC_HEADER_MENU_LINK = "xpath=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static final String ADMIN_DYNAMIC_SUB_MENU_LINK_BY_PAGE_NAME = "xpath=//li[@class='nav-item has-treeview menu-is-opening menu-open']//ul[@class='nav nav-treeview']//p[text()='%s']";
	// ul[@class='nav nav-treeview'and @style='display: block;']//p[contains(text(),'%s')]/parent::a
	public static final String ADMIN_DYNAMIC_MENU_LINK = "xpath=//ul[@data-widget='treeview']//p[text()='Customers ']";
	public static final String AJAX_LOADING = "css=#ajaxBusy span";
	public static final String HOME_PAGE_LINK = "css=div.header-logo a";
}
