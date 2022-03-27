package com.example.AuthForSecretRecipe.Controller;


import com.example.AuthForSecretRecipe.Models.Chef;
import com.example.AuthForSecretRecipe.Models.Recipe;
import com.example.AuthForSecretRecipe.Repositories.ChefRepositories;
import com.example.AuthForSecretRecipe.Repositories.RecipeRepositories;
import com.example.AuthForSecretRecipe.Repositories.RecipeRepositoriesCrud;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SecretRecipeController {

//    @Autowired
    private final ChefRepositories chefRepositories;
    private final RecipeRepositories recipeRepositories;
    private final RecipeRepositoriesCrud recipeRepositoriesCrud;

    public SecretRecipeController(ChefRepositories chefRepositories, RecipeRepositories recipeRepositories, RecipeRepositoriesCrud recipeRepositoriesCrud) {
        this.chefRepositories = chefRepositories;
        this.recipeRepositories = recipeRepositories;
        this.recipeRepositoriesCrud = recipeRepositoriesCrud;
    }

    @GetMapping("/loginWithSecret")
    public String getLoginPageWithSecret()
    {
        return "/loginWithSecret.html";
    }

    @PostMapping("/loginWithSecret")
    public RedirectView loginWithSecretPage(HttpServletRequest request, String username, String password){
        Chef chef = chefRepositories.findByUsername(username);
        if((chef == null) || (!BCrypt.checkpw(password, chef.password))){
            return new RedirectView("/loginWithSecret");
        }
        HttpSession session =request.getSession();
        session.setAttribute("username",username);

        return new RedirectView("/secretRecipe");
    }

    @PostMapping("/logoutWithSecret")
    public RedirectView logOutUserWithSecret(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.invalidate();

        return new RedirectView("/loginWithSecret");
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "/signup";
    }

    @PostMapping("/signup")
    public RedirectView signupChef(Model model,String username,String password){

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        Chef chef = new Chef(username ,hashedPassword);

        chefRepositories.save(chef);
        return new RedirectView("/loginWithSecret");
    }



    @PostMapping("/addRecipe")
    public RedirectView addRecipe(@ModelAttribute Recipe recipe){
        recipeRepositoriesCrud.save(recipe);
        return new RedirectView("/secretRecipe");
    }





}
