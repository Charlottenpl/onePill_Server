package com.medicine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.Doctor;
import com.entity.Medicine;
import com.util.DbUtil;

public class MedicineDao {

	/**
	 * 通过Name查询药品
	 * @throws SQLException 
	 */
	
	public Medicine searchMedicineByName(String name) throws SQLException {
		Medicine d = null;
		String sql = "select * from tbl_medicine where medicine = "+name;
		System.out.println(""+sql);
		java.sql.Connection connection = null;
		PreparedStatement pstm = null;
		connection = DbUtil.getCon();
		//取消自动提交
		connection.setAutoCommit(false);
		pstm = connection.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		if(rs.next()){
			int id = rs.getInt("id");
			String generalName = rs.getString("generalName");
			String medicine = rs.getString("medicine");
			String price = rs.getString("price");
			String overview = rs.getString("overview");
			String function = rs.getString("function");
			String introduction = rs.getString("introduction");
			String side_effect = rs.getString("side_effect");
			String forbiddance = rs.getString("forbiddance");
			int doctor_id = rs.getInt("doctor_id");
			String img = rs.getString("img");
			String standard = rs.getString("standard");
			d = new Medicine(id,generalName,medicine,price,overview,function,introduction,side_effect,forbiddance,doctor_id,img,standard);
		}
		return d;		
	}
}
