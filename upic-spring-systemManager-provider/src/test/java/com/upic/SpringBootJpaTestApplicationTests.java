package com.upic;

import com.upic.common.utils.redis.UpicRedisComponent;
import com.upic.condition.*;
import com.upic.dto.*;
import com.upic.po.Banner;
import com.upic.po.CategoryNode;
import com.upic.po.ProjectCategory;
import com.upic.po.SystemAdministrationLog;
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
    private BannerService bannerService;

    @Autowired
    private CategoryNodeService categoryNodeService;

    @Autowired
    private ConfirmationBasisService confirmationBasisService;

    @Autowired
    private ProjectCategoryService projectCategoryService;

    @Autowired
    private SystemAdministrationLogService systemAdministrationLogService;

    @Autowired
    private UpicRedisComponent redisComponent;

    /**
     * ************************************** Banner *****************************************
     */
    @Test
    public void testAddBanner() {
        for (int i = 0; i < 20; i++) {
            BannerInfo bannerInfo = new BannerInfo();
            bannerInfo.setCreatTime(new Date());
            bannerInfo.setPic("http://img.sootuu.com/vector/200801/072/0556.jpg");
            bannerInfo.setUrl("www.baidu.com");
            bannerInfo.setNum(i + 1);
            bannerService.addBanner(bannerInfo);
        }
    }

    @Test
    public void testUpdateBanner() {
        BannerInfo bannerInfo = bannerService.getBannerByBannerId(1L);
        bannerInfo.setUrl("www.taobao.com");
        bannerService.updateBanner(bannerInfo);
    }

    @Test
    public void testSearchBanner() {
        BannerCondition bannerCondition = new BannerCondition();
        PageRequest pageRequest = new PageRequest();
        Page<BannerInfo> bannerInfoPage = bannerService.searchBanner(bannerCondition, pageRequest);
        System.out.println(bannerInfoPage.getTotalElements());
        System.out.println(bannerInfoPage.getTotalPages());
        for (BannerInfo bannerInfo : bannerInfoPage.getContent()) {
            System.out.println(bannerInfo);
        }
    }

    @Test
    public void testDeleteBanner() {
        bannerService.deleteBanner(2);
    }

    /**
     * ************************************** CategoryNode *****************************************
     */
    @Test
    public void testAddCategoryNode() {
        for (int i = 0; i < 20; i++) {
            CategoryNodeInfo categoryNodeInfo = new CategoryNodeInfo();
            categoryNodeInfo.setLongNum(1);
            categoryNodeInfo.setWidthNum(1);
            categoryNodeInfo.setLevel(i + 1);
            categoryNodeInfo.setFatherId(i);
            if (i == 19) {
                categoryNodeInfo.setIsLeaf(1);
            } else {
                categoryNodeInfo.setIsLeaf(0);
            }
            categoryNodeService.addCategoryNode(categoryNodeInfo);
        }
    }

    @Test
    public void testUpdateCategoryNode() {
        for (int i = 0; i < 20; i++) {
            CategoryNodeInfo categoryNodeInfo = categoryNodeService.getCategoryNodeById(i + 1);
            if (categoryNodeInfo.getIsLeaf() == 1)
                categoryNodeInfo.setNodeContent("2.0");
            else
                categoryNodeInfo.setNodeContent("Happy" + i);
            categoryNodeInfo = categoryNodeService.updateCategoryNode(categoryNodeInfo);
        }
    }

    @Test
    public void testSearchCategoryNode() {
        CategoryNodeCondition categoryNodeCondition = new CategoryNodeCondition();
        categoryNodeCondition.setIsLeaf(1);
        PageRequest pageRequest = new PageRequest();
        Page<CategoryNodeInfo> categoryNodeInfoPage = categoryNodeService.searchCategoryNode(categoryNodeCondition, pageRequest);
        System.out.println(categoryNodeInfoPage.getTotalElements());
        System.out.println(categoryNodeInfoPage.getTotalPages());
        for (CategoryNodeInfo categoryNodeInfo : categoryNodeInfoPage.getContent()) {
            System.out.println(categoryNodeInfo);
        }
    }

    /**
     * ************************************** ConfirmationBasis *****************************************
     */
    @Test
    public void testAddConfirmationBasis() {
        ConfirmationBasisInfo confirmationBasisInfo = new ConfirmationBasisInfo();
        CategoryNodeInfo categoryNodeInfo = categoryNodeService.getCategoryNodeById(1L);
        confirmationBasisInfo.setCategoryNode(categoryNodeInfo);
        confirmationBasisInfo.setContent("offer");
        confirmationBasisService.addConfirmationBasis(confirmationBasisInfo);
    }

    @Test
    public void testUpdateConfirmationBasis() {
        ConfirmationBasisInfo confirmationBasisInfo = confirmationBasisService.getByConfirmationBasisId(1L);
        confirmationBasisInfo.setContent("o");
        confirmationBasisService.updateConfirmationBasis(confirmationBasisInfo);
    }

    @Test
    public void testSearchConfirmationBasis() {
        ConfirmationBasisCondition confirmationBasisCondition = new ConfirmationBasisCondition();
        PageRequest pageRequest = new PageRequest();
        Page<ConfirmationBasisInfo> confirmationBasisInfoPage = confirmationBasisService.searchConfirmationBasis(confirmationBasisCondition, pageRequest);
        System.out.println(confirmationBasisInfoPage.getTotalElements());
        System.out.println(confirmationBasisInfoPage.getTotalPages());
        for (ConfirmationBasisInfo confirmationBasisInfo : confirmationBasisInfoPage.getContent()) {
            System.out.println(confirmationBasisInfo);
        }
    }

    /**
     * ************************************** ConfirmationBasis *****************************************
     */
    @Test
    public void testAddProjectCategory() {
        for (int i = 0; i < 20; i++) {
            ProjectCategoryInfo projectCategoryInfo = new ProjectCategoryInfo();
            projectCategoryInfo.setCategoryName(i + "项目");
            projectCategoryInfo.setSubordinateSector(i + "部门");
            projectCategoryService.addProjectCategory(projectCategoryInfo);
        }
    }

    @Test
    public void testUpdateProjectCategory() {
        ProjectCategoryInfo projectCategoryInfo = projectCategoryService.getProjectCategoryById(1L);
        projectCategoryInfo.setCategoryName("zhubuqing");
        projectCategoryService.updateProjectCategory(projectCategoryInfo);
    }

    @Test
    public void testSearchProjectCategory() {
        ProjectCategoryCondition projectCategoryCondition = new ProjectCategoryCondition();
        PageRequest pageRequest = new PageRequest();
        Page<ProjectCategoryInfo> projectCategoryInfoPage = projectCategoryService.searchProjectCategory(projectCategoryCondition, pageRequest);
        System.out.println(projectCategoryInfoPage.getTotalElements());
        System.out.println(projectCategoryInfoPage.getTotalPages());
        for (ProjectCategoryInfo projectCategoryInfo : projectCategoryInfoPage.getContent()) {
            System.out.println(projectCategoryInfo);
        }
    }

    /**
     * ************************************** SystemAdministrationLog *****************************************
     */
    @Test
    public void testAddSystemAdministrationLog() {
        for (int i = 0; i < 20; i++) {
            SystemAdministrationLogInfo systemAdministrationLogInfo = new SystemAdministrationLogInfo();
            systemAdministrationLogInfo.setOperation("Operation" + i);
            systemAdministrationLogInfo.setOperatorName("OperatorName" + i);
            systemAdministrationLogInfo.setOperatorNum("OperatorNum" + i);
            systemAdministrationLogService.addSystemAdministrationLog(systemAdministrationLogInfo);
        }
    }

    @Test
    public void testUpdateSystemAdministrationLog() {
        SystemAdministrationLogInfo systemAdministrationLogInfo = systemAdministrationLogService.getBySystemAdministrationLogId(1L);
        systemAdministrationLogInfo.setOperatorNum("1");
        systemAdministrationLogService.updateSystemAdministrationLog(systemAdministrationLogInfo);
    }

    @Test
    public void testSearchSystemAdministrationLog() {
        SystemAdministrationLogCondition systemAdministrationLogCondition = new SystemAdministrationLogCondition();
        PageRequest pageRequest = new PageRequest();
        Page<SystemAdministrationLogInfo> systemAdministrationLogInfoPage = systemAdministrationLogService.searchSystemAdministrationLog(systemAdministrationLogCondition, pageRequest);
        System.out.println(systemAdministrationLogInfoPage.getTotalElements());
        System.out.println(systemAdministrationLogInfoPage.getTotalPages());
        for (SystemAdministrationLogInfo systemAdministrationLogInfo : systemAdministrationLogInfoPage.getContent()) {
            System.out.println(systemAdministrationLogInfo);
        }
    }

    /************************************审批流程！！！！************************************/
    @Test
    public void testGetCategoryNameBySubordinateSectorOtherName() {
        String subordinateSectorOtherName = "5";
        List<String> a = projectCategoryService.getCategoryNameBySubordinateSectorOtherName(subordinateSectorOtherName);
        System.out.println(a.toString());
    }
}
