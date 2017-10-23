package com.upic.service;

import com.upic.condition.ClazzCondition;
import com.upic.dto.ClazzInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface ClazzService {
    /**
     * 添加Clazz
     *
     * @param clazzInfo
     * @return
     */
    ClazzInfo addClazz(ClazzInfo clazzInfo);

    /**
     * 修改Clazz
     *
     * @param clazzInfo
     * @return
     */
    ClazzInfo updateClazz(ClazzInfo clazzInfo);

    /**
     * 查询Clazz（条件）
     *
     * @param clazzCondition
     * @return
     */
    Page<ClazzInfo> searchClazz(ClazzCondition clazzCondition, Pageable pageable);

    /**
     * 根据ID查询Clazz
     *
     * @param clazzId
     * @return
     */
    ClazzInfo getById(long clazzId);

    /**
     * 根据ID删除Clazz
     *
     * @param clazzId
     */
    void deleteClazz(long clazzId);
}
