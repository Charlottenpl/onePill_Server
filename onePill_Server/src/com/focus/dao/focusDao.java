package com.focus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Address;
import com.entity.Doctor;
import com.entity.focus;
import com.medicine.MedicineDao;
import com.user.dao.DoctorDao;
import com.util.DbUtil;

public class focusDao {
	
		public List<focus> searchAll(int UserId,int UserType) throws SQLException{
			List<focus> focusList = new ArrayList<focus>();
			String sql = "select * from tbl_focus where userId = "+UserId+"and userType="+UserType;
			focus f = null;
			java.sql.Connection connection = null;
			PreparedStatement pstm = null;
			DoctorDao doctorDao = new DoctorDao();
			MedicineDao medicineDao = new MedicineDao();
			
			connection = DbUtil.getCon();
			//取消自动提交
			connection.setAutoCommit(false);
			pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("userId");
				int userType = rs.getInt("userType");
				int type = rs.getInt("type");
				int typeId = rs.getInt("typeId");
				
			/*
			 * String name = rs.getString("name"); String phoneNumber =
			 * rs.getString("phoneNumber"); String address = rs.getString("address"); String
			 * more = rs.getString("more"); String postalCode = rs.getString("postalCode");
			 */
				f = new focus(id, userId, userType, type, typeId);
				focusList.add(f);
				
			}
			
			pstm.close();
			connection.commit();
			return focusList;
		}
}
