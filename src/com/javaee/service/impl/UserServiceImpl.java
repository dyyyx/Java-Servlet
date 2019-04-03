package com.javaee.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.javaee.dao.UserDao;
import com.javaee.dao.impl.UserDaoImpl;
import com.javaee.pojo.User;
import com.javaee.service.UserService;

public class UserServiceImpl implements UserService{
	//声明日志对象
	Logger logger = Logger.getLogger(UserServiceImpl.class);
	//声明Dao层对象
	UserDao ud = new UserDaoImpl();
	//用户登录
	@Override
	public User checkUserLoginService(String uname, String pwd) {
		//打印日志
		logger.debug(uname+"发起登录请求");
		User u = ud.checkUserLoginDao(uname, pwd);
		if(u!=null){
			logger.debug(uname+"登陆成功");
		}else{
			logger.debug(uname+"登录失败");
		}
		return u;
	}
	//修改用户密码
	@Override
	public int userChangePwdService(String newPwd, int uid) {
		logger.debug(uid+"发起密码修改请求");
		int index = ud.userChangePwdDao(newPwd,uid);
		if(index>0){
			logger.debug(uid+"密码修改成功");
		}else{
			logger.debug(uid+"密码修改失败");
		}
		return index;
	}
	@Override
	public List<User> showUserService() {
		List<User> lu = ud.showUserDao();
		logger.debug("所有用户信息："+lu);
		return lu;
	}
	@Override
	public int userRegService(User u) {
		logger.debug(u.getUname()+"发起注册请求");
		int index = ud.userRegDao(u);
		if(index>0){
			logger.debug(u.getUname()+"注册成功");
		}else{
			logger.debug(u.getUname()+"注册失败");
		}
		return index;
	}

}
