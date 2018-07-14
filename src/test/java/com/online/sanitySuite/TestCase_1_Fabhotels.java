package com.online.sanitySuite;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.steadystate.css.parser.ParseException;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


public class TestCase_1_Fabhotels {
	
//	WebElement firstCityLink;
//	@FindBy(xpath = "//android.support.v7.widget.RecyclerView[contains(@resource-id,'rv_fabDeals')]/android.widget.FrameLayout[@index='1']")
//	
//	WebElement List_calendar;
//    @FindBy(xpath = "//android.widget.CheckedTextView[contains(@resource-id,'btn_calendar')]")
//    
	
	public static Properties config=null;
	public static Properties data=null;
	public static String old_payment;
	public static String payment;
	public static Logger log = null;
	public static AndroidDriver driver;	
	
	

	

	//Syn_consumer_app consumer;
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		            config = new Properties();
					String config_fileName = "config.properties";
					String config_path = System.getProperty("user.dir") + File.separator+ "config" + File.separator + config_fileName;
					FileInputStream config_ip = new FileInputStream(config_path);
					config.load(config_ip);	
		            DesiredCapabilities capabilities = new DesiredCapabilities();
					capabilities.setCapability("platformName", config.getProperty("platformName"));
					capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, config.getProperty("deviceName"));
					capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, config.getProperty("deviceVersion"));
				    capabilities.setCapability("appPackage", config.getProperty("app-package"));
					capabilities.setCapability("appActivity", config.getProperty("app-activity"));
					
		            driver= new AndroidDriver( new URL("http://127.0.0.1:"+config.getProperty("AppiumPort")+"/wd/hub"), capabilities) ;
					System.out.println("Driver"+driver);
					System.out.println("App Launched ");
					Thread.sleep(10000);
		
	}
	
	
	@Test(priority=1)
	public void click_on_search_city(){
		driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'rv_fabCities')]/android.widget.FrameLayout[@index='1']")).click();
     }
	
	@Test(priority=2)
	public void select_checkin_checkout_date_in_date_picker_and_verify_date_format() throws ParseException, java.text.ParseException{
		PageBase base=new PageBase(driver);
		base.delay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.CheckedTextView[contains(@resource-id,'btn_calendar')]")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.CheckedTextView[contains(@resource-id,'btn_calendar')]")));
	    String date=base.getdatefromcurrent(0,"MM/dd/yy"); 
	    
        
		base.delay();
		String date1=base.getdatefromcurrent(-1,"MM/dd/yy"); 
		

		base.clickElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_done')]")));
		base.longDelay();
		String date3=base.getTextForElement(driver.findElement(By.id("btn_calendar")));
		System.out.println("date is"+date3);
		String dd[]=date3.split("-");
		String checkin_date=dd[0].trim();
		String checkout_date=dd[1].trim();
		base.validateDateFormat("dd MMM yy",checkin_date);
	}
	
	@Test(priority=3)
	public void click_on_first_hotel_link(){
		PageBase base=new PageBase(driver);
		base.delay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'rv_hotel_listing')]/android.widget.FrameLayout[@index='1']")));
		base.clickElement(driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'rv_hotel_listing')]/android.widget.FrameLayout[@index='1']")));
	}
	
	@Test(priority=4)
	public void click_on_select_room_button(){
		PageBase base=new PageBase(driver);
		base.delay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_bookNow')]")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_bookNow')]")));
	}
	
	@Test(priority=5)
	public void click_on_first_pagination_button(){
		PageBase base=new PageBase(driver);
		base.delay();
		List<WebElement> element=driver.findElements(By.className("android.widget.RadioButton"));
		element.get(1).click();
	}
	
	@Test(priority=6)
	public void click_on_book_now_button(){
		PageBase base=new PageBase(driver);
		base.delay();
//		base.clickElement(driver.findElement(By.xpath("//android.widget.FrameLayout[@index='0']/android.widget.ImageButton[@index='0']")));
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_save')]")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_save')]")));
		base.delay();
	}
	
	@Test(priority=7)
	public void remove_coupon_discount(){
		PageBase base=new PageBase(driver);
	    base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_discount_text')]")));
	    old_payment=base.getTextForElement(driver.findElementByXPath("//android.widget.Button[contains(@resource-id,'btn_pay')]"));
		driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_discount_text')]")).click();		
	}


	@Test(priority=8)
	public void click_on_apply_coupon(){
		PageBase base=new PageBase(driver);
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_discount_text')]")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_discount_text')]")));
	}
	
	@Test(priority=9)
	public void enter_coupon_code() {
		PageBase base=new PageBase(driver);
        base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_coupon_code')]")));
		base.iFillInText(driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_coupon_code')]")),"FABAPP25");
	}
	
	@Test(priority=10)
	public void click_apply_button() throws InterruptedException{
		PageBase base=new PageBase(driver);
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_apply')]")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_apply')]")));
		base.delay();

	}
	

	@Test(priority=11)
	public void enter_details() throws InterruptedException{
		PageBase base=new PageBase(driver);
		base.swipingVertical();
		
		
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_full_name')]")));
		//tapOnElement(driver,driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_full_name')]")));
	    base.iFillInText(driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_full_name')]")),"Akshat Jain");
	    driver.hideKeyboard();
		base.iFillInText(driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_email')]")),"akshatj7590@gmail.com");
		driver.hideKeyboard();
		base.iFillInText(driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_mobile_number')]")),"9990915358");
		driver.hideKeyboard();
		base.delay();
		payment=base.getTextForElement(driver.findElementByXPath("//android.widget.Button[contains(@resource-id,'btn_pay')]"));
	}
	
	@Test(priority=12)
	public void click_pay_button(){
		PageBase base=new PageBase(driver);
		base.clickElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_pay')]")));
	}
	
	@Test(priority=13)
	public void click_pay_hotel_tab(){
		PageBase base=new PageBase(driver);
		base.longDelay();
		List<WebElement> elements=driver.findElementsByClassName("android.support.v7.app.a$c");
		elements.get(3).click();
		elements.get(4).click();
}
	
	@Test(priority=14)
	public void click_pay_hotel_button(){
		PageBase base=new PageBase(driver);
		base.delay();
		base.clickElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnPayNow')]")));
	}
	
	@Test(priority=15)
	public void click_on_deny_on_sms_pop_up(){
		PageBase base=new PageBase(driver);
		base.delay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'permission_deny_button')]")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'permission_deny_button')]")));
	}
	
	@Test(priority=16)
	public void close_otp_pop_up(){
		PageBase base=new PageBase(driver);
		base.delay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'ivClose')]")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'ivClose')]")));
	}

	@Test(priority=17)
	public void verify_amount_on_all_tabs(){
		PageBase base=new PageBase(driver);
		List<WebElement> elements=driver.findElementsByClassName("android.support.v7.app.a$c");
		
		String Payment=base.getTextForElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnPayNow')]")));
		String pay=Payment.substring(Payment.length() - 5);
		String pay1=payment.substring(payment.length() - 5);
		Assert.assertTrue(!pay.contains(pay1));
		for(int i=3;i>=0;i--){
			base.delay();
			elements.get(i).click();
			String Payment1=base.getTextForElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnPayNow')]")));
			String pay2=Payment1.substring(Payment1.length() - 5);
            Assert.assertTrue(pay.contains(pay2));
            Assert.assertTrue(!pay.contains(pay1));
		}		
	}
	
	
	@AfterClass
	public void quitdriver()
	{
		driver.quit();
	}
	
	 public String removeFirstChar(String s){
		   return s.substring(1);
		}
}