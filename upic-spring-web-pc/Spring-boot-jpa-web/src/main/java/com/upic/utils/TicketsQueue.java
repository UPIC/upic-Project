package com.upic.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.web.context.request.async.DeferredResult;

import com.upic.po.Student;

public class TicketsQueue {
	private static BlockingQueue<DeferredResult<Student>> b=new LinkedBlockingQueue<DeferredResult<Student>>();
	
//	public static BlockingQueue<DeferredResult> getQueue(){
//		return b;
//	}
	
	public static void putQueue(DeferredResult<Student> d) throws InterruptedException{
		b.put(d);
	}
	
	public static DeferredResult<Student> take() throws InterruptedException{
		return b.take();
	}
}
