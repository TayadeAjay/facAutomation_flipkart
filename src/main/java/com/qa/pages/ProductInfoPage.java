package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.qa.constants.Constant;
import com.qa.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By SearchBox = By.xpath("//input[@type='text']");
	private By Brands = By.xpath("(//div[contains(text(),'Brand')])[1]");
	private By HpBrand = By.xpath("//div[@title='HP']//div[@class='_24_Dny']");
	

	public ProductInfoPage(WebDriver driver1) {
		this.driver = driver1;
		eleUtil = new ElementUtil(driver);
	}

	public void Search_And_Filter() throws InterruptedException {

		driver.findElement(SearchBox).sendKeys(Constant.BRANDS_NAME, Keys.ENTER);

		driver.findElement(Brands).click();
		driver.findElement(HpBrand).click();
		

	}

}