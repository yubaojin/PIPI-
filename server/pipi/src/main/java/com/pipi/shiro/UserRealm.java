//package com.pipi.shiro;
//
//import java.util.Set;
//
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.IncorrectCredentialsException;
//import org.apache.shiro.authc.LockedAccountException;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.xinhaosi.sys.entity.SysUserEntity;
//import com.xinhaosi.sys.service.SysMenuService;
//import com.xinhaosi.sys.service.SysUserService;
//
///**
// * 认证
// * 
// */
//@Component
//public class UserRealm extends AuthorizingRealm {
//	
//    @Autowired
//    private SysUserService sysUserService;
//    @Autowired
//    private SysMenuService sysMenuService;
//    
//    /**
//     * 授权(验证权限时调用)
//     */
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
//		Long userId = user.getUserId();
//
//		//用户权限列表
//		Set<String> permsSet = sysMenuService.getUserPermissions(userId);
//
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		info.setStringPermissions(permsSet);
//		return info;
//	}
//
//	/**
//	 * 认证(登录时调用)
//	 */
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(
//			AuthenticationToken token) throws AuthenticationException {
//		String username = (String) token.getPrincipal();
//        String password = new String((char[]) token.getCredentials());
//        
//        //查询用户信息
//        SysUserEntity user = sysUserService.queryByUserName(username);
//        
//        //账号不存在
//        if(user == null) {
//            throw new UnknownAccountException("账号或密码不正确");
//        }
//        
//        //密码错误
//        if(!password.equals(user.getPassword())) {
//            throw new IncorrectCredentialsException("账号或密码不正确");
//        }
//        
//        //账号锁定
//        if(user.getStatus() == 0){
//        	throw new LockedAccountException("账号已被锁定,请联系管理员");
//        }
//        
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
//        return info;
//	}
//
//}
