package com.upic.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.upic.condition.OperatorCondition;
import com.upic.condition.ResourceCondition;
import com.upic.condition.RoleCondition;
import com.upic.dto.OperatorInfo;
import com.upic.dto.ResourceInfo;
import com.upic.dto.RoleInfo;
import com.upic.enums.OperatorStatusEnum;
import com.upic.service.OperatorService;
import com.upic.service.ResourceService;
import com.upic.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return resourceService.searchResource(resourceCondition, pageable);
        } catch (Exception e) {
            LOGGER.info("searchResource:" + e.getMessage());
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
            return resourceService.updateResource(resourceInfo);
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
    @GetMapping
    @ApiOperation("添加菜单")
    public ResourceInfo addResource(ResourceInfo resourceInfo) {
        try {
            return resourceService.addResource(resourceInfo);
        } catch (Exception e) {
            LOGGER.info("addResource:" + e.getMessage());
            return null;
        }
    }

//    @GetMapping
//    @ApiOperation("删除菜单")
}
