package com.upic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
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
