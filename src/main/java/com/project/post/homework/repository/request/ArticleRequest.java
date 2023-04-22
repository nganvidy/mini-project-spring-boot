package com.project.post.homework.repository.request;

import com.project.post.homework.model.Author;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {
    @NotEmpty(message = "Title Can't be empty.")
    private String title;
    private int id;
    @NotEmpty(message = "Description Can't be empty.")
    private String desciption;
    private int authorId;
//    @NotEmpty(message = "Please Select Option.!!")
    private MultipartFile file;
    private int categoryId;

}
