package com.upic.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.upic.condition.OperatorCondition;
import com.upic.condition.ResourceCondition;
import com.upic.condition.RoleCondition;
import com.upic.dto.*;
import com.upic.enums.OperatorStatusEnum;
import com.upic.service.*;

import com.upic.social.user.SocialUsers;
import com.upic.utils.UserUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private UserService userService;

    @Autowired
    private OperatorRoleService operatorRoleService;

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
     * 加载角色
     *
     * @param jobNum
     * @return
     */
    @GetMapping("/getRoleByJobNum")
    @ApiOperation("加载角色")
    public List<RoleInfo> getRoleByJobNum(String jobNum) {
        try {
            List<RoleInfo> roleInfoList = new ArrayList<>();
            List<Long> roleIdList = new ArrayList<>();
            List<OperatorRoleInfo> operatorRoleInfoList = operatorRoleService.getByJobNum(jobNum);
            for (OperatorRoleInfo operatorRoleInfo : operatorRoleInfoList) {
                roleIdList.add(operatorRoleInfo.getRoleId());
            }
            for (long roleId : roleIdList) {
                roleInfoList.add(roleService.getRoleById(roleId));
            }
            return roleInfoList;
        } catch (Exception e) {
            LOGGER.info("searchRole:" + e.getMessage());
        }
        return null;
    }

    @GetMapping("/getAllRoles")
    @ApiOperation("获取所有的角色")
    public List<RoleInfo> getAllRoles(RoleCondition roleCondition) {
        try {
            return roleService.getAll(roleCondition);
        } catch (Exception e) {
            LOGGER.info("getAllRoles:" + e.getMessage());
        }
        return null;
    }

    @GetMapping("/getMyRoles")
    @ApiOperation("获取我所有的角色")
    public List<RoleInfo> getMyRoles() {
        try {
            SocialUsers socialUsers = getUser();
            int rank = Integer.parseInt(socialUsers.getRank());
            return roleService.getByRank(rank);
        } catch (Exception e) {
            LOGGER.info("getAllRoles:" + e.getMessage());
        }
        return null;
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
     * 更新操作员（t_system_manager/permissions_manager.html）
     *
     * @param operatorInfo
     * @return
     */
    @PostMapping("/updateTheOperator")
    @ApiOperation("更新操作员")
    public String updateTheOperator(String operatorInfo) {
        try {
            OperatorInfo operator = JSON.parseObject(operatorInfo, OperatorInfo.class);
            OperatorInfo o = operatorService.getByJobNum(operator.getJobNum());
            o.setStatus(operator.getStatus());
            operatorService.updateOperator(o);
            return "SUCCESS";
        } catch (Exception e) {
            LOGGER.info("updateOperator:" + e.getMessage());
            return null;
        }
    }

    /**
     * 更新操作员角色关系
     *
     * @param roleIdList
     * @param jobNum
     * @return
     */
    @GetMapping("/updateOperatorRole")
    public String updateOperatorRole(String roleIdList, String jobNum) {
        try {
            List<Long> roleIdLists = JSONArray.parseArray(roleIdList, Long.class);

            List<OperatorRoleInfo> operatorRoleInfoList = operatorRoleService.getByJobNum(jobNum);

            for (OperatorRoleInfo operatorRoleInfo : operatorRoleInfoList) {
                int j = 0;
                for (int i = 0; i < roleIdLists.size(); i++) {
                    if (operatorRoleInfo.getRoleId() != roleIdLists.get(i)) {
                        j++;
                    }
                }
                if (j == roleIdLists.size()) {
                    operatorRoleService.deleteOperatorRole(operatorRoleInfo);
                }
            }

            for (int i = 0; i < roleIdLists.size(); i++) {
                OperatorRoleInfo operatorRoleInfo = operatorRoleService.getByJobNumAndRoleId(jobNum, roleIdLists.get(i));
                if (operatorRoleInfo == null) {
                    operatorRoleInfo = new OperatorRoleInfo();
                    operatorRoleInfo.setJobNum(jobNum);
                    operatorRoleInfo.setRoleId(roleIdLists.get(i));
                    operatorRoleInfo.setCreatTime(new Date());
                    operatorRoleService.addOperatorRole(operatorRoleInfo);
                }
            }
            return "SUCCESS";
        } catch (Exception e) {
            LOGGER.info("updateOperatorRole:" + e.getMessage());
        }
        return null;
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
     * 修改操作员状态
     *
     * @param jobNum
     * @return
     */
    @GetMapping("/changeOperatorStatus")
    public String changeOperatorStatus(String jobNum) {
        try {
            OperatorInfo operatorInfo = operatorService.getByJobNum(jobNum);
            if (operatorInfo.getStatus() == OperatorStatusEnum.NORMAL_CONDITION) {
                operatorInfo.setStatus(OperatorStatusEnum.FROZE);
            } else {
                operatorInfo.setStatus(OperatorStatusEnum.NORMAL_CONDITION);
            }
            operatorService.updateOperator(operatorInfo);
            return "SUCCESS";
        } catch (Exception e) {
            LOGGER.info("changeOperatorStatus:" + e.getMessage());
        }
        return null;
    }

    @GetMapping("/getAllRoleOperator")
    @ApiOperation("获取此角色下的所有操作员")
    public List<OperatorInfo> getAllRoleOperator(long roleId) {
        try {
            List<OperatorInfo> operatorInfoList = new ArrayList<>();
            List<OperatorRoleInfo> operatorRoleInfoList = operatorRoleService.getByRoleId(roleId);
            for (OperatorRoleInfo operatorRoleInfo : operatorRoleInfoList) {
                operatorInfoList.add(operatorService.getByJobNum(operatorRoleInfo.getJobNum()));
            }
            return operatorInfoList;
        } catch (Exception e) {
            LOGGER.info("getAllRoleOperator:" + e.getMessage());
        }
        return null;
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
     * 更新角色（t_systemManager/permissions_manage2.js updateRole）
     *
     * @param roleInfo
     * @return
     */
    @PostMapping("/updateMyRole")
    @ApiOperation("更新角色")
    public RoleInfo updateMyRole(String roleInfo) {
        try {
            RoleInfo role = JSON.parseObject(roleInfo, RoleInfo.class);
            RoleInfo r = roleService.getRoleById(role.getId());
            r.setRoleName(role.getRoleName());
            r.setContent(role.getContent());
            return roleService.updateRole(r);
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
            return resourceInfoList;
        } catch (Exception e) {
            LOGGER.info("listResource:" + e.getMessage());
            return null;
        }
    }

    /**
     * 加载菜单列表
     *
     * @param resourceCondition
     * @return
     */
    @GetMapping("/listAllResource")
    @ApiOperation("加载菜单列表")
    public List<ResourceInfo> listAllResource(ResourceCondition resourceCondition) {
        try {
            List<ResourceInfo> resourceInfoList = resourceService.listResource(resourceCondition);
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
    public String updateRoleResourceRelation(String roleResourceInfoList, long roleId) {
        List<RoleResourceInfo> parseArray = JSONArray.parseArray(roleResourceInfoList, RoleResourceInfo.class);
        List<RoleResourceInfo> beforeRoleResourceInfoList = null;
        String result = "ERROR";
        try {
            if (roleResourceInfoList != null && !roleResourceInfoList.isEmpty()) {
                beforeRoleResourceInfoList = roleResourceService.getByRoleId(roleId);
                result = roleResourceService.updateRoleResource(parseArray, beforeRoleResourceInfoList);
            }

            return result;
        } catch (Exception e) {
            LOGGER.info("addRoleResource:" + e.getMessage());
            return null;
        }
    }

    @GetMapping("/getResourceBySelf")
    @ApiOperation("获取自己的菜单列表")
    public List<ResourceInfo> getResourceBySelf() {
        try {
            List<ResourceInfo> resourceList = getUser().getResourceList();
//            List<ResourceInfo> resourceList = resourceService.getAll();
            return resourceList;
        } catch (Exception e) {
            LOGGER.info("getResourceBySelf:" + e.getMessage());
            return null;
        }
    }

    private SocialUsers getUser() {
        SocialUsers user= UserUtils.getUser();
        return user;
    }
}
