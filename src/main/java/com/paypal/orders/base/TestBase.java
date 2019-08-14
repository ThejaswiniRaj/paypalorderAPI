package com.paypal.orders.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import static com.jayway.restassured.RestAssured.*; 

public class TestBase {
	
	String path="C:\\jansi_javafiles_2\\paypal_practice\\src\\main\\java\\com\\paypal\\properties\\client.properties";
	public Properties prop;
	
	
	
	
	public TestBase() 
	{
		
		try
		{
		FileInputStream ip=new FileInputStream(path);
		 prop=new Properties();
		prop.load(ip);
		
		}
		
		catch( FileNotFoundException e)
		{
			e.printStackTrace();
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		}
	
	
	
	public String accesstoken()
	{
		
		String accesstoken=given()
		.param("grant_type", "client_credentials")
		.auth()
		.preemptive()
		.basic(prop.getProperty("clientid"), prop.getProperty("clientsecret"))
		.when()
		.post("https://api.sandbox.paypal.com/v1/oauth2/token")
		.then()
		.extract()
		.path("access_token");
		
		//System.out.println("Access token is :"+accesstoken);
		return accesstoken;
		
		
		
		
	}
	
	

}
