package com.upic.repository;

import com.upic.enums.RankEnum;
import com.upic.po.Project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    public Project findByProjectNum(String projectNum);

    @Query(value = "SELECT project FROM Project project where project.rankEnum=?1 and project.unit=?2 and project.implementationProcess ='AUDITED' or project.implementationProcess= 'HAVE_IN_HAND'")
    public Page<Project> getOnlineProject(RankEnum rankEnum, String unit, Pageable page);

    @Query(value = "select project from Project project where project.signUpStartTime < ?1 and project.signUpEndTime > ?1 and project.implementationProcess <> 'NOT_PASS'")
    Page<Project> getProjectWithoutSignUp(Date now, Pageable pageable);
}