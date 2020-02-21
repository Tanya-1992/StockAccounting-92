package commonFunctionliberary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import utilities.PropertyFileUtil;

public class FunctionLibrary {
	static WebDriver driver;
	public static  WebDriver startbrowser() throws Exception{
		
		if(PropertyFileUtil.getValueForKey("browser").equalsIgnoreCase("chrome"))
		{
			
			System.setProperty("webdriver.chrome.driver","D:\\New folder (2)\\tanya\\StockAccounting_hybrid\\Driver\\chromedriver.exe");
			driver=new ChromeDriver();
		}
	else if(PropertyFileUtil.getValueForKey("browser").equalsIgnoreCase("FireFox"))
	{
		System.setProperty("webdriver.gecko.driver", "D:\\New folder (2)\\tanya\\StockAccounting_hybrid\\Driver\\geckodriver.exe");
	}else 
	{
		System.setProperty("webdriver.ie.driver", "D:\\New folder (2)\\tanya\\StockAccounting_hybrid\\Driver\\IEDriverServer.exe");
	}
		return driver;
}
	public static void  OpenAPPlication(WebDriver driver) throws Exception{
		driver.get(PropertyFileUtil.getValueForKey("url"));
		driver.manage().window().maximize();
		
	}
	  public static void waitforElement (WebDriver driver,String locatortype,String locatorvalue,String waittime){
		  WebDriverWait  mywait=new WebDriverWait(driver, Integer.parseInt(waittime));
		  if(locatortype.equalsIgnoreCase("id"))
		  {
			  mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
		  }
			if (locatortype.equalsIgnoreCase("x.path"))
			{
				mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
			} if (locatortype.equalsIgnoreCase("name")) {
				mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
			}if (locatortype.equalsIgnoreCase("className")) {
				mywait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorvalue)));
			}if (locatortype.equalsIgnoreCase("tagName")) {
				mywait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorvalue)));
			}if (locatortype.equalsIgnoreCase("linktest")) {
				mywait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorvalue)));
			}else {
				System.out.println("unable to locate for waitForElement method with "+locatortype);
			}
	  }
	  public static void typeaction( WebDriver driver,String locatortype,String locatorvalue,String testdata){
		  if(locatortype.equalsIgnoreCase("id")){
			  driver.findElement(By.id(locatorvalue)).clear();
			  driver.findElement(By.id(locatorvalue)).sendKeys(testdata);;
			  
		  }if (locatortype.equalsIgnoreCase("xpath")) {
			  driver.findElement(By.xpath(locatorvalue)).clear();
			  driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
			
		}if (locatortype.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
		}if(locatortype.equalsIgnoreCase("className")){
			
			driver.findElement(By.className(locatorvalue)).clear();
			driver.findElement(By.className(locatorvalue)).sendKeys(testdata);
		}if (locatortype.equalsIgnoreCase("tagName")) {
			driver.findElement(By.tagName(locatorvalue)).clear();
			driver.findElement(By.tagName(locatorvalue)).sendKeys(testdata);
			
		}if (locatortype.equalsIgnoreCase("linktest")) {
			driver.findElement(By.linkText(locatorvalue)).clear();
			driver.findElement(By.linkText(locatorvalue)).sendKeys(testdata);
		}else {
			System.out.println("unable to locate typeaction with"+locatortype);
		}
		  
	  }
	  public static void clickaction(WebDriver driver,String locatortype,String locatorvalue){
		  
		  if(locatortype.equalsIgnoreCase("id"))
		  {
			  driver.findElement(By.id(locatorvalue)).click();
	     }if (locatortype.equalsIgnoreCase("xpath"))
	     {
	    	  driver.findElement(By.xpath(locatorvalue)).click();
		}
	     if (locatortype.equalsIgnoreCase("className"))
	     {
			driver.findElement(By.className(locatorvalue)).click();
		}
	     if (locatortype.equalsIgnoreCase("name")) 
	     {
			driver.findElement(By.name(locatorvalue)).click();
	     if (locatortype.equalsIgnoreCase("tagName")) 
	     {
			driver.findElement(By.tagName(locatorvalue)).click();
	  }if (locatortype.equalsIgnoreCase("linktext"))
	  {
			driver.findElement(By.linkText(locatorvalue)).click();
	  }
	  }
	  }
	  public static void tableValidation(WebDriver driver,String column) throws Exception{
			
			FileReader  fr=new FileReader("./CaptureData/suppnumber.txt");
			BufferedReader br=new BufferedReader(fr);
			
			String Exp_data=br.readLine();
			
			if(driver.findElement(By.id(PropertyFileUtil.getValueForKey("searchtextbox"))).isDisplayed()){
				Thread.sleep(5000);
				driver.findElement(By.id(PropertyFileUtil.getValueForKey("searchtextbox"))).sendKeys(Exp_data);
				driver.findElement(By.id(PropertyFileUtil.getValueForKey("searchbutton"))).click();
			}else{
				driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchpanelbutton"))).click();
				Thread.sleep(5000);
				driver.findElement(By.id(PropertyFileUtil.getValueForKey("searchtextbox"))).sendKeys(Exp_data);
				driver.findElement(By.id(PropertyFileUtil.getValueForKey("searchbutton"))).click();
			}
			
			WebElement table=driver.findElement(By.id(PropertyFileUtil.getValueForKey("suppliertable")));
			
			List<WebElement>rows=table.findElements(By.tagName("tr"));
			
			for(int i=1;i<rows.size();i++){
			       String act_data= driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+column+"]/div/span")).getText();	
			       Assert.assertEquals(Exp_data, act_data); 
			       System.out.println(act_data+"   "+Exp_data);
			       break;
			}
			}
	 
	  public static String generateDate(){
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_DD_hh_mm_ss");
			String requiredDate=sdf.format(d);
			return requiredDate;
		}
		
		
		public static void captureData(WebDriver driver,String locatortytpe,
				String locatorvalue) throws Exception{
			
			String supplierdata="";
			
			if(locatortytpe.equalsIgnoreCase("id")){
				supplierdata=driver.findElement(By.id(locatorvalue)).getAttribute("value");
			}
			
			else if(locatortytpe.equalsIgnoreCase("xpath")){
				supplierdata=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
			}
			
			else if(locatortytpe.equalsIgnoreCase("name")){
				supplierdata=driver.findElement(By.name(locatorvalue)).getAttribute("value");
			}
			
			FileWriter fw=new FileWriter ("D:\\New folder (2)\\tanya\\StockAccounting_hybrid\\CaptureData\\suppnumber.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(supplierdata);
			bw.flush();
			bw.close();	
		}
		 public static void closeBrowser(WebDriver driver){
				driver.close();
			}
		
}