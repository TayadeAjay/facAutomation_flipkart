package com.qa.basesetup;

import java.util.Properties;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.factory.DriverFactory;
import com.qa.pages.ProductInfoPage;


public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
protected ProductInfoPage product;	
	
	protected SoftAssert softAssert;
	
	@BeforeTest
	public void setup()
	{
		df=new DriverFactory();
		prop=df.initProp();
		driver=df.initDriver(prop);
		product=new ProductInfoPage(driver);
		softAssert =new SoftAssert();
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
}
