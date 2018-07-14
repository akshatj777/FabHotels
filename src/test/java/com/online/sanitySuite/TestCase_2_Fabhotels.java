package com.online.sanitySuite;


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import com.steadystate.css.parser.ParseException;



public class TestCase_2_Fabhotels extends TestBase {

	public static String RoomPrice;
	public static String RoomPrice1;
	public static String HotelCutPrices;
	public static String HotelCutPrices1;
	public static String SavePrice;
	public static String SavePrice1;
	public static String PromoCutPrices;
	public static String Coupon_Discount;
	public static String PromoTotalPrices;
	 public static String Tax_Promo;
	 public static String Discount_price;
	 public static String Payalble_Amount;
	 public static String Saving_Amount;
	 public static String Saved_Prices;
	 
	
 @Test(priority=1)	
	 public void setup() throws Throwable {
	        driver.navigate().to(TestBase.config.getProperty("BaseUrl"));
	        driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        }
	


 
 @Test(priority=2)
 public void enter_city() throws ParseException, java.text.ParseException{
	 WebPageBase webpage=new WebPageBase(driver);
	 webpage.delay();
	 webpage.clickElement(driver.findElement(By.xpath("//*[@id='autocomplete-location']")));
	 webpage.iFillInText(driver.findElement(By.xpath("//*[@id='autocomplete-location']")),"Bangalore, Karnataka, India");
	 webpage.delay();
	 webpage.clickElement(driver.findElement(By.cssSelector("svg.icon.search-icon")));
	 webpage.delay();

}

 @Test(priority=3)
 public void select_date(){
	 WebPageBase webpage=new WebPageBase(driver);
	// webpage.iWillWaitToSee(driver.findElement(By.cssSelector("div.select-dates.select-dates-checkin.select_dates_checkin>div>svg")));
	 webpage.clickElement(driver.findElement(By.cssSelector("div.select-dates.select-dates-checkin")));
	 webpage.delay();
	 webpage.clickElement(driver.findElement(By.cssSelector("td.today.day")));
	 webpage.delay();
	 String date=webpage.getTextForElement(driver.findElement(By.xpath("//td[contains(@class,'today selected range-start day')]")));
	 int new_date=Integer.parseInt(date)+1;
	 webpage.delay();
	 webpage.iWillWaitToSee(driver.findElement(By.xpath("//*[@class='day' and text()='"+new_date+"' and not(contains(@class,'disabled'))]")));
	 webpage.clickElement(driver.findElement(By.xpath("//*[@class='day' and text()='"+new_date+"' and not(contains(@class,'disabled'))]")));
 }
 
 @Test(priority=4)
 public void verify_date_format() throws ParseException, java.text.ParseException{
	 WebPageBase webpage=new WebPageBase(driver);
	 webpage.delay();
	 webpage.clickElement(driver.findElement(By.xpath("//span[@data-val='1']")));
	 webpage.delay();
	 String date=driver.findElement(By.cssSelector("div.searchCheckInBox.date-selected")).getText();
	 webpage.validateDateFormat("dd MMM yyyy",date);
	 String date1=driver.findElement(By.cssSelector("div.searchCheckOutBox.date-selected")).getText();
	 webpage.validateDateFormat("dd MMM yyyy",date1);

}
 

 
 @Test(priority=5)
 public void click_search_button() throws ParseException, java.text.ParseException{
	 WebPageBase webpage=new WebPageBase(driver);
	 webpage.iWillWaitToSee(driver.findElement(By.id("listingPageBtn")));
	 webpage.clickElement(driver.findElement(By.id("listingPageBtn")));
	 webpage.delay();
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
	 webpage.iWillWaitToSee(driver.findElement(By.xpath("//div[contains(@class,'hotel_info_container')]/div/following-sibling::button[contains(@class,'select_room_button')]")));
	 webpage.clickElement(driver.findElement(By.xpath("//div[contains(@class,'hotel_info_container')]/div/following-sibling::button[contains(@class,'select_room_button')]")));
	 driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
	}
 
