package com.upic.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.request.async.DeferredResult;

//import com.upic.po.Student;
import com.upic.utils.TicketsQueue;

public class StartListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		new Thread(() -> {
//			while (true) {
//				try {
//					DeferredResult<Student> take = TicketsQueue.take();
//					Thread.sleep(1000);
////					Student result = (Student) take.getResult();
//					Student result=new Student();
//					result.setStuNum(Math.random()+"");
//					take.setResult(result);
//				} catch (InterruptedException e) {
//					// 自救
//					Thread.currentThread().interrupt();
//				}
//			}
//		}).start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
