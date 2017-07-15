package udacity.alc.dannytee.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import udacity.alc.dannytee.bakingapp.fragments.RecipesFragment;
import udacity.alc.dannytee.bakingapp.models.Recipe;

/**
 * Created by dannytee on 25/06/2017.
 */

public class RecipesListAdapter extends RecyclerView.Adapter<RecipesListAdapter.RecipeViewHolder> {

    private final ListItemClickListener mOnClickListener;
    private final List<Recipe> mRecipes;

    public RecipesListAdapter(ListItemClickListener mOnClickListener, List<Recipe> mRecipes) {
        this.mOnClickListener = mOnClickListener;
        this.mRecipes = mRecipes;
    }


    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutIdForListItem = R.layout.item_recipes;
        boolean shouldAttachToParent = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParent);

        return new RecipeViewHolder(view);
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

        @BindView(R.id.thumbnail)
        ImageView recipeImage;
        @BindView(R.id.title)
        TextView recipeTitle;
        @BindView(R.id.servings)
        TextView servings;
        @BindView(R.id.card_view)
        CardView cardView;


        public RecipeViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void onBind(int position) {
            if (!mRecipes.isEmpty()) {

                String imagePath = mRecipes.get(position).getImage();
                if (TextUtils.isEmpty(imagePath)) {
                    Picasso
                            .with(itemView.getContext())
                            .cancelRequest(recipeImage);

                    recipeImage.setImageDrawable(null);

                } else {
                    Picasso
                            .with(itemView.getContext())
                            .load(mRecipes.get(position)
                                    .getImage()).error(R.mipmap.error).into(recipeImage);

                }

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
