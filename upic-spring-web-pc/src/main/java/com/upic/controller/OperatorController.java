package com.upic.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSONArray;
import com.upic.condition.OperatorCondition;
import com.upic.condition.ResourceCondition;
import com.upic.condition.RoleCondition;
import com.upic.dto.*;
import com.upic.enums.OperatorStatusEnum;
import com.upic.service.OperatorService;
import com.upic.service.ResourceService;
import com.upic.service.RoleResourceService;
import com.upic.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operator")
public class OperatorController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(OperatorController.class);

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 加载操作员
     *
     * @param pageable
     * @param operatorCondition
     * @return
     */
    @GetMapping("/searchOperator")
    @ApiOperation("加载所有操作员")
    public Page<OperatorInfo> searchOperator(@PageableDefault(size = 10) Pageable pageable, OperatorCondition operatorCondition) {
        try {
            return operatorService.searchOperator(operatorCondition, pageable);
        } catch (Exception e) {
            LOGGER.info("searchOperator:" + e.getMessage());
            return null;
        }
    }

    /**
     * 加载角色
     *
     * @param pageable
     * @param roleCondition
     * @return
     */
    @GetMapping("/searchRole")
    @ApiOperation("加载角色")
    public Page<RoleInfo> searchRole(@PageableDefault(size = 10) Pageable pageable, RoleCondition roleCondition) {
        try {
            return roleService.searchRole(roleCondition, pageable);
        } catch (Exception e) {
            LOGGER.info("searchRole:" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据角色查找操作员
     *
     * @param pageable
     * @param roleId
     * @return
     */
    @GetMapping("/getOperatorByRole")
    @ApiOperation("根据角色查找操作员")
    public Page<OperatorInfo> getOperatorByRole(@PageableDefault(size = 10) Pageable pageable, long roleId) {
        try {
            return operatorService.getOperatorByRole(roleId, pageable);
        } catch (Exception e) {
            LOGGER.info("getOperatorByRole:" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据ID查看详情
     *
     * @param operatorId
     * @return
     */
    @GetMapping("/operatorId")
    @ApiOperation("根据ID查看详情")
    public OperatorInfo getById(long operatorId) {
        try {
            return operatorService.getOperatorByOperatorId(operatorId);
        } catch (Exception e) {
            LOGGER.info("getById:" + e.getMessage());
            return null;
        }
    }

    /**
     * 更新操作员
     *
     * @param operatorInfo
     * @return
     */
    @GetMapping("/updateOperator")
    @ApiOperation("更新操作员")
    public OperatorInfo updateOperator(OperatorInfo operatorInfo) {
        try {
            return operatorService.updateOperator(operatorInfo);
        } catch (Exception e) {
            LOGGER.info("updateOperator:" + e.getMessage());
            return null;
        }
    }

    /**
     * 冻结操作员
     *
     * @param jobNum
     * @param status
     * @return
     */
    @GetMapping("/freezeOperator")
    @ApiOperation("冻结操作员")
    public OperatorInfo freezeOperator(String jobNum, OperatorStatusEnum status) {
        try {
            OperatorInfo operatorInfo = operatorService.getByJobNum(jobNum);
            operatorInfo.setStatus(status);
            return operatorService.updateOperator(operatorInfo);
        } catch (Exception e) {
            LOGGER.info("freezeOperator:" + e.getMessage());
            return null;
        }
    }

    /**
     * 更新角色
     *
     * @param roleInfo
     * @return
     */
    @GetMapping("/updateRole")
    @ApiOperation("更新角色")
    public RoleInfo updateRole(RoleInfo roleInfo) {
        try {
            return roleService.updateRole(roleInfo);
        } catch (Exception e) {
            LOGGER.info("updateRole:" + e.getMessage());
            return null;
        }
    }

    /**
     * 加载菜单
     *
     * @param pageable
     * @param resourceCondition
     * @return
     */
    @GetMapping("/searchResource")
    @ApiOperation("加载菜单")
    public Page<ResourceInfo> searchResource(@PageableDefault(size = 10) Pageable pageable, ResourceCondition resourceCondition) {
        try {
            Page<ResourceInfo> resourceInfoPage = resourceService.searchResource(resourceCondition, pageable);
            System.out.println(resourceInfoPage.toString());
            return resourceInfoPage;
        } catch (Exception e) {
            LOGGER.info("searchResource:" + e.getMessage());
            return null;
        }
    }

    /**
     * 加载菜单ById
     *
     * @return
     */
    @GetMapping("/getResourceById")
    @ApiOperation("加载菜单列表")
    public ResourceInfo getResourceById(long id) {
        try {
            ResourceInfo resourceInfo = resourceService.getResourceById(id);
            System.out.println(resourceInfo.toString());
            return resourceInfo;
        } catch (Exception e) {
            LOGGER.info("getResourceById:" + e.getMessage());
            return null;
        }
    }

    /**
     * 加载菜单列表
     *
     * @param resourceCondition
     * @return
     */
    @GetMapping("/listResource")
    @ApiOperation("加载菜单列表")
    public List<ResourceInfo> listResource(ResourceCondition resourceCondition) {
        try {
            List<ResourceInfo> resourceInfoList = resourceService.listResource(resourceCondition);
            System.out.println("1234wqwedu1dbjhqwebduqyb3dbwed" + resourceCondition);
            System.out.println("ksdc1asdcnasdncasndckasdcasdcn" + resourceInfoList.toString());
            return resourceInfoList;
        } catch (Exception e) {
            LOGGER.info("listResource:" + e.getMessage());
            return null;
        }
    }

    /**
     * 加载菜单列表
     *
     * @return
     */
    @GetMapping("/listResourceByRoleId")
    @ApiOperation("加载菜单列表")
    public List<ResourceInfo> listResourceByRoleId(long roleId) {
        try {
            List<ResourceInfo> resourceInfoList = resourceService.listResourceByRoleId(roleId);
            return resourceInfoList;
        } catch (Exception e) {
            LOGGER.info("listResource:" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据fatherId查询
     *
     * @param pageable
     * @param fatherId
     * @return
     */
    @GetMapping("/findByFatherId")
    @ApiOperation("根据fatherId查询")
    public Page<ResourceInfo> findByFatherId(@PageableDefault(size = 10) Pageable pageable, long fatherId) {
        try {
            return resourceService.findByFatherId(fatherId, pageable);
        } catch (Exception e) {
            LOGGER.info("findByFatherId:" + e.getMessage());
            return null;
        }
    }

    /**
     * 更新菜单
     *
     * @param resourceInfo
     * @return
     */
    @GetMapping("/updateResource")
    @ApiOperation("更新菜单")
    public ResourceInfo updateResource(ResourceInfo resourceInfo) {
        try {
            ResourceInfo r = resourceService.getResourceById(resourceInfo.getId());
            r.setResourceName(resourceInfo.getResourceName());
            r.setUrl(resourceInfo.getUrl());
            r = resourceService.updateResource(r);
            System.out.println(r.toString());
            return r;
        } catch (Exception e) {
            LOGGER.info("updateResource:" + e.getMessage());
            return null;
        }
    }

    /**
     * 添加菜单
     *
     * @param resourceInfo
     * @return
     */
    @GetMapping("/addResource")
    @ApiOperation("添加菜单")
    public ResourceInfo addResource(ResourceInfo resourceInfo) {
        try {
            ResourceInfo r = resourceService.addResource(resourceInfo);
            System.out.println(r.toString());
            return r;
        } catch (Exception e) {
            LOGGER.info("addResource:" + e.getMessage());
            return null;
        }
    }

    /**
     * 添加角色菜单关系
     *
     * @return
     */
	@PostMapping("/updateRoleResourceRelation")
    @ApiOperation("更新角色菜单关系列表")
    public String updateRoleResourceRelation(  String roleResourceInfoList, long roleId) {
		List<RoleResourceInfo> parseArray = JSONArray.parseArray(roleResourceInfoList,RoleResourceInfo.class);
    	List<RoleResourceInfo> beforeRoleResourceInfoList = null;
    	String result = "ERROR";
    	try {
        	if(roleResourceInfoList!=null && !roleResourceInfoList.isEmpty()) {
        		beforeRoleResourceInfoList = roleResourceService.getByRoleId(roleId);
        		result = roleResourceService.updateRoleResource(parseArray,beforeRoleResourceInfoList);
        	}
            
            return result;
        } catch (Exception e) {
            LOGGER.info("addRoleResource:" + e.getMessage());
            return null;
        }
    }

//    @GetMapping
//    @ApiOperation("删除菜单")
}
