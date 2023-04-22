package com.project.post.homework.repository;

import com.project.post.homework.model.Article;
import com.project.post.homework.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArticleRepository {
    List<Article> articles = new ArrayList<>(){{
        add(new Article(1, 1001,"sunday","https://img.freepik.com/free-vector/set-torii-gates-water_52683-44986.jpg","my free day and relax",new AuthorRepository().getAllAuthors().get(0),new CategoryRepository().getAllCategories().get(0)));
        add(new Article(2,1002,"hello world","https://img.freepik.com/free-vector/copy-space-bokeh-spring-lights-background_52683-55649.jpg"," learning day",new AuthorRepository().getAllAuthors().get(1),new CategoryRepository().getAllCategories().get(1)));
        add(new Article(3,1003,"hard day","https://t4.ftcdn.net/jpg/02/97/79/83/360_F_297798377_VB9egqGnRKcZxU53wybEHLRnnTrcvlAH.jpg","sunrise and sunset",new AuthorRepository().getAllAuthors().get(2),new CategoryRepository().getAllCategories().get(2)));
        add(new Article(4,1004,"beautiful day","https://w0.peakpx.com/wallpaper/432/229/HD-wallpaper-anime-street-road-buildings-scenery-night-stars-anime.jpg","new day give me that perfect day",new AuthorRepository().getAllAuthors().get(3),new CategoryRepository().getAllCategories().get(3)));
    }};
    public List<Article> getAllArticles(){
        return articles;
    }
   public Article getArticleById(int id){
        return articles.stream().filter(el->el.getId()==id).findFirst().orElse(null);
    }
    public Article getArticleByNo(int id){
        return articles.stream().filter(el->el.getArtId()==id).findFirst().orElse(null);
    }
    public List<Article> getArticleAllAuthor(int ids) {
        return articles.stream()
                .filter(el -> el.getId() == ids)
                .collect(Collectors.toList());
    }
    public void addNewUser(Article article){
        articles.add(article);
    }
   public void deleteArticle(int id) {
       for (int i = 0; i < articles.size(); i++) {
           Article article = articles.get(i);
           if (article.getArtId() == id) {
               articles.remove(i);
           }
       }
    }

       public void updateArticle(int id,Article article) {
           for (int i = 0; i < articles.size(); i++) {
                article = articles.get(i);
               if (article.getArtId() == id) {
                   articles.set(i,article);
               }
           }
       }

}
