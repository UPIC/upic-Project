package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.GrainCoinLogStatusEnum;
import com.upic.enums.GrainCoinLogTypeEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class GrainCoinLog extends BaseEntity {
    private String event; //消费事件

    private double score; //消费积分

    @Enumerated(EnumType.STRING)
    private GrainCoinLogTypeEnum type; //消费类型

    @Enumerated(EnumType.STRING)
    private GrainCoinLogStatusEnum status; //消费状态

    private String username; //消费人

    private String userNum; //用户编号

    private String projectNum;

    private long prizeId; //消费奖品

    private String projectName;

    private String prizeName;
}
