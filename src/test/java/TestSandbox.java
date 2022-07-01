import amazon.config.EnvFactory;
import amazon.factories.DriverFactory;
import pages.HomePage;
import pages.resultsPage;
import pages.televisionsPage;

import com.typesafe.config.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class TestSandbox {
	private static Config config = EnvFactory.getInstance().getConfig();
	private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");
	private WebDriver driver = DriverFactory.getDriver();
	HomePage home;
	televisionsPage tv;
	resultsPage result;

	@Tag("smokeTest")
	@DisplayName("This test is for demo purpose only to show that the basic code works." +
			"You have to use the best practices that you normally use to design your tests")
	/* @Test
    void assertThatHomePageTitleIsCorrect() {
        driver.get(HOME_PAGE_URL);
        assertEquals("Amazon.com. Spend less. Smile more.", driver.getTitle());
    }*/
	
	@Test
	void validateProductAboutData() {
		driver.get(HOME_PAGE_URL);
		assertEquals("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in", driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		home = new HomePage(driver);
		home.selectDepartment("TV, Appliances, Electronics");
		home.selectSubDepartment("Televisions");
		tv = new televisionsPage(driver);
		tv.selectBrand("Samsung");
		result = new resultsPage(driver);
		result.sortBy("Price: High to Low");
		result.clickOnResult(2);
		String parentWindow = driver.getWindowHandle();
		ArrayList<String> windows = new ArrayList<String> (driver.getWindowHandles());
	    for (String string : windows) {
			if (!string.equals(parentWindow)) {
				driver.switchTo().window(string);
				assertEquals("About this item", driver.findElement(By.xpath("//div[@id='feature-bullets']/h1")).getText().trim());
				System.out.println(driver.findElement(By.xpath("//div[@id='feature-bullets']")).getText().trim());
				driver.close();
			}
		}
	    driver.switchTo().window(parentWindow);
	    //driver.close();
	    driver.quit();		
	}
}
