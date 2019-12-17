package com.form.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.form.dao.DBUtilForm;
import com.util.DbUtil;

/**
 * Servlet implementation class FormListServlet
 */
@WebServlet("/FormListServlet")
public class FormListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
				
		List<Map<String,Object>> list = DBUtilForm.findAll("tbl_order");
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<list.size();i++){
			Map<String,Object> map = list.get(i);
			
			JSONObject obj = new JSONObject();
			for(String key:map.keySet()){
				obj.put(key, map.get(key));
			}
			System.out.print("list"+list);
			jsonArray.put(obj);
		}
		
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
