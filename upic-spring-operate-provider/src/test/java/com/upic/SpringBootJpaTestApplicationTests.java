package com.upic;

import com.upic.condition.*;
import com.upic.dto.*;
import com.upic.po.OperatorRole;
import com.upic.po.RoleCheckStatus;
import com.upic.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaTestApplication.class)
public class SpringBootJpaTestApplicationTests {
    @Autowired
    private OperatorRoleService operatorRoleService;

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private RoleCheckStatusService roleCheckStatusService;

    /**
     * ************************************** Operator *****************************************
     */
    @Test
    public void testAddOperator() {
        for (int i = 0; i < 20; i++) {
            OperatorInfo operatorInfo = new OperatorInfo();
            operatorInfo.setJobNum("JobNum" + i);
            operatorInfo.setUsername("UserName" + i);
            operatorInfo.setPassword("Password" + i);
            operatorInfo.setEmail("12345678" + i + "@qq.com");
            operatorInfo.setPic("https://b-ssl.duitang.com/uploads/item/201609/19/20160919020343_nkPjR.jpeg");
            operatorInfo.setPhone("1234567890" + i);
            operatorInfo.setIdcard("12345676543212345" + i);
            operatorService.addOperator(operatorInfo);
        }
    }

    @Test
    public void testUpdateOperator() {
        OperatorInfo operatorInfo = operatorService.getOperatorByOperatorId(1L);
        operatorInfo.setIdcard("1");
        operatorService.updateOperator(operatorInfo);
    }

    @Test
    public void testSearchOperator() {
        OperatorCondition operatorCondition = new OperatorCondition();
        PageRequest pageRequest = new PageRequest();
        Page<OperatorInfo> operatorInfoPage = operatorService.searchOperator(operatorCondition, pageRequest);
        System.out.println(operatorInfoPage.getTotalElements());
        System.out.println(operatorInfoPage.getTotalPages());
        for (OperatorInfo operatorInfo : operatorInfoPage.getContent()) {
            System.out.println(operatorInfo);
        }
    }

    @Test
    public void testDeleteByOperatorId() {
        operatorService.deleteByOperatorId(10L);
    }

    /**
     * ************************************** Role *****************************************
     */
    @Test
    public void testAddRole() {
        for (int i = 2; i < 21; i++) {
            RoleInfo roleInfo = new RoleInfo();
            roleInfo.setRoleName("roleName" + i);
            roleService.addRole(roleInfo);
        }
    }

    @Test
    public void testUpdateRole() {
        RoleInfo roleInfo = roleService.getRoleById(1L);
        roleInfo.setRoleName("1");
        roleService.updateRole(roleInfo);
    }

    @Test
    public void testSearchRole() {
        RoleCondition roleCondition = new RoleCondition();
        PageRequest pageRequest = new PageRequest();
        Page<RoleInfo> roleInfoPage = roleService.searchRole(roleCondition, pageRequest);
        System.out.println(roleInfoPage.getTotalElements());
        System.out.println(roleInfoPage.getTotalPages());
        for (RoleInfo roleInfo : roleInfoPage.getContent()) {
            System.out.println(roleInfo);
        }
    }

    @Test
    public void testDeleteRole() {
        roleService.deleteRole(10L);
    }

    /**
     * ************************************** Resource *****************************************
     */
    @Test
    public void testAddResource() {
        for (int i = 0; i < 20; i++) {
            ResourceInfo resourceInfo = new ResourceInfo();
            resourceInfo.setResourceNum(new Date().getTime() + i + "");
            resourceInfo.setResourceName("菜单名称" + (i + 1));
            resourceInfo.setUrl("www.baidu.com");
            resourceInfo.setLevel(i + 1);
            resourceInfo.setFatherId(i);
            if (i == 19)
                resourceInfo.setIsLeaf(1);
            else
                resourceInfo.setIsLeaf(0);
            resourceService.addResource(resourceInfo);
        }
    }

