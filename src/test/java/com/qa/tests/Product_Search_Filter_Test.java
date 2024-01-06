package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.basesetup.BaseTest;
import com.qa.constants.Constant;

public class Product_Search_Filter_Test extends BaseTest {
	@Test
	public void GetUrl() {
		product.Get_Url();
		softAssert.assertEquals(product.PageUrl, Constant.PAGE_URL);
	}

	@Test
	public void GetTitle() {
		product.Get_Title();
		softAssert.assertEquals(product.PageTitile, Constant.PAGE_TITLE);
	}

	@Test
	public void Verify_Product_Filter() throws InterruptedException {
		product.Search_And_Filter();
		System.out.println("done");

	}

}
