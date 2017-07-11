package com.daiyong;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Created by daiyong on 2017/7/11.
 * email daiyong@coohua.com
 */
@Component
public class Consumer {

/*	@JmsListener(destination = "spring-boot-queue")
	public void receiveQueue(String text) {
		System.out.println("spring boot receive : " + text);
	}*/

/*	@JmsListener(destination = "spring-boot-queue")
	public void receiveQueue(Map<String, String> map) {
		System.out.println("spring boot receive : " + map);
	}*/

	@JmsListener(destination = "spring-boot-queue", containerFactory = "jmsListenerContainerFactory")
	public void receiveQueue(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			System.out.println("spring boot receive : " + textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
