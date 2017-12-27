package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.PrizeStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class Prize extends BaseEntity {
    private String prizeName; //奖品名

    private double score; //消费分数

    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> prizePic; //图片

    private String title; //标题

    private String content; //奖品内容

    @Enumerated(EnumType.STRING)
    private PrizeStatusEnum status; //奖品状态

    private String remark; //备注

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime; //开始时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime; //结束时间
}
