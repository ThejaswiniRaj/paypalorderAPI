package com.paypal.createorder_resourcebuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;


public class resourcebuilder {
	
	String path="C:\\jansi_javafiles_2\\paypal_practice\\createpojo.csv";
	
	public List<HashMap<String, String>> readcsvdata(String path) 
	{
		int linenum=0;
		String stringline;
		String [] legend = null;
		HashMap<String,String> hm=new HashMap();
		List<HashMap<String,String>>lst=new ArrayList();
		try
		{
		FileReader file=new FileReader(path);
		BufferedReader br=new BufferedReader(file);
		
		while( (stringline=br.readLine())!=null)
		{
			
		String[] array=stringline.split(",");
			if( linenum==0)
			{
				legend=array;
				
			}
			else
			{
				for(int i=0;i<legend.length;i++)
				{
					hm.put(legend[i], array[i]);
					
				}
				
				lst.add(hm);
						
		}
			linenum++;
			
		}
		}
		
		
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//System.out.println("Value of intent is "+lst.get(0).get("Intent"));
		return lst;
	}
	
	
	//@Test
    //public void resourcebuild()
	public post_obj resourcebuild()
	
	{
		List<HashMap<String, String>> lstcsv =readcsvdata(path) ;
		
		Amount amt=new Amount();
	amt.setCurrency_code(lstcsv.get(0).get("currency_code"));
	amt.setValue(lstcsv.get(0).get("value"));
	
	Purchase_units pu=new Purchase_units();
	pu.setAmount(amt);
	
	List<Purchase_units> lst=new ArrayList();
	lst.add(pu);
	post_obj po=new post_obj();
	po.setIntent(lstcsv.get(0).get("intent"));
	po.setPurchase_units(lst);
	
	/*System.out.println("value of intent is :" +po.getIntent());
	List<Purchase_units> lst1 =po.getPurchase_units();
	for( Purchase_units e:lst1)
	{
		System.out.println("value of currencycode is:" +e.getAmount().getCurrency_code() );
	}*/
	
	
	return po;
	
	
	
		
	}
	
	

	
	

}
