package com.project.post.homework.repository;

import com.project.post.homework.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryRepository {
    List<Category> categories = new ArrayList<>(){{
       add(new Category(1,1001,"News","news suggest daily"));
        add(new Category(2,1002,"Funny","relate video and post fun"));
        add(new Category(3,1003,"Entertainment","relax and Entertainment"));
        add(new Category(4,1004,"Cookie","Home"));
//        add(new Category(5,1004,"Education","Home"));
//        add(new Category(6,1001,"programming","Home"));
    }};
   public List<Category> getAllCategories(){
        return categories;
    }
}
