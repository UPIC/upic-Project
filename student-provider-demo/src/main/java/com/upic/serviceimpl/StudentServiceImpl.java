package com.upic.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upic.dto.StudentInfo;
import com.upic.po.Student;
import com.upic.repository.StudentRspoitory;
import com.upic.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRspoitory rspoitory;
	
	@Override
	public StudentInfo getStudent(Long id) {
		Student findById = rspoitory.findById(id);
		if(findById==null) {
			return null;
		}
		StudentInfo info=new StudentInfo();
		BeanUtils.copyProperties(findById, info);
		return info;
	}

}
