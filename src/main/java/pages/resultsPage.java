package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class resultsPage {

	WebDriver driver;
	private By drpDwn_SortBy= By.xpath("//span[@aria-label='Sort by:']");
	private By lnk_Products = By.xpath("//div[@data-component-type='s-search-result']");

	public resultsPage(WebDriver driver) {
		this.driver = driver;	
		if (!driver.getTitle().equals("Amazon.in")) {
			throw new IllegalStateException("This is not login Page. The current page is: "+driver.getCurrentUrl());
		}
	}

	public void sortBy(String sortBy) {
		try {
			driver.findElement(drpDwn_SortBy).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//li[@role='option']/a[text()='"+sortBy+"']")).click();
			//Select select = new Select(driver.findElement(drpDwn_SortBy));
			//select.selectByVisibleText(sortBy);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnResult(int resultNo) {
		try {
			if (driver.findElements(lnk_Products).size()>0) {
				List<WebElement> products=driver.findElements(lnk_Products);
				products.get(resultNo).click();
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
