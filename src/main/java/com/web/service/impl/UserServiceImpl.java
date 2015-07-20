package com.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.web.dao.UserDao;
import com.web.model.User;
import com.web.service.UserService;

@Component
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, String>
	implements UserService{
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}
}
