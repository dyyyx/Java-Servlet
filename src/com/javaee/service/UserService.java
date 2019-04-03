package com.javaee.service;

import java.util.List;

import com.javaee.pojo.User;

public interface UserService {
	/**
	 * 校验登录信息
	 * @param uname 用户名
	 * @param pwd 密码
	 * @return 返回查询到的用户信息
	 */
	User checkUserLoginService(String uname,String pwd);
	/**
	 * 修改用户密码
	 * @param newPwd
	 * @param uid
	 * @return
	 */
	int userChangePwdService(String newPwd, int uid);
	/**
	 * 查询所有用户信息
	 * @return
	 */
	List<User> showUserService();
	/**
	 * 用户注册
	 * @param u
	 * @return
	 */
	int userRegService(User u);
	
}
