package com.online.sanitySuite;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.steadystate.css.parser.ParseException;

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
					capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
				    capabilities.setCapability("appPackage", config.getProperty("app-package"));
					capabilities.setCapability("appActivity", config.getProperty("app-activity"));
		            driver= new AndroidDriver( new URL("http://127.0.0.1:"+config.getProperty("AppiumPort")+"/wd/hub"), capabilities) ;
					System.out.println("Driver"+driver);
					System.out.println("App Launched ");
					Thread.sleep(10000);
		
	}
	
	
	@Test(priority=2)
	public void click_on_search_city(){
		driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'rv_fabCities')]/android.widget.FrameLayout[@index='1']")).click();
     }
	
	@Test(priority=3)
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
	
	@Test(priority=4)
	public void click_on_first_hotel_link(){
		PageBase base=new PageBase(driver);
		base.delay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'rv_hotel_listing')]/android.widget.FrameLayout[@index='1']")));
		base.clickElement(driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'rv_hotel_listing')]/android.widget.FrameLayout[@index='1']")));
	}
	
	@Test(priority=5)
	public void click_on_select_room_button(){
		PageBase base=new PageBase(driver);
		base.delay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_bookNow')]")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_bookNow')]")));
	}
	
	@Test(priority=6)
	public void click_on_first_pagination_button(){
		PageBase base=new PageBase(driver);
		base.delay();
		List<WebElement> element=driver.findElements(By.className("android.widget.RadioButton"));
		element.get(1).click();
	}
	
	@Test(priority=7)
	public void click_on_book_now_button(){
		PageBase base=new PageBase(driver);
		base.delay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_save')]")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_save')]")));
	}
	
	@Test(priority=8)
	public void remove_coupon_discount(){
		PageBase base=new PageBase(driver);
		base.delay();
		base.delay();
		
		base.clickSingleElementFromList(By.className("android.widget.TextView"),"STAYFAB applied!");
	
//		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_discount_text')]")));
//		base.clickElement(driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_discount_text')]")));
	}
	
	@Test(priority=9)
	public void click_on_apply_coupon(){
		PageBase base=new PageBase(driver);
		base.longDelay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_discount_text') and contains(text(),'(Apply Coupon)')]")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_discount_text') and contains(text(),'(Apply Coupon)')]")));
	}
	
	@Test(priority=10)
	public void enter_coupon_code(){
		PageBase base=new PageBase(driver);
		base.longDelay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_coupon_code') and contains(text(),'Enter Coupon Code')]")));
		base.iFillInText(driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_coupon_code') and contains(text(),'Enter Coupon Code')]")),"FABAPP25");
	}
	
	@Test(priority=11)
	public void click_apply_button() throws InterruptedException{
		PageBase base=new PageBase(driver);
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_apply') and contains(text(),'APPLY')]")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_apply') and contains(text(),'APPLY')]")));
		base.longDelay();
		base.swipingVertical();
	}
	

	@Test(priority=12)
	public void enter_details(){
		PageBase base=new PageBase(driver);
		base.delay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_full_name')")));
		base.iFillInText(driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_full_name')")),"Akshat Jain");
		base.iFillInText(driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_email')")),"akshatj7590@gmail.com");
		base.iFillInText(driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_mobile_number')")),"9990915358");
		base.delay();
	}
	
	@Test(priority=13)
	public void click_pay_button(){
		PageBase base=new PageBase(driver);
		base.clickElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnPayNow')]")));
	}
	
	@Test(priority=14)
	public void click_pay_hotel_tab(){
		PageBase base=new PageBase(driver);
		base.longDelay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.HorizontalScrollView[contains(@resource-id,'tabLayout')]/android.support.v7.app.a$c[@index='3']")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.HorizontalScrollView[contains(@resource-id,'tabLayout')]/android.support.v7.app.a$c[@index='3']")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.HorizontalScrollView[contains(@resource-id,'tabLayout')]/android.support.v7.app.a$c[@index='4']")));
}
	
	@Test(priority=15)
	public void click_pay_hotel_button(){
		PageBase base=new PageBase(driver);
		base.delay();
		base.clickElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnPayNow')]")));
	}
	
	@Test(priority=16)
	public void click_on_deny_on_sms_pop_up(){
		PageBase base=new PageBase(driver);
		base.delay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'permission_deny_button')]")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'permission_deny_button')]")));
	}
	
	@Test(priority=17)
	public void close_otp_pop_up(){
		PageBase base=new PageBase(driver);
		base.delay();
		base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'ivClose')]")));
		base.clickElement(driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'ivClose')]")));
	}
	
	@Test(priority=18)
	public void verify_amount_on_all_tabs(){
		PageBase base=new PageBase(driver);
		for(int i=4;i>=0;i--){
			base.delay();
			base.iWillWaitToSee(driver.findElement(By.xpath("//android.widget.HorizontalScrollView[contains(@resource-id,'tabLayout')]/android.support.v7.app.a$c[@index='"+i+"']")));
			base.clickElement(driver.findElement(By.xpath("//android.widget.HorizontalScrollView[contains(@resource-id,'tabLayout')]/android.support.v7.app.a$c[@index='"+i+"']")));
			String payment=base.getTextForElement(driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnPayNow')]")));
			AssertJUnit.assertTrue(payment.contains("1,549"));
			Assert.assertNotEquals(payment, "1,204");
		}
		
	}
	
	@Test(priority=19)
	public void click_on_back_button(){
		PageBase base=new PageBase(driver);
		base.clickElement(driver.findElement(By.xpath("//android.widget.FrameLayout[@index='0']/android.widget.ImageButton[@index='0']")));
		base.longDelay();
		}
}