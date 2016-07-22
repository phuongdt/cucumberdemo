package pageobjects;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class shoppingPage extends baseClass{
	/*Menu element*/
	static String ELEMENT_WOMEN_MAIN_MENU = "//*[text()='${name}']";

	/*Product element*/
	String ELEMENT_PRODUCT_NAME = "//*[@class='product-name']//*[@title='${name}']";
	String ELEMENT_ADD_TO_CARD_BY_NAME="//*[@class='product-name']//*[@title='${name}']/../..//*[@title='Add to Cart']";
	String ELEMENT_ADD_NUMBER_BY_NAME="//*[@id='shopping-cart-table']//*[@title='${name}']/../..//*[@class='input-text qty']";
	String ELEMENT_ADD_TO_CARD_BY_INDEX="//li[${index}]//*[@title='Add to Cart']";
	String ELEMENT_ADD_NUMBER_BY_INDEX="//*[@id='shopping-cart-table']/tbody/tr[${index}]//*[@class='input-text qty']";
	By ELEMENT_CONTINUE_SHOPPING = By.xpath("//*[@title='Continue Shopping']");
	String ELEMENT_UPDATE_BUTTON_BY_NAME="//*[@id='shopping-cart-table']//*[@title='${name}']/../..//*[@title='Update']";
	By ELEMENT_PROCEED_CHECKOUT = By.xpath("//*[@class='page-title title-buttons']//*[@title='Proceed to Checkout']");

	/*Filling information*/
	By ELEMENT_FIRST_NAME = By.id("billing:firstname");
	By ELEMENT_LAST_NAME = By.id("billing:lastname");
	By ELEMENT_ADDRESS = By.id("billing:street1");
	By ELEMENT_CITY = By.id("billing:city");
	By ELEMENT_STATE = By.id("billing:region_id");
	By ELEMENT_ZIP = By.id("billing:postcode");
	By ELEMENT_PHONE = By.id("billing:telephone");
	By ELEMNT_CONTINUE_BUTTON = By.xpath("//*[@title='Continue']");

	/*Shipping method*/
	By ELEMENT_CONTINUE_SHIPPING_METHOD = By.xpath("//*[@id='shipping-method-buttons-container']//*[@type='button']");

	/*Payment information*/
	By ELEMENT_CONTINUE_PAYMENT=By.xpath("//*[@id='payment-buttons-container']//*[@type='button']");
	By ELEMENT_RADIO_CHECK_MONEY=By.id("p_method_checkmo");
	By ELEMENT_RADIO_CREDIT_CARD=By.id("p_method_ccsave");
	/*Define a type of check money*/
	public enum type {
		CHECK, CREDIT;
	}
	
	/*Place order*/
	By ELEMNT_PLACE_ORDER_BUTTON = By.xpath("//*[@title='Place Order']");
	
	/*purchase successfully message*/
	By ELEMENT_PURCHASE_SUCCESSFULLY= By.xpath("//*[@class='sub-title' and text()='Thank you for your purchase!']");
	
	public shoppingPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Select menu
	 * @param menu
	 */
	public shoppingPage selectMainMenu(String menu){
		System.out.println("select menu");
		driver.findElement(By.xpath(ELEMENT_WOMEN_MAIN_MENU.replace("${name}", menu))).click();;
		System.out.println("select menu successfully");
		return new shoppingPage(driver);
	}

	/**
	 * select products"
	 * @param main
	 * @param arrProducts
	 * @return
	 */
	public shoppingPage addToCardByName(String main, List<HashMap<String, String>> arrProducts){
		System.out.println("select products");
		if(arrProducts!=null){
			for(int i = 0; i < arrProducts.size(); i++){
				selectMainMenu(main);
				System.out.println(arrProducts.get(i).get("product"));
				driver.findElement(By.xpath(ELEMENT_ADD_TO_CARD_BY_NAME.replace("${name}", arrProducts.get(i).get("product")))).click();
				driver.findElement(By.xpath(ELEMENT_ADD_NUMBER_BY_NAME.replace("${name}", arrProducts.get(i).get("product")))).clear();
				driver.findElement(By.xpath(ELEMENT_ADD_NUMBER_BY_NAME.replace("${name}", arrProducts.get(i).get("product")))).sendKeys(arrProducts.get(i).get("number"));
				driver.findElement(By.xpath(ELEMENT_UPDATE_BUTTON_BY_NAME.replace("${name}", arrProducts.get(i).get("product")))).click();
				if(i<(arrProducts.size()-1)){
					driver.findElement(ELEMENT_CONTINUE_SHOPPING).click();
				}
			}
		}
		return new shoppingPage(driver);
	}

	/**
	 * proceed to checkout
	 * @return
	 */
	public shoppingPage clickToCheckout(){
		System.out.println("proceed to checkout");
		driver.findElement(ELEMENT_PROCEED_CHECKOUT).click();
		return new shoppingPage(driver);
	}
	/**
	 * fill information
	 * @param first
	 * @param last
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param phone
	 * @return
	 */
	public shoppingPage doFillingInfor(String first, String last, String address, String city, String state, String zip, String phone){
		System.out.println("fill information");
		driver.findElement(ELEMENT_FIRST_NAME).clear();
		driver.findElement(ELEMENT_LAST_NAME).clear();
		driver.findElement(ELEMENT_ADDRESS).clear();
		driver.findElement(ELEMENT_CITY).clear();
		driver.findElement(ELEMENT_ZIP).clear();
		driver.findElement(ELEMENT_PHONE).clear();

		driver.findElement(ELEMENT_FIRST_NAME).sendKeys(first);
		driver.findElement(ELEMENT_LAST_NAME).sendKeys(last);
		driver.findElement(ELEMENT_ADDRESS).sendKeys(address);
		driver.findElement(ELEMENT_CITY).sendKeys(city);
		Select drState = new Select(driver.findElement(ELEMENT_STATE));
		drState.selectByVisibleText(state);
		driver.findElement(ELEMENT_ZIP).sendKeys(zip);
		driver.findElement(ELEMENT_PHONE).sendKeys(phone);
		driver.findElement(ELEMNT_CONTINUE_BUTTON).click();
		return new shoppingPage(driver);
	}

	/**
	 * clickToContinueShippingMethod
	 * @return
	 */
	public shoppingPage clickToContinueShippingMethod(){
		System.out.println("clickToContinueShippingMethod");
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(ELEMENT_CONTINUE_SHIPPING_METHOD));
		return new shoppingPage(driver);
	}

	/**
	 * select Credit Method
	 * @return
	 */
	public shoppingPage selectCreditMethod(type tMethod){
		System.out.println("select Credit Method");
		switch(tMethod){
		case CHECK:
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(ELEMENT_RADIO_CHECK_MONEY));
			break;
		case CREDIT:
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(ELEMENT_CONTINUE_PAYMENT));
			break;
		default:
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(ELEMENT_RADIO_CREDIT_CARD));
			break;
		}
		driver.findElement(ELEMENT_CONTINUE_PAYMENT).click();
		return new shoppingPage(driver);
	}
	
	/**
	 * clickToPlaceOrder
	 * @return
	 */
	public shoppingPage clickToPlaceOrder(){
		System.out.println("clickToPlaceOrder");
		driver.findElement(ELEMNT_PLACE_ORDER_BUTTON).click();
		return new shoppingPage(driver);
	}
	
	/**
	 * verify ordering
	 */
	public Boolean verifyOrdering(){
		System.out.println("verify ordering");
		return (driver.findElement(ELEMENT_PURCHASE_SUCCESSFULLY)!=null);
	}
}

