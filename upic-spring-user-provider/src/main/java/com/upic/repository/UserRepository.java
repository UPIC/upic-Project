package com.upic.repository;

import com.upic.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User getByUserNum(String userNum);

    @Query(value = "select sum(integralLog.integral) from Project project join IntegralLog integralLog where project.guidanceNum = ?1 and project.projectNum = integralLog.integralLogId.projectNum")
    double getTeacherNowWorkloadSummary(String teacherNum);
}