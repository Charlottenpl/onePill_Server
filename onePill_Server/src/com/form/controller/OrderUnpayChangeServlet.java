package com.form.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.entity.Order;
import com.form.service.OrderService;

/**
 * Servlet implementation class OrderUnpayChangeServlet
 */
@WebServlet("/buyer/order/unpay")
public class OrderUnpayChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderUnpayChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		InputStream is = request.getInputStream();
		byte[] temp = new byte[255];
		int len;
		StringBuffer sb = new StringBuffer();
		while((len=is.read(temp))!=-1){
			sb.append(new String(temp,0,len));
		}
		String read = new String(sb);
		JSONObject getObj = new JSONObject(read);
		int orderId = getObj.getInt("orderId");
		int changeType = getObj.getInt("changeType");
		int medicineId = getObj.getInt("medicineId");
		int count = getObj.getInt("stock");
		int updateRow = 0;
		if(changeType == Order.TYPE_UNPAY || changeType == Order.TYPE_WAITGET){
			OrderService buyerServiceImp = new OrderService();
			updateRow = buyerServiceImp.updateOrderType(orderId, changeType,medicineId,count);
		}
		OutputStream os = response.getOutputStream();
		os.write((updateRow+"").getBytes());
		is.close();
		os.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
