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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import udacity.alc.dannytee.bakingapp.R;
import udacity.alc.dannytee.bakingapp.activities.RecipeDetailsActivity;
import udacity.alc.dannytee.bakingapp.adapters.RecipesListAdapter;
import udacity.alc.dannytee.bakingapp.api.RecipeService;
import udacity.alc.dannytee.bakingapp.api.ServiceGenerator;
import udacity.alc.dannytee.bakingapp.models.Recipe;

import static udacity.alc.dannytee.bakingapp.activities.RecipesActivity.isDualPane;


/**
 * Created by dannytee on 25/06/2017.
 */

public class RecipesFragment extends Fragment implements RecipesListAdapter.ListItemClickListener {

    public static final String TAG = RecipesFragment.class.getSimpleName();
    public static ArrayList<Recipe> mRecipes;
    @BindView(R.id.recipes_list) RecyclerView recyclerView;
    private RecipesListAdapter recipesListAdapter;
    private ArrayList<Integer> mImages;
    private ProgressDialog dialog;

    private RecipeService recipeService;
    private static final String RECIPES_LIST = "recipes_list";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipes, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        if (savedInstanceState != null){
            mRecipes = savedInstanceState.getParcelableArrayList(RECIPES_LIST);
//           loadRecipesData();
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        dialog = new ProgressDialog(getActivity());

        mImages = new ArrayList<>();
        mImages.add(R.mipmap.nutellapie);
        mImages.add(R.mipmap.brownies);
        mImages.add(R.mipmap.cheesecake);
        mImages.add(R.mipmap.yellowcake);

        loadRecipes();



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
//                           mMovieAdapter.setMovieData(mResponseMovieItems);

                           loadRecipesData();



                       } catch (Exception e) {
                           Log.d("onResponse", "There is an error");
                           e.printStackTrace();
                       }

                   } else {
//                       mLoadingIndicator.setVisibility(View.INVISIBLE);
//                       showErrorMessage();
                       Log.d("response error", "response error");

                   }

               }

               @Override
               public void onFailure(Call<List<Recipe>> call, Throwable t) {
                   Log.d("onFailure", t.toString());
                   if (dialog.isShowing()) {
                       dialog.dismiss();
                   }


               }
           });
       }

    }


    private void loadRecipesData() {
        if (isDualPane) {
            if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
            }
            else{
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3));
            }
        } else {
            if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1));
            }
            else{
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
            }
        }
        recipesListAdapter = new RecipesListAdapter(this, mRecipes, mImages);
        recyclerView.setAdapter(recipesListAdapter);

    }

    @Override
    public void onClick(int clickedItemIndex) {
        Intent intent = new Intent(getActivity(), RecipeDetailsActivity.class);
        intent.putExtra("item", clickedItemIndex);
        startActivity(intent);
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
//           loadRecipesData();
        }

    }



}
