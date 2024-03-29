package com.user.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.entity.User;
import com.google.gson.Gson;
import com.user.service.UserService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterPatientServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		InputStream is = request.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
		StringBuffer stringBuffer = new StringBuffer();
		String str = null;
		while ((str = reader.readLine()) != null) {
			stringBuffer.append(str);
		}
		Gson gson = new Gson();
		User newuser = gson.fromJson(stringBuffer.toString(), User.class);
		System.out.println(newuser.toString());
		UserService service = new UserService();
		Boolean isSuccessful = service.RegisterPatientService("nickName,phone,password,PID,headImg,address",
				"'" + newuser.getNickName() + "','" + newuser.getPhone() + "','" + newuser.getPassword() + "','"
						+ newuser.getPID() + "','"+"image/buyer.jpg"+"','"
						+ newuser.getAddress() + "'");
		if (isSuccessful) {
			String result = gson.toJson(true);
			System.out.println("病人注册成功！");
			response.getWriter().append(result);

		} else {
			String result = gson.toJson(false);
			System.out.println("病人注册失败！");
			response.getWriter().append(result);
		}
	}

}
