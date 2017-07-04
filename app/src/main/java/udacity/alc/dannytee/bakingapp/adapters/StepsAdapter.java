package udacity.alc.dannytee.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.alc.dannytee.bakingapp.R;
import udacity.alc.dannytee.bakingapp.fragments.StepsFragment;
import udacity.alc.dannytee.bakingapp.models.Step;

/**
 * Created by dannytee on 25/06/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder>{

    private final StepsAdapter.ListItemClickListener mOnClickListener;
    private final List<Step> mSteps;
    private Context mContext;

    public StepsAdapter(StepsAdapter.ListItemClickListener listener, List<Step> steps, Context context) {
        mOnClickListener = listener;
        mSteps = steps;
        this.mContext = context;

    }



    @Override
    public StepsAdapter.StepsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutIdFromParent = R.layout.step_list_item;
        boolean shouldAttachToParent = false;

        View view = inflater.inflate(layoutIdFromParent, viewGroup, shouldAttachToParent);

        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsAdapter.StepsViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mSteps == null) return 0;
        return mSteps.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


    class StepsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.step_id) TextView stepText_id;
        @BindView(R.id.step_description_short) TextView descriptionText;
        @BindView(R.id.thumbnail_steps)
        ImageView thumbNailImageView;


        public StepsViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void onBind(int position) {
            if (!mSteps.isEmpty()) {
                stepText_id.setText(R.string.step_text + String.valueOf(mSteps.get(position).getId() + 1));
                descriptionText.setText(mSteps.get(position).getShortDescription());

                if(mSteps.get(position).getThumbnailURL() != "") {
                    Picasso.with(mContext)
                            .load(mSteps.get(position).getThumbnailURL())
                            .placeholder(R.drawable.border)
                            .resize(50, 50)
                            .centerCrop()
                            .into(thumbNailImageView);
                }

            }
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
