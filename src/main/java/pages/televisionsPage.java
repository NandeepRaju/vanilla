package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class televisionsPage {

	private WebDriver driver;

	private By txt_Brands = By.xpath("//span[text()='Brands']/parent::div/following::ul/li//span/a/span");
	//private By lnk_BrandChkBox = By.id("//span[text()='Brands']/parent::div/following::ul/li//span/a//input"); 
	/* private By lnk_Deparatments = By.xpath("//ul[@class='hmenu hmenu-visible']//div");
	 * private By lnk_ReturntoMainMenu =
	 * By.xpath("//ul[@class='hmenu hmenu-visible hmenu-translateX']//i"); private
	 * By lnk_SubDepartments =
	 * By.xpath("//ul[@class='hmenu hmenu-visible hmenu-translateX']//li");
	 */

	public televisionsPage(WebDriver driver) {
		this.driver = driver;
		if (!driver.getTitle().equals("Buy the latest LED TVs, 4K TVs and Android TVs online at Best Prices in India-Amazon.in | Shop by size, price, features and more")) {
			throw new IllegalStateException("This is not login Page. The current page is: "+driver.getCurrentUrl());
		}
	}

	public void selectBrand(String Brand) {
		try {
			if (driver.findElements(txt_Brands).size()>0) {
				List<WebElement> brands=driver.findElements(txt_Brands);
				//List<WebElement> brandsChxBox=driver.findElements(lnk_BrandChkBox);
				for (int i = 0; i < brands.size(); i++){
					Actions actions = new Actions(driver);
					actions.moveToElement(brands.get(i)).build().perform();
					System.out.println(brands.get(i).getText().trim());
					if (brands.get(i).getText().trim().equalsIgnoreCase(Brand)) {
						brands.get(i).click();
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

}
