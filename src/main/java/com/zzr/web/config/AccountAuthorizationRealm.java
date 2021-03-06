package com.zzr.web.config;

import com.zzr.business.service.UserService;
import com.zzr.util.shiro.auth.CyptoUtils;
import com.zzr.util.shiro.auth.ShiroUser;
import com.zzr.vo.user.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @data 2015年9月24日 下午5:22:33修改
 */
@Slf4j
public class AccountAuthorizationRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 查询获得用户信息 AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）
     *
     * AuthenticationInfo有两个作用： 1、如果Realm 是AuthenticatingRealm
     * 子类，则提供给AuthenticatingRealm 内部使用的
     * CredentialsMatcher进行凭据验证；（如果没有继承它需要在自己的Realm中自己实现验证）；
     * 2、提供给SecurityManager来创建Subject（提供身份信息）；
     * @param authcToken
     * @return
     * @throws org.apache.shiro.authc.AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userName = token.getUsername();
        String password = new String(token.getPassword());
        UserVO userVO = userService.getUser(userName);
        if (userVO == null) {
            // 用户不存在
            throw new UnknownAccountException("用户不存在");
        }
        if(userVO. isLock()){
            //用户被锁定
            throw new LockedAccountException("用户被锁定");
        }
        if(!isPasswordRight(userVO.getPassword(),password)){
            //密码错误
            throw new AuthenticationException("用户名或密码错误");
        }
        ShiroUser shiroUser = new ShiroUser(userVO.getId(), userVO.getNickname(), userVO.getAccount(), null);
        return new SimpleAuthenticationInfo(shiroUser, CyptoUtils.md5(new String(token.getPassword())), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    private Boolean isPasswordRight(String passwordDb,String passwordVO){
        if(passwordDb.equals(CyptoUtils.md5(passwordVO))){
            return true;
        }
        return false;
    }

}
