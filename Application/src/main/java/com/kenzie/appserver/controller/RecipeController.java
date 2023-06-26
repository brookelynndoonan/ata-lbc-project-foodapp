package com.kenzie.appserver.controller;


import com.kenzie.appserver.controller.model.RecipeCreateRequest;
import com.kenzie.appserver.controller.model.RecipeResponse;
import com.kenzie.appserver.service.RecipeService;
import com.kenzie.appserver.service.model.Recipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    //Retrieve Recipe by ID.
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable("id") String id) {
        Recipe recipe = recipeService.findRecipeByID(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        RecipeResponse recipeResponse = createRecipeResponse(recipe);
        return ResponseEntity.ok(recipeResponse);
    }


    //A Post for creating and posting a recipe.
    @PostMapping
    public ResponseEntity<RecipeResponse> addNewRecipe(@RequestBody RecipeCreateRequest recipeCreateRequest){
        Recipe recipe = new Recipe(
                recipeCreateRequest.getTitle(),
                recipeCreateRequest.getId(),
                recipeCreateRequest.getCuisine(),
                recipeCreateRequest.getDescription(),
                recipeCreateRequest.getDietaryRestrictions(),
                recipeCreateRequest.getDietaryRestrictionsBool(),
                recipeCreateRequest.getIngredients(),
                recipeCreateRequest.getInstructions());

        recipeService.addNewRecipe(recipe);

        RecipeResponse recipeResponse = createRecipeResponse(recipe);

        return ResponseEntity.created(URI.create("/recipes/" + recipeResponse.getId())).body(recipeResponse);
    }



    //Helper Method for our Recipe Response.
    private RecipeResponse createRecipeResponse(Recipe recipe) {

        RecipeResponse recipeResponse = new RecipeResponse();

        recipeResponse.setTitle(recipe.getTitle());
        recipeResponse.setId(recipe.getId());
        recipeResponse.setCuisine(recipe.getCuisine());
        recipeResponse.setDescription(recipe.getDescription());
        recipeResponse.setDietaryRestrictions(recipe.getDietaryRestrictions());
        recipeResponse.setDietaryRestrictionsBool(recipe.isHasDietaryRestrictions());
        recipeResponse.setIngredients(recipe.getIngredients());
        recipeResponse.setInstructions(recipe.getInstructions());

        return recipeResponse;
    }

}
