package com.paypal.orders.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import com.paypal.createorder_resourcebuilder.post_obj;
import com.paypal.createorder_resourcebuilder.resourcebuilder;
import com.paypal.orders.base.TestBase;

import static com.jayway.restassured.RestAssured.*;

public class createorder extends TestBase{
	
	// RequestSpecification spec;
	  Response createorder_response;
	
	public createorder()
	{
		super();
	}
	
	@BeforeMethod
	public void initialization()
	{
		
		 accesstoken=accesstoken();
		  spec  =RequestSpecBuilder();
		 
		 
		
		
	}
	
	 
	
	public   Response createorders()
	{
		resourcebuilder resource=new resourcebuilder();
		post_obj pojo=resource.resourcebuild();
	
		
		
		 createorder_response=given()
		//.baseUri(prop.getProperty("baseuri"))
		//.basePath(prop.getProperty("basepath"))
		//.contentType(ContentType.JSON)
				 .spec(spec)
		.auth()
		.oauth2(accesstoken)
		.when()
		.body(pojo)
		.post("/checkout/orders");
		 return createorder_response;
	}
	
		
		public String extractid(Response response)
		{
		
			String id=response.then().extract().path("id");
			
			return id;
			
		}
		@Test
		public void createorderassertion()
		{
			Response res=createorders();
			String id=extractid(res);
			System.out.println("createorderid is:"+id);
			System.out.println("Response is:"+res.asString());
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
	
	
}