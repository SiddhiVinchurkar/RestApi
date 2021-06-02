package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient 
{
	
	//Get method without header

	public CloseableHttpResponse getMethod(String url) throws ClientProtocolException, IOException
	{
		
		CloseableHttpClient httpClient = HttpClients .createDefault();   // creates a client connection
		HttpGet httpGet = new HttpGet(url) ;                            // creates a get connection for the passed url. httpGet is of type HTTP request
		
		 /*
		  * closeableHttpResponse will contain the full response
		  * closeableHttpResponse  -> String using EntityUtils -> JsonResponse JSONObject
		  */
		
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);    // This will hit the Get url
		
		return closeableHttpResponse;	 
		 
	}
	
	//Get method with header
	
	public CloseableHttpResponse getMethod(String url , HashMap<String,String> hashMap) throws ClientProtocolException, IOException
	{
		
		CloseableHttpClient httpClient = HttpClients .createDefault();   // creates a client connection
		HttpGet httpGet = new HttpGet(url) ;                            // creates a get connection for the passed url. httpGet is of type HTTP request
	
		for(Map.Entry<String, String> entry : hashMap.entrySet())		
		{
			httpGet.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);    // This will hit the Get url
		
		return closeableHttpResponse;	 
		 
	}
	

}
