package com.project.post.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileArticle {
    String uplaodFile(MultipartFile file) throws IOException;
}
