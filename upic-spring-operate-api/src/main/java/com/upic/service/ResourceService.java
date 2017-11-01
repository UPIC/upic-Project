package com.upic.service;

import com.upic.condition.ResourceCondition;
import com.upic.dto.ResourceInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface ResourceService {
    /**
     * 添加资源
     *
     * @param resourceInfo
     * @return
     */
    ResourceInfo addResource(ResourceInfo resourceInfo);

    /**
     * 更新资源
     *
     * @param resourceInfo
     * @return
     */
    ResourceInfo updateResource(ResourceInfo resourceInfo);

    /**
     * 查询资源（条件）
     *
     * @param resourceCondition
     * @param pageable
     * @return
     */
    Page<ResourceInfo> searchResource(ResourceCondition resourceCondition, Pageable pageable);

    /**
     * 根据ID查询资源
     *
     * @param resourceId
     * @return
     */
    ResourceInfo getResourceById(long resourceId);

    /**
     * 删除资源
     *
     * @param resourceId
     */
    void deleteResource(long resourceId);

    /**
     * 根据fatherId查询
     *
     * @param fatherId
     * @param pageable
     * @return
     */
    Page<ResourceInfo> findByFatherId(long fatherId, Pageable pageable);

    /**
     * 查询菜单列表
     *
     * @param resourceCondition
     * @return
     */
    List<ResourceInfo> listResource(ResourceCondition resourceCondition);

    /**
     * 根据角色ID查找菜单列表
     *
     * @param roleId
     * @return
     */
    List<ResourceInfo> listResourceByRoleId(long roleId);

    /**
     * 根据角色ID查找level为1的菜单列表
     * @param id
     * @return
     */
    List<ResourceInfo> getResourceBySelf(Long id);
}
