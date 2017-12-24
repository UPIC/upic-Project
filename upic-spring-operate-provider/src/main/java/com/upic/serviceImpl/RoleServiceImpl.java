package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.RoleCondition;
import com.upic.dto.RoleInfo;
import com.upic.po.Role;
import com.upic.repository.RoleRepository;
import com.upic.repository.Spec.RoleSpec;
import com.upic.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhubuqing on 2017/9/11.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    public RoleInfo addRole(RoleInfo roleInfo) {
        try {
            Role role = new Role();
            UpicBeanUtils.copyProperties(roleInfo, role);
            role = roleRepository.save(role);
            UpicBeanUtils.copyProperties(role, roleInfo);
            return roleInfo;
        } catch (Exception e) {
            LOGGER.info("addRole:角色" + roleInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public RoleInfo updateRole(RoleInfo roleInfo) {
        try {
            Role role = new Role();
            UpicBeanUtils.copyProperties(roleInfo, role);
            role = roleRepository.saveAndFlush(role);
            UpicBeanUtils.copyProperties(roleInfo, role);
            return roleInfo;
        } catch (Exception e) {
            LOGGER.info("updateRole:角色" + roleInfo.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<RoleInfo> searchRole(RoleCondition roleCondition, Pageable pageable) {
        Page<Role> rolePage = null;
        try {
            rolePage = roleRepository.findAll(new RoleSpec(roleCondition), pageable);
            return QueryResultConverter.convert(rolePage, pageable, new AbstractDomain2InfoConverter<Role, RoleInfo>() {
                protected void doConvert(Role domain, RoleInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchRole:角色列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public RoleInfo getRoleById(long roleId) {
        try {
            Role role = roleRepository.findOne(roleId);
            RoleInfo roleInfo = new RoleInfo();
            UpicBeanUtils.copyProperties(role, roleInfo);
            return roleInfo;
        } catch (Exception e) {
            LOGGER.info("getRoleById:角色查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public void deleteRole(long roleId) {
        try {
            roleRepository.delete(roleId);
        } catch (Exception e) {
            LOGGER.info("deleteRole:角色删除失败。错误信息：" + e.getMessage());
        }
    }

    @Override
    public List<RoleInfo> getRoleByJobNum(String userNum) {
        List<Role> roleList = new ArrayList<>();
        List<RoleInfo> roleInfoList = new ArrayList<>();
        try {
            roleList = roleRepository.getRoleByJobNum(userNum);
            for (Role role : roleList) {
                RoleInfo roleInfo = new RoleInfo();
                UpicBeanUtils.copyProperties(role, roleInfo);
                roleInfoList.add(roleInfo);
            }
            return roleInfoList;
        } catch (Exception e) {
            LOGGER.info("getRoleByJobNum：" + e.getMessage());
            return null;
        }
    }

    @Override
    public RoleInfo getRoleByalins(String alinsNum) {
        try {
            Role role = roleRepository.getRoleByAliasName(alinsNum);
            RoleInfo roleInfo = new RoleInfo();
            UpicBeanUtils.copyProperties(role, roleInfo);
            return roleInfo;
        } catch (Exception e) {
            LOGGER.info("getRoleByalins:角色查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<RoleInfo> getAll(RoleCondition roleCondition) {
        List<Role> roleList = new ArrayList<>();
        try {
            roleList = roleRepository.findAll(new RoleSpec(roleCondition));
            return QueryResultConverter.convert(roleList, new AbstractDomain2InfoConverter<Role, RoleInfo>() {
                @Override
                protected void doConvert(Role domain, RoleInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("getAll。错误信息：" + e.getMessage());
        }
        return null;
    }
}
