package com.javaee.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.javaee.dao.UserDao;
import com.javaee.dao.impl.UserDaoImpl;
import com.javaee.pojo.User;
import com.javaee.service.UserService;

public class UserServiceImpl implements UserService{
	//������־����
	Logger logger = Logger.getLogger(UserServiceImpl.class);
	//����Dao�����
	UserDao ud = new UserDaoImpl();
	//�û���¼
	@Override
	public User checkUserLoginService(String uname, String pwd) {
		//��ӡ��־
		logger.debug(uname+"�����¼����");
		User u = ud.checkUserLoginDao(uname, pwd);
		if(u!=null){
			logger.debug(uname+"��½�ɹ�");
		}else{
			logger.debug(uname+"��¼ʧ��");
		}
		return u;
	}
	//�޸��û�����
	@Override
	public int userChangePwdService(String newPwd, int uid) {
		logger.debug(uid+"���������޸�����");
		int index = ud.userChangePwdDao(newPwd,uid);
		if(index>0){
			logger.debug(uid+"�����޸ĳɹ�");
		}else{
			logger.debug(uid+"�����޸�ʧ��");
		}
		return index;
	}
	@Override
	public List<User> showUserService() {
		List<User> lu = ud.showUserDao();
		logger.debug("�����û���Ϣ��"+lu);
		return lu;
	}
	@Override
	public int userRegService(User u) {
		logger.debug(u.getUname()+"����ע������");
		int index = ud.userRegDao(u);
		if(index>0){
			logger.debug(u.getUname()+"ע��ɹ�");
		}else{
			logger.debug(u.getUname()+"ע��ʧ��");
		}
		return index;
	}

}
