package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class registerPage extends baseClass{
	/*Register element*/
	By ELEMENT_FIRST_NAME = By.id("firstname");
	By ELEMENT_LAST_NAME = By.id("lastname");
	By ELEMENT_EMAIL = By.id("email_address");
	By ELEMENT_PASS = By.id("password");
	By ELEMENT_CONFIRM_PASS = By.id("confirmation");
	By ELEMENT_REGISTER_BUTTON = By.xpath("//*[@title='Register' and @type='submit']");

	public registerPage(WebDriver driver){
		super(driver);
	}    
	
	/**
	 * registter new accout
	 * @param first
	 * @param last
	 * @param email
	 * @param pass
	 * @return
	 */
	public registerPage doRegister(String first, String last, String email, String pass){
		System.out.println("registter new accout");
		driver.findElement(ELEMENT_FIRST_NAME).sendKeys(first);
		driver.findElement(ELEMENT_LAST_NAME).sendKeys(last);
		driver.findElement(ELEMENT_EMAIL).sendKeys(email);
		driver.findElement(ELEMENT_PASS).sendKeys(pass);
		driver.findElement(ELEMENT_CONFIRM_PASS).sendKeys(pass);
		driver.findElement(ELEMENT_REGISTER_BUTTON).click();
		return new registerPage(driver);
	}
}
