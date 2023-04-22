package com.project.post.homework.controller;

import com.project.post.homework.model.Article;
import com.project.post.homework.model.Author;
import com.project.post.homework.model.Category;
import com.project.post.homework.repository.AuthorRepository;
import com.project.post.homework.repository.CategoryRepository;
import com.project.post.homework.repository.request.ArticleRequest;
import com.project.post.homework.service.ArticleService;
import com.project.post.homework.service.AuthorService;
import com.project.post.homework.service.CategoryService;
import com.project.post.homework.service.UploadFileArticle;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    AuthorService authorService;
    @Autowired
    UploadFileArticle uploadFileArticle;
    @GetMapping("/home")
   public String homePage(Model model){
//        List<Article> articles = articleService.getAllArticle();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("article",articleService.getAllArticle().stream().sorted((a,b)->b.getArtId()-a.getArtId()));
        model.addAttribute("category",categories);
        return "home";
    }
    @GetMapping("/alluser")
    public String allUser(Model model){
        List<Article> articles = articleService.getAllArticle();
        model.addAttribute("article",articles);
        return "allusers";
    }
    @GetMapping("/viewcard/{id}")
    String viewUser(@PathVariable("id") int id,Model model){
        Article article = articleService.getArticleByNo(id);
        System.out.println(article);
        model.addAttribute("article",article );
        return "onlyview";
    }
    @GetMapping("/alluser/{id}")
    public String allUser(@PathVariable("id") int id, Model model){
        List<Article> allfind=articleService.getAllAuthor(id);
        System.out.println("result :"+allfind);
        model.addAttribute("article",allfind);
        return "allusers";
    }
    @GetMapping("/viewprofile/{id}")
    public String viewprofile(@PathVariable("id") int id,Model model){
       Article article = articleService.getArticleById(id);
       List<Article> allfind=articleService.getAllAuthor(id);
        model.addAttribute("article",article);
        model.addAttribute("articlelast",allfind);
        return "viewprofile";
    }
    @GetMapping("/viewform")
    public String formPost(Model model){
        List<Author> authors= authorService.getAllAuthor();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("articleRequest",new ArticleRequest());
        model.addAttribute("author",authors);
        model.addAttribute("category",categories);
        return "postform";
    }
    @PostMapping("/handleAddArticle")
    String handAddArticle(@Valid @ModelAttribute("articleRequest") ArticleRequest article, BindingResult bindingResult , Model model) throws IOException {
     if(bindingResult.hasErrors()){
         System.out.println("Error Has Happen!!");
         model.addAttribute("author",authorService.getAllAuthor());
         model.addAttribute("category",categoryService.getAllCategories());
         return "postform";
     }


        System.out.println(article.getFile().getSize());
     if(article.getFile().getSize()>1000000){
         model.addAttribute("author",authorService.getAllAuthor());
         model.addAttribute("category",categoryService.getAllCategories());
         model.addAttribute("error","Image size must be less than 1MB");
         return "postform";
     }
        Article newArticle = new Article();
        try {
            String fileName = "http://localhost:8080/images/"+uploadFileArticle.uplaodFile(article.getFile());
            newArticle.setImageURL(fileName);
            System.out.println("FileName is"+fileName);
        }catch (Exception e){
            newArticle.setImageURL("https://developers.elementor.com/docs/assets/img/elementor-placeholder-image.png");
            System.out.println("Error"+e.getMessage());
        }
        newArticle.setTitle(article.getTitle());
        newArticle.setArticle(article.getDesciption());
        newArticle.setId(article.getAuthorId());
        newArticle.setAuthor(authorService.getAllAuthor().stream().filter(e -> e.getId() == article.getAuthorId())
                .findFirst().orElse(null));
        newArticle.setCategory(categoryService.getAllCategories().stream().filter(el->el.getId()==article.getCategoryId())
                .findFirst().orElse(null));
        newArticle.setArtId(articleService.getAllArticle().stream().max(Comparator.comparingInt(Article::getArtId)).orElseThrow().getArtId() + 1);
        System.out.println("value is the value of article"+newArticle);
        articleService.addNewArticle(newArticle);
        return "redirect:/home";

    }
    @GetMapping("/deleteId/{id}")
    String deleteById(@PathVariable("id") int id,Model model){
         articleService.deleteById(id);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("article",articleService.getAllArticle().stream().sorted((a,b)->b.getArtId()-a.getArtId()));
        model.addAttribute("category",categories);
      return "home";
    }

    @GetMapping("/updateId/{id}/{ids}")
    String updateById(@PathVariable("id") Integer id,@PathVariable("ids") Integer ids,Model model){
        List<Author> authors= authorService.findAllByCategory(ids);
        List<Category> categories = categoryService.getAllCategories();
        ArticleRequest articles=new ArticleRequest();
        articles.setTitle(articleService.getArticleByNo(id).getTitle());
        articles.setDesciption(articleService.getArticleByNo(id).getArticle());
        articles.setId(articleService.getArticleByNo(id).getId());
        articles.setAuthorId(articleService.getArticleByNo(id).getId());
        articles.setCategoryId(articleService.getArticleByNo(id).getId());
        System.out.println(articles);
        model.addAttribute("articles",articles);
        model.addAttribute("author",authors);
        model.addAttribute("category",categories);
        return "update";
    }
    @PostMapping("/saveupdate/{id}")
    String updateArticle(@PathVariable("id")Integer id,@Valid @ModelAttribute("articleRequest") ArticleRequest article, BindingResult bindingResult , Model model)throws IOException{
//        @RequestParam("file")MultipartFile file
        if(bindingResult.hasErrors()){
            ArticleRequest articles=new ArticleRequest();
            articles.setTitle(articleService.getArticleByNo(id).getTitle());
            articles.setDesciption(articleService.getArticleByNo(id).getArticle());
            articles.setId(articleService.getArticleByNo(id).getId());
            articles.setAuthorId(articleService.getArticleByNo(id).getId());
            articles.setCategoryId(articleService.getArticleByNo(id).getId());
            List<Author> authors= authorService.getAllAuthor();
            System.out.println("Error Has Happen!!");
            model.addAttribute("articles",articles);
            model.addAttribute("author",authorService.getAllAuthor());
            model.addAttribute("category",categoryService.getAllCategories());
            return "update";
        }
        System.out.println("This Size Pic :"+article.getFile().getSize());
        if(article.getFile().getSize()>1000000){
            ArticleRequest articles=new ArticleRequest();
            articles.setTitle(articleService.getArticleByNo(id).getTitle());
            articles.setDesciption(articleService.getArticleByNo(id).getArticle());
            articles.setId(articleService.getArticleByNo(id).getId());
            articles.setAuthorId(articleService.getArticleByNo(id).getId());
            articles.setCategoryId(articleService.getArticleByNo(id).getId());
            model.addAttribute("articles",articles);
            model.addAttribute("author",authorService.getAllAuthor());
            model.addAttribute("category",categoryService.getAllCategories());
            model.addAttribute("error","Image size must be less than 1MB");
            return "update";
        }
        Article newArticle = articleService.getArticleByNo(id);
        try {
            String fileName = "http://localhost:8080/images/"+uploadFileArticle.uplaodFile(article.getFile());
            newArticle.setImageURL(fileName);
            System.out.println("FileName is"+fileName);
        }catch (Exception e){
            newArticle.setImageURL("https://developers.elementor.com/docs/assets/img/elementor-placeholder-image.png");
            System.out.println("Error"+e.getMessage());
        }
        newArticle.setTitle(article.getTitle());
        newArticle.setArticle(article.getDesciption());
        newArticle.setId(article.getAuthorId());
        newArticle.setAuthor(authorService.getAllAuthor().stream().filter(e -> e.getId() == article.getAuthorId())
                .findFirst().orElse(null));
        newArticle.setCategory(categoryService.getAllCategories().stream().filter(el->el.getId()==article.getCategoryId())
                .findFirst().orElse(null));
        newArticle.setArtId(articleService.getAllArticle().stream().max(Comparator.comparingInt(Article::getArtId)).orElseThrow().getArtId() + 1);
        System.out.println("value is the value of article"+newArticle);
        articleService.updateArticleById(id,newArticle);
        System.out.println(newArticle);
        return "redirect:/home";

    }
}
