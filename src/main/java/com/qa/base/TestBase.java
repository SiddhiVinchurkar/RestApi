package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase
{
	
	public Properties prop;
	
    
	public TestBase()
	{
		
		/*
		 * Reading the properties file
		 */
		try 
		{
		prop = new Properties();
		FileInputStream fis = new FileInputStream("./src/main/java/com/qa/config/config.properties");
		prop.load(fis);
		
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
