package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class SubscriptionsPageObject extends BasePage {
	WebDriver driver;

	public SubscriptionsPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
