package com.paypal.orders.tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.internal.mapper.ObjectMapperType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.paypal.orders.base.TestBase;
import com.paypal.showorder_resourcebuilder.links;
import com.paypal.showorder_resourcebuilder.purchase_units;
import com.paypal.showorder_resourcebuilder.showorder_obj;

import junit.framework.Assert;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.List;

public class showorder extends TestBase {
	
	String orderid;
	//RequestSpecification spec;
	//dont initialize spec as class variable in child class bcz,this value is fetched from base class
	Response showorder_response;
	
	
	
	
	public showorder()
	{
		super();
	}
	
	
	@BeforeMethod
	public void showinit()
	{
		accesstoken=accesstoken();
		 spec  =RequestSpecBuilder();
		
		createorder co=new createorder();
		Response createorder_response=co.createorders();
		 orderid=co.extractid(createorder_response);
		 
		
		  
		
	}
	
	
	public Response showorderresponse()
	{
		 showorder_response=given()
		//.baseUri(prop.getProperty("baseuri"))
		//.basePath(prop.getProperty("basepath"))
		//.contentType(ContentType.JSON)
		 .spec(spec)
		.auth()
		.oauth2(accesstoken)
		.when()
		.get("/checkout/orders/"+orderid);
		return  showorder_response;
		//System.out.println("Show order response is :" +showorder_response.asString());
		
		
	}
	@Test
	public void parsingthrumappingtoclass()
	{
		SoftAssert softAssert = new SoftAssert ();

		Response showorder_response=showorderresponse();
		showorder_obj showorderpojo=showorder_response.as(showorder_obj.class, ObjectMapperType.GSON);
		System.out.println("fetching value of simple member variables");
		System.out.println("value of intent is:"+showorderpojo.getIntent());
		String intent=showorderpojo.getIntent();
		softAssert.assertEquals(intent, "CAPTURE","intentasssert failed");
		softAssert.assertEquals(showorderpojo.getStatus(), "CREATED","statusasssert failed");
		
		System.out.println("fetching value of purchaseunitsarraylist");
		
		List<purchase_units>lst=showorderpojo.getPurchase_units();
		for(purchase_units e :lst)
		{
		System.out.println("value of ref id is "+e.getReference_id());	
		System.out.println("value of currency code is "+e.getAmount().getCurrency_code());
		System.out.println("fetching value of linksarraylist");
		}
		
		List<links> lstlnk= showorderpojo.getLinks();
		for(links e1:lstlnk)
		{
			System.out.println("all values of href"+e1.getHref());
			if(e1.getRel().equalsIgnoreCase("self"))
			{
				System.out.println("fetch values of particular index in arraylist");
				System.out.println("value of href where rel=self" +e1.getHref());
				softAssert.assertEquals(e1.getRel(), "self","relasssert failed");
				
			}
		}
		softAssert.assertAll();
		
		}
	
	@Test
	public void parsingthrugroovyscript()
	{
		//hard assert
		
		Response showorder_response=showorderresponse();
		showorder_response
		.then()
		.body("intent",equalToIgnoringCase("capture"))
		.body("purchase_units[0].amount.value", equalTo("100.00"))//when we know the index
		.body("links.find{it.rel='self'}.method",equalToIgnoringCase("GEt"))//selecting index based on some condition and checking the value in that particular index
		.body("links.findAll{it.method='GET'}",hasItem(hasValue("approve") ));
		//get all the variables inside arraylist of index where method=get and then checks if it has an item whose value is approve
		
		//soft assert
		/*Response showorder_response=showorderresponse();
		.then()
		.body("intent",equalToIgnoringCase("capture"),
		"purchase_units[0].amount.value", equalTo("1001.00"),
		"links.find{it.rel='self'}.method",equalToIgnoringCase("GEt"),
		"links.findAll{it.method='GET'}",hasItem(hasValue("approves") ));*/
		//soft assert checks for 2 below assert stmts even if above assert fails and displays all failures in console
		//hard  assert stops checking for below assert stmats  if above assert fails and only the first failure stmt.
		
		}
	@Test
	public void parsingthruJSONobject()
	{   //get response
		Response showorder_response=showorderresponse();
		//convert it to string
		String string_response=showorder_response.asString();
		//convert  string to jsonobject through org.json dependency jar
		JSONObject jsonobj=new JSONObject(string_response);
		System.out.println("parsing simple object through json object");
		System.out.println("value of id thru jsonobject parsing is " +jsonobj.get("id"));
		System.out.println("value of status thru jsonobject parsing is " +jsonobj.get("status"));
		System.out.println("parsing complex objects through json object");
		JSONArray  linkarray=jsonobj.getJSONArray("links");
		//Assert.assertEquals(jsonobj.getString("status"), "CREATED");
		
		   for( Object e:linkarray)
		   {
			   JSONObject jobj = (JSONObject)e;
			   System.out.println("value of rel through jsonobject is " +jobj.getString("rel") );//without index
			   
			   if(jobj.getString("method").equalsIgnoreCase("post"))
			   {
				   System.out.println("value of rel with index ref through jsonobject is " +jobj.getString("rel") );//with index ref
			   }
		   }
		   JSONArray purchasearray= jsonobj.getJSONArray("purchase_units");
		   for(Object e:purchasearray)
		   { 
			   JSONObject jobj=(JSONObject)e;
			   System.out.println("value of ref id thru json object is:" +jobj.get("reference_id"));
			   System.out.println("value of currency_code thru json object is:" +jobj.getJSONObject("amount").getString("currency_code"));
			   
		   }
		 
		
		}

}
