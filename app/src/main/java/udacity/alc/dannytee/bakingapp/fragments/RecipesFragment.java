package udacity.alc.dannytee.bakingapp.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import udacity.alc.dannytee.bakingapp.LastIngredientPreference;
import udacity.alc.dannytee.bakingapp.R;
import udacity.alc.dannytee.bakingapp.activities.RecipeDetailsActivity;
import udacity.alc.dannytee.bakingapp.adapters.RecipesListAdapter;
import udacity.alc.dannytee.bakingapp.api.RecipeService;
import udacity.alc.dannytee.bakingapp.api.ServiceGenerator;
import udacity.alc.dannytee.bakingapp.models.Ingredient;
import udacity.alc.dannytee.bakingapp.models.Recipe;

import static udacity.alc.dannytee.bakingapp.activities.RecipesActivity.isDualPane;


/**
 * Created by dannytee on 25/06/2017.
 */

public class RecipesFragment extends Fragment implements RecipesListAdapter.ListItemClickListener {

    public static final String TAG = RecipesFragment.class.getSimpleName();
    public static ArrayList<Recipe> mRecipes;
    @BindView (R.id.recipes_list) RecyclerView recyclerView;
    private RecipesListAdapter recipesListAdapter;
    private ProgressDialog dialog;

    public static int index = -1;
    public static int top = -1;
    private GridLayoutManager layoutManager;

    private RecipeService recipeService;
    private static final String RECIPES_LIST = "recipes_list";

    public static RecipesFragment newInstance() {
        RecipesFragment recipesFragment = new RecipesFragment();
//        Bundle args = new Bundle();
//        args.putInt("index", index);
//        f.setArguments(args);
        return recipesFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipes, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);



        dialog = new ProgressDialog(getActivity());


        if (savedInstanceState != null){
            mRecipes = savedInstanceState.getParcelableArrayList(RECIPES_LIST);
        } else {
            loadRecipes();
        }


//        loadRecipes();



    }

    // method to load recipes list
    private void loadRecipes() {
       if (isNetworkAvailable(getActivity())) {
           dialog.setMessage(getString(R.string.dialog_loading));
           dialog.show();

           recipeService = ServiceGenerator.createService(RecipeService.class);

           Call<List<Recipe>> recipesResponseCall = recipeService.getRecipes();
           recipesResponseCall.enqueue(new Callback<List<Recipe>>() {
               @Override
               public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {

                   if (response.isSuccessful()) {
                       if (dialog.isShowing()) {
                           dialog.dismiss();
                       }

                       try {
                           mRecipes = (ArrayList<Recipe>) response.body();
                           Log.d("response body", mRecipes.size()+"");

                           loadRecipesData();


                       } catch (Exception e) {
                           Log.d("onResponse", "There is an error");
                           e.printStackTrace();
                       }

                   } else {

                       Log.d("response error", "response error");

                   }
               }

               @Override
               public void onFailure(Call<List<Recipe>> call, Throwable t) {
                   Log.d("onFailure", t.toString());
                   if (dialog.isShowing()) {
                       dialog.dismiss();
                       Toast.makeText(getActivity(), getString(R.string.error_fetching_recipes), Toast.LENGTH_LONG).show();
                   }


               }
           });
       } else {
           // network error message
           Toast.makeText(getActivity(), getString(R.string.no_internet_connection_error_message), Toast.LENGTH_LONG).show();

       }

    }


    private void loadRecipesData() {
        if (isDualPane) {
            if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                layoutManager = new GridLayoutManager(recyclerView.getContext(), 2);
                recyclerView.setLayoutManager(layoutManager);
            }
            else{
                layoutManager = new GridLayoutManager(recyclerView.getContext(), 3);
                recyclerView.setLayoutManager(layoutManager);
            }
        } else {
            if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                layoutManager = new GridLayoutManager(recyclerView.getContext(), 1);
                recyclerView.setLayoutManager(layoutManager);
            }
            else{
                layoutManager = new GridLayoutManager(recyclerView.getContext(), 2);
                recyclerView.setLayoutManager(layoutManager);
            }
        }
        recipesListAdapter = new RecipesListAdapter(this, mRecipes);
        recyclerView.setAdapter(recipesListAdapter);

    }

    @Override
    public void onClick(int clickedItemIndex) {
        Intent intent = new Intent(getActivity(), RecipeDetailsActivity.class);
        intent.putExtra("item", clickedItemIndex);
        saveTouchedRecipeIngredient(mRecipes.get(clickedItemIndex).getIngredients());
        startActivity(intent);
    }


    private void saveTouchedRecipeIngredient(List<Ingredient> ingredients){
        String formattedIngredient;
        double quantity;
        String measure;
        String ingredientDetails;
        String finalFormattedString = "";

        for (Ingredient ingredient : ingredients){
            formattedIngredient  = getActivity().getString(R.string.bullet);
            quantity = ingredient.getQuantity();
            measure = ingredient.getMeasure();
            ingredientDetails = ingredient.getIngredient();
            formattedIngredient += " " + ingredientDetails + " ("+ quantity +" " + measure + ")";
            finalFormattedString += formattedIngredient+ "\n\n";

        }

        LastIngredientPreference.setIngredientPreference(getActivity(), finalFormattedString);
    }

    private static boolean isNetworkAvailable(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mRecipes != null){
            outState.putParcelableArrayList(RECIPES_LIST, mRecipes);
        }

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null){
            mRecipes = savedInstanceState.getParcelableArrayList(RECIPES_LIST);
           loadRecipesData();
        }

    }

    @Override
    public void onPause()
    {
        super.onPause();
        //read current recyclerview position
        index = layoutManager.findFirstVisibleItemPosition();
        View v = recyclerView.getChildAt(0);
        top = (v == null) ? 0 : (v.getTop() - recyclerView.getPaddingTop());
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //set recyclerview position
        if(index != -1)
        {
            layoutManager.scrollToPositionWithOffset(index, top);
        }
    }

//    public boolean isSyncFinished() {
//        return getActivity().recipesListAdapter != null;
//
//    }

}
