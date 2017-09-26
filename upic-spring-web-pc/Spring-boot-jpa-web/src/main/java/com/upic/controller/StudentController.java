package com.upic.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.annotation.JsonView;
import com.upic.dto.StudentInfo;
import com.upic.dto.StudentInfo.StudentListView;
import com.upic.dto.StudentInfo.StudentView;
import com.upic.po.Student;
import com.upic.utils.TicketsQueue;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/stu")
public class StudentController {

	@GetMapping
	@JsonView(StudentListView.class)
	public List<StudentInfo> getStu(String name,@CookieValue String cookie,@RequestHeader String auth) {
		System.out.println(name);
		System.out.println(cookie);
		System.out.println(auth);
		ArrayList<StudentInfo> arrayList = new ArrayList<StudentInfo>();
		arrayList.add(new StudentInfo());
		arrayList.add(new StudentInfo());
		arrayList.add(new StudentInfo());
		return arrayList;
	}

	@GetMapping("/page/{id:\\d}")
	@JsonView(StudentView.class)
//	public StudentInfo getStuPage(Long id, String name, @PageableDefault(size = 15) Pageable pageable) {
	public void getStuPage(Long id) {
		throw new RuntimeException("testError");
//		System.out.println(id);
//		System.out.println(name);
//		System.out.println(pageable.getPageNumber());
//		System.out.println(pageable.getPageSize());
//		System.out.println(pageable.getSort());
//		StudentInfo studentInfo = new StudentInfo();
//		studentInfo.setStuNum("1422110108");
//		studentInfo.setPassword("123456");
//		return studentInfo;
//		return new StudentInfo();
	}

	@PostMapping
	public StudentInfo createStudentInfo(@Valid @RequestBody StudentInfo studentInfo, BindingResult result) {
		if (result.hasErrors()) {
			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		System.out.println(studentInfo);
		StudentInfo s = new StudentInfo(1L, "1422110108", null);
		s.setCreateTime(new Date());
		return s;
	}
	
	@PutMapping("/{id}")
	public StudentInfo updateStudentInfo(@PathVariable Long id,@Valid @RequestBody StudentInfo studentInfo, BindingResult result) {
		if (result.hasErrors()) {
			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		System.out.println(id);
		System.out.println(studentInfo);
		StudentInfo s = new StudentInfo(1L, "1422110108", null);
		s.setCreateTime(new Date());
		return s;
	}

	@DeleteMapping("/{id}")
	public void deleteById(@ApiParam("根据ID删除")@PathVariable Long id){
		System.out.println(id);
	}
	
	// @PostMapping
	// public StudentInfo createStudentInfo(@Valid @RequestBody StudentInfo
	// studentInfo){
	// System.out.println(studentInfo);
	// StudentInfo s=new StudentInfo(1L, "1422110108", null);
	// s.setCreateTime(new Date());
	// return s;}
	
	@PostMapping("/getTickets")
	@ApiOperation("callable抢票")
	public Callable<Student> getTickets(){
		long startTime=new Date().getTime();
		System.out.println(Thread.currentThread().getName()+"开始");
		Callable<Student> result=()->{
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName()+"开始");
			Student s=new Student();
			s.setStuNum(Math.random()+"");
			System.out.println("用时："+(startTime-(new Date().getTime())));
			System.out.println(Thread.currentThread().getName()+"结束");
			return s;
		};
		System.out.println("用时："+(startTime-(new Date().getTime())));
		System.out.println(Thread.currentThread().getName()+"结束");
		return result;
	}
	
	@PostMapping("/getQueueTickets")
	public DeferredResult<Student> getQueueTickets() throws InterruptedException{
		DeferredResult<Student> d=new DeferredResult<Student>();
//		d.setResult(new Student());
		TicketsQueue.putQueue(d);
		return d;
	}
}
