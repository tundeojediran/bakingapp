package udacity.alc.dannytee.bakingapp.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import udacity.alc.dannytee.bakingapp.R;
import udacity.alc.dannytee.bakingapp.fragments.StepDetailsFragment;

public class StepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            StepDetailsFragment stepsDetailsFragment = new StepDetailsFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.steps_details_frame, stepsDetailsFragment)
                    .commit();
        }
    }
}
