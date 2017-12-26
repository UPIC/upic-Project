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
public class GrainCoinJobConfig {
	
	@Autowired
	private JobBuilderFactory jobs;
	
	@Autowired
	private StepBuilderFactory steps;
	
	@Autowired
	private GrainCoinTask grainCoinTask;
	
	@Bean
	public Job job(Step step1) {
		return jobs.get("grainCoinJob").start(step1).build();
	}
	
	@Bean
	public Step step1() {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(grainCoinTask);
		adapter.setTargetMethod("doTask");
		return steps.get("grainCoinJobStep1").tasklet(adapter).build();
	}

}
