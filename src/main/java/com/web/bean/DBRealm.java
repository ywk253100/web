package com.web.bean;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.model.User;
import com.web.service.UserService;

@Component
public class DBRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String userId = (String) principalCollection.fromRealm(getName()).iterator().next();
		User user = userService.get(userId);
		if(user == null)
			return null;
		
		SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
		authInfo.addRole(StringUtils.lowerCase(user.getRole().name()));
		return authInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		User user = userService.get("username", token.getUsername());
		if(user == null)
			return null;
		return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
	}
}
