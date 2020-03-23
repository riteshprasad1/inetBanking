package com.inetBanking.testCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;

import com.inetBanking.pageObject.LoginPage;

import junit.framework.Assert;

public class TC001_LoginTest extends BaseClass 
{
	@Test
	public void loginTest() throws IOException 
	{	
		logger= LogManager.getLogger(TC001_LoginTest.class);
		logger.info("****TC001_LoginTeststarted****");
		//driver.get(baseURL);
		driver.manage().window().maximize();
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("****Username entered****");
		lp.setPassword(password);
		logger.info("****password entered****");
		lp.clickSubmit();
		logger.info("****Click button clicked*****");
		if(driver.getTitle().equalsIgnoreCase("Guru99 Bank Manager HomePage11111"))
		{
		Assert.assertTrue(true);
		logger.info("****Title Verified*****");
		}
		else
		{	
			captureScreen(driver, "loginTest");
			logger.info("***Wrong title***");
			Assert.assertTrue(false);
			
		}
	
}
}
