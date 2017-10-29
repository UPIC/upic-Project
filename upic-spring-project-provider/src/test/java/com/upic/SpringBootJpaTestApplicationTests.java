package com.upic;

import com.upic.condition.ProjectCondition;
import com.upic.dto.AdviceInfo;
import com.upic.dto.ProjectInfo;
import com.upic.enums.ImplementationProcessEnum;
import com.upic.enums.ProjectAddWayEnum;
import com.upic.enums.RankEnum;
import com.upic.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaTestApplication.class)
public class SpringBootJpaTestApplicationTests {

    @Autowired
    private ProjectService projectService;

    @Test
    public void testAddProject() {
        for (int i = 0; i < 50; i++) {
            ProjectInfo p = new ProjectInfo();
            p.setContent("测试" + i);
            p.setDeclareUnit("团委");
            p.setProjectNum("xm123456789" + i);
            if (i < 5) {
                p.setRankEnum(RankEnum.SCHOOL);
            } else if (i < 15) {
                p.setRankEnum(RankEnum.COLLEGE);
                if (i < 10)
                    p.setUnit("信息工程学院");
                else
                    p.setUnit("艺术与传媒学院");
            } else if (i < 30) {
                p.setRankEnum(RankEnum.MAJOR);
                if (i < 25)
                    p.setUnit("计算机科学与技术");
                else
                    p.setUnit("专业英语");
            } else {
                p.setRankEnum(RankEnum.CLAZZ);
                p.setUnit("14计科1班");
            }
            if (i < 10) {
                p.setImplementationProcess(ImplementationProcessEnum.AUDITED);
            } else if (i < 15) {
                p.setImplementationProcess(ImplementationProcessEnum.COMPLETED);
            } else if (i < 20) {
                p.setImplementationProcess(ImplementationProcessEnum.HAVE_IN_HAND);
            } else if (i < 30) {
                p.setImplementationProcess(ImplementationProcessEnum.IN_AUDIT);
            } else if (i < 40) {
                p.setImplementationProcess(ImplementationProcessEnum.NOT_PASS);
            } else if (i < 50) {
                p.setImplementationProcess(ImplementationProcessEnum.SAVED);
            }
            if (i < 25) {
                p.setProjectAddWay(ProjectAddWayEnum.AUTO_ADD);
            } else {
                p.setProjectAddWay(ProjectAddWayEnum.MANUAL_ADDITION);
            }
            p.setMaximum(2 + i);
            p.setProjectName("projectName" + i);
            p.setStartTime(new Date());
            p.setEndTime(new Date());
            p.setSignUpStartTime(new Date());
            p.setSignUpEndTime(new Date());
            p.setIntegral(2.0);
            projectService.addProject(p);
        }
        System.out.println();
    }

    @Test
    public void testChangeProjectStatus() {
        ProjectInfo p = new ProjectInfo();
        p.setId(3L);
        AdviceInfo a = new AdviceInfo();
        a.setAdvice("通过");
        a.setOperator("DTZ");
        a.setProject(p);
        a.setOperatorNum("1422110108");
        System.out.println(projectService.changeProjectStatus(a));
    }

    @Test
    public void searchProject() {
        ProjectCondition projectCondition = new ProjectCondition();
        PageRequest p = new PageRequest();
        projectCondition.setImplementationProcess(ImplementationProcessEnum.IN_AUDIT);
        Page<ProjectInfo> page = projectService.searchProject(projectCondition, p);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
//        page.getContent().forEach(x->System.out.println());
        for (ProjectInfo p1 : page.getContent()) {
            System.out.println(p1);
        }
    }

    @Test
    public void testGetProjectByNum() {
        System.out.println(projectService.getProjectByNum("xm1234567890"));
    }

    @Test
    public void testUpdateProject() {
        ProjectInfo p = projectService.getProjectByNum("1");
        if (p == null) {

        } else {
            p.setMaximum(5);
            System.out.println(projectService.updateProject(p));
        }
    }

    @Test
    public void testGetOnlineProject() {
        PageRequest pageRequest = new PageRequest();
        Page<ProjectInfo> projectInfoPage = projectService.getOnlineProject(RankEnum.COLLEGE, "信息工程学院", pageRequest);
        System.out.println(projectInfoPage.getTotalElements());
        System.out.println(projectInfoPage.getTotalPages());
        for (ProjectInfo projectInfo : projectInfoPage.getContent()) {
            System.out.println(projectInfo);
        }
    }

    @Test
    public void testProjectSearchBar() {
        String keyword = "1";
        PageRequest pageRequest = new PageRequest();
        Page<ProjectInfo> projectInfoPage = projectService.projectSearchBar(keyword, pageRequest);
        if (projectInfoPage != null) {
            System.out.println(projectInfoPage.getTotalElements());
            System.out.println(projectInfoPage.getTotalPages());
            for (ProjectInfo projectInfo : projectInfoPage.getContent()) {
                System.out.println(projectInfo);
            }
        }
    }

    @Test
    public void testGetTeacherAllWorkloadSummary() {
        String teacherNum = "101045";
        System.out.println(projectService.getTeacherAllWorkloadSummary(teacherNum));
    }

    @Test
    public void testListProject() {
        ProjectCondition p = new ProjectCondition();
        List<Object> o = projectService.listProject(p);
        System.out.println(o.toString());
    }

    @Test
    public void testGetProjectBySql() {
        List<String> statusList = new ArrayList<>();
        List<String> projectCategoryList = new ArrayList<>();
        PageRequest pageRequest = new PageRequest();
        statusList.add("HAVE_IN_HAND");
        statusList.add("SAVED");
        projectCategoryList.add("社会实践积分（必修积分）");
        projectCategoryList.add("社会实践积分（必修积分）1");

        Page<ProjectInfo> projectInfoPage = projectService.getProjectBySql(statusList, projectCategoryList, pageRequest);
        if (projectInfoPage != null) {
            System.out.println(projectInfoPage.getTotalElements() + "");
            System.out.println(projectInfoPage.getTotalPages());
            for (ProjectInfo projectInfo : projectInfoPage.getContent()) {
                System.out.println(projectInfo);
            }
        }
    }
}
