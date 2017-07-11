package com.daiyong;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringActivemqApplicationTests {

	@Test
	public void contextLoads() throws InterruptedException {
		Thread.sleep(Integer.MAX_VALUE);
	}

	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void testSend() {
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = new ActiveMQTextMessage();
				textMessage.setText("spring-message-3333");
				return textMessage;
			}
		});
	}

	@Test
	public void testReceive() {
		String msg = (String) jmsTemplate.receiveAndConvert();
		System.out.println(msg);
	}
}
