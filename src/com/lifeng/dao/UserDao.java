package com.lifeng.dao;

import java.util.List;

import com.lifeng.entity.User;

public interface UserDao {	
	// ���û���������Ѱ���û�
	public User findUser(String username, String password) ;	
}
