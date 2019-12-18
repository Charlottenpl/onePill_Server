package com.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Comment;
import com.entity.Medicine;
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
		String sql = String.format("insert into tbl_cart"
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
	public List<Medicine> findAllMedicineInfo(int medicineId){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Medicine> list = new ArrayList<>();
		try{
			con = DbUtil.getCon();
			String sql = String.format("select * from tbl_medicine where id = %s",medicineId);
			System.out.println(sql);
			// 取消自动提交
			con.setAutoCommit(false);
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Medicine medicine = new Medicine();
				medicine.setId(rs.getInt(1));
				medicine.setGeneralName(rs.getString(2));
				medicine.setMedicine(rs.getString(3));
				medicine.setPrice(rs.getString(4));
				medicine.setOverview(rs.getString(5));
				medicine.setFunction(rs.getString(6));
				medicine.setSideEffect(rs.getString(7));
				medicine.setForbiddance(rs.getString(8));
				medicine.setDoctorId(rs.getInt(9));
				medicine.setImg1(rs.getString(10));
				medicine.setStandard(rs.getString(11));
				medicine.setImg2(rs.getString(12));
				medicine.setImg3(rs.getString(13));
				medicine.setIntrodutions(rs.getString(14));
				medicine.setStock(rs.getInt(15));
				list.add(medicine);
			}
			System.out.println(list.get(0).getForbiddance());
			con.commit();
			rs.close();
			pstm.close();
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbUtil.close(con);
		}
		return null;
	}
}
