package udacity.alc.dannytee.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import udacity.alc.dannytee.bakingapp.R;
import udacity.alc.dannytee.bakingapp.models.Ingredient;

/**
 * Created by dannytee on 25/06/2017.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {


    private final List<Ingredient> mIngredients;

    public IngredientAdapter(List<Ingredient> ingredients) {
        mIngredients = ingredients;
    }

    @Override
    public IngredientAdapter.IngredientViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutIdFromParent = R.layout.ingredient_list_item;
        boolean shouldAttachToParent = false;

        View view = inflater.inflate(layoutIdFromParent, viewGroup, shouldAttachToParent);

        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientAdapter.IngredientViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mIngredients == null) return 0;
        return mIngredients.size();
    }


    class IngredientViewHolder extends RecyclerView.ViewHolder{

        final TextView ingredientTextView;
        final TextView measureTextView;
        final TextView quantityTextView;


        public IngredientViewHolder(View itemView) {
            super(itemView);

            ingredientTextView = (TextView) itemView.findViewById(R.id.ingredient_text);
            measureTextView = (TextView) itemView.findViewById(R.id.measure_value);
            quantityTextView = (TextView) itemView.findViewById(R.id.quantity_value);
        }

        void onBind(int position) {
            if (!mIngredients.isEmpty()) {
                ingredientTextView.setText(mIngredients.get(position).getIngredient());
                measureTextView.setText(mIngredients.get(position).getMeasure());
                quantityTextView.setText(String.valueOf(mIngredients.get(position).getQuantity()));
            }
        }
    }
}
