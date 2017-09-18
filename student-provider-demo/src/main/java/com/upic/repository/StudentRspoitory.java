package com.upic.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.upic.po.Student;

public interface StudentRspoitory extends JpaRepository<Student,Long>,JpaSpecificationExecutor<Student>{

	@Query(value="UPDATE upic_student SET upic_stu_num=?2 WHERE upic_id=?1",nativeQuery=true)
	@Modifying
	public int updateByStuId(Long id,String stuid);
	
	@EntityGraph(value="Student.fetch")
	public Student findById(Long id);
	
	@EntityGraph(value="Student.fetch")
	public Student findByStuNum(String stuNum);

}
