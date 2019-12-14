package com.user.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Doctor;
import com.user.dao.DoctorDao;
import com.user.dao.UserDao;

/**
 * Servlet implementation class EditDoctorServlet
 */
@WebServlet("/EditDoctorServlet")
public class EditDoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean f = false;//操作结果
	private DoctorDao dao = new DoctorDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDoctorServlet() {
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
		System.out.print("医生信息服务器");
		String code = request.getParameter("Code");
		int DoctorId = Integer.valueOf(request.getParameter("doctorId"));//request:Code+UserId+要更改的部分,response:返回yes或no
		
		System.out.println("Code"+code+"UserId"+DoctorId);
		switch (code) {
		case "name":
			//更新昵称
			String name1 = request.getParameter("name");
			//存入数据库
			try {
				f = dao.editDoctorkName(DoctorId, name1);
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
				f = dao.editDoctorPhone(DoctorId, phone);
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
				f = dao.editDoctorPassword(DoctorId, password);
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
				f = dao.editDoctorPID(DoctorId, PID);
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
				f = dao.editDoctorHeadimg(DoctorId, headimg);
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
