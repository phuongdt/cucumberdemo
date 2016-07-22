package testRunning;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import pageobjects.homePage;
import pageobjects.loginPage;
import pageobjects.registerPage;
import pageobjects.shoppingPage;
import pageobjects.shoppingPage.type;
import utils.PropertiesUtil;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class shopScenario {
	public static WebDriver driver;
	homePage hpage;
	registerPage rPage;
	loginPage lPage;
	shoppingPage sPage;
	private String baseUrl="";
	private String email="";
	private String passWord="";
	private String fistName="";
	private String lastName="";
	private String address="";
	private String city="";
	private String state=""; 
	private String zip="";
	private String phone="";
	private String menu="";
	private List<HashMap<String,String>> arrProducts = null;

	public shopScenario() throws IOException
	{		
		File file;
		String webdriver_operator = System.getProperty("webdriver.operator");
		String webdriver_driver = System.getProperty("webdriver.driver");
		if (webdriver_operator==null || webdriver_operator=="") 
			webdriver_operator = PropertiesUtil.load_config_sys().getProperty("webdriver.operator");
		if (webdriver_driver==null || webdriver_driver=="") 
			webdriver_driver = PropertiesUtil.load_config_sys().getProperty("webdriver.driver");
		if(webdriver_driver.contains("phantomjs")){
			System.out.println("run with phantomjs");
			if(webdriver_operator=="windows")
				file = new File(System.getProperty("user.dir")+File.separator+"phantomjs.exe");		
			else
				file = new File(System.getProperty("user.dir")+File.separator+"phantomjs_"+webdriver_operator);	
			System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
			driver = new PhantomJSDriver();
		}
		if(webdriver_driver.contains("firefox")){
			System.out.println("run with firefox");
			driver = new FirefoxDriver();
		}
		hpage = new homePage(driver);
		rPage = new registerPage(driver);
		lPage = new loginPage(driver);
		sPage = new shoppingPage(driver);
		baseUrl="http://live.guru99.com/";
		email=hpage.getRandomString()+"@gmail.com";
		passWord="123456";
		fistName="Phuong";
		lastName="Phuong";
		address="Ha Noi";
		city="Ha Noi";
		state="Alabama"; 
		zip="084";
		phone="999999";
		menu="Mobile";
		arrProducts = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> sampleData1 = new HashMap<String,String>();
		sampleData1.put("product","Samsung Galaxy");
		sampleData1.put("number","2");
		HashMap<String,String> sampleData2 = new HashMap<String,String>();
		sampleData2.put("product","Sony Xperia");
		sampleData2.put("number","4");
		arrProducts.add(sampleData1);
		arrProducts.add(sampleData2);
	}

	@When("^I open website$")
	public void i_open_website() throws Throwable {
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	@When("^I register$")
	public void i_register() throws Throwable {
		System.out.println("register");
		Thread.sleep(3000);
		hpage.goToRegisterPage();
		rPage.doRegister(fistName,lastName,email,passWord);
	}

	@When("^I log in$")
	public void i_log_in() throws Throwable {
		System.out.println("log-in");
		hpage.goToLoginPage();
		lPage.doLogin(email,passWord);
	}

	@Then("^I select products$")
	public void i_select_products() throws Throwable {
		System.out.println("select products");
		sPage.addToCardByName(menu,arrProducts);
		Thread.sleep(3000);
		sPage.clickToCheckout();
		Thread.sleep(3000);
		sPage.doFillingInfor(fistName, lastName, address, city, state, zip, phone);
		Thread.sleep(3000);
		sPage.clickToContinueShippingMethod();
		Thread.sleep(3000);
		sPage.selectCreditMethod(type.CHECK);
		Thread.sleep(3000);
		sPage.clickToPlaceOrder();
		Thread.sleep(3000);
		System.out.println(sPage.verifyOrdering());
		assert (sPage.verifyOrdering()==true);
	}

	@Then("^I log out$")
	public void i_log_out() throws Throwable {
		System.out.println("log-out");
		hpage.goToLogoutPage();
	}

	@After
	public void closeBrowser(){
		driver.quit();
	}
}
