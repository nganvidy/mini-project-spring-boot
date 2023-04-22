package com.project.post.homework.service.implement;

import com.project.post.homework.model.Author;
import com.project.post.homework.repository.AuthorRepository;
import com.project.post.homework.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AuthorServiceImp implements AuthorService {
     @Autowired
    AuthorRepository authorRepository;
    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.getAllAuthors();
    }

    @Override
    public List<Author> findAllByCategory(int ids) {
        return authorRepository.getArticleAllAuthor(ids);
    }
}
