package udacity.alc.dannytee.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import udacity.alc.dannytee.bakingapp.activities.RecipesActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by dannytee on 03/07/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeActivityTest {

    public static final String RECIPE_TEST_TEXT = "Nutella Pie";
    @Rule
    public ActivityTestRule<RecipesActivity> mMainActivityTestRule = new ActivityTestRule<>(RecipesActivity.class);

    @Test
    public void mainActivityTest(){
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.recipes_list), withParent(allOf(withId(R.id.recipes_portrait), withParent(withId(android.R.id.content)))),
                isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }


}
