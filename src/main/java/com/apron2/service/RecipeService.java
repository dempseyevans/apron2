package com.apron2.service;

import java.util.List;

import com.apron2.entity.Recipe;

public interface RecipeService {
    List<Recipe> getAllRecipes(String search);
    Recipe getRecipeById(Long id);
    Recipe createRecipe(Recipe recipe);
    Recipe updateRecipe(Long id, Recipe recipe);
    void deleteRecipe(Long id);
}
