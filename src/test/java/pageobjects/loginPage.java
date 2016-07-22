package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage extends baseClass{
	/*Login element*/
	By ELEMENT_EMAIL= By.id("email");
	By ELEMENT_PASSWORD = By.id("pass");
	By ELEMENT_LOGIN_BUTTON = By.id("send2");
	
	public loginPage(WebDriver driver){
		super(driver);
	}    

	/**
	 * Login function
	 * @param userName
	 * @param pass
	 */
	public loginPage doLogin(String userName, String pass){
		System.out.println("do login");
		driver.findElement(ELEMENT_EMAIL).sendKeys(userName);
		driver.findElement(ELEMENT_PASSWORD).sendKeys(pass);
		driver.findElement(ELEMENT_LOGIN_BUTTON).click();
		System.out.println("login successfully");
		return new loginPage(driver);
	}
}
