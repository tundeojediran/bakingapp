package udacity.alc.dannytee.bakingapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import udacity.alc.dannytee.bakingapp.fragments.IngredientFragment;
import udacity.alc.dannytee.bakingapp.fragments.StepsFragment;

/**
 * Created by dannytee on 25/06/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                IngredientFragment ingredientFragment = new IngredientFragment();
                return ingredientFragment;
            case 1:
                StepsFragment stepsFragment = new StepsFragment();
                return stepsFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "INGREDIENTS";
            case 1:
                return "STEPS";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
