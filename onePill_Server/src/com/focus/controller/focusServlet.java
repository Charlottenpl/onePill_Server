package com.focus.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Doctor;
import com.entity.Medicine;
import com.entity.focus;
import com.focus.dao.focusDao;
import com.google.gson.Gson;

/**
 * Servlet implementation class focusServlet
 */
@WebServlet("/focusServlet")
public class focusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	boolean b = false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public focusServlet() {
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
		System.out.println("收藏服务器:");
		String code = request.getParameter("Code");
		focusDao fDao = new focusDao();
		List<Doctor> doctorList = new ArrayList<Doctor>();
		List<Medicine> medicineList = new ArrayList<Medicine>();
		switch (code) {
		case "searchDoctorList":
			
			int userId = Integer.valueOf(request.getParameter("userId"));
			int userType = Integer.valueOf(request.getParameter("userType"));
			System.out.println("打印Id"+userId+userType);
			try {
				doctorList = fDao.searchAllDoctor(userId,userType);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String json = null;
			json = gson.toJson(doctorList);
			System.out.println(""+json);
		response.getWriter().append(json);
			
			break;
		case "searchMedicineList":
			int userId1 = Integer.valueOf(request.getParameter("userId"));
			int userType1 = Integer.valueOf(request.getParameter("userType"));
			try {
				medicineList = fDao.searchAllMedicine(userId1,userType1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String json2 = null;
			json2 = gson.toJson(medicineList);
			System.out.println(json2);
			response.getWriter().append(json2);
			break;
		case "add":
			int userId2 = Integer.valueOf(request.getParameter("userId"));
			int userType2 = Integer.valueOf(request.getParameter("userType"));
			int type2 = Integer.valueOf(request.getParameter("type"));
			int typeId2 = Integer.valueOf(request.getParameter("typeId"));
			focus f = new focus( userId2, userType2, type2, typeId2);
			try {
				b = fDao.add(f);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!b) {
				response.getWriter().append("yes");
			}else {
				response.getWriter().append("no");
			}
			break;
		case "del":
			int userId3 = Integer.valueOf(request.getParameter("userId"));
			int userType3 = Integer.valueOf(request.getParameter("userType"));
			int type3 = Integer.valueOf(request.getParameter("type"));
			int typeId3 = Integer.valueOf(request.getParameter("typeId"));
			try {
				b = fDao.del(userId3,userType3,type3,typeId3);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!b) {
				response.getWriter().append("yes");
			}else {
				response.getWriter().append("no");
			}
			break;
		case "isHave":
			int userId4 = Integer.valueOf(request.getParameter("userId"));
			int userType4 = Integer.valueOf(request.getParameter("userType"));
			int type4 = Integer.valueOf(request.getParameter("type"));
			int typeId4 = Integer.valueOf(request.getParameter("typeId"));
			try {
				b=fDao.isHave(userId4, userType4, type4, typeId4);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(b) {
				response.getWriter().append("yes");
			}else {
				response.getWriter().append("no");
			}
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
