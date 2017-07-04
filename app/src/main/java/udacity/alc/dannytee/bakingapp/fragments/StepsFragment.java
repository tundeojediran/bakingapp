package udacity.alc.dannytee.bakingapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import udacity.alc.dannytee.bakingapp.R;
import udacity.alc.dannytee.bakingapp.activities.StepsActivity;
import udacity.alc.dannytee.bakingapp.adapters.StepsAdapter;
import udacity.alc.dannytee.bakingapp.models.Step;

import static udacity.alc.dannytee.bakingapp.activities.RecipesActivity.isDualPane;
import static udacity.alc.dannytee.bakingapp.fragments.RecipesFragment.mRecipes;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepsFragment extends Fragment implements StepsAdapter.ListItemClickListener {

    private RecyclerView stepsRecyclerView;
    private StepsAdapter stepsAdapter;
    private int index = 0;

    public static List<Step> mSteps = new ArrayList<>();

    public StepsFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        index = getActivity().getIntent().getExtras().getInt(getString(R.string.extra));
        mSteps = mRecipes.get(index).getSteps();

        stepsRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_steps, container, false);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        stepsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        stepsRecyclerView.setHasFixedSize(true);
        stepsAdapter = new StepsAdapter(this, mSteps, getActivity());
        stepsRecyclerView.setAdapter(stepsAdapter);

        return stepsRecyclerView;

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (!isDualPane) {
            Intent intent = new Intent(getActivity(), StepsActivity.class);
            intent.putExtra("item", clickedItemIndex);
            Log.d("item", clickedItemIndex+"");
            startActivity(intent);
        } else {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            StepDetailsFragment stepsDetailsFragment = new StepDetailsFragment();
            StepDetailsFragment.index = clickedItemIndex;
            fragmentManager.beginTransaction()
                    .replace(R.id.steps_details_frame, stepsDetailsFragment)
                    .commit();
        }
    }
}
