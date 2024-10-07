package com.apron2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apron2.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
