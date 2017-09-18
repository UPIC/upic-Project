package com.upic.service;

import com.upic.condition.UserCondition;
import com.upic.dto.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param userInfo
     * @return
     */
    UserInfo addUser(UserInfo userInfo);

    /**
     * 更新用户
     *
     * @param userInfo
     * @return
     */
    UserInfo updateUser(UserInfo userInfo);

    /**
     * 查询用户（条件）
     *
     * @param userCondition
     * @param pageable
     * @return
     */
    Page<UserInfo> searchUser(UserCondition userCondition, Pageable pageable);

    /**
     * 根据用户编号查询用户
     *
     * @param userNum
     * @return
     */
    UserInfo getUserByUserNum(String userNum);

    /**
     * 根据用户编号删除用户
     *
     * @param userNum
     */
    void deleteUser(String userNum);
}
