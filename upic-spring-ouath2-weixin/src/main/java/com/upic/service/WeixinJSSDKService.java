package com.upic.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.upic.common.lock.GlobalLock;


@Service
public class WeixinJSSDKService {
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	/**
	 * 每两小时运行一次
	 */
	@Scheduled(cron = "0 0 0/2 * * ? ")
	@GlobalLock(path = "/weixin/jssdk")
	public void task() throws Exception {
		Map<String, JobParameter> param = new HashMap<>();
		param.put("startTime", new JobParameter(new Date()));
		jobLauncher.run(job, new JobParameters(param));
	}
}
