package com.upic.repository;

import com.upic.dto.OperatorInfo;
import com.upic.po.Operator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface OperatorRepository extends JpaRepository<Operator, Long>, JpaSpecificationExecutor<Operator> {
    @Query(value = "select operator from Operator operator , OperatorRole operatorRole where operator = operatorRole.operator and operatorRole.role.id=?1")
    Page<Operator> getOperatorByRole(long roleId, Pageable pageable);

    Operator getByJobNum(String jobNum);
}