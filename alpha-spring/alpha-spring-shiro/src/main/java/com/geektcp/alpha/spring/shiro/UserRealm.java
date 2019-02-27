package com.geektcp.alpha.spring.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chengmo on 2018/1/4.
 */
public class UserRealm extends AuthorizingRealm {

//    @Autowired
//    private SysUserDao sysUserDao;

    public UserRealm(CredentialsMatcher matcher) {
        super(matcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        UserRoleVo userRoleDto = (UserRoleVo) principals.getPrimaryPrincipal();
        Set<String> roleSet = new HashSet<>();
//        if (userRoleDto.getRoles() != null) {
//            for (RoleVo role : userRoleDto.getRoles()) {
//                roleSet.add(role.getId().toString());
//            }
//        }
        authorizationInfo.setRoles(roleSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken credential = (UsernamePasswordToken) token;
//        SysUserPo user = sysUserDao.findByUserNo(credential.getUsername());
//        if (user == null) {
//            throw new AccountException();
//        }
//        UserRoleVo userRoleDto = new UserRoleVo(user);
//        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                userRoleDto, user.getPassword(), user.getUserNo());
//        return authenticationInfo;
        return null;
    }
}
