package com.base.kafka.producer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.alibaba.fastjson.JSON;
import com.base.kafka.constant.KafkaConstant;

/**
 * kafka生产者 模板
 * 
 * @author Administrator
 * 
 */
@Component
public class KafkaProducerServer
{
	
	Logger logger = LoggerFactory.getLogger(KafkaProducerServer.class) ;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	/**
	 * 根据key的hash数值选择数据分区
	 * @param key
	 * @param partitionNum
	 * @return
	 */
	private int getPartitionIndex(String key, int partitionNum)
	{
		if (key == null)
		{
			Random random = new Random();
			return random.nextInt(partitionNum);
		}
		else
		{
			int partion = Math.abs(key.hashCode()) % partitionNum;
			return partion;
		}
	}
	
	/**
	 * 
	 * @param topic 消息主题
	 * @param key	key 用作计算hashcode
	 * @param value	messageValue
	 * @param ifPartition	是否分区
	 * @param partitionNum  分区数(最大值为broker中设置的最大值)
	 * @return
	 */
	public Map<String, Object> sendMsgForTemplate(String topic, String key, Object data, boolean isPartition, int partitionNum)
	{
		String keyCode = key + "-" + data.hashCode();
		String dataMsg = JSON.toJSONString(data);
		if (isPartition)
		{
			//计算分区
			int partition = getPartitionIndex(keyCode, partitionNum);
			ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, partition, keyCode, dataMsg);
			Map<String, Object> map = checkKafkaResult(result);
			return map;
		}
		else
		{
			ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, keyCode, dataMsg);
			Map<String, Object> map = checkKafkaResult(result);
			return map;
		}
	}
	
	/**
	 * 判断生产者数据是否正常
	 * @param result
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> checkKafkaResult(ListenableFuture<SendResult<String, String>> result)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if (result != null)
		{
			try
			{
				SendResult res = result.get(); //返回结果集
				Long offsetIndex = res.getRecordMetadata().offset();
				if (offsetIndex != null && offsetIndex > 0)
				{
					map.put("code", KafkaConstant.SUCCESSCODE);
					map.put("msg", KafkaConstant.SUCCESSMSG);
					return map;
				}
				else
				{
					map.put("code", KafkaConstant.KAFKA_NO_OFFSETCODE);
					map.put("msg", KafkaConstant.KAFKA_NO_OFFSETMSG);
					return map;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				map.put("code", KafkaConstant.KAFKA_SEND_ERRORCODE);
				map.put("msg", KafkaConstant.KAFKA_SEND_ERRORMSG + ":" + e.getMessage());
				return map;
			}
		}
		else
		{
			map.put("code", KafkaConstant.KAFKA_NO_RESULTCODE);
			map.put("msg", KafkaConstant.KAFKA_NO_RESULTMSG);
			return map;
		}
	}
}
