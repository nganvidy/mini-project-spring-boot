package com.project.post.homework.service;

import com.project.post.homework.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthor();
    List<Author> findAllByCategory(int ids);
}
