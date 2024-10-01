package commons;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;

	public BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	public WebDriver getDriverInstance() {
		return this.driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String userType, String environmentName) {

		if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			driver = new FirefoxDriver(options);

		} else if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);

		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			driver = new EdgeDriver(options);

		} else if (browserName.equals("opera")) {
			WebDriverManager.operadriver().setup();
			OperaOptions options = new OperaOptions();
			driver = new OperaDriver(options);

		} else {
			throw new RuntimeException("Browser name is invalid");
		}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(getEnvironmentUrl(userType, environmentName));
		return driver;
	}

	protected String getEnvironmentUrl(String userType, String environmentName) {
		String envUrl = null;
		if (userType.equals("user")) {
			if (environmentName.equalsIgnoreCase(EnvironmentList.DEV.toString())) {
				envUrl = "http://demo.nopcommercelocaldev";
			} else if (environmentName.equalsIgnoreCase(EnvironmentList.TEST.toString())) {
				envUrl = "http://demo.nopcommercelocal";
			} else if (environmentName.equalsIgnoreCase(EnvironmentList.STAGING.toString())) {
				envUrl = "http://demo.nopcommercelocalstaging";
			} else if (environmentName.equalsIgnoreCase(EnvironmentList.PROD.toString())) {
				envUrl = "http://nopcommercelocal";
			}
		} else {
			if (environmentName.equalsIgnoreCase(EnvironmentList.DEV.toString())) {
				envUrl = "http://demo.nopcommercelocaldev/admin";
			} else if (environmentName.equalsIgnoreCase(EnvironmentList.TEST.toString())) {
				envUrl = "http://demo.nopcommercelocal/admin";
			} else if (environmentName.equalsIgnoreCase(EnvironmentList.STAGING.toString())) {
				envUrl = "http://demo.nopcommercelocalstaging/admin";
			} else if (environmentName.equalsIgnoreCase(EnvironmentList.PROD.toString())) {
				envUrl = "http://nopcommercelocal/admin";
			}
		}
		return envUrl;
	}

	protected int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	protected boolean verifyTrue(boolean condition) {
		boolean status = true;
		try {
			Assert.assertTrue(condition);
			log.info("---------------------- Passed -----------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("---------------------- Failed -----------------------");
		}
		return status;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean status = true;
		try {
			Assert.assertFalse(condition);
			log.info("---------------------- Passed -----------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("---------------------- Failed -----------------------");
		}
		return status;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean status = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info("---------------------- Passed -----------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("---------------------- Failed -----------------------");
		}
		return status;
	}

	protected void returnToAdminDashboard(String url) {
		driver.get(url);
	}

	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			}

			if (osName.contains("windows")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill" + browserDriverName;
			}

		} catch (Exception e) {
			log.info(e.getMessage());

		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
