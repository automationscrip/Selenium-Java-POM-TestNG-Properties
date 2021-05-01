package quickstart.Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Constants.TimeOuts;

public class GoogleSearchPage  {

	WebDriver driver;
	Properties properties;
	
	/**
	 * Each Page class has all the locators of WeblElements in the respective 
	 * Properties file with same name(this is for convenience, you can name it any way you want).
	 * These WebElements can be used in the page class across all the methods.
	 * Notice the import statement import java.util.Properties;;in the top.
	 * This is provided by Java Util
	 */


	/**
	 * Constructor of the Page class to set the driver and load property file.
	 * @param driver
	 * @throws IOException 
	 */
	public GoogleSearchPage(WebDriver driver) throws IOException {
		this.driver=driver;
		this.properties= new Properties();
		InputStream input= new FileInputStream("GoogleSearchPage.properties");
		this.properties.load(input);
	}

	/**
	 * This method is for entering a keyword in an input field on the webpage
	 * Notice how the locators of WebElement searchBox is read from the 
	 * properties file which is loaded in the constructor of the class.
	 * command to read the property - properties.getProperty("searchBox")
	 * @param keyword A string value to be entered in the field
	 */
	public void enterKeyWord(String keyword) {

		driver.findElement(By.xpath(properties.getProperty("searchBox"))).sendKeys(keyword);
		driver.findElement(By.xpath(properties.getProperty("searchBox"))).sendKeys(Keys.ENTER);
	}

	public void waitForResultLink(String keyword) {
		String searchResultLink="//h3[contains(.,'"+keyword+"')]";
		WebDriverWait wait= new WebDriverWait(driver, TimeOuts.DEFAULT_TIMEOUT);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResultLink)));		
	}

	public  void searchKeyword(String keyword) {

		this.enterKeyWord(keyword);
		this.waitForResultLink(keyword);			
	}


}
