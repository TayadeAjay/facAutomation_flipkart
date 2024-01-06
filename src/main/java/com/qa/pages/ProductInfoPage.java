package com.qa.pages;

import org.apache.poi.hssf.record.FeatHdrRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.qa.constants.Constant;

public class ProductInfoPage {

	private WebDriver driver;

	private By SearchBox = By.xpath("//input[@type='text']");
	private By Brands = By.xpath("(//div[contains(text(),'Brand')])[1]");
	private By HpBrand = By.xpath("//div[@title='HP']//div[@class='_24_Dny']");

	public ProductInfoPage(WebDriver driver1) {
		this.driver = driver1;

	}

	public void Get_Title() {
		System.out.println("The Page Title is : " + driver.getTitle());
	}

	public void Get_Url() {
		System.out.println("The Page Url is : " + driver.getCurrentUrl());
	}

	public void Search_And_Filter() throws InterruptedException {

		driver.findElement(SearchBox).sendKeys(Constant.BRANDS_NAME, Keys.ENTER);

		driver.findElement(Brands).click();
		driver.findElement(HpBrand).click();

	}

}