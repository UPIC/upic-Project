package com.upic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.upic.support.RepositoryImpl;


@SpringBootApplication
@ImportResource("classpath:provider.xml")
@EnableJpaRepositories(repositoryBaseClass = RepositoryImpl.class)
public class StudentProviderApplication {
	private static final Log log = LogFactory.getLog(StudentProviderApplication.class);
	public static void main(String[] args) {
		
//		SpringApplication.run(StudentProviderApplication.class, args);
		
		SpringApplication application = new SpringApplication(StudentProviderApplication.class);
//		application.setAdditionalProfiles("pro");
		application.run(args);
		synchronized (StudentProviderApplication.class) {
			while (true) {
				try {
					StudentProviderApplication.class.wait();
				} catch (InterruptedException e) {
					log.error("== synchronized error:",e);
				}
			}
		}
		
	}
}
