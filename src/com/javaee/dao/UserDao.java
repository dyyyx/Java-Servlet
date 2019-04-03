package com.javaee.dao;

import java.util.List;

import com.javaee.pojo.User;

public interface UserDao {
	/**
	 * �����û����������ѯ�û���Ϣ
	 * @param uname �û���
	 * @param pwd ����
	 * @return ���ز�ѯ�����û���Ϣ
	 */
	User checkUserLoginDao(String uname,String pwd);
	/**
	 * �����û�id�޸��û�����
	 * @param newPwd
	 * @param uid
	 * @return
	 */
	int userChangePwdDao(String newPwd, int uid);
	/**
	 * ��ѯ�����û���Ϣ
	 * @return
	 */
	List<User> showUserDao();
	/**
	 * �û�ע��
	 * @param u
	 * @return
	 */
	int userRegDao(User u);
}
