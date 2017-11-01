package com.upic.utils;

import com.upic.social.user.SocialUsers;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.security.SocialUser;

/**
 * Created by zhubuqing on 2017/10/31.
 */
public class UserUtils {
    public static SocialUsers getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (SocialUsers) authentication.getPrincipal();
    }
}
