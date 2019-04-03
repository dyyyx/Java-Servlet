package com.javaee.servlet;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.javaee.pojo.User;
import com.javaee.service.UserService;
import com.javaee.service.impl.UserServiceImpl;
/**
 * Servlet重定向：
 * 		第一个/表示服务器根目录
 * 		resp.sendRedirect("/虚拟项目名/资源路径");
 * Servlet请求转发：
 * 		/表示项目根目录
 * 		req.getRequestDispatcher("/资源路径").forward(req,resp);
 * @author 979739537
 *
 */
public class UserServlet extends HttpServlet {
	//获取service层对象
	UserService us = new UserServiceImpl();
	//声明日志对象
	Logger logger = Logger.getLogger(UserServlet.class);
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		//获取请求信息
		String oper = req.getParameter("oper");
		if("login".equals(oper)){
			//调用登录处理方法处理请求信息
			checkUserLogin(req,resp);
		}else if("out".equals(oper)){
			//调用退出方法
			userOut(req,resp);
		}else if("pwd".equals(oper)){
			//调用修改密码方法
			userChangePwd(req,resp);
		}else if("show".equals(oper)){
			//调用查询所有用户方法
			showUser(req,resp);
		}else if("reg".equals(oper)){
			//调用注册方法
			userReq(req,resp);
		}else{
			logger.debug("没有找到对应的操作符:"+oper);
		}
	}

	private void userReq(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取请求信息
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		String sex = req.getParameter("sex");
		int age = req.getParameter("age")!=""?Integer.parseInt(req.getParameter("age")):0;
		String birth = req.getParameter("birth");
		String bs[] = null; 
		if(birth!=""){
			bs = birth.split("/");
			birth = bs[2]+"-"+bs[0]+"-"+bs[1];
		}
		System.out.println(uname+"-"+pwd+"-"+sex+"-"+age+"-"+birth);
		User u = new User(0,uname,pwd,sex,age,birth);
		//处理请求信息
			//调用业务层处理
		int index = us.userRegService(u);
		//响应处理结果
		if(index>0){
			//获取session对象
			HttpSession hs = req.getSession();
			hs.setAttribute("reg", "true");
			//重定向
			resp.sendRedirect("/mag/login.jsp");
		}
		
	}

	private void showUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//处理请求
		List<User> lu = us.showUserService();
		req.setAttribute("lu", lu);
		req.getRequestDispatcher("/user/showUser.jsp").forward(req, resp);
		return;
	}

	private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取数据
		String newPwd = req.getParameter("newPwd");
		//从session中获取用户信息
		User u = (User) req.getSession().getAttribute("user");
		int uid = u.getUid();
		//处理请求
			//调用service处理
		int index = us.userChangePwdService(newPwd,uid);
		if(index>0){
			//获取Session对象
			HttpSession hs = req.getSession();
			hs.setAttribute("pwd", "true");
			resp.sendRedirect("/mag/login.jsp");
			return;
		}
	}

	private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取session对象并销毁
		HttpSession hs = req.getSession();
		hs.invalidate();
		//重定向
		resp.sendRedirect("/mag/login.jsp");
		return;
	}

	//处理登录
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//获取请求信息
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		
		//处理请求信息
		
		User u = us.checkUserLoginService(uname, pwd);
		if(u!=null){
			//获取Session对象
			HttpSession hs = req.getSession();
			//将用户信息存储到session对象中
			hs.setAttribute("user", u);
			//重定向
			resp.sendRedirect("/mag/main/main.jsp");
			return;
		}else{
			//添加标识符到request中
			req.setAttribute("flag", 0);
			//请求转发
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			return;
		}
		//响应处理信息
			//直接响应
			//请求转发
			
	}
}
