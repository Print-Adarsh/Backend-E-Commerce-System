package com.example.projectname.repository;

import com.example.projectname.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Optional<Category> findByTitle(String title);
    Optional<Category> findById(Integer id);
}
