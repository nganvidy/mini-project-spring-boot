package com.project.post.homework.service;

import com.project.post.homework.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticle();
    Article getArticleById(int id);
    List<Article> getAllAuthor(int id);
    void addNewArticle(Article article);
    void deleteById(int id);
    void updateArticleById(int id,Article article);
    Article getArticleByNo(int id);
}
