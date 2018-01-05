package com.upic.serviceimpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.GrainCoinLogCondition;
import com.upic.dto.GrainCoinLogInfo;
import com.upic.po.GrainCoinLog;
import com.upic.repository.GrainCoinLogRepository;
import com.upic.repository.PrizeRepository;
import com.upic.repository.Spec.GrainCoinLogSpec;
import com.upic.service.GrainCoinLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("grainCoinLogService")
public class GrainCoinLogServiceImpl implements GrainCoinLogService {
	protected static final Logger LOGGER = LoggerFactory.getLogger(GrainCoinLogServiceImpl.class);
	
	@Autowired
	private GrainCoinLogRepository grainCoinLogRepository;

	@Autowired
	private PrizeRepository prizeRepository;
	
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Override
	public double watchGrainCoin(String userNum) {
		try {
			return grainCoinLogRepository.findByUserNum(userNum);
		} catch (Exception e) {
			LOGGER.info("watchGrainCoin:查看分数失败。错误信息：" + e.getMessage());
			return 0;
		}
	}

	@Override
	public Page<GrainCoinLogInfo> searchPrizeByCondition(GrainCoinLogCondition grainCoinLogCondition,
			Pageable pageable) {
		Page<GrainCoinLog> grainCoinLogs = null;
		try {
			grainCoinLogs = grainCoinLogRepository.findAll(new GrainCoinLogSpec(grainCoinLogCondition), pageable);
			return QueryResultConverter.convert(grainCoinLogs, pageable,
					new AbstractDomain2InfoConverter<GrainCoinLog, GrainCoinLogInfo>() {
						@Override
						protected void doConvert(GrainCoinLog domain, GrainCoinLogInfo info) throws Exception {
							UpicBeanUtils.copyProperties(domain, info);
						}
					});
		} catch (Exception e) {
			LOGGER.info("searchPrizeByCondition:批量查看素拓记录失败。错误信息：" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<Object> exportGrainCoinLog(GrainCoinLogCondition condition) {
		List<GrainCoinLog> grainCoinLogs = new ArrayList<>();
		List<GrainCoinLogInfo> grainCoinLogInfoList = new ArrayList<>();
		try {
			grainCoinLogs = grainCoinLogRepository.findAll(new GrainCoinLogSpec(condition));
			for (GrainCoinLog grainCoinLog : grainCoinLogs) {
				GrainCoinLogInfo grainCoinLogInfo = new GrainCoinLogInfo();
				UpicBeanUtils.copyProperties(grainCoinLog, grainCoinLogInfo);
				grainCoinLogInfoList.add(grainCoinLogInfo);
			}
			return toObject(grainCoinLogInfoList);
		} catch (Exception e) {
			LOGGER.info("exportGrainCoinLog:批量查看素拓记录失败。错误信息：" + e.getMessage());
			return null;
		}
	}

	@Override
	public GrainCoinLogInfo saveGrainCoinLog(GrainCoinLogInfo grainCoinLogInfo) {
		try {
			GrainCoinLog grainCoinLog = new GrainCoinLog();
			UpicBeanUtils.copyProperties(grainCoinLogInfo, grainCoinLog);
			grainCoinLog = grainCoinLogRepository.save(grainCoinLog);
			UpicBeanUtils.copyProperties(grainCoinLog, grainCoinLogInfo);
			return grainCoinLogInfo;
		} catch (Exception e) {
			LOGGER.info("saveGrainCoinLog。错误信息：" + e.getMessage());
			return null;
		}
	}

	static public <E> List<Object> toObject(List<E> list) {
		List<Object> objlist = new ArrayList<Object>();
		for (Object e : list) {
			Object obj = (Object) e;
			objlist.add(obj);
		}
		return objlist;
	}

	/**
	 * 定时任务 每天晚上十二点执行
	 */
//	@Scheduled(cron = "0 0 1,13,19 * * ?")
	@Scheduled(cron = "0 0 0/1 * * ?")//测试每小时更新一次
//	@Scheduled(cron = "0 0/1 * * * ?")
	@Override
	// @Scheduled(cron = "0 0 0 * * ?")
	public void task() throws Exception {
		Map<String, JobParameter> param = new HashMap<>();
		param.put("startTime", new JobParameter(new Date()));
		jobLauncher.run(job, new JobParameters(param));
	}
}
