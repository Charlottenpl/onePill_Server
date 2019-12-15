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
 * Servlet implementation class OrderUnsendNewServlet
 */
@WebServlet("/buyer/order/new/unsend")
public class OrderUnsendNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderUnsendNewServlet() {
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
		Order order = new Order();
		order.setAddressId(getObj.getInt("addressId"));
		order.setBuyerId(getObj.getInt("buyerId"));
		order.setMedicineId(getObj.getInt("medicineId"));
		order.setCount(getObj.getInt("count"));
		order.setDoctorId(getObj.getInt("doctorId"));
		order.setType(getObj.getInt("type"));
		OrderService buyerServiceImp = new OrderService();
		int updateRow = buyerServiceImp.insertNewOrder(order);
		
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
