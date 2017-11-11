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
	@Autowired
	private UpicRedisComponent upicRedisComponent;
	@Override
	public void doTask() {
		//处理结束的
		doProjectSinEnd();
		//上架
		doProjectSinStart();
		//项目结束的标记为结束
		doProjectEnd();
	}
	
	/**
	 * 处理项目结束的标记为结束
	 */
	private void doProjectEnd() {
		PageRequest page=new PageRequest(0, 100);
		ProjectCondition p=new ProjectCondition();
		p.setEndTimeTo(getOneDayBefore(new Date(),-7));
		p.setEndTime(new Date());
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
		p.setSignUpStartTimeTo(getOneDayBefore(new Date(),1));
		p.setSignUpStartTime(new Date());
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
		p.setSignUpEndTime(new Date());
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
			upicRedisComponent.keys(x.getProjectNum()+"hash").forEach(s->{
				String hashKey=(String) s;
				upicRedisComponent.deletByHashKey(x.getProjectNum(),hashKey);
			});
		});
	}
	private void doPrijectRedisUp(List<Project> content) {
		content.parallelStream().forEach(x->{
			upicRedisComponent.init(x.getProjectNum());
//			upicRedisComponent.deletByKey(x.getProjectNum()+"hash");
		});
	}
}
