package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.UserStatusEnum;
import com.upic.enums.UserTypeEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String userNum;

    private String username;

    private String password;

    private String college;

    private String major;

    private String clazz;

    private String phone;

    private String idCard;

    private String email;

    private String pic;

    private Date birthday;

    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;

    private String nickName;

    @Enumerated(EnumType.STRING)
    private UserTypeEnum type;
}
