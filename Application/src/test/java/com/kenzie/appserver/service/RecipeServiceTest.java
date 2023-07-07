//package com.kenzie.appserver.service;
//
//import com.kenzie.appserver.converters.RecipeMapper;
//import com.kenzie.appserver.exceptions.RecipeNotFoundException;
//import com.kenzie.appserver.repositories.RecipeRepository;
//import com.kenzie.appserver.repositories.model.Enums;
//import com.kenzie.appserver.repositories.model.RecipeRecord;
//import com.kenzie.appserver.service.RecipeService;
//import com.kenzie.appserver.service.model.Recipe;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.util.AssertionErrors.fail;
//
//class RecipeServiceTest {
//
//    @Mock
//    private RecipeRepository recipeRepository;
//
//    private RecipeService recipeService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        recipeService = new RecipeService(recipeRepository);
//    }
//
//    @Test
//    void testGetAllRecipes() {
//        List<RecipeRecord> recipeRecords = createRecipeRecords();
//        recipeRepository.saveAll(recipeRecords);
//        when(recipeRepository.findAll()).thenReturn(recipeRecords);
//
//        List<Recipe> recipes = recipeService.getAllRecipes();
//
//        assertEquals(recipeRecords.size(), recipes.size());
//        verify(recipeRepository, times(1)).findAll();
//    }
//
//    @Test
//    void testCreateRecipe() {
//        Recipe recipe = createRecipe();
//        RecipeRecord recipeRecord = createRecipeRecord();
//        when(recipeRepository.save(any(RecipeRecord.class))).thenReturn(recipeRecord);
//
//        Recipe createdRecipe = recipeService.addNewRecipe(recipe);
//
//        assertEquals(recipe.getTitle(), createdRecipe.getTitle());
//        assertEquals(recipe.getCuisine(), createdRecipe.getCuisine());
//        assertEquals(recipe.getDescription(), createdRecipe.getDescription());
//        assertEquals(recipe.getDietaryRestrictions(), createdRecipe.getDietaryRestrictions());
//        assertEquals(recipe.getHasDietaryRestrictions(), createdRecipe.getHasDietaryRestrictions());
//        assertEquals(recipe.getIngredients(), createdRecipe.getIngredients());
//
//        // Check the format of the instructions
//        String[] expectedSteps = recipe.getInstructions().split("\n ");
//        String[] actualSteps = createdRecipe.getInstructions().split("\n");
//        assertEquals(expectedSteps.length, actualSteps.length);
//        for (int i = 0; i < expectedSteps.length; i++) {
//            String expectedStep = (i + 1) + ". " + expectedSteps[i];
//            assertEquals(expectedStep, actualSteps[i]);
//        }
//
//        verify(recipeRepository, times(1)).save(any(RecipeRecord.class));
//    }
//
//    @Test
//    void testGetRecipesByCuisine() {
//        List<RecipeRecord> recipeRecords = createRecipeRecords();
//        when(recipeRepository.findByCuisine(any(Enums.Cuisine.class))).thenReturn(recipeRecords);
//
//        List<Recipe> recipes = recipeService.getRecipesByCuisine("ITALIAN");
//
//        assertEquals(recipeRecords.size(), recipes.size());
//        verify(recipeRepository, times(1)).findByCuisine(any(Enums.Cuisine.class));
//    }
//
//    @Test
//    void testGetRecipesByDietaryRestrictions() {
//        List<RecipeRecord> recipeRecords = createRecipeRecords();
//        when(recipeRepository.findByDietaryRestrictions(any(Enums.DietaryRestrictions.class))).thenReturn(recipeRecords);
//
//        List<Recipe> recipes = recipeService.getRecipesByDietaryRestrictions("GLUTEN_FREE");
//
//        assertEquals(recipeRecords.size(), recipes.size());
//        verify(recipeRepository, times(1)).findByDietaryRestrictions(any(Enums.DietaryRestrictions.class));
//    }
//
//    @Test
//    void testGetRecipeById() {
//        RecipeRecord recipeRecord = createRecipeRecord();
//        when(recipeRepository.findById(anyString())).thenReturn(Optional.of(recipeRecord));
//
//        try {
//            RecipeRecord recipe = recipeService.getRecipeById("12345");
//
//            assertEquals(recipeRecord.getTitle(), recipe.getTitle());
//            assertEquals(recipeRecord.getCuisine().toString(), recipe.getCuisine().toString());
//            assertEquals(recipeRecord.getDescription(), recipe.getDescription());
//            assertEquals(recipeRecord.getDietaryRestrictions().toString(), recipe.getDietaryRestrictions().toString());
//            assertEquals(recipeRecord.getHasDietaryRestrictions(), recipe.getHasDietaryRestrictions());
//            assertEquals(recipeRecord.getIngredients(), recipe.getIngredients());
//            assertEquals(recipeRecord.getInstructions(), recipe.getInstructions());
//        } catch (NullPointerException e) {
//            // Handle the case when recipe is null
//            // You can throw an exception, fail the test, or take appropriate action based on your requirements
//            fail("Recipe is null");
//        } catch (RecipeNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        verify(recipeRepository, times(1)).findById(anyString());
//    }
//
//
//    private List<RecipeRecord> createRecipeRecords() {
//        List<RecipeRecord> recipeRecords = new ArrayList<>();
//        recipeRecords.add(createRecipeRecord());
//        recipeRecords.add(createRecipeRecord());
//        recipeRecords.add(createRecipeRecord());
//        recipeRepository.saveAll(recipeRecords);
//        return recipeRecords;
//    }
//
//    private RecipeRecord createRecipeRecord() {
////        RecipeRecord recipeRecord = new RecipeRecord();
////        recipeRecord.setId(UUID.randomUUID().toString());
////        recipeRecord.setTitle("Sample Recipe");
////        recipeRecord.setCuisine(Enums.Cuisine.ITALIAN);
////        recipeRecord.setDescription("A delicious Italian dish");
////        recipeRecord.setDietaryRestrictions(Enums.DietaryRestrictions.GLUTEN_FREE);
////        recipeRecord.setHasDietaryRestrictions(true);
////        List<String> ingredients = new ArrayList<>();
////        ingredients.add("Ingredient 1 cup"); // Update ingredient string format
////        ingredients.add("Ingredient 2 cup"); // Update ingredient string format
////        recipeRecord.setIngredients(ingredients);
////        recipeRecord.setIngredients(ingredients);
////        recipeRecord.setInstructions("Step 1\nStep 2\nStep 3");
////        recipeRepository.save(recipeRecord);
//        RecipeMapper.recipeToRecipeRecord(createRecipe());
//        return RecipeMapper.recipeToRecipeRecord(createRecipe());
//    }
//
//
//    private Recipe createRecipe() {
//
//        List<String> ingredients = new ArrayList<>();
//        ingredients.add("Ingredient 1 cups");
//        ingredients.add("Ingredient 2 cups");
//        Recipe recipe = new Recipe(
//                UUID.randomUUID().toString(),
//                "Sample Recipe",
//                "ITALIAN",
//                "A delicious Italian dish",
//                "GLUTEN_FREE",
//                true,
//                ingredients,
//                "Step 1, Step 2, Step 3"
//        );
//        return recipe;
//    }
//}
//>>>>>>> main
