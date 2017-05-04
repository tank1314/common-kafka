package com.base.kafka.test;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.base.kafka.entity.EmployeeCommond;
import com.base.kafka.producer.KafkaProducerServer;

public class KafkaTestMain
{
	
	Logger logger = LoggerFactory.getLogger(KafkaTestMain.class);
	
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		proc(context);
		//KafkaProducerServer kafkaProducer = context.getBean("kafkaProducerServer", KafkaProducerServer.class);
	}
	
	public static void proc(ApplicationContext context)
	{
		final KafkaProducerServer kafkaProducer = context.getBean("kafkaProducerServer", KafkaProducerServer.class);
		ExecutorService fixThreadPool = Executors.newFixedThreadPool(50);
		final long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10; i++)
		{
			final int index = i;
			fixThreadPool.execute(new Runnable()
			{
				public void run()
				{
					try
					{
						String topic = "defaultTopic";
						Integer partitionNum = 4; //
						String key = "one";//用来生成key 避免随机分配分区
						EmployeeCommond employee = new EmployeeCommond();
						employee.setAddress("中国-天涯海角2" + index);
						employee.setAge(25);
						employee.setEmpName("ABC");
						employee.setEmpNo("6xx8");
						employee.setEmpSex("男");
						Map<String, Object> res = kafkaProducer.sendMsgForTemplate(topic, key, employee, true, partitionNum);
						System.out.println(res);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			});
		}
	}
}
