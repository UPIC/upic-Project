package com.upic;

import com.upic.condition.UserCondition;
import com.upic.dto.UserInfo;
import com.upic.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaTestApplication.class)
public class SpringBootJpaTestApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void testAddUser() {
        for (int i = 0; i < 50; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserNum(new Date().getTime() + i + "");
            userInfo.setUsername("Test" + i);
            userInfo.setPassword("12345" + i);
            userInfo.setCollege("信工");
            userInfo.setMajor("计科");
            userInfo.setClazz("1班");
            userInfo.setPhone("12345678901");
            userInfo.setIdCard("11223344556677");
            userInfo.setEmail("12345678" + i + "@qq.com");
            userInfo.setNickName("测试" + i);
            userService.addUser(userInfo);
        }
    }

    @Test
    public void testUpdateUser() {
        UserInfo u1 = userService.getUserByUserNum("1504927905724");
        u1.setClazz("100");
        UserInfo u = userService.updateUser(u1);
        System.out.println(u);
    }

    @Test
    public void testSearchUser() {
        UserCondition userCondition = new UserCondition();
        userCondition.setClazz("1班");
        PageRequest p = new PageRequest();
        Page<UserInfo> page = userService.searchUser(userCondition, p);

        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        for (UserInfo userInfo : page.getContent()) {
            System.out.println(userInfo);
        }
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser("1504927905724");
    }
}
