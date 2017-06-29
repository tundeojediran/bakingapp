package udacity.alc.dannytee.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import udacity.alc.dannytee.bakingapp.R;
import udacity.alc.dannytee.bakingapp.fragments.StepsFragment;
import udacity.alc.dannytee.bakingapp.models.Step;

/**
 * Created by dannytee on 25/06/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder>{

    private final StepsAdapter.ListItemClickListener mOnClickListener;
    private final List<Step> mSteps;

    public StepsAdapter(StepsAdapter.ListItemClickListener listener, List<Step> steps) {
        mOnClickListener = listener;
        mSteps = steps;

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

        final TextView stepText;
        final TextView stepText_id;
        final TextView descriptionText;


        public StepsViewHolder(View itemView) {
            super(itemView);

            stepText = (TextView) itemView.findViewById(R.id.step_text);
            stepText_id = (TextView) itemView.findViewById(R.id.step_id);
            descriptionText = (TextView) itemView.findViewById(R.id.step_description_short);
            itemView.setOnClickListener(this);
        }

        void onBind(int position) {
            if (!mSteps.isEmpty()) {
                stepText.setText(R.string.step_text);
                stepText_id.setText(String.valueOf(mSteps.get(position).getId() + 1));
                descriptionText.setText(mSteps.get(position).getShortDescription());
            }
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
