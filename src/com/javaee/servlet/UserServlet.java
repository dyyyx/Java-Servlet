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
 * Servlet�ض���
 * 		��һ��/��ʾ��������Ŀ¼
 * 		resp.sendRedirect("/������Ŀ��/��Դ·��");
 * Servlet����ת����
 * 		/��ʾ��Ŀ��Ŀ¼
 * 		req.getRequestDispatcher("/��Դ·��").forward(req,resp);
 * @author 979739537
 *
 */
public class UserServlet extends HttpServlet {
	//��ȡservice�����
	UserService us = new UserServiceImpl();
	//������־����
	Logger logger = Logger.getLogger(UserServlet.class);
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//������������ʽ
		req.setCharacterEncoding("utf-8");
		//������Ӧ�����ʽ
		resp.setContentType("text/html;charset=utf-8");
		//��ȡ������Ϣ
		String oper = req.getParameter("oper");
		if("login".equals(oper)){
			//���õ�¼����������������Ϣ
			checkUserLogin(req,resp);
		}else if("out".equals(oper)){
			//�����˳�����
			userOut(req,resp);
		}else if("pwd".equals(oper)){
			//�����޸����뷽��
			userChangePwd(req,resp);
		}else if("show".equals(oper)){
			//���ò�ѯ�����û�����
			showUser(req,resp);
		}else if("reg".equals(oper)){
			//����ע�᷽��
			userReq(req,resp);
		}else{
			logger.debug("û���ҵ���Ӧ�Ĳ�����:"+oper);
		}
	}

	private void userReq(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//��ȡ������Ϣ
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
		//����������Ϣ
			//����ҵ��㴦��
		int index = us.userRegService(u);
		//��Ӧ������
		if(index>0){
			//��ȡsession����
			HttpSession hs = req.getSession();
			hs.setAttribute("reg", "true");
			//�ض���
			resp.sendRedirect("/mag/login.jsp");
		}
		
	}

	private void showUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��������
		List<User> lu = us.showUserService();
		req.setAttribute("lu", lu);
		req.getRequestDispatcher("/user/showUser.jsp").forward(req, resp);
		return;
	}

	private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//��ȡ����
		String newPwd = req.getParameter("newPwd");
		//��session�л�ȡ�û���Ϣ
		User u = (User) req.getSession().getAttribute("user");
		int uid = u.getUid();
		//��������
			//����service����
		int index = us.userChangePwdService(newPwd,uid);
		if(index>0){
			//��ȡSession����
			HttpSession hs = req.getSession();
			hs.setAttribute("pwd", "true");
			resp.sendRedirect("/mag/login.jsp");
			return;
		}
	}

	private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//��ȡsession��������
		HttpSession hs = req.getSession();
		hs.invalidate();
		//�ض���
		resp.sendRedirect("/mag/login.jsp");
		return;
	}

	//�����¼
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//��ȡ������Ϣ
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		
		//����������Ϣ
		
		User u = us.checkUserLoginService(uname, pwd);
		if(u!=null){
			//��ȡSession����
			HttpSession hs = req.getSession();
			//���û���Ϣ�洢��session������
			hs.setAttribute("user", u);
			//�ض���
			resp.sendRedirect("/mag/main/main.jsp");
			return;
		}else{
			//��ӱ�ʶ����request��
			req.setAttribute("flag", 0);
			//����ת��
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			return;
		}
		//��Ӧ������Ϣ
			//ֱ����Ӧ
			//����ת��
			
	}
}
