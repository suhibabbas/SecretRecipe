package com.example.AuthForSecretRecipe.Controller;

import com.example.AuthForSecretRecipe.Repositories.RecipeRepositoriesCrud;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RecipeController {

    private final RecipeRepositoriesCrud recipeRepositoriesCrud;

    public RecipeController(RecipeRepositoriesCrud recipeRepositoriesCrud) {
        this.recipeRepositoriesCrud = recipeRepositoriesCrud;
    }

    @GetMapping("/secretRecipe")
    public String secretRecipePage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();
        model.addAttribute("username",username);

        model.addAttribute("recipeList",recipeRepositoriesCrud.findAll());

        return "secretRecipe";
    }
}
