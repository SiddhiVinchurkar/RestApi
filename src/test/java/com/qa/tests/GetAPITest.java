package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.Constants;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase
{
	TestBase testBase;
	String url;
	String apiUrl;
	String completeUrl;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setUp() 
	{
		testBase = new TestBase();  						  // this will initialize the prop object
		url = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		
	    completeUrl = url + apiUrl;
		
	}
	
	@Test(priority = 1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException
	{
		System.out.println("Executing Get API Mehtod Without Headers ");
		
		restClient = new RestClient();
		closeableHttpResponse = restClient.getMethod(completeUrl);
		
		
		//Status code
		
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code : "+statusCode);
		
		
		Assert.assertEquals(statusCode,Constants.RESPONSE_STATUS_CODE_200,"Status code is not 200" );
		
		System.out.println();
		
		//JSON String
		
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity() , "UTF-8");
		
		JSONObject jsonResponse = new JSONObject(responseString);  			// responseString is converted into a Json object
	
		System.out.println("Json response from Api : "+jsonResponse);
		
		System.out.println();
		
		//Per page attribute
		
		String perPageValue =  TestUtil.getValueByJPath(jsonResponse,"/per_page");
	    System.out.println("Per Page Value : "+perPageValue);
		
		Assert.assertEquals(Integer.parseInt(perPageValue), Constants.PER_PAGE_VALUE);
		
		System.out.println();
		
		//Total value attribute 	
		
		String totalValue =  TestUtil.getValueByJPath(jsonResponse,"/total");
	    System.out.println("Total Value : "+totalValue );
		
		Assert.assertEquals(Integer.parseInt(totalValue) , Constants.TOTAL_VALUE);
		
		System.out.println();
		
		//JSON Array
		
		String last_name = TestUtil.getValueByJPath(jsonResponse ,"/data[0]/last_name");
		String id = TestUtil.getValueByJPath(jsonResponse ,"/data[0]/id");
		String avatar = TestUtil.getValueByJPath(jsonResponse ,"/data[0]/avatar");
		String first_name = TestUtil.getValueByJPath(jsonResponse ,"/data[0]/first_name");
		
		System.out.println("----------------------JSON Array ------------------------"+"\n");
			
		System.out.println("Last name : "+last_name);
		System.out.println("Id : "+id);
		System.out.println("Avatar : "+avatar);
		System.out.println("First name : "+first_name);
		
		Assert.assertEquals(last_name, "Bluth");
		Assert.assertEquals(id, "1");
		Assert.assertEquals(avatar, "https://reqres.in/img/faces/1-image.jpg");
		Assert.assertEquals(first_name,"George");
		
		System.out.println();
		
		//All Headers
		 Header[] headerArray = closeableHttpResponse.getAllHeaders();
		 
		 HashMap<String,String> allHeaders = new HashMap<String, String>();

		 for(Header h: headerArray)
		 {
			 allHeaders.put(h.getName(), h.getValue());  // add header values into a HashMap
		 }
		
		 System.out.println("----------------------Header Array ------------------------"+"\n");
		
	
		 System.out.println("Header Array : "+allHeaders);
	}
	
	
	@Test(priority = 2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException
	{
		System.out.println("Executing Get API Mehtod With Headers ");
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		
		closeableHttpResponse = restClient.getMethod(completeUrl);
		
		
		//Status code
		
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code : "+statusCode);
		
		
		Assert.assertEquals(statusCode,Constants.RESPONSE_STATUS_CODE_200,"Status code is not 200" );
		
		System.out.println();
		
		//JSON String
		
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity() , "UTF-8");
		
		JSONObject jsonResponse = new JSONObject(responseString);  			// responseString is converted into a Json object
	
		System.out.println("Json response from Api : "+jsonResponse);
		
		System.out.println();
		
		//Per page attribute
		
		String perPageValue =  TestUtil.getValueByJPath(jsonResponse,"/per_page");
	    System.out.println("Per Page Value : "+perPageValue);
		
		Assert.assertEquals(Integer.parseInt(perPageValue), Constants.PER_PAGE_VALUE);
		
		System.out.println();
		
		//Total value attribute 	
		
		String totalValue =  TestUtil.getValueByJPath(jsonResponse,"/total");
	    System.out.println("Total Value : "+totalValue );
		
		Assert.assertEquals(Integer.parseInt(totalValue) , Constants.TOTAL_VALUE);
		
		System.out.println();
		
		//JSON Array
		
		String last_name = TestUtil.getValueByJPath(jsonResponse ,"/data[0]/last_name");
		String id = TestUtil.getValueByJPath(jsonResponse ,"/data[0]/id");
		String avatar = TestUtil.getValueByJPath(jsonResponse ,"/data[0]/avatar");
		String first_name = TestUtil.getValueByJPath(jsonResponse ,"/data[0]/first_name");
		
		System.out.println("----------------------JSON Array ------------------------"+"\n");
			
		System.out.println("Last name : "+last_name);
		System.out.println("Id : "+id);
		System.out.println("Avatar : "+avatar);
		System.out.println("First name : "+first_name);
		
		Assert.assertEquals(last_name, "Bluth");
		Assert.assertEquals(id, "1");
		Assert.assertEquals(avatar, "https://reqres.in/img/faces/1-image.jpg");
		Assert.assertEquals(first_name,"George");
		
		System.out.println();
		
		//All Headers
		 Header[] headerArray = closeableHttpResponse.getAllHeaders();
		 
		 HashMap<String,String> allHeaders = new HashMap<String, String>();

		 for(Header h: headerArray)
		 {
			 allHeaders.put(h.getName(), h.getValue());  // add header values into a HashMap
		 }
		
		 System.out.println("----------------------Header Array ------------------------"+"\n");
		
	
		 System.out.println("Header Array : "+allHeaders);
	}
}
