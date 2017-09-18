package com.upic.repository;

import com.upic.dto.GrainCoinLogInfo;
import com.upic.po.GrainCoinLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface GrainCoinLogRepository extends JpaRepository<GrainCoinLog, Long>, JpaSpecificationExecutor<GrainCoinLog> {
	@Query("SELECT SUM(g.score) FROM GrainCoinLog g where g.userNum=?1")
    double findByUserNum(String userNum);
}