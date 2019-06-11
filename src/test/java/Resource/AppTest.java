package Resource;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest 
{
	WebDriver driver;
	Logger log;
	
	@BeforeTest
	public void setup()
	{
		log=Logger.getLogger("Apptest");
		PropertyConfigurator.configure("D:\\Viswa\\Maven_Project\\src\\main\\resources\\Log4j.properties");
		System.setProperty("www.gecko.driver", "D:\\geckodriver.exe");
		log.info("Set the location of the driver path");
		driver=new FirefoxDriver();
		log.info("Launch firefox browser");
		
		driver.manage().window().maximize();
		log.info("Maximize the browser window");
		driver.manage().deleteAllCookies();
		log.info("Delete All cookies present in firefox browser");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		log.info("Please wait 20 seconds,the page will be loaded");
		driver.get("http://primusbank.qedgetech.com");
		log.info("Launch the Primus bank Aplication");
		
	}
	
	
	
	@Test
	public void login()
	{
		driver.findElement(By.id("txtuId")).sendKeys("Admin");
		log.info("Enter the correct username");
		driver.findElement(By.id("txtPword")).sendKeys("Admin");
		log.info("Enter correct password");
		driver.findElement(By.id("login")).click();
		log.info("Click on Submit button");
	}
	
	@Test
	public void titletest()
	{
		String title;
		title=driver.getTitle();
		log.info("Capture the title of the page"+title);
		Assert.assertEquals(title, "Primus BANK");
		log.info("Title matched,test pass");
		
	}
	
	@Test
	public void logotest()
	{
		boolean res=driver.findElement(By.xpath("//img[@src='images/TopFrame_01.jpg']")).isDisplayed();
		log.info("logo displayed in homepage");
		Assert.assertTrue(res);
		log.info("logo displayed,success");
		
	}
	
	
	
	@AfterTest
	public void close()
	{
		driver.close();
	}
}
