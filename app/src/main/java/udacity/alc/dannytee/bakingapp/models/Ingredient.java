package udacity.alc.dannytee.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by dannytee on 25/06/2017.
 */

public class Ingredient implements Parcelable {

    private Integer quantity;
    private String measure;
    private String ingredient;
    public final static Parcelable.Creator<Ingredient> CREATOR = new Creator<Ingredient>() {


        @Override
        public Ingredient createFromParcel(Parcel parcel) {
            return null;
        }

        public Ingredient[] newArray(int size) {
            return (new Ingredient[size]);
        }

    }
            ;

    /**
     * No args constructor for use in serialization
     *
     * @param jsonObject
     */
    public Ingredient(JSONObject jsonObject) {
    }

    /**
     *
     * @param measure
     * @param ingredient
     * @param quantity
     */
    public Ingredient(Integer quantity, String measure, String ingredient) {
        super();
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }



    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(quantity);
        dest.writeValue(measure);
        dest.writeValue(ingredient);
    }

    public int describeContents() {
        return  0;
    }
}
