package com.upic.service;

import com.upic.condition.BannerCondition;
import com.upic.condition.CollegeCondition;
import com.upic.dto.BannerInfo;
import com.upic.dto.CollegeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface CollegeService {
    /**
     * 添加College
     *
     * @param collegeInfo
     * @return
     */
    CollegeInfo addCollege(CollegeInfo collegeInfo);

    /**
     * 修改College
     *
     * @param collegeInfo
     * @return
     */
    CollegeInfo updateCollege(CollegeInfo collegeInfo);

    /**
     * 查询College（条件）
     *
     * @param collegeCondition
     * @return
     */
    Page<CollegeInfo> searchCollege(CollegeCondition collegeCondition, Pageable pageable);

    /**
     * 根据ID查询College
     *
     * @param collegeId
     * @return
     */
    CollegeInfo getById(long collegeId);

    /**
     * 根据ID删除Banner
     *
     * @param collegeId
     */
    void deleteCollege(long collegeId);
}
