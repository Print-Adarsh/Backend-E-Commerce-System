package com.example.projectname.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@MappedSuperclass // mapped with child class
public class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto-increament part take care
    private Integer id;


    private Date createdAt;
    private Date updatedAt;
    private boolean isDelected;
    private String createdByUserName;
}
