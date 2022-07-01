package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

	private WebDriver driver;
	private By btn_HamburgerMenu = By.xpath("//i[@class='hm-icon nav-sprite']");
	private By txt_HomeMenuGreeting = By.id("hmenu-customer-name");
	private By lnk_Deparatments = By.xpath("//ul[@class='hmenu hmenu-visible']//div");
	private By lnk_ReturntoMainMenu = By.xpath("//ul[@class='hmenu hmenu-visible hmenu-translateX']//i");
	private By lnk_SubDepartments = By.xpath("//ul[@class='hmenu hmenu-visible hmenu-translateX']//li");


	public HomePage(WebDriver driver) {
		this.driver = driver;
		if (!driver.getTitle().equals("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in")) {
			throw new IllegalStateException("This is not login Page. The current page is: "+driver.getCurrentUrl());
		}
	}

	public void selectDepartment(String Department) {
		try {
			driver.findElement(btn_HamburgerMenu).click();
			Thread.sleep(2000);
			if (driver.findElements(txt_HomeMenuGreeting).size()>0) {
				List<WebElement> departments=driver.findElements(lnk_Deparatments);
				for (WebElement dept : departments) {
					Actions actions = new Actions(driver);
					actions.moveToElement(dept).build().perform();
					//jsScroll(dept);
					//System.out.println(dept.getText());
					if (dept.getText().trim().equalsIgnoreCase(Department)) {
						dept.click();
						Thread.sleep(2000);
						break;
					}
				}		
			} else {
				//assertTrue("Failed to click on Hamburger Menu", false);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectSubDepartment(String SubDepartment) {
		try {
			if (driver.findElements(lnk_ReturntoMainMenu).size()>0) {
				List<WebElement> subDepartments=driver.findElements(lnk_SubDepartments);
				for (WebElement sdept : subDepartments) {				
					Actions actions = new Actions(driver);
					actions.moveToElement(sdept).build().perform();
					//jsScroll(sdept);
					//System.out.println(sdept.getText());
					if (sdept.getText().trim().equalsIgnoreCase(SubDepartment)) {
						sdept.click();
						Thread.sleep(2000);
						break;
					}
				}		
			} else {
				//assertTrue("User not on SubDepartment menu", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void jsScroll(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
