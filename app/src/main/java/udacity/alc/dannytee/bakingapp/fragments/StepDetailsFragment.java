package udacity.alc.dannytee.bakingapp.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.alc.dannytee.bakingapp.R;
import udacity.alc.dannytee.bakingapp.activities.StepsActivity;
import udacity.alc.dannytee.bakingapp.models.Step;

import static udacity.alc.dannytee.bakingapp.activities.RecipesActivity.isDualPane;
import static udacity.alc.dannytee.bakingapp.fragments.StepsFragment.mSteps;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepDetailsFragment extends Fragment {

    private static final String TAG = StepsActivity.class.getSimpleName();
    public static int index = 0;
    @BindView(R.id.text_description)
    TextView longDescription;


    @BindView(R.id.prev_button)
    Button prev;

    @BindView(R.id.next_button)
    Button next;

    @BindView(R.id.playerView)
    SimpleExoPlayerView playerView;


    @BindView(R.id.playerView_no_video)
    ImageView mNoVideoImageView;

    private View rootView;
    private SimpleExoPlayer player;
    private Step mStep;

    boolean playWhenReady = false;
    long playbackPosition = 0;
    int currentWindow = 0;

    public StepDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_step_details, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initializePlayer();//hash this line


        if (!isDualPane) {
            index = getActivity().getIntent().getExtras().getInt("item");
        }


        getActivity().setTitle(mSteps.get(index).getShortDescription());
        longDescription.setText(mSteps.get(index).getDescription());

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    index--;
                    Log.d("index", index + "");
                    longDescription.setText(mSteps.get(index).getDescription());
                    getActivity().setTitle(mSteps.get(index).getShortDescription());

                    player.stop();
                    initializePlayer();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < mSteps.size() - 1) {
                    index++;
                    Log.d("index", index + "");
                    longDescription.setText(mSteps.get(index).getDescription());
                    getActivity().setTitle(mSteps.get(index).getShortDescription());

                    player.stop();
                    initializePlayer();

                }
            }
        });

//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && !isDualPane) {
//            hideSystemUI();
//            playerView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
//            restExoPlayer(position, true);
//            longDescription.setVisibility(View.GONE);
//            prev.setVisibility(View.GONE);
//            next.setVisibility(View.GONE);
//            guideline.setVisibility(View.GONE);
//        }

    }


    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);

        //set the video URL
        if (mSteps.get(index) != null) {
            mStep = mSteps.get(index);
            if (mStep.hasVideo()) {
                mNoVideoImageView.setVisibility(View.GONE);
                playerView.setVisibility(View.VISIBLE);
                Uri uri = Uri.parse(mStep.getVideoURL());
                MediaSource mediaSource = buildMediaSource(uri);
                player.prepare(mediaSource, true, false);

            }
            else {
                playerView.setVisibility(View.GONE);
                mNoVideoImageView.setVisibility(View.VISIBLE);
                Picasso.with(getActivity()).load(R.mipmap.no_video).fit().into(mNoVideoImageView);

            }
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory("ua"),
                new DefaultExtractorsFactory(), null, null);
    }



    private void hideSystemUI() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }


    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

}
