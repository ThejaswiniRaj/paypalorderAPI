package com.paypal.orders.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.*; 

public class TestBase {
	
	String path="C:\\jansi_javafiles_2\\paypal_practice\\src\\main\\java\\com\\paypal\\properties\\client.properties";
	public Properties prop;
	public static String accesstoken;
	 public static RequestSpecification spec;
	 //initializing as public,so that child class can see the var
	 //initializing as static,so that value of that variable remains same in all child classes and it cannot be duplicated or changed in other child classes.
	//spec in all child class points to base class spec,so that dont declare class level in inherited child classes.
	
	
	
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
		
		 accesstoken=given()
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
	public RequestSpecification  RequestSpecBuilder()
	{
		RequestSpecBuilder builder=new RequestSpecBuilder();
		 builder.setBaseUri(prop.getProperty("baseuri"));
		 builder.setBasePath(prop.getProperty("basepath"));
		 builder.setContentType(ContentType.JSON);
		  spec= builder.build();
		  return spec;
		 
	}
	
	

}
