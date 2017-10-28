package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/9/4.
 */
@Data
@Entity
public class ConfirmationBasis extends BaseEntity {
    private String content; //内容

    private String projectNum;

    private String projectName;

    private String otherName;

    private long categoryNodeId;
}
