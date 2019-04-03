package com.javaee.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaee.dao.UserDao;
import com.javaee.pojo.User;

public class UserDaoImpl implements UserDao{

	@Override
	public User checkUserLoginDao(String uname, String pwd) {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		//��������
		User u = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/servlettest", "root", "123456");
			//����sql����
			String sql = "select * from t_user where uname = ? and pwd = ?";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//ռλ����ֵ
			ps.setString(1, uname);
			ps.setString(2, pwd);
			//ִ��sql
			rs = ps.executeQuery();
			//�������
				while(rs.next()){
					//��������ֵ
					u = new User();
					u.setUid(rs.getInt("uid"));
					u.setUname(rs.getString("uname"));
					u.setPwd(rs.getString("pwd"));
					u.setSex(rs.getString("sex"));
					u.setAge(rs.getInt("age"));
					u.setBirth(rs.getString("birth"));
				}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر���Դ
			try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//���ػ�ȡ����
		return u;
	}
	//�����û�id�޸��û�����
	@Override
	public int userChangePwdDao(String newPwd, int uid) {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		//��������
		int index = -1;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/servlettest", "root", "123456");
			//����Sql����
			String sql = "update t_user set pwd=? where uid=?";
			//����Sql�������
			ps = conn.prepareStatement(sql);
			//ռλ����ֵ
			ps.setString(1, newPwd);
			ps.setInt(2, uid);
			//ִ��
			index = ps.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر���Դ
			try {
				ps.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//���ؽ��
		return index;
	}
	@Override
	public List<User> showUserDao() {
		//����jdbc����
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null; 
				//��������
				List<User> lu = null;
				try {
					//��������
					Class.forName("com.mysql.jdbc.Driver");
					//��ȡ����
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/servlettest", "root", "123456");
					//����sql����
					String sql = "select * from t_user";
					//����sql�������
					ps = conn.prepareStatement(sql);
					//ִ��sql
					rs = ps.executeQuery();
					lu = new ArrayList<User>();
					//�������
						while(rs.next()){
							//��������ֵ
							User u = new User();
							u.setUid(rs.getInt("uid"));
							u.setUname(rs.getString("uname"));
							u.setPwd(rs.getString("pwd"));
							u.setSex(rs.getString("sex"));
							u.setAge(rs.getInt("age"));
							u.setBirth(rs.getString("birth"));
							lu.add(u);
						}
				
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					//�ر���Դ
					try {
						rs.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						ps.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				//���ػ�ȡ����
				return lu;
	}
	@Override
	public int userRegDao(User u) {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		//��������
		int index = -1;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/servlettest", "root", "123456");
			//����sql����
			String sql = "insert into t_user values(default,?,?,?,?,?)";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//ռλ����ֵ
			ps.setString(1, u.getUname());
			ps.setString(2, u.getPwd());
			ps.setString(3, u.getSex());
			ps.setInt(4, u.getAge());
			ps.setString(5, u.getBirth());
			//ִ��
			index = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر���Դ
			try {
				ps.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//���ؽ��
		return index;
	}

}
