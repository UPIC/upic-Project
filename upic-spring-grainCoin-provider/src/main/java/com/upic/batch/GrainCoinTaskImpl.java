/**
 * 
 */
package com.upic.batch;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.upic.common.utils.redis.service.IRedisService;
import com.upic.dto.IntegralLogInfo;
import com.upic.enums.GrainCoinLogTypeEnum;
import com.upic.enums.IntegralLogStatusEnum;
import com.upic.po.GrainCoinLog;
import com.upic.po.IntegralLog;
import com.upic.repository.GrainCoinLogRepository;
import com.upic.repository.IntegralLogRepository;
import com.upic.service.GrainCoinLogService;
import com.upic.service.IntegralLogService;

/**
 * @author DTZ
 *
 */
@Component
public class GrainCoinTaskImpl implements GrainCoinTask {

	@Autowired
	private IRedisService upicRedisComponent;

	@Autowired
	private IntegralLogRepository integralLogRepository;

	@Autowired
	private GrainCoinLogRepository grainCoinLogRepository;

	@Override
	@Transactional(rollbackOn=Exception.class)
	public void doTask() {
		// 处理redis中的project
		String[] redisAllProjectNum = getRedisAllProjectNum();
		Stream.of(redisAllProjectNum).parallel().forEach(x->{
			List<IntegralLog> byProjectNum = integralLogRepository.getByProjectNum(x);
			byProjectNum.parallelStream().forEach(i->{
				if(i.getStatus().equals(IntegralLogStatusEnum.SIGNED_IN)) {
					i.setStatus(IntegralLogStatusEnum.COMPLETED);
					integralLogRepository.saveAndFlush(i);
				}
				GrainCoinLog g=new GrainCoinLog();
				g.setCreatTime(new Date());
				g.setProjectName(i.getProjectName());
				g.setProjectNum(i.getIntegralLogId().getProjectNum());
				g.setScore(i.getIntegral()*1000);
				g.setType(GrainCoinLogTypeEnum.INCOME);
				g.setUsername(i.getStudent());
				g.setUserNum(i.getIntegralLogId().getStudentNum());
				grainCoinLogRepository.save(g);
			});
		});
	}

	/**
	 * key:PROJECT_CHECKED
	 * 
	 * @return
	 */
	private String[] getRedisAllProjectNum() {
		String result = upicRedisComponent.get("PROJECT_CHECKED");
		return result.split(",");
	}

}
