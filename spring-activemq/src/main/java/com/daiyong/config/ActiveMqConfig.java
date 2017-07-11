package com.daiyong.config;

import com.daiyong.listener.QueueMessageListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.MessageListener;

/**
 * Created by daiyong on 2017/7/11.
 * email daiyong@coohua.com
 */
@Configuration
public class ActiveMqConfig {

	@Bean("jmsFactory")
	public PooledConnectionFactory connectionFactory() {
		PooledConnectionFactory factory = new PooledConnectionFactory();
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL("tcp://192.168.100.131:61616");

		factory.setConnectionFactory(activeMQConnectionFactory);
		factory.setMaxConnections(100);

		return factory;
	}

	@Bean("destination")
	public ActiveMQQueue destination() {
		return new ActiveMQQueue("spring-queue");
	}


	@Bean("jmsTemplate")
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setDefaultDestination(destination());
		jmsTemplate.setMessageConverter(new SimpleMessageConverter());
		return jmsTemplate;
	}

	@Bean("queueListener")
	public MessageListener queueMessageListener() {
		return new QueueMessageListener();
	}

	@Bean("queueContainer")
	public DefaultMessageListenerContainer queueContainer() {
		DefaultMessageListenerContainer queueContainer = new DefaultMessageListenerContainer();
		queueContainer.setConnectionFactory(connectionFactory());
		queueContainer.setDestination(destination());
		queueContainer.setMessageListener(queueMessageListener());
		queueContainer.setSessionTransacted(false);
		queueContainer.setSessionAcknowledgeMode(4);
		return queueContainer;
	}

}
