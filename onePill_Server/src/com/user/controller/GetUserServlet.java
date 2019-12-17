package com.user.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Address;
import com.entity.Doctor;
import com.google.gson.Gson;
import com.user.dao.DoctorDao;
import com.user.dao.UserDao;

/**
 * Servlet implementation class GetUserServlet
 */
@WebServlet("/GetUserServlet")
public class GetUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userdao = new UserDao();
	Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserServlet() {
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
				System.out.println("查询服务器:");
				String code = request.getParameter("Code");
				Doctor doctor = null;
				switch (code) {
				case "searchDoctorById":
					int id = Integer.valueOf(request.getParameter("doctorId"));
					try {
						doctor = userdao.searchDoctorById(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String json = null;
					json = gson.toJson(doctor);
					System.out.println(""+json);
				response.getWriter().append(json);
					
					break;
				case "searchDoctorByName":
					String name = request.getParameter("name");
					try {
						doctor = userdao.searchDoctorByName(name);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					String json2 = null;
					json2 = gson.toJson(doctor);
					System.out.println(json2);
					response.getWriter().append(json2);
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
