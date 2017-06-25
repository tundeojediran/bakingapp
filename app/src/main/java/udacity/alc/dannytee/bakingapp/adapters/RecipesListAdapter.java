package udacity.alc.dannytee.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.alc.dannytee.bakingapp.R;
import udacity.alc.dannytee.bakingapp.models.Recipe;
import udacity.alc.dannytee.bakingapp.models.RecipesResponse;

/**
 * Created by dannytee on 25/06/2017.
 */

public class RecipesListAdapter extends RecyclerView.Adapter<RecipesListAdapter.RecipeViewHolder> {

    private ListItemClickListener mOnClickListener;
    private List<Recipe> mRecipes;
    private List<Integer> mImages;

    public RecipesListAdapter(ListItemClickListener mOnClickListener, List<Recipe> mRecipes, List<Integer> mImages) {
        this.mOnClickListener = mOnClickListener;
        this.mRecipes = mRecipes;
        this.mImages = mImages;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutIdForListItem = R.layout.item_recipes;
        boolean shouldAttachToParent = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParent);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mRecipes == null) return 0;
        return mRecipes.size();
    }

    public interface ListItemClickListener {
        void onClick(int clickedItemIndex);
    }


    class RecipeViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.thumbnail) ImageView recipeImage;
        @BindView(R.id.title) TextView recipeTitle;
        @BindView(R.id.servings) TextView servings;
        @BindView(R.id.card_view) CardView cardView;


        public RecipeViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void onBind(int position) {
            if (!mRecipes.isEmpty()) {
                Picasso.with(itemView.getContext()).load(mImages.get(position)).into(recipeImage);
                recipeTitle.setText(mRecipes.get(position).getName());
                servings.setText(itemView.getContext().getString(R.string.number_of_servings) + " " + mRecipes.get(position).getServings());
            }
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onClick(clickedPosition);
        }
    }
}
