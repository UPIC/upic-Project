package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.OperatorRoleCondition;
import com.upic.dto.OperatorRoleInfo;
import com.upic.po.OperatorRole;
import com.upic.repository.OperatorRoleRepository;
import com.upic.repository.Spec.OperatorRoleSpec;
import com.upic.service.OperatorRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by zhubuqing on 2017/9/11.
 */
@Service("operatorRoleService")
public class OperatorRoleServiceImpl implements OperatorRoleService {
    @Autowired
    private OperatorRoleRepository operatorRoleRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(OperatorRoleServiceImpl.class);

    public OperatorRoleInfo addOperatorRole(OperatorRoleInfo operatorRoleInfo) {
        try {
            OperatorRole operatorRole = new OperatorRole();
            UpicBeanUtils.copyProperties(operatorRoleInfo, operatorRole);
            operatorRole = operatorRoleRepository.save(operatorRole);
            UpicBeanUtils.copyProperties(operatorRole, operatorRoleInfo);
            return operatorRoleInfo;
        } catch (Exception e) {
            LOGGER.info("deleteOperatorRole:管理员角色关系" + operatorRoleInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<OperatorRoleInfo> searchOperatorRole(OperatorRoleCondition operatorRoleCondition, Pageable pageable) {
        Page<OperatorRole> operatorRolePage = null;
        try {
            operatorRolePage = operatorRoleRepository.findAll(new OperatorRoleSpec(operatorRoleCondition), pageable);
            return QueryResultConverter.convert(operatorRolePage, pageable, new AbstractDomain2InfoConverter<OperatorRole, OperatorRoleInfo>() {
                protected void doConvert(OperatorRole domain, OperatorRoleInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchOperatorRole:管理员角色关系列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public void deleteOperatorRole(OperatorRoleInfo operatorRoleInfo) {
        try {
            operatorRoleRepository.delete(operatorRoleInfo.getId());
        } catch (Exception e) {
            LOGGER.info("deleteOperatorRole:管理员角色关系删除失败。错误信息：" + e.getMessage());
        }
    }
}
