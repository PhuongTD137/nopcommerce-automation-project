package pageUIs.admin;

public class AdminDashboardPageUI {
	public static final String PRODUCT_LINK = "xpath=//p[contains(text(),'Products')]//parent::a[contains(@href,'/Admin/Product/List')]";
	public static final String CATALOG_LINK = "xpath=//p[contains(text(),'Catalog')]/preceding-sibling::i[contains(@class,'fa-book')]/parent::a";
}
