package com.example.projectname.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

@Entity
public class Product extends BaseModel implements Serializable {

    @Setter
    @Getter
    private Integer id;
    private String title;
    private String description;
    private String imageURL;

   // @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore // it will help to not create product categry loop in ouutput during postman test;
    private Category category;

}
