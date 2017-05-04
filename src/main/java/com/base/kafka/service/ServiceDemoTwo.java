package com.base.kafka.service;


public class ServiceDemoTwo implements ServiceMsg
{
	
	public void doMethod(String dataObject)
	{
		System.out.println("serviceTwo接收到数据:"+dataObject);
	}
}
