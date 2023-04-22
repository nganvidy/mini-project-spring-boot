package com.project.post.homework.service.implement;

import com.project.post.homework.service.UploadFileArticle;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
@Service
public class UploadFileArticleImp implements UploadFileArticle {
    Path fileLocationStorage;
    UploadFileArticleImp(){
        fileLocationStorage= Paths.get("src/main/resources/images");
    }
    @Override
    public String uplaodFile(MultipartFile file) throws IOException {
        String fillName=file.getOriginalFilename();
        //file name dog.jpg

       if(fillName!=null){
           if(fillName.contains("..")){
               System.out.println("fillName is Incorrect...");
               return null;
           }

           String[] filePart=fillName.split("\\.");
           fillName= UUID.randomUUID()+"."+filePart[1];
           Path fileResovle=fileLocationStorage.resolve(fillName);
           Files.copy(file.getInputStream(),fileResovle, StandardCopyOption.REPLACE_EXISTING);
           return fillName;
       }

        return null;
    }
}
