package com.example.AuthForSecretRecipe.Models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Chef() {
    }

    public Long getId() {
        return id;
    }

    String username;
    public String password;

    public Chef(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @OneToMany(mappedBy = "chef")
    Set<Recipe> recipes;

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
