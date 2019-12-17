package com.cart.controller;

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

import com.cart.service.CartService;
import com.entity.Cart;
import com.entity.Medicine;

/**
 * Servlet implementation class ShowCartListServlet
 */
@WebServlet("/buyer/cart/list")
public class ShowCartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCartListServlet() {
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
		// 1. 获取传过来的账户账号信息
		InputStream is = request.getInputStream();
		byte[] temp = new byte[255];
		int len = is.read(temp);
		String read =new String(temp,0,len);
		JSONObject readObj = new JSONObject(read);
		int buyerId = readObj.getInt("buyerId");
		// 2. 根据账号信息查询其所有的cart
		CartService buyerServiceImp = new CartService();
		List<Cart> carts = buyerServiceImp.findCartsByBuyerId(buyerId);
		
		JSONArray jsonArray = new JSONArray();
		for(Cart cart:carts){
			JSONObject object = new JSONObject();
			Medicine medicine = buyerServiceImp.findMedicineById(cart.getMedicineId());
			object.put("medicineId", cart.getMedicineId());
			object.put("count", cart.getCount());
			object.put("id", cart.getId());
			object.put("status", cart.getStatus());
			object.put("generaname",medicine.getGeneralName());
			object.put("medicineName",medicine.getMedicine());
			object.put("price",medicine.getPrice());
			object.put("overView",medicine.getOverview());
			object.put("introdution",medicine.getIntrodutions());
			object.put("forbiddancet", medicine.getForbiddance());
			object.put("sideeffect", medicine.getSideEffect());
			object.put("size", medicine.getStandard());
			object.put("stock", medicine.getStock());
			jsonArray.put(object);
		}
		// 3. 将搜索到的cartlist发送给客户端
		OutputStream os = response.getOutputStream();
		os.write(jsonArray.toString().getBytes());
		os.close();
		is.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
