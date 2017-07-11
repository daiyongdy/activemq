package com.daiyong;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

/**
 * Created by daiyong on 2017/7/11.
 * email daiyong@coohua.com
 */
@Configuration
public class JmsConfig {

	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerFactory(
			ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setTransactionManager(transactionManager());
		factory.setSessionTransacted(false);
		factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		return factory;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JmsTransactionManager transactionManager = new JmsTransactionManager();
		transactionManager.setConnectionFactory(jmsConnectionFactory());
		return transactionManager;
	}

	@Bean
	public QueueConnectionFactory jmsConnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.100.131:61616");
		return connectionFactory;
	}

}
