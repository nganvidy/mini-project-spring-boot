package com.project.post.homework;

import com.project.post.homework.model.Article;
import com.project.post.homework.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

class ProjectPostSystemApplicationTests {
   @Autowired
    ArticleRepository articleRepository;
   @Test
    void getData(){
       List<Article> articles=articleRepository.getArticleAllAuthor(1001);
       for(int i=0;i<articles.size();i++){
           System.out.println("this hello"+articles.get(i));
       }
   }

}
