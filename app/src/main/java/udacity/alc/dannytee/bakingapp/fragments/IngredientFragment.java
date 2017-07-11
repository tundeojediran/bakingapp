package udacity.alc.dannytee.bakingapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import udacity.alc.dannytee.bakingapp.R;
import udacity.alc.dannytee.bakingapp.adapters.IngredientAdapter;

import static udacity.alc.dannytee.bakingapp.fragments.RecipesFragment.mRecipes;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientFragment extends Fragment {

    private RecyclerView recyclerView;
    private IngredientAdapter adapter;
    private int index = 0;


    public IngredientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_ingredient, container, false);

        return recyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        if (getActivity().getIntent() != null) {
            index = getActivity().getIntent().getExtras().getInt(getString(R.string.extra));
            getActivity().setTitle(mRecipes.get(index).getName());
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new IngredientAdapter(mRecipes.get(index).getIngredients());
        recyclerView.setAdapter(adapter);
    }
}
