package com.daiyong.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by daiyong on 2017/7/11.
 * email daiyong@coohua.com
 */
public class QueueMessageListener implements MessageListener {
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("recive message : " + ((TextMessage)message).getText());

			message.acknowledge();

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
