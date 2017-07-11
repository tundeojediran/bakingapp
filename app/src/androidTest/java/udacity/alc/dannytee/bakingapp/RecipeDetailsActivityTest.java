package udacity.alc.dannytee.bakingapp;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import udacity.alc.dannytee.bakingapp.activities.RecipeDetailsActivity;
import udacity.alc.dannytee.bakingapp.activities.StepsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by dannytee on 11/07/2017.
 */

public class RecipeDetailsActivityTest {

    @Rule
    public ActivityTestRule<RecipeDetailsActivity> mActivityTestRule = new ActivityTestRule<>(RecipeDetailsActivity.class);


//    @Test
//    public void recipeDetailsTest(){
//        onView(withId(R.id.pager)).perform(swipeLeft());
//
//    }
}
