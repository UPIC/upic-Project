/**
 * 
 */
package com.upic.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DTZ
 *
 */
@Configuration
@EnableBatchProcessing
public class WeixinJSSDKJobConfig {
	
	@Autowired
	private JobBuilderFactory jobs;
	
	@Autowired
	private StepBuilderFactory steps;
	
	@Autowired
	private WeixinJSSDKTask weixinJSSDKTask;
	
	@Bean
	public Job job(Step step1) {
		//可以有next
		return jobs.get("weixinJSSDKJob").start(step1).build();
	}
	
	@Bean
	public Step step1() {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		//执行对象
		adapter.setTargetObject(weixinJSSDKTask);
		//执行对象方法
		adapter.setTargetMethod("doTask");
		return steps.get("weixinJSSDKJobStep1").tasklet(adapter).build();
	}

}
