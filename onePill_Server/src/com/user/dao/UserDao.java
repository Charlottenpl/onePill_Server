package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Address;
import com.entity.Doctor;
import com.entity.Inquiry;
import com.entity.User;
import com.user.controller.EditUserServlet;
import com.util.DbUtil;

public class UserDao {
	public Boolean patientRegister(String valuesName,String values){
		Connection con = null;
		PreparedStatement pstm = null;
		int i = 0 ;
		try{
			con = DbUtil.getCon();
			String sql = String.format("insert into tbl_user (%s) values (%s)",valuesName,values);
			//取消自动提交
			con.setAutoCommit(false);
			pstm = con.prepareStatement(sql);
			i = pstm.executeUpdate();
			pstm.close();
			con.commit();
			if(i!=0)
				return true;
			else
				return false;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DbUtil.close(con);
		}
		return false;
	}
	
	public Boolean doctorRegister(String valuesName,String values){
		Connection con = null;
		PreparedStatement pstm = null;
		int i = 0;
		try{
			con = DbUtil.getCon();
			String sql = String.format("insert into tbl_doctor (%s) values (%s)",valuesName,values);
			//取消自动提交
			con.setAutoCommit(false);
			pstm = con.prepareStatement(sql);
			i = pstm.executeUpdate();
			pstm.close();
			con.commit();
			if(i!=0)
				return true;
			else
				return false;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DbUtil.close(con);
		}
		return false;
		
	}
	
	public User userLogin(String phone,String password){
		Connection con = null;
		try {
			con = DbUtil.getCon();
			PreparedStatement pstm = con.prepareStatement("select * from tbl_user where"
					+ " phone = ? and password = ?");
			pstm.setString(1, phone);
			pstm.setString(2, password);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setNickName(rs.getString(2));;
				user.setPhone(phone);
				user.setPassword(password);
				user.setPID(rs.getString(5));
				user.setHeadImg(rs.getString(6));
				user.setAddress(rs.getString(7));
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbUtil.close(con);
		}
		return null;
	}
	
	public Doctor doctorLogin(String phone,String password){
		Connection con = null;
		try{
			con = DbUtil.getCon();
			PreparedStatement pstm = con.prepareStatement("select * from tbl_doctor where"
					+ " phone = ? and password = ?");
			pstm.setString(1, phone);
			pstm.setString(2, password);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				Doctor doctor = new Doctor();
				doctor.setDoctorId(rs.getInt(1));
				doctor.setName(rs.getString(2));
				doctor.setPhone(phone);
				doctor.setAddress(rs.getString(4));
				doctor.setPassword(password);
				doctor.setPID(rs.getString(6));
				doctor.setHospital(rs.getString(7));
				doctor.setLicence1(rs.getString(8));
				doctor.setHeadImg(rs.getString(9));
				doctor.setLicence2(rs.getString(10));
				doctor.setTag(rs.getString(11));
				return doctor;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbUtil.close(con);
		}
		return null;
	}
	
	/**
	 * 更改用户昵称
	 * @throws SQLException 
	 */
	public boolean editUserNickName(int UserId,String nickName) throws SQLException {
		boolean f = false;//操作结果
		System.out.println("更改用户昵称UserId"+UserId+"\n"+nickName);
		int i = 0;
		Connection connection = null;
		PreparedStatement pstmPreparedStatement = null;
		String sqlString = "update tbl_user set nickName='"+nickName+"' where Id = "+UserId+";";
		System.out.println(sqlString);
		connection = DbUtil.getCon();
		connection.setAutoCommit(false);
		pstmPreparedStatement = connection.prepareStatement(sqlString);
		i = pstmPreparedStatement.executeUpdate();
		pstmPreparedStatement.close();
		connection.commit();
		if(i!=0)
			f = true;
		else
			f = false;
		
		return f;
		
	}
	
	
	/**
	 * 更改用户手机号
	 * @throws SQLException 
	 */
	public boolean editUserPhone(int UserId,String phone) throws SQLException {
		boolean f = false;//操作结果
		int i = 0;
		Connection connection = null;
		PreparedStatement pstmPreparedStatement = null;
		String sqlString = "update tbl_user set phone = '"+phone+"' where id = "+UserId+";";
		connection = DbUtil.getCon();
		connection.setAutoCommit(false);
		pstmPreparedStatement = connection.prepareStatement(sqlString);
		i = pstmPreparedStatement.executeUpdate();
		pstmPreparedStatement.close();
		connection.commit();
		if(i!=0)
			f = true;
		else
			f = false;
		
		return f;
		
	}
	
