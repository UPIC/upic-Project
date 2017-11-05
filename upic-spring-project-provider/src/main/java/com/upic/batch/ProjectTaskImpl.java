/**
 * 
 */
package com.upic.batch;

import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component
public class ProjectTaskImpl implements ProjectTask {

	/* (non-Javadoc)
	 * @see com.lesson.batch.BookTask#doTask()
	 */
	@Override
	public void doTask() {
		System.out.println("task 开始运行");
	}

}
