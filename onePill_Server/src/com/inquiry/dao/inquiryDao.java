package com.inquiry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.Article;
import com.entity.Inquiry;
import com.util.DbUtil;

public class inquiryDao {
	// 添加病例
	public Boolean add(String valuesName,String values) throws SQLException {
		Connection con = null;
		PreparedStatement pstm = null;
		
		int i = 0 ;
		try{
			con = DbUtil.getCon();
			String sql = String.format("insert into tbl_inquiry (%s) values (%s)",valuesName,values);
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

	public List<Inquiry> searchByUserId(int userId) throws SQLException {
		List<Inquiry> inquiryList = new ArrayList<Inquiry>();
		String SQL = "select * from tbl_inquiry where user_id = " + userId;
		Inquiry inq = null;
		java.sql.Connection connection = null;
		PreparedStatement pstm = null;

		connection = DbUtil.getCon();
		pstm = connection.prepareStatement(SQL);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			inq = new Inquiry();
			inq.setUserId(rs.getInt(rs.getInt(2)));
			inq.setTitle(rs.getString(rs.getString(3)));
			inq.setContent(rs.getString(rs.getString(4)));
			inq.setTime(rs.getString(rs.getString(7)));
			inq.setImg(rs.getString(6));
			inquiryList.add(inq);
		}

		pstm.close();
		return inquiryList;

	}
	
	public List<Inquiry> findAllInquiry() {
		Connection con = null;
		PreparedStatement pstm = null;
		List<Inquiry> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			con = DbUtil.getCon();
			String sql = String.format("select * from tbl_inquiry");
			// 取消自动提交
			con.setAutoCommit(false);
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Inquiry inquiry = new Inquiry();
				inquiry.setId(rs.getInt(1));
				inquiry.setUserId(rs.getInt(2));
				inquiry.setTitle(rs.getString(3));
				inquiry.setContent(rs.getString(4));
				inquiry.setFlag(rs.getInt(5));
				inquiry.setImg(rs.getString(6));
				inquiry.setTime(rs.getString(7));
				list.add(inquiry);
			}
			pstm.close();
			con.commit();
			rs.close();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(con);
		}
		return null;
	}
}