	/**
	 * 更改用户密码
	 * @throws SQLException 
	 */
	public boolean editUserPassword(int UserId,String password) throws SQLException {
		boolean f = false;//操作结果
		int i = 0;
		Connection connection = null;
		PreparedStatement pstmPreparedStatement = null;
		String sqlString = "update tbl_user set password = '"+password+"' where id = "+UserId+";";
		connection = DbUtil.getCon();
		connection.setAutoCommit(false);
		pstmPreparedStatement = connection.prepareStatement(sqlString);
		i = pstmPreparedStatement.executeUpdate();
		pstmPreparedStatement.close();
		connection.commit();
		if(i!=0)
			f = true;
		else
			f = false;
		
		return f;
		
	}
	
	/**
	 * 更改用户PID
	 * @throws SQLException 
	 */
	public boolean editUserPID(int UserId,String PID) throws SQLException {
		boolean f = false;//操作结果
		int i = 0;
		Connection connection = null;
		PreparedStatement pstmPreparedStatement = null;
		String sqlString = "update tbl_user set PID = '"+PID+"' where id = "+UserId+";";
		connection = DbUtil.getCon();
		connection.setAutoCommit(false);
		pstmPreparedStatement = connection.prepareStatement(sqlString);
		i = pstmPreparedStatement.executeUpdate();
		pstmPreparedStatement.close();
		connection.commit();
		if(i!=0)
			f = true;
		else
			f = false;
		
		return f;
		
	}
	
	/**
	 * 更改用户头像
	 * @throws SQLException 
	 */
	public boolean editUserHeadimg(int UserId,String headimg) throws SQLException {
		boolean f = false;//操作结果
		int i = 0;
		Connection connection = null;
		PreparedStatement pstmPreparedStatement = null;
		String sqlString = "update tbl_user set headimg = '"+headimg+"' where id = "+UserId;
		connection = DbUtil.getCon();
		connection.setAutoCommit(false);
		pstmPreparedStatement = connection.prepareStatement(sqlString);
		System.out.println(sqlString);
		i = pstmPreparedStatement.executeUpdate();
		pstmPreparedStatement.close();
		connection.commit();
		if(i!=0)
			f = true;
		else
			f = false;
		
		return f;
		
	}
	
	/**
	 * 通过DoctorId查询医生
	 * @throws SQLException 
	 */
	
	public Doctor searchDoctorById(int DoctorId) throws SQLException{
			Doctor d = null;
			String sql = "select * from tbl_doctor where Id = "+DoctorId;
			System.out.println(""+sql);
			java.sql.Connection connection = null;
			PreparedStatement pstm = null;
			connection = DbUtil.getCon();
			//取消自动提交
			connection.setAutoCommit(false);
			pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				int doctorId = rs.getInt("id");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String password = rs.getString("password");
				String PID = rs.getString("PID");
				String hospital = rs.getString("hospital");
				String headImg = rs.getString("headImg");
				String tag = rs.getString("tag");
				String resume = rs.getString("resume");
				String licence1 = rs.getString("licence1");
				String licence2 = rs.getString("licence2");
				d = new Doctor(doctorId, name, phone, address, password, PID,hospital,headImg,licence1,licence2,tag,resume);
			}
			return d;		
	}
	
	/**
	 * 通过DoctorName查询医生
	 * @throws SQLException 
	 */
	
	public Doctor searchDoctorByName(String Name) throws SQLException{
			Doctor d = null;
			String sql = "select * from tbl_doctor where name = '"+Name+"'";
			System.out.println(""+sql);
			java.sql.Connection connection = null;
			PreparedStatement pstm = null;
			connection = DbUtil.getCon();
			//取消自动提交
			connection.setAutoCommit(false);
			pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				int doctorId = rs.getInt("id");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String password = rs.getString("password");
				String PID = rs.getString("PID");
				String hospital = rs.getString("hospital");
				String headImg = rs.getString("headImg");
				String tag = rs.getString("tag");
				String resume = rs.getString("resume");
				String licence1 = rs.getString("licence1");
				String licence2 = rs.getString("licence2");
				d = new Doctor(doctorId, name, phone, address, password, PID,hospital,headImg,licence1,licence2,tag,resume);
			}
			return d;		
	}
	
	public User searchByUserId(int userId){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = DbUtil.getCon();
			String sql = String.format("select * from tbl_user where id=(%s)",userId);
			// 取消自动提交
			con.setAutoCommit(false);
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUserId(userId);
				
				user.setNickName(rs.getString(2));
				user.setAddress(rs.getString(7));
				user.setPhone(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setPID(rs.getString(5));
				user.setHeadImg(rs.getString(6));
			}
			pstm.close();
			con.commit();
			rs.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(con);
		}
		return null;
	}
}
