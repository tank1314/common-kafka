package com.base.kafka.consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.kafka.listener.MessageListener;

/**
 * 消费者
 * @author Administrator
 *
 */
public class KafkaConsumerServer implements MessageListener<String, Object>, InitializingBean
{
	
	private String methodName;
	
	
	public String getMethodName()
	{
		return methodName;
	}

	
	public void setMethodName(String methodName)
	{
		this.methodName = methodName;
	}

	// 消费者开启的线程池 小于等于分区数
	ExecutorService executorService = Executors.newFixedThreadPool(4);
	
	long startTime = System.currentTimeMillis();
	
	Logger logger = LoggerFactory.getLogger(KafkaConsumerServer.class);
	
	public void onMessage(final ConsumerRecord<String, Object> data)
	{
		
		if (data == null)
		{
			logger.warn("消费者暂未收到数据...");
			return;
		}
		logger.info("消费者消费数据START...");
		System.out.println("key:"+data.key());
		System.out.println("partion:" + data.partition());
		System.out.println("offset:" + data.offset());
		System.out.println(data.value());
		System.out.println("kafka消费数据END......" + (System.currentTimeMillis() - startTime) + ":ms");
		/*executorService.execute(new Runnable()
		{
			public void run()
			{
				System.out.println("partion:" + data.partition());
				System.out.println("offset:" + data.offset());
				System.out.println(data.value());
				System.out.println("kafka消费数据END......"+(System.currentTimeMillis()-startTime)+":ms");
			}
		});*/
	}
	
	public void afterPropertiesSet() throws Exception
	{
		System.out.println("执行afterPropertiresSet方法...");
		System.out.println(methodName+"===afterPropertiresSet");
	}
	
	public void init()
	{
		System.out.println("执行init方法...");
		System.out.println(methodName+"===init");
	}
}
