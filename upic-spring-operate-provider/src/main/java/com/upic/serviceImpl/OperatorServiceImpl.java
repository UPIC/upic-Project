package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.OperatorCondition;
import com.upic.dto.OperatorInfo;
import com.upic.po.Operator;
import com.upic.repository.OperatorRepository;
import com.upic.repository.Spec.OperatorSpec;
import com.upic.service.OperatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by zhubuqing on 2017/9/11.
 */
@Service("operatorService")
public class OperatorServiceImpl implements OperatorService {
    @Autowired
    private OperatorRepository operatorRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(OperatorServiceImpl.class);

    public OperatorInfo addOperator(OperatorInfo operatorInfo) {
        try {
            Operator operator = new Operator();
            UpicBeanUtils.copyProperties(operatorInfo, operator);
            operator = operatorRepository.save(operator);
            UpicBeanUtils.copyProperties(operator, operatorInfo);
            return operatorInfo;
        } catch (Exception e) {
            LOGGER.info("addOperator:管理员" + operatorInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public OperatorInfo updateOperator(OperatorInfo operatorInfo) {
        try {
            Operator operator = new Operator();
            UpicBeanUtils.copyProperties(operatorInfo, operator);
            operator = operatorRepository.saveAndFlush(operator);
            UpicBeanUtils.copyProperties(operator, operatorInfo);
            return operatorInfo;
        } catch (Exception e) {
            LOGGER.info("updateOperator:管理员" + operatorInfo.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<OperatorInfo> searchOperator(OperatorCondition operatorCondition, Pageable pageable) {
        Page<Operator> operatorPage = null;
        try {
            operatorPage = operatorRepository.findAll(new OperatorSpec(operatorCondition), pageable);
            return QueryResultConverter.convert(operatorPage, pageable, new AbstractDomain2InfoConverter<Operator, OperatorInfo>() {
                protected void doConvert(Operator domain, OperatorInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchOperator:管理员列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public OperatorInfo getOperatorByOperatorId(long operatorId) {
        try {
            OperatorInfo operatorInfo = new OperatorInfo();
            UpicBeanUtils.copyProperties(operatorRepository.findOne(operatorId), operatorInfo);
            return operatorInfo;
        } catch (Exception e) {
            LOGGER.info("getOperatorByOperatorId:管理员查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public void deleteByOperatorId(long operatorId) {
        try {
            operatorRepository.delete(operatorId);
        } catch (Exception e) {
            LOGGER.info("deleteByOperatorId:管理员删除失败。错误信息：" + e.getMessage());
        }
    }
}
