package com.example.AuthForSecretRecipe.Repositories;

import com.example.AuthForSecretRecipe.Models.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChefRepositories extends JpaRepository<Chef,Long> {
    Chef findByUsername(String username);

    Optional<Chef> findById(Long id);
}
