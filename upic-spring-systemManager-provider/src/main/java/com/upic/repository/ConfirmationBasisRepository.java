package com.upic.repository;

import com.upic.po.ConfirmationBasis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface ConfirmationBasisRepository extends JpaRepository<ConfirmationBasis, Long>, JpaSpecificationExecutor<ConfirmationBasis> {

    ConfirmationBasis findByCategoryNodeId(long categoryNodeId);
}