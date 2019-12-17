package com.order.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Address;
import com.entity.Orders;
import com.medicine.MedicineDao;
import com.util.DbUtil;

public class OrdersDao {
	
	/**
	 * 查询订单
	 * @param UserId
	 * @return
	 * @throws SQLException
	 */
	public List<Orders> searchAll(int UserId) throws SQLException{
		MedicineDao mDao = new MedicineDao();
		List<Orders> ordersList = new ArrayList<Orders>();
		String sql = "select * from tbl_order where userId = "+UserId;
		Orders orders = null;
		java.sql.Connection connection = null;
		PreparedStatement pstm = null;
		
		connection = DbUtil.getCon();
		//取消自动提交
		connection.setAutoCommit(false);
		pstm = connection.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
			
		while (rs.next()) {
			int Id = rs.getInt("id");
			int userId = UserId;
			int medicineId = rs.getInt("medicineId");
			int count = rs.getInt("count");
			int price = rs.getInt("price");
			String imgString = mDao.searchMedicineById(medicineId).getImg1();
			orders = new Orders(Id,userId,medicineId,count,imgString,price);
			ordersList.add(orders);
			
		}
		
		pstm.close();
		connection.commit();
		return ordersList;
	}

}
