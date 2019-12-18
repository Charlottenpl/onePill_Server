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
	
		public boolean add(int userId,int medicineId,int count,int number,int status) throws SQLException {
		
			Boolean f = false;//操作结果
			int i = 0;
			java.sql.Connection connection = null;
			PreparedStatement pstm = null;
			
			String valuesName = "userId,medicineId,count,number,status";
			String values = ""+userId+","+medicineId+","+count+","+number+","+status+"";
		
			connection = DbUtil.getCon();
			String sql = String.format("insert into tbl_order"
					+ " (%s) values (%s)",valuesName,values);
			//取消自动提交
			connection.setAutoCommit(false);
			pstm = connection.prepareStatement(sql);
			i = pstm.executeUpdate();
			pstm.close();
			connection.commit();
			if(i!=0)
				f = true;
			else
				f = false;
			
			return f;
			
		}
	
	
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
			int status = rs.getInt("status");
			int price = Integer.valueOf(mDao.searchMedicineById(medicineId).getPrice());
			String imgString = mDao.searchMedicineById(medicineId).getImg1();
			
			orders = new Orders(Id,userId,medicineId,count,imgString,price,status);
			ordersList.add(orders);
			
		}
		
		pstm.close();
		connection.commit();
		return ordersList;
	}

}
