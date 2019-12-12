package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.DbUtil;

public class DoctorDao {
	/**
	 * 更改用户昵称
	 * @throws SQLException 
	 */
	public boolean editDoctorkName(int DoctorId,String name) throws SQLException {
		boolean f = false;//操作结果
		int i = 0;
		Connection connection = null;
		PreparedStatement pstmPreparedStatement = null;
		String sqlString = "update tbl_doctor set name='"+name+"' where id = "+DoctorId+";";
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
	public boolean editDoctorPhone(int DoctorId,String phone) throws SQLException {
		boolean f = false;//操作结果
		int i = 0;
		Connection connection = null;
		PreparedStatement pstmPreparedStatement = null;
		String sqlString = "update tbl_doctor set phone = '"+phone+"' where id = "+DoctorId+";";
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
	public boolean editDoctorPassword(int DoctorId,String password) throws SQLException {
		boolean f = false;//操作结果
		int i = 0;
		Connection connection = null;
		PreparedStatement pstmPreparedStatement = null;
		String sqlString = "update tbl_doctor set password = '"+password+"' where id = "+DoctorId+";";
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
	public boolean editDoctorPID(int DoctorId,String PID) throws SQLException {
		boolean f = false;//操作结果
		int i = 0;
		Connection connection = null;
		PreparedStatement pstmPreparedStatement = null;
		String sqlString = "update tbl_doctor set PID = '"+PID+"' where id = "+DoctorId+";";
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
	public boolean editDoctorHeadimg(int DoctorId,String headimg) throws SQLException {
		boolean f = false;//操作结果
		int i = 0;
		Connection connection = null;
		PreparedStatement pstmPreparedStatement = null;
		String sqlString = "update tbl_doctor set headimg = '"+headimg+"' where id = "+DoctorId;
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

}
