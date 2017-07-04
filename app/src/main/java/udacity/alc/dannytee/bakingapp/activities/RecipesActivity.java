package udacity.alc.dannytee.bakingapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import udacity.alc.dannytee.bakingapp.R;
import udacity.alc.dannytee.bakingapp.fragments.RecipesFragment;

public class RecipesActivity extends AppCompatActivity {

    public static boolean isDualPane = false;
    private RecipesFragment recipesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        if (savedInstanceState == null) {

            if (findViewById(R.id.recipes_landscape) != null) {
                isDualPane = true;
                FragmentManager fragmentManager = getSupportFragmentManager();
                recipesFragment = new RecipesFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.recipes_landscape, recipesFragment)
                        .commit();
            } else {
                FragmentManager fragmentManager = getSupportFragmentManager();
                 recipesFragment = new RecipesFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.recipes_portrait, recipesFragment)
                        .commit();
            }
        } else {
                recipesFragment = (RecipesFragment) getSupportFragmentManager().getFragment(savedInstanceState, "recipesFrag");
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's instance
        getSupportFragmentManager().putFragment(outState, "recipesFrag", recipesFragment);
    }



}
