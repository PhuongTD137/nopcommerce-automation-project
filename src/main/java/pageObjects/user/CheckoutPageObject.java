package pageObjects.user;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.CheckoutPageUI;

public class CheckoutPageObject extends BasePage {
	WebDriver driver;

	public CheckoutPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void uncheckShipToTheSameAddressCheckbox() {
		waitForElementClickable(driver, CheckoutPageUI.SHIP_TO_SAME_ADDRESS_CHECKBOX);
		uncheckToDefaultCheckbox(driver, CheckoutPageUI.SHIP_TO_SAME_ADDRESS_CHECKBOX);
	}

	public void inputToDynamicTextbox(String fieldID, String textValue) {
		waitForElementVisible(driver, CheckoutPageUI.DYNAMIC_TEXTBOX, fieldID);
		sendKeyToElement(driver, CheckoutPageUI.DYNAMIC_TEXTBOX, textValue, fieldID);
	}

	public void selectDynamicDropdown(String fieldID, String optionName) {
		waitForElementClickable(driver, CheckoutPageUI.DYNAMIC_DROPDOWN, fieldID);
		selectItemInDefaultDropdown(driver, CheckoutPageUI.DYNAMIC_DROPDOWN, optionName, fieldID);
	}

	public void clickContinueButtonAtBillingAddressSection() {
		waitForElementClickable(driver, CheckoutPageUI.BA_CONTINUE_BUTTON);
		clickToElement(driver, CheckoutPageUI.BA_CONTINUE_BUTTON);
	}

	public void clickContinueButtonAtShippingAddressSection() {
		waitForElementClickable(driver, CheckoutPageUI.SA_CONTINUE_BUTTON);
		clickToElement(driver, CheckoutPageUI.SA_CONTINUE_BUTTON);

	}

	public void checkToDynamicRadioButton(String labelName) {
		waitForElementClickable(driver, CheckoutPageUI.DYNAMIC_RADIO_BUTTON, labelName);
		checkToDefaultCheckboxRadio(driver, CheckoutPageUI.DYNAMIC_RADIO_BUTTON, labelName);
	}

	public void clickContinueButtonAtShippingMethodSection() {
		waitForElementClickable(driver, CheckoutPageUI.SM_CONTINUE_BUTTON);
		clickToElement(driver, CheckoutPageUI.SM_CONTINUE_BUTTON);
	}

	public void clickContinueButtonAtPaymentMethodSection() {
		waitForElementClickable(driver, CheckoutPageUI.PM_CONTINUE_BUTTON);
		clickToElement(driver, CheckoutPageUI.PM_CONTINUE_BUTTON);
	}

	public String getPaymentInformationText() {
		waitForElementVisible(driver, CheckoutPageUI.PM_INFO_TEXT);
		return getElementText(driver, CheckoutPageUI.PM_INFO_TEXT).trim();
	}

	public void clickContinueButtonAtPaymentInformationSection() {
		waitForElementClickable(driver, CheckoutPageUI.PI_CONTINUE_BUTTON);
		clickToElement(driver, CheckoutPageUI.PI_CONTINUE_BUTTON);
	}

	public List<String> saveInfoToList(String... infoValues) {
		List<String> tempList = new ArrayList<>();
		for (String infoValue : infoValues) {
			tempList.add(infoValue);
		}
		return tempList;
	}

	public List<String> saveDisplayedInfoIntoList(String infoType) {
		List<WebElement> elementList = getListWebElement(driver, CheckoutPageUI.ALL_INFO_TEXT, infoType);
		List<String> tempList = new ArrayList<>();
		for (WebElement element : elementList) {
			String elementText = element.getText().trim();
			String attributeText = element.getAttribute("class");
			if (elementText.contains("Email") || elementText.contains("Phone")) {
				tempList.add(elementText.substring(7));
			} else if (attributeText.equals("fax") || attributeText.equals("stateprovince") || attributeText.equals("county") || attributeText.equals("address2")) {
				continue;
			} else {
				tempList.add(elementText);
			}
		}
		return tempList;
	}

	public String getDisplayedShippingMethodText() {
		waitForElementVisible(driver, CheckoutPageUI.CO_SHIPPING_METHOD_TEXT);
		return getElementText(driver, CheckoutPageUI.CO_SHIPPING_METHOD_TEXT).trim();
	}

	public String getDisplayedPaymentMethodText() {
		waitForElementVisible(driver, CheckoutPageUI.CO_PAYMENT_METHOD_TEXT);
		return getElementText(driver, CheckoutPageUI.CO_PAYMENT_METHOD_TEXT).trim();
	}

	public String getShippingFeeText() {
		waitForElementVisible(driver, CheckoutPageUI.CHECKED_SHIPPING_METHOD_LABEL);
		String[] text = getElementText(driver, CheckoutPageUI.CHECKED_SHIPPING_METHOD_LABEL).split(" ");
		String shippingFee = text[1].replace("(", "").replace(")", "");
		System.out.println(shippingFee);
		return shippingFee;
	}

	public String getGiftWrappingFeeDisplayedAtConfirmOrder() {
		waitForElementVisible(driver, CheckoutPageUI.CO_GIFT_WRAPPING_VALUE_TEXT);
		return getElementText(driver, CheckoutPageUI.CO_SUB_TOTAL_VALUE_TEXT).trim();
	}

	public String getSubTotalDisplayedAtConfirmOrder() {
		waitForElementVisible(driver, CheckoutPageUI.CO_SUB_TOTAL_VALUE_TEXT);
		return getElementText(driver, CheckoutPageUI.CO_SUB_TOTAL_VALUE_TEXT).trim();
	}

