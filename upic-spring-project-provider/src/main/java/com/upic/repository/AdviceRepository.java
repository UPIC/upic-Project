package com.upic.repository;

import com.upic.po.Advice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public interface AdviceRepository extends JpaRepository<Advice, Long>, JpaSpecificationExecutor<Advice> {
    Advice getByProjectId(long projectId, Sort sort);
}