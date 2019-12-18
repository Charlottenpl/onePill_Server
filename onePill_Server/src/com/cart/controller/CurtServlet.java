package com.cart.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.dao.cartsDao;
import com.entity.Address;
import com.google.gson.Gson;

/**
 * Servlet implementation class CurtServlet
 */
@WebServlet("/CurtServlet")
public class CurtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean f = false;
	cartsDao cartsDao = new cartsDao();
	Gson gson = new Gson();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CurtServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("购物车服务器:");
		String code = request.getParameter("Code");
		switch (code) {
		case "add":
			// 添加地址
			int userId = Integer.valueOf(request.getParameter("userId"));
			int medicineId = Integer.valueOf(request.getParameter("medicineId"));
			int price = Integer.valueOf(request.getParameter("price"));
			int count = 2;
			int status = 1;
			try {
				f = cartsDao.add(userId, medicineId, price, count, status);
				System.out.println(medicineId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (f) {
				response.getWriter().append("yes");
			} else {
				response.getWriter().append("no");
			}
			break;
		default:
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
