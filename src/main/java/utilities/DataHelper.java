package utilities;

import java.util.Locale;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;

public class DataHelper {
	Locale locale = new Locale("en");
	Faker faker = new Faker(locale);

	public static DataHelper getDataHelper() {
		return new DataHelper();
	}

	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}

	public String getFirstName() {
		return faker.name().firstName();
	}

	public String getLastName() {
		return faker.name().lastName();
	}

	public String getCompanyName() {
		return faker.company().name();
	}

	public String getPassword() {
		return faker.internet().password(6, 10);
	}

	public String getPhoneNumber() {
		return faker.phoneNumber().cellPhone();
	}

	public String getCardHolderName() {
		return faker.name().fullName();
	}

	public String getCardNumber() {
		String cardNumber = null;
		cardNumber = faker.finance().creditCard();
		// switch (creditCardType.toLowerCase()) {
		// case "visa":
		// cardNumber = faker.finance().creditCard();
		// break;
		// case "master card":
		// cardNumber = faker.finance().creditCard("mastercard");
		// break;
		// case "american express":
		// cardNumber = faker.finance().creditCard("american_express");
		// break;
		// default:
		// throw new IllegalArgumentException("Unsupported credit card type: " + creditCardType);
		// }
		return cardNumber;
	}
}
