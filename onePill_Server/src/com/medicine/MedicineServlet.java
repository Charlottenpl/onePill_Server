package com.medicine;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Medicine;
import com.google.gson.Gson;
import com.user.dao.DoctorDao;

/**
 * Servlet implementation class MedicineServlet
 */
@WebServlet("/MedicineServlet")
public class MedicineServlet extends HttpServlet {
	private boolean f = false;//操作结果
	private MedicineDao dao = new MedicineDao();
	Gson gson = new Gson();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedicineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("utf-8");
				System.out.print("药品信息服务器");
				String code = request.getParameter("Code");
				
				System.out.println("Code"+code);
				switch (code) {
				case "searchMedicineByName":
					Medicine medicine = null;
					System.out.println("进入这里查找药品");
					String name = request.getParameter("name");
					
					try {
						medicine = dao.searchMedicineByName(name);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String json = gson.toJson(medicine);
					System.out.println(json.toString());
					response.getWriter().append(json);
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
