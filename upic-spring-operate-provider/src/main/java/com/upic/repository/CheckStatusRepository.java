package com.upic.repository;

import com.upic.po.CheckStatus;
import com.upic.po.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface CheckStatusRepository extends JpaRepository<CheckStatus, Long>, JpaSpecificationExecutor<CheckStatus> {

    List<CheckStatus> getByType(int i);
}