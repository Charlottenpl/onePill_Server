package com.article.service;

import java.util.List;

import com.article.dao.ArticleDao;
import com.entity.Article;
import com.entity.Comment;

public class ArticleService {
	public List<Article> ArticleFindAllService(){
		return new ArticleDao().findAllArticle();
	}
	public List<Comment> CommentFindAllService(){
		return new ArticleDao().findAllComment();
	}
}