 @Test(priority=8)
 public void select_guests() throws ParseException, java.text.ParseException{
	 WebPageBase webpage=new WebPageBase(driver);
	 webpage.longDelay();
	
		 webpage.iWillWaitToSee(driver.findElement(By.cssSelector("button.select_room_type")));
	     webpage.clickElement(driver.findElement(By.cssSelector("button.select_room_type")));
		
	 webpage.delay();
	 webpage.clickElement(driver.findElement(By.cssSelector("span.rooms_select_value")));
	 webpage.delay();
	 webpage.iWillWaitToSee(driver.findElement(By.xpath("//span[@class='room_select_no' and @data-value='2']")));
	 webpage.clickElement(driver.findElement(By.xpath("//span[@class='room_select_no' and @data-value='2']")));
	 webpage.delay();
	 HotelCutPrices=webpage.getTextForElement(driver.findElement(By.cssSelector("span.hotel_cut_price"))).replaceAll(",", " ");
	 HotelCutPrices1=HotelCutPrices.replaceAll("\\s","");
	 RoomPrice=webpage.getTextForElement(driver.findElement(By.cssSelector("div.room-type-section-active>div>div.room-type-price-wrap>div>strong>span"))).replaceAll(",", " ");
	 RoomPrice1=RoomPrice.replaceAll("\\s","");
	 SavePrice=webpage.getTextForElement(driver.findElement(By.cssSelector("span.save-rupees.save_rupees"))).substring(10).replaceAll(",", " ");
	 SavePrice1=SavePrice.replaceAll("\\s","");
	}
 
 @Test(priority=9)
 public void click_pay_button() throws ParseException{
	 WebPageBase webpage=new WebPageBase(driver);
	 webpage.iWillWaitToSee(driver.findElement(By.cssSelector("button.btn.proceed_to_pay_button")));
	 webpage.clickElement(driver.findElement(By.cssSelector("button.btn.proceed_to_pay_button")));
   }
 
 @Test(priority=10)
 public void verify_prices() throws ParseException, java.text.ParseException{
	 WebPageBase webpage=new WebPageBase(driver);
	 PromoCutPrices=removeFirstChar(webpage.getTextForElement(driver.findElement(By.cssSelector("span.promo-rate-cut"))));
	 
	 
	 PromoTotalPrices=removeFirstChar(webpage.getTextForElement(driver.findElement(By.cssSelector("span.sub-total-amount"))));
	 Coupon_Discount=webpage.getTextForElement(driver.findElement(By.cssSelector("span.discount_amount")));
	
	 
	 Discount_price=webpage.getTextForElement(driver.findElement(By.cssSelector("span.discounted_price")));
	 Tax_Promo=webpage.getTextForElement(driver.findElement(By.cssSelector("span.tax-amount.tax_amount")));
	 Payalble_Amount=webpage.getTextForElement(driver.findElement(By.cssSelector("div.review_booking_total_amount > span")));
	 Saving_Amount=webpage.getTextForElement(driver.findElement(By.cssSelector("span.total_saving")));
	 Assert.assertEquals(PromoCutPrices, HotelCutPrices1);
	 Assert.assertEquals(SavePrice1, Saving_Amount);
	 Assert.assertEquals(RoomPrice1, Discount_price);
	 Assert.assertEquals(Integer.parseInt(PromoTotalPrices)-Integer.parseInt(Coupon_Discount), Discount_price);
	 int value=Integer.parseInt(PromoTotalPrices)-Integer.parseInt(Coupon_Discount)+Integer.parseInt(Tax_Promo);
	 Assert.assertEquals(String.valueOf(value).trim() , Payalble_Amount.trim());
	
 }
 
 
	@AfterClass
	public void tearDown() {
		quitDriver();
	}
	
	
 public String removeFirstChar(String s){
	   return s.substring(1);
	}
}
