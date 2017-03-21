package com.base.kafka.test;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.base.kafka.producer.KafkaProducerServer;

public class KafkaTestMain
{
	
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		long startTime = System.currentTimeMillis();
		//proc(context);
		System.out.println("任务执行时间:" + (System.currentTimeMillis() - startTime) + ":ms");
		
		/*ExecutorService fixThreadPool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 100; i++)
		{
			final int index = i;
			fixThreadPool.execute(new Runnable()
			{
				public void run()
				{
					try
					{
						String topic = "orderTopic";
						String value = "test" + index;
						Integer partitionNum = 2; //topic数据发往0 和 1分区中
						String key = "test";//用来生成key 避免随机分配分区
						
						Map<String, Object> res = kafkaProducer.sendMsgForTemplate(topic, key, value, true, partitionNum);
						
						EmployeeCommond employee = new EmployeeCommond() ;
						employee.setAddress("湖北-恩施");
						employee.setAge(25); 
						employee.setEmpName("谭坤");
						employee.setEmpNo("6758");
						employee.setEmpSex("男");
						KafkaSendUtil.sendMessage("logTopic", "method_"+1, employee, true) ;
						//KafkaSendUtil.sendMessage("test1", "method1_"+1, "value1_"+index, true) ;
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					System.out.println("任务执行时间："+(System.currentTimeMillis()-startTime)+" ms");
				}
			});
		}*/
		/*for(int i= 0 ;i<8;i++){
			String topic = "orderTopic";
			String value = "test"; 
			Integer partitionNum = 2; //topic数据发往0 和 1分区中
			String key = "test";//用来生成key
			Map<String,Object> res = kafkaProducer.sendMsgForTemplate(topic, key, value, true, partitionNum) ;
			System.out.println("测试结果如下：===============");
			String message = (String)res.get("message");
			String code = (String)res.get("code");
			
			System.out.println("code:"+code);
			System.out.println("message:"+message);
		}*/
	}
	
	public static void proc(ApplicationContext context)
	{
		final KafkaProducerServer kafkaProducer = context.getBean("kafkaProducerServer", KafkaProducerServer.class);
		ExecutorService fixThreadPool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 1; i++)
		{
			final int index = i;
			fixThreadPool.execute(new Runnable()
			{
				public void run()
				{
					try
					{
						String topic = "orderTopic";
						String value = "test" + index;
						Integer partitionNum = 2; //topic数据发往0 和 1分区中
						String key = "test";//用来生成key 避免随机分配分区
						Map<String, Object> res = kafkaProducer.sendMsgForTemplate(topic, key, value, true, partitionNum);
						/*EmployeeCommond employee = new EmployeeCommond() ;
						employee.setAddress("湖北-恩施");
						employee.setAge(25); 
						employee.setEmpName("谭坤");
						employee.setEmpNo("6758");
						employee.setEmpSex("男");
						KafkaSendUtil.sendMessage("logTopic", "method_"+1, employee, true) ;*/
						//KafkaSendUtil.sendMessage("test1", "method1_"+1, "value1_"+index, true) ;
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
