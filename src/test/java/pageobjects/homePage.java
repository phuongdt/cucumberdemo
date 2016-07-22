package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;

public class homePage  extends baseClass{
	/*Menu element*/
	By ELEMENT_ACCOUNT_LINK = By.xpath("//*[@class='label' and text()='Account']");
	By ELEMENT_REGISTER_LINK = By.xpath("//*[@title='Register']");
	By ELEMENT_LOGIN_LINK = By.linkText("Log In");
	By ELEMENT_LOGOUT_LINK = By.linkText("Log Out");
	
	public homePage(WebDriver driver){
		super(driver);
	}    
	
	/**
	 * goToRegisterPage
	 * @return
	 * @throws InterruptedException 
	 */
	public homePage goToRegisterPage() throws InterruptedException{
		System.out.println("Go to register page");
		driver.findElement(ELEMENT_ACCOUNT_LINK).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(ELEMENT_REGISTER_LINK));
		Thread.sleep(5000);
		return new homePage(driver);
	}
	
	/**
	 * Go to login page
	 * @return
	 */
	public homePage goToLoginPage(){
		System.out.println("Go to login page");
		driver.findElement(ELEMENT_ACCOUNT_LINK).click();
		driver.findElement(ELEMENT_LOGIN_LINK).click();
		return new homePage(driver);
	}
	
	/**
	 * Go to logout page
	 * @return
	 */
	public homePage goToLogoutPage(){
		System.out.println("Go to logout page");
		driver.findElement(ELEMENT_ACCOUNT_LINK).click();
		driver.findElement(ELEMENT_LOGOUT_LINK).click();
		return new homePage(driver);
	}
}