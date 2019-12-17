package com.focus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Address;
import com.entity.Doctor;
import com.entity.Medicine;
import com.entity.focus;
import com.medicine.MedicineDao;
import com.user.dao.DoctorDao;
import com.user.dao.UserDao;
import com.util.DbUtil;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

public class focusDao {
	
	/**
	 * 获取收藏医生列表
	 * @param UserId
	 * @param UserType
	 * @return doctorList
	 * @throws SQLException
	 */
		public List<Doctor> searchAllDoctor(int UserId,int UserType) throws SQLException{
			List<Doctor> doctorList = new ArrayList<Doctor>();
			String sql = "select typeId from tbl_focus where userId = "+UserId+"and userType="+UserType+"&&type=1";
			UserDao userDao = new UserDao();
			java.sql.Connection connection = null;
			PreparedStatement pstm = null;
			MedicineDao medicineDao = new MedicineDao();
			connection = DbUtil.getCon();
			//取消自动提交
			connection.setAutoCommit(false);
			pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("typeId");
				doctorList.add(userDao.searchDoctorById(id));
			}
			pstm.close();
			connection.commit();
			return doctorList;
		}
		
		/**
		 * 获取收藏药品列表
		 * @param UserId
		 * @param UserType
		 * @return
		 * @throws SQLException
		 */
		public List<Medicine> searchAllMedicine(int UserId,int UserType) throws SQLException{
			List<Medicine> medicineList = new ArrayList<Medicine>();
			String sql = "select typeId from tbl_focus where userId = "+UserId+"and userType="+UserType+"&&type=2";
			MedicineDao medicineDao = new MedicineDao();
			java.sql.Connection connection = null;
			PreparedStatement pstm = null;
			connection = DbUtil.getCon();
			//取消自动提交
			connection.setAutoCommit(false);
			pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("typeId");
				medicineList.add(medicineDao.searchMedicineById(id));
			}
			pstm.close();
			connection.commit();
			return medicineList;
		}
		
		
		/**
		 * 取消收藏
		 * @param Id
		 * @return
		 * @throws SQLException
		 */
		public boolean del(int Id) throws SQLException {
			
			Boolean f = false;//操作结果
			int i = 0;
			java.sql.Connection connection = null;
			PreparedStatement pstm = null;
			
			connection = DbUtil.getCon();
			String sql = "delete from tbl_focus where id="+Id;
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
		 * 新增收藏
		 * @param f
		 * @return
		 * @throws SQLException
		 */
		public boolean add(focus f) throws SQLException {
			
			String valuesName = "userId,userType,type,typeId";
			String values = "'"+f.getUserId()+"','"
					+f.getUserType()+"','"
					+f.getType()+"','"
					+f.getTypeId()+"'";
			
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
		
		
		
public boolean isHave(int userId,int userType,int type,int typeId) throws SQLException {
			
			Boolean fus = false;//操作结果
			int i = 0;
			java.sql.Connection connection = null;
			PreparedStatement pstm = null;
			
			connection = DbUtil.getCon();
			String sql = "select * from tbl_focus where userId="+userId+" and userType="+userType+" and type="+type+" and typeId="+typeId;
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
