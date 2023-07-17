
//TestID -LGN9P-KDWMT-VVXSF-FPC8N
package org.lambda.LambdaAutomation;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LambdaTestAutomation2 {

	private WebDriver driver;

	@BeforeMethod
	@Parameters({ "browser", "version", "platform" })
	public void setup(String browser, String version, String platform) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();

// Set LambdaTest credentials
		capabilities.setCapability("user", "christy.shily");
		capabilities.setCapability("accessKey", "8Cx9NQnqii9Oq1yShSjxTthBwuquF4dQctQxTGwzWtHBjzPxQd");

// Set browser and platform
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			capabilities.merge(chromeOptions);
		} else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions edgeOptions = new EdgeOptions();
			capabilities.merge(edgeOptions);
		}
		capabilities.setCapability("browserName", browser);
		capabilities.setCapability("version", version);
		capabilities.setCapability("platform", platform);

// Initialize WebDriver
		driver = new RemoteWebDriver(new URL(
				"https://christy.shily:8Cx9NQnqii9Oq1yShSjxTthBwuquF4dQctQxTGwzWtHBjzPxQd@hub.lambdatest.com/wd/hub"),
				capabilities);
	}

	@Test
	public void lambdaTestIntegrationScenario() throws InterruptedException {
// 1. Navigate to http://www.lambdatest.com
		driver.get("http://www.lambdatest.com");

// 2. Perform an explicit wait until all the elements in the DOM are available.
		WebDriverWait wait = new WebDriverWait(driver, (5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"a[class='uppercase font-bold text-black text-size-16 tracking-widest inline-block hover:underline']")));

// 3. Scroll to the WebElement 'SEE ALL INTEGRATION' using the scrollIntoView() method.
		WebElement seeAllIntegrationLink = driver.findElement(By.cssSelector(
				"a[class='uppercase font-bold text-black text-size-16 tracking-widest inline-block hover:underline']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seeAllIntegrationLink);

// 4. Click on the link and ensure that it opens in a new tab.
		String mainWindowHandle = driver.getWindowHandle();
		seeAllIntegrationLink.click();

// 5. Save the window handles in a list. Print the window handles of the opened windows.
		Set<String> allWindowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<>(allWindowHandles);
		System.out.println("Window Handles: " + windowHandlesList);

// 6. Verify whether the URL is the same as expected URL.
		String expectedURL = "https://www.lambdatest.com/integrations";
		for (String windowHandle : windowHandlesList) {
			driver.switchTo().window(windowHandle);
			String actualURL = driver.getCurrentUrl();
			Assert.assertEquals(actualURL, expectedURL);
		}

// 7. Close the current browser window.
		driver.close();

// Switch back to the main window
		driver.switchTo().window(mainWindowHandle);
	}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
}