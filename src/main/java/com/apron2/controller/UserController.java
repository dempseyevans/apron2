package com.apron2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
public class UserController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RecipeService recipeService;

    @GetMapping(value = {"/", "/login"})
    public String login() {
        System.out.println("Login accessed");
        return "login"; // Points to login.html in the templates folder
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<Recipe> recipes = recipeService.getAllRecipes(null);
        model.addAttribute("recipes", recipes);
        model.addAttribute("recipe", new Recipe());
        return "home"; // This allows access to the home page directly via GET request
    }

    // List items
    @GetMapping("/items")
    public String listItems(@RequestParam(required = false) String search, Model model) {
        List<Item> items = itemService.getAllItems(search);
        model.addAttribute("items", items);
        model.addAttribute("search", search);
        return "items"; // Points to items.html
    }

    // Show form for creating a new item
    @GetMapping("/items/new")
    public String createItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "item_form"; // Create a new template for item form
    }

    // Handle the creation of a new item
    @PostMapping("/items")
public String createItem(@Validated @ModelAttribute Item item, BindingResult result, Model model) {
    if (result.hasErrors()) {
        model.addAttribute("item", item);
        return "item_form";
    }
    itemService.createItem(item);
    return "redirect:/items"; // Redirect to items page after saving
}

    // Show form for editing an existing item
    @GetMapping("/items/edit/{id}")
    public String editItemForm(@PathVariable Long id, Model model) {
        Item item = itemService.getItemById(id);
        model.addAttribute("item", item);
        return "item_form"; // Reuse the item form for editing
    }

    // Handle the update of an existing item
    @PostMapping("/items/edit/{id}")
    public String updateItem(@PathVariable Long id, @ModelAttribute Item item) {
        item.setId(id); // Ensure the id is set for the update
        itemService.updateItem(id, item);
        return "redirect:/items"; // Redirect to items page after updating
    }

    // Delete an item
    @GetMapping("/items/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "redirect:/items"; // Redirect to items page after deletion
    }
}
