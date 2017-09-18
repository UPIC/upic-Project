package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.BannerCondition;
import com.upic.dto.BannerInfo;
import com.upic.po.Banner;
import com.upic.repository.BannerRepository;
import com.upic.repository.Spec.BannerSpec;
import com.upic.service.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhubuqing on 2017/9/10.
 */
@Service("bannerService")
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerRepository bannerRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(BannerServiceImpl.class);

    public BannerInfo addBanner(BannerInfo bannerInfo) {
        try {
            Banner banner = new Banner();
            UpicBeanUtils.copyProperties(bannerInfo, banner);
            banner = bannerRepository.save(banner);
            UpicBeanUtils.copyProperties(banner, bannerInfo);
            return bannerInfo;
        } catch (Exception e) {
            LOGGER.info("Banner图：" + bannerInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public BannerInfo updateBanner(BannerInfo bannerInfo) {
        try {
            Banner banner = new Banner();
            UpicBeanUtils.copyProperties(bannerInfo, banner);
            banner = bannerRepository.saveAndFlush(banner);
            UpicBeanUtils.copyProperties(banner, bannerInfo);
            return bannerInfo;
        } catch (Exception e) {
            LOGGER.info("Banner图" + bannerInfo.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<BannerInfo> searchBanner(BannerCondition bannerCondition, Pageable pageable) {
        Page<Banner> bannerPage = null;
        try {
            bannerPage = bannerRepository.findAll(new BannerSpec(bannerCondition), pageable);
            return QueryResultConverter.convert(bannerPage, pageable, new AbstractDomain2InfoConverter<Banner, BannerInfo>() {
                protected void doConvert(Banner domain, BannerInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("Banner图列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public BannerInfo getBannerByBannerId(long bannerId) {
        try {
            Banner banner = bannerRepository.findOne(bannerId);
            if (banner == null) throw new Exception();
            BannerInfo bannerInfo = new BannerInfo();
            UpicBeanUtils.copyProperties(banner, bannerInfo);
            return bannerInfo;
        } catch (Exception e) {
            LOGGER.info("Banner图ID为：" + bannerId + "查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public void deleteBanner(long bannerId) {
        try {
            bannerRepository.delete(bannerId);
        } catch (Exception e) {
            LOGGER.info("Banner图删除失败。错误信息：" + e.getMessage());
        }
    }
}
