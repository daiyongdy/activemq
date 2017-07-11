package com.daiyong.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;


/**
 * Created by daiyong on 2017/7/11.
 * email daiyong@coohua.com
 */
@Configuration
public class JMSConfig {

	@Bean("springBootQueue")
	public Queue springBootQueue() {
		return new ActiveMQQueue("spring-boot-queue");
	}

}
