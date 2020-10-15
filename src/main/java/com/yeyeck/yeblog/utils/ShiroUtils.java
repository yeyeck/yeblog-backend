package com.yeyeck.yeblog.utils;

import com.yeyeck.yeblog.constants.YeConstants;
import com.yeyeck.yeblog.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.UUID;

public class ShiroUtils {

    private ShiroUtils(){}

    public static int getUserId() {
        return getCurrentUser().getId();
    }

    public static User getCurrentUser() {
        User user =  (User)SecurityUtils.getSubject().getPrincipal();
        if (user == null){
            throw new AuthorizationException("NO AUTHENTICATION INFO.");
        } else {
            return user;
        }
    }

    public static String generateSalt() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String encrypt(String salt, String password) {
        SimpleHash hash = new SimpleHash(YeConstants.HASH_TYPE, password, ByteSource.Util.bytes(salt), YeConstants.HASH_TIMES);
        return hash.toString();
    }

    public static boolean isAuthenticated() {
        return SecurityUtils.getSubject().isAuthenticated();
    }
}
