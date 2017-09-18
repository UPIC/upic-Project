package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.OperatorStatusEnum;
import lombok.Data;
import sun.security.provider.MD5;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class Operator extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String jobNum; //工号

    private String username; //用户名

    private String password; //密码

    private String email; //邮箱

    private String pic; //头像

    @Enumerated(EnumType.STRING)
    private OperatorStatusEnum status; //状态

    private String phone; //手机号

    private String idcard; //身份证

    private int type; //

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "operator")
    private List<OperatorRole> operatorRoles;
}
