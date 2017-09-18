package com.upic.service;

import com.upic.condition.BannerCondition;
import com.upic.dto.BannerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface BannerService {
    /**
     * 添加Banner
     *
     * @param bannerInfo
     * @return
     */
    BannerInfo addBanner(BannerInfo bannerInfo);

    /**
     * 修改Banner
     *
     * @param bannerInfo
     * @return
     */
    BannerInfo updateBanner(BannerInfo bannerInfo);

    /**
     * 查询Banner（条件）
     *
     * @param bannerCondition
     * @return
     */
    Page<BannerInfo> searchBanner(BannerCondition bannerCondition, Pageable pageable);

    /**
     * 根据ID查询Banner
     *
     * @param bannerId
     * @return
     */
    BannerInfo getBannerByBannerId(long bannerId);

    /**
     * 根据ID删除Banner
     *
     * @param bannerId
     */
    void deleteBanner(long bannerId);
}
