package com.yeyeck.yeblog.shiro;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@Slf4j
public class ShiroTest {

    static final String HASH_TYPE = "MD5";
    static final int HASH_TIME = 5;


    @Test
    void  hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(HASH_TYPE);
        hashedCredentialsMatcher.setHashIterations(HASH_TIME);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(Boolean.TRUE);
    }

    @Test
    void encrypt() {
        String password = "Admin";
        String salt = UUID.randomUUID().toString().replaceAll("-","");
        SimpleHash hash = new SimpleHash(HASH_TYPE, password, ByteSource.Util.bytes(salt), HASH_TIME);
        log.info("password: {}, salt: {}", hash.toString(), salt);
    }
}
