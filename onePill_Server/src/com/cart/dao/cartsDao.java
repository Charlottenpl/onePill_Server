package com.cart.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.entity.focus;
import com.util.DbUtil;

public class cartsDao {
	
	/**
	 * 添加
	 * @param userId
	 * @param medicineId
	 * @param price
	 * @param count
	 * @param status
	 * @return
	 * @throws SQLException
	 */
	public boolean add(int userId,int medicineId,int price,int count,int status ) throws SQLException {
		
		String valuesName = "userId,medicineId,price,count,status";
		String values = userId+","+medicineId+","+price+","+count+","+status+"";
		
		Boolean fus = false;//操作结果
		int i = 0;
		java.sql.Connection connection = null;
		PreparedStatement pstm = null;
		
		connection = DbUtil.getCon();
		String sql = String.format("insert into tbl_focus"
				+ " (%s) values (%s)",valuesName,values);
		//取消自动提交
		connection.setAutoCommit(false);
		pstm = connection.prepareStatement(sql);
		i = pstm.executeUpdate();
		pstm.close();
		connection.commit();
		if(i!=0)
			fus = true;
		else
			fus = false;
		
		return fus;
		
	}
}
