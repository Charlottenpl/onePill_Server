package com.form.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.entity.Address;
import com.entity.Medicine;
import com.entity.Order;
import com.form.service.OrderService;

/**
 * Servlet implementation class OrderPaShowServlet
 */
@WebServlet("/buyer/order/show")
public class OrderPaShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderPaShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		// 先获取传过来的buyerId，以及其搜索的状态位
		InputStream is = request.getInputStream();
		byte[] temp = new byte[255];
		int len;
		StringBuffer sb = new StringBuffer();
		while((len=is.read(temp))!=-1){
			sb.append(new String(temp,0,len));
		}
		String get = new String(sb);
		JSONObject getObj = new JSONObject(get);
		int buyerId = getObj.getInt("buyerId");
		String type = getObj.getString("listType");
		OrderService orderService = new OrderService();
		// 根据状态位获取列表
		List<Order> orders = null;
		switch (type) {
		case "all":
			orders = orderService.findOrderByBuyerIdAndType(buyerId, Order.SELECT_ALL);
			break;
		case "unpay":
			orders = orderService.findOrderByBuyerIdAndType(buyerId, Order.TYPE_UNPAY);
			break;
		case "unsend":
			orders = orderService.findOrderByBuyerIdAndType(buyerId, Order.TYPE_UNSEND);
			break;
		case "waitget":
			orders = orderService.findOrderByBuyerIdAndType(buyerId, Order.TYPE_WAITGET);
			break;
		case "finish":
			orders = orderService.findOrderByBuyerIdAndType(buyerId, Order.TYPE_FINISH);
			break;
		}
		// 将获取的order列表用JSONArray封装好
		JSONArray jsonArray = new JSONArray();
		for(Order order : orders){
			// 获取这个 order对应的地址,以及对应的蛋糕
			Medicine medicine = orderService.findMedicineById(order.getMedicineId());
			Address address = orderService.findOrderAddress(order.getAddressId());
			// 封装数据并发送
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", order.getId());
			jsonObject.put("medicineId", order.getMedicineId());
			jsonObject.put("count", order.getCount());
			jsonObject.put("type", order.getType());
			jsonObject.put("addressId", order.getAddressId());
			
			jsonObject.put("medicineName",medicine.getMedicine());
			jsonObject.put("medicineSize", medicine.getStandard());
			jsonObject.put("medicineGeneraName", medicine.getGeneralName());
			jsonObject.put("medicinePrice", medicine.getPrice());
			jsonObject.put("medicineForbiddance", medicine.getForbiddance());
			jsonObject.put("medicineIntrodutions", medicine.getIntrodutions());
			jsonObject.put("medicineStock",medicine.getStock());
			jsonObject.put("medicineFunction",medicine.getFunction());
			jsonObject.put("medicineOverView", medicine.getOverview());
			jsonObject.put("medicineSideEffect", medicine.getSideEffect());
			
			jsonObject.put("addressId", address.getId());
			jsonObject.put("addressName",address.getName());
			jsonObject.put("addressPhone", address.getPhoneNumber());
			jsonObject.put("addressMore", address.getMore());
			jsonObject.put("addressDetail", address.getAddress());
			jsonObject.put("addressCode", address.getPostalCode());
					
			jsonArray.put(jsonObject);
		}
		// 将封装好的jsonArray发送出去
		OutputStream os = response.getOutputStream();
		os.write(jsonArray.toString().getBytes());
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
