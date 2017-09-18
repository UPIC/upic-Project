package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.ConfirmationBasisCondition;
import com.upic.dto.ConfirmationBasisInfo;
import com.upic.po.ConfirmationBasis;
import com.upic.repository.ConfirmationBasisRepository;
import com.upic.repository.Spec.ConfirmationBasisSpec;
import com.upic.service.ConfirmationBasisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/11.
 */
@Service("confirmationBasisService")
public class ConfirmationBasisServiceImpl implements ConfirmationBasisService {
    @Autowired
    private ConfirmationBasisRepository confirmationBasisRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(ConfirmationBasisServiceImpl.class);

    public ConfirmationBasisInfo addConfirmationBasis(ConfirmationBasisInfo confirmationBasisInfo) {
        try {
            ConfirmationBasis confirmationBasis = new ConfirmationBasis();
            UpicBeanUtils.copyProperties(confirmationBasisInfo, confirmationBasis);
            confirmationBasis = confirmationBasisRepository.save(confirmationBasis);
            UpicBeanUtils.copyProperties(confirmationBasis, confirmationBasisInfo);
            return confirmationBasisInfo;
        } catch (Exception e) {
            LOGGER.info("addConfirmationBasis:节点依据" + confirmationBasisInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public ConfirmationBasisInfo updateConfirmationBasis(ConfirmationBasisInfo confirmationBasisInfo) {
        try {
            ConfirmationBasis confirmationBasis = new ConfirmationBasis();
            UpicBeanUtils.copyProperties(confirmationBasisInfo, confirmationBasis);
            confirmationBasis = confirmationBasisRepository.saveAndFlush(confirmationBasis);
            UpicBeanUtils.copyProperties(confirmationBasis, confirmationBasisInfo);
            return confirmationBasisInfo;
        } catch (Exception e) {
            LOGGER.info("updateConfirmationBasis:节点依据" + confirmationBasisInfo.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<ConfirmationBasisInfo> searchConfirmationBasis(ConfirmationBasisCondition confirmationBasisCondition, Pageable pageable) {
        Page<ConfirmationBasis> confirmationBasisPage = null;
        try {
            confirmationBasisPage = confirmationBasisRepository.findAll(new ConfirmationBasisSpec(confirmationBasisCondition), pageable);
            return QueryResultConverter.convert(confirmationBasisPage, pageable, new AbstractDomain2InfoConverter<ConfirmationBasis, ConfirmationBasisInfo>() {
                protected void doConvert(ConfirmationBasis domain, ConfirmationBasisInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("updateConfirmationBasis:节点依据列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public ConfirmationBasisInfo getByConfirmationBasisId(long confirmationBasisId) {
        try {
            ConfirmationBasis confirmationBasis = confirmationBasisRepository.findOne(confirmationBasisId);
            if (confirmationBasis == null) throw new Exception();
            ConfirmationBasisInfo confirmationBasisInfo = new ConfirmationBasisInfo();
            UpicBeanUtils.copyProperties(confirmationBasis, confirmationBasisInfo);
            return confirmationBasisInfo;
        } catch (Exception e) {
            LOGGER.info("updateConfirmationBasis:节点依据查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }
}
