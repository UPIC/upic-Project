package com.upic.serviceimpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.AdviceCondition;
import com.upic.condition.ProjectCondition;
import com.upic.dto.AdviceInfo;
import com.upic.dto.ProjectInfo;
import com.upic.enums.RankEnum;
import com.upic.po.Advice;
import com.upic.po.Project;
import com.upic.po.ProjectLog;
import com.upic.repository.AdviceRepository;
import com.upic.repository.ProjectRepository;
import com.upic.repository.spec.AdviceSpec;
import com.upic.repository.spec.ProjectSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.upic.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Dtz
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AdviceRepository adviceRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

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
                            filterProject(domain);
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

            filterProject(project);
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
                            filterProject(domain);
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
                    filterProject(domain);
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
                    filterProject(domain);
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
                    filterProject(domain);
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("projectSearchBar:项目" + projectPage.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    private void filterProject(Project project) {
        if (project.getProjectLogs() != null) {
            project.setProjectLogs(null);
        }
    }
}
