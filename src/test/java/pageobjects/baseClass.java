package pageobjects;

import java.util.Random;

import org.openqa.selenium.WebDriver;

public abstract class baseClass {
	public static WebDriver driver;
	public static boolean bResult;

	public  baseClass(WebDriver driver){
		baseClass.driver = driver;
		baseClass.bResult = true;
	}
	
	/**
	 * getRandomString
	 * @return
	 */
	public String getRandomString(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
}
