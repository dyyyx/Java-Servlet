package com.javaee.service;

import java.util.List;

import com.javaee.pojo.User;

public interface UserService {
	/**
	 * У���¼��Ϣ
	 * @param uname �û���
	 * @param pwd ����
	 * @return ���ز�ѯ�����û���Ϣ
	 */
	User checkUserLoginService(String uname,String pwd);
	/**
	 * �޸��û�����
	 * @param newPwd
	 * @param uid
	 * @return
	 */
	int userChangePwdService(String newPwd, int uid);
	/**
	 * ��ѯ�����û���Ϣ
	 * @return
	 */
	List<User> showUserService();
	/**
	 * �û�ע��
	 * @param u
	 * @return
	 */
	int userRegService(User u);
	
}
