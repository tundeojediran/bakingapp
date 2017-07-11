package udacity.alc.dannytee.bakingapp;

import android.os.SystemClock;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import udacity.alc.dannytee.bakingapp.activities.RecipeDetailsActivity;
import udacity.alc.dannytee.bakingapp.activities.StepsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


/**
 * Created by dannytee on 09/07/2017.
 */

@RunWith(AndroidJUnit4.class)
public class StepsActivityTest {

    @Rule public ActivityTestRule<StepsActivity> mActivityTestRule = new ActivityTestRule<>(StepsActivity.class);

//    @Test
//    public void stepsTest(){
//        onView(allOf(withId(R.id.next_button), isCompletelyDisplayed()))
//                .perform(click());
//    }

}
