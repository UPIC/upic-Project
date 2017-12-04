package com.upic.serviceimpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.common.utils.redis.UpicRedisComponent;
import com.upic.condition.AdviceCondition;
import com.upic.condition.ProjectCondition;
import com.upic.dto.AdviceInfo;
import com.upic.dto.ProjectInfo;
import com.upic.enums.ImplementationProcessEnum;
import com.upic.enums.RankEnum;
import com.upic.po.Advice;
import com.upic.po.Project;
import com.upic.repository.AdviceRepository;
import com.upic.repository.ProjectRepository;
import com.upic.repository.spec.AdviceSpec;
import com.upic.repository.spec.ProjectSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.upic.service.ProjectService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;
import sun.security.provider.MD5;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dtz
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AdviceRepository adviceRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;


    public ProjectInfo addProject(ProjectInfo projectInfo) {
        try {
            Project p = new Project();
            UpicBeanUtils.copyProperties(projectInfo, p);
            p = projectRepository.save(p);
            UpicBeanUtils.copyProperties(p, projectInfo);
        } catch (Exception e) {
            LOGGER.info("addProject:项目" + projectInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
        return projectInfo;
    }

    public AdviceInfo changeProjectStatus(AdviceInfo adviceInfo) {
        try {
            Advice a = new Advice();
            UpicBeanUtils.copyProperties(adviceInfo, a);
            a = adviceRepository.save(a);
            UpicBeanUtils.copyProperties(a, adviceInfo);
            return adviceInfo;
        } catch (Exception e) {
            LOGGER.info("changeProjectStatus:建议" + adviceInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<AdviceInfo> searchAdvice(AdviceCondition adviceCondition, Pageable pageable) {
        Page<Advice> advicePage = null;
        try {
            advicePage = adviceRepository.findAll(new AdviceSpec(adviceCondition), pageable);
            return QueryResultConverter.convert(advicePage, pageable,
                    new AbstractDomain2InfoConverter<Advice, AdviceInfo>() {
                        @Override
                        protected void doConvert(Advice domain, AdviceInfo info) throws Exception {
                            UpicBeanUtils.copyProperties(domain, info);
                        }
                    });
        } catch (Exception e) {
            LOGGER.info("searchAdvice:建议列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<ProjectInfo> searchProject(ProjectCondition projectCondition, Pageable pageable) {
        Page<Project> pageData = null;
        try {
            pageData = projectRepository.findAll(new ProjectSpec(projectCondition), pageable);

            return QueryResultConverter.convert(pageData, pageable,
                    new AbstractDomain2InfoConverter<Project, ProjectInfo>() {
                        @Override
                        protected void doConvert(Project domain, ProjectInfo info) throws Exception {
                            // 过滤懒加载
                            UpicBeanUtils.copyProperties(domain, info);
                        }
                    });
        } catch (Exception e) {
            LOGGER.info("searchProject:项目列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public ProjectInfo getProjectByNum(String projectNum) {
        try {
            Project project = projectRepository.findByProjectNum(projectNum);
            if (project == null)
                throw new Exception();

            ProjectInfo p = new ProjectInfo();
            UpicBeanUtils.copyProperties(project, p);
            return p;
        } catch (Exception e) {
            LOGGER.info("getProjectByNum:项目查询失败。错误信息：" + e.getMessage());
            return null;
        }

    }

    public ProjectInfo updateProject(ProjectInfo projectInfo) {
        try {
            Project p = new Project();
            UpicBeanUtils.copyProperties(projectInfo, p);
            projectRepository.saveAndFlush(p);
            return projectInfo;
        } catch (Exception e) {
            LOGGER.info("getProjectByNum:项目" + projectInfo.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<ProjectInfo> getOnlineProject(RankEnum rankEnum, String unit, Pageable page) {
        Page<Project> projectPage = null;
        try {
            projectPage = projectRepository.getOnlineProject(rankEnum, unit, page);
            return QueryResultConverter.convert(projectPage, page,
                    new AbstractDomain2InfoConverter<Project, ProjectInfo>() {
                        protected void doConvert(Project domain, ProjectInfo info) throws Exception {
                            UpicBeanUtils.copyProperties(domain, info);
                        }
                    });
        } catch (Exception e) {
            LOGGER.info("getOnlineProject:项目" + projectPage.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<ProjectInfo> getProjectWithoutSignUp(Date now, Pageable pageable) {
        Page<Project> projectPage = null;
        try {
            projectPage = projectRepository.getProjectWithoutSignUp(now, pageable);
            return QueryResultConverter.convert(projectPage, pageable, new AbstractDomain2InfoConverter<Project, ProjectInfo>() {
                protected void doConvert(Project domain, ProjectInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("getProjectWithoutSignUp:项目" + projectPage.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<ProjectInfo> projectSearchBar(String userNum, String keyword, Pageable pageable) {
        Page<Project> projectPage = null;
        try {
            projectPage = projectRepository.projectSearchBar(userNum, keyword, pageable);
            return QueryResultConverter.convert(projectPage, pageable, new AbstractDomain2InfoConverter<Project, ProjectInfo>() {
                @Override
                protected void doConvert(Project domain, ProjectInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("projectSearchBar:项目" + projectPage.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<ProjectInfo> projectSearchBar(String keyword, Pageable pageable) {
        Page<Project> projectPage = null;
        try {
            projectPage = projectRepository.projectSearchBar(keyword, pageable);
            return QueryResultConverter.convert(projectPage, pageable, new AbstractDomain2InfoConverter<Project, ProjectInfo>() {
                @Override
                protected void doConvert(Project domain, ProjectInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("projectSearchBar:项目" + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    @Override
    public double getTeacherAllWorkloadSummary(String teacherNum) {
        List<Project> projectPage = null;
        try {
            projectPage = projectRepository.getByGuidanceNum(teacherNum);
            double allWorkloadSummary = 0;
            for (Project project : projectPage) {
                allWorkloadSummary += project.getIntegral() * project.getMaximum();
            }
            return allWorkloadSummary;
        } catch (Exception e) {
            LOGGER.info("getTeacherAllWorkloadSummary：" + e.getMessage());
            return 0;
        }
    }

    @Override
    public List<String> getByGuidanceNum(String teacherNum) {
        List<Project> projectPage = null;
        List<String> projectNumList = new ArrayList<String>();
        try {
            projectPage = projectRepository.getByGuidanceNum(teacherNum);
            if (projectPage == null) {
                throw new Exception();
            }
            for (Project project : projectPage) {
                projectNumList.add(project.getProjectNum());
            }
            return projectNumList;
        } catch (Exception e) {
            LOGGER.info("getTeacherAllWorkloadSummary：" + e.getMessage());
            return null;
        }
    }

    @Override
    public Page<ProjectInfo> getProjectByGuidanceNum(String guidanceNum, Pageable pageable) {
        Page<Project> projectPage = null;
        try {
            projectPage = projectRepository.getProjectByGuidanceNum(guidanceNum, pageable);
            return QueryResultConverter.convert(projectPage, pageable, new AbstractDomain2InfoConverter<Project, ProjectInfo>() {
                @Override
                protected void doConvert(Project domain, ProjectInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("getProjectByGuidanceNum：" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Object> listProject(ProjectCondition condition) {
        try {
            List<Object> objectList = new ArrayList<>();
            List<Project> projectList = projectRepository.findAll(new ProjectSpec(condition));
            List<ProjectInfo> projectInfoList = new ArrayList<>();
            for (Project project : projectList) {
                ProjectInfo projectInfo = new ProjectInfo();
                UpicBeanUtils.copyProperties(project, projectInfo);
                projectInfoList.add(projectInfo);
            }
            objectList = toObject(projectInfoList);
            return objectList;
        } catch (Exception e) {
            LOGGER.info("listProject：" + e.getMessage());
            return null;
        }
    }

    @Override
    public void saveAll(List<Object> list) {
        try {
            Project i = new Project();
            list.stream().parallel().forEach(x -> {
                x = (ProjectInfo) x;
                UpicBeanUtils.copyProperties(x, i);
                projectRepository.save(i);
            });
        } catch (Exception e) {
            LOGGER.info("saveAll:" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Page<ProjectInfo> getProjectBySql(List<String> statusList, List<String> projectCategoryList, Pageable pageable, String rank, String colloge, String type) {
        Page<Project> projectPage = null;
        try {
            Specification<Project> projectSpecification = new Specification<Project>() {
                @Override
                public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    Predicate statusOr = null;
                    if (statusList.size() > 1) {
                        List<Predicate> statusOrList = new ArrayList<>();
                        for (ImplementationProcessEnum status : changeStatus(statusList, type)) {
                            Predicate predicate = cb.equal(root.get("implementationProcess"), status);
                            statusOrList.add(predicate);
                        }
                        Predicate[] statusPredicates = new Predicate[statusOrList.size()];
                        statusPredicates = statusOrList.toArray(statusPredicates);
                        statusOr = cb.or(statusPredicates);
                    } else if (statusList.size() == 1) {
                        statusOr = cb.equal(root.get("status"), changeStatus(statusList, type).get(0));
                    }

                    Predicate projectCategoryOr = null;
                    if (projectCategoryList.size() > 1) {
                        List<Predicate> projectCategoryOrList = new ArrayList<Predicate>();
                        for (String projectCategory : projectCategoryList) {
                            Predicate predicate = cb.equal(root.get("projectCategory"), projectCategory);
                            projectCategoryOrList.add(predicate);
                        }
                        Predicate[] projectCategoryPredicates = new Predicate[projectCategoryOrList.size()];
                        projectCategoryPredicates = projectCategoryOrList.toArray(projectCategoryPredicates);
                        projectCategoryOr = cb.or(projectCategoryPredicates);
                    } else if (projectCategoryList.size() == 1) {
                        projectCategoryOr = cb.equal(root.get("projectCategory"), projectCategoryList.get(0));
                    }

//                    Predicate and = changeStatus(statusList).size()==0?cb.and( projectCategoryOr):cb.and(statusOr, projectCategoryOr);
                    Predicate status = null;
//                    Predicate projectCategory=null;
                    Predicate result = null;
                    if (changeStatus(statusList, type).size() > 0) {
                        status = cb.and(statusOr);
                    }
                    if (projectCategoryOr != null) {
                        if (status == null) {
                            result = cb.and(projectCategoryOr);
                        } else {
                            result = cb.and(projectCategoryOr, status);
                        }
                    }
                    if (projectCategoryOr == null) {
                        result = status;
                    }
                    if (rank.equals("3")) {
                        Predicate equal = cb.equal(root.get("collegeOtherName"), colloge);
                        result = cb.and(result, equal);
                    }
                    return result;

                }
            };
            projectPage = projectRepository.findAll(projectSpecification, pageable);
            return QueryResultConverter.convert(projectPage, pageable, new AbstractDomain2InfoConverter<Project, ProjectInfo>() {
                @Override
                protected void doConvert(Project domain, ProjectInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("getProjectBySql:" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Object> exportProjectByGuidanceNum(String guidanceNum) {
        List<Project> projectList = new ArrayList<Project>();
        try {
            projectList = projectRepository.exportProjectByGuidanceNum(guidanceNum);
            List<ProjectInfo> convert = QueryResultConverter.convert(projectList, new AbstractDomain2InfoConverter<Project, ProjectInfo>() {
                @Override
                protected void doConvert(Project domain, ProjectInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
            List<Object> objectList = toObject(convert);
            return objectList;
        } catch (Exception e) {
            LOGGER.info("exportProjectByGuidanceNum：" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Object> exportProjectSearchBar(String userNum, String keyword) {
        List<Project> projectPage = null;
        try {
            projectPage = projectRepository.exportProjectSearchBar(userNum, keyword);
            return toObject(projectPage);
        } catch (Exception e) {
            LOGGER.info("projectSearchBar:项目" + projectPage.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    static public <E> List<Object> toObject(List<E> list) {
        List<Object> objlist = new ArrayList<Object>();
        for (Object e : list) {
            Object obj = (Object) e;
            objlist.add(obj);
        }
        return objlist;
    }

    private List<ImplementationProcessEnum> changeStatus(List<String> statusList, String type) {
        List<ImplementationProcessEnum> statusEnums = new ArrayList<>();
        for (String status : statusList) {
            if (type != "Y" && !type.equals("Y")) {
                if (status.equals(ImplementationProcessEnum.SAVED.name())) {
                    statusEnums.add(ImplementationProcessEnum.SAVED);
                } else if (status.equals(ImplementationProcessEnum.IN_AUDIT.name())) {
                    statusEnums.add(ImplementationProcessEnum.IN_AUDIT);
                } else if (status.equals(ImplementationProcessEnum.IN_AUDIT_AGAIN.name())) {
                    statusEnums.add(ImplementationProcessEnum.IN_AUDIT_AGAIN);
                } else if (status.equals(ImplementationProcessEnum.IN_AUDIT_FINAL.name())) {
                    statusEnums.add(ImplementationProcessEnum.IN_AUDIT_FINAL);
                } else if (status.equals(ImplementationProcessEnum.AUDITED.name())) {
                    statusEnums.add(ImplementationProcessEnum.AUDITED);
                } else if (status.equals(ImplementationProcessEnum.ENROLLMENT.name())) {
                    statusEnums.add(ImplementationProcessEnum.ENROLLMENT);
                } else if (status.equals(ImplementationProcessEnum.HAVE_IN_HAND.name())) {
                    statusEnums.add(ImplementationProcessEnum.HAVE_IN_HAND);
                } else if (status.equals(ImplementationProcessEnum.COMPLETED.name())) {
                    statusEnums.add(ImplementationProcessEnum.COMPLETED);
                }
            } else {
                if (status.equals(ImplementationProcessEnum.CHECKING.name())) {
                    statusEnums.add(ImplementationProcessEnum.CHECKING);
                } else if (status.equals(ImplementationProcessEnum.CHECKING_AGAIN.name())) {
                    statusEnums.add(ImplementationProcessEnum.CHECKING_AGAIN);
                } else if (status.equals(ImplementationProcessEnum.CHECKING_FINAL.name())) {
                    statusEnums.add(ImplementationProcessEnum.CHECKING_FINAL);
                } else if (status.equals(ImplementationProcessEnum.CHECKED.name())) {
                    statusEnums.add(ImplementationProcessEnum.CHECKED);
                }
            }
        }
        return statusEnums;
    }

    /**
     * 定时任务
     * 每天晚上十二点执行
     */
//    @Scheduled(cron = "0/10 * * * * *")
    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void task() throws Exception {
        Map<String, JobParameter> param = new HashMap<>();
        param.put("startTime", new JobParameter(new Date()));
        jobLauncher.run(job, new JobParameters(param));
    }

    /**
     * 二维码生成
     *
     * @param projectNum
     * @return
     */
    @Override
    public String qrCodeGenerate(String projectNum, long freshTime) {
        try {
            // 随机生成AccessToken
            String token = projectNum + (new Date().getTime()) + "QR";

            // MD5加密
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String accessToken = base64Encoder.encode(messageDigest.digest(token.getBytes("utf-8")));

            // 存Redis
            UpicRedisComponent upicRedisComponent = new UpicRedisComponent();
            upicRedisComponent.set("QR" + projectNum, accessToken, freshTime);

            return accessToken;
        } catch (Exception e) {
            LOGGER.info("qrCodeGenerate。错误信息：" + e.getMessage());
            return null;
        }
    }
}
