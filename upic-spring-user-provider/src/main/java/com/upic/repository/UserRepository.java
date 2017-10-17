package com.upic.repository;

import com.upic.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User getByUserNum(String userNum);

    @Query(value = "select user from User user where user.username like %?1% or user.userNum like %?1% or user.college like %?1%")
    Page<User> userSearchBar(String keyword, Pageable pageable);
}