package com.inquiry.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.entity.Inquiry;
import com.google.gson.Gson;
import com.inquiry.dao.inquiryDao;

/**
 * Servlet implementation class InquiryServlet
 */
@WebServlet("/InquiryServlet")
public class InquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private inquiryDao Dao = new inquiryDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		InputStream is = request.getInputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(is,"utf-8"));
        StringBuffer stringBuffer=new StringBuffer();
        String str=null;
        while((str=reader.readLine()) != null) {
            stringBuffer.append(str);
        }
        Gson gson=new Gson();
        Inquiry inquiry = gson.fromJson(stringBuffer.toString(),Inquiry.class); 
        System.out.println(inquiry.getUserId());
        String location = (String) getServletContext().getAttribute("img");
        try {
			Boolean isSuccessful = new inquiryDao().add("user_id,title,content,flag,img,time",
					"'"+inquiry.getUserId()+"','"+inquiry.getTitle()+"','"+inquiry.getContent()+"',"+1+",'"+location+"','"+inquiry.getTime()+"'");
			response.getWriter().append("成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
