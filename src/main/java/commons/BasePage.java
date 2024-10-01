package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.user.HomePageObject;
import pageUIs.user.BasePageUI;

public class BasePage {

	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;

	// Ham tuong tac voi trinh duyet

	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	// Alert
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		Alert alert = waitForAlertPresence(driver);
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	public String getCurrentWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}

	public void switchWindowByID(WebDriver driver, String windowID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			driver.switchTo().window(id);
			String currentTitle = driver.getTitle();
			if (tabTitle.equals(currentTitle)) {
				break;
			}
		}
	}

	public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}

	// Ham tuong tac voi element

	public By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("xpath")) {
			by = By.xpath(locatorType.substring(6));
		} else if (locatorType.startsWith("CSS=") || locatorType.startsWith("Css=") || locatorType.startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("ID=") || locatorType.startsWith("Id=") || locatorType.startsWith("id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("NAME=") || locatorType.startsWith("Name=") || locatorType.startsWith("name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("CLASSNAME=") || locatorType.startsWith("ClassName=") || locatorType.startsWith("Classname=") || locatorType.startsWith("classname=")) {
			by = By.className(locatorType.substring(10));
		} else {
			throw new RuntimeException("Locator By is not supported");
		}
		return by;
	}

	public String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}

	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public WebElement getWebElement(WebDriver driver, String locatorType, String... dynamicValues) {
		return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorType, String... dynamicValues) {
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}

	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, locatorType, dynamicValues).click();
	}

	public void sendKeyToElement(WebDriver driver, String locatorType, String textValue) {
		getWebElement(driver, locatorType).clear();
		getWebElement(driver, locatorType).sendKeys(textValue);
	}

	public void sendKeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		getWebElement(driver, locatorType, dynamicValues).clear();
		getWebElement(driver, locatorType, dynamicValues).sendKeys(textValue);
	}

	// public void clearValueInElementByPressKey() {

	// }

	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, locatorType, dynamicValues).getText();
	}

	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, locatorType, dynamicValues).size();
	}

	public boolean isElementEnable(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, locatorType, dynamicValues).isDisplayed();
	}

	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String optionText) {
		Select dropdown = new Select(getWebElement(driver, locatorType));
		dropdown.selectByVisibleText(optionText);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String optionText, String... dynamicValues) {
		Select dropdown = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		dropdown.selectByVisibleText(optionText);
	}

	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select dropdown = new Select(getWebElement(driver, locatorType));
		return dropdown.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select dropdown = new Select(getWebElement(driver, locatorType));
		return dropdown.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String locatorDropdown, String locatorOption, String expectedText) {
		clickToElement(driver, locatorDropdown);
		sleepInSecond(1);

		// wait for all options displayed
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> optionList = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locatorOption)));

		for (WebElement option : optionList) {
			if (option.getText().trim().equalsIgnoreCase(expectedText)) {
				System.out.println("option =" + option.getText());
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("argument[0].scrollIntoView(true);", option);
				sleepInSecond(1);
				option.click();
				break;
			}
		}
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String locatorType, String cssName) {
		return getWebElement(driver, locatorType).getCssValue(cssName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected() == false) {
			element.click();
		}
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected() == false) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	public void switchToDefaultContent(WebDriver driver, String locatorType) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		WebElement element = getWebElement(driver, locatorType);
		Actions action = new Actions(driver);
		action.sendKeys(element, key).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		Actions action = new Actions(driver);
		action.sendKeys(element, key).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scollHeight)");
	}

	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void highlightElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElementByJS(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void removeAttributeInDOMByJS(WebDriver driver, String locatorType, String attributeName) {
		WebElement element = getWebElement(driver, locatorType);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeName + "');", element);
	}

	public void editAttributeValue(WebDriver driver, String locatorType, String attributeName, String attributeValue) {
		WebElement element = getWebElement(driver, locatorType);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeName, attributeValue);
	}

	public void editAttributeValue(WebDriver driver, String locatorType, String attributeName, String attributeValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeName, attributeValue);
	}

	// public boolean areJQueryandJSLoadedSuccess() {
	//
	// }

	public String getElementValidationMessageByJS(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}

	public boolean isImageLoadedByJS(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
		if (status == true) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForAllElementsVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	public void waitForAllElementsVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForAllElementsInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}

	public void waitForAllElementsInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getWebElement(driver, locatorType)));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(driver, longTimeout);
	}

	public void overrideImplicitTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	// Dynamic My Account navigation link
	public Object openDynamicMyAccountLink(WebDriver driver, String... pageName) {
		Object object = null;
		waitForElementClickable(driver, BasePageUI.DYNAMIC_MY_ACCOUNT_NAV_LINK, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_MY_ACCOUNT_NAV_LINK, pageName);
		switch (pageName[0]) {
		case "Customer info":
			object = PageGeneratorManager.openCustomerInfoPage(driver);
			break;
		case "Addresses":
			object = PageGeneratorManager.openAddressesPage(driver);
			break;
		case "Orders":
			object = PageGeneratorManager.openOrdersPage(driver);
			break;
		case "Downloadable products":
			object = PageGeneratorManager.openDownloadableProductsPage(driver);
			break;
		case "Back in stock subscriptions":
			object = PageGeneratorManager.openSubscriptionsPage(driver);
			break;
		case "Reward points":
			object = PageGeneratorManager.openRewardPointsPage(driver);
			break;
		case "Change password":
			object = PageGeneratorManager.openChangePasswordPage(driver);
			break;
		case "My product reviews":
			object = PageGeneratorManager.openProductReviewsPage(driver);
			break;
		default:
			System.out.println("Page name is not supported");
			break;
		}
		return object;
	}

	// Dynamic footer link
	public Object clickToDynamicFooterLink(WebDriver driver, String... linkName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_FOOTER_LINK, linkName);
		clickToElement(driver, BasePageUI.DYNAMIC_FOOTER_LINK, linkName);

		Object object = null;
		switch (linkName[0]) {
		case "Search":
			object = PageGeneratorManager.openSearchPage(driver);
			break;
		case "Compare products list":
			object = PageGeneratorManager.openComparisonPage(driver);
			break;
		default:
			System.out.println("Page name is not supported");
			break;
		}
		return object;
	}

	// Dynamic header sub menu link
	public Object clickToDynamicHeaderSubMenuLink(WebDriver driver, String menu, String subMenu) {
		scrollToElementByJS(driver, BasePageUI.DYNAMIC_HEADER_MENU_LINK, menu);
		waitForElementClickable(driver, BasePageUI.DYNAMIC_HEADER_MENU_LINK, menu);
		hoverMouseToElement(driver, BasePageUI.DYNAMIC_HEADER_MENU_LINK, menu);
		waitForElementClickable(driver, BasePageUI.DYNAMIC_HEADER_MENU_LINK, subMenu);
		clickToElement(driver, BasePageUI.DYNAMIC_HEADER_MENU_LINK, subMenu);
		return PageGeneratorManager.openProductPage(driver);
	}

	public HomePageObject returnToHomePage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.HOME_PAGE_LINK);
		clickToElement(driver, BasePageUI.HOME_PAGE_LINK);
		return PageGeneratorManager.openHomePage(driver);
	}

	// Admin Page
	// Navigate to sub menu link on Sidebar menu
	public void openAdminDynamicPageOnSideBarMenu(WebDriver driver, String menuName, String pageName) {
		waitForElementClickable(driver, BasePageUI.ADMIN_DYNAMIC_MENU_LINK, menuName);
		clickToElementByJS(driver, BasePageUI.ADMIN_DYNAMIC_MENU_LINK, menuName);
		scrollToElementByJS(driver, BasePageUI.ADMIN_DYNAMIC_SUB_MENU_LINK_BY_PAGE_NAME, pageName);
		waitForElementClickable(driver, BasePageUI.ADMIN_DYNAMIC_SUB_MENU_LINK_BY_PAGE_NAME, pageName);
		clickToElement(driver, BasePageUI.ADMIN_DYNAMIC_SUB_MENU_LINK_BY_PAGE_NAME, pageName);
		waitForElementVisible(driver, BasePageUI.AJAX_LOADING);
	}

}
