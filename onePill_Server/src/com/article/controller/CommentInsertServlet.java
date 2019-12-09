package com.article.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.service.ArticleService;
import com.entity.Comment;
import com.entity.Doctor;
import com.google.gson.Gson;

/**
 * Servlet implementation class CommentUpdateServlet
 */
@WebServlet("/CommentInsertServlet")
public class CommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsertServlet() {
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
        Comment comment = gson.fromJson(stringBuffer.toString(),Comment.class);
        Boolean isSuccessful = new ArticleService().CommentInsertService("name,ccomment,headImg,articleId", "'"+comment.getName()+
        		"','"+comment.getCcomment()+"','"+null+"','"+comment.getArticleId()+"'");
        if(isSuccessful){
			String result = gson.toJson(true);
			response.getWriter().append(result);
			
		}else{
			String result = gson.toJson(false);
			response.getWriter().append(result);
		}
	}

}
