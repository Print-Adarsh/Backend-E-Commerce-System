package com.example.projectname.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter

@Entity
public class Category extends BaseModel implements Serializable{

    private Integer id;

    private String title;


    @OneToMany(mappedBy = "category" , cascade = {CascadeType.REMOVE},fetch= FetchType.EAGER)
    private List<Product> products;



}
