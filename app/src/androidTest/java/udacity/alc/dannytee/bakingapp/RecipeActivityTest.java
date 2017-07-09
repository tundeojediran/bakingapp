package udacity.alc.dannytee.bakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import udacity.alc.dannytee.bakingapp.activities.RecipesActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by dannytee on 03/07/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeActivityTest {

    public static final String NUTELLA_TEST_TEXT = "Nutella Pie";
    public static final String BROWNIES_TEST_TEXT = "Brownies";
    @Rule
    public ActivityTestRule<RecipesActivity> mActivityTestRule = new ActivityTestRule<>(RecipesActivity.class);
    private BakingIdlingResource idlingResource;

    @Before
    public void registerIntentServiceIdlingResource() {
        RecipesActivity activity = mActivityTestRule.getActivity();
        idlingResource = new BakingIdlingResource(activity);
        Espresso.registerIdlingResources(idlingResource);
    }


//    @Test
//    public void nutellaTitleTest(){
//        // First scroll to the position that needs to be matched and click on it.
//        onView(allOf(withId(R.id.recipes_list), withParent(allOf(withId(R.id.recipes_portrait), withParent(withId(android.R.id.content)))),
//                isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click())).check(matches(withText(NUTELLA_TEST_TEXT)));
//    }


//    @Test
//    public void browniesTitleTest(){
//        // First scroll to the position that needs to be matched and click on it.
//        onView(allOf(withId(R.id.recipes_list), withParent(allOf(withId(R.id.recipes_portrait), withParent(withId(android.R.id.content)))),
//                isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1, click())).check(matches(withText(BROWNIES_TEST_TEXT)));
//    }



    @Test
    public void nutellaPieInActionBar(){
        // First scroll to the position that needs to be matched and click on it.
        final int BROWNIES_POSITION = 0;
        final String BROWNIES = "Nutella Pie";
        onView(withId(R.id.recipes_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(BROWNIES_POSITION, click()));
        onView(withText(BROWNIES) ).check(matches(isDisplayed()));
    }

    @Test
    public void browniesShowsInActionBar(){
        // First scroll to the position that needs to be matched and click on it.
        final int BROWNIES_POSITION = 1;
        final String BROWNIES = "Brownies";
        onView(withId(R.id.recipes_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(BROWNIES_POSITION, click()));
        onView(withText(BROWNIES) ).check(matches(isDisplayed()));
    }


    @After
    public void unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource);

    }




}
