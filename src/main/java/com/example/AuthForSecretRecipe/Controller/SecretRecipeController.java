package com.example.AuthForSecretRecipe.Controller;


import com.example.AuthForSecretRecipe.Models.Chef;
import com.example.AuthForSecretRecipe.Models.Recipe;
import com.example.AuthForSecretRecipe.Repositories.ChefRepositories;
import com.example.AuthForSecretRecipe.Repositories.RecipeRepositories;
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
//    RecipeRepositories recipeRepositories;

    private final RecipeRepositories recipeRepositories;

    public SecretRecipeController(ChefRepositories chefRepositories, RecipeRepositories recipeRepositories) {
        this.chefRepositories = chefRepositories;
        this.recipeRepositories = recipeRepositories;
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

    // For practicing
    @GetMapping("/login")
    public String loginPage(){
        return "/login.html";
    }

    @PostMapping("/login")
    public RedirectView loginChef(String username,String password){
        Chef chef = chefRepositories.findByUsername(username);
        if((chef== null) || (!BCrypt.checkpw(password,chef.password))){
            return new RedirectView("/login");
        }
        return new RedirectView("/");
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
        return new RedirectView("/login");
    }

    /**
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/recipe")
    List<Recipe> getAllRecipe(){
        return recipeRepositories.findAll();
    }

    /**
     *
     * @param recipe
     * @return
     */
    @ResponseBody
    @PostMapping("/recipe")
    Recipe addRecipe(@RequestBody Recipe recipe){
        return recipeRepositories.save(recipe);
    }

    @ResponseBody
    @PostMapping("chef/{id}")
    Recipe addRecipeToChef(@RequestBody Recipe recipe, @PathVariable Long id){
        Chef chef =chefRepositories.findById(id).orElseThrow();
        recipe.setChef(chef);

        return recipeRepositories.save(recipe);
    }


}
