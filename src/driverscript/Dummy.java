package driverscript;
import org.openqa.selenium.WebDriver;

import commonFunctionliberary.FunctionLibrary;
import utilities.PropertyFileUtil;
public class Dummy {

	public static void main(String[] args) throws Exception {
	WebDriver driver1= FunctionLibrary.startbrowser();
	FunctionLibrary.OpenAPPlication(driver1);
	FunctionLibrary.waitforElement(driver1, "id", "username", "10");
	FunctionLibrary.typeaction(driver1, "id", "username", "admin");
	FunctionLibrary.waitforElement(driver1, "name", "password", "10");
	FunctionLibrary.typeaction(driver1, "name", "password", "master");
	FunctionLibrary.waitforElement(driver1, "xpath", "//*[@id='btnsubmit']", "10");
	FunctionLibrary.clickaction(driver1, "xpath", "//*[@id='btnsubmit']");
	FunctionLibrary.waitforElement(driver1, "id", "logout", "10");
	FunctionLibrary.clickaction(driver1, "id", "logout");
	FunctionLibrary.waitforElement(driver1, "xpath", "//*[text()='OK!']", "10");
	FunctionLibrary.clickaction(driver1, "xpath", "//*[text()='OK!']");
	FunctionLibrary.closeBrowser(driver1);
	
	//System.out.println(PropertyFileUtil.getValueForKey("browser"));
	

	}

}
