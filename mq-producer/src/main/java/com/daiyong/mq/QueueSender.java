package com.daiyong.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by daiyong on 2017/7/10.
 * email daiyong@coohua.com
 */
public class QueueSender {

	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.100.131:61616");

		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

		Destination destination = session.createQueue("spring-queue");

		MessageProducer messageProducer = session.createProducer(destination);
		messageProducer.setDisableMessageTimestamp(true);

		for (int i = 0; i < 1; i++) {
			TextMessage textMessage = session.createTextMessage("message--333--" + i);
			Thread.sleep(1000);
			messageProducer.send(textMessage);
		}

		/*TextMessage textMessage = session.createTextMessage("message--");
		textMessage.setJMSMessageID("222222222222222222222222");
		textMessage.setJMSType("test");
		textMessage.setJMSTimestamp(1111L);
		textMessage.setStringProperty("p1", "p1");

		messageProducer.send(textMessage);*/

		session.commit();
		session.close();
		connection.close();
	}

}
