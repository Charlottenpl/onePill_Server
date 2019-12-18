package com.order.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.address.dao.AddressDao;
import com.entity.Address;
import com.entity.Orders;
import com.form.dao.OrderDao;
import com.google.gson.Gson;
import com.order.dao.OrdersDao;

/**
 * Servlet implementation class OrdersServlet
 */
@WebServlet("/OrdersServlet")
public class OrdersServlet extends HttpServlet {
	private boolean f = false;
	private OrdersDao dao = new OrdersDao();
	private Gson gson = new Gson();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("utf-8");
				System.out.println("订单服务器:");
				String code = request.getParameter("Code");
				switch (code) {
				case "searchAll":
					int userId = Integer.parseInt(request.getParameter("UserId"));
					List<Orders> ordersList = new ArrayList<Orders>();
					try {
						ordersList = dao.searchAll(userId);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						response.getWriter().append(null);
					}
					response.getWriter().append(gson.toJson(ordersList));
					break;
				case "add":
					int userId1 = Integer.parseInt(request.getParameter("UserId"));
					String imgString = request.getParameter("img");
					int medicineId1 = Integer.parseInt(request.getParameter("medicineId"));
					int count1 = Integer.parseInt(request.getParameter("count"));
					int number1 = Integer.parseInt(request.getParameter("number"));
					int status1 = Integer.parseInt(request.getParameter("status"));
					try {
						dao.add(userId1,medicineId1,count1,number1,status1,imgString);
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
