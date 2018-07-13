package com.online.sanitySuite;


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.steadystate.css.parser.ParseException;



public class TestCase_2_Fabhotels extends TestBase {

	public static String RoomPrice;
	
 @Test(priority=1)	
	 public void setup() throws Throwable {
	        driver.navigate().to(TestBase.config.getProperty("BaseUrl"));
	        driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        }
	
 @Test(priority=2)
 public void select_date(){
	 WebPageBase webpage=new WebPageBase(driver);
	// webpage.iWillWaitToSee(driver.findElement(By.cssSelector("div.select-dates.select-dates-checkin.select_dates_checkin>div>svg")));
	 webpage.longDelay();
	 webpage.clickElement(driver.findElement(By.cssSelector("div.select-dates.select-dates-checkin")));
	 webpage.delay();
	 webpage.clickElement(driver.findElement(By.cssSelector("td.today.day")));
	 webpage.delay();
	 webpage.clickElement(driver.findElement(By.xpath("//td[contains(@class,'today selected range-start day')]/following-sibling::td")));
 }
 
 @Test(priority=3)
 public void verify_date_format() throws ParseException, java.text.ParseException{
	 WebPageBase webpage=new WebPageBase(driver);
	 webpage.longDelay();
	 webpage.clickElement(driver.findElement(By.xpath("//span[@data-val='1']")));
	 webpage.delay();
	 String date=driver.findElement(By.cssSelector("div.searchCheckInBox.date-selected")).getText();
	 webpage.validateDateFormat("dd MMM yyyy",date);
	 String date1=driver.findElement(By.cssSelector("div.searchCheckOutBox.date-selected")).getText();
	 webpage.validateDateFormat("dd MMM yyyy",date1);

}
 
 @Test(priority=4)
 public void enter_city() throws ParseException, java.text.ParseException{
	 WebPageBase webpage=new WebPageBase(driver);
	 webpage.delay();
	 webpage.iFillInText(driver.findElement(By.cssSelector("input.search_bx_srch.error")),"Bangalore, Karnataka, India");
	 webpage.delay();
	 webpage.clickElement(driver.findElement(By.cssSelector("svg.icon.search-icon")));
	 webpage.delay();

}
 
 @Test(priority=5)
 public void click_search_button() throws ParseException, java.text.ParseException{
	 WebPageBase webpage=new WebPageBase(driver);
	 webpage.iWillWaitToSee(driver.findElement(By.id("listingPageBtn")));
	 webpage.clickElement(driver.findElement(By.id("listingPageBtn")));
	 webpage.longDelay();
	}
 
 @Test(priority=6)
 public void click_first_hotel_link() throws ParseException, java.text.ParseException{
	 WebPageBase webpage=new WebPageBase(driver);
	 List <WebElement> element = driver.findElements(By.cssSelector("div.table-cell.detail-wrap>h3"));
	    for(WebElement ele: element) {
	    	ele.click();
	    	break;
	    	}
	    webpage.switchToNewWindow();
	    webpage.longDelay();
	}
 
 @Test(priority=7)
 public void click_select_room() throws ParseException, java.text.ParseException{
	 WebPageBase webpage=new WebPageBase(driver);
	 webpage.iWillWaitToSee(driver.findElement(By.xpath("//button[contains(@class,'select_room_type') and not(contains(@class,'disable-add-rooms'))]")));
	 webpage.clickElement(driver.findElement(By.xpath("//button[contains(@class,'select_room_type') and not(contains(@class,'disable-add-rooms'))]")));
	 driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
	}
 
 @Test(priority=8)
 public void select_guests() throws ParseException, java.text.ParseException{
	 WebPageBase webpage=new WebPageBase(driver);
	 webpage.iWillWaitToSee(driver.findElement(By.cssSelector("span.rooms_select_value")));
	 webpage.clickElement(driver.findElement(By.cssSelector("span.rooms_select_value")));
	 webpage.delay();
	 webpage.iWillWaitToSee(driver.findElement(By.xpath("//span[@class='room_select_no' and @data-value='2']")));
	 webpage.clickElement(driver.findElement(By.xpath("//span[@class='room_select_no' and @data-value='2']")));
	 RoomPrice=webpage.getTextForElement(driver.findElement(By.cssSelector("div.room-type-section-active>div>div.room-type-price-wrap>div>strong>span")));
	}
 
 @Test(priority=9)
 public void click_pay_button() throws ParseException, java.text.ParseException{
	 WebPageBase webpage=new WebPageBase(driver);
	 webpage.iWillWaitToSee(driver.findElement(By.cssSelector("button.btn.proceed_to_pay_button")));
	 webpage.clickElement(driver.findElement(By.cssSelector("button.btn.proceed_to_pay_button")));
   }
 
 
}
