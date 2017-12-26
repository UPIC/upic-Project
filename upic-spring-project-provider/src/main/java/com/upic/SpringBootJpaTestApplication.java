package com.upic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.upic.common.utils.redis.service.IRedisService;
import com.upic.common.utils.redis.service.RedisService;
import com.upic.common.utils.redis.service.RedisServiceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
@ImportResource("classpath:provider.xml")
public class SpringBootJpaTestApplication {
	
	private static final Log log = LogFactory.getLog(SpringBootJpaTestApplication.class);
	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(SpringBootJpaTestApplication.class);
//		application.setAdditionalProfiles("pro");
		application.run(args);
		synchronized (SpringBootJpaTestApplication.class) {
			while (true) {
				try {
					SpringBootJpaTestApplication.class.wait();
				} catch (InterruptedException e) {
					log.error("== synchronized error:",e);
				}
			}
		}
	}
}
