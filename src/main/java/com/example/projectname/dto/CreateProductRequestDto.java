package com.example.projectname.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateProductRequestDto {
    private String title;
    private String description;
    private String imageURL;
    private CategoryRequestDTO category;
}

