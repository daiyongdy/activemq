package com.daiyong.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by daiyong on 2017/7/10.
 * email daiyong@coohua.com
 */
public class QueueReceiver {

	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.100.131:61616");

		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

		Destination destination = session.createQueue("my-queue");

		MessageConsumer messageConsumer = session.createConsumer(destination);

		int i = 0 ;

		while (i < 3) {
			i ++;
			TextMessage textMessage = (TextMessage) messageConsumer.receive();
			session.commit();
			System.out.println(textMessage.getText());
		}

		session.close();
		connection.close();
	}
}
