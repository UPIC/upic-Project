package com.upic.repository;

import com.upic.po.Prize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface PrizeRepository extends JpaRepository<Prize, Long>, JpaSpecificationExecutor<Prize> {
    @Query(value = "select prize from Prize prize order by prize.creatTime")
    Page<Prize> getHistoryPrize(Pageable pageable);
}