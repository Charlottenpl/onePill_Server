package com.form.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.util.DbUtil;

public class DBUtilForm {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * @param con
	 */
	public static Connection getCon(){
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/onepill_db?useUnicode=true&characterEncoding=utf-8","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 关闭数据库
	 * @param con
	 */
	public static void close(Connection con){
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//封装查询所有对象
	public static List<Map<String,Object>> findAll(String tableName){
		Connection con =null;
		PreparedStatement pstm =null;
		ResultSet rs = null;
		String sql = String.format("select * from (%s)", tableName);
		List<Map<String,Object>> list = new ArrayList<>();
		con = DbUtil.getCon();
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Map<String,Object> map = new HashMap<>();
				map.put("id", rs.getInt(1));
				map.put("userId", rs.getInt(2));
				map.put("medicineId", rs.getInt(3));
				map.put("count", rs.getInt(4));
				map.put("medicineName", rs.getString(5));
				map.put("price", rs.getInt(6));
				map.put("size", rs.getString(7));
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbUtil.close(con);
		}
		
		return list;
		
	}

}
