package com.upic.repository;

import com.upic.po.CategoryNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface CategoryNodeRepository extends JpaRepository<CategoryNode, Long>, JpaSpecificationExecutor<CategoryNode> {
    @Query(value = "select categoryNode FROM CategoryNode categoryNode where categoryNode.fatherId = ?1")
    List<CategoryNode> getCategoryNodeByFatherId(long fatherId);
}