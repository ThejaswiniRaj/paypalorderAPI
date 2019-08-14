package com.paypal.orders.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import com.paypal.orders.base.TestBase;
import com.paypal.resourcebuilder.post_obj;
import com.paypal.resourcebuilder.resourcebuilder;
import static com.jayway.restassured.RestAssured.*;

public class createorder extends TestBase{
	String accesstoken;
	RequestSpecification spec;
	Response createorder_response;
	
	public createorder()
	{
		super();
	}
	
	@BeforeMethod
	public void initialization()
	{
		
		 accesstoken=accesstoken();
		 RequestSpecBuilder builder=new RequestSpecBuilder();
		 builder.setBaseUri(prop.getProperty("baseuri"));
		 builder.setBasePath(prop.getProperty("basepath"));
		 builder.setContentType(ContentType.JSON);
		  spec= builder.build();
		 
		 
		
		
	}
	
	 
	
	public Response createorders()
	{
		resourcebuilder resource=new resourcebuilder();
		post_obj pojo=resource.resourcebuild();
	
		
		
		 createorder_response=given()
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