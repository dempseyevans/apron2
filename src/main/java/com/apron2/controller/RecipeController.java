package com.apron2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apron2.entity.Item;
import com.apron2.entity.Recipe;
import com.apron2.service.ItemService;
import com.apron2.service.RecipeService;

@Controller
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ItemService itemService;


    @GetMapping("/recipes")
    public String listRecipes(@RequestParam(required = false) String search, Model model) {
        List<Recipe> recipes = recipeService.getAllRecipes(search);
        model.addAttribute("recipes", recipes);
        model.addAttribute("search", search);
        return "recipes"; // Points to recipes.html
    }

    @GetMapping("/recipes/new")
    public String createRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("items", itemService.getAllItems(null));
        return "recipe_form"; // Create a new template for recipe form
    }

    @PostMapping("/recipes")
    public String createRecipe(@ModelAttribute Recipe recipe, @RequestParam(required = false) String newIngredients) {
        if (newIngredients != null && !newIngredients.isEmpty()) {
            String[] newIngredientNames = newIngredients.split(",");
            for (String name : newIngredientNames) {
                Item newItem = new Item(name.trim(), 0.0); // Assuming default price is 0.0, adjust as needed
                itemService.createItem(newItem);
                recipe.getIngredients().add(newItem);
            }
    }
    recipeService.createRecipe(recipe);
    return "redirect:/recipes"; // Redirect to recipes page after saving
}

    @GetMapping("/recipes/edit/{id}")
    public String editRecipeForm(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "recipe_form"; // Reuse the recipe form for editing
    }

    @PostMapping("/recipes/edit/{id}")
    public String updateRecipe(@PathVariable Long id, @ModelAttribute Recipe recipe) {
        recipe.setId(id); // Ensure the id is set for the update
        recipeService.updateRecipe(id, recipe);
        return "redirect:/recipes"; // Redirect to recipes page after updating
    }

    @GetMapping("/recipes/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return "redirect:/recipes"; // Redirect to recipes page after deletion
    }

    @GetMapping("/recipes/{id}")
public String viewRecipe(@PathVariable Long id, Model model) {
    Recipe recipe = recipeService.getRecipeById(id);
    model.addAttribute("recipe", recipe);
    return "recipe_details"; // Points to recipe_details.html
}


}
