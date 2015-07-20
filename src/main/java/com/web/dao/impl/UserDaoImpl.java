package com.web.dao.impl;

import org.springframework.stereotype.Component;

import com.web.dao.UserDao;
import com.web.model.User;

@Component
public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao{

}
