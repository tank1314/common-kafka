package com.base.kafka.service;


public class ServiceDemoOne implements ServiceMsg
{
	
	public void doMethod(String dataObject)
	{
		System.out.println("Service1接收到数据："+dataObject);
	}
	
}
