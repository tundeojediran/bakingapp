package udacity.alc.dannytee.bakingapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import udacity.alc.dannytee.bakingapp.models.RecipesResponse;

/**
 * Created by dannytee on 25/06/2017.
 */

public interface RecipeService {

    /*
    * Retrofit get annotation with URL
    * And method that will return all recipes.
   */
    @GET("baking.json")
    Call<RecipesResponse> getRecipes();


}
