package com.user.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Address;
import com.user.dao.UserDao;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean f = false;//操作结果
	private UserDao dao = new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		System.out.print("用户信息服务器");
		String code = request.getParameter("Code");
		int UserId = Integer.valueOf(request.getParameter("UserId"));//request:Code+UserId+要更改的部分,response:返回yes或no
		
		System.out.println("Code"+code+"UserId"+UserId);
		switch (code) {
		case "nickName":
			//更新昵称
			String name1 = request.getParameter("nickName");
			//存入数据库
			try {
				f = dao.editUserNickName(UserId, name1);
				System.out.println(f);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (f) {
				response.getWriter().append("yes");
			}else {
				response.getWriter().append("no");
			}
			break;
		case "phone":
			//更新手机号
			String phone = request.getParameter("phone");
			//存入数据库
			try {
				f = dao.editUserNickName(UserId, phone);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (f) {
				response.getWriter().append("yes");
			}else {
				response.getWriter().append("no");
			}
			break;
		case "password":
			//更新密码
			String password = request.getParameter("password");
			//存入数据库
			try {
				f = dao.editUserPassword(UserId, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (f) {
				response.getWriter().append("yes");
			}else {
				response.getWriter().append("no");
			}
			break;
		case "PID":
			//更新PID
			String PID = request.getParameter("PID");
			//存入数据库
			try {
				f = dao.editUserPID(UserId, PID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (f) {
				response.getWriter().append("yes");
			}else {
				response.getWriter().append("no");
			}
			break;
		case "headimg":
			//更新PID
			String headimg = request.getParameter("headimg");
			//存入数据库
			try {
				f = dao.editUserHeadimg(UserId, headimg);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (f) {
				response.getWriter().append("yes");
			}else {
				response.getWriter().append("no");
			}
			break;
		case "address":
			System.out.println("address不用修改");
			break;

		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
