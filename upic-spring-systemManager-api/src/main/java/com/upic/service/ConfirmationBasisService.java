package com.upic.service;

import com.upic.condition.ConfirmationBasisCondition;
import com.upic.dto.ConfirmationBasisInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface ConfirmationBasisService {
    /**
     * 添加节点依据
     *
     * @param confirmationBasisInfo
     * @return
     */
    ConfirmationBasisInfo addConfirmationBasis(ConfirmationBasisInfo confirmationBasisInfo);

    /**
     * 修改节点依据
     *
     * @param confirmationBasisInfo
     * @return
     */
    ConfirmationBasisInfo updateConfirmationBasis(ConfirmationBasisInfo confirmationBasisInfo);

    /**
     * 查询节点依据（条件）
     *
     * @param confirmationBasisCondition
     * @return
     */
    Page<ConfirmationBasisInfo> searchConfirmationBasis(ConfirmationBasisCondition confirmationBasisCondition, Pageable pageable);

    /**
     * 根据ID查找节点依据详情
     *
     * @param confirmationBasisId
     * @return
     */
    ConfirmationBasisInfo getByConfirmationBasisId(long confirmationBasisId);
}
