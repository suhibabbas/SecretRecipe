package com.example.AuthForSecretRecipe.Repositories;

import com.example.AuthForSecretRecipe.Models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepositories extends JpaRepository<Recipe,Integer> {
}
