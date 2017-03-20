package com.base.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

/**
 * 消费者
 * @author Administrator
 *
 */
public class KafkaConsumerServer implements MessageListener<String, String>
{

	public void onMessage(ConsumerRecord<String, String> data)
	{
		System.out.println("kafka生产数据开始......");
		System.out.println(data.topic());
		System.out.println(data.offset());
		System.out.println(data.partition());
		System.out.println(data.value());
		System.out.println(data.key());
		System.out.println("kafka生产数据END......");
	}
	
}
