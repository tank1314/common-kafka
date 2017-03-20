package com.base.kafka.test;

import java.util.Map;

import com.base.kafka.producer.KafkaProducerServer;


public class KafkaTestMain
{
	
	public static void main(String[] args)
	{
		KafkaProducerServer kafkaProducer = new KafkaProducerServer();
		String topic = "orderTopic";
		String value = "test";
		Integer partitionNum = 2;
		String key = "test";//用来生成key
		Map<String,Object> res = kafkaProducer.sendMsgForTemplate(topic, key, value, true, partitionNum) ;
		System.out.println("测试结果如下：===============");
		String message = (String)res.get("message");
		String code = (String)res.get("code");
		
		System.out.println("code:"+code);
		System.out.println("message:"+message);
	}
	
}