	public String getShippingFeeDisplayedAtConfirmOrder() {
		waitForElementVisible(driver, CheckoutPageUI.CO_SHIPPING_FEE_VALUE_TEXT);
		return getElementText(driver, CheckoutPageUI.CO_SHIPPING_FEE_VALUE_TEXT).trim();
	}

	public String getTaxValueDisplayedAtConfirmOrder() {
		waitForElementVisible(driver, CheckoutPageUI.CO_TAX_VALUE_TEXT);
		return getElementText(driver, CheckoutPageUI.CO_TAX_VALUE_TEXT).trim();
	}

	public String getPointDisplayedAtConfirmOrder() {
		waitForElementVisible(driver, CheckoutPageUI.CO_REWARD_POINT_VALUE_TEXT);
		return getElementText(driver, CheckoutPageUI.CO_REWARD_POINT_VALUE_TEXT).trim();
	}

	public ArrayList<Map<String, String>> saveListOfProductInfo() {
		ArrayList<Map<String, String>> products = new ArrayList<>();
		int rowNumber = getListWebElement(driver, CheckoutPageUI.ALL_ROW).size();
		for (int i = 1; i <= rowNumber; i++) {
			Map<String, String> productMap = new HashMap<>();
			productMap.put("SKU", getSKUValueByRowNumber(i));
			productMap.put("Name", getProductNameByRowNumber(i));
			productMap.put("Price", getProductPriceByRowNumber(i));
			productMap.put("Quantity", getProductQuantityByRowNumber(i));
			productMap.put("Total", getProductTotalByRowNumber(i));
			products.add(productMap);
		}
		System.out.println("thong tin product at CO:");
		for (Map<String, String> map : products) {
			System.out.println(map);
		}
		return products;
	}

	public String getSKUValueByRowNumber(int rowNumber) {
		return getElementText(driver, CheckoutPageUI.SKU_BY_ROW_NUMBER, String.valueOf(rowNumber));
	}

	public String getProductNameByRowNumber(int rowNumber) {
		return getElementText(driver, CheckoutPageUI.PRODUCT_NAME_BY_ROW_NUMBER, String.valueOf(rowNumber));
	}

	public String getProductPriceByRowNumber(int rowNumber) {
		return getElementText(driver, CheckoutPageUI.PRICE_BY_ROW_NUMBER, String.valueOf(rowNumber));
	}

	public String getProductQuantityByRowNumber(int rowNumber) {
		return getElementText(driver, CheckoutPageUI.QUANTITY_BY_ROW_NUMBER, String.valueOf(rowNumber));
	}

	public String getProductTotalByRowNumber(int rowNumber) {
		return getElementText(driver, CheckoutPageUI.TOTAL_BY_ROW_NUMBER, String.valueOf(rowNumber));
	}

	public void clickConfirmButtonAtConfirmOrder() {
		waitForElementClickable(driver, CheckoutPageUI.CO_CONFIRM_BUTTON);
		clickToElement(driver, CheckoutPageUI.CO_CONFIRM_BUTTON);
	}

	public boolean isTotalValueAtConfirmOrderCorrect() {
		float subTotal, shippingFee, tax, giftWrappingFee, total;
		subTotal = Float.parseFloat(getSubTotalDisplayedAtConfirmOrder().replace("$", "").replace(",", ""));
		shippingFee = Float.parseFloat(getShippingFeeDisplayedAtConfirmOrder().replace("$", ""));
		tax = Float.parseFloat(getTaxValueDisplayedAtConfirmOrder().replace("$", ""));

		if (getGiftWrappingFeeDisplayedAtConfirmOrder().contains("Yes")) {
			giftWrappingFee = Float.parseFloat(getGiftWrappingFeeDisplayedAtConfirmOrder().replace("Gift wrapping: Yes [+$", "").replace("]", ""));
		} else {
			giftWrappingFee = 0.00f;
		}

		total = subTotal + shippingFee + tax + giftWrappingFee;

		System.out.println("shipping fee = " + shippingFee);
		System.out.println("tax = " + tax);

		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
		String formattedTotal = formatter.format(total);

		String totalAtConfirmOrder = getElementText(driver, CheckoutPageUI.CO_TOTAL_VALUE_TEXT).trim();
		return formattedTotal.equals(totalAtConfirmOrder);
	}

	public String getSuccessMessageTitle() {
		waitForElementVisible(driver, CheckoutPageUI.SUCCESS_PAGE_TITLE);
		return getElementText(driver, CheckoutPageUI.SUCCESS_PAGE_TITLE).trim();
	}

	public String getSuccessMessage() {
		waitForElementVisible(driver, CheckoutPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, CheckoutPageUI.SUCCESS_MESSAGE).trim();
	}

	public boolean isOrderNumberDisplayed() {
		waitForElementVisible(driver, CheckoutPageUI.ORDER_NUMBER_TEXT);
		return isElementDisplayed(driver, CheckoutPageUI.ORDER_NUMBER_TEXT);
	}

	public String getOrderNumberText() {
		String text, orderNumber;
		text = getElementText(driver, CheckoutPageUI.ORDER_NUMBER_TEXT).trim();
		String[] string = text.split(" ");
		orderNumber = string[2];
		System.out.println("order number = " + orderNumber);
		return orderNumber;
	}

	public HomePageObject clickContinueButtonAtSuccessfulOrder() {
		waitForElementClickable(driver, CheckoutPageUI.SO_CONTINUE_BUTTON);
		clickToElement(driver, CheckoutPageUI.SO_CONTINUE_BUTTON);
		return PageGeneratorManager.openHomePage(driver);
	}

	public void clickContinueButtonAtPaymentInfoSection() {
		waitForElementClickable(driver, CheckoutPageUI.PI_CONTINUE_BUTTON);
		clickToElement(driver, CheckoutPageUI.PI_CONTINUE_BUTTON);
	}

}
