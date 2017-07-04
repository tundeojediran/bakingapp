package udacity.alc.dannytee.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dannytee on 25/06/2017.
 */

public class Recipe implements Parcelable {

    public final static Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {


        @Override
        public Recipe createFromParcel(Parcel parcel) {
            return null;
        }

        public Recipe[] newArray(int size) {
            return (new Recipe[size]);
        }

    };
    private Integer id;
    private String name;
    private List<Ingredient> ingredients = null;
    private List<Step> steps = null;
    private Integer servings;
    private String image;

    /**
     * No args constructor for use in serialization
     */
    public Recipe() {
    }

    /**
     * @param ingredients
     * @param id
     * @param servings
     * @param name
     * @param image
     * @param steps
     */
    public Recipe(Integer id, String name, List<Ingredient> ingredients, List<Step> steps, Integer servings, String image) {
        super();
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public Recipe(JSONObject jsonObject) throws JSONException {
        this.name = jsonObject.getString("name");
        this.ingredients = new ArrayList<>();
        JSONArray ingredientsJsonArray = jsonObject.getJSONArray("ingredients");
        for (int i = 0; i < ingredientsJsonArray.length(); i++) {
            ingredients.add(new Ingredient(ingredientsJsonArray.getJSONObject(i)));
        }
        this.steps = new ArrayList<>();
        JSONArray stepsJsonArray = jsonObject.getJSONArray("steps");
        for (int i = 0; i < stepsJsonArray.length(); i++) {
            steps.add(new Step(stepsJsonArray.getJSONObject(i)));
        }
        this.servings = jsonObject.getInt("servings");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeList(ingredients);
        dest.writeList(steps);
        dest.writeValue(servings);
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }
}
