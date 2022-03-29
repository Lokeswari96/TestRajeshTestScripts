package com.crm.VtigerRAJ.CRMTestScript;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.VtigerRAJ.ObjectRepository.CampaiginsPage;
import com.crm.VtigerRAJ.ObjectRepository.CampaignInformationPage;
import com.crm.VtigerRAJ.ObjectRepository.CreateNewComapignPage;
import com.crm.VtigerRAJ.ObjectRepository.CreateNewProductPage;
import com.crm.VtigerRAJ.ObjectRepository.HomePage;
import com.crm.VtigerRAJ.ObjectRepository.MoreOptionsPage;
import com.crm.VtigerRAJ.ObjectRepository.ProductInformationPage;
import com.crm.VtigerRAJ.ObjectRepository.ProductPage;
import com.crm.VtigerRAJ.ObjectRepository.ProductwindowPage;
import com.crm.VtigerRAJ.genericUtility.BaseClass;

@Listeners(com.crm.VtigerRAJ.genericUtility.ListenersImplementationClass.class)
public class CreateCompaignChooseProjectTest extends BaseClass {
	
	@Test(groups = {"smoke","IntegrationTest"})
	public void createCampaignChooseProduct() throws Throwable, IOException {
	    //fetching CompaignTestData from excelSheet
		String expectedCompaignName=eLib.getDataFromExcel("CreateCompaign", 1, 3);
		expectedCompaignName=expectedCompaignName+jLib.getrandomNum();
		System.out.println(expectedCompaignName);
		
		//fetching ProductNameTestData from excelSheet
		 String expectedProductName=eLib.getDataFromExcel("ProductName", 1, 3);
		 expectedProductName=expectedProductName+jLib.getrandomNum();
		 System.out.println(expectedProductName);

	        //click On productLinkText
	        HomePage homePage=new HomePage(driver);
	        homePage.products();
	        
	        //click On "+" AddImageIcon
	        ProductPage productPage=new ProductPage(driver);
	        productPage.clickAddImageIcon();
	        
	        //createNewProduct with mandatoryFields
			CreateNewProductPage newProductPage=new CreateNewProductPage(driver);
			newProductPage.createNewOrganization(expectedProductName);
		
			//verification for Product
			ProductInformationPage informationProduct=new ProductInformationPage(driver);
			String actualProduct=informationProduct.getInformationMsg();
			
			Assert.assertEquals(actualProduct.contains(expectedProductName), true);
	        
	    //mouseOver action to moreLinkText and Click On CamPaignLinkText
	    homePage.MoreOption(driver);
	    MoreOptionsPage moreOptions=new MoreOptionsPage(driver);
	    moreOptions.compaign();
	    
	    //click on "+" AddImaeIcon
	    CampaiginsPage compaignPage=new CampaiginsPage(driver);
	    compaignPage.clickOnCreateCompaignAddImaeIcon();
	
	    //create new campaign with MandatoryFields and Choose product
	    CreateNewComapignPage newCompaignName=new CreateNewComapignPage(driver);
	    newCompaignName.compaignName(expectedCompaignName, driver, actualProduct);
	   
	   //search for productName into productWindow
	    ProductwindowPage productWindow=new ProductwindowPage(driver);
	    productWindow.searchProduct(driver, expectedProductName);
	    newCompaignName.saveCompaign(driver, actualProduct);
	    
	    //verification for Campaign
	    CampaignInformationPage campaignInformation=new CampaignInformationPage(driver);
	    String ActualCampaignName = campaignInformation.campaignInformationText();
	    
		Assert.assertEquals(ActualCampaignName.contains(expectedCompaignName), true);

    	
	}
}
