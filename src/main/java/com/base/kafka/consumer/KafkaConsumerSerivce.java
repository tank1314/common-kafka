package com.base.kafka.consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;

public class KafkaConsumerSerivce implements MessageListener<String, Object>, InitializingBean
{
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private KafkaMessageListenerContainer kafkaMessageListenerContainer;
	
	private int threadNum = 8;
	
	private int maxQueueSize = 2000;
	
	private ExecutorService executorService = new ThreadPoolExecutor(threadNum, threadNum, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(maxQueueSize),
			new ThreadPoolExecutor.CallerRunsPolicy());
	
	long startTime = System.currentTimeMillis();
	
	public void onMessage(final ConsumerRecord<String, Object> data)
	{
		executorService.execute(new Runnable()
		{
			public void run()
			{
				System.out.println("partion:" + data.partition());
				System.out.println("offset:" + data.offset());
				System.out.println(data.value());
				System.out.println("kafka消费数据END......" + (System.currentTimeMillis() - startTime) + ":ms");
			}
		});
	}
	
	/**
	 * 设置监听
	 */
	public void afterPropertiesSet() throws Exception
	{
		ContainerProperties containerProperties = kafkaMessageListenerContainer.getContainerProperties();
		if (containerProperties != null)
		{
			containerProperties.setMessageListener(this);
		}
		else
		{
			System.out.println("等待10s再次发送...");
			Thread.sleep(10000);
		}
	}
	
}
