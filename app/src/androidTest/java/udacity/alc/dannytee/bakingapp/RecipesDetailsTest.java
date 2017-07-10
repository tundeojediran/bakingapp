package udacity.alc.dannytee.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import udacity.alc.dannytee.bakingapp.activities.RecipeDetailsActivity;
import udacity.alc.dannytee.bakingapp.activities.StepsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by dannytee on 09/07/2017.
 */

@RunWith(AndroidJUnit4.class)
public class RecipesDetailsTest {

    @Rule public ActivityTestRule<RecipeDetailsActivity> mActivityTestRule = new ActivityTestRule<>(RecipeDetailsActivity.class);


    @Test
    public void recipesDetails(){
        onView(withId(R.id.ingredients_list)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }



}
