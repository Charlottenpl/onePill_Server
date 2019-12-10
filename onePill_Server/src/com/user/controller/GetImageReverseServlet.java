package com.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class GetImageReverseServlet
 */
@WebServlet("/GetImageReverseServlet")
public class GetImageReverseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetImageReverseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// Parse the request
			List<FileItem> /* FileItem */ items = upload.parseRequest(request);
			System.out.println(items.size()+"");
			for (FileItem fi : items) {
				if (fi.isFormField()) {
					// 表单
					String temp = fi.getString();
					temp = new String(temp.getBytes("ISO-8859-1"), "UTF-8");
					System.out.println(fi.getFieldName() + ":" + temp);
				} else {
					// 文件
					String name = fi.getName();
					String rootPath = this.getServletContext().getRealPath("/");
					File file = new File(rootPath+"image/");//在根路径下创建存放图片的文件夹
					if(!file.exists())//如果不存在，创建
						file.mkdirs();
					File realPath = new File(file,name);//图片的实际路径
					fi.write(realPath);
					System.out.println(realPath.toString());
					String imgLocation = "image/"+name;
					ServletContext context = getServletContext();
					context.setAttribute("imageReverse", imgLocation);
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 返回响应
		response.getWriter().append("上传成功");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
