package com.yeyeck.yeblog.configuration;

import com.yeyeck.yeblog.mapper.UserMapper;
import com.yeyeck.yeblog.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class YeRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User)getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String role = user.getRole();
        Set<String> roles = new HashSet<>();
        roles.add(role);
        info.setRoles(roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user = userMapper.findByUsername(token.getUsername());
        if (user == null ) {
            throw new IncorrectCredentialsException("Invalid Username or Password");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), getName());
        return info;
    }

}
