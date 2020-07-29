package com.ael.configuration.shiro;

import com.ael.configuration.entity.Roles;
import com.ael.configuration.entity.UserPermissions;
import com.ael.configuration.entity.UserRoles;
import com.ael.configuration.entity.Users;
import com.ael.configuration.repository.UserPermissionsRepository;
import com.ael.configuration.repository.UserRepository;
import com.ael.configuration.repository.UserRolesRepository;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    private UserPermissionsRepository userPermissionsRepository;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        // 查询用户名称
        Users user = userRepository.findTopByUsername(name);
        // 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<UserRoles> userRoles = userRolesRepository.findAllByUserId(user.getId());
        for (UserRoles userRole : userRoles) {
            // 添加角色
            simpleAuthorizationInfo.addRole(userRole.getId());
        }
        List<UserPermissions> userPermissions = userPermissionsRepository.findAllByUserId(user.getId());
        for (UserPermissions userPermission : userPermissions) {
            simpleAuthorizationInfo.addStringPermission(userPermission.getId());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        // 获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        Users user = userRepository.findTopByUsername(name);
        if (user == null) {
            // 这里返回后会报出对应异常
            return null;
        } else {
            // 这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name,
                    user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
