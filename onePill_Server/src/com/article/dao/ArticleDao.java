package com.article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Article;
import com.entity.Comment;
import com.util.DbUtil;

public class ArticleDao {
	public List<Article> findAllArticle() {
		Connection con = null;
		PreparedStatement pstm = null;
		List<Article> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			con = DbUtil.getCon();
			String sql = String.format("select * from tbl_article");
			// 取消自动提交
			con.setAutoCommit(false);
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				article.setId(rs.getInt(1));
				article.setContent(rs.getString(3));
				article.setTag(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setWritename(rs.getString(2)); 
				article.setHeadImg(rs.getString(6));
				list.add(article);
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
	public List<Comment> findAllComment(int artilceId){
		Connection con = null;
		PreparedStatement pstm = null;
		List<Comment> list = new ArrayList<>();
		ResultSet rs = null;
		try{
			con = DbUtil.getCon();
			String sql = String.format("select * from tbl_comment where articleId = (%s)",artilceId);
			// 取消自动提交
			con.setAutoCommit(false);
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setName(rs.getString(1));
				comment.setCcomment(rs.getString(2));
				comment.setHeadImg(rs.getString(3));
				comment.setArticleId(rs.getInt(4));
				list.add(comment);
			}
			pstm.close();
			con.commit();
			rs.close();
			return list;
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DbUtil.close(con);
		}
		return null;
	}
	
	public Boolean insertComment(String valuesName,String values){
		Connection con = null;
		PreparedStatement pstm = null;
		int i = 0;
		try{
			con = DbUtil.getCon();
			String sql = String.format("insert into tbl_comment (%s) values (%s)",valuesName,values);
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
}