    @Test
    public void testUpdateResource() {
        ResourceInfo resourceInfo = resourceService.getResourceById(1L);
        resourceInfo.setResourceName("帅气朱步青");
        resourceService.updateResource(resourceInfo);
    }

    @Test
    public void testSearchResource() {
        ResourceCondition resourceCondition = new ResourceCondition();
        PageRequest pageRequest = new PageRequest();
        Page<ResourceInfo> resourceInfoPage = resourceService.searchResource(resourceCondition, pageRequest);
        System.out.println(resourceInfoPage.getTotalElements());
        System.out.println(resourceInfoPage.getTotalPages());
        for (ResourceInfo resourceInfo : resourceInfoPage.getContent()) {
            System.out.println(resourceInfo);
        }
    }

    @Test
    public void testDeleteResource() {
        resourceService.deleteResource(20L);
    }

    /**
     * ************************************** OperatorRole *****************************************
     */
//    @Test
//    public void testAddOperatorRole() {
//        for (long i = 5; i < 8; i++) {
//            OperatorRoleInfo operatorRoleInfo = new OperatorRoleInfo();
//            OperatorInfo operatorInfo = operatorService.getOperatorByOperatorId(i);
//            RoleInfo roleInfo = roleService.getRoleById(i);
//            operatorRoleInfo.setOperator(operatorInfo);
//            operatorRoleInfo.setRole(roleInfo);
//            operatorRoleService.addOperatorRole(operatorRoleInfo);
//        }
//    }

    @Test
    public void testSearchOperatorRole() {
        OperatorRoleCondition operatorRoleCondition = new OperatorRoleCondition();
        PageRequest pageRequest = new PageRequest();
        Page<OperatorRoleInfo> operatorRoleInfoPage = operatorRoleService.searchOperatorRole(operatorRoleCondition, pageRequest);
        System.out.println(operatorRoleInfoPage.getTotalElements());
        System.out.println(operatorRoleInfoPage.getTotalPages());
        for (OperatorRoleInfo operatorRoleInfo : operatorRoleInfoPage.getContent()) {
            System.out.println(operatorRoleInfo);
        }
    }

    @Test
    public void testDeleteOperatoeRole() {
        OperatorRoleInfo operatorRoleInfo = new OperatorRoleInfo();
        operatorRoleInfo.setId(1L);
        operatorRoleService.deleteOperatorRole(operatorRoleInfo);
    }

    /**
     * ************************************** RoleResource *****************************************
     */
//    @Test
//    public void testAddRoleResource() {
//        for (long i = 15; i < 18; i++) {
//            RoleInfo roleInfo = roleService.getRoleById(i);
//            ResourceInfo resourceInfo = resourceService.getResourceById(i);
//            RoleResourceInfo roleResourceInfo = new RoleResourceInfo();
//            roleResourceInfo.setRole(roleInfo);
//            roleResourceInfo.setResource(resourceInfo);
//            roleResourceService.addRoleResource(roleResourceInfo);
//        }
//    }

    @Test
    public void testSearchRoleResource() {
        RoleResourceCondition roleResourceCondition = new RoleResourceCondition();
        PageRequest pageRequest = new PageRequest();
        Page<RoleResourceInfo> roleResourceInfoPage = roleResourceService.searchRoleResource(roleResourceCondition, pageRequest);
        System.out.println(roleResourceInfoPage.getTotalElements());
        System.out.println(roleResourceInfoPage.getTotalPages());
        for (RoleResourceInfo roleResourceInfo : roleResourceInfoPage.getContent()) {
            System.out.println(roleResourceInfo);
        }
    }

    @Test
    public void testDeleteRoleResource() {
        RoleResourceInfo roleResourceInfo = new RoleResourceInfo();
        roleResourceInfo.setId(1L);
        roleResourceService.deleteRoleResource(roleResourceInfo);
    }

    /*********************************************审批过程！！！*********************************************/
    @Test
    public void testGetCheckStatusEnumName() {
        long id = 1;
        List<String> a = roleCheckStatusService.getCheckStatusEnumName(id);
        System.out.println(a.toString());
    }
}
