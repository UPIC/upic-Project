package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.BannerStatusEnum;
import com.upic.enums.BannerTypeEnum;

/**
 * Created by zhubuqing on 2017/9/5.
 */
public class BannerInfo extends BaseInfo {
    private String pic; //图片

    private String url; //URL

    private int num; //顺序

    private BannerStatusEnum status;

    private BannerTypeEnum type;

    public BannerInfo() {
        super();
    }

    public BannerInfo(String pic, String url, int num, BannerStatusEnum status, BannerTypeEnum type) {
        super();
        this.pic = pic;
        this.url = url;
        this.num = num;
        this.status = status;
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public BannerStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BannerStatusEnum status) {
        this.status = status;
    }

    public BannerTypeEnum getType() {
        return type;
    }

    public void setType(BannerTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BannerInfo{" +
                "pic='" + pic + '\'' +
                ", url='" + url + '\'' +
                ", num=" + num +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
