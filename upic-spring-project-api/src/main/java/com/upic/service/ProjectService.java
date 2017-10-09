package com.upic.service;

import com.upic.condition.AdviceCondition;
import com.upic.condition.ProjectCondition;
import com.upic.dto.AdviceInfo;
import com.upic.dto.ProjectInfo;
import com.upic.enums.RankEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public interface ProjectService {
    /**
     * 添加项目
     *
     * @param projectInfo
     * @return
     */
    ProjectInfo addProject(ProjectInfo projectInfo);

    /**
     * 项目审核/项目验收
     *
     * @param adviceInfo
     * @return
     */
    AdviceInfo changeProjectStatus(AdviceInfo adviceInfo);

    /**
     * 批量获取Advice
     *
     * @param adviceCondition
     * @return
     */
    Page<AdviceInfo> searchAdvice(AdviceCondition adviceCondition, Pageable pageable);

    /**
     * 项目查询
     *
     * @param projectCondition
     * @param pageable
     * @return
     */
    Page<ProjectInfo> searchProject(ProjectCondition projectCondition, Pageable pageable);

    /**
     * 根据项目NUM，查询项目明细
     *
     * @param projectNum
     * @return
     */
    ProjectInfo getProjectByNum(String projectNum);

    /**
     * 维护项目
     *
     * @param projectInfo
     * @return
     */
    ProjectInfo updateProject(ProjectInfo projectInfo);

    /**
     * 移动端查询项目
     *
     * @param rankEnum
     * @param unit
     * @param page
     * @return
     */
    Page<ProjectInfo> getOnlineProject(RankEnum rankEnum, String unit, Pageable page);

    /**
     * 查询我未报名、并且在报名期间内的活动（学生移动端全部活动查询）
     *
     * @param now
     * @param pageable
     * @return
     */
    Page<ProjectInfo> getProjectWithoutSignUp(Date now, Pageable pageable);

    /**
     * 我的项目搜索条
     *
     * @param userNum
     * @param keyword
     * @param pageable
     * @return
     */
    Page<ProjectInfo> projectSearchBar(String userNum, String keyword, Pageable pageable);

    /**
     * 项目搜索条
     *
     * @param keyword
     * @param pageable
     * @return
     */
    Page<ProjectInfo> projectSearchBar(String keyword, Pageable pageable);
}
