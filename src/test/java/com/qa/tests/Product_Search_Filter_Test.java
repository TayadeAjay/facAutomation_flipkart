package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.basesetup.BaseTest;

public class Product_Search_Filter_Test extends BaseTest{
@Test
	public void Verify_Product_Filter() throws InterruptedException {
		product.Search_And_Filter();
		System.out.println("done");
		
	}
	
	
}
