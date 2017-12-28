/**
 * 
 */
package com.upic.batch;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.upic.common.utils.redis.UpicRedisComponent;
import com.upic.common.utils.redis.service.IRedisService;
import com.upic.condition.ProjectCondition;
import com.upic.enums.ImplementationProcessEnum;
import com.upic.po.Project;
import com.upic.repository.ProjectRepository;
import com.upic.repository.spec.ProjectSpec;

/**
 * @author zhailiang
 *
 */
@Component
public class ProjectTaskImpl implements ProjectTask {

	@Autowired
	private ProjectRepository projectRepository;
	/* (non-Javadoc)
	 * @see com.lesson.batch.BookTask#doTask()
	 */
	
//	@Autowired
//	private IRedisService redisService;
	@Autowired
	private IRedisService upicRedisComponent;
	@Override
	public void doTask() {
		//处理结束的
		doProjectSinEnd();
		//上架
		doProjectSinStart();
		//项目结束的标记为结束
		doProjectEnd();
		//查询所有验收的项目
		searchAllCheckedPro();
	}
	

	/**
	 * 处理项目结束的标记为结束
	 */
	private void doProjectEnd() {
		PageRequest page=new PageRequest(0, 100);
		ProjectCondition p=new ProjectCondition();
		p.setEndTime(getOneDayBefore(new Date(),-7));
		p.setEndTimeTo(new Date());
		p.setImplementationProcess(ImplementationProcessEnum.HAVE_IN_HAND);
		Page<Project> pageBean = projectRepository.findAll(new ProjectSpec(p), page);
		for(int i=0;i<pageBean.getTotalPages();i++) {
			List<Project> content=null;
			if(i==0) {
				content = pageBean.getContent();
			}else {
				page=new PageRequest(i, 100);
				content = projectRepository.findAll(new ProjectSpec(p), page).getContent();
			}
			doProjectSeEnd(content);
		}
	}
	/**
	 * 标记为项目结束
	 * @param content
	 */
	private void doProjectSeEnd(List<Project> content) {
		content.parallelStream().forEach(x->{
			if(x.getImplementationProcess().getNum()==ImplementationProcessEnum.HAVE_IN_HAND.getNum()) {
				if(x.getUnit().equals("2")) {
					x.setImplementationProcess(ImplementationProcessEnum.CHECKING_AGAIN);
				}else if(x.getUnit().equals("3")) {
					x.setImplementationProcess(ImplementationProcessEnum.CHECKING);
				}else if(x.getUnit().equals("1")) {
					x.setImplementationProcess(ImplementationProcessEnum.CHECKING_FINAL);
				}else {
					return;
				}
				projectRepository.saveAndFlush(x);
			}
		});
	}

	/**
	 * 上架
	 */
	private void doProjectSinStart() {
		PageRequest page=new PageRequest(0, 100);
		ProjectCondition p=new ProjectCondition();
		p.setSignUpStartTime(getOneDayBefore(new Date(),-1));
		p.setSignUpStartTimeTo(new Date());
		p.setImplementationProcess(ImplementationProcessEnum.AUDITED);
		Page<Project> pageBean = projectRepository.findAll(new ProjectSpec(p), page);
		for(int i=0;i<pageBean.getTotalPages();i++) {
			List<Project> content=null;
			if(i==0) {
				content = pageBean.getContent();
			}else {
				page=new PageRequest(i, 100);
				content = projectRepository.findAll(new ProjectSpec(p), page).getContent();
			}
			doPrijectRedisUp(content);
		}
		
	}
	public  Date getOneDayBefore(Date dateEnd,int dates){
	    Calendar date = Calendar.getInstance();
	    date.setTime(dateEnd);
	    date.set(Calendar.DATE, date.get(Calendar.DATE) + dates);
	    return date.getTime();
	}
	/**
	 * 处理报名结束的
	 */
	private void doProjectSinEnd() {
		PageRequest page=new PageRequest(0, 100);
		ProjectCondition p=new ProjectCondition();
		p.setSignUpEndTime(getOneDayBefore(new Date(),-7));
		p.setSignUpEndTimeTo(new Date());
		p.setImplementationProcess(ImplementationProcessEnum.ENROLLMENT);
		//查询是否报名的
		Page<Project> pageBean = projectRepository.findAll(new ProjectSpec(p), page);
		for(int i=0;i<pageBean.getTotalPages();i++) {
			List<Project> content=null;
			if(i==0) {
				content = pageBean.getContent();
			}else {
				page=new PageRequest(i, 100);
				content = projectRepository.findAll(new ProjectSpec(p), page).getContent();
			}
			doPrijectRedis(content);
		}
	}
	
	private void doPrijectRedis(List<Project> content) {
		content.parallelStream().forEach(x->{
			upicRedisComponent.deletByKey(x.getProjectNum());
			x.setImplementationProcess(ImplementationProcessEnum.COMPLETED);
			upicRedisComponent.keys(x.getProjectNum()+"hash").forEach(s->{
				String hashKey=(String) s;
				upicRedisComponent.deletByHashKey(x.getProjectNum()+"hash",hashKey);
			});
		});
	}
	private void doPrijectRedisUp(List<Project> content) {
		content.stream().forEach(x->{
			x.setImplementationProcess(ImplementationProcessEnum.ENROLLMENT);
			projectRepository.saveAndFlush(x);
			upicRedisComponent.init(x.getProjectNum());
			upicRedisComponent.init(x.getProjectNum()+"hash");
//			upicRedisComponent.deletByKey(x.getProjectNum()+"hash");
		});
	}
	

	/**
	 * 查询所有已验收的项目编号，并且存放到redis
	 * key:PROJECT_CHECKED
	 */
	private void searchAllCheckedPro() {
		ProjectCondition p=new ProjectCondition();
		StringBuffer sb=new StringBuffer();
		p.setImplementationProcess(ImplementationProcessEnum.CHECKED);
		List<Project> findAll = projectRepository.findAll(new ProjectSpec(p));
		findAll.parallelStream().forEach(x->{
			sb.append(x.getProjectNum()).append(",");
		});
		upicRedisComponent.set("PROJECT_CHECKED", sb.toString());
	}
}
