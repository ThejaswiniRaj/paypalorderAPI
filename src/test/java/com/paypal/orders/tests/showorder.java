package com.paypal.orders.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.paypal.orders.base.TestBase;
import static com.jayway.restassured.RestAssured.*;

public class showorder extends TestBase {
	
	String orderid;
	RequestSpecification spec;
	 //String accesstoken;
	
	
	
	public showorder()
	{
		super();
	}
	
	
	@BeforeMethod
	public void showinit()
	{
		accesstoken=accesstoken();
		
		createorder co=new createorder();
		Response createorder_response=co.createorders();
		 orderid=co.extractid(createorder_response);
		/* RequestSpecBuilder builder=new RequestSpecBuilder();
		 builder.setBaseUri(prop.getProperty("baseuri"));
		 builder.setBasePath(prop.getProperty("basepath"));
		 builder.setContentType(ContentType.JSON);
		  spec= builder.build();*/
		
		
		  
		
	}
	
	@Test
	public void showorderresponse()
	{
		given()
		.baseUri(prop.getProperty("baseuri"))
		.basePath(prop.getProperty("basepath"))
		.contentType(ContentType.JSON)
		.auth()
		.oauth2(accesstoken)
		.when()
		.get("/checkout/orders/"+orderid)
		.then()
		.log()
		.all();
		
		
		
	}

}
