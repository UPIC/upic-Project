package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.UserCondition;
import com.upic.dto.UserInfo;
import com.upic.po.User;
import com.upic.repository.Spec.UserSpec;
import com.upic.repository.UserRepository;
import com.upic.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by zhubuqing on 2017/9/9.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserInfo addUser(UserInfo userInfo) {
        try {
            User user = new User();
            UpicBeanUtils.copyProperties(userInfo, user);
            user = userRepository.save(user);
            UpicBeanUtils.copyProperties(user, userInfo);
            return userInfo;
        } catch (Exception e) {
            LOGGER.info("用户：" + userInfo.toString() + "添加失败。addUser错误信息：" + e.getMessage());
            return null;
        }
    }

    public UserInfo updateUser(UserInfo userInfo) {
        try {
            User user = new User();
            UpicBeanUtils.copyProperties(userInfo, user);
            user = userRepository.saveAndFlush(user);
            UpicBeanUtils.copyProperties(user, userInfo);
            return userInfo;
        } catch (Exception e) {
            LOGGER.info("用户" + userInfo.toString() + "更新失败。updateUser错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<UserInfo> searchUser(UserCondition userCondition, Pageable pageable) {
        Page<User> userPage = null;
        try {
            userPage = userRepository.findAll(new UserSpec(userCondition), pageable);
            return QueryResultConverter.convert(userPage, pageable, new AbstractDomain2InfoConverter<User, UserInfo>() {
                protected void doConvert(User domain, UserInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("用户列表查询失败。searchUser错误信息：" + e.getMessage());
            return null;
        }
    }

    public UserInfo getUserByUserNum(String userNum) {
        try {
            User user = userRepository.getByUserNum(userNum);
            if (user == null) throw new Exception();
            UserInfo userInfo = new UserInfo();
            UpicBeanUtils.copyProperties(user, userInfo);
            return userInfo;
        } catch (Exception e) {
            LOGGER.info("用户编号为：" + userNum + "查询失败。getUserByUserNum错误信息：" + e.getMessage());
            return null;
        }
    }

    public void deleteUser(String userNum) {
        try {
            User user = userRepository.getByUserNum(userNum);
            userRepository.delete(user);
        } catch (Exception e) {
            LOGGER.info("用户删除失败。deleteUser错误信息：" + e.getMessage());
        }
    }

    @Override
    public Page<UserInfo> userSearchBar(String keyword, Pageable pageable) {
        Page<User> userPage = null;
        try {
            userPage = userRepository.userSearchBar(keyword, pageable);
            return QueryResultConverter.convert(userPage, pageable, new AbstractDomain2InfoConverter<User, UserInfo>() {
                protected void doConvert(User domain, UserInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("用户列表查询失败。userSearchBar：" + e.getMessage());
            return null;
        }
    }
}
