package com.upic.repository;

import com.upic.enums.IntegralLogTypeEnum;
import com.upic.po.IntegralLog;
import com.upic.po.IntegralLogId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface IntegralLogRepository extends JpaRepository<IntegralLog, Long>, JpaSpecificationExecutor<IntegralLog> {

    List<IntegralLog> getByApartmentAndType(String apartment, IntegralLogTypeEnum type);

    IntegralLog findByIntegralLogId(IntegralLogId integralLogId);

    @Query(value = "SELECT SUM(integralLog.integral) from IntegralLog integralLog where integralLog.integralLogId.studentNum = ?1 and integralLog.status = 'COMPLETED' or integralLog.status = 'HAVEPASSED'")
    double findByStudentNum(String studentNum);

    @Query(value = "SELECT integralLog from IntegralLog integralLog where integralLog.integralLogId.projectNum = ?1")
    Page<IntegralLog> getUserListByProjectNum(String projectNum, Pageable pageable);

    @Query(value = "SELECT count(integralLog) from IntegralLog integralLog where integralLog.integralLogId.projectNum = ?1")
    int getSignUpNumberByProjectNum(String projectNum);

    @Query(value = "select integralLog from IntegralLog integralLog where integralLog.integralLogId.studentNum = ?1 and integralLog.type = 'VOLUNTARY_APPLICATION'")
    Page<IntegralLog> getIntegralLogByMySelf(String studentNum, Pageable pageable);

    @Query(value = "select integralLog from IntegralLog integralLog where integralLog.integralLogId.studentNum = ?1")
    Page<IntegralLog> getAllIntegralLogByStudentNum(String studentNum, Pageable pageable);

    @Query(value = "select integralLog from IntegralLog integralLog where integralLog.integralLogId.studentNum = ?1 and integralLog.type = 'VOLUNTARY_APPLICATION' and integralLog.status <> 'HAVEPASSED'")
    Page<IntegralLog> getIntegralLogDeclaring(String studentNum, Pageable pageable);
}