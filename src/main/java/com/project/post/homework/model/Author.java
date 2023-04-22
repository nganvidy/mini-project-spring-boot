package com.project.post.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private int id;
    private String fullName;
    private String username;
    private String profileImage;
    private String gender;
    private String bio;
    private String address;
    private String email;

}
