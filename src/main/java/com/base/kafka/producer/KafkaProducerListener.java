package com.base.kafka.producer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;

/**
 * kafkaProducer监听器，在producer配置文件中开启
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
public class KafkaProducerListener implements ProducerListener
{
	private static Logger logger = LoggerFactory.getLogger(KafkaProducerListener.class) ;
	/**
	 * 发送成功后调用
	 */
	public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata)
	{
		System.out.println("成功调用测试....");
		System.out.println("kafka调用测试END....");
		logger.info("成功调用...");
	}

	/**
	 * 发送失败后调用
	 */
	public void onError(String topic, Integer partition, Object key, Object value, Exception exception)
	{
		logger.info("失败返回...");
	}

	/**
	 * 方法返回值代表是否启动kafkaProducer监听器
	 */
	public boolean isInterestedInSuccess()
	{
		System.out.println("kafka调用测试START....");
		return true;
	}
	
}
