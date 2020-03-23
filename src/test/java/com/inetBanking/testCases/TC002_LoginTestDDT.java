package com.inetBanking.testCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObject.LoginPage;
import com.inetBanking.utilities.XlUtils;

import junit.framework.Assert;

public class TC002_LoginTestDDT  extends BaseClass{
	
	@Test(dataProvider="exceldata")
	public void loginDDT(String uname,String password)
	{
		logger= LogManager.getLogger(TC001_LoginTest.class);
		logger.info("****TC002_LoginTestDDT started****");
		driver.get(baseURL);
		driver.manage().window().maximize();
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(uname);
		logger.info("****Username entered****");
		lp.setPassword(password);
		logger.info("****Password entered****");
		lp.clickSubmit();
		logger.info("****Click button clicked****");
				
		if(isAlertPresent()==true) 
		{
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		Assert.assertTrue(false);
		logger.warn("Login Failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("****Login Successfull****");
			lp.logout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}
	
	public boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert(); 
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	@DataProvider(name="exceldata")
	String[][] getData() throws IOException
	{
		//C:\Users\RITESH\seleniumframework\inetBankingV1\src\test\java\com\inetBanking\testData\LoginData.xlsx
		
		String path = System.getProperty("user.dir")+ "/src/test/java/com/inetBanking/testData/LoginData.xlsx";
		int rowcount = XlUtils.getRowCount(path, "Sheet1");
		int cellcount = XlUtils.getCellCount(path, "Sheet1", 1);
		
		String loginData[][]= new String[rowcount][cellcount];
		
		for (int i =1 ; i<= rowcount ; i++)
		{
			for(int j=0; j<cellcount; j++)
			{
				loginData[i-1][j]= XlUtils.getCellData(path, "Sheet1",i, j);
			}
		}
		return loginData;
	}
}
