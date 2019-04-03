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
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		//声明变量
		User u = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/servlettest", "root", "123456");
			//创建sql命令
			String sql = "select * from t_user where uname = ? and pwd = ?";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//占位符赋值
			ps.setString(1, uname);
			ps.setString(2, pwd);
			//执行sql
			rs = ps.executeQuery();
			//遍历结果
				while(rs.next()){
					//给变量赋值
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
			//关闭资源
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
		//返回获取对象
		return u;
	}
	//根据用户id修改用户密码
	@Override
	public int userChangePwdDao(String newPwd, int uid) {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		//创建变量
		int index = -1;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/servlettest", "root", "123456");
			//创建Sql命令
			String sql = "update t_user set pwd=? where uid=?";
			//创建Sql命令对象
			ps = conn.prepareStatement(sql);
			//占位符赋值
			ps.setString(1, newPwd);
			ps.setInt(2, uid);
			//执行
			index = ps.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭资源
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
		
		//返回结果
		return index;
	}
	@Override
	public List<User> showUserDao() {
		//声明jdbc对象
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null; 
				//声明变量
				List<User> lu = null;
				try {
					//加载驱动
					Class.forName("com.mysql.jdbc.Driver");
					//获取连接
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/servlettest", "root", "123456");
					//创建sql命令
					String sql = "select * from t_user";
					//创建sql命令对象
					ps = conn.prepareStatement(sql);
					//执行sql
					rs = ps.executeQuery();
					lu = new ArrayList<User>();
					//遍历结果
						while(rs.next()){
							//给变量赋值
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
					//关闭资源
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
				//返回获取对象
				return lu;
	}
	@Override
	public int userRegDao(User u) {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		//声明变量
		int index = -1;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/servlettest", "root", "123456");
			//创建sql命令
			String sql = "insert into t_user values(default,?,?,?,?,?)";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//占位符赋值
			ps.setString(1, u.getUname());
			ps.setString(2, u.getPwd());
			ps.setString(3, u.getSex());
			ps.setInt(4, u.getAge());
			ps.setString(5, u.getBirth());
			//执行
			index = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭资源
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
		//返回结果
		return index;
	}

}
