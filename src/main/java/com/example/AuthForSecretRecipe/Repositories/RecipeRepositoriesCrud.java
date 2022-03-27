package com.example.AuthForSecretRecipe.Repositories;

import com.example.AuthForSecretRecipe.Models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepositoriesCrud extends CrudRepository<Recipe,Long> {
}
