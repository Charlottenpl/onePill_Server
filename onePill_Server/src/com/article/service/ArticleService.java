package com.article.service;

import java.util.List;

import com.article.dao.ArticleDao;
import com.entity.Article;
import com.entity.Comment;

public class ArticleService {
	public List<Article> ArticleFindAllService(){
		return new ArticleDao().findAllArticle();
	}
	public List<Comment> CommentFindAllService(int articleId){
		return new ArticleDao().findAllComment(articleId);
	}
	public Boolean CommentInsertService(String valuesName,String values){
		return new ArticleDao().insertComment(valuesName, values);
	}
}
