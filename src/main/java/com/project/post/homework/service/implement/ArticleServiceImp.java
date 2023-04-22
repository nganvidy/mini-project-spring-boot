package com.project.post.homework.service.implement;

import com.project.post.homework.model.Article;
import com.project.post.homework.repository.ArticleRepository;
import com.project.post.homework.repository.AuthorRepository;
import com.project.post.homework.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ArticleServiceImp implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Override
    public List<Article> getAllArticle() {
        return articleRepository.getAllArticles();
    }

    @Override
    public Article getArticleById(int id) {
        return articleRepository.getArticleById(id);
    }

    @Override
    public List<Article> getAllAuthor(int id) {
        return articleRepository.getArticleAllAuthor(id);
    }

    @Override
    public void addNewArticle(Article article) {
    articleRepository.addNewUser(article);
    }

    @Override
    public void deleteById(int id) {
         articleRepository.deleteArticle(id);
    }

    @Override
    public void updateArticleById(int id, Article article) {
        articleRepository.updateArticle(id,article);
    }

    @Override
    public Article getArticleByNo(int id) {
        return articleRepository.getArticleByNo(id);
    }
}
