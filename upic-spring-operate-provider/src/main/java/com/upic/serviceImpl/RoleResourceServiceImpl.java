package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.RoleResourceCondition;
import com.upic.dto.RoleResourceInfo;
import com.upic.po.Role;
import com.upic.po.RoleResource;
import com.upic.repository.RoleResourceRepository;
import com.upic.repository.Spec.RoleResourceSpec;
import com.upic.service.RoleResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhubuqing on 2017/9/11.
 */
@Service("roleResourceService")
public class RoleResourceServiceImpl implements RoleResourceService {
    @Autowired
    private RoleResourceRepository roleResourceRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(RoleResourceServiceImpl.class);

    public RoleResourceInfo addRoleResource(RoleResourceInfo roleResourceInfo) {
        try {
            RoleResource roleResource = new RoleResource();
            UpicBeanUtils.copyProperties(roleResourceInfo, roleResource);
            roleResource = roleResourceRepository.save(roleResource);
            UpicBeanUtils.copyProperties(roleResource, roleResourceInfo);
            return roleResourceInfo;
        } catch (Exception e) {
            LOGGER.info("addRoleResource:角色资源关系" + roleResourceInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<RoleResourceInfo> searchRoleResource(RoleResourceCondition roleResourceCondition, Pageable pageable) {
        Page<RoleResource> roleResourcePage = null;
        try {
            roleResourcePage = roleResourceRepository.findAll(new RoleResourceSpec(roleResourceCondition), pageable);
            return QueryResultConverter.convert(roleResourcePage, pageable,
                    new AbstractDomain2InfoConverter<RoleResource, RoleResourceInfo>() {
                        protected void doConvert(RoleResource domain, RoleResourceInfo info) throws Exception {
                            UpicBeanUtils.copyProperties(domain, info);
                        }
                    });
        } catch (Exception e) {
            LOGGER.info("searchRoleResource:角色资源关系列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public void deleteRoleResource(RoleResourceInfo roleResourceInfo) {
        try {
            roleResourceRepository.delete(roleResourceInfo.getId());
        } catch (Exception e) {
            LOGGER.info("deleteRoleResource:角色资源关系删除失败。错误信息：" + e.getMessage());
        }
    }

    @Override
    public List<RoleResourceInfo> findAll(RoleResourceCondition roleResourceCondition) {
        try {
            List<RoleResource> findAll = roleResourceRepository.findAll(new RoleResourceSpec(roleResourceCondition));
            return QueryResultConverter.convert(findAll,
                    new AbstractDomain2InfoConverter<RoleResource, RoleResourceInfo>() {
                        protected void doConvert(RoleResource domain, RoleResourceInfo info) throws Exception {
                            UpicBeanUtils.copyProperties(domain, info);
                        }
                    });
        } catch (Exception e) {
            LOGGER.info("RoleResourceInfo:角色资源关系删除失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    @Override
    public String addAll(List<RoleResourceInfo> roleResourceInfoList) {
        try {
            List<RoleResource> roleResourceList = new ArrayList<>();
            for (RoleResourceInfo roleResourceInfo : roleResourceInfoList) {
                RoleResource roleResource = new RoleResource();
                UpicBeanUtils.copyProperties(roleResourceInfo, roleResource);
                roleResourceList.add(roleResource);
            }
            Iterable<RoleResource> it = (Iterable<RoleResource>) roleResourceList.iterator();//把元素导入迭代器
            roleResourceRepository.save(it);
            return "SUCCESS";
        } catch (Exception e) {
            LOGGER.info("addAll：" + e.getMessage());
            return "ERROR";
        }
    }
}
