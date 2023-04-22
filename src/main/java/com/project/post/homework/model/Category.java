package com.project.post.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private int catID;
    private int id;
    private String category;
    private String categoryDesc;
}
