package com.geektcp.alpha.sys.auth.shiro;


import com.geektcp.alpha.sys.auth.dao.SysUserDao;
import com.geektcp.alpha.sys.auth.model.po.SysUserPo;
import com.geektcp.alpha.sys.auth.shiro.model.RoleVo;
import com.geektcp.alpha.sys.auth.shiro.model.UserRoleVo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chengmo on 2018/1/4.
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;

    public UserRealm(CredentialsMatcher matcher) {
        super(matcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserRoleVo userRoleDto = (UserRoleVo) principals.getPrimaryPrincipal();
        Set<String> roleSet = new HashSet<>();
        if (userRoleDto.getRoles() != null) {
            for (RoleVo role : userRoleDto.getRoles()) {
                roleSet.add(role.getId().toString());
            }
        }
        authorizationInfo.setRoles(roleSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken credential = (UsernamePasswordToken) token;
        SysUserPo user = sysUserDao.findByUserNo(credential.getUsername());
        if (user == null) {
            throw new AccountException();
        }
        UserRoleVo userRoleDto = new UserRoleVo(user);
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userRoleDto, user.getPassword(), user.getUserNo());
        return authenticationInfo;
    }
}
