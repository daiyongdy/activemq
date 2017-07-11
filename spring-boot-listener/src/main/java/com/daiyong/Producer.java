package com.daiyong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daiyong on 2017/7/11.
 * email daiyong@coohua.com
 */
@Component
@EnableScheduling
public class Producer {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Queue queue;

	@Scheduled(fixedDelay = 3000)
	public void send() {
		Map<String, String> map = new HashMap<>();
//		map.put("hello", "activeMQ");
		jmsMessagingTemplate.convertAndSend(queue, "hell daiyong");
	}

}
