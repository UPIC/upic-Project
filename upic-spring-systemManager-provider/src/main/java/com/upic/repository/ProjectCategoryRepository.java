package com.upic.repository;

import com.upic.po.ProjectCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface ProjectCategoryRepository extends JpaRepository<ProjectCategory, Long>, JpaSpecificationExecutor<ProjectCategory> {

    List<ProjectCategory> findBySubordinateSectorOtherName(String subordinateSectorOtherName);
}