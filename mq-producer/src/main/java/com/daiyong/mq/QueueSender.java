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

		Destination destination = session.createQueue("my-queue");

		MessageProducer messageProducer = session.createProducer(destination);

		for (int i = 0; i < 3; i++) {
			TextMessage textMessage = session.createTextMessage("message--" + i);
			Thread.sleep(1000);
			messageProducer.send(textMessage);
		}

		session.commit();
		session.close();
		connection.close();
	}

}
