package udacity.alc.dannytee.bakingapp;

import android.support.test.espresso.IdlingResource;

import udacity.alc.dannytee.bakingapp.activities.RecipesActivity;

/**
 * Created by dannytee on 09/07/2017.
 */

public class BakingIdlingResource implements IdlingResource {

    private RecipesActivity recipesActivity;
    private IdlingResource.ResourceCallback callback;

    public BakingIdlingResource(RecipesActivity activity) {
        this.recipesActivity = activity;
    }

    @Override
    public String getName() {
        return BakingIdlingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        Boolean idle = isIdle();
        if (idle) callback.onTransitionToIdle();
        return idle;
    }

    public boolean isIdle() {
        return recipesActivity != null && callback != null && recipesActivity.isSyncFinished();
    }

    @Override
    public void registerIdleTransitionCallback(IdlingResource.ResourceCallback resourceCallback) {
        this.callback = resourceCallback;
    }


}
